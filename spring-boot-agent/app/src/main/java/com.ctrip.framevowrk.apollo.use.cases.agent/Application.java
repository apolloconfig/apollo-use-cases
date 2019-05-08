package com.ctrip.framevowrk.apollo.use.cases.agent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

/**
 * @author: kl @kailing.pub
 * @date: 2019/5/8
 */
@SpringBootApplication
public class Application implements CommandLineRunner{


    @Value("${test.input:777}")
    private String input;

    @Override
    public void run(String... args) {
        while (true){
            System.err.println(input);
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
               e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
