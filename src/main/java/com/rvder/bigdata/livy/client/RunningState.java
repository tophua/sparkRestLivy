package com.rvder.bigdata.livy.client;

/**
 * create by liush on 2018-4-24
 */
public class RunningState {
    //开始
    public static final int STARTING = 0;
    //空闲
    public static final int IDLE = 1;
    //错误
    public static final int ERROR = 2;
    //死亡
    public static final int DEAD = 3;
    //运行中
    public static final int RUNNING = 4;
    //成功
    public static final int SUCCESS = 5;
    //忙
    public static final int BUSY = 6;
    //没有开始
    public static final int NOT_STARTED = 7;
}
