package com.cqjtu.wlw.controller;

import com.cqjtu.wlw.pojo.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {
    @RequestMapping("/login")
    public String login(){
        return "homepage";
    }
    /**
     * 用户注册
     * @return
     */
    @RequestMapping("/register")
    public String register(){
        //add
        return "homepage";
    }

    /**
     * 登录跳转
     * 0：用户
     * 1：维修人员
     * 2：管理员
     * @param user
     * @param session
     * @return
     */
    @RequestMapping(value = "/dologin")
    public String login(Login user, HttpSession session) {
        System.out.println("用户:" + user.getId() + "\t密码：" + user.getPassword());
        session.setAttribute("user", user);

        if (user.getId() == 0) {
            //check密码
            return "manager";
        }
        if (user.getId() == 2) {
            return "protect";
        }
        if (user.getId() == 1) {
            return "client";
        }else {
            return "homepage";
        }
    }

}
