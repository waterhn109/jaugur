package com.jcc.yijing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by HY on 2016/10/25.
 */
public class CiActivity extends Activity {

    private TextView guaciText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.guaci);
        // 初始化各控件
        guaciText = (TextView) findViewById(R.id.gua_ci);
        String gua_ci = getIntent().getStringExtra("gua_ci");
        gua_ci = gua_ci.replace("\\n\\r","\n\r");
        guaciText.setText(gua_ci);
        guaciText.setMovementMethod(ScrollingMovementMethod.getInstance()) ;
    }


    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("from_guaci", true);
        startActivity(intent);
        finish();
    }
}
