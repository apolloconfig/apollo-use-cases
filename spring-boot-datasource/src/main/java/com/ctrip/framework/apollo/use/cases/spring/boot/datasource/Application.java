package com.ctrip.framework.apollo.use.cases.spring.boot.datasource;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.concurrent.TimeUnit;

/**
 * Created by kl on 2018/6/25.
 * Content :动态数据源实例
 */
@EnableApolloConfig
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private EntityManager entityManager;

    @PostConstruct
    public void printUser()throws Exception{
        while (true){
            System.err.println(entityManager.find(User.class,1).getName());
            TimeUnit.SECONDS.sleep(1);
        }

    }
}
