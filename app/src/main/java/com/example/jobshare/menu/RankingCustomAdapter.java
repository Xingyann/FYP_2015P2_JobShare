package com.example.jobshare.menu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RankingCustomAdapter extends BaseAdapter {

    Context context;
    List<RankingRowItem> rankingRowItems;

    public RankingCustomAdapter(Context context, List<RankingRowItem> rankingRowItems) {
        this.context = context;
        this.rankingRowItems = rankingRowItems;
    }

    @Override
    public int getCount() {
        return rankingRowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rankingRowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rankingRowItems.indexOf(getItem(position));
    }

    /* private view holder class */
    private class ViewHolder {
        ImageView profile_pic;
        TextView member_name;
        TextView status;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.ranking_list_item, null);
            holder = new ViewHolder();

            holder.member_name = (TextView) convertView
                    .findViewById(R.id.member_name);
            holder.profile_pic = (ImageView) convertView
                    .findViewById(R.id.profile_pic);
            holder.status = (TextView) convertView.findViewById(R.id.status);

            RankingRowItem row_pos = rankingRowItems.get(position);

            holder.profile_pic.setImageResource(row_pos.getProfile_pic_id());
            holder.member_name.setText(row_pos.getMember_name());
            holder.status.setText(row_pos.getStatus());

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

}

