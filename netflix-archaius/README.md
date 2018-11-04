# Purpose

Netflix开源的一系列组件(Zuul, Hystrix, Eureka, Ribbon)的配置管理使用的是自家的[Archaius](https://github.com/Netflix/archaius)，遗憾的是Netflix没有开源Archaius的服务端，现在可以使用Apollo作为Archaius的服务端使用。

- Apollo可以为Netflix微服务全家桶提供集中化的配置管理
- Archaius可以做为Apollo Client的另一种选择

# Instructions

1. 在Apollo配置中心创建AppId为`netflix-archaius`的项目
2. 在默认的`application`下做如下配置（可以通过文本模式直接复制、粘贴下面的内容）：

    ```properties
    hystrix.command.default.circuitBreaker.forceClosed = false
    ```
3. 运行`com.ctrip.framework.apollo.use.cases.netflix.archaius.Application`启动Demo
4. 程序会在控制台输出hystrix.command.default.circuitBreaker.forceClosed参数值的变化
5. 在Apollo配置中心修改配置，把`hystrix.command.default.circuitBreaker.forceClosed`的值改为`true`并发布配置
  * Archaius 默认30秒从服务端更新一次配置信息，所以需要等待30秒配置生效
