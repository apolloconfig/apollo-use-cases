# Purpose

展示Apollo配置中心的各种使用场景和示例代码，目前包含了以下示例项目：

* [spring-boot-logger](spring-boot-logger)：演示[Spring Boot Logging](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-logging.html)如何通过Apollo配置中心实现动态调整Logging Level
* [spring-cloud-logger](spring-cloud-logger)：演示[Spring Boot Logging](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-logging.html)在Spring Cloud环境下如何通过Apollo配置中心方便地实现动态调整Logging Level
* [spring-cloud-zuul](spring-cloud-zuul)：演示[Spring Cloud Zuul](https://cloud.spring.io/spring-cloud-netflix/single/spring-cloud-netflix.html#netflix-zuul-reverse-proxy)如何通过Apollo配置中心实现动态路由
* [spring-boot-encrypt](spring-boot-encrypt)：演示如何结合[jasypt-spring-boot](https://github.com/ulisesbocchio/jasypt-spring-boot)实现Apollo中存储加密配置
* [dynamic-datasource](dynamic-datasource)：演示[Spring Boot默认的HikariCP DataSource](https://github.com/brettwooldridge/HikariCP)如何通过Apollo配置中心实现动态切换数据源（其它类型的DataSource也是类似的，可以依样画葫芦）
* [dubbo](dubbo): 演示[Dubbo](https://github.com/apache/incubator-dubbo)如何通过Apollo配置中心实现中心化配置
* [spring-boot-dubbo](spring-boot-dubbo): 演示[Dubbo Spring Boot Starter](https://github.com/apache/incubator-dubbo-spring-boot-project)如何通过Apollo配置中心实现中心化配置


欢迎大家把日常工作中的更多配置使用案例分享出来，提交Pull Request即可！

# Instructions

1. 部署并启动Apollo配置中心
	* 请参考[分布式部署指南](https://github.com/ctripcorp/apollo/wiki/%E5%88%86%E5%B8%83%E5%BC%8F%E9%83%A8%E7%BD%B2%E6%8C%87%E5%8D%97)
	* 如果只是Demo用途的话，可以参考[Quick Start](https://github.com/ctripcorp/apollo/wiki/Quick-Start)文档快速地在本地启动一套Apollo配置中心，或者参考[Apollo开发指南](https://github.com/ctripcorp/apollo/wiki/Apollo%E5%BC%80%E5%8F%91%E6%8C%87%E5%8D%97)通过IDE在本地启动一套Apollo配置中心
2. 部署Apollo客户端jar包
	* 如果在分布式部署过程中已经部署过Apollo客户端jar包，可以跳过此步骤
	* 如果没有部署过，请参考分布式部署指南中的[2.3 配置各环境meta service地址](https://github.com/ctripcorp/apollo/wiki/%E5%88%86%E5%B8%83%E5%BC%8F%E9%83%A8%E7%BD%B2%E6%8C%87%E5%8D%97#23-%E9%85%8D%E7%BD%AE%E5%90%84%E7%8E%AF%E5%A2%83meta-service%E5%9C%B0%E5%9D%80)、[2.4 执行编译、打包](https://github.com/ctripcorp/apollo/wiki/%E5%88%86%E5%B8%83%E5%BC%8F%E9%83%A8%E7%BD%B2%E6%8C%87%E5%8D%97#24-%E6%89%A7%E8%A1%8C%E7%BC%96%E8%AF%91%E6%89%93%E5%8C%85)和[2.8 部署apollo-client](https://github.com/ctripcorp/apollo/wiki/%E5%88%86%E5%B8%83%E5%BC%8F%E9%83%A8%E7%BD%B2%E6%8C%87%E5%8D%97#28-%E9%83%A8%E7%BD%B2apollo-client)等章节
3. 配置机器环境信息
	* Apollo支持应用在不同的环境有不同的配置，所以需要配置环境信息
	* 请参考Java客户端使用指南中的[1.2.2 Environment](https://github.com/ctripcorp/apollo/wiki/Java%E5%AE%A2%E6%88%B7%E7%AB%AF%E4%BD%BF%E7%94%A8%E6%8C%87%E5%8D%97#122-environment)章节
4. 示例项目中引用的`apollo-client`版本为`0.10.2`，如果本地部署的版本不一样的话，记得修改根目录下[pom.xml](pom.xml)中的`apollo.version`变量
5. 以上步骤都完成后，就可以参考各子模块的README.md来运行示例项目了
