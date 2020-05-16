package com.nop.githubusersub2_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nop.githubusersub2_2.R;
import com.nop.githubusersub2_2.model.FollowingModel;
import com.nop.githubusersub2_2.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder> {

    private Context context;
    private ArrayList<FollowingModel> data = new ArrayList<>();

    public FollowingAdapter(Context context, ArrayList<FollowingModel> listFollowing) {
        this.context = context;
        this.data = listFollowing;
    }

    //1. method yang menyambungkan layout item
    @NonNull
    @Override
    public FollowingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.following_items, parent, false);
        return new FollowingViewHolder(itemview);
    }

    //3. set data
    @Override
    public void onBindViewHolder(@NonNull FollowingViewHolder holder, int position) {
        holder.tvUsernameFollowing.setText(data.get(position).getLogin());
        Glide.with(context)
                .load(data.get(position).getAvatarUrl())
                .into(holder.ivAvatarFollowing);
    }

    //4.jumlah data
    @Override
    public int getItemCount() {
        return data.size();
    }

    //2. mengenalkan komponen
    public class FollowingViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsernameFollowing;
        ImageView ivAvatarFollowing;

        public FollowingViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsernameFollowing = itemView.findViewById(R.id.tv_username_following);
            ivAvatarFollowing = itemView.findViewById(R.id.iv_avatar_following);
        }
    }
}
