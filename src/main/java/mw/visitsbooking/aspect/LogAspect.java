package mw.visitsbooking.aspect;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import mw.visitsbooking.entity.Log;
import mw.visitsbooking.service.LogService;

@Aspect
@Component
public class LogAspect {
	
	@Autowired
	private LogService logService;

	private Logger logger = Logger.getLogger(getClass().getName());
	
	// Only info about deleting customers and visits are saving in database 
	
	@Pointcut("execution(* mw.visitsbooking.service.CustomerServiceImpl.deleteCustomer(..))")
	private void forDeleteUser() {
	}
	
	@Pointcut("execution(* mw.visitsbooking.service.VisitServiceImpl.deleteVisit(..))")
	private void forDeleteVisit() {
	}


	@AfterReturning(pointcut = "forDeleteUser() || forDeleteVisit()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {

		String method = joinPoint.getSignature().toShortString();
		logger.info("===> @AfterReturning: from method: " + method);

		logger.info("===> result: " + result);

		String username;

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		String methodName = null;
		
		if(method.equals("CustomerService.deleteCustomer(..)")) {
			methodName = "delete customer";
		}else if(method.equals("VisitService.deleteVisit(..)")){
			methodName = "delete visit";
		}
		
		Log log = new Log(LocalDateTime.now(), methodName, username);
		
		logService.saveLog(log);

	}

}
