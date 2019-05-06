package com.example.administrator.inspectionsystem.inspectionsystem.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.inspectionsystem.R;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.Register;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class RegisterAdapter extends RecyclerView.Adapter<RegisterAdapter.RegisterViewHolder> {
    List<Register> list;
    public RegisterAdapter(List<Register> list) {
        this.list = list;
    }
    public void setList(List<Register> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RegisterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RegisterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_register, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RegisterViewHolder holder, int position) {
        holder.load(list.get(position));
    }

    @Override
    public int getItemCount() {
       return list == null ? 0 : list.size();
    }

    public class RegisterViewHolder extends RecyclerView.ViewHolder {
        TextView tvTemperature;
        TextView tvPressure;
        TextView tvAccount;
        TextView tvTime;
        TextView tvComment;
        public RegisterViewHolder(View itemView) {
            super(itemView);
            tvTemperature = itemView.findViewById(R.id.tv_item_register_temperature);
            tvPressure = itemView.findViewById(R.id.tv_item_register_pressure);
            tvAccount = itemView.findViewById(R.id.tv_item_register_operatorName);
            tvTime = itemView.findViewById(R.id.tv_item_register_time);
            tvComment = itemView.findViewById(R.id.tv_item_register_comment);
        }

        public void load(final Register register) {
            tvTemperature.setText("设备温度：" + register.getTemperature().toString());
            tvPressure.setText("设备压强：" + register.getPressure().toString());
            tvAccount.setText("操作人员：" + register.getOperatorName().toString());
            tvTime.setText("操作时间：" + formatTime("yyyy-MM-dd HH:mm", register.getTime()));
            tvComment.setText("特殊描述：" + register.getComment().toString());
        }
    }
    public static String formatTime(String format, long time)
    {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date(time));
    }
}
