# spring-boot-encrypt 配置加解密实例

jasypt-spring-boot-starter项目地址：https://github.com/ulisesbocchio/jasypt-spring-boot   

使用EncryptUtil工具输出加密后的配置，加解密的keyjasypt.encryptor.password自己指定，添加配置时使用ENC()包含配置，如加密配置为xxx，则ENC(xxx)   
本实例apollo中配置如下：               
jasypt.encryptor.password = klklklklklklklkl    
test.input = ENC(Ore69lUopDHL5R8Bw/G3bQ==)      
test.input1 = ckl     

