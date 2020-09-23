<img src="https://asiainfo.hirede.com/Templates/asiainfo/Images/logo.png" style="padding:10px;background-color: #3e4145" width="200" alt="亚信科技"/>

# 新架构test
## 更新于2020年05月01日
* 建表语句在根目录下create_table.sql
* 配置中心使用nacos
* 服务注册和发现使用naco
* 数据库连接池使用druid
* dao层使用的mybatis
* API文档使用Swagger
* 分页使用PageHelper
* 权限控制Spring Security

## 页面和地址
* druid监控页面：http://127.0.0.1:8080/test/druid/index.html
* Swagger页面地址：http://localhost:8080/test/swagger-ui.html
* 启动本机redis命令：cd C:\1A\redis    redis-server.exe redis.windows.conf
* actuator 监控  http://localhost:8080/test/actuator/health
* 系统参数表结构：create table GRID_ASMT_PARAMS(  id          NUMBER(15) not null,  type        VARCHAR2(64),  type_desc   VARCHAR2(64),  param_level NUMBER(5),  code        VARCHAR2(64),  name        VARCHAR2(64),  parent_id   NUMBER(15),  state       NUMBER(5));