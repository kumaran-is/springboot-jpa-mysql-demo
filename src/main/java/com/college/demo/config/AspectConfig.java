package com.college.demo.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Aspect
@Slf4j
public class AspectConfig {
	
/*	@Before(value = "execution(* com.college.demo.controller.*.*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		log.debug("Inside Before Advice");
	}
	
	@Before(value = "execution(* com.college.demo.controller.*.*(..)) and args(object)")
	public void oneMethodParameterBeforeAdvice(JoinPoint joinPoint, Object object) {
		log.debug("Inside One Method Parameter Before Advice"+ object);
	}
	
	@After(value = "execution(* com.college.demo.controller.*.*(..)) and args(object)")
	public void oneMethodParameterAfterAdvice(JoinPoint joinPoint, Object object) {
		log.debug("Inside One Method Parameter After Advice"+ object);
	}
	
	@AfterReturning(value = "execution(* com.college.demo.controller.*.*(..)) and args(object)", returning = "returningObject")
	public void afterAdvice(JoinPoint joinPoint, Object object, Object returningObject) {
		log.debug("Inside One Method Parameter After Returning"+ object);
	} */
	
	@Around(value = "execution(* com.college.demo.controller.*.*(..)) and args(object)")
	public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint, Object object) {
		log.debug("##############Inside One Method Parameter aroundAdvice request##########>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ object);
		
		Object returningObject = null;
		
		try {
			returningObject = proceedingJoinPoint.proceed();
		} catch(Throwable e) {
			e.printStackTrace();
		}
		
		log.debug("**************************Inside One Method Parameter aroundAdvice returningObject***************>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ returningObject);
			
	} 
}

