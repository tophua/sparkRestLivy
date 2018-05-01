package com.rvder.bigdata.livy.model.response;

import lombok.Getter;
import lombok.Setter;

/**
 * create by liush on 2018-4-23
 */
@Getter
@Setter
public class LivyState {
    //Batch session id
    private  int id;
    //The current state of batch session
    private  String state;
}
