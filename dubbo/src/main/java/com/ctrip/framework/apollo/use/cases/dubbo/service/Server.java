package com.ctrip.framework.apollo.use.cases.dubbo.service;

import java.io.IOException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Server {

  public static void main(String[] args) throws IOException {
    // run with -Djava.net.preferIPv4Stack=true if met 'Can't assign requested address' error
    new ClassPathXmlApplicationContext("server.xml").start();

    System.in.read();
  }
}
