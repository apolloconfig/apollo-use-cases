# Purpose

演示[Dubbo](https://github.com/apache/incubator-dubbo)如何通过Apollo配置中心实现中心化配置

# Instructions

1. 在Apollo配置中心创建AppId为`dubbo`的项目
2. 在默认的`application`下配置zookeerper的地址，注意key为`zookeeper.address`
	* 如：zookeeper.address = 127.0.0.1:2181
3. 启动zookeeper
4. 运行`com.ctrip.framework.apollo.use.cases.dubbo.service.Server`启动Demo服务端
5. 运行`com.ctrip.framework.apollo.use.cases.dubbo.client.Consumer`启动Demo调用端
6. 在调用端输入任意字符后按回车，即可发起一次Dubbo服务请求并输出服务端的响应
	* 如输入`dubbo`，服务端会响应`Hello dubbo`
