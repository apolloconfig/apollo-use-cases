package com.ctrip.framework.apollo.use.cases.spring.cloud.zuul;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import java.awt.Desktop;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableApolloConfig
@EnableZuulProxy
@SpringBootApplication
@RestController
@Slf4j
public class Application {

  @Value("${server.port}")
  private int port;

  public static void main(String[] args) throws Exception {
    val app = new SpringApplication(Application.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }

  @GetMapping("/index")
  public String index() {
    return String.format("index %s",  port);
  }

}
