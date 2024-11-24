package com.example.mental_health_programs_collaborative_projects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



public class ChatlistAdapter extends RecyclerView.Adapter
        <ChatlistAdapter.MyViewHolder> {
    private List<Chatlist> mDatas;
    private Context mContext;
    private LayoutInflater inflater;

    public ChatlistAdapter(Context context, List<Chatlist> datas){
        this. mContext=context;
        this. mDatas=datas;
        inflater=LayoutInflater. from(mContext);
    }

    public void ResetChatlistAdapter(List<Chatlist> datas){
        this. mDatas=datas;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView rc_tv_speakername;
        TextView rc_tv_speakcontent;
        ImageView rc_iv_portrait;
        View v;
        public MyViewHolder(View view) {
            super(view);
            rc_tv_speakername=(TextView) view.findViewById(R.id.rc_tv_speakername);
            rc_tv_speakcontent=(TextView) view.findViewById(R.id.rc_tv_speakcontent);
            rc_iv_portrait=view.findViewById(R.id.rc_iv_portrait);
            v=view;
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Chatlist da=mDatas.get(position);
        holder.rc_tv_speakername.setText(da.getSpeakerName());
        holder.rc_tv_speakcontent.setText(da.getSpeakContent());

        if(da.getSpeakerName().equals("AI")){//如果是文心一言，重新设置头像
            //ai头像
            holder.rc_iv_portrait.setImageResource(R.drawable.ai_icon);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //这里边还可以对RecycleView里的独立Item进行操作(简单起见这次没有这样弄)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rc_chatlist_layout,parent,false);
        MyViewHolder holder= new MyViewHolder(view);


        return holder;
    }
}
