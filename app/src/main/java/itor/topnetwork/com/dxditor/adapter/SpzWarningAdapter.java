package itor.topnetwork.com.dxditor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.bean.SpzWarning;

/**
 * @Description:
 * @Created by D.Han on 2018/3/27 15:25 in Peking.
 */

public class SpzWarningAdapter extends RecyclerView.Adapter<SpzWarningAdapter.ViewHolder> implements View.OnClickListener{
    ArrayList<SpzWarning> mData;
    Context context;
    private final DecimalFormat df;

    public SpzWarningAdapter(Context context, ArrayList<SpzWarning> sw) {
        this.context=context;
        //格式化
        df = new DecimalFormat("0.00");
        this.mData=sw;
    }
    public void updateData(ArrayList<SpzWarning> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public SpzWarningAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.spz_warning_recycle_item, parent, false);
        //设置view的点击事件
        v.setOnClickListener(this);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 绑定数据
        holder.title_tv.setText(mData.get(position).getMonitorName());
        holder.type_tv.setText(mData.get(position).getType());
        Double cny = Double.parseDouble(mData.get(position).getMonitorValue());
        holder.value_tv.setText(df.format(cny));
        holder.dw_tv.setText(mData.get(position).getUnit());

        //给holder的itemview添加Tag为position
        holder.itemView.setTag(position);
    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title_tv;
        TextView type_tv;
        TextView value_tv;
        TextView dw_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            title_tv = (TextView) itemView.findViewById(R.id.title_tv);
            type_tv = (TextView) itemView.findViewById(R.id.type_tv);
            value_tv = (TextView) itemView.findViewById(R.id.value_tv);
            dw_tv = (TextView) itemView.findViewById(R.id.dw_tv);
        }
    }
    private OnRecyclerViewItemClickListener  mOnItemClickListener = null;
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onClick(v,(int)v.getTag());
        }
    }
    public void setOnItemClickListener(OnRecyclerViewItemClickListener  listener) {
        this.mOnItemClickListener = listener;
    }
    public interface OnRecyclerViewItemClickListener  {
        void onClick(View view, int position);
    };
}
