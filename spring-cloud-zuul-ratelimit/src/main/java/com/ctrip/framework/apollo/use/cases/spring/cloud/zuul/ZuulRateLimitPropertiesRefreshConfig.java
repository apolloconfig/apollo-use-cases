package com.ctrip.framework.apollo.use.cases.spring.cloud.zuul;

import static com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties.PREFIX;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
@ConditionalOnProperty(prefix = PREFIX, name = "enabled", havingValue = "true")
public class ZuulRateLimitPropertiesRefreshConfig implements ApplicationContextAware {

  private ApplicationContext applicationContext;

  @ApolloConfigChangeListener(interestedKeyPrefixes = PREFIX)
  public void onChange(ConfigChangeEvent changeEvent) {
    log.info("Refreshing Zuul rateLimit Properties");

    this.applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));

    log.info("Zuul rateLimit Properties refreshed!");
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
