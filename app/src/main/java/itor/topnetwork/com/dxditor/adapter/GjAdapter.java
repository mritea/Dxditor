package itor.topnetwork.com.dxditor.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.bean.Gjlb;

/**
 * Created by D.Han on 2017/11/24.
 */

public class GjAdapter extends RecyclerView.Adapter<GjAdapter.ViewHolder>{
    private ArrayList<Gjlb> mData;

    public GjAdapter(Context context,ArrayList<Gjlb> data) {
        this.mData = data;
    }

    public void updateData(ArrayList<Gjlb> data) {
        this.mData = data;
        notifyDataSetChanged();
    }


    @Override
    public GjAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_gj_item, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    
    @Override
    public void onBindViewHolder(GjAdapter.ViewHolder holder, int position) {
        // 绑定数据
        holder.xl_tv.setText(mData.get(position).getXl());
        holder.glb_tv.setText(mData.get(position).getGlb());
        holder.yw_tv.setText(mData.get(position).getYw());
        holder.sb_tv.setText(mData.get(position).getSb());
        holder.dqz_tv.setText(mData.get(position).getDqz());
        holder.sj_tv.setText(mData.get(position).getSj());
        if(Integer.parseInt(mData.get(position).getDqz())>30){
            holder.wd_rl.setBackground(Color.);
        }else if (Integer.parseInt(mData.get(position).getDqz())>30){

        }else if (Integer.parseInt(mData.get(position).getDqz())>30){}
    }

   
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView xl_tv;
        TextView glb_tv;
        TextView yw_tv;
        TextView sb_tv;
        TextView dqz_tv;
        TextView sj_tv;
        RelativeLayout wd_rl;

        public ViewHolder(View itemView) {
            super(itemView);
            xl_tv= (TextView) itemView.findViewById(R.id.xl_value);
            glb_tv= (TextView) itemView.findViewById(R.id.glb_value);
           yw_tv= (TextView) itemView.findViewById(R.id.sb_value);
            sb_tv= (TextView) itemView.findViewById(R.id.sbbh_value);
            dqz_tv= (TextView) itemView.findViewById(R.id.wd_value_tv);
            sj_tv= (TextView) itemView.findViewById(R.id.sj_value);
            wd_rl= (RelativeLayout) itemView.findViewById(R.id.wd_rl);
        }
    }


}
