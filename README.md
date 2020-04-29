# Arouter with onActivitResult
Arouter + onActivityResult

利用RxOnActivityResul和jarfilter插件,对ARouter的增强:

```
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
```