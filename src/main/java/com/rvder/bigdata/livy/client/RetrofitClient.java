//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.rvder.bigdata.livy.client;


import okhttp3.MediaType;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public abstract class RetrofitClient {
    protected static Retrofit retrofit = null;
    protected final MediaType jsonReq = MediaType.parse("application/json; charset=utf-8");

    public RetrofitClient(String baseUrl) {
        this.initRetrofit(baseUrl);
    }

    private void initRetrofit(String baseUrl) {
        if(!baseUrl.substring(baseUrl.length() - 1).equals("/")) {
            baseUrl = baseUrl + "/";
        }

        if(retrofit == null) {
            retrofit = (new Builder()).baseUrl(baseUrl).addConverterFactory(JacksonConverterFactory.create()).build();
        }

    }

    protected <T> T executeCall(Call<T> call) {
        try {
            //每个请求都被包装成一个Call对象,发送同步请求还是异步请求都是在Call对象
            Response<T> response = call.execute();
            System.out.println("==response=="+response);
            // code >= 200 && code < 300
            if(response.isSuccessful()) {

                return response.body();
            } else {

                System.out.println("execute error:" + response.code() + ":" + response.errorBody().string());
                throw new RuntimeException("execute error:" + response.code() + ":" + response.errorBody().string());
            }
        } catch (IOException var3) {
            throw new RuntimeException("execute error:" + var3.getMessage());
        }
    }
}
