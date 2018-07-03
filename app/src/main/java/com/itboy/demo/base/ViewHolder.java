package com.itboy.demo.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



public class ViewHolder extends RecyclerView.ViewHolder {
    //用于缓存已经加载过的界面 Map

    private SparseArray<View> mViews;

    public ImageView imageView;

    public ViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();

    }


    //获取item中的每个view
    public <T extends View> T getView(int viewId) {
        //多次findViewById 对已有的找到的View做一个缓存
        View view = mViews.get(viewId);
        //使用缓存减少findViewbyId的次数
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        return (T) view;
    }


    //封装通用的功能  设置文本
    public ViewHolder setText(int viewId, CharSequence text) {
        TextView textView = getView(viewId);
        textView.setText(text);


        return this;
    }

    //封装通用的功能  设置文本
    public ViewHolder setText(TextView textView, CharSequence text) {
        textView.setText(text);
        return this;
    }


}
