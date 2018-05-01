package com.rvder.bigdata.livy;

import com.rvder.bigdata.livy.client.LivyBatchClient;
import com.rvder.bigdata.livy.client.RunningState;
import com.rvder.bigdata.livy.model.BatchJobParameters;
import com.rvder.bigdata.livy.model.response.BatchSession;

import java.util.List;

/**
 * create by liush on 2018-4-24
 */
public class LivyTest {
    public static void main(String[] args){
        run();

    }

    public static void run() {
        LivyBatchClient client=new LivyBatchClient();
        BatchJobParameters param = new BatchJobParameters("/Users/apple/Desktop/sparklib/spark-examples_2.11-2.2.0.jar", "org.apache.spark.examples.SparkPi");
        try {

            BatchSession session= client.createJob(param);
            int status = RunningState.NOT_STARTED;
            while(true) {
                status = client.getState(session.getId());
                if(status == RunningState.RUNNING) {
                    System.out.println("Status: RUNNING");
                  List logs= client.getDetailLogs(0,100);
                    for (int i = 0; i < logs.size(); i++) {
                        System.out.println("===="+logs.get(i));
                    }
                }
                else if(status == RunningState.SUCCESS) {
                    System.out.println("Status: SUCCESS");
                    break;
                }
                else if(status == RunningState.ERROR) {
                    System.out.println("Status: ERROR");
                    break;
                }
                else if(status == RunningState.STARTING) {
                    System.out.println("Status: STARTING");
                }
                else {
                    System.out.println("Status: None");
                    break;
                }
                Thread.sleep(1000);
            }
        }  catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
