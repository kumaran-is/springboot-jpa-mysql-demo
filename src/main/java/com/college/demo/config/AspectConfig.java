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
	
	/*@Around(value = "execution(* com.college.demo.controller.*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		
		Object returningObject = null;
		String targetClass = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        String targetMethod = proceedingJoinPoint.getSignature().getName();
        Object[] argsArray = proceedingJoinPoint.getArgs();
       
        log.debug("Executing {}.{} with argument: {}", targetClass, targetMethod, argsArray);
		
		try {
			returningObject = proceedingJoinPoint.proceed();
		} catch(Throwable e) {
			e.printStackTrace();
		}
		
		log.debug("{}.{} returns: {} ", targetClass, targetMethod, returningObject);
		
		return returningObject;
			
	} */
}

