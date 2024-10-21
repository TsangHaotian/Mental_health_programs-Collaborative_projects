package com.example.mental_health_programs_collaborative_projects;

import android.content.Context;
import android.widget.Toast;
//toastuti提示代码
public class toastuti {
    private static Toast mToast;
    public static void showmsg(Context context,String msg){
        if (mToast == null){
            mToast = Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        }else{
            mToast.setText(msg);
        }
        mToast.show();
    }

}
