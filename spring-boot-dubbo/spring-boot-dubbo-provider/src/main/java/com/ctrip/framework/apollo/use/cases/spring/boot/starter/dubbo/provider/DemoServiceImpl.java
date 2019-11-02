package com.ctrip.framework.apollo.use.cases.spring.boot.starter.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.ctrip.framework.apollo.use.cases.spring.boot.starter.dubbo.api.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DemoServiceImpl implements DemoService {
  private static final Logger LOGGER = LoggerFactory.getLogger(DemoServiceImpl.class);

  @Override
  public String sayHello(String name) {
    LOGGER.info("Say hello to {}", name);
    return "Hello " + name;
  }
}
