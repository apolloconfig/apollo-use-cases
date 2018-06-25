# Purpose

演示如何结合[jasypt-spring-boot](https://github.com/ulisesbocchio/jasypt-spring-boot)实现Apollo中存储加密配置

# Instructions 

1. 在Apollo配置中心创建AppId为`spring-boot-encrypt`的项目
2. 在默认的`application`下做如下配置（可以通过文本模式直接复制、粘贴下面的内容）：

    ```properties
    jasypt.encryptor.password = klklklklklklklkl
    test.input = ENC(Ore69lUopDHL5R8Bw/G3bQ==)
    test.input1 = ckl
    ```
3. 运行`com.ctrip.framework.apollo.use.cases.spring.boot.encrypt.Application`启动Demo
4. 程序会输出解密后的明文配置
5. 使用EncryptUtil小工具输出加密后的配置，加解密的keyjasypt.encryptor.password自己指定，添加配置时使用ENC()包含配置，如加密配置为xxx，则ENC(xxx)   
