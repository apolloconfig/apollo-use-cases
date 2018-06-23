package com.ctrip.framework.apollo.use.cases.spring.boot.starter.dubbo.provider;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Server {

  public static void main(String[] args) throws Exception {
    new SpringApplicationBuilder(Server.class).web(WebApplicationType.NONE).run(args);
  }
}
