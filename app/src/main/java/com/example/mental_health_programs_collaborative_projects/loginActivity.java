package com.example.mental_health_programs_collaborative_projects;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

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
        CheckBox rememberMe = findViewById(R.id.remember_password);

        // 读取SharedPreferences中的用户名和密码
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString(USERNAME, "");
        String savedPassword = sharedPreferences.getString(PASSWORD, "");

        if (!savedUsername.isEmpty() && !savedPassword.isEmpty()) {
            musername.setText(savedUsername);
            mpassword.setText(savedPassword);
            rememberMe.setChecked(true);
        }

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

        // 先定义一个变量来标记是否有错误
        boolean hasError = false;
        if (username.isEmpty()) {
            musername.setError("用户名不能为空");
            hasError = true;
        }

        if (password.isEmpty()) {
            mpassword.setError("密码不能为空");
            hasError = true;
        }

        // 检查是否勾选了协议
        if (!agreement.isChecked()) {
            showAgreementDialog();
            hasError = true;
        }

        if (hasError) {
            // 如果有错误，就不执行登录操作
            return;
        }

        // 从SharedPreferences读取保存的用户名和密码
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String savedUsername = sharedPreferences.getString(USERNAME, "");
        String savedPassword = sharedPreferences.getString(PASSWORD, "");

        CheckBox rememberMe = findViewById(R.id.remember_password);
        if (rememberMe.isChecked()) {
            editor.putString(USERNAME, username);
            editor.putString(PASSWORD, password);
        } else {
            editor.remove(USERNAME);
            editor.remove(PASSWORD);
        }
        editor.apply();

        if (username.equals(savedUsername) && password.equals(savedPassword)) {
            // 登录成功，跳转到主页面
            Intent intent = new Intent(loginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
        }
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
                agreement.setChecked(true);
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