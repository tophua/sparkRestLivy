package com.rvder.bigdata.livy.client;


import com.rvder.bigdata.livy.model.response.BatchSession;
import com.rvder.bigdata.livy.model.response.LivyLog;
import com.rvder.bigdata.livy.model.response.LivyState;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by liush on 2018/4/24.
 */
public interface LivyBatch {
    @GET("/batches/{batchId}/state")
    //获得状态信息
    Call<LivyState> getBatchSateById(@Path("batchId") Integer batchId);
    @DELETE("/batches/{batchId}")
    //根据sessinId删除session,即kill掉
    //@HTTP(method = "DELETE", path = "/revoke", hasBody = true)
    Call<Void> deleteByBatchId(@Path("batchId") Integer id);
    //获得会话信息
    @GET("/batches/{batchId}")
    Call<BatchSession> getBatchSessionById(@Path("batchId") Integer id);
    //获得运行日志信息
    @GET("/batches/{batchId}/log")
    //from Offset,size,Max number of log lines to return
    Call<LivyLog> getBatchLogById(@Path("batchId") Integer batchId, @Query("from") Integer from,    @Query("size") Integer size);
    @POST("/batches")
    //提交Spark 信息
    Call<BatchSession> createJob(@Body RequestBody requestBody);
}
