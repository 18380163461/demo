package com.github.pagehelper;


import com.github.pagehelper.cache.Cache;
import com.github.pagehelper.cache.CacheFactory;
import com.github.pagehelper.util.MSUtils;
import com.github.pagehelper.util.StringUtil;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts({@Signature(
    type = Executor.class,
    method = "query",
    args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
), @Signature(
    type = Executor.class,
    method = "query",
    args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
)})
public class PageInterceptor implements Interceptor {
  protected Cache<String, MappedStatement> msCountMap = null;
  private Dialect dialect;
  private String default_dialect_class = "com.github.pagehelper.PageHelper";
  private Field additionalParametersField;
  private String countSuffix = "_COUNT";

  public PageInterceptor() {
  }

  public Object intercept(Invocation invocation) throws Throwable {
    Object var22;
    try {
      Object[] args = invocation.getArgs();
      MappedStatement ms = (MappedStatement)args[0];
      Object parameter = args[1];
      RowBounds rowBounds = (RowBounds)args[2];
      ResultHandler resultHandler = (ResultHandler)args[3];
      Executor executor = (Executor)invocation.getTarget();
      CacheKey cacheKey;
      BoundSql boundSql;
      if (args.length == 4) {//对重载支持
        boundSql = ms.getBoundSql(parameter);
        cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
      } else {
        cacheKey = (CacheKey)args[4];
        boundSql = (BoundSql)args[5];
      }

      List resultList;
      if (this.dialect.skip(ms, parameter, rowBounds)) {//判断是否需要跳过分页，如果不需要分页直接返回查询结果
        resultList = executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
      } else {//
        String msId = ms.getId();
        Configuration configuration = ms.getConfiguration();
        //反射获取的boundSql的additionalParameters ,additionalParameters是本来就带的参数
        Map<String, Object> additionalParameters = (Map)this.additionalParametersField.get(boundSql);
        if (this.dialect.beforeCount(ms, parameter, rowBounds)) {//判断是否需要count查询
          String countMsId = msId + this.countSuffix;
          MappedStatement countMs = this.getExistedMappedStatement(configuration, countMsId);
          Long count;
          if (countMs != null) {
            count = this.executeManualCount(executor, countMs, parameter, boundSql, resultHandler);
          } else {
            countMs = (MappedStatement)this.msCountMap.get(countMsId);
            if (countMs == null) {
              countMs = MSUtils.newCountMappedStatement(ms, countMsId);
              this.msCountMap.put(countMsId, countMs);
            }
            //执行count查询
            count = this.executeAutoCount(executor, countMs, parameter, boundSql, rowBounds, resultHandler);
          }
          //保存count 到page对象里，在判断是否需要继续查询数据
          if (!this.dialect.afterCount(count, parameter, rowBounds)) {
            //count查询为0，直接返回空集合
            Object var24 = this.dialect.afterPage(new ArrayList(), parameter, rowBounds);
            return var24;
          }
        }
        //判断是否需要分页
        if (!this.dialect.beforePage(ms, parameter, rowBounds)) {
          resultList = executor.query(ms, parameter, RowBounds.DEFAULT, resultHandler, cacheKey, boundSql);
        } else {
          parameter = this.dialect.processParameterObject(ms, parameter, boundSql, cacheKey);
          //通过数据库方言，生成分页sql
          String pageSql = this.dialect.getPageSql(ms, boundSql, parameter, rowBounds, cacheKey);
          //把sql和参数绑定
          BoundSql pageBoundSql = new BoundSql(configuration, pageSql, boundSql.getParameterMappings(), parameter);
          Iterator var17 = additionalParameters.keySet().iterator();

          while(true) {//遍历additionalParameters，把所有参数放到 pageBoundSql 对象中。
            if (!var17.hasNext()) {//执行结果集的查询
              resultList = executor.query(ms, parameter, RowBounds.DEFAULT, resultHandler, cacheKey, pageBoundSql);
              break;
            }

            String key = (String)var17.next();
            pageBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
          }
        }
      }
      //保存查询结果到page对象里
      var22 = this.dialect.afterPage(resultList, parameter, rowBounds);
    } finally {
      //clear 使用的时候
      this.dialect.afterAll();
    }
    return var22;
  }

  private Long executeManualCount(Executor executor, MappedStatement countMs, Object parameter, BoundSql boundSql, ResultHandler resultHandler) throws IllegalAccessException, SQLException {
    CacheKey countKey = executor.createCacheKey(countMs, parameter, RowBounds.DEFAULT, boundSql);
    BoundSql countBoundSql = countMs.getBoundSql(parameter);
    Object countResultList = executor.query(countMs, parameter, RowBounds.DEFAULT, resultHandler, countKey, countBoundSql);
    Long count = ((Number)((List)countResultList).get(0)).longValue();
    return count;
  }

  private Long executeAutoCount(Executor executor, MappedStatement countMs, Object parameter, BoundSql boundSql, RowBounds rowBounds, ResultHandler resultHandler) throws IllegalAccessException, SQLException {
    Map<String, Object> additionalParameters = (Map)this.additionalParametersField.get(boundSql);
    CacheKey countKey = executor.createCacheKey(countMs, parameter, RowBounds.DEFAULT, boundSql);
    //通过数据库方言获取count查询语句
    String countSql = this.dialect.getCountSql(countMs, boundSql, parameter, rowBounds, countKey);
    //把count查询的sql和占位符的参数进行绑定，生成BoundSql
    BoundSql countBoundSql = new BoundSql(countMs.getConfiguration(), countSql, boundSql.getParameterMappings(), parameter);
    Iterator var11 = additionalParameters.keySet().iterator();

    while(var11.hasNext()) {
      String key = (String)var11.next();
      countBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
    }
    //count查询获取总条数
    Object countResultList = executor.query(countMs, parameter, RowBounds.DEFAULT, resultHandler, countKey, countBoundSql);
    Long count = (Long)((List)countResultList).get(0);
    return count;
  }

  private MappedStatement getExistedMappedStatement(Configuration configuration, String msId) {
    MappedStatement mappedStatement = null;

    try {
      mappedStatement = configuration.getMappedStatement(msId, false);
    } catch (Throwable var5) {
    }

    return mappedStatement;
  }

  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  public void setProperties(Properties properties) {
    this.msCountMap = CacheFactory.createCache(properties.getProperty("msCountCache"), "ms", properties);
    String dialectClass = properties.getProperty("dialect");
    if (StringUtil.isEmpty(dialectClass)) {
      dialectClass = this.default_dialect_class;
    }

    try {
      Class<?> aClass = Class.forName(dialectClass);
      this.dialect = (Dialect)aClass.newInstance();
    } catch (Exception var6) {
      throw new PageException(var6);
    }

    this.dialect.setProperties(properties);
    String countSuffix = properties.getProperty("countSuffix");
    if (StringUtil.isNotEmpty(countSuffix)) {
      this.countSuffix = countSuffix;
    }

    try {
      this.additionalParametersField = BoundSql.class.getDeclaredField("additionalParameters");
      this.additionalParametersField.setAccessible(true);
    } catch (NoSuchFieldException var5) {
      throw new PageException(var5);
    }
  }
}