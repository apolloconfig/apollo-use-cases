package com.ctrip.framework.apollo.use.cases.netflix.archaius;

import com.ctrip.framework.apollo.build.ApolloInjector;
import com.ctrip.framework.apollo.core.enums.Env;
import com.ctrip.framework.apollo.core.enums.EnvUtils;
import com.ctrip.framework.apollo.core.internals.LegacyMetaServerProvider;
import com.ctrip.framework.apollo.core.spi.MetaServerProvider;
import com.ctrip.framework.apollo.util.ConfigUtil;
import com.ctrip.framework.foundation.Foundation;
import com.netflix.config.DynamicBooleanProperty;
import com.netflix.config.DynamicPropertyFactory;
import java.time.LocalDateTime;

public class Application {

  private static final String ARCHAIUS_ADD_ITIONAL_URLS = "archaius.configurationSource.additionalUrls";

  public static void main(String[] args) throws Exception {
    Env env = EnvUtils.transformEnv(Foundation.server().getEnvType());
    MetaServerProvider metaServerProvider = new LegacyMetaServerProvider();
    ConfigUtil m_configUtil = ApolloInjector.getInstance(ConfigUtil.class);
    System.setProperty(ARCHAIUS_ADD_ITIONAL_URLS,
        metaServerProvider.getMetaServerAddress(env) + "/configfiles/" + m_configUtil.getAppId()
            + "/default/application");
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
