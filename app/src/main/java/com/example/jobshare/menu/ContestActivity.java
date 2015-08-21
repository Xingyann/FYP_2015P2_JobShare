package com.example.jobshare.menu;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.format.Time;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

public class ContestActivity extends Activity {

    private static final String TAG = "CountdownTimer";

    private TextView mCountdownNote;
    private ProgressWheel mDaysWheel;
    private TextView mDaysLabel;
    private ProgressWheel mHoursWheel;
    private TextView mHoursLabel;
    private ProgressWheel mMinutesWheel;
    private TextView mMinutesLabel;
    private ProgressWheel mSecondsWheel;
    private TextView mSecondsLabel;

    // Timer setup
    Time conferenceTime = new Time(Time.getCurrentTimezone());
    int hour = 22;
    int minute = 33;
    int second = 0;
    int monthDay = 28;
    // month is zero based...7 == August
    int month = 7;
    int year;

    // Values displayed by the timer
    private int mDisplayDays;
    private int mDisplayHours;
    private int mDisplayMinutes;
    private int mDisplaySeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contest_layout);

        configureViews();
        configureConferenceDate();

    }

    private void configureViews() {

        this.conferenceTime.setToNow();
        this.year = conferenceTime.year;

        //this.mCountdownNote = (TextView) findViewById(R.id.activity_countdown_timer_note);
        this.mDaysWheel = (ProgressWheel) findViewById(R.id.activity_countdown_timer_days);
        this.mHoursWheel = (ProgressWheel) findViewById(R.id.activity_countdown_timer_hours);
        this.mMinutesWheel = (ProgressWheel) findViewById(R.id.activity_countdown_timer_minutes);
        this.mSecondsWheel = (ProgressWheel) findViewById(R.id.activity_countdown_timer_seconds);
        this.mDaysLabel = (TextView) findViewById(R.id.activity_countdown_timer_days_text);
        this.mHoursLabel = (TextView) findViewById(R.id.activity_countdown_timer_hours_text);
        this.mMinutesLabel = (TextView) findViewById(R.id.activity_countdown_timer_minutes_text);
        this.mSecondsLabel = (TextView) findViewById(R.id.activity_countdown_timer_seconds_text);

    }

    private void configureConferenceDate() {
        conferenceTime.set(second, minute, hour, monthDay, month, year);
        conferenceTime.normalize(true);
        long confMillis = conferenceTime.toMillis(true);

        Time nowTime = new Time(Time.getCurrentTimezone());
        nowTime.setToNow();
        nowTime.normalize(true);
        long nowMillis = nowTime.toMillis(true);

        long milliDiff = confMillis - nowMillis;

        new CountDownTimer(milliDiff, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // decompose difference into days, hours, minutes and seconds
                ContestActivity.this.mDisplayDays = (int) ((millisUntilFinished / 1000) / 86400);
                ContestActivity.this.mDisplayHours = (int) (((millisUntilFinished / 1000) - (ContestActivity.this.mDisplayDays * 86400)) / 3600);
                ContestActivity.this.mDisplayMinutes = (int) (((millisUntilFinished / 1000) - ((ContestActivity.this.mDisplayDays * 86400) + (ContestActivity.this.mDisplayHours * 3600))) / 60);
                ContestActivity.this.mDisplaySeconds = (int) ((millisUntilFinished / 1000) % 60);

                ContestActivity.this.mDaysWheel.setText(String.valueOf(ContestActivity.this.mDisplayDays));
                ContestActivity.this.mDaysWheel.setProgress(ContestActivity.this.mDisplayDays);

                ContestActivity.this.mHoursWheel.setText(String.valueOf(ContestActivity.this.mDisplayHours));
                ContestActivity.this.mHoursWheel.setProgress(ContestActivity.this.mDisplayHours * 15);

                ContestActivity.this.mMinutesWheel.setText(String.valueOf(ContestActivity.this.mDisplayMinutes));
                ContestActivity.this.mMinutesWheel.setProgress(ContestActivity.this.mDisplayMinutes * 6);

                Animation an = new RotateAnimation(0.0f, 90.0f, 250f, 273f);
                an.setFillAfter(true);

                ContestActivity.this.mSecondsWheel.setText(String.valueOf(ContestActivity.this.mDisplaySeconds));
                ContestActivity.this.mSecondsWheel.setProgress(ContestActivity.this.mDisplaySeconds * 6);
            }

            @Override
            public void onFinish() {
                //TODO: this is where you would launch a subsequent activity if you'd like.  I'm currently just setting the seconds to zero
                CountDownLogger.d(TAG, "Timer Finished...");
                ContestActivity.this.mSecondsWheel.setText("0");
                ContestActivity.this.mSecondsWheel.setProgress(0);
            }
        }.start();
    }
}
