package com.cqjtu.wlw.controller;

import com.cqjtu.wlw.pojo.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class LoginController {
    @RequestMapping(value = "dologin")
    public String login(Login a, HttpSession session) {
        System.out.println("用户:" + a.getId() + "\t密码：" + a.getPassword());
        session.setAttribute("user", a);

        if (a.getId() == 123456) {
            //check密码
            return "manager";
        }
        if (a.getId() == 10000) {
            return "protect";
        }
        if (a.getId() == 1) {
            return "client";
        }else {
            return "/homepage.html";
        }
    }

}
