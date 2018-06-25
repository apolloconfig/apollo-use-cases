# spring-boot-datasource 动态数据源实例

将test1.sql,test2.sql导入到数据库，apollo中配置如下数据库配置：            
spring.datasource.url = jdbc:mysql://127.0.0.1:3306/test1?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf-8          
spring.datasource.username = root         
spring.datasource.password = sasa

启动程序后会打印kl ，切换test2的数据库后就会打印ckl
