package com.example.jobshare.menu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.List;

public class ShareLineActivity extends Activity implements OnItemClickListener {

	private static final String rssFeed = "https://www.dropbox.com/s/jbld4yfakokqn92/imagelistview.xml?dl=1";

	List<ShareLineItem> arrayOfList;
	ListView list;
	ImageButton FAB;
	//final ArrayList<String> sArr = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.shareline);

		list = (ListView) findViewById(R.id.list);
		list.setOnItemClickListener(this);

		if (Utils.isNetworkAvailable(ShareLineActivity.this)) {
			new MyTask().execute(rssFeed);

		} else {
			showToast("No Network Connection!!!");
		}
	}

	// My AsyncTask start...
	class MyTask extends AsyncTask<String, Void, Void> {

		ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pDialog = new ProgressDialog(ShareLineActivity.this);
			pDialog.setMessage("Please wait while loading...");
			pDialog.show();
		}

		@Override
		protected Void doInBackground(String... params) {
			arrayOfList = new ShareLineNamesParser().getData(params[0]);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if (null != pDialog && pDialog.isShowing()) {
				pDialog.dismiss();
			}

			if (null == arrayOfList || arrayOfList.size() == 0) {
				showToast("No data!!!");
				ShareLineActivity.this.finish();
			} else {

				setAdapterToListview();
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
							long id) {
		ShareLineItem shareLineItem = arrayOfList.get(position);
		Intent intent = new Intent(ShareLineActivity.this, DetailActivity.class);
		intent.putExtra("url", shareLineItem.getLink());
		intent.putExtra("title", shareLineItem.getTitle());
		intent.putExtra("desc", shareLineItem.getDesc());
		startActivity(intent);
	}

	public void setAdapterToListview() {
		ShareLineNewsRowAdapter objAdapter = new ShareLineNewsRowAdapter(ShareLineActivity.this,
				R.layout.row, arrayOfList);
		list.setAdapter(objAdapter);
	}

	public void showToast(String msg) {

	}
}
//	public void retrieveData() {
//
//		Firebase ref = new Firebase("https://jobshare.firebaseio.com/shareline");
//
//		ref.addValueEventListener(new ValueEventListener() {
//			@Override
//			public void onDataChange(DataSnapshot dataSnapshot) {
//				for (DataSnapshot child : dataSnapshot.getChildren()) {
//
//					String id = child.child("id").getValue(String.class);
//					String title = child.child("sharelineTitle").getValue(String.class);
//					String desc = child.child("desc").getValue(String.class);
//					String pubDate = child.child("pubDate").getValue(String.class);
//					String link = child.child("link").getValue(String.class);
//
//					sArr.add(new Item(id, title, desc, pubDate, link));
//				}
//
//			}
//
//			@Override
//			public void onCancelled(FirebaseError firebaseError) {
//
//			}
//		});
//
//		Toast.makeText(this, "Loading data...", Toast.LENGTH_SHORT);
//		//return sArr;
//	}
//}

