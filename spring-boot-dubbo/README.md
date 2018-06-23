# Purpose

演示[Dubbo Spring Boot Starter](https://github.com/apache/incubator-dubbo-spring-boot-project)如何通过Apollo配置中心实现中心化配置

# Instructions

## 服务端配置
1. 在Apollo配置中心创建AppId为`spring-boot-dubbo-provider`的项目
2. 在默认的`application`下做如下配置（可以通过文本模式直接复制、粘贴下面的内容）：

	```properties
	# Base packages to scan Dubbo Components (e.g @Service , @Reference)
	dubbo.scan.basePackages = com.ctrip.framework.apollo.use.cases.spring.boot.starter.dubbo.provider
		
	## ApplicationConfig Bean
	dubbo.application.name = spring-boot-dubbo-provider
		
	## RegistryConfig Bean
	dubbo.registry.protocol = zookeeper
	dubbo.registry.address = 127.0.0.1:2181
	```
3. 启动zookeeper
4. 运行`com.ctrip.framework.apollo.use.cases.spring.boot.starter.dubbo.provider.Server`启动Demo服务端

## 调用端配置
1. 在Apollo配置中心创建AppId为`spring-boot-dubbo-consumer`的项目
2. 在默认的`application`下做如下配置（可以通过文本模式直接复制、粘贴下面的内容）：

	```properties
	## ApplicationConfig Bean
	dubbo.application.name = spring-boot-dubbo-consumer

	## RegistryConfig Bean
	dubbo.registry.protocol = zookeeper
	dubbo.registry.address = 127.0.0.1:2181
	```
3. 运行`com.ctrip.framework.apollo.use.cases.spring.boot.starter.dubbo.consumer.Consumer`启动Demo调用端
4. 在调用端输入任意字符后按回车，即可发起一次Dubbo服务请求并输出服务端的响应
	* 如输入`dubbo`，服务端会响应`Hello dubbo`
