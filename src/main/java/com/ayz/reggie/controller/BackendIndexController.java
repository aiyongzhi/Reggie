package com.ayz.reggie.controller;

import com.ayz.reggie.common.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class BackendIndexController {

    @PostMapping(value = "/backend/logout.do")
    public R<String> logout(HttpSession session){
        //1:销毁session
        session.removeAttribute("employee");
        return R.success("退出成功!");
    }
}
