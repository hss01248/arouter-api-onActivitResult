package com.hss01248.arouter.api.enhance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.hss01248.activityresult.StartActivityUtil;
import com.hss01248.activityresult.TheActivityListener;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;


@Aspect
public class ARouterAspect {

    @Around("execution(* com.alibaba.android.arouter.launcher._ARouter.startActivity(..))")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Object result = null;

        final int requestCode = (int) args[0];
        Context currentContext = (Context) args[1];
        Intent intent = (Intent) args[2];
        final Postcard postcard = (Postcard) args[3];
        final NavigationCallback callback = (NavigationCallback) args[4];

        if (currentContext instanceof FragmentActivity) {
            FragmentActivity activity = (FragmentActivity) currentContext;
            StartActivityUtil.startActivity(activity, null, intent, requestCode != 0
                    , new TheActivityListener<Activity>() {
                        @Override
                        public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                            super.onActivityResult(requestCode, resultCode, data);
                            if (callback != null) {
                                callback.onActivityResult(requestCode, resultCode, data);
                            }
                        }

                        @Override
                        public void onActivityNotFound(Throwable e) {
                            super.onActivityNotFound(e);
                            if (callback != null) {
                                callback.onLost(postcard);
                            }

                        }
                    });
        } else {
            ActivityCompat.startActivity(currentContext, intent, postcard.getOptionsBundle());
        }

        if ((-1 != postcard.getEnterAnim() && -1 != postcard.getExitAnim()) && currentContext instanceof Activity) {    // Old version.
            ((Activity) currentContext).overridePendingTransition(postcard.getEnterAnim(), postcard.getExitAnim());
        }

        if (null != callback) { // Navigation over.
            callback.onArrival(postcard);
        }

        return result;
    }

}
