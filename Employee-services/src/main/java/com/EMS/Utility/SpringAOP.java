package com.EMS.Utility;



import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SpringAOP {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before("execution(* com.EMS.ServiceImpl.*(..)")
	public Object beforeGetEmployee(ProceedingJoinPoint  joinPoint) throws Throwable {
		 System.out.println("Start transaction");
	        try {
	            return joinPoint.proceed();
	        } finally {
	            System.out.println("Commit transaction");
	        }
	}
	
}
