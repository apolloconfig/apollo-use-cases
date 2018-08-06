package com.ctrip.framework.apollo.use.cases.dubbo.client;

import com.ctrip.framework.apollo.core.ConfigConsts;
import com.ctrip.framework.apollo.use.cases.dubbo.api.DemoService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {

  public static void main(String[] args) throws IOException {
    // set apollo meta server address, adjust to actual address if necessary
    System.setProperty(ConfigConsts.APOLLO_META_KEY, "http://localhost:8080");

    // run with -Djava.net.preferIPv4Stack=true if met 'Can't assign requested address' error
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
    context.start();

    DemoService demoService = (DemoService) context.getBean("demoService");

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
      System.out.println(demoService.sayHello(input));
    }
  }
}
