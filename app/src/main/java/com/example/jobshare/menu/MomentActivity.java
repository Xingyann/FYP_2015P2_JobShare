package com.example.jobshare.menu;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MomentActivity extends TabActivity {

    TabHost tabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moment_layout);

        tabHost = getTabHost();

        TabSpec homespec = tabHost.newTabSpec("Home");
        homespec.setIndicator("Home");
        Intent homeIntent = new Intent(this, HomeActivity.class);
        homespec.setContent(homeIntent);

       TabSpec contestspec = tabHost.newTabSpec("Contest");
        contestspec.setIndicator("Contest");
        Intent contestIntent = new Intent(this, ContestActivity.class);
        contestspec.setContent(contestIntent);

        TabSpec mespec = tabHost.newTabSpec("Me");
        mespec.setIndicator("Me");
        Intent meIntent = new Intent(this, MeActivity.class);
        mespec.setContent(meIntent);

        tabHost.addTab(homespec);
        tabHost.addTab(contestspec);
        tabHost.addTab(mespec);
    }
  }
