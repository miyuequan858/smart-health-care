// HospitalDetailActivity.java
package com.example.travelassistant;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.travelassistant.model.Hospital;

public class HospitalDetailActivity extends AppCompatActivity {

    private TextView tvName, tvAttributes, tvDistance, tvLocation, tvContact;
    private ImageView ivHospitalImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detail);

        // 初始化视图
        tvName = findViewById(R.id.tv_name);
        tvAttributes = findViewById(R.id.tv_attributes);
        tvDistance = findViewById(R.id.tv_distance);
        tvLocation = findViewById(R.id.tv_location);
        tvContact = findViewById(R.id.tv_contact);
        ivHospitalImage = findViewById(R.id.iv_hospital_image);

        // 获取传递过来的医院数据
        Hospital hospital = (Hospital) getIntent().getSerializableExtra("hospital");

        if (hospital != null) {
            // 设置医院信息
            tvName.setText(hospital.getName());
            tvAttributes.setText(hospital.getAttributes());
            tvDistance.setText(hospital.getDistance());
            tvLocation.setText(hospital.getLocation());
            tvContact.setText(hospital.getContact());

            // 加载医院图片
            Glide.with(this).load(hospital.getImageUrl()).into(ivHospitalImage);
        }
    }
}