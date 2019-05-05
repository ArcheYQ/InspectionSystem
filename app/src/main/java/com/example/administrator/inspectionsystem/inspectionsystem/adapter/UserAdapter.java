package com.example.administrator.inspectionsystem.inspectionsystem.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.inspectionsystem.R;
import com.example.administrator.inspectionsystem.inspectionsystem.bean.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    List<User> list;
    public UserAdapter(List<User> list) {
        this.list = list;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.load(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvAccount;
        TextView tvUserName;
        Button btEdit;
        public UserViewHolder(View itemView) {
            super(itemView);
            tvAccount = itemView.findViewById(R.id.tv_item_account);
            btEdit = itemView.findViewById(R.id.bt_item_edit);
            tvUserName = itemView.findViewById(R.id.tv_item_username);
        }
        public void load(User user){
            tvAccount.setText("账号：" + user.getAccount().toString());
            tvUserName.setText("用户名：" + user.getName().toString());
        }
    }
}
