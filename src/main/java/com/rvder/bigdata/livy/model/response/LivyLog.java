package com.rvder.bigdata.livy.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * create by liush on 2018-4-21
 */
@Getter
@Setter
@ToString(callSuper=true, includeFieldNames=true)
public class LivyLog {
    //
    private int id;
    //开始
    private int from;
    //大小
    private int size;
    private int  total;
    private List<String> log;
}
