package com.nop.githubusersub2_2.retrofit;

import com.nop.githubusersub2_2.model.DetailUserModel;
import com.nop.githubusersub2_2.model.FollowerModel;
import com.nop.githubusersub2_2.model.FollowingModel;
import com.nop.githubusersub2_2.model.ResponseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 460e52a7f08cd1a29966a9f69a0c51003c59f496")
    Call<ResponseUser> getSearchUser(
            @Query("q") String username
    );

    @GET("users/{username}")
    @Headers("Authorization: token 460e52a7f08cd1a29966a9f69a0c51003c59f496")
    Call<DetailUserModel> getDetailUser(
            @Path("username") String username
    );

    @GET("users/{username}/followers")
    @Headers("Authorization: token 460e52a7f08cd1a29966a9f69a0c51003c59f496")
    //<list> soalnya modelnya dibungkus array karena data ne banyak
    Call<List<FollowerModel>> getFollowerUser(
            @Path("username") String username
    );

    @GET("users/{username}/following")
    @Headers("Authorization: token 460e52a7f08cd1a29966a9f69a0c51003c59f496")
    Call<List<FollowingModel>> getFollowingUser(
            @Path("username") String username
    );
}
