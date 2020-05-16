package com.nop.githubusersub2_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.nop.githubusersub2_2.adapter.PageAdapter;
import com.nop.githubusersub2_2.adapter.UserAdapter;
import com.nop.githubusersub2_2.model.DetailUserModel;
import com.nop.githubusersub2_2.model.ResponseUser;
import com.nop.githubusersub2_2.model.UserModel;
import com.nop.githubusersub2_2.retrofit.ApiClient;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailUserActivity extends AppCompatActivity {

    DetailUserModel dataDetailUser;
    UserModel dataUser;
    TextView tvName, tvUsername, tvLocation;
    ImageView ivAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        getSupportActionBar().setTitle(getString(R.string.detail_user));

        Bundle bundle = getIntent().getBundleExtra(UserAdapter.DATA_EXTRA);
        dataUser = Parcels.unwrap(bundle.getParcelable(UserAdapter.DATA_USER));

        ivAvatar = findViewById(R.id.iv_avatar_detail);
        tvUsername = findViewById(R.id.tv_username_detail);
        tvName = findViewById(R.id.tv_name_detail);
        tvLocation = findViewById(R.id.tv_location_detail);

        // menampilkan progressdialog
        final ProgressDialog progress = new ProgressDialog(DetailUserActivity.this);
        progress.setMessage(getString(R.string.progress));
        progress.show();

        //ambil data dari parcelable
        Glide.with(DetailUserActivity.this)
                .load(dataUser.getAvatarUrl())
                .into(ivAvatar);
        tvUsername.setText(dataUser.getLogin());

        //panggil api
        Call<DetailUserModel> request = ApiClient.getApiService().getDetailUser(dataUser.getLogin());
        request.enqueue(new Callback<DetailUserModel>() {
            @Override
            public void onResponse(Call<DetailUserModel> call, @NotNull Response<DetailUserModel> response) {
                dataDetailUser = response.body();

                tvName.setText(dataDetailUser.getName());
                tvLocation.setText(dataDetailUser.getLocation());
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<DetailUserModel> call, Throwable t) {

            }
        });

        //tablayout dan viewpager
        PageAdapter pageAdapter = new PageAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(pageAdapter);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        getSupportActionBar().setElevation(0);
    }
}
