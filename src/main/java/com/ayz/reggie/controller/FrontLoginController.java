package com.ayz.reggie.controller;

import com.ayz.reggie.common.R;
import com.ayz.reggie.pojo.AddressBook;
import com.ayz.reggie.pojo.User;
import com.ayz.reggie.service.UserService;
import com.ayz.reggie.utils.SMSUtils;
import com.ayz.reggie.utils.ValidateCodeUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class FrontLoginController {
    @Autowired
    UserService userService;

    /*  发送验证码 */
    @RequestMapping(value = "/front/page/login/sendMsg.do")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        String phone=user.getPhone();
        if(phone==null){
            return R.error("获取验证码失败!");
        }
        //1: 生成一个4位验证码
        String validateCode= ValidateCodeUtils.generateValidateCode4String(4);
        log.info("code:{}",validateCode);
        //2: 调用阿里云短信服务api发送短信
//        SMSUtils.sendMessage("阿里云短信测试","SMS_154950909",phone,validateCode);
        //3: 需要将生成的验证码保存到session
        session.setAttribute(phone,validateCode);
        return R.success("验证码发送成功!");
    }

    @RequestMapping(value = "/front/page/login/login.do")
    public R<String> login(@RequestBody Map<String, String> map,HttpSession session){
        //1: 进行登录校验
        String phone=map.get("phone");
        String code=map.get("code");
        String attribute =(String)session.getAttribute(phone);
        boolean success=attribute!=null && attribute.equals(code);//用户登录成功
        //2:判断用户是否为首次登录，如果是首次登录在user表中记录其信息
        if(success){
            LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(queryWrapper);
            if(user==null){
                User newUser=new User();
                newUser.setPhone(phone);
                userService.save(newUser);
                //保存用户id进session中，让过滤器能通过
                session.setAttribute("user",newUser.getId());
            }else{
                session.setAttribute("user",user.getId());
            }
        }
        //3；返回结果
        if(success){
            return R.success("登录成功!");
        }else{
            return R.error("登录失败!");
        }
    }

}
