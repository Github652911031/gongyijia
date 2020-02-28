本项目利用微服务架构完成登录注册功能，具体数据库表字段和逻辑没有完成，只是搭建了一个基本框架。
项目结构如下：  
service-common包含一些服务模块的公有包，每次进行修改的时候要使用Maven的install重新安装一下。  
service-gateway是微服务架构中的网关，所有请求都经过网关转发都后端服务，因此可以在里面完成权限过滤。
service-user是用户模块，负责处理用户部分的逻辑。

### 项目启动过程  
1.在IDEA中直接打开项目，选择用Maven导入  
2.在IDEA右侧选项卡中的Maven Project，展开gongyijia选项，展开Lifecycle选项，双击install完成整体项目的构建。  
    ![image](https://github.com/Github652911031/gongyijia/blob/master/image/%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200227230557.png)  
3.展开service-common选项，和2一样，双击install完成common的构建。
4.启动Consul注册中心。（Consul需要另外下载，可自行百度启动命令）
5.更改gateway和user模块下的配置文件application-dev.yml和application-prod.yml(具体运行的时候使用哪个配置  
文件取决于application.yml中的spring.profiles.active字段)，修改mysql的主机地址和用户名密码以及Consul的主机地址，  
启动gateway和user模块即可。  
    

### 微服务访问URL  
   {Zuul网关的服务器地址}:{Zuul网关启动的端口}/{微服务模块名称，在各模块的配置文件application.yml中}/{具体请求路径}


### Zuul网关流程
Zuul网关主要是鉴权，逻辑在filter包下的AccessFilter文件中的run函数中。流程如下：

1.首先判别请求的URL是否在白名单中，如果在白名单不需要鉴权（白名单在gateway模块的application.yml文件中配置）  
2.提取HTTP头部字段的accessToken，调用user模块的validToken进行判别token是否有效
3.如果是有效的token，会根据识别出来的结果中的type字段判定是用户还是社区类型，并在之后的HTTP请求头上填写字段值。  
4.在service-common模块下的com.zy.base.web包里实现了过滤器WebFilter，每次请求进来都会对请求的头信息h_userId和h_communityId  
提取，然后存在WebContext中。每次请求的时候如果想要获取登录id，看UserController的exampleUser和exampleCommunity  

### 用户注册流程
1.用户注册接口不需要走网关判别，因为没有权限的问题，所以需要在白名单中配置  
2.用户名和密码匹配后，调用JWTService的createToken方法生成一个Token，传入Map参数。  
该Map中需要放入：
type:是用户还是社区类型
id:用户的id或者社区的Id
state:token的状态
expire:过期时间
例子见UserController的accountLogin接口

### 数据库设计
本工程仅仅简单创建了几个数据库，由于使用SpringBoot JPA开发，因此可以根据Java中的实体在数据库中  
创建相应的表结构，但是在application-dev.yml中定义了spring.jpa.hibernate.ddl-auto为create，  
因此每次启动工程的时候都会把原来的表删除并重新创建表，同时导入import.sql文件中的数据。如果不需要每次  
都将数据清空，可以改为update  
数据库设计如下：
![image]()