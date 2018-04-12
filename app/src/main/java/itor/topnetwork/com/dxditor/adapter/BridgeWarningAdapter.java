package itor.topnetwork.com.dxditor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.bean.BridgeWarning;

/**
 * @Description:
 * @Created by D.Han on 2018/3/23 15:18 in Peking.
 */

public class BridgeWarningAdapter extends RecyclerView.Adapter<BridgeWarningAdapter.ViewHolder> implements View.OnClickListener {
    ArrayList<BridgeWarning> mData;
    Context context;

    public BridgeWarningAdapter(Context context, ArrayList<BridgeWarning> bw) {
        this.mData = bw;
        this.context = context;
    }

    public void updateData(ArrayList<BridgeWarning> data) {
        if (data != null) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public BridgeWarningAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bridge_warning_recycle_item, parent, false);
        //设置view的点击事件
        v.setOnClickListener(this);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(BridgeWarningAdapter.ViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.bg_rl.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
        // 绑定数据
        holder.monitorname_tv.setText(mData.get(position).getMonitorName());
        // holder.itemname_tv.setText(mData.get(position).getItemName());
        holder.initValue_tv.setText(mData.get(position).getInitValue());
        holder.currentValue_tv.setText(mData.get(position).getCurrentValue());
        holder.time_tv.setText(mData.get(position).getTime());

        //给holder的itemview添加Tag为position
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout bg_rl;
        TextView monitorname_tv;
        TextView itemname_tv;
        TextView initValue_tv;
        TextView currentValue_tv;
        TextView time_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            bg_rl = itemView.findViewById(R.id.bg_rl);
            monitorname_tv = (TextView) itemView.findViewById(R.id.monitorname_tv);
            // itemname_tv = (TextView) itemView.findViewById(R.id.itemname_tv);
            initValue_tv = (TextView) itemView.findViewById(R.id.initValue_tv);
            currentValue_tv = (TextView) itemView.findViewById(R.id.currentValue_tv);
            time_tv = (TextView) itemView.findViewById(R.id.time_tv);
        }
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onClick(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnRecyclerViewItemClickListener {
        void onClick(View view, int position);
    }

    ;
}
