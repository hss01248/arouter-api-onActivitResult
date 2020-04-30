package com.hss01248.arouterwithonactivitresult;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.lang.reflect.ParameterizedType;
import java.util.concurrent.Callable;

/**
 * time:2020/4/30
 * author:hss
 * desription:
 */
public class TransFragmentUtil<Frag extends BaseTransFragment,Bean> {
    Bean bean;
    FragmentActivity fragmentActivity;
    String fragmentTag;
    public TransFragmentUtil(FragmentActivity fragmentActivity,Bean bean,String fragmentTag) {
        this.bean = bean;
        this.fragmentActivity = fragmentActivity;
        this.fragmentTag = fragmentTag;
    }

    public Frag getFragment(){
       return getTransFragment(fragmentActivity, fragmentTag,
                new Callable<Frag>() {
                    @Override
                    public Frag call() throws Exception {
                        return newInstance(bean);
                    }
                });
    }

    private   Frag getTransFragment(FragmentActivity fragmentActivity,
                                                                  String fragmentTag, Callable<Frag> newIntance) {
        try {
            FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
            Frag fragment = findFragment(fragmentManager, fragmentTag);
            boolean isNewInstance = fragment == null;
            if (isNewInstance) {
                fragment = newIntance.call();
                fragmentManager.beginTransaction()
                        .add(fragment, fragmentTag)
                        .commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
            }
            fragment.setHostActivity(fragmentActivity);
            return fragment;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                return newIntance.call();
            } catch (Exception e1) {
                e1.printStackTrace();
                return null;
            }
        }

    }

    private   Frag findFragment(FragmentManager fragmentManager, String fragmentTag) {
        Fragment fragment = fragmentManager.findFragmentByTag(fragmentTag);
        if (fragment == null) {
            return null;
        }
        return (Frag) fragment;
    }

    private <Frag extends BaseTransFragment> Frag newInstance(Bean bean) {
        /*Bundle args = new Bundle();
        String json = JsonParser.getInstance().toStr(info);
        args.putString(VENDOR_PAY_BUNDLE_KEY, json);
        VendorPayTransFragment fragment = new VendorPayTransFragment();
        fragment.setArguments(args);*/
        Frag fragment = getNewInstance(this,0);
        fragment.setBean(bean);
        return fragment;
    }

    private <Frag> Frag getNewInstance(Object object, int i) {
        if(object!=null){
            try {
                return ((Class<Frag>) ((ParameterizedType) (object.getClass()
                        .getGenericSuperclass())).getActualTypeArguments()[i])
                        .newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }

        }
        return null;

    }
}
