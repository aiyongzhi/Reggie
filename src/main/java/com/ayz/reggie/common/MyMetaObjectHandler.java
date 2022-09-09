package com.ayz.reggie.common;

import com.ayz.reggie.utils.DateUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
/*
* 自定义元数据对象处理器
* 完成公共字段自动填充功能
* 难点:如何动态的获得当前用户的id
* */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if(metaObject.hasSetter("createUser")){
            metaObject.setValue("createUser", UserIdContextHolder.getContextHolder());
        }
        if(metaObject.hasSetter("createTime")){
            metaObject.setValue("createTime", DateUtils.formatDateTime(new Date()));
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if(metaObject.hasSetter("updateUser")){
            metaObject.setValue("updateUser", UserIdContextHolder.getContextHolder());
        }
        if(metaObject.hasSetter("updateTime")){
            metaObject.setValue("updateTime", DateUtils.formatDateTime(new Date()));
        }
    }
}
