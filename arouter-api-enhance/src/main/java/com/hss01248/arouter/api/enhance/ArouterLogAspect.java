package com.hss01248.arouter.api.enhance;

import android.util.Log;

import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.facade.template.IProvider;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ArouterLogAspect {

    @Around("execution(* com.alibaba.android.arouter.launcher.ARouter.navigation(..))")
    public Object callbackProxy(ProceedingJoinPoint joinPoint) throws Throwable {
       // Log.w("log","callbackProxy-->"+joinPoint.getSignature());
        Object[] args = joinPoint.getArgs();
        if(args.length != 4){
            return joinPoint.proceed(args);
        }
        NavigationCallback callback = (NavigationCallback) args[3];
        args[3] = LogProxy.getProxy(callback);
        return joinPoint.proceed(args);


    }


    //@Around("execution(* com.alibaba.android.arouter.core.LogisticsCenter.completion(..))")
    @Around("execution(* com.alibaba.android.arouter.facade.Postcard.setProvider(..))")
    public Object providerProxy(ProceedingJoinPoint joinPoint) throws Throwable {
        //Log.w("log","providerProxy-->"+joinPoint.getSignature());
        Object[] args = joinPoint.getArgs();
        IProvider provider = (IProvider) args[0];
        args[0] = LogProxy.getProxy(provider);
        return joinPoint.proceed(args);
    }

}
