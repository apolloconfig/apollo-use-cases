package com.ctrip.framework.apollo.use.cases.spring.boot.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Created by kl on 2018/6/25.
 * Content :
 */
@Service
public class PrintLogger {
    private static Logger logger = LoggerFactory.getLogger(PrintLogger.class);

    @PostConstruct
    public void printLogger() throws Exception{

        while (true) {
            logger.info("我是info级别日志");
            logger.error("我是error级别日志");
            logger.warn("我是warn级别日志");
            logger.debug("我是debug级别日志");
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
