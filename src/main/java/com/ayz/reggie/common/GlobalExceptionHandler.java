package com.ayz.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice(annotations ={RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> SQLExceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error(ex.getMessage());
        String message=ex.getMessage();
        if(message.contains("Duplicate entry")){
            String[] split = message.split(" ");
            return R.error("字段:"+split[2]+"不能重复录入!");
        }
        return R.error("未知错误!");
    }
}
