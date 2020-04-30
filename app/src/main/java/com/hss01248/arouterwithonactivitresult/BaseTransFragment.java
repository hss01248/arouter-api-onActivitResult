package com.hss01248.arouterwithonactivitresult;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;






public  class BaseTransFragment<Frag extends BaseTransFragment,Bean> extends Fragment {

    protected FragmentActivity activity;


    public void setBean(Bean bean) {
        this.bean = bean;
    }

    protected Bean bean;

    public void setHostActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


}
