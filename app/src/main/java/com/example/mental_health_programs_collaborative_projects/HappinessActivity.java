package com.example.mental_health_programs_collaborative_projects;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HappinessActivity extends AppCompatActivity {

    private RadioGroup question1;
    private RadioGroup question2;
    private RadioGroup question3;
    private RadioGroup question4;
    private RadioGroup question5;
    private RadioGroup question6;
    private RadioGroup question7;
    private RadioGroup question8;
    private RadioGroup question9;
    private RadioGroup question10;
    private RadioGroup question11;
    private RadioGroup question12;
    private RadioGroup question13;
    private RadioGroup question14;
    private RadioGroup question15;
    private RadioGroup question16;
    private RadioGroup question17;
    private RadioGroup question18;
    private RadioGroup question19;
    private RadioGroup question20;
    private RadioGroup question21;
    private RadioGroup question22;
    private RadioGroup question23;
    private RadioGroup question24;
    private RadioGroup question25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happiness);

        question1 = findViewById(R.id.question1);
        question2 = findViewById(R.id.question2);
        question3 = findViewById(R.id.question3);
        question4 = findViewById(R.id.question4);
        question5 = findViewById(R.id.question5);
        question6 = findViewById(R.id.question6);
        question7 = findViewById(R.id.question7);
        question8 = findViewById(R.id.question8);
        question9 = findViewById(R.id.question9);
        question10 = findViewById(R.id.question10);
        question11 = findViewById(R.id.question11);
        question12 = findViewById(R.id.question12);
        question13 = findViewById(R.id.question13);
        question14 = findViewById(R.id.question14);
        question15 = findViewById(R.id.question15);
        question16 = findViewById(R.id.question16);
        question17 = findViewById(R.id.question17);
        question18 = findViewById(R.id.question18);
        question19 = findViewById(R.id.question19);
        question20 = findViewById(R.id.question20);
        question21 = findViewById(R.id.question21);
        question22 = findViewById(R.id.question22);
        question23 = findViewById(R.id.question23);
        question24 = findViewById(R.id.question24);
        question25 = findViewById(R.id.question25);

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateScore();
            }
        });
    }

    private void calculateScore() {
        int score = 0;

        // 问题1
        int selectedId = question1.getCheckedRadioButtonId();
        if (selectedId == R.id.q1_0) score += 6;
        else if (selectedId == R.id.q1_1) score += 5;
        else if (selectedId == R.id.q1_2) score += 4;
        else if (selectedId == R.id.q1_3) score += 3;
        else if (selectedId == R.id.q1_4) score += 2;
        else if (selectedId == R.id.q1_5) score += 1;

        // 问题2
        selectedId = question2.getCheckedRadioButtonId();
        if (selectedId == R.id.q2_0) score += 1;
        else if (selectedId == R.id.q2_1) score += 2;
        else if (selectedId == R.id.q2_2) score += 3;
        else if (selectedId == R.id.q2_3) score += 4;
        else if (selectedId == R.id.q2_4) score += 5;

        // 问题3 (反向计分)
        selectedId = question3.getCheckedRadioButtonId();
        if (selectedId == R.id.q3_0) score += 5;
        else if (selectedId == R.id.q3_1) score += 4;
        else if (selectedId == R.id.q3_2) score += 3;
        else if (selectedId == R.id.q3_3) score += 2;
        else if (selectedId == R.id.q3_4) score += 1;

        // 问题4
        selectedId = question4.getCheckedRadioButtonId();
        if (selectedId == R.id.q4_0) score += 1;
        else if (selectedId == R.id.q4_1) score += 2;
        else if (selectedId == R.id.q4_2) score += 3;
        else if (selectedId == R.id.q4_3) score += 4;
        else if (selectedId == R.id.q4_4) score += 5;
        else if (selectedId == R.id.q4_5) score += 6;

        // 问题5
        selectedId = question5.getCheckedRadioButtonId();
        if (selectedId == R.id.q5_0) score += 1;
        else if (selectedId == R.id.q5_1) score += 2;
        else if (selectedId == R.id.q5_2) score += 3;
        else if (selectedId == R.id.q5_3) score += 4;
        else if (selectedId == R.id.q5_4) score += 5;

        // 问题6 (反向计分)
        selectedId = question6.getCheckedRadioButtonId();
        if (selectedId == R.id.q6_0) score += 5;
        else if (selectedId == R.id.q6_1) score += 4;
        else if (selectedId == R.id.q6_2) score += 3;
        else if (selectedId == R.id.q6_3) score += 2;
        else if (selectedId == R.id.q6_4) score += 1;

        // 问题7
        selectedId = question7.getCheckedRadioButtonId();
        if (selectedId == R.id.q7_0) score += 6;
        else if (selectedId == R.id.q7_1) score += 5;
        else if (selectedId == R.id.q7_2) score += 4;
        else if (selectedId == R.id.q7_3) score += 3;
        else if (selectedId == R.id.q7_4) score += 2;
        else if (selectedId == R.id.q7_5) score += 1;

        // 问题8
        selectedId = question8.getCheckedRadioButtonId();
        if (selectedId == R.id.q8_0) score += 1;
        else if (selectedId == R.id.q8_1) score += 2;
        else if (selectedId == R.id.q8_2) score += 3;
        else if (selectedId == R.id.q8_3) score += 4;
        else if (selectedId == R.id.q8_4) score += 5;
        else if (selectedId == R.id.q8_5) score += 6;

        // 问题9 (反向计分)
        selectedId = question9.getCheckedRadioButtonId();
        if (selectedId == R.id.q9_0) score += 6;
        else if (selectedId == R.id.q9_1) score += 5;
        else if (selectedId == R.id.q9_2) score += 4;
        else if (selectedId == R.id.q9_3) score += 3;
        else if (selectedId == R.id.q9_4) score += 2;
        else if (selectedId == R.id.q9_5) score += 1;

        // 问题10
        selectedId = question10.getCheckedRadioButtonId();
        if (selectedId == R.id.q10_0) score += 1;
        else if (selectedId == R.id.q10_1) score += 2;
        else if (selectedId == R.id.q10_2) score += 3;
        else if (selectedId == R.id.q10_3) score += 4;
        else if (selectedId == R.id.q10_4) score += 5;
        else if (selectedId == R.id.q10_5) score += 6;

        // 问题11
        selectedId = question11.getCheckedRadioButtonId();
        if (selectedId == R.id.q11_0) score += 6;
        else if (selectedId == R.id.q11_1) score += 5;
        else if (selectedId == R.id.q11_2) score += 4;
        else if (selectedId == R.id.q11_3) score += 3;
        else if (selectedId == R.id.q11_4) score += 2;
        else if (selectedId == R.id.q11_5) score += 1;

        // 问题12
        selectedId = question12.getCheckedRadioButtonId();
        if (selectedId == R.id.q12_0) score += 1;
        else if (selectedId == R.id.q12_1) score += 2;
        else if (selectedId == R.id.q12_2) score += 3;
        else if (selectedId == R.id.q12_3) score += 4;
        else if (selectedId == R.id.q12_4) score += 5;
        else if (selectedId == R.id.q12_5) score += 6;

        // 问题13 (反向计分)
        selectedId = question13.getCheckedRadioButtonId();
        if (selectedId == R.id.q13_0) score += 6;
        else if (selectedId == R.id.q13_1) score += 5;
        else if (selectedId == R.id.q13_2) score += 4;
        else if (selectedId == R.id.q13_3) score += 3;
        else if (selectedId == R.id.q13_4) score += 2;
        else if (selectedId == R.id.q13_5) score += 1;

        // 问题14
        selectedId = question14.getCheckedRadioButtonId();
        if (selectedId == R.id.q14_0) score += 1;
        else if (selectedId == R.id.q14_1) score += 2;
        else if (selectedId == R.id.q14_2) score += 3;
        else if (selectedId == R.id.q14_3) score += 4;
        else if (selectedId == R.id.q14_4) score += 5;
        else if (selectedId == R.id.q14_5) score += 6;


        // 问题15
        selectedId = question15.getCheckedRadioButtonId();
        if (selectedId == R.id.q15_0) score += 10;
        else if (selectedId == R.id.q15_1) score += 9;
        else if (selectedId == R.id.q15_2) score += 8;
        else if (selectedId == R.id.q15_3) score += 7;
        else if (selectedId == R.id.q15_4) score += 6;
        else if (selectedId == R.id.q15_5) score += 5;
        else if (selectedId == R.id.q15_6) score += 4;
        else if (selectedId == R.id.q15_7) score += 3;
        else if (selectedId == R.id.q15_8) score += 2;
        else if (selectedId == R.id.q15_9) score += 1;

        // 问题16
        selectedId = question16.getCheckedRadioButtonId();
        if (selectedId == R.id.q16_0) score += 10;
        else if (selectedId == R.id.q16_1) score += 9;
        else if (selectedId == R.id.q16_2) score += 8;
        else if (selectedId == R.id.q16_3) score += 7;
        else if (selectedId == R.id.q16_4) score += 6;
        else if (selectedId == R.id.q16_5) score += 5;
        else if (selectedId == R.id.q16_6) score += 4;
        else if (selectedId == R.id.q16_7) score += 3;
        else if (selectedId == R.id.q16_8) score += 2;
        else if (selectedId == R.id.q16_9) score += 1;

        // 问题17
        selectedId = question17.getCheckedRadioButtonId();
        if (selectedId == R.id.q17_0) score += 1;
        else if (selectedId == R.id.q17_1) score += 2;
        else if (selectedId == R.id.q17_2) score += 3;
        else if (selectedId == R.id.q17_3) score += 4;
        else if (selectedId == R.id.q17_4) score += 5;
        else if (selectedId == R.id.q17_5) score += 6;
        else if (selectedId == R.id.q17_6) score += 7;
        else if (selectedId == R.id.q17_7) score += 8;
        else if (selectedId == R.id.q17_8) score += 9;
        else if (selectedId == R.id.q17_9) score += 10;

        // 问题18
        selectedId = question18.getCheckedRadioButtonId();
        if (selectedId == R.id.q18_0) score += 1;
        else if (selectedId == R.id.q18_1) score += 2;
        else if (selectedId == R.id.q18_2) score += 3;
        else if (selectedId == R.id.q18_3) score += 4;
        else if (selectedId == R.id.q18_4) score += 5;
        else if (selectedId == R.id.q18_5) score += 6;
        else if (selectedId == R.id.q18_6) score += 7;
        else if (selectedId == R.id.q18_7) score += 8;
        else if (selectedId == R.id.q18_8) score += 9;
        else if (selectedId == R.id.q18_9) score += 10;

        // 问题19
        selectedId = question19.getCheckedRadioButtonId();
        if (selectedId == R.id.q19_0) score += 1;
        else if (selectedId == R.id.q19_1) score += 2;
        else if (selectedId == R.id.q19_2) score += 3;
        else if (selectedId == R.id.q19_3) score += 4;
        else if (selectedId == R.id.q19_4) score += 5;

        // 问题20
        selectedId = question20.getCheckedRadioButtonId();
        if (selectedId == R.id.q20_0) score += 1;
        else if (selectedId == R.id.q20_1) score += 2;
        else if (selectedId == R.id.q20_2) score += 3;

        // 问题21
        selectedId = question21.getCheckedRadioButtonId();
        if (selectedId == R.id.q21_0) score += 1;
        else if (selectedId == R.id.q21_1) score += 2;
        else if (selectedId == R.id.q21_2) score += 3;

        // 问题22
        selectedId = question22.getCheckedRadioButtonId();
        if (selectedId == R.id.q22_0) score += 1;
        else if (selectedId == R.id.q22_1) score += 2;
        else if (selectedId == R.id.q22_2) score += 3;

        // 问题23
        selectedId = question23.getCheckedRadioButtonId();
        if (selectedId == R.id.q23_0) score += 1;
        else if (selectedId == R.id.q23_1) score += 2;
        else if (selectedId == R.id.q23_2) score += 3;

        // 问题24
        selectedId = question24.getCheckedRadioButtonId();
        if (selectedId == R.id.q24_0) score += 1;
        else if (selectedId == R.id.q24_1) score += 2;

        // 问题25
        selectedId = question25.getCheckedRadioButtonId();
        if (selectedId == R.id.q25_0) score += 1;
        else if (selectedId == R.id.q25_1) score += 2;
        else if (selectedId == R.id.q25_2) score += 3;
        else if (selectedId == R.id.q25_3) score += 4;
        else if (selectedId == R.id.q25_4) score += 5;
        else if (selectedId == R.id.q25_5) score += 6;
        else if (selectedId == R.id.q25_6) score += 7;

        // 显示总分和评价
        String result = "你的幸福感总分是: " + score + "\n";
        if (score >= 80) {
            result += "你非常幸福！";
        } else if (score >= 60) {
            result += "你比较幸福。";
        } else if (score >= 40) {
            result += "你的幸福感一般。";
        } else {
            result += "你可能需要关注自己的幸福感。";
        }

        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}