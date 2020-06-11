package com.example.demo.config;

import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class ControllerAspect {

  ThreadLocal<Long> startTime = new ThreadLocal<>();
  private Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

  // 定义切点Pointcut
  @Pointcut("execution(* com.example.demo.controller..*.*(..))")
  public void executeController() {
  }

  //@Around("executeController()")
  public Object Around(ProceedingJoinPoint pjp) throws Throwable {
    Object o = null;
    logger.info("-------------Around-----111-------------");
    o = pjp.proceed(pjp.getArgs());
    logger.info("-------------Around-----222-------------");
    return o;
  }

  @Before("executeController()")
  public void before(JoinPoint joinPoint) throws Exception {
    startTime.set(System.currentTimeMillis());
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
    HttpServletRequest request = servletRequestAttributes.getRequest();
    //这一步获取到的方法有可能是代理方法也有可能是真实方法
    Method m = ((MethodSignature) joinPoint.getSignature()).getMethod();
    //判断代理对象本身是否是连接点所在的目标对象，不是的话就要通过反射重新获取真实方法
    if (joinPoint.getThis().getClass() != joinPoint.getTarget().getClass()) {
      m = ReflectUtil.getMethod(joinPoint.getTarget().getClass(), m.getName(), m.getParameterTypes());
    }
    //通过真实方法获取该方法的参数名称
    LocalVariableTableParameterNameDiscoverer paramNames = new LocalVariableTableParameterNameDiscoverer();
    String[] parameterNames = paramNames.getParameterNames(m);
    //获取连接点方法运行时的入参列表
    Object[] args = joinPoint.getArgs();
    //将参数名称与入参值一一对应起来
//    Map<String, Object> params = new HashMap<>();
    //自己写的一个判空类方法
    if (!ObjectUtils.isEmpty(parameterNames)) {
      for (int i = 0; i < parameterNames.length; i++) {
        //这里加一个判断，如果使用requestParam接受参数，加了require=false，这里会存现不存在的现象
        if (ObjectUtils.isEmpty(args[i])) {
          continue;
        }
//        //通过所在类转换，获取值，包含各种封装类都可以
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.convertValue(args[i], args[i].getClass());
//        params.put(parameterNames[i], JSON.toJSON(objectMapper.convertValue(args[i], args[i].getClass())));
      }
    }
    logger.info("URL : " + request.getRequestURL().toString());
    logger.info("HTTP_METHOD : " + request.getMethod());
    logger.info("IP : " + request.getRemoteAddr());
    logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    //这里经过处理，就可以获得参数名字与值一一对应
//    logger.info("ARGS-JSON : " + params);
  }


  @AfterReturning(value = "executeController()", returning = "rtv")
  public void after(JoinPoint joinPoint, Object rtv) {
    logger.info("responseBody:" + JSON.toJSONString(rtv, SerializerFeature.WriteMapNullValue));
    logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
  }
}