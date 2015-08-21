package com.example.jobshare.menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;


public class DetailActivity extends Activity implements View.OnClickListener {

	private DisplayImageOptions options;
	private ImageLoader imageLoader;

	private TextView tvTitle, tvDesc,liketext,disliketext;
	private ImageView imgView,likealert,dislikealert;

	String[] spinnerValues = {
			"Comments(10)",
			"Anonymous",
			"Anonymous",
			"Anonymous",
			"Anonymous",
			"Anonymous",
			"Anonymous",
			"Anonymous",
			"Anonymous",
			"Anonymous"};

	String[] spinnerSubs = {
			"",
			"Roughly what is the job like?",
			"Wah still need to work at night?",
			"Is the job operating at 24/7?",
			"Did you enjoy your job?",
			"So far what you like about this job?",
			"How long have you been working?",
			"Is it hard?",
			"What opportunities?",
			"Did you get to learn something?",};

	int total_images[] = {
			R.drawable.profile,
			R.drawable.profile,
			R.drawable.profile,
			R.drawable.profile,
			R.drawable.profile,
			R.drawable.profile,
			R.drawable.profile,
			R.drawable.profile,
			R.drawable.profile,
			R.drawable.profile};

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);

		Spinner mySpinner = (Spinner) findViewById(R.id.spinner_show);
		mySpinner.setAdapter(new MyAdapter(this, R.layout.custom_spinner, spinnerValues));

		tvTitle = (TextView) findViewById(R.id.tvtitle);
		tvDesc = (TextView) findViewById(R.id.tvdesc);
		imgView = (ImageView) findViewById(R.id.imgdesc);

		Bundle b = getIntent().getExtras();
		String title = b.getString("title");
		String desc = b.getString("desc");

		tvTitle.setText(title);
		tvDesc.setText(desc);

		ImageView likealert = (ImageView) findViewById(R.id.like);
		likealert.setOnClickListener(this);

		ImageView dislikealert = (ImageView) findViewById(R.id.dislike);
		dislikealert.setOnClickListener(this);

	}

	public class MyAdapter extends ArrayAdapter<String> {

		public MyAdapter(Context ctx, int txtViewResourceId, String[] objects) {
			super(ctx, txtViewResourceId, objects);
		}

		@Override
		public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
			return getCustomView(position, cnvtView, prnt);
		}

		@Override
		public View getView(int pos, View cnvtView, ViewGroup prnt) {
			return getCustomView(pos, cnvtView, prnt);
		}

		public View getCustomView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = getLayoutInflater();

			View mySpinner = inflater.inflate(R.layout.custom_spinner, parent, false);
			TextView main_text = (TextView) mySpinner.findViewById(R.id.text_main_seen);
			main_text.setText(spinnerValues[position]);

			TextView subSpinner = (TextView) mySpinner.findViewById(R.id.sub_text_seen);
			subSpinner.setText(spinnerSubs[position]);

			ImageView left_icon = (ImageView) mySpinner.findViewById(R.id.left_pic);
			left_icon.setImageResource(total_images[position]);
			return mySpinner;
		}
	}

	public void onClick(View view) {

		if (view == findViewById(R.id.like)) {

			AlertDialog.Builder likealertbox = new AlertDialog.Builder(this);
			likealertbox.setMessage("You have liked this post");
			likealertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface arg0, int arg1) {
					Toast.makeText(getApplicationContext(), "Liked", Toast.LENGTH_LONG).show();
				}
			});

			likealertbox.show();
			ImageView likealert = (ImageView) findViewById(R.id.like);

			//increment the like counter
			liketext=(TextView)findViewById(R.id.likelabel);
			String presentValStr=liketext.getText().toString();
			int presentIntVal=Integer.parseInt(presentValStr);
			presentIntVal++;
			liketext.setText(String.valueOf(presentIntVal));

			likealert.setBackgroundColor(Color.rgb(255, 255, 0));
		}

		else if (view == findViewById(R.id.dislike)) {

			AlertDialog.Builder dislikealertbox = new AlertDialog.Builder(this);
			dislikealertbox.setMessage("You disliked this post");
			dislikealertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface arg0, int arg1) {
					Toast.makeText(getApplicationContext(), "Disliked", Toast.LENGTH_LONG).show();
				}
			});

			dislikealertbox.show();
			ImageView dislikealert = (ImageView) findViewById(R.id.dislike);

			//increment the dislike counter
			disliketext=(TextView)findViewById(R.id.dislikelabel);
			String presentValStr=disliketext.getText().toString();
			int presentIntVal=Integer.parseInt(presentValStr);
			presentIntVal++;
			disliketext.setText(String.valueOf(presentIntVal));

			dislikealert.setBackgroundColor(Color.rgb(255, 255, 0));
		}
	}
}




