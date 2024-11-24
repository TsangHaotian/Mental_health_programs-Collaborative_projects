package com.example.mental_health_programs_collaborative_projects;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.example.mental_health_programs_collaborative_projects.fragment.online_consultationFragment;
import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// 将Activity更改为AppCompatActivity
public class ChatActivity extends AppCompatActivity {

    private EditText et_chat;
    private Button btn_send, btn_chat_return;
    private ChatlistAdapter chatAdapter;
    private List<Chatlist> mDatas;

    private SharedPreferences preferences;

    private RecyclerView rc_chatlist;
    private LottieAnimationView lo_msgloading;
    final int MESSAGE_UPDATE_VIEW = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 确保你的布局文件activity_chat中包含了兼容的Toolbar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        // 检查是否需要设置状态栏颜色
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);
        init();

        // 聊天信息
        mDatas = new ArrayList<Chatlist>();

        // 读取用户设置里的文心一言的API_Key等信息
        preferences = this.getSharedPreferences("usersetting", MODE_PRIVATE);
        String apikey = preferences.getString("API_Key", "bGegXiN3FV4ffTvgURYYxNcu");
        String secretkey = preferences.getString("Secret_Key", "ZT2U7idJX2s02LHlWMX4hXhv9vpEb0AW");
        String airole = preferences.getString("Role", "你是一个心理医生，你的病人或许是有抑郁症人群或者没有疾病，你对用户的语言也不要太啰嗦，尽可能给出一些实质性的回答");

        chatAdapter = new ChatlistAdapter(this, mDatas);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rc_chatlist.setLayoutManager(layoutManager);
        // 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rc_chatlist.setHasFixedSize(true);
        // 创建并设置Adapter
        rc_chatlist.setAdapter(chatAdapter);

        // 点击btn_send发送聊天信息
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_send.setVisibility(View.GONE); // 点击发送后，隐藏发送按钮（防止用户重复点点点）
                lo_msgloading.setVisibility(View.VISIBLE);
                // 用户的提问
                String user_ask = et_chat.getText().toString(); // 获取输入框里的信息
                Chatlist C3;
                C3 = new Chatlist("你", user_ask);
                mDatas.add(C3);

                chatAdapter.ResetChatlistAdapter(mDatas);
                rc_chatlist.setAdapter(chatAdapter);

                et_chat.setText("");
                // Wenxin wx=new WenXin(apikey,secretkey,airole);
                WenXin wx = new WenXin(apikey, secretkey, airole);

                // 文心一言的回答
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 请求详情

                        // 调用 GetAnswer 方法
                        try {
                            wx.GetAnswer(user_ask, new WenXin.ResponseCallback() {
                                @Override
                                public void onSuccess(String response) {
                                    // 在这里处理获取到的结果
                                    Chatlist C4;
                                    String wxresult = response.toString();
                                    C4 = new Chatlist("AI", wxresult);

                                    mDatas.add(C4);
                                    chatAdapter.ResetChatlistAdapter(mDatas);

                                    Message msg = new Message();
                                    msg.what = MESSAGE_UPDATE_VIEW;
                                    ChatActivity.this.gHandler.sendMessage(msg);
                                }

                                @Override
                                public void onError(String error) {
                                    // 在这里处理错误情况
                                    Chatlist C4;
                                    String wxresult = "获取信息失败";
                                    C4 = new Chatlist("AI", wxresult);

                                    mDatas.add(C4);
                                    chatAdapter.ResetChatlistAdapter(mDatas);

                                    Message msg = new Message();
                                    msg.what = MESSAGE_UPDATE_VIEW;
                                    ChatActivity.this.gHandler.sendMessage(msg);
                                }
                            });
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }).start();

            }

        });

        // 点击返回，返回mainActivity
        btn_chat_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 直接结束当前Activity，返回上一级
                ChatActivity.this.finish();
            }
        });

    }

    private void init() {
        btn_send = findViewById(R.id.btn_send);
        et_chat = findViewById(R.id.et_chat);
        btn_chat_return = findViewById(R.id.btn_chat_return);
        rc_chatlist = findViewById(R.id.rc_chatlist);
        lo_msgloading = findViewById(R.id.lo_msgloading);
    }

    public Handler gHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_UPDATE_VIEW) {
                rc_chatlist.setAdapter(chatAdapter);
                btn_send.setVisibility(View.VISIBLE); // 恢复按钮
                lo_msgloading.setVisibility(View.INVISIBLE);
                // 读出回答
                // at.ReadOut("Hello?");

                // 滚动到最后一个位置
                LinearLayoutManager layoutManager = (LinearLayoutManager) rc_chatlist.getLayoutManager();
                if (layoutManager != null) {
                    // 直接滚动到最后一个位置
                    rc_chatlist.scrollToPosition(mDatas.size() - 1);
                }
            }
        }
    };

}