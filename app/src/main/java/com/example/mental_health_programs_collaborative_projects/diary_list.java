package com.example.mental_health_programs_collaborative_projects;

// 导入所需的Android类库
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

// 使用 androidx 库来实现状态栏透明效果
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

// 导入用于文件操作和日期格式化的Java类库
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// diary_list 类继承自 AppCompatActivity，用于显示日记列表
public class diary_list extends AppCompatActivity {

    // 成员变量，用于引用ListView和它的适配器
    private ListView diaryListView;
    private DiaryAdapter adapter;

    // onCreate 方法是Activity生命周期的一部分，用于初始化界面和数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 使状态栏透明
        EdgeToEdge.enable(this);
        // 设置布局文件
        setContentView(R.layout.activity_diary_list);

        // 获取ListView的引用，并设置适配器
        diaryListView = findViewById(R.id.diaryListView);
        List<Diary> diaries = loadDiaries();
        adapter = new DiaryAdapter(this, diaries);
        diaryListView.setAdapter(adapter);

        // 设置长按监听器，用于删除日记项
        diaryListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // 显示删除确认对话框
                new AlertDialog.Builder(diary_list.this)
                        .setTitle("删除日记")
                        .setMessage("确定要删除这条日记吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 从适配器中删除日记项，并更新适配器
                                adapter.remove(adapter.getItem(position));
                                adapter.notifyDataSetChanged();
                                // 删除对应的文件
                                File file = new File(getExternalFilesDir(null), "diary_" + position + ".txt");
                                if (file.exists()) {
                                    file.delete();
                                }
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();

                return true;
            }
        });
    }

    // 加载日记的方法，从外部存储中读取日记文件
    private List<Diary> loadDiaries() {
        List<Diary> diaries = new ArrayList<>();
        File storageDir = getExternalFilesDir(null);
        if (storageDir != null) {
            File[] files = storageDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    // 筛选出以"diary_"开头并以".txt"结尾的文件
                    if (file.isFile() && file.getName().startsWith("diary_") && file.getName().endsWith(".txt")) {
                        diaries.add(new Diary(file));
                    }
                }
            }
        }
        return diaries;
    }

    // Diary 类表示一个日记项，包含文件和日记内容
    public class Diary {
        private File file;

        public Diary(File file) {
            this.file = file;
        }

        // 读取日记内容的方法
        public String getContent() {
            StringBuilder contentBuilder = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String currentLine;
                while ((currentLine = br.readLine()) != null) {
                    contentBuilder.append(currentLine).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return contentBuilder.toString();
        }

        // 获取日记简短内容的方法
        public String getShortContent() {
            String content = getContent();
            return content.length() > 5000 ? content.substring(0, 5000) + "..." : content;
        }

        // 获取格式化后的日记日期的方法
        public String getFormattedDate() {
            long lastModified = file.lastModified();
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return outputFormat.format(new Date(lastModified));
        }
    }

    // DiaryAdapter 类是 ArrayAdapter 的子类，用于适配日记列表
    public class DiaryAdapter extends ArrayAdapter<Diary> {
        public DiaryAdapter(Context context, List<Diary> diaries) {
            super(context, 0, diaries);
        }

        // 获取列表项视图的方法
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Diary diary = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
            }
            TextView text1 = convertView.findViewById(android.R.id.text1);
            TextView text2 = convertView.findViewById(android.R.id.text2);
            text1.setText(diary.getFormattedDate());
            text2.setText(diary.getShortContent()); // 设置日记项的简短内容
            return convertView;
        }
    }
}