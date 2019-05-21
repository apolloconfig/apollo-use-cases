package com.ctrip.framework.apollo.use.cases.spring.cloud.gateway;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author ksewen
 * @date 2019/5/175:24 PM
 */
@Component
public class GatewayPropertiesRefresher implements ApplicationContextAware,ApplicationEventPublisherAware {

    private static final Logger logger = LoggerFactory.getLogger(GatewayPropertiesRefresher.class);

    private ApplicationContext applicationContext;

    private ApplicationEventPublisher publisher;

    @Autowired
    private GatewayProperties gatewayProperties;

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @ApolloConfigChangeListener(interestedKeyPrefixes = "spring.cloud.gateway.")
    public void onChange(ConfigChangeEvent changeEvent) {
        refreshGatewayProperties(changeEvent);
    }

    /***
     * 刷新org.springframework.cloud.gateway.config.PropertiesRouteDefinitionLocator中定义的routes
     *
     * @param changeEvent
     * @return void
     * @author ksewen
     * @date 2019/5/21 2:13 PM
     */
    private void refreshGatewayProperties(ConfigChangeEvent changeEvent) {
        logger.info("Refreshing GatewayProperties!");
        preDestroyGatewayProperties();
        this.applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));
        refreshGatewayRouteDefinition();
        logger.info("GatewayProperties refreshed!");
    }

    /***
     * GatewayProperties没有@PreDestroy和destroy方法
     * org.springframework.cloud.context.properties.ConfigurationPropertiesRebinder#rebind(java.lang.String)中destroyBean时不会销毁当前对象
     * 如果把spring.cloud.gateway.前缀的配置项全部删除（例如需要动态删除最后一个路由的场景），initializeBean时也无法创建新的bean，则return当前bean
     * 这个方法提供了类似@PreDestroy的操作，把org.springframework.cloud.gateway.config.GatewayProperties#routes和org.springframework.cloud.gateway.config.GatewayProperties#defaultFilters两个集合清空
     *
     * @param
     * @return void
     * @author ksewen
     * @date 2019/5/21 2:13 PM
     */
    private synchronized void preDestroyGatewayProperties() {
        logger.info("Pre Destroy GatewayProperties!");
        this.gatewayProperties.getRoutes().clear();
        this.gatewayProperties.getDefaultFilters().clear();
        logger.info("Pre Destroy GatewayProperties finished!");
    }

    private void refreshGatewayRouteDefinition() {
        logger.info("Refreshing Gateway RouteDefinition!");
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        logger.info("Gateway RouteDefinition refreshed!");
    }

}
