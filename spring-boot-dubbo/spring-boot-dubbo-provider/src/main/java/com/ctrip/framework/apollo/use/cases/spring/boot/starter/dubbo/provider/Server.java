package com.ctrip.framework.apollo.use.cases.spring.boot.starter.dubbo.provider;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * config the following properties in the spring-boot-dubbo-provider app of apollo
 * <ul>
 *   <li>dubbo.application.name = spring-boot-dubbo-provider</li>
 *   <li>dubbo.registry.protocol = zookeeper</li>
 *   <li>dubbo.registry.address = 127.0.0.1:2181</li>
 *   <li>dubbo.scan.basePackages = com.ctrip.framework.apollo.use.cases.spring.boot.starter.dubbo.provider</li>
 * </ul>
 */
@SpringBootApplication
public class Server {

  public static void main(String[] args) throws Exception {
    new SpringApplicationBuilder(Server.class).web(WebApplicationType.NONE).run(args);
  }
}
