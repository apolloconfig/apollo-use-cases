package com.ctrip.framework.apollo.use.cases.netflix.archaius;

import com.netflix.config.DynamicBooleanProperty;
import com.netflix.config.DynamicPropertyFactory;
import java.time.LocalDateTime;

public class Application {

  private static final String ARCHAIUS_ADD_ITIONAL_URLS = "archaius.configurationSource.additionalUrls";

  public static void main(String[] args) throws Exception {
    // set apollo meta server address, adjust to actual address if necessary
    String apolloConfigServiceUrl = "http://localhost:8080";

    String appId = "netflix-archaius";
    System.setProperty(ARCHAIUS_ADD_ITIONAL_URLS,
        apolloConfigServiceUrl + "/configfiles/" + appId + "/default/application");

    DynamicBooleanProperty hystrixForceClosedProperty = DynamicPropertyFactory.getInstance()
        .getBooleanProperty("hystrix.command.default.circuitBreaker.forceClosed", false);

    //Archaius 默认30秒从服务端更新一次配置信息
    hystrixForceClosedProperty.addCallback(
        () -> System.out.println("[" + LocalDateTime.now().toString() + "] update forceClosed :" +  hystrixForceClosedProperty.get()));

    while (true) {
      System.in.read();
    }
  }
}
