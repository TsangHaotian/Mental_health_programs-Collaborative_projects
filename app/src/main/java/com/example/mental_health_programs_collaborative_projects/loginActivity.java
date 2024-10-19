package com.example.mental_health_programs_collaborative_projects;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class loginActivity extends AppCompatActivity {
    // 声明控件
    private View btnregister;
    private Button btnlogin;
    private EditText musername;
    private EditText mpassword;
    private ImageView togglePassword;
    private CheckBox agreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // 找到控件
        btnregister = findViewById(R.id.btn_register);
        btnlogin = findViewById(R.id.btn_login);
        musername = findViewById(R.id.uer_name);
        mpassword = findViewById(R.id.uer_password);
        togglePassword = findViewById(R.id.view_password);
        agreement = findViewById(R.id.btnagree);

        // 注册按钮跳转
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this, registerActivity.class);
                startActivity(intent);
            }
        });

        // 修改密码可见性代码
        togglePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((mpassword.getInputType() & InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    // 前提检测如果密码可见，就强制转化为不可见的密码
                    mpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    togglePassword.setImageResource(R.drawable.baseline_key_off_24);
                } else {
                    // 如果当前是隐藏状态，切换为可见状态
                    mpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    togglePassword.setImageResource(R.drawable.baseline_key_24);
                }
            }
        });

        // 登录按钮点击事件
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkFieldsAndSubmit();
            }
        });

        // 登录协议的实现
        agreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAgreementDialog();
            }
        });

        agreement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btnlogin.setEnabled(isChecked);
            }
        });
    }

    private void checkFieldsAndSubmit() {
        String username = musername.getText().toString().trim();
        String password = mpassword.getText().toString().trim();

        if (username.isEmpty()) {
            musername.setError("用户名不能为空");
            return;
        }

        if (password.isEmpty()) {
            mpassword.setError("密码不能为空");
            return;
        }

        submitCredentials(username, password);
        // 如果用户名和密码都不为空，执行提交操作并跳转到主页面
        submitCredentials(username, password);
        Intent intent = new Intent(loginActivity.this, MainActivity.class); // 假设你要跳转到的页面是MainActivity
        startActivity(intent);
        /*overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // 添加过渡动画，如果有需要*/
    }

    private void submitCredentials(String username, String password) {
    // 这里添加提交用户名和密码的代码
    // 例如：使用HTTP请求发送到服务器
}

    private void showAgreementDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登录协议");
        builder.setMessage("《应用服务协议》\n" +
                "\n" +
                "欢迎来到我们的应用！本协议规定了您与我们应用之间的关系。通过使用本应用，您同意以下条款：\n" +
                "\n" +
                "1. **服务条款**：您同意仅出于个人非商业目的使用本应用，并遵守所有适用的法律法规。\n" +
                "\n" +
                "2. **用户行为**：您承诺不使用本应用进行任何非法活动，包括但不限于侵犯版权、传播恶意软件或参与诈骗活动。\n" +
                "\n" +
                "3. **内容权利**：我们应用中的内容和材料受版权、商标及其他知识产权法律的保护。未经我们明确书面许可，您不得对这些内容进行修改、复制、分发或以任何其他方式使用。\n" +
                "\n" +
                "4. **隐私政策**：我们尊重您的隐私。我们将按照本应用的隐私政策来处理您的个人信息。\n" +
                "\n" +
                "5. **免责声明**：本应用提供的信息仅供参考，我们不保证信息的准确性、可靠性或完整性。在任何情况下，我们均不对任何直接或间接损失负责，无论是否由我们的行为或遗漏引起。\n" +
                "\n" +
                "6. **协议修改**：我们保留随时修改本协议的权利。任何修改将通过本应用通知您。\n" +
                "\n" +
                "7. **争议解决**：本协议的订立、执行和解释及争议的解决均适用中华人民共和国法律。如有争议，双方应首先友好协商解决；协商不成的，任何一方均可向我们应用注册地的人民法院提起诉讼。\n" +
                "\n" +
                "8. **联系方式**：如果您对本协议有任何疑问，请联系我们：[您的联系邮箱或电话]。\n" +
                "\n" +
                "感谢您选择我们的应用，希望您使用愉快！\n" +
                "\n" +
                "—— [登录吧] 啊对对队");
//设置"同意"按钮
        builder.setPositiveButton("同意", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
//设置"不同意"按钮
        builder.setNegativeButton("不同意", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });

        builder.show();
    }
}