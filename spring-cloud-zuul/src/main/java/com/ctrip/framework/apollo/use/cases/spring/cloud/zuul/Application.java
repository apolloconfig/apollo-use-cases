package com.ctrip.framework.apollo.use.cases.spring.cloud.zuul;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import java.awt.Desktop;
import java.net.URI;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@EnableApolloConfig
@EnableZuulProxy
@SpringBootApplication
public class Application {

  static final String ZUUL_PROPERTIES_BEAN = "ZUUL_PROPERTIES_BEAN";

  @Primary
  @Bean(ZUUL_PROPERTIES_BEAN)
  @RefreshScope
  @ConfigurationProperties("zuul")
  public ZuulProperties zuulProperties() {
    return new ZuulProperties();
  }

  public static void main(String[] args) throws Exception {
    System.setProperty("java.awt.headless", "false");
    ApplicationContext context = new SpringApplicationBuilder(Application.class).run(args);
    Desktop.getDesktop().browse(
        new URI("http://localhost:" + context.getEnvironment().getProperty("server.port", "8080")));
  }

}
