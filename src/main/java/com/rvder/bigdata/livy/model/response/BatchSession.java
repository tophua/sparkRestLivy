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
package com.rvder.bigdata.livy.model.response;

import com.rvder.bigdata.livy.client.SessionState;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a session data for a batch session.
 *表示批处理会话的会话数据
 */
@Getter
@Setter
@ToString(callSuper=true, includeFieldNames=true)
public class BatchSession {

    // For interactive
    //用于交互式
    private Integer id;
    //应用ID
    private String appId;
    //提交此会话的远程用户
    private String  owner;
    //模拟运行时的用户
    private String proxyUser;
    //Session kind (spark, pyspark, or sparkr)
    private String kind;
    //日志
    private ArrayList<String> log=new ArrayList<>();
    //状态
    private String state;
    //运行状态
    /**
     *
     */
    private  Integer runState=0;
    //详细应用信息
    private Map<String,String> appInfo=new HashMap<String,String>();

    public void reset() {
        id = 0;
        state = SessionState.not_started.toString();
    }


}
