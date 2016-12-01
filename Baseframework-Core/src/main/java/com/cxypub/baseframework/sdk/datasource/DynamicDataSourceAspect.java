package com.cxypub.baseframework.sdk.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DynamicDataSourceAspect {

	@Pointcut("execution(* com.cxypub..*Service.*(..))")
	public void serviceExecution() {
	}

	@Before("serviceExecution()")
	public void setDynamicDataSource(JoinPoint jp) {
		System.out.println("==================================");
		for (Object o : jp.getArgs()) {
			// 处理具体的逻辑 ，根据具体的境况CustomerContextHolder.setCustomerType()选取DataSource
		}
	}
}
