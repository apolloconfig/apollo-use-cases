# Purpose

演示如何通过java agent技术无缝集成Apollo配置中心,典型使用场如：

1. 有些配置已经打到jar包里了，而源码不方便修改
2. 不想改动项目代码直接集成Apollo

PS: 通过java agent的偷懒方式有缺陷，很难使用到配置变更动态生效功能，这里只是提供场景实例思路，最好还是按照官方wiki的方式正确接入，也非常简单
# Instructions

1. 在Apollo配置中心创建AppId为`spring-boot-agent`的项目
2. 在默认的`application`下做如下配置（可以通过文本模式直接复制、粘贴下面的内容）：

    ```properties
    test.input = 666
    ```
3. 运行`com.ctrip.framework.apollo.use.cases.agent.Application`启动Demo，程序会打印application.properties配置的`888`
4. 编译apollo-agent模块，得到apollo-agent-1.0-SNAPSHOT.jar，然后在VM options中，添加如下javaagent配置：
   ```
   -javaagent:xxx\apollo-agent-1.0-SNAPSHOT.jar
   -Ddev_meta=http://127.0.0.1:8801
   -Denv=DEV
   -Dapp.id=spring-boot-agent
   ```
   javaagent需要自行替换apollo-agent-1.0-SNAPSHOT.jar的决定路径
5. 重新运行`com.ctrip.framework.apollo.use.cases.agent.Application`启动Demo，这个时候就会输出apollo中配置的`666`了
