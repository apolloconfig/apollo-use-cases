package com.ctrip.framework.apollo.use.cases.spring.boot.logger;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.core.utils.StringUtils;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * Created by kl on 2018/6/25.
 * Content :动态日志配置
 */
@Service
public class LoggerConfiguration {
    Logger logger= LoggerFactory.getLogger(getClass());

    @ApolloConfig
    private Config config;

    private final static String LoggerTag="logging.level.";

    private final LoggingSystem loggingSystem;

    public LoggerConfiguration(LoggingSystem loggingSystem) {
        Assert.notNull(loggingSystem, "LoggingSystem must not be null");
        this.loggingSystem = loggingSystem;
    }

    @ApolloConfigChangeListener
    private void configChangeListter(ConfigChangeEvent changeEvent){
        setLogLevel();
    }

    @PostConstruct
    private void setLogLevel(){
        Set<String> keyNames=config.getPropertyNames();
        for (String key:keyNames){
            if (containsIgnoreCase(key,LoggerTag)){
                String strLevel=config.getProperty(key,"info");
                LogLevel level = LogLevel.valueOf(strLevel.toUpperCase());
                loggingSystem.setLogLevel(key.replace(LoggerTag,""),level);
                logger.info("{}:{}",key,strLevel);
            }
        }
    }

    public static boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        int len = searchStr.length();
        int max = str.length() - len;
        for (int i = 0; i <= max; i++) {
            if (str.regionMatches(true, i, searchStr, 0, len)) {
                return true;
            }
        }
        return false;
    }
}
