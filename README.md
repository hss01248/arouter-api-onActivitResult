# Arouter with onActivitResult
Arouter + onActivityResult
[![](https://jitpack.io/v/hss01248/arouter-api-onActivitResult.svg)](https://jitpack.io/#hss01248/arouter-api-onActivitResult)

enhance ARouter by [StartActivityResult](https://github.com/hss01248/StartActivityResult)

get the  ActivityResult in NavigationCallback





# Usage

change the source code and republish ,so you have to exclude com.alibaba: arouter-api, in case of class conflict.

```
//gradle里: android同级,排除alibaba原始代码
android{
...
}
configurations {
    all*.exclude group: 'com.alibaba', module: 'arouter-api'
}
dependencies {
	 api "com.github.hss01248:arouter-api-onActivitResult:1.5.0-001"
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

