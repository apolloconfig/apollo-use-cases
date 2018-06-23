package com.ctrip.framework.apollo.use.cases.dubbo.service;

import com.ctrip.framework.apollo.use.cases.dubbo.api.DemoService;

public class DemoServiceImpl implements DemoService {

  @Override
  public String sayHello(String name) {
    return "Hello " + name;
  }
}
