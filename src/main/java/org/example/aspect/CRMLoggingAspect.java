package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    //setup logger

    private Logger myLogger = Logger.getLogger(getClass().getName());


    //setup pointcut declarations

    @Pointcut("execution(* org.example.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* org.example.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* org.example.DAO.*.*(..))")
    private void forDAOPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    private void forAppFlow() {}


    //add @Before advice

    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint){

        //display method we are calling
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("====>> @Before: calling method" + theMethod);

        //display the arguments to the method
        //get the arguments

        Object[] args = theJoinPoint.getArgs();

        //loop through and desplay args

        for(Object tempArg : args){
            myLogger.info("===>> argument:: " + tempArg);
        }

    }

    //add @AfterReturning advice
    @AfterReturning(
            pointcut="forAppFlow()",
            returning="theResult"
    )
    private void afterReturning(JoinPoint theJoinPoint, Object theResult){
        //display method we are returning from
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("====>> @AfterReturning: calling method" + theMethod);

        //display data returned
        myLogger.info("====>> result: " + theResult);

    }
}
