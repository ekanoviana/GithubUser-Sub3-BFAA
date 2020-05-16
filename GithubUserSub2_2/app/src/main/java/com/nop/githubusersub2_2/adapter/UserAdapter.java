package com.nop.githubusersub2_2.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nop.githubusersub2_2.DetailUserActivity;
import com.nop.githubusersub2_2.R;
import com.nop.githubusersub2_2.model.UserModel;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    public static final String DATA_USER = "datauser";
    public static final String DATA_EXTRA = "dataextra";
    private Context context;
    private List<UserModel> data = new ArrayList<>();


    public UserAdapter(Context context, List<UserModel> data) {
        this.context = context;
        this.data = data;
    }

    //1. method yang menyambungkan layout item
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //ngenalin item didalem recyclerview
        View itemview = LayoutInflater.from(context).inflate(R.layout.user_items, parent, false);
        return new MyViewHolder(itemview);
    }

    //3. set data
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        //nggak bisa langsung panggil objectnya, harus panggil method myViewHolder dulu, disni namanya holder
        holder.tvUsername.setText(data.get(position).getLogin());
        Glide.with(context)
                .load(data.get(position).getAvatarUrl())
                .into(holder.ivAvatar);

        //pindah ke detail activity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveDetailActivity = new Intent(context, DetailUserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(DATA_USER, Parcels.wrap(data.get(position)));
                moveDetailActivity.putExtra(DATA_EXTRA, bundle);
                context.startActivity(moveDetailActivity);
            }
        });
    }

    //4. jumlah data
    @Override
    public int getItemCount() {
        //biar yang ditampilkan sesuai sama jumlah datanya
        return data.size();
    }

    //2. mengenalkan komponen item
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername;
        ImageView ivAvatar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_username);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
        }
    }
}
