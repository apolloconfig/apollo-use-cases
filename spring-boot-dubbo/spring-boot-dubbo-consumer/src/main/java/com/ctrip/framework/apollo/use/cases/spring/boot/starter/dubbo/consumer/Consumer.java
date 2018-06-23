package com.ctrip.framework.apollo.use.cases.spring.boot.starter.dubbo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ctrip.framework.apollo.use.cases.spring.boot.starter.dubbo.api.DemoService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Consumer implements CommandLineRunner {

  @Autowired
  private ConsumerService consumerService;

  public static void main(String[] args) throws Exception {
    new SpringApplicationBuilder(Consumer.class).web(WebApplicationType.NONE).run(args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Please input any key to test. Input quit to exit.");
    while (true) {
      System.out.print("> ");
      String input = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8)).readLine();
      if (input == null || input.length() == 0) {
        continue;
      }
      input = input.trim();
      if (input.equalsIgnoreCase("quit")) {
        System.exit(0);
      }
      System.out.println(consumerService.sayHello(input));
    }
  }

  @Component
  private static class ConsumerService {

    @Reference
    private DemoService demoService;

    public String sayHello(String message) {
      return demoService.sayHello(message);
    }
  }
}
