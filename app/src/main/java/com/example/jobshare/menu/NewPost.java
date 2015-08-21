package com.example.jobshare.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class NewPost extends Activity {

    TextView text_name, text_hdname;
    CheckBox cb_hdname;
    View.OnClickListener checkBoxListener;
    EditText edit_like,edit_dislike;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpost);

        text_name = (TextView) findViewById(R.id.name);
        text_hdname = (TextView) findViewById(R.id.hiddenname);

        cb_hdname = (CheckBox) findViewById(R.id.chk_hname);
        cb_hdname.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

              @Override
              public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
              {
                  if(isChecked){
                      text_name.setVisibility(View.INVISIBLE);
                      text_hdname.setVisibility(View.VISIBLE);
                  }
                  else{
                      text_name.setVisibility(View.VISIBLE);
                      text_hdname.setVisibility(View.INVISIBLE);
                  }
              }
            }
        );

        RadioGroup groupRadio=(RadioGroup)findViewById(R.id.myRadioGroup);
        edit_like = (EditText) findViewById(R.id.editlike);
        edit_dislike = (EditText) findViewById(R.id.editdislike);

        groupRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.radio_like) {
                    edit_like.setVisibility(View.VISIBLE);
                    edit_dislike.setVisibility(View.INVISIBLE);
                }
                else if (checkedId == R.id.radio_dislike) {
                    edit_dislike.setVisibility(View.VISIBLE);
                    edit_like.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void ShareLineActivity(View view) {
        Intent intent = new Intent(this, ShareLineActivity.class);
        startActivity(intent);
    }
}