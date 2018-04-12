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
import itor.topnetwork.com.dxditor.bean.SpzWarning;

/**
 * @Description:
 * @Created by D.Han on 2018/3/27 15:25 in Peking.
 */

public class SpzWarningAdapter extends RecyclerView.Adapter<SpzWarningAdapter.ViewHolder> implements View.OnClickListener{
    ArrayList<SpzWarning> mData;
    Context context;

    public SpzWarningAdapter(Context context, ArrayList<SpzWarning> sw) {
        this.context=context;
        this.mData=sw;
    }
    public void updateData(ArrayList<SpzWarning> data) {
        if(data!=null){
        mData.addAll(data);}

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
        if (position % 2 == 0) {
            holder.bg_rl.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
        // 绑定数据
        holder.title_tv.setText(mData.get(position).getMonitorName());
        holder.time_tv.setText(mData.get(position).getTime());
        holder.fx_tv.setText(mData.get(position).getFx());
        holder.fl_tv.setText(mData.get(position).getFl());

        //给holder的itemview添加Tag为position
        holder.itemView.setTag(position);
    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout bg_rl;
        TextView title_tv;
        TextView time_tv;
        TextView fx_tv;
        TextView fl_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            bg_rl = itemView.findViewById(R.id.bg_rl);
            title_tv = (TextView) itemView.findViewById(R.id.title_tv);
            time_tv = (TextView) itemView.findViewById(R.id.time_tv);
            fx_tv = (TextView) itemView.findViewById(R.id.fx_tv);
            fl_tv = (TextView) itemView.findViewById(R.id.fl_tv);
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
