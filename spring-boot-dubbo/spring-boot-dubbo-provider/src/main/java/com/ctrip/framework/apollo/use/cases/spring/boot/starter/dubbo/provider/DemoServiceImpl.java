package com.ctrip.framework.apollo.use.cases.spring.boot.starter.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.ctrip.framework.apollo.use.cases.spring.boot.starter.dubbo.api.DemoService;

@Service
public class DemoServiceImpl implements DemoService {

  @Override
  public String sayHello(String name) {
    return "Hello " + name;
  }
}
