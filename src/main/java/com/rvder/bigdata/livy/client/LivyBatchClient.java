/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rvder.bigdata.livy.client;

import com.rvder.bigdata.livy.model.response.BatchSession;
import com.rvder.bigdata.livy.model.response.LivyLog;
import com.rvder.bigdata.livy.model.response.LivyState;
import com.rvder.bigdata.livy.model.BatchJobParameters;
import com.rvder.bigdata.livy.utils.JsonUtil;
import com.rvder.bigdata.livy.utils.PropUtils;
import lombok.Getter;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Call;

import java.util.List;

/**
 * LivyBatchClient是一个使用批处理模式向Livy服务器提交spark工作的类
 * Created by lishu on 2018/4/23.
 */
public class LivyBatchClient extends RetrofitClient{
    @Getter
    private LivyBatch service = null;
    private BatchSession session=null;
    static String baseUrl= PropUtils.getBaseUrl();
    public LivyBatchClient() {
         this(baseUrl);
    }
    /**
     * Retrieves the current session information
     * 检索当前会话信息
     * @return Session object
     */
    public BatchSession getCurrentSession() {
        return session;
    }
    /**
     * 初始化
     */
    public LivyBatchClient(String baseUrl) {
        super(baseUrl);
        service=retrofit.create(LivyBatch.class);
    }
    //提交Job
    public BatchSession createJob(BatchJobParameters jobParameters) {
        if(StringUtils.isEmpty(jobParameters.className)||StringUtils.isEmpty(jobParameters.className)){
            throw  new IllegalArgumentException("jobParameters.className,file不能为null");
        }
        String json= JsonUtil.toJson(jobParameters);
        Call<BatchSession> call = service.createJob(RequestBody.create(jsonReq,json));
        BatchSession session=  executeCall(call);
        String st= session.getState();
        Integer state= RunningState.RUNNING;
        if(st != null) {
            state=getState(st);
            session.setRunState(state);
        }else{
            session.setRunState(RunningState.ERROR);
        }
        this.session=session;
        return session;
    }
    //获得Job状态信息
    public Integer getState(Integer batchId) {
        Call<LivyState> call = service.getBatchSateById(batchId);
        LivyState state=executeCall(call);
       int   runstate=getState(state.getState());
        //session.setRunState(runstate);
        return runstate;
    }
    //获得最新BatchSession
    public BatchSession getSession() {
        return session;
    }

    //获得最新BatchSession
    public BatchSession getSessionById(Integer batchId) {
        if(batchId==null){
            throw  new IllegalArgumentException("sessionId is not null");
        }
        Call<BatchSession> call = service.getBatchSessionById(batchId);
       BatchSession session= executeCall(call);
        int   runstate=getState(session.getState());
        session.setRunState(runstate);
        return session;
    }
    //杀死运行的任务kill
    public void deleteByBatchId(Integer sessionId) {
        if(sessionId==null){
            throw  new IllegalArgumentException("sessionId is not null");
        }
         service.deleteByBatchId(sessionId);
    }
    //获得日志信息
    //from开始的偏移量,size 返回日志行的最大数目
    public List<String> getDetailLogs(int from, int size) {
        if(from<0|| size<=0){
            throw  new IllegalArgumentException("Log Offset,size 不能小于0,");
        }
        Call<LivyLog> call = service.getBatchLogById(session.getId(),from,size);
        LivyLog logs=executeCall(call);
        return logs.getLog();
    }

    private  int getState(String st){
        int state=0;
        if(st.equals("running") == true) state = RunningState.RUNNING;
        else if(st.equals("error") == true) state = RunningState.ERROR;
        else if(st.equals("success") == true) state = RunningState.SUCCESS;
        else if(st.equals("starting") == true) state = RunningState.STARTING;
        else if(st.equals("idle") == true) state = RunningState.IDLE;
        else state = RunningState.ERROR;
        return state;
    }
}
