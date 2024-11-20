package com.example.mental_health_programs_collaborative_projects.health_resource_fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mental_health_programs_collaborative_projects.NewsDetailsActivity;
import com.example.mental_health_programs_collaborative_projects.adapter.Newsinfo;
import com.example.mental_health_programs_collaborative_projects.R;
import com.example.mental_health_programs_collaborative_projects.adapter.NewsListAdapter;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TabNewsFragment extends Fragment {
    //定义接口域名
    private String url="http://v.juhe.cn/toutiao/index?key=5f98a30931391d7a8f4f01955012cdfe&type=";

    private View rootView;
    private RecyclerView recyclerView;
    private NewsListAdapter mNewsListAdapter;


    private static final String ARG_PARAM = "title";
    private String title;


    private Handler mHandler=new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==100){
                String data=(String) msg.obj;
                Newsinfo newsinfo = new Gson().fromJson(data, Newsinfo.class);
                if(newsinfo!=null && newsinfo.getError_code()==0){
//逻辑处理
                    if (null!=mNewsListAdapter){
                        mNewsListAdapter.setListData(newsinfo.getResult().getData());

                    }

                }else {
                    Toast.makeText(getActivity(),"获取数据失败，请稍后重试", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    public TabNewsFragment() {

    }

    public static TabNewsFragment newInstance(String param) {
        TabNewsFragment fragment = new TabNewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =inflater.inflate(R.layout.fragment_tab_news, container, false);

//初始化控件
        recyclerView=rootView.findViewById(R.id.recyclerView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        初始化适配器
        mNewsListAdapter = new NewsListAdapter(getActivity());

//        设置adapter
        recyclerView.setAdapter(mNewsListAdapter);
//        recyclerView列表点击事件
        mNewsListAdapter.setmOnItemClickListener(new NewsListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Newsinfo.ResultDTO.DataDTO dataDTO, int position) {
//                跳转到详情页
                Intent intent =new Intent(getActivity(), NewsDetailsActivity.class);
//                传递对象的时候，该类一定要实现Serializable
                intent.putExtra("dataDTO",dataDTO);
                startActivity(intent);

            }
        });

//        获取数据
        getHttpData();
    }

    private void getHttpData() {
        //创建OkHttpClient对象
        OkHttpClient okHttpClient=new OkHttpClient();
        //构构造Request对象
        Request request = new Request.Builder()
                .url(url+title)
                .get()
                .build();

        //通过OkHttpClient和Request对象来构建Call对象
        Call call = okHttpClient.newCall(request);
        //通过Call对象的enqueue(Callback)方法来执行异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("------------------","onFailure:"+e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String data = response.body().string();
//                Log.d("----------------","onResponse:"+data);
//                Newsinfo newsinfo = new Gson().fromJson(data, Newsinfo.class);
//                if(newsinfo!=null && newsinfo.getError_code()==0){
////逻辑处理
//             if (null!=mNewsListAdapter){
//                 mNewsListAdapter.setListData(newsinfo.getResult().getData());
//
//             }
//
//                }else {
//                    Toast.makeText(getActivity(),"获取数据失败，请稍后重试", Toast.LENGTH_SHORT).show();
//                }


//                不能在耗时操作里面更新UI，那么需要使用handler
                Message message =new Message();
                message.what=100;
                message.obj =data;
//                发送
                mHandler.sendMessage(message);

            }
        });

    }
}