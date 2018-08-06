package com.ctrip.framework.apollo.use.cases.dubbo.service;

import com.ctrip.framework.apollo.core.ConfigConsts;
import java.io.IOException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Server {

  public static void main(String[] args) throws IOException {
    // set apollo meta server address, adjust to actual address if necessary
    System.setProperty(ConfigConsts.APOLLO_META_KEY, "http://localhost:8080");

    // run with -Djava.net.preferIPv4Stack=true if met 'Can't assign requested address' error
    new ClassPathXmlApplicationContext("server.xml").start();

    System.in.read();
  }
}
