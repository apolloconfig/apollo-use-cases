# Purpose

演示[Spring Cloud Zuul](https://cloud.spring.io/spring-cloud-netflix/single/spring-cloud-netflix.html#netflix-zuul-reverse-proxy)的第三方限流插件[marcosbarbero/spring-cloud-zuul-ratelimit](https://github.com/marcosbarbero/spring-cloud-zuul-ratelimit)如何通过Apollo配置中心实现动态限流

# Instructions
0. 启动本机redis或者手动修改对应配置
1. 在Apollo配置中心创建AppId为`spring-cloud-zuul-ratelimit`的项目，也可以沿用`spring-cloud-zuul`的项目（注意配置文件中`app.id`配置）
2. 在默认的`application`下做如下配置（可以通过文本模式直接复制、粘贴下面的内容）：

    ```properties
    zuul.routes.test.path = /limit/**
    zuul.routes.test.url = forward:/index
    zuul.ratelimit.enabled = true
    zuul.ratelimit.repository = REDIS
    zuul.ratelimit.behind-proxy = true
    zuul.ratelimit.add-response-headers = true
    zuul.ratelimit.default-policy-list[0].limit = 0
    zuul.ratelimit.default-policy-list[0].quota = 1000
    zuul.ratelimit.default-policy-list[0].refresh-interval = 5
    zuul.ratelimit.default-policy-list[0].type[0] = user
    zuul.ratelimit.default-policy-list[0].type[1] = origin
    zuul.ratelimit.default-policy-list[0].type[2] = url
    # 通过实例配置覆盖默认配置，注意这里的`test`需要和网关对应路由的`test`关联
    # zuul.ratelimit.policy-list.test[0].limit = 1
    # zuul.ratelimit.policy-list.test[0].quota = 1000
    # zuul.ratelimit.policy-list.test[0].refresh-interval = 5
    # zuul.ratelimit.policy-list.test[0].type[0] = user
    # zuul.ratelimit.policy-list.test[0].type[1] = origin
    # zuul.ratelimit.policy-list.test[0].type[2] = url
    ```
3. 运行`com.ctrip.framework.apollo.use.cases.spring.cloud.zuul.Application`启动Demo
4. 手动打开`http://localhost:9090/limit`，页面显示`429`访问过载：
![](http://ww1.sinaimg.cn/large/006tNc79gy1g4fmqgu5rvj311u0eugm7.jpg)
5. 在Apollo配置中心修改配置，把`zuul.ratelimit.default-policy-list[0].limit`的值改为`1`并发布配置，再次访问，即可在5秒时间窗口内访问到1次`http://localhost:9090/limit`端点，说明动态路由生效了
  * 更详细的限流配置，请看[marcosbarbero/spring-cloud-zuul-ratelimit](https://github.com/marcosbarbero/spring-cloud-zuul-ratelimit)或者这里有一篇快速入门[Spring Cloud 入门教程9、服务限流/API限流（Zuul+RateLimiter）](https://ken.io/note/spring-cloud-zuul-ratelimiter-quickstart)
