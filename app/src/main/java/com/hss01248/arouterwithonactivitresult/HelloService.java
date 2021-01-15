package com.hss01248.arouterwithonactivitresult;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface HelloService extends IProvider {
    String sayHello(String name);
}
