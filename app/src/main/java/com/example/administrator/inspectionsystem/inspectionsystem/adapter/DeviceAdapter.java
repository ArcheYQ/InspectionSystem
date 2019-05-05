package com.example.administrator.inspectionsystem.inspectionsystem.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.inspectionsystem.R;
import com.example.administrator.inspectionsystem.inspectionsystem.activity.DetailRegisterActivity;
import com.example.administrator.inspectionsystem.inspectionsystem.activity.EditDeviceActivity;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.Device;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {
    List<Device> list;
    Context context;
    boolean isRegister;
    public DeviceAdapter(List<Device> list, Context context, boolean isRegister) {
        this.list = list;
        this.context = context;
        this.isRegister = isRegister;
    }
    public void setList(List<Device> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DeviceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
        holder.load(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class DeviceViewHolder extends RecyclerView.ViewHolder {
        TextView tvAdmAccount;
        TextView tvPublic;
        TextView tvNumber;
        TextView tvName;
        TextView tvAddTime;
        TextView tvLocation;
        Button btEdit;
        public DeviceViewHolder(View itemView) {
            super(itemView);
            tvAdmAccount = itemView.findViewById(R.id.tv_item_device_admaccount);
            tvPublic = itemView.findViewById(R.id.tv_item_device_public);
            tvNumber = itemView.findViewById(R.id.tv_item_device_number);
            tvName = itemView.findViewById(R.id.tv_item_device_name);
            tvAddTime = itemView.findViewById(R.id.tv_item_device_addtime);
            tvLocation = itemView.findViewById(R.id.tv_item_device_location);
            btEdit = itemView.findViewById(R.id.bt_item_device);
        }
        public void load(final Device device){
            tvAdmAccount.setText("管理人员：" + device.getAdmAccount().toString());
            tvNumber.setText("设备编号：" + device.getNumber().toString());
            tvName.setText("设备名称：" + device.getName().toString());
            tvAddTime.setText("添加时间：" + formatTime("yyyy-MM-dd HH:mm",device.getAddTime()));
            tvLocation.setText("设备位置：" + device.getLocation().toString());
            if (!device.isPublic()) tvPublic.setVisibility(View.VISIBLE);
            if (isRegister) {
                btEdit.setText("登记");
                btEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(DetailRegisterActivity.class,device);

                    }
                });
            }else{
                btEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(EditDeviceActivity.class,device);
                    }
                });
            }

        }
    }
    public void startActivity( Class<?> cls, Device device){
        Intent intent = new Intent(context, cls);
        Bundle bundle = new Bundle();
        bundle.putSerializable("editDevice",device);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    public static String formatTime(String format, long time)
    {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date(time));
    }
}
