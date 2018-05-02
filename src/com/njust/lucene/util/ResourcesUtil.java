package com.njust.lucene.util;


/**
 * Created by Trey Wang on 2018/5/2.
 * 根据相对路径返回资源文件的路径
 */
public class ResourcesUtil {

    public static final String getResource(String file) {
        return ResourcesUtil.class.getClassLoader().getResource(".").getPath().substring(1)+file;
    }

    public static void main(String[] args) {
        ResourcesUtil resourcesUtil = new ResourcesUtil();
        System.out.println(resourcesUtil.getResource("index"));
    }
}
