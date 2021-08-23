package com.example.sunmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    TextView verificationCode, loginModeSwitch;
    ArrayAdapter arrayAdapter,codeAdapter;
    LinearLayout inputPsw;
    EditText pswview1,pswview2,pswview3,pswview4,pswview5,pswview6;
    TextView loginbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginModeSwitch = findViewById(R.id.LoginSwitchview);
        RelativeLayout loginBottomLayout = findViewById(R.id.Home2);
        Spinner platformSpinner = findViewById(R.id.platformSpinner);
        Spinner codeSpinner = findViewById(R.id.codeSpinner);
        loginbutton = findViewById(R.id.loginButton);
        inputPsw = findViewById(R.id.inputPsw);
        pswview1 = findViewById(R.id.pswview1);
        pswview2 = findViewById(R.id.view2);
        pswview3 = findViewById(R.id.view3);
        pswview4 = findViewById(R.id.view4);
        pswview5 = findViewById(R.id.view5);
        pswview6 = findViewById(R.id.view6);
        //切换登录方式点击事件
        loginModeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if(loginModeSwitch.getText().equals(getString(R.string.login_mode_switch))){
                    View layout = inflater.inflate(R.layout.login_bottom_swtich2, null);
                    pswview1 = findViewById(R.id.pswview1);
                    pswview2 = findViewById(R.id.view2);
                    pswview3 = findViewById(R.id.view3);
                    pswview4 = findViewById(R.id.view4);
                    pswview1.addTextChangedListener(textWatcher);
                    pswview2.addTextChangedListener(textWatcher);
                    pswview3.addTextChangedListener(textWatcher);
                    pswview4.addTextChangedListener(textWatcher);
                    loginModeSwitch.setText(getString(R.string.login_mode_switch2));
                    loginBottomLayout.removeAllViews();
                    loginBottomLayout.addView(layout);
                    //获取验证码
                    verificationCode = findViewById(R.id.Tooltip);
                    verificationCode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            countDownTimer.start();
                        }
                    });
                } else {
                    View layout = inflater.inflate(R.layout.login_bottom_swtich, null);
                    loginModeSwitch.setText(getString(R.string.login_mode_switch));
                    loginBottomLayout.removeAllViews();
                    loginBottomLayout.addView(layout);        pswview1 = findViewById(R.id.pswview1);
                    pswview2 = findViewById(R.id.view2);
                    pswview3 = findViewById(R.id.view3);
                    pswview4 = findViewById(R.id.view4);
                    pswview5 = findViewById(R.id.view5);
                    pswview6 = findViewById(R.id.view6);
                    pswview1.addTextChangedListener(textWatcher);
                    pswview2.addTextChangedListener(textWatcher);
                    pswview3.addTextChangedListener(textWatcher);
                    pswview4.addTextChangedListener(textWatcher);
                    pswview5.addTextChangedListener(textWatcher);
                    pswview6.addTextChangedListener(textWatcher);
                }
            }
        });

        arrayAdapter = ArrayAdapter.createFromResource(this,R.array.platform,android.R.layout.simple_spinner_item);
        codeAdapter = ArrayAdapter.createFromResource(this,R.array.phoneCode,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(R.layout.dropdown_stytle);
        codeAdapter.setDropDownViewResource(R.layout.dropdown_stytle);
        platformSpinner.setAdapter(arrayAdapter);
        codeSpinner.setAdapter(codeAdapter);
        //注册onItemSelected事件
        platformSpinner.setOnItemSelectedListener(new ProvOnItemSelectedListener());
        codeSpinner.setOnItemSelectedListener(new ProvOnItemSelectedListener());
        //密码输入框调用afterTextChanged
        pswview1.addTextChangedListener(textWatcher);
        pswview2.addTextChangedListener(textWatcher);
        pswview3.addTextChangedListener(textWatcher);
        pswview4.addTextChangedListener(textWatcher);
        pswview5.addTextChangedListener(textWatcher);
        pswview6.addTextChangedListener(textWatcher);

    }
    //onItemSelected监听器
    private class ProvOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            TextView textView = (TextView) view;
            textView.setPadding(60,10,0,10);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(16);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
    //倒计时方法 CountDownTimer(参数1：总的倒计时数 参数2：倒计时间隔 单位毫秒)
    private CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            long time = millisUntilFinished / 1000;
            verificationCode.setText(time + "秒后重新获取");
            verificationCode.setClickable(false);
        }

        @Override
        public void onFinish() {
            verificationCode.setText("重新获取验证码");
            verificationCode.setClickable(true);
        }
    };



    //监听字符变化（输入密码）
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
                if(s.toString().length()==1){
                    if(pswview1.isFocused()){
                        pswview1.clearFocus();
                        pswview2.requestFocus();
                    } else if(pswview2.isFocused()){
                        pswview2.clearFocus();
                        pswview3.requestFocus();
                    } else if(pswview3.isFocused()){
                        pswview3.clearFocus();
                        pswview4.requestFocus();
                    } else if(pswview4.isFocused()){
                        pswview4.clearFocus();
                        pswview5.requestFocus();
                    } else if(pswview5.isFocused()){
                        pswview5.clearFocus();
                        pswview6.requestFocus();
                    }
                } else {
                    if(pswview6.isFocused()){
                        pswview6.clearFocus();
                        pswview5.requestFocus();
                    } else if(pswview5.isFocused()){
                        pswview5.clearFocus();
                        pswview4.requestFocus();
                    } else if(pswview4.isFocused()){
                        pswview4.clearFocus();
                        pswview3.requestFocus();
                    } else if(pswview3.isFocused()&&inputPsw.getChildCount()==11){
                        pswview3.clearFocus();
                        pswview2.requestFocus();
                    } else if(pswview2.isFocused()&&inputPsw.getChildCount()==11){
                        pswview2.clearFocus();
                        pswview1.requestFocus();
                    }
                }
            }
    };

    @Override
    protected void onDestroy() {
        //销毁计时器
        countDownTimer.cancel();
        countDownTimer.onFinish();
        countDownTimer = null;
        super.onDestroy();
    }
}
