本项目利用微服务架构完成登录注册功能，具体数据库表字段和逻辑没有完成，只是搭建了一个基本框架。
项目结构如下：  
service-common包含一些服务模块的公有包，每次进行修改的时候要使用Maven的install重新安装一下。  
service-gateway是微服务架构中的网关，所有请求都经过网关转发都后端服务，因此可以在里面完成权限过滤。
service-user是用户模块，负责处理用户部分的逻辑。

### 项目启动过程  
    1.在IDEA中直接打开项目，选择用Maven导入
    2.在IDEA右侧选项卡中的Maven Project，展开gongyijia选项，展开Lifecycle选项，    
    双击install完成整体项目的构建。
    3.展开service-common选项，和2一样，双击install完成common的构建。
    4.启动Consul注册中心。（Consul需要另外下载，可自行百度启动命令）
    5.更改gateway和user模块下的配置文件application-dev.yml和application-prod.yml(具体运行的时候使用哪个配置  
    文件取决于application.yml中的spring.profiles.active字段)，修改mysql的主机地址和用户名密码以及Consul的主机地址，  
    启动gateway和user模块即可。  
    

### 微服务访问URL  
   {Zuul网关的服务器地址}:{Zuul网关启动的端口}/{微服务模块名称，在各模块的配置文件application.yml中}/{具体请求路径}


