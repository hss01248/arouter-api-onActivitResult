package com.hss01248.arouterwithonactivitresult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

public class MainActivity extends AppCompatActivity {

    @Autowired
    HelloService helloService;

    @Autowired(name = "/yourservicegroupname/hello")
    HelloService helloService2;


    HelloService helloService3;

    HelloService helloService4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_main);
    }

    public void findByName(View view) {
        helloService2.sayHello("findByName-autowired");
        helloService4 = (HelloService) ARouter.getInstance().build("/yourservicegroupname/hello").navigation();
        helloService4.sayHello("findByName-navigation");
    }

    public void findByType(View view) {
        helloService.sayHello("findByType-autowired");
        helloService3 = ARouter.getInstance().navigation(HelloService.class);
        helloService3.sayHello("findByType-navigation");

    }

    public void activityResult(View view) {
        ARouter.getInstance().build("/test/activity")
                .withLong("key1", 666L)
                .withString("key3", "888")
                //.withObject("key4", new Test("Jack", "Rose"))
                .navigation(MainActivity.this, new NavigationCallback() {
                    @Override
                    public void onFound(Postcard postcard) {

                    }

                    @Override
                    public void onLost(Postcard postcard) {

                    }

                    @Override
                    public void onArrival(Postcard postcard) {

                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {

                    }

                    @Override
                    public void onActivityResult(int requestCode, int resultCode, Intent data) {
                        Log.e("onActivityResult","resultcode:"+resultCode+",data:"+data.getStringExtra("data"));
                    }
                });

        // new TransFragmentUtil<OrderFragment,Integer>(MainActivity.this,9,"orderfrag").getFragment().startOrder();
    }


}
