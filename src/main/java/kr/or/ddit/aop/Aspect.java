package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@org.aspectj.lang.annotation.Aspect
public class Aspect {

	private static final Logger logger = LoggerFactory.getLogger(Aspect.class);
	
	//포인트 컷을 지정하게 하려고 만든 더미
	@Pointcut("execution(public * kr.or.ddit..service.*.*(..))")
	public void dummy() {}
	
	//특정 메소드가 실행되기 전에 실행되어야 할 공통의 관심사
	@Before("dummy()")
	public void beforeMethod(JoinPoint joinpoint) {
			logger.debug("Aspect.beforeMethod");
	}
	
	//around : 특정 메소드 실행 전후
	// 		   메소드 실행전  - 공통 관심사항
	// 		         메소드 원래 로직
	//		   메소드 실행후 - 공통 관심사항
	@Around("dummy()")
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		
		
		//메소드 본 로직 실행전
		long start = System.nanoTime();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName= joinPoint.getSignature().getName();
		String signatureString = joinPoint.getSignature().toString();
		
		Object ret = joinPoint.proceed(joinPoint.getArgs());
		
		//메소드 본 로직 실행후
		long end = System.nanoTime();
		  
		logger.debug("{} {} {} : duration : {}",signatureString, className, methodName, end - start);
		return ret;
	}
}
