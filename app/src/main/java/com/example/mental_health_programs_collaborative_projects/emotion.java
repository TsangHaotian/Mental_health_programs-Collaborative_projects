package com.example.mental_health_programs_collaborative_projects;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;



public class emotion extends AppCompatActivity {

    private int score = 0; // 总分初始化为0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_emotion);

        // 初始化RadioGroups
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        RadioGroup radioGroup1 = findViewById(R.id.radioGroup1);
        RadioGroup radioGroup2 = findViewById(R.id.radioGroup2);
        RadioGroup radioGroup3 = findViewById(R.id.radioGroup3);
        RadioGroup radioGroup4 = findViewById(R.id.radioGroup4);
        RadioGroup radioGroup5 = findViewById(R.id.radioGroup5);
        RadioGroup radioGroup6 = findViewById(R.id.radioGroup6);
        RadioGroup radioGroup7 = findViewById(R.id.radioGroup7);
        RadioGroup radioGroup8 = findViewById(R.id.radioGroup8);

        Button calculateButton = findViewById(R.id.submit_button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = 0; // 重置总分
                score += getSelectedValue(radioGroup);
                score += getSelectedValue(radioGroup1);
                score += getSelectedValue(radioGroup2);
                score += getSelectedValue(radioGroup3);
                score += getSelectedValue(radioGroup4);
                score += getSelectedValue(radioGroup5);
                score += getSelectedValue(radioGroup6);
                score += getSelectedValue(radioGroup7);
                score += getSelectedValue(radioGroup8);

                displayScore(); // 显示分数
            }
        });
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 结束当前 Activity，实现返回操作
            }
        });
    }

    // 获取选中的值
    private int getSelectedValue(RadioGroup radioGroup) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            return 0;
        }
        RadioButton radioButton = findViewById(selectedId);
        // 提取括号中的数字作为分数
        return Integer.parseInt(radioButton.getText().toString().split("\\(|\\)")[1]);
    }

    // 显示分数
    private void displayScore() {
        String message = "您的总分是: " + score;
        if (score <= 8) {
            message += " - 无不良情绪";
        } else if (score <= 12) {
            message += " - 轻度不良情绪";
        } else if (score <= 16) {
            message += " - 中度不良情绪";
        } else {
            message += " - 重度不良情绪";
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
