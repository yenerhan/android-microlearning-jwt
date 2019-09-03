package com.microlearning.api;


import com.microlearning.model.user.LoginUserRequestDTO;
import com.microlearning.model.user.SaveUserRequestDTO;
import com.microlearning.model.user.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {

    @Headers({"Content-Type: application/json"})
    @POST("users/createUser")
    Call<User> createUser(@Body SaveUserRequestDTO saveUserRequestDTO);

    @Headers({"Content-Type: application/json"})
    @POST("users/login")
    Call<Void> userLogin(@Body LoginUserRequestDTO loginUserRequestDTO);
}
