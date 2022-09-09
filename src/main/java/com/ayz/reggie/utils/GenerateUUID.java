package com.ayz.reggie.utils;

import java.util.UUID;

public class GenerateUUID {
    public static String getByFilename(String filename){
        if(filename==null){
            throw new RuntimeException("文件名不能为空!");
        }
        String extendName=filename.substring(filename.lastIndexOf("."));
        return UUID.randomUUID().toString()+extendName;
    }
}
