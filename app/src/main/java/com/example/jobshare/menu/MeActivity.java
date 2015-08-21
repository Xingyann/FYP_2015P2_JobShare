package com.example.jobshare.menu;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import java.util.ArrayList;
import android.content.Intent;
import android.view.View;

public class MeActivity extends Activity {

    private MePullScrollView mScrollView;
    private ImageView mHeadImg;
    private GridView gridView;
    private GridViewAdapter gridAdapter;
    private ImageView mPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_layout);

        initView();

        mPost = (ImageView) findViewById(R.id.add);
        mPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);
    }

    private void selectImage()
    {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    protected void initView() {
        mScrollView = (MePullScrollView) findViewById(R.id.scroll_view);
        mHeadImg = (ImageView) findViewById(R.id.background_img);

        mScrollView.setHeader(mHeadImg);
    }

    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageItems.add(new ImageItem(bitmap, "Foreign worker" + i));
        }
        return imageItems;
    }
}



