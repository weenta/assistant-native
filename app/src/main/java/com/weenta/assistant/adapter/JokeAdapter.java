package com.weenta.assistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weenta.assistant.R;
import com.weenta.assistant.bean.JokeItem;

import java.util.List;

/**
 * 笑话列表 adapter
 */
public class JokeAdapter extends BaseAdapter {
    private List<JokeItem> list;
    private Context context;
    private LayoutInflater inflater;

    public JokeAdapter(Context context, List<JokeItem> list) {
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
        JokeItem item = list.get(position);
        ViewHolder holder = new ViewHolder();
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.joke_item,null);
            holder.hContent = convertView.findViewById(R.id.tv_joke_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.hContent.setText(item.getContent());
        return convertView;
    }

    /**
     * viewHolder
     */
    public class ViewHolder {
        public TextView hContent;
    }
}
