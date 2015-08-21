package com.example.jobshare.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

    private EditText edit_username;
    private EditText edit_pwd;
    private Button btn_login;
    private Thread mLoginThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_username = (EditText) findViewById(R.id.editTextUsername);
        edit_username.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(edit_username);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        edit_pwd = (EditText) findViewById(R.id.editTextPassword);
        edit_pwd.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(edit_pwd);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        btn_login = (Button)findViewById(R.id.btnlogin);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidation())
                    submitForm();
                else
                    Toast.makeText(Login.this, "Please fill in the missing blanks", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void submitForm() {
        Toast.makeText(this, "Login...", Toast.LENGTH_LONG).show();

        final Login login = this;
        mLoginThread =  new Thread(){
            @Override
            public void run(){
                try {
                    synchronized(this){

                        wait(5000);
                    }
                }
                catch(InterruptedException ex){
                }

                finish();

                Intent intent = new Intent();
                intent.setClass(login, SlideActivity.class);
                startActivity(intent);

            }
        };

        mLoginThread.start();
    }

    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.hasText(edit_username)) ret = false;
        if (!Validation.hasText(edit_pwd)) ret = false;
        return ret;
    }

    public void SlideActivity(View view) {
        Intent intent = new Intent(this, SlideActivity.class);
        startActivity(intent);
    }

    public void Register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}



