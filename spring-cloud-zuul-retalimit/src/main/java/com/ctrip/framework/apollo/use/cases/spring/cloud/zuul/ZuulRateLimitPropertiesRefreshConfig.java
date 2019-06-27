package com.ctrip.framework.apollo.use.cases.spring.cloud.zuul;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ZuulRateLimitPropertiesRefreshConfig {

    private static final String BEAN_NAME_RATELIMITPROPERTIES = "rateLimitProperties";

    private final RefreshScope refreshScope;

    @ApolloConfigChangeListener(interestedKeyPrefixes = RateLimitProperties.PREFIX)
    public void onChange(ConfigChangeEvent changeEvent) {
        log.info("Refreshing Zuul rateLimit Properties");

        refreshScope.refresh(BEAN_NAME_RATELIMITPROPERTIES);

        log.info("Zuul rateLimit Properties refreshed!");
    }

}
