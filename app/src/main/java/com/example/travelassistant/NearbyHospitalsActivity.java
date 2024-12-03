// NearbyHospitalsActivity.java
package com.example.travelassistant;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.travelassistant.R;
import com.example.travelassistant.adapter.HospitalListAdapter;
import com.example.travelassistant.model.Hospital;

import java.util.ArrayList;
import java.util.List;

public class NearbyHospitalsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNearbyHospitals;
    private HospitalListAdapter hospitalListAdapter;
    private List<Hospital> hospitals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_hospitals);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 显示返回按钮
        getSupportActionBar().setTitle("附近医院"); // 设置标题

        recyclerViewNearbyHospitals = findViewById(R.id.recyclerViewNearbyHospitals);
        hospitals = getNearbyHospitals(); // 获取附近的医院列表

        // 传递 Context 和 hospitals 列表
        hospitalListAdapter = new HospitalListAdapter(this, hospitals); // 使用适配器
        recyclerViewNearbyHospitals.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNearbyHospitals.setAdapter(hospitalListAdapter);
    }

    private List<Hospital> getNearbyHospitals() {
        // 这里应该从服务器或本地数据库获取附近的医院列表
        List<Hospital> hospitals = new ArrayList<>();
        // 填充示例数据
        hospitals.add(new Hospital("浙江大学医学院附属第一医院余杭院区", "3 km", "综合医院, 三甲", "杭州市余杭区文一西路1369号", "0571-88888888", "hospital1.jpg"));
        hospitals.add(new Hospital("杭州臣品口腔诊所", "4 km", "专科诊所, 口腔", "杭州市西湖区文二路158号", "0571-77777777", "https://example.com/hospital2.jpg"));
        hospitals.add(new Hospital("杭州市妇产科医院", "5 km", "专科医院, 妇产科", "杭州市上城区学士路1号", "0571-66666666", "https://example.com/hospital3.jpg"));
        hospitals.add(new Hospital("浙江省中医院西溪院区", "7 km", "综合医院, 三甲", "杭州市西湖区留和路12号", "0571-99999999", "https://example.com/hospital4.jpg"));
        return hospitals;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}