package com.weenta.assistant.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.weenta.assistant.R;
import com.weenta.assistant.bean.GirlItem;

import java.util.List;

public class GirlAdapter extends RecyclerView.Adapter {
    private static final int NORMAL_ITEM = 0;  //普通Item View
    private static final int FOOTER_ITEM = 1;  //FootView
    private Context context;
    private List<GirlItem> list;
    private LayoutInflater inflater;
    private RecyclerView.LayoutManager layoutManager;

    public GirlAdapter(Context context, List<GirlItem> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder;
        View view;
        switch (viewType) {
            case FOOTER_ITEM:
                view = inflater.inflate(R.layout.item_recycler_footer, null);
                break;
            case NORMAL_ITEM:
            default:
                view = inflater.inflate(R.layout.item_recycler_view, null);
                break;

        }
        holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        if (position < list.size()) {
            GirlItem item = list.get(position);
            Glide.with(context)
                    .load(item.getUrl())
                    .apply(new RequestOptions().override(700, 800).centerCrop())
                    .thumbnail(Glide.with(context).load(R.drawable.girl_off))
                    .into(holder1.hImageView);
        }
    }

    @Override
    public int getItemCount() {
        // 添加footer布局
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position + 1 == getItemCount()) {
            return FOOTER_ITEM;
        } else {
            return NORMAL_ITEM;
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int itemViewType = holder.getItemViewType();
        if (itemViewType == FOOTER_ITEM) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) lp;
            params.setFullSpan(true);
        }
    }

    /**
     * viewholder
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView hImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            hImageView = itemView.findViewById(R.id.girl_img);
        }
    }
}
