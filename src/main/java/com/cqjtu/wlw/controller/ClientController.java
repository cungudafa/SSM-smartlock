package com.cqjtu.wlw.controller;

import com.cqjtu.wlw.pojo.ClientInfo;
import com.cqjtu.wlw.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired//自动装配，将ClientInfoService接口实现的类自动注入进来
    private ClientService clientInfoService;

    /**
     * 处理用户注册的请求
     * @param clientInfo
     * @return
     */
    @RequestMapping("/doreg")//....client/doreg.d
    public String doReg(ClientInfo clientInfo){
        System.out.println("执行ClientInfoController.doReg...");
        //获取前端的输入
        clientInfoService.regClientInfo(clientInfo);
        return "client";
    }
    /**
     * 处理用户注销的请求
     * @param clientInfo
     * @return
     */
    @RequestMapping("/dodelete")//.....client/dodelete.d?clientId=2
    public String doDelete(ClientInfo clientInfo){
        clientInfoService.delClientInfo(clientInfo);
        return "client";
    }

    /**
     * 处理用户信息更新的请求
     * @param clientInfo
     * @return
     */
    @RequestMapping("/doupdate")
    public String doUpdate(ClientInfo clientInfo){
        clientInfoService.updateClientInfo(clientInfo);
        return "client";
    }
    /**
     * 处理查询单个教师信息的请求
     * @param clientInfo
     * @param request
     * @return
     */
    @RequestMapping("/show")//...client/show?clientId=1
    public @ResponseBody ClientInfo show(ClientInfo clientInfo, HttpServletRequest request){
        clientInfo = clientInfoService.getClientById(clientInfo);
        request.setAttribute("client", clientInfo);
        System.out.println(clientInfo);
        return clientInfo;
    }

    /**
     * 根据条件查询用户信息
     * @param clientInfo {clientName 可能为空，可能不为空 clientPhone可能为空，可能不为空}
     * @return
     */
    @RequestMapping("/list")//...client/list.d?clientId=1
    public String list(ClientInfo clientInfo, HttpSession session){
        List<ClientInfo> list = clientInfoService.getClientInfos(clientInfo);
        session.setAttribute("clients", list);
        System.out.println(list);
        return "client";
    }
}
