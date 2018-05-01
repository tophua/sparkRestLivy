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
package com.rvder.bigdata.livy.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * POJO class reqresenting a request for a new batch job.
 * POJO类需要一个新的批处理作业参数
 */
@Getter
@Setter
@ToString(callSuper=true, includeFieldNames=true)
public class BatchJobParameters {
	// Required parameters
	//必需的参数
	public String file;
	//class类Spark Main入库类
	public String className;
	// Optional parameters
	//可选参数
	public List<String> args=null;
	//代理用户
	public String proxyUser;
	//用逗号隔开的driver本地jar包列表以及executor类路径
	public List<String> jars=null;
	//用逗号隔开的放置在Python应用程序PYTHONPATH上的.zip, .egg, .py文件列表
	public List<String> pyFiles=null;
	//用逗号隔开的要放置在每个executor工作目录的文件列表
	public List<String> files=null;
	//Driver程序使用内存大小
	public String driverMemory;
	//Driver程序的使用CPU个数
	public Integer driverCores;
	//executor内存大小
	public String executorMemory;
	//每个executor使用的内核数
	public Integer executorCores;
	//启动的executor数量
	public Integer numExecutors;
	//使用—jars 和 –archives添加应用程序所依赖的第三方jar包等
	public List<String> archives=null;

	public Map<String,String> conf=null;
	/**
	 *  @param pathToJar jar的路径
	 *  @param cls class name 类的名称
	 */
	public BatchJobParameters(String pathToJar, String cls) {
		file = pathToJar;
		className = cls;
	}
}
