package com.weenta.assistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weenta.assistant.R;
import com.weenta.assistant.bean.ExpressItem;

import java.util.List;

/**
 * 物流查询adapter
 */
public class ExpressAdapter extends BaseAdapter {
    private Context context;
    private List<ExpressItem> list;
    private LayoutInflater inflater;

    public ExpressAdapter(Context context, List<ExpressItem> list) {
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
        ExpressItem item = list.get(position);
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.express_item,null);
            holder.hDesc = convertView.findViewById(R.id.tv_desc);
            holder.hTime = convertView.findViewById(R.id.tv_time);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.hDesc.setText(item.getDesc());
        holder.hTime.setText(item.getTime());
        return convertView;
    }

    /**
     * ViewHolder
     */
    public class ViewHolder {
        public TextView hDesc, hTime;
    }
}
