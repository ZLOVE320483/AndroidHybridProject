package com.zlove.hybridproject.adapter;

import android.content.Context;

import com.zlove.hybridproject.R;
import com.zlove.hybridproject.base.BaseRecylerAdapter;
import com.zlove.hybridproject.base.MyRecyclerViewHolder;

import java.util.List;

/**
 * Created by anzhuo002 on 2016/7/5.
 */

public class MyTextAdapter extends BaseRecylerAdapter<String> {
    public MyTextAdapter(Context context, List<String> mDatas) {
        super(context, mDatas, R.layout.item_text);
    }

    /**
     * 这个方法 设置数据到item 中
     * @param holder
     * @param position
     */
    @Override
    public void convert(MyRecyclerViewHolder holder, int position) {
        holder.setText(R.id.tvName, "zlove***" + position);
    }
}
