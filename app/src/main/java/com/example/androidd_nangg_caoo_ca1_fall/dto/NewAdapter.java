package com.example.androidd_nangg_caoo_ca1_fall.dto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidd_nangg_caoo_ca1_fall.R;

import java.util.List;

public class NewAdapter extends BaseAdapter {
    Context context;
    List<News> news;

    public NewAdapter(Context context, List<News> news) {
        this.context = context;
        this.news = news;
    }

    @Override
    public int getCount() {
        if(news != null) {
            return  news.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(news != null) {
            return  news.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      if(convertView == null) {
          convertView = LayoutInflater.from(context).inflate(R.layout.listview_item, null);
      } else {
          News current = news.get(position);
          TextView txtTitle = convertView.findViewById(R.id.txtTitle);
          TextView txtDes = convertView.findViewById(R.id.txtDes);
          txtTitle.setText(current.getTitle());
          txtDes.setText(current.getDescription());
      }
      return  convertView;
    }
}
