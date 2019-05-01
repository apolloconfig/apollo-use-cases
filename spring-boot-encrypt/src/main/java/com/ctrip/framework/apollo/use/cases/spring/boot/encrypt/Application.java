package com.ctrip.framework.apollo.use.cases.spring.boot.encrypt;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * Created by kl on 2018/6/25.
 * Content : 配置加解密实例
 */
@SpringBootApplication
@EnableApolloConfig
public class Application implements CommandLineRunner, EnvironmentAware {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Value("${test.input}")
    private String input;

    @Value("${test.input1}")
    private String input1;

    private Environment environment;

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            System.err.println("test.input 值 ENC(Ore69lUopDHL5R8Bw/G3bQ==) 解密后:" + input);
            System.err.println("test.input from environment: " + environment.getProperty("test.input"));
            System.err.println("test.input1 不需要解密:" + input1);
            TimeUnit.SECONDS.sleep(1);
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
