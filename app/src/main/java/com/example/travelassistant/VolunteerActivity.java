package com.example.travelassistant;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class VolunteerActivity extends AppCompatActivity {

    private ListView listView;
    private VolunteerAdapter adapter;
    private List<Volunteer> volunteerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);

        // 获取 toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 设置返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listView = findViewById(R.id.volunteer_list);
        volunteerList = new ArrayList<>();

        // 假设从数据库或其他来源获取志愿者信息
        volunteerList.add(new Volunteer("志愿者1", "空闲", 25, "1234567890"));
        volunteerList.add(new Volunteer("志愿者2", "忙碌", 30, "0987654321"));
        volunteerList.add(new Volunteer("志愿者3", "空闲", 28, "1122334455"));

        adapter = new VolunteerAdapter(this, volunteerList);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}