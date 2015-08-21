package com.example.jobshare.menu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RankingFragment extends Fragment implements View.OnClickListener {

	private ImageView iv;
	private Calendar cal;
	private int day;
	private int month;
	private int year;
	private EditText et;

	String[] member_names;
	TypedArray profile_pics;
	String[] statues;
	static final String TAG_IMAGES = "images";
	List<RankingRowItem> rankingRowItems;
	ListView mylistview;
	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_ranking, container, false);

		iv = (ImageView) rootView.findViewById(R.id.imageView1);
		cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
		et = (EditText) rootView.findViewById(R.id.editText);
		iv.setOnClickListener(this);

		rankingRowItems = new ArrayList<RankingRowItem>();
		member_names = getResources().getStringArray(R.array.Member_names);
		profile_pics = getResources().obtainTypedArray(R.array.profile_pics);
		statues = getResources().getStringArray(R.array.statues);

		for (int i = 0; i < member_names.length; i++) {
			RankingRowItem item = new RankingRowItem(member_names[i],
					profile_pics.getResourceId(i, -1), statues[i]);
					rankingRowItems.add(item);
		}

		mylistview = (ListView) rootView.findViewById(R.id.list);
		RankingCustomAdapter adapter = new RankingCustomAdapter(getActivity(), rankingRowItems);
		mylistview.setAdapter(adapter);
		profile_pics.recycle();

		return rootView;
	}

	public void onClick (View v) {
		createdDialog(0).show();
	}
	
	@Deprecated
	protected Dialog createdDialog(int id) {
		return new DatePickerDialog(getActivity(), datePickerListener, year, month, day);
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear,
							  int selectedMonth, int selectedDay) {
			et.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
					+ selectedYear);
		}
	};
}
