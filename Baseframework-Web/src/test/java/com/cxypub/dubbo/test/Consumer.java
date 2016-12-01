package com.cxypub.dubbo.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cxypub.entity.PublicUser;
import com.cxypub.service.IPublicUserService;

public class Consumer {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "dubbo/spring-consumer-api.xml" });
		context.start();

		IPublicUserService publicUserService = (IPublicUserService) context.getBean("publicUserService"); //
		PublicUser hello = publicUserService.getPublicUserById("512f3cb6-30de-4cad-a6fc-e69fd752a091");
		System.out.println(hello); //

		// System.out.println(demoService.hehe());
		System.in.read();
	}
}