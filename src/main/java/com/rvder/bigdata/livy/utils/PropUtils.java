package com.rvder.bigdata.livy.utils;

import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import static java.util.stream.Collectors.toMap;
/**
 * create by liush on 2018-4-23
 */
public class PropUtils {
    //properties转换成Map
    public static Map<String, String> asMap(@NonNull Properties properties) {
        return properties.entrySet().stream()
                .collect(toMap(e -> (String) e.getKey(), e -> (String) e.getValue()));
    }


    public static Properties getProperties(String fileName) {
            //加载配置文件，处理一些外部参数
        Properties prop = new Properties();
            InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            try {
                prop.load(in);
            }
            catch (IOException e) {
                System.err.println(e.getMessage());
            }
       return  prop;
    }

    public static  String getBaseUrl(){
        Properties properties= PropUtils.getProperties("application.properties");
         Map map=asMap(properties);
        String url=(String)map.get("livy.server.url");
        String port=(String)map.get("livy.server.port");

        if(StringUtils.isEmpty(url)||StringUtils.isEmpty(port)){
            throw new IllegalArgumentException("properties 文件的livy.server.url,livy.server.port属性不能为null,");
        }
        String baseUrl=url+":"+port;
       return  baseUrl;
    }
}
