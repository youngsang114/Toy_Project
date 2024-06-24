package com.example.filter_interceptor_aop.aop;

import com.example.filter_interceptor_aop.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Aspect
@Component
public class TimerAop {
    @Pointcut(value = "within(com.example.filter_interceptor_aop.controller.UserApiController)")
    public void timerPointCut(){}

    @Before(value= "timerPointCut()")
    public void before(JoinPoint joinPoint){
        System.out.println("before");
    }
    @After(value= "timerPointCut()")
    public void after(JoinPoint joinPoint){
        System.out.println("after");
    }
    @AfterReturning(value= "timerPointCut()",returning = "result")
    public void afterRetuning(JoinPoint joinPoint,Object result){
        System.out.println("after returning");
    }
    @AfterThrowing(value= "timerPointCut()",throwing = "tx")
    public void afterThrowing(JoinPoint joinPoint,Throwable tx){
        System.out.println("after throwing");
    }


    @Around(value = "timerPointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        Arrays.stream(joinPoint.getArgs()).forEach(
                it ->{
                    if (it instanceof UserRequest){
                        UserRequest tempUser = (UserRequest) it;
                        tempUser.setPhoneNumber(tempUser.getPhoneNumber().replace("-",""));
                    }
                }
        );
        // 암복호화, 로깅을 해서 넣을 수 있다
        List<UserRequest> newObjs = Arrays.asList(new UserRequest());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        log.info("메소드 실행 이전");
        joinPoint.proceed();

        stopWatch.stop();
        System.out.println("총 소요된 시간 : "+stopWatch.getTotalTimeMillis());

        log.info("메소드 실행 이후");
    }
}
