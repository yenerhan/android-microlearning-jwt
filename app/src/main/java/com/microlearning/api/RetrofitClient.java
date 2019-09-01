package com.microlearning.api;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://ec2-52-15-236-131.us-east-2.compute.amazonaws.com/";

    public static Retrofit getRetrofitInstance(Context context) {
        /*
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(chain -> {
            Request original = chain.request();

            Request.Builder builder = original.newBuilder();

            // Veritabanından ya da SharedPreference vs. den tokenımızı çekiyoruz
            String tokenType = "Bearer";
            String authToken = SharedPrefManager.getInstance(context).getToken();

            // Eğer token bilgisi var ise request imizin header ına "Authorization: Bearer tokenanahtari" şeklinde ekleme yapıyoruz.
            if (authToken != null) {
                builder.header("Authorization", tokenType + " " + authToken);
            }

            Request request = builder
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        });

        client.authenticator(new TokenAuthenticator(context));

        client.connectTimeout(60, TimeUnit.SECONDS);
        client.readTimeout(60, TimeUnit.SECONDS);
        client.writeTimeout(60, TimeUnit.SECONDS);*/

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
