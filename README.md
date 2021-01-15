# Arouter with onActivitResult

[![](https://jitpack.io/v/hss01248/arouter-api-onActivitResult.svg)](https://jitpack.io/#hss01248/arouter-api-onActivitResult)

Arouter + onActivityResult

enhance ARouter by [StartActivityResult](https://github.com/hss01248/StartActivityResult)



* get the  ActivityResult in NavigationCallback.

* Add log to callback and IProvider method call.



# Usage

change the source code and republish ,so you have to exclude com.alibaba: arouter-api, in case of class conflict.

```java
//gradle里: android同级,排除alibaba原始代码
android{
...
}
configurations {
    all*.exclude group: 'com.alibaba', module: 'arouter-api'
}
dependencies {
	 api "com.github.hss01248:arouter-api-onActivitResult:1.5.0-002"
}
```

## the other same as ARouter

please read:

https://github.com/alibaba/ARouter

## In callback:

NavigationCallback里增加了onActivityResult(int requestCode, int resultCode, Intent data)回调

            ARouter.getInstance().build("/test/activity")
            .withLong("key1", 666L)
            .withString("key3", "888")
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





# Log for IProvider and NavigationCallback:

![image-20210115104656120](https://gitee.com/hss012489/picbed/raw/master/picgo/1610678821813-image-20210115104656120.jpg)

D/ARouter: HelloServiceImpl@668af7f.sayHello(Vergil), result:hello, Vergil, cost:0ms ,thread:main

## 参考:

https://github.com/xiaojinzi123/Component/wiki/Component-%E5%92%8C-ARouter-%E6%AF%94%E8%BE%83
