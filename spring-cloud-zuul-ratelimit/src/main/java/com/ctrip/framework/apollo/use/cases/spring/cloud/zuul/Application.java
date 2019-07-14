package com.ctrip.framework.apollo.use.cases.spring.cloud.zuul;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableApolloConfig
@EnableZuulProxy
@SpringBootApplication
@RestController
public class Application {

  @Value("${server.port}")
  private int port;

  public static void main(String[] args) throws Exception {
    new SpringApplicationBuilder(Application.class).run(args);
  }

  @GetMapping("/index")
  public String index() {
    return String.format("index %s",  port);
  }

}
