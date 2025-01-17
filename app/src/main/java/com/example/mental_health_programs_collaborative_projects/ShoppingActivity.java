package com.example.mental_health_programs_collaborative_projects;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shopping);



        // 初始化 RecyclerView
        RecyclerView recyclerView = findViewById(R.id.product_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 设置 Adapter
        List<Product> productList = getSampleProductList();
        ProductAdapter adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);
    }

    // 示例商品数据
    private List<Product> getSampleProductList() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("解压骰子", "玩着解压", 9.99, R.drawable.ic_shop_product));
        productList.add(new Product("日记本打印", "我们可以打印您在该软件中写下的日记并且邮寄给您", 188.88, R.drawable.ic_shop_product_2));
        productList.add(new Product("商品3", "描述3", 7.77, R.drawable.ai_icon));
        productList.add(new Product("商品4", "描述4", 6.66, R.drawable.ai_icon));
        productList.add(new Product("商品5", "描述5", 6.66, R.drawable.ai_icon));
        productList.add(new Product("商品6", "描述6", 6.66, R.drawable.ai_icon));
        return productList;
    }

}
