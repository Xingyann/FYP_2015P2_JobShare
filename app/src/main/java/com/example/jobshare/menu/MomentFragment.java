package com.example.jobshare.menu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.graphics.Typeface;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MomentFragment extends Fragment {

	Intent intent;
	LinearLayout count_layout;
	TextView page_text[];
	int count = 0;
	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_moment,container, false);

		Button mButton = (Button) rootView.findViewById(R.id.button);
		mButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), MomentActivity.class);
				getActivity().startActivity(intent);
			}
		});

		Gallery_Activity mGallery = (Gallery_Activity) rootView.findViewById(R.id.mygallery01);
		mGallery.setAdapter(new MomentImageAdapter(getActivity()));
		mGallery.setScrollingEnabled(true);

		count_layout = (LinearLayout) rootView.findViewById(R.id.image_count);
		count = mGallery.getAdapter().getCount();
		System.out.println("Gallery Image Count======>>>" + count);

		page_text = new TextView[count];
		for (int i = 0; i < count; i++)
		{
			page_text[i] = new TextView(getActivity());
			page_text[i].setText(".");
			page_text[i].setTextSize(45);
			page_text[i].setTypeface(null, Typeface.BOLD);
			page_text[i].setTextColor(android.graphics.Color.GRAY);
			count_layout.addView(page_text[i]);
		}
		mGallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
									   int pos, long id) {
				// TODO Auto-generated method stub
				System.out.println("Item Selected Position=======>>>" + pos);
				for (int i = 0; i < count; i++) {
					page_text[i]
							.setTextColor(android.graphics.Color.GRAY);
				}
				page_text[pos]
						.setTextColor(android.graphics.Color.WHITE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		return rootView;
	}


}