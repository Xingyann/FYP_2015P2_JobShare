package com.example.jobshare.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {

    private EditText edit_username;
    private EditText edit_pwd;
    private EditText edit_email;
    private Button btn_register;
    private Thread mRegisterThread;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerJobShare();
    }

    private void registerJobShare() {
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

        edit_email = (EditText) findViewById(R.id.editTextEmail);
        edit_email.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isEmailAddress(edit_email, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        btn_register = (Button) findViewById(R.id.btnregister);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidation())
                    submitForm();
                else
                    Toast.makeText(Register.this, "Please fill in the missing blanks", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void submitForm() {
        // Submit your form here. your form is valid
        Toast.makeText(this, "Registering...", Toast.LENGTH_LONG).show();

        final Register register = this;
        mRegisterThread =  new Thread(){
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
                intent.setClass(register, Login.class);
                startActivity(intent);

            }
        };

        mRegisterThread.start();
    }

    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.hasText(edit_username)) ret = false;
        if (!Validation.hasText(edit_pwd)) ret = false;
        if (!Validation.isEmailAddress(edit_email, true)) ret = false;

        return ret;
    }

    public void Login(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}