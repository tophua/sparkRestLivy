package com.rvder.bigdata.livy.client;

public enum SessionState {
    //会话尚未启动
    not_started,
    //会话运行中
    starting,
    //会话等待输入
    idle,
    //会话正在执行语句
    busy,
    //会话正在关闭
    shutting_down,
    //会话出错
    error,
    //会话已退出
    dead,
    //会话已成功停止
    success
}
