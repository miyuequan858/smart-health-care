// HospitalListAdapter.java
package com.example.travelassistant.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelassistant.R;
import com.example.travelassistant.HospitalDetailActivity;
import com.example.travelassistant.model.Hospital;

import java.util.List;

public class HospitalListAdapter extends RecyclerView.Adapter<HospitalListAdapter.HospitalViewHolder> {

    private List<Hospital> hospitalList;
    private Context context;

    public HospitalListAdapter(Context context, List<Hospital> hospitalList) {
        this.context = context;
        this.hospitalList = hospitalList;
    }

    @NonNull
    @Override
    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hospital, parent, false);
        return new HospitalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalViewHolder holder, int position) {
        final Hospital hospital = hospitalList.get(position);
        holder.nameTextView.setText(hospital.getName());
        holder.attributesTextView.setText(hospital.getAttributes());
        holder.distanceTextView.setText(hospital.getDistance());
        holder.locationTextView.setText(hospital.getLocation());

        // 设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HospitalDetailActivity.class);
                intent.putExtra("hospital", hospital);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hospitalList == null ? 0 : hospitalList.size();
    }

    static class HospitalViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView attributesTextView;
        TextView distanceTextView;
        TextView locationTextView;

        HospitalViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_hospital_name);
            attributesTextView = itemView.findViewById(R.id.tv_attributes);
            distanceTextView = itemView.findViewById(R.id.tv_distance);
            locationTextView = itemView.findViewById(R.id.tv_location);
        }
    }
}