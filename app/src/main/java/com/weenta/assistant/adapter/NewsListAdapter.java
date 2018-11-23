package com.weenta.assistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;
import com.weenta.assistant.R;
import com.weenta.assistant.bean.NewsListItem;

import java.util.List;

/**
 * 今日要闻adtpter
 */
public class NewsListAdapter extends BaseAdapter {
    private Context context;
    private List<NewsListItem> list;
    private LayoutInflater inflater;

    public NewsListAdapter(Context context,List<NewsListItem> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();

        if(convertView == null) {
           convertView = inflater.inflate(R.layout.news_list_item,null);
           holder.hImageView = convertView.findViewById(R.id.news_img);
           holder.hTitle = convertView.findViewById(R.id.news_title);
           holder.hSource = convertView.findViewById(R.id.news_source);
           convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        NewsListItem item = list.get(position);
        holder.hTitle.setText(item.getTitle());
        holder.hSource.setText(item.getSource());

        Glide.with(context)
                .load(item.getNewsImg())
                .apply(new RequestOptions().override(150,100).centerCrop())
                .thumbnail(Glide.with(context).load(R.drawable.loading))
                .into(holder.hImageView);

        return convertView;
    }

    /**
     * ViewHolder
     */
    public class ViewHolder {
        public ImageView hImageView;
        public TextView hTitle;
        public TextView hSource;

    }
}
