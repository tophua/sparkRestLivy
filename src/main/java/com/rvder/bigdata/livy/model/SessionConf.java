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

/**
 * A class to set the configurations that is commonly used for both batch and interactive mode.
 * 一个类用于设置批处理和交互模式通常使用的配置
 */
abstract public class SessionConf {
	//用逗号隔开的driver本地jar包列表以及executor类路径
	protected String jars = null;
	//用逗号隔开的放置在Python应用程序PYTHONPATH上的.zip, .egg, .py文件列表
	protected String pyFiles = null;		// Not implemented yet
	//用逗号隔开的要放置在每个executor工作目录的文件列表
	protected String files = null;			// Not implemented yet
	//代理用户
	protected String proxyUser  =null;		// Not implemented yet
	//Driver程序使用内存大小
	protected String driverMemory = null;	// Not implemented yet
	//Driver程序的使用CPU个数
	protected int driverCores = 0;			// Not implemented yet
	//executor内存大小
	protected String executorMemory = null;	// Not implemented yet
	//每个executor使用的内核数
	protected int executorCores = 0;		// Not implemented yet
	//启动的executor数量
	protected int numExecutors = 0;			// Not implemented yet
	//使用—jars 和 –archives添加应用程序所依赖的第三方jar包等
	protected String archives = null;		// Not implemented yet
	//Application名称
	protected String appName = null;		// Not implemented yet
	//提交YARN队列的名称
	protected String queue=null;
	/**
	 * Set file path of jars to be placed on the java classpath
	 * 设置放置在java类路径上的jar文件路径
	 * @param path path for Jar files Jar文件的路径
	 */
	public void setJars(String[] path) {
		if(path == null) return;
		if(path.length < 1) return;
		
		StringBuffer buf = new StringBuffer();
		// Jars are comma-separated if two or more paths are set.
		//如果设置了两个或多个路径,则分隔符以逗号分隔
		for(int cnt=0; cnt<path.length; cnt++) {
			buf.append("\"");
			buf.append(path[cnt]);
			if(cnt < path.length-1) buf.append("\",");
		}
		jars = buf.toString();
	}

	/**
	 * Set a name for your application
	 * 为您的应用程序设置一个名称
	 * @param name of the application 应用程序
	 */
	public void setAppName(String name) {
		appName = name;
	}

	/**
	 * Get the configuration. The differences of configuration between batch and interactive mode needs to be implemented by subclass.
	 * 获取配置,批处理和交互模式之间的配置差异需要通过子类来实现
	 * Spark configuration properties
	 * @return configuration
	 */
	abstract public String getConf();
}
