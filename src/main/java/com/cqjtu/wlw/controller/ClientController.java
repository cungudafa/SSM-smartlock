package com.cqjtu.wlw.controller;

import com.cqjtu.wlw.pojo.ClientInfo;
import com.cqjtu.wlw.pojo.FaceRecord;
import com.cqjtu.wlw.pojo.FamilyInfo;
import com.cqjtu.wlw.pojo.RepairInfo;
import com.cqjtu.wlw.service.ClientService;
import com.cqjtu.wlw.service.FamilyService;
import com.cqjtu.wlw.service.RepairService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired//自动装配，将ClientService接口实现的类自动注入进来
    private ClientService clientService;
    @Autowired
    private FamilyService familyService;

    @Autowired
    private RepairService repairService;
    /**
     * 处理用户注册的请求
     * @param clientInfo
     * @return
     */
    @RequestMapping("/register")//....client/register.d
    public String doReg(ClientInfo clientInfo,HttpServletRequest request){
        System.out.println("执行ClientInfoController.doReg...");
        //获取前端的输入
        clientService.regClientInfo(clientInfo);
        return "/homepage.html";
    }
    /**
     * 处理用户注销的请求
     * @param clientInfo
     * @return
     */
    @RequestMapping("/dodelete")//.....client/dodelete.d?clientId=2
    public String doDelete(ClientInfo clientInfo){
        clientService.delClientInfo(clientInfo);
        return "client";
    }

    /**
     * 更新顾客信息
     * client/updateInfo.d?clientId=${user_id }
     * client_id,lock_id不可更改获取之前的值
     * 更新client_name,client_password,client_sex,client_phone,client_addr,client_photo
     * @param clientInfo
     * @return
     */
    @RequestMapping("/updateInfoPwd")//.....client/updateInfo.d?clientId=2
    public @ResponseBody ClientInfo doUpdate(ClientInfo clientInfo, HttpServletRequest request){
        String inputUserid=request.getSession().getAttribute("user_id").toString();
        System.out.println("将要更新的clientid："+inputUserid);

        ClientInfo client=new ClientInfo();
        client.setClientId(inputUserid);
        client=clientService.getClientByClientId(client);
        if(clientInfo.getClientId().equals(client.getClientId()) && clientInfo.getLockId().equals(client.getLockId())){
            client=clientService.updateClientInfo(clientInfo);
            System.out.println("------"+inputUserid+"更新client成功！-------");
        }
        return client;
    }


    /**
     * 更新
     * @param cid
     * @param cphone
     * @param cname
     * @param csex
     * @param caddr
     * @param request
     */
    @RequestMapping("/update_client")
    public void dochange(String cid,String cphone,String cname,String csex,String caddr, HttpServletRequest request){
        ClientInfo clientInfo=new ClientInfo();
        clientInfo.setClientId(cid);
        clientInfo=clientService.getClientByClientId(clientInfo);//获取原信息
        System.out.println("将要更新的clientid："+cid);
        clientInfo.setClientPhone(cphone);//更新信息
        clientInfo.setClientAddr(caddr);
        clientInfo.setClientName(cname);
        clientInfo.setClientSex(csex);
        clientService.updateClientInfo(clientInfo);
    }
    @RequestMapping("/updatePwd_client")
    public void dochange(String cid,String pwd1,String pwd2, HttpServletRequest request){
        ClientInfo clientInfo=new ClientInfo();
        clientInfo.setClientId(cid);
        clientInfo=clientService.getClientByClientId(clientInfo);//获取原信息
        //System.out.println("将要更新的clientid："+cid);

        if(clientInfo.getClientPassword().equals(pwd1)){

            clientInfo.setClientPassword(pwd2);//更新信息
            clientService.updateClientPwd(clientInfo);
            //System.out.println("------"+pwd2+"更新client密码成功！-------");
        }
    }

    /**
     * 根据lock——id查询全部人脸识别记录
     * @param lock_id
     * @param resp
     * @param request
     */

    @RequestMapping("/showfaceRecord")
    public void doshowface(String lock_id,HttpServletResponse resp, HttpServletRequest request){
        FaceRecord faceRecord=new FaceRecord();
        faceRecord.setLockId(Integer.parseInt(lock_id));
        //faceRecord.getFaceRecordName(key_words);
        System.out.println(lock_id);

        List<FaceRecord> faceRecords = clientService.getKnownFaceRecords(faceRecord);//.getAllFaceRecordsByName(faceRecord);
        System.out.println(faceRecords.get(0).getFaceRecordUrl());
        try{
            JSONArray data = JSONArray.fromObject(faceRecords);
            resp.setCharacterEncoding("utf-8");
            PrintWriter respWritter = null;
            respWritter = resp.getWriter();
            respWritter.append(data.toString());
            System.out.println("-----------查询人脸识别记录成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 处理查询单个客户信息,及关联的信息
     * 根据client_id查询（client_info表、lock_info表、family_info表）
     * @param clientInfo
     * @param request
     * @return
     * 前端交互：
     * 返回client单个信息
     * clientId,clientName,clientPassword,...
     * client.lockId,client.lockType
     * client.memberId,client.memberName,...
     */
    @RequestMapping("/show")//...client/show?clientId=1
    public @ResponseBody ClientInfo show(ClientInfo clientInfo, HttpServletRequest request){
        clientInfo = clientService.getClientByClientId(clientInfo);
        request.setAttribute("client", clientInfo);
        //System.out.println(clientInfo);
        return clientInfo;//@ResponseBody ClientInfo只返回ClientInfo实体类，不跳转
    }

    @RequestMapping("/repairshow")
    public void repairshow( HttpServletResponse resp, HttpServletRequest request) {
        String inputUserid =request.getSession().getAttribute("user_id").toString();
        System.out.println("repairshow");
        RepairInfo repairInfo =new RepairInfo();
        repairInfo.setClientId(inputUserid);
        try {
            repairInfo=repairService.getNewRepairByClientId(repairInfo);//姓名、地址、手机号
            System.out.println(repairInfo.getWorkerId());
            System.out.println(repairInfo.getRepairGrade());
            JSONArray data = JSONArray.fromObject(repairInfo);
            resp.setCharacterEncoding("utf-8");
            PrintWriter respWritter = resp.getWriter();
            respWritter.append(data.toString());
            System.out.println("成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //System.out.println("失败");
        }

    }
    /**
     * 根据条件模糊查询用户信息
     * @param clientInfo {clientName 可能为空，可能不为空 clientPhone可能为空，可能不为空}
     * @return
     * 前端交互：
     * 返回clients列表,<c>标签循环打印
     * client/list.d?clientId=185 就是id（电话）为185开头的都能查询出来，循环打印到表格中
     */
    @RequestMapping("/list")//...client/list.d?clientId=185
    public String list(ClientInfo clientInfo, HttpSession session){
        List<ClientInfo> list = clientService.getClientInfos(clientInfo);
        session.setAttribute("clients", list);
        System.out.println(list);
        return "client";//跳转到client.html
    }
    @RequestMapping("/clientInfo")
    public void doPost1(ClientInfo clientInfo, HttpServletResponse resp, HttpServletRequest request) {
        try {
            String client_id= request.getSession().getAttribute("user_id").toString();
            System.out.println("登录的"+client_id);
            clientInfo.setClientId(client_id);
            clientInfo = clientService.getClientByClientId(clientInfo);

            System.out.println("查询到："+clientInfo.getClientName()+"\t"+clientInfo.getClientPhone());
            JSONArray data = JSONArray.fromObject(clientInfo);
            resp.setCharacterEncoding("utf-8");
            PrintWriter respWritter = resp.getWriter();
            respWritter.append(data.toString());
            System.out.println("返回前端成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("失败");

        }
    }

    @RequestMapping("/familyshow")
    public void doPost( HttpServletResponse resp, HttpServletRequest request) {
        String inputUserid =request.getSession().getAttribute("user_id").toString();
        System.out.println(inputUserid);
        FamilyInfo familyInfo=new FamilyInfo();
        familyInfo.setClientId(inputUserid);
        try {
            List<FamilyInfo> list =familyService.getFamilyByClientId(familyInfo);//姓名、地址、手机号
            JSONArray data = JSONArray.fromObject(list);

            resp.setCharacterEncoding("utf-8");
            PrintWriter respWritter = resp.getWriter();
            respWritter.append(data.toString());
//            for(FamilyInfo f : list){
//                System.out.println("f.name = " + f.getMemberName());
//            }
//            System.out.println("成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("失败");
        }
    }


    @RequestMapping("/delete_member")
    public void delete(Integer mid, HttpServletRequest request){
        FamilyInfo familyInfo=new FamilyInfo();
        familyInfo.setMemberId(mid);
        System.out.println("将要删除的"+mid);
        familyService.delFamilyInfo(familyInfo);
    }

}
