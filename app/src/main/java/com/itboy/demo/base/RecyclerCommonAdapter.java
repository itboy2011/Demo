package com.itboy.demo.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：KangJianMedical
 * 包名：com.sunking.medical.base.adapter
 * 描述：万能适配器
 * 作者： ITBOY
 * 日期： 2017/12/31
 * 时间： 20:09
 */

public abstract class RecyclerCommonAdapter<DATA> extends RecyclerView.Adapter<ViewHolder> {

    protected List<DATA> mData;
    private int mLayoutId;
    protected Context mContext;
    private final LayoutInflater mInflater;
    private MultiItemInterface multiItemInterface;
    protected OnItemClickListener listener;
    protected OnItemLongClickListener longClickListener;





    public RecyclerCommonAdapter(Context context, List<DATA> data, int layoutId) {
        this.mContext = context;
        this.mData = data;
        this.mLayoutId = layoutId;
        mInflater = LayoutInflater.from(context);
    }


    public RecyclerCommonAdapter(Context context, List<DATA> data, MultiItemInterface multiItemInterface) {

        this(context, data, -1);
        this.multiItemInterface = multiItemInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (multiItemInterface != null) {
            //需要多布局
            mLayoutId = viewType;
        }

        View itemView = mInflater.inflate(mLayoutId, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick((ViewHolder) holder, v, position);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longClickListener != null) {
                    longClickListener.onItemLongClick((ViewHolder) holder, v, position);
                /**
                  * 返回值true  说明该事件已处理 不让父类在去处理了
                 */
                    return true;
                }
                /**
                 * 返回值false  说明该事件未处理 让父类继续去处理
                 */
                return false;
            }
        });
        //viewHolder优化
        convert((ViewHolder) holder, mData.get(position), position);

    }


    /**
     * 把必要的参数传出去，让子类去实现做具体的操作
     *
     * @param holder   ViewHolder
     * @param item     当前位置的条目
     * @param position 当前的位置
     */
    protected abstract void convert(ViewHolder holder, DATA item, int position);

    public void setData(List<DATA> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public List<DATA> getData() {
        return mData;
    }


    //下拉加载更多数据
    public void addMoreData(List<DATA> data) {
        if (mData != null) {
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }
    //添加一条数据

    /**
     * @param index mData不为空的情况下
     *              index >=0  正常插入
     *              index 为负数  添加到末尾
     *              if mData为空  直接添加到末尾
     * @param data  图片地址
     */
    public void addData(int index, DATA data) {
        if (mData != null) {
            if (index >= 0) mData.add(index, data);
            else mData.add(data);
        } else {
            mData = new ArrayList<>();
            mData.add(data);
        }
        notifyDataSetChanged();
    }


    public void addData(DATA data) {
        if (mData == null) {
            mData = new ArrayList<>();
            mData.add(data);
        }else  mData.add(data);
        notifyDataSetChanged();

    }

    //删除指定下标数据
    public void removeData(int index) {
        if (mData != null) {
            mData.remove(index);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (multiItemInterface != null)
            return multiItemInterface.getLayoutId(mData, position);

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }
    public OnItemClickListener getItemClickListener() {
        return listener;
    }



    public OnItemLongClickListener getOnItemLongClickListener() {
        return longClickListener;
    }

    /**
     * 定义一个点击事件接口回调
     */
    public interface OnItemClickListener {
        void onItemClick(ViewHolder holder, View view, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(ViewHolder holder, View view, int position);
    }

}
