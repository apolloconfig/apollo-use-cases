# Purpose

展示Apollo配置中心的各种使用场景和示例代码，目前包含了以下示例项目：

* [spring-boot-logger](spring-boot-logger)：演示[Spring Boot Logging](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-logging.html)如何通过Apollo配置中心实现动态调整Logging Level
* [spring-cloud-logger](spring-cloud-logger)：演示[Spring Boot Logging](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-logging.html)在Spring Cloud环境下如何通过Apollo配置中心方便地实现动态调整Logging Level
* [spring-cloud-zuul](spring-cloud-zuul)：演示[Spring Cloud Zuul](https://cloud.spring.io/spring-cloud-netflix/single/spring-cloud-netflix.html#netflix-zuul-reverse-proxy)如何通过Apollo配置中心实现动态路由
* [spring-cloud-zuul-ratelimit](spring-cloud-zuul-ratelimit)：演示[Spring Cloud Zuul](https://cloud.spring.io/spring-cloud-netflix/single/spring-cloud-netflix.html#netflix-zuul-reverse-proxy)的第三方限流插件[marcosbarbero/spring-cloud-zuul-ratelimit](https://github.com/marcosbarbero/spring-cloud-zuul-ratelimit)如何通过Apollo配置中心实现动态限流
* [spring-cloud-gateway](spring-cloud-gateway)：演示[Spring Cloud Gateway](https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.0.3.RELEASE/single/spring-cloud-gateway.html)如何通过Apollo配置中心实现动态路由
* [spring-boot-encrypt](spring-boot-encrypt)：演示如何结合[jasypt-spring-boot](https://github.com/ulisesbocchio/jasypt-spring-boot)实现Apollo中存储加密配置
* [dynamic-datasource](dynamic-datasource)：演示[Spring Boot默认的HikariCP DataSource](https://github.com/brettwooldridge/HikariCP)如何通过Apollo配置中心实现动态切换数据源（其它类型的DataSource也是类似的，可以依样画葫芦）
* [dubbo](dubbo): 演示[Dubbo](https://github.com/apache/incubator-dubbo)如何通过Apollo配置中心实现中心化配置
* [spring-boot-dubbo](spring-boot-dubbo): 演示[Dubbo Spring Boot Starter](https://github.com/apache/incubator-dubbo-spring-boot-project)如何通过Apollo配置中心实现中心化配置
  * 该项目同时也演示了如何通过apollo管理logback的配置，详见[logback-spring.xml](https://github.com/ctripcorp/apollo-use-cases/blob/master/spring-boot-dubbo/spring-boot-dubbo-provider/src/main/resources/logback-spring.xml)
* [netflix-archaius](netflix-archaius): 演示[Netflix Archaius](https://github.com/Netflix/archaius)如何使用Apollo配置中心作为其服务端使用
* [sentinel](sentinel): 演示[Sentinel](https://github.com/alibaba/Sentinel)如何通过Apollo配置中心实现中心化流控规则配置
* [properties-keeper](properties-keeper): 演示如何通过apollo管理启动前需要加载的properties文件配置
* [spring-boot-agent](spring-boot-agent): 演示如何通过java agent探针技术实现应用无缝接入Apollo配置中心

欢迎大家把日常工作中的更多配置使用案例分享出来，提交Pull Request即可！

# Instructions

1. 部署并启动Apollo配置中心
	* 请参考[分布式部署指南](https://github.com/ctripcorp/apollo/wiki/%E5%88%86%E5%B8%83%E5%BC%8F%E9%83%A8%E7%BD%B2%E6%8C%87%E5%8D%97)
	* 如果只是Demo用途的话，可以参考[Quick Start](https://github.com/ctripcorp/apollo/wiki/Quick-Start)文档快速地在本地启动一套Apollo配置中心，或者参考[Apollo开发指南](https://github.com/ctripcorp/apollo/wiki/Apollo%E5%BC%80%E5%8F%91%E6%8C%87%E5%8D%97)通过IDE在本地启动一套Apollo配置中心
2. 配置Apollo Meta信息
	* Apollo支持应用在不同的环境有不同的配置，所以需要配置Apollo Meta信息
	* 示例代码在`application.properties`或System Property配置了`apollo.meta=http://localhost:8080`，请根据实际部署情况调整该配置
3. 以上步骤都完成后，就可以参考各子模块的README.md来运行示例项目了
