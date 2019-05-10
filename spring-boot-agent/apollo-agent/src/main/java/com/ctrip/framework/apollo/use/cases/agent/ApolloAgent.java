package com.ctrip.framework.apollo.use.cases.agent;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.property.AutoUpdateConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

/**
 * @author: kl @kailing.pub
 * @date: 2019/5/8
 */
public class ApolloAgent {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApolloAgent.class);

    public static void premain(String agentArgs, Instrumentation inst) {
        Config config = ConfigService.getAppConfig();
        for (String key : config.getPropertyNames()) {
            System.getProperties().put(key, config.getProperty(key, ""));
        }
        LOGGER.debug("Apollo Configure load complete");
    }
}
