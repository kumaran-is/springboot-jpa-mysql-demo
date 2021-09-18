package com.college.demo.aop;

import java.util.Arrays;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
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
public class LoggingAspect {
	
/*	@Before(value = "execution(* com.college.demo.controller.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		log.debug("logBefore running .....");
		log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
	}
	
	@Before(value = "execution(* com.college.demo.controller.*.*(..)) and args(object)")
	public void oneMethodParameterLogBefore(JoinPoint joinPoint, Object object) {
		log.debug("Inside One Method Parameter Before Advice"+ object);
	}
	
	@After(value = "execution(* com.college.demo.controller.*.*(..)) and args(object)")
	public void oneMethodParameterLogAfter(JoinPoint joinPoint, Object object) {
		log.debug("ogAfter running"+ object);
		log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()))
	}
	
	
	@AfterReturning(value = "execution(* com.college.demo.controller.*.*(..)) and args(object)", returning = "returningObject")
	public void logAfterReturning(JoinPoint joinPoint, Object object, Object returningObject) {
		log.debug("logAfterReturning running ....."+ object);
		log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
	} */
	
	/*@Around(value = "execution(* com.college.demo.controller.*.*(..))")
	public Object logAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		
		log.debug("logAround running .....");
		Object returningObject = null;
		String targetClass = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        String targetMethod = proceedingJoinPoint.getSignature().getName();
        Object[] argsArray = proceedingJoinPoint.getArgs();
       
        log.debug("Executing {}.{} with argument[s]: {}", targetClass, targetMethod, argsArray);
		
		try {
			returningObject = proceedingJoinPoint.proceed();
		} catch(Throwable e) {
			e.printStackTrace();
		}
		
		log.debug("{}.{} returns: {} ", targetClass, targetMethod, returningObject);
		
		return returningObject;
			
	} */
	
	/**
	 * Run around the method execution.
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
 	@Around(value = "execution(* com.college.demo.controller.*.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		log.debug("logAround running .....");
		if (log.isDebugEnabled()) {
			log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
		}
		try {
			Object result = joinPoint.proceed();
			if (log.isDebugEnabled()) {
				log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
						joinPoint.getSignature().getName(), result);
			}
			return result;
		} catch (IllegalArgumentException e) {
			log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
			throw e;
		}

	} 
	
	/**
	 * Advice that logs methods throwing exceptions.
	 *
	 * @param joinPoint join point for advice
	 * @param e         exception
	 */

	@AfterThrowing(pointcut = "execution(* com.college.demo.controller.*.*(..))", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		log.debug("logAfterThrowing running .....");
		log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), error.getCause() != null ? error.getCause() : "NULL");
	} 
}

