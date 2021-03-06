package pt.joja.lab;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(2)
public class ValidateAspect {

    @Before("pt.joja.lab.LogUtil.myPoint()")
    public void validateStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        System.out.println("[Validate@Before]" + signature.getName() + "方法开始执行，参数:" + Arrays.asList(args));
    }

    @After("pt.joja.lab.LogUtil.myPoint()")
    public void validateAfter(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("[Validate@After]" + signature.getName() + "方法结束了");
    }

    @AfterReturning(value = "pt.joja.lab.LogUtil.myPoint()", returning = "result")
    public void validateAfterReturning(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        System.out.println("[Validate@AfterReturning]" + signature.getName() + "方法执行结束，结果是：" + result);
    }

    @AfterThrowing(value = "pt.joja.lab.LogUtil.myPoint()", throwing = "throwable")
    public void validateAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        Signature signature = joinPoint.getSignature();
        System.out.println("[Validate@AfterThrowing]" + signature.getName() + "方法抛出异常，异常信息是：" + throwable);
    }
}
