package com.example.jobshare.menu;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class ShareLineFragment extends Fragment {

	private EditText et;
	private String[] listview_names = {"Information Technology", "Healthcare", "Banking and Finance", "Law", "Teaching and Education", "Social Care", "Engineering", "Others"};

	private int[] listview_images = {R.drawable.it, R.drawable.healthcare, R.drawable.bf, R.drawable.law, R.drawable.te, R.drawable.socialcare, R.drawable.engineering, R.drawable.others};
	private int[] InformationTechnology = {R.drawable.it};
	private int[] Healthcare = {R.drawable.healthcare};
	private int[] BankingandFinance = {R.drawable.bf};
	private int[] Law = {R.drawable.law};
	private int[] TeachingandEducation = {R.drawable.te};
	private int[] SocialCare = {R.drawable.socialcare};
	private int[] Engineering = {R.drawable.engineering};
	private int[] Others = {R.drawable.others};

	private ArrayList<String> array_sort;
	private ArrayList<Integer> image_sort;
	int textlength = 0;

	private ListView lv;
	View rootView;
	Activity cntx;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_shareline, container, false);

		et = (EditText) rootView.findViewById(R.id.EditText01);
		lv = (ListView) rootView.findViewById(android.R.id.list);

		array_sort = new ArrayList<String>(Arrays.asList(listview_names));
		image_sort = new ArrayList<Integer>();

		for (int index = 0; index < listview_images.length; index++) {
			image_sort.add(listview_images[index]);
		}

		lv.setAdapter(new bsAdapter(getActivity()));

		et.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				// Abstract Method of TextWatcher Interface.
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// Abstract Method of TextWatcher Interface.
			}

			public void onTextChanged(CharSequence s,
									  int start, int before, int count) {
				textlength = et.getText().length();
				array_sort.clear();
				image_sort.clear();
				for (int i = 0; i < listview_names.length; i++) {
					if (textlength <= listview_names[i].length()) {
						if (listview_names[i].toLowerCase().contains(
								et.getText().toString().toLowerCase().trim())) {
							array_sort.add(listview_names[i]);
							image_sort.add(listview_images[i]);
						}
					}
				}
				AppendList(lv, array_sort, image_sort);

			}
		});

		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0,
									View arg1, int position, long arg3) {

				if (array_sort.get(position).equals("Information Technology")) {
					Intent i = new Intent(getActivity(), com.example.jobshare.menu.JobType.class);
					startActivity(i);
				}
				if (array_sort.get(position).equals("Health Care")) {
					Toast.makeText(getActivity().getApplicationContext(), "No Data found!", Toast.LENGTH_LONG).show();;
				}
				if (array_sort.get(position).equals("Banking and Finance")) {
					Toast.makeText(getActivity().getApplicationContext(), "No Data found!", Toast.LENGTH_LONG).show();;
				}
				if (array_sort.get(position).equals("Law")) {
					Toast.makeText(getActivity().getApplicationContext(), "No Data found!", Toast.LENGTH_LONG).show();;
				}
				if (array_sort.get(position).equals("Teaching and Education")) {
					Toast.makeText(getActivity().getApplicationContext(), "No Data found!", Toast.LENGTH_LONG).show();;
				}
				if (array_sort.get(position).equals("Social Care")) {
					Toast.makeText(getActivity().getApplicationContext(), "No Data found!", Toast.LENGTH_LONG).show();;
				}
				if (array_sort.get(position).equals("Engineering")) {
					Toast.makeText(getActivity().getApplicationContext(), "No Data found!", Toast.LENGTH_LONG).show();;
				}
				if (array_sort.get(position).equals("Others")) {
					Toast.makeText(getActivity().getApplicationContext(), "No Data found!", Toast.LENGTH_LONG).show();;
				}

				if(array_sort.get(position)== null)
				{
					Toast.makeText(getActivity().getApplicationContext(), "No search results found!!", Toast.LENGTH_LONG).show();;
				}
			}
		});

		return rootView;
	}


	public void AppendList(ListView lv, ArrayList<String> str, ArrayList<Integer> img) {
		lv.setAdapter(new bsAdapter(getActivity()));
	}

	public class bsAdapter extends BaseAdapter {
		Activity cntx;

		public bsAdapter(Activity context) {
			// TODO Auto-generated constructor stub
			this.cntx = context;
		}

		public int getCount() {
			// TODO Auto-generated method stub
			return array_sort.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return array_sort.get(position);
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return array_sort.size();
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			View row = null;

			LayoutInflater inflater = cntx.getLayoutInflater();
			row = inflater.inflate(R.layout.search_list_item, null);

			TextView tv = (TextView) row.findViewById(R.id.title);
			ImageView im = (ImageView) row.findViewById(R.id.imageview);

			tv.setText(array_sort.get(position));
			im.setImageDrawable(getResources().getDrawable(image_sort.get(position)));

			return row;
		}
	}
}



