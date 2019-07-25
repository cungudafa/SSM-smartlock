package com.cqjtu.wlw.controller;

import com.cqjtu.wlw.pojo.ClientInfo;
import com.cqjtu.wlw.pojo.RepairInfo;

import com.cqjtu.wlw.pojo.WorkerInfo;
import com.cqjtu.wlw.service.ClientService;
import com.cqjtu.wlw.service.RepairService;
import com.cqjtu.wlw.service.WorkerService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/manager")
public class ManagerController extends BaseController {
    @Autowired
    private RepairService repairService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private WorkerService workerService;

    /**
     * 处理访问维修模块主页的条件+分页查询的请求
     * @return
     */

    @RequestMapping(value = "/index")
    public String login(RepairInfo repairInfo, HttpServletRequest request) {

        //处理请求中的pager.offset
        handleOffset(request);
        repairInfo.setStart(this.getStart());
        request.setAttribute("repairnamecondition", repairInfo.getStartTime());
        request.setAttribute("requesttimecondition", repairInfo.getRequestTime());
        request.setAttribute("clientidcondition", repairInfo.getClientId());

        //按条件/分页查询出所有记录
        List<RepairInfo> repairs = repairService.getRepairInfos(repairInfo);
        //按条件查询出所有的记录数
        Long count = repairService.getRepairCount(repairInfo);

        request.setAttribute("repairs", repairs);
        request.setAttribute("count", count);

        return "manager";
    }

    @RequestMapping("/repair_list")
    public void doPost1(RepairInfo repairInfo, HttpServletResponse resp, HttpServletRequest request) {
        try {
            List<RepairInfo> list = repairService.getRepairInfos(repairInfo);
            JSONArray data = JSONArray.fromObject(list);
            resp.setCharacterEncoding("utf-8");
            PrintWriter respWritter = resp.getWriter();
            respWritter.append(data.toString());
            System.out.println("成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("失败");

        }
    }
    @RequestMapping("/repair_list2")
    public void doPost2(ClientInfo clientInfo, HttpServletResponse resp, HttpServletRequest request) {
        try {
            List<RepairInfo> list = repairService.getallRepairInfos();//姓名、地址、手机号
            JSONArray data = JSONArray.fromObject(list);
            resp.setCharacterEncoding("utf-8");
            PrintWriter respWritter = resp.getWriter();
            respWritter.append(data.toString());
            System.out.println(list);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("失败");

        }
    }
    @RequestMapping("/worker_list")
    public void doPost3(WorkerInfo workerInfo, HttpServletResponse resp, HttpServletRequest request) {
        try {
            List<WorkerInfo> list = workerService.getWorkerInfos(workerInfo);//workerName
            JSONArray data = JSONArray.fromObject(list);
            resp.setCharacterEncoding("utf-8");
            PrintWriter respWritter = resp.getWriter();
            respWritter.append(data.toString());
            System.out.println(list.get(1).getWorkerId());
            System.out.println("成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("失败");

        }
    }
    @RequestMapping("/worker_grade")
    public void workerGrade(HttpServletResponse resp,HttpServletRequest request) {
        try {
            String inputUserid =request.getSession().getAttribute("user_id").toString();
            System.out.println(inputUserid);
            List<WorkerInfo> list = workerService.getAllWorkers();//姓名、地址、手机号
            System.out.println(list.get(0).getWorkerId());
            JSONArray data = JSONArray.fromObject(list);
            resp.setCharacterEncoding("utf-8");
            PrintWriter respWritter = resp.getWriter();
            respWritter.append(data.toString());
            //System.out.println("成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //System.out.println("失败");
        }
    }
    @RequestMapping("/add_worker")
    public void addWorker(String wid,String wphone,String wname,String wsex,String warea,String waddr, HttpServletRequest request) {

        WorkerInfo workerInfo = new WorkerInfo();
        workerInfo.setWorkerId(wid);//不为空
        workerInfo.setWorkerName(wname);
        workerInfo.setWorkerPassword(wid);//不为空
        workerInfo.setWorkerSex("1");
        workerInfo.setWorkerPhone(wid);//等于id
        workerInfo.setWorkerAddr(waddr);
        workerInfo.setWorkerArea(warea);//默认是："重庆南岸区"
        workerInfo.setWorkerGrade((float) 5.0);//默认5.0不为空，后面可更新
        workerService.regWorkerInfo(workerInfo);
        //System.out.println("添加员工成功");
    }
    //update_RepiarWithWorkerId
    @RequestMapping("/update_RepiarWithWorkerId")
    public void UpdateRepiarWithWid(Integer rid,String wid, HttpServletRequest request) {
        RepairInfo repairInfo=new RepairInfo();
        repairInfo.setRepairId(rid);
        repairService.getRepairById(repairInfo);
        repairInfo.setWorkerId(wid);
        repairService.updateRepairInfo(repairInfo);
        //System.out.println("添加员工成功");
    }

    @RequestMapping("/MessageShow")
    void  doPost22(HttpServletResponse resp,HttpServletRequest request)
    {
        try {
            List<RepairInfo> list = repairService.getMessage22();//姓名、地址、手机号
            System.out.println(list.get(0).getRepairId());
            JSONArray data = JSONArray.fromObject(list);
            resp.setCharacterEncoding("utf-8");
            PrintWriter respWritter = resp.getWriter();
            respWritter.append(data.toString());
            System.out.println("成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("失败");

        }
    }

}
