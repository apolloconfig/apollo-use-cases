package com.ctrip.framework.apollo.use.cases.spring.cloud.zuul;

import static com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties.PREFIX;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = PREFIX, name = "enabled", havingValue = "true")
public class ZuulRateLimitPropertiesRefreshConfig implements ApplicationContextAware {

  private static final Logger logger = LoggerFactory.getLogger(ZuulRateLimitPropertiesRefreshConfig.class);

  private ApplicationContext applicationContext;

  @ApolloConfigChangeListener(interestedKeyPrefixes = PREFIX)
  public void onChange(ConfigChangeEvent changeEvent) {
    logger.info("Refreshing Zuul rateLimit Properties");

    this.applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));

    logger.info("Zuul rateLimit Properties refreshed!");
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
