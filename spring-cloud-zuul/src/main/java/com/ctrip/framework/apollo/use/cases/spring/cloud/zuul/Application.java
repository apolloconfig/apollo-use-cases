package com.ctrip.framework.apollo.use.cases.spring.cloud.zuul;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import java.awt.Desktop;
import java.net.URI;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.ApplicationContext;

@EnableApolloConfig
@EnableZuulProxy
@SpringBootApplication
public class Application {

  public static void main(String[] args) throws Exception {
    System.setProperty("java.awt.headless", "false");
    ApplicationContext context = new SpringApplicationBuilder(Application.class).run(args);
    Desktop.getDesktop().browse(
        new URI("http://localhost:" + context.getEnvironment().getProperty("server.port", "8080")));
  }

}
