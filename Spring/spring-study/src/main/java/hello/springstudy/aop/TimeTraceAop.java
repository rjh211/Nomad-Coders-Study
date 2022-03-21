package hello.springstudy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component //어노테이션으로 빈등록을 하기도하지만, SpringConfig에서 직접 빈등록하는걸 선호한다.
public class TimeTraceAop {
    @Around("execution(* hello.springstudy..*(..))")//targeting (* 패키지명.. 하위의 모든 파일, 모든 타입)
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("Start : " + joinPoint.toString());
        try{
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
