package com.hss01248.arouterwithonactivitresult;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/yourservicegroupname/hello", name = "test service")
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "hello, " + name;
    }

    @Override
    public void init(Context context) {

    }
}
