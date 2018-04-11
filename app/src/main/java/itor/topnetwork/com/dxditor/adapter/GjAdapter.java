package itor.topnetwork.com.dxditor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.bean.Gjlb;

/**
 * 首页告警列表
 * Created by D.Han on 2017/11/24.
 */

public class GjAdapter extends RecyclerView.Adapter<GjAdapter.ViewHolder> {
    private ArrayList<Gjlb> mData;
    Context context;

    public GjAdapter(Context context, ArrayList<Gjlb> data) {
        this.mData = data;
        this.context = context;
    }

    public void updateData(ArrayList<Gjlb> data) {
        mData.addAll(data);
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
        if(mData.get(position).getType().equals("1")) {
            holder.sb_img_iv.setBackground(context.getResources().getDrawable(R.drawable.zt_img));
            holder.xl_tv.setText(mData.get(position).getXl());
            holder.xl_tv.setTextColor(context.getResources().getColor(R.color.zt_text));
            holder.glb_tv.setText(mData.get(position).getGlb());
            holder.glb_tv.setTextColor(context.getResources().getColor(R.color.zt_text));
            holder.yw_tv.setText(mData.get(position).getYw());
            holder.yw_tv.setTextColor(context.getResources().getColor(R.color.zt_text));
            holder.sb_tv.setText(mData.get(position).getSb());
            holder.sb_tv.setTextColor(context.getResources().getColor(R.color.zt_text));
            holder.sj_tv.setText(mData.get(position).getSj());
            holder.sj_tv.setTextColor(context.getResources().getColor(R.color.zt_text));
        }else if(mData.get(position).getType().equals("2")){
            holder.sb_img_iv.setBackground(context.getResources().getDrawable(R.drawable.ql_img));
            holder.xl_tv.setText(mData.get(position).getXl());
            holder.xl_tv.setTextColor(context.getResources().getColor(R.color.ql_text));
            holder.glb_tv.setText(mData.get(position).getGlb());
            holder.glb_tv.setTextColor(context.getResources().getColor(R.color.ql_text));
            holder.yw_tv.setText(mData.get(position).getYw());
            holder.yw_tv.setTextColor(context.getResources().getColor(R.color.ql_text));
            holder.sb_tv.setText(mData.get(position).getSb());
            holder.sb_tv.setTextColor(context.getResources().getColor(R.color.ql_text));
            holder.sj_tv.setText(mData.get(position).getSj());
            holder.sj_tv.setTextColor(context.getResources().getColor(R.color.ql_text));
        }else if(mData.get(position).getType().equals("3")){
            holder.sb_img_iv.setBackground(context.getResources().getDrawable(R.drawable.spz_img));
            holder.xl_tv.setText(mData.get(position).getXl());
            holder.xl_tv.setTextColor(context.getResources().getColor(R.color.spz_text));
            holder.glb_tv.setText(mData.get(position).getGlb());
            holder.glb_tv.setTextColor(context.getResources().getColor(R.color.spz_text));
            holder.yw_tv.setText(mData.get(position).getYw());
            holder.yw_tv.setTextColor(context.getResources().getColor(R.color.spz_text));
            holder.sb_tv.setText(mData.get(position).getSb());
            holder.sb_tv.setTextColor(context.getResources().getColor(R.color.spz_text));
            holder.sj_tv.setText(mData.get(position).getSj());
            holder.sj_tv.setTextColor(context.getResources().getColor(R.color.spz_text));
        }else if(mData.get(position).getType().equals("4")){
            holder.sb_img_iv.setBackground(context.getResources().getDrawable(R.drawable.gdb_img));
            holder.xl_tv.setText(mData.get(position).getXl());
            holder.xl_tv.setTextColor(context.getResources().getColor(R.color.gdb_text));
            holder.glb_tv.setText(mData.get(position).getGlb());
            holder.glb_tv.setTextColor(context.getResources().getColor(R.color.gdb_text));
            holder.yw_tv.setText(mData.get(position).getYw());
            holder.yw_tv.setTextColor(context.getResources().getColor(R.color.gdb_text));
            holder.sb_tv.setText(mData.get(position).getSb());
            holder.sb_tv.setTextColor(context.getResources().getColor(R.color.gdb_text));
            holder.sj_tv.setText(mData.get(position).getSj());
            holder.sj_tv.setTextColor(context.getResources().getColor(R.color.gdb_text));
        }else if(mData.get(position).getType().equals("5")){
            holder.sb_img_iv.setBackground(context.getResources().getDrawable(R.drawable.rygqj_img));
            holder.xl_tv.setText(mData.get(position).getXl());
            holder.xl_tv.setTextColor(context.getResources().getColor(R.color.rygqj_text));
            holder.glb_tv.setText(mData.get(position).getGlb());
            holder.glb_tv.setTextColor(context.getResources().getColor(R.color.rygqj_text));
            holder.yw_tv.setText(mData.get(position).getYw());
            holder.yw_tv.setTextColor(context.getResources().getColor(R.color.rygqj_text));
            holder.sb_tv.setText(mData.get(position).getSb());
            holder.sb_tv.setTextColor(context.getResources().getColor(R.color.rygqj_text));
            holder.sj_tv.setText(mData.get(position).getSj());
            holder.sj_tv.setTextColor(context.getResources().getColor(R.color.rygqj_text));
        }

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
        ImageView sb_img_iv;
        TextView sj_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            xl_tv = (TextView) itemView.findViewById(R.id.xl_value);
            glb_tv = (TextView) itemView.findViewById(R.id.glb_value);
            yw_tv = (TextView) itemView.findViewById(R.id.sb_value);
            sb_tv = (TextView) itemView.findViewById(R.id.sbbh_value);
            sb_img_iv =  itemView.findViewById(R.id.sb_img_iv);
            sj_tv = (TextView) itemView.findViewById(R.id.sj_value);
        }
    }


}
