package com.order.orderdemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;

@Aspect
@Component
public class LoggingAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Before Aspect for logging the method entry point.
	 *
	 * @param joinPoint
	 */
	@Before("execution(* com.order.orderdemo..*(..))")
	public void before(JoinPoint joinpoint) {
		logger.info("{}.{}() Start", joinpoint.getSignature().getDeclaringType().getSimpleName(),
				joinpoint.getSignature().getName());

	}

	/**
	 * Before Aspect for logging Parameters.
	 *
	 * @param joinPoint
	 */
	@Before("execution(* com.order.orderdemo.controller..*(..)) || execution(* com.order.orderdemo.service..*(..))  || execution(* com.order.orderdemo.dao..*(..))")
	public void loggingParameters(JoinPoint joinPoint) {
		var requestParameters = new StringBuilder();
		for (Object requestParam : joinPoint.getArgs()) {
			try {
				var objectMapper = new ObjectMapper();
				var requestParamString = objectMapper.writeValueAsString(requestParam);
				requestParameters.append(requestParamString).append(", ");
			} catch (JsonProcessingException jsonProcessingException) {
				logger.error("Exception while logging request parameters. ", jsonProcessingException);
			}
			logger.debug("Request Parameter = {}", requestParameters);
		}

	}

	/**
	 * Around Aspect for logging the method execution time.
	 * 
	 * @param joinPoint
	 * @return Object
	 * @throws Throwable
	 */
	@Around("execution(* com.order.orderdemo.controller..*(..)) || execution(* com.order.orderdemo.dao..*(..)))")
	public Object measureMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object output = joinPoint.proceed();
		long endTime = System.currentTimeMillis();
		logger.info("PERFORMANCE: {}.{}() - Total execution time = {} ms.",
				joinPoint.getSignature().getDeclaringType().getSimpleName(), joinPoint.getSignature().getName(),
				(endTime - startTime));
		return output;
	}

	/**
	 * After Aspect for logging the method exit point.
	 *
	 * @param joinPoint
	 */
	@After("execution(* com.jpmc.p2p.business..*(..))")
	public void after(JoinPoint joinPoint) {
		logger.info("{}.{}() End", joinPoint.getSignature().getDeclaringType().getSimpleName(),
				joinPoint.getSignature().getName());
	}

}
