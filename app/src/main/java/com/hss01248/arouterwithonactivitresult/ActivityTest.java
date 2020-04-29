package com.hss01248.arouterwithonactivitresult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * time:2020/4/29
 * author:hss
 * desription:
 */
@Route(path = "/test/activity")
public class ActivityTest extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        TextView textView = new TextView(this);
        textView.setText("activity 2");
        setContentView(textView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("data","123456");

                setResult(90,intent);
                finish();
            }
        });
    }
}
