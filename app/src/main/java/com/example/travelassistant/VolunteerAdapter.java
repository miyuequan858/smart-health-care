package com.example.travelassistant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

public class VolunteerAdapter extends BaseAdapter {

    private Context context;
    private List<Volunteer> volunteers;

    public VolunteerAdapter(Context context, List<Volunteer> volunteers) {
        this.context = context;
        this.volunteers = volunteers;
    }

    @Override
    public int getCount() {
        return volunteers.size();
    }

    @Override
    public Object getItem(int position) {
        return volunteers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_volunteer, parent, false);
        }

        TextView volunteerName = convertView.findViewById(R.id.volunteer_name);
        TextView statusText = convertView.findViewById(R.id.status_text);
        ImageView statusIcon = convertView.findViewById(R.id.status_icon);

        final Volunteer volunteer = volunteers.get(position);
        volunteerName.setText(volunteer.getName());
        statusText.setText(volunteer.getStatus());

        if ("空闲".equals(volunteer.getStatus())) {
            statusIcon.setImageResource(R.drawable.ic_green_light); // 假设这是你的绿色图标
        } else {
            statusIcon.setImageResource(R.drawable.ic_red_light); // 假设这是你的红色图标
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVolunteerInfoPopup(v, volunteer);
            }
        });

        return convertView;
    }

    private void showVolunteerInfoPopup(View anchorView, Volunteer volunteer) {
        // 弹出下拉框展示志愿者基本信息
        PopupWindow popupWindow = new PopupWindow(context);

        // 创建弹出窗口的布局
        View popupView = LayoutInflater.from(context).inflate(R.layout.popup_volunteer_info, null);

        // 设置弹出窗口的内容
        TextView nameTextView = popupView.findViewById(R.id.popup_name);
        TextView ageTextView = popupView.findViewById(R.id.popup_age);
        TextView phoneTextView = popupView.findViewById(R.id.popup_phone);

        nameTextView.setText("姓名: " + volunteer.getName());
        ageTextView.setText("年龄: " + volunteer.getAge());
        phoneTextView.setText("电话: " + volunteer.getPhone());

        popupWindow.setContentView(popupView);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(anchorView);
    }
}