package com.cqjtu.wlw.controller;

import com.cqjtu.wlw.pojo.ClientInfo;
import com.cqjtu.wlw.pojo.RepairInfo;
import com.cqjtu.wlw.pojo.WorkerInfo;
import com.cqjtu.wlw.service.ClientService;
import com.cqjtu.wlw.service.RepairService;
import com.cqjtu.wlw.service.WorkerService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/worker")
public class WorkerController extends BaseController{

    @Autowired//自动装配，将WorkerInfoService接口实现的类自动注入进来
    private WorkerService workerInfoService;
    @Autowired
    private RepairService repairService;
    @Autowired//自动装配，将WorkerInfoService接口实现的类自动注入进来
    private ClientService clientInfoService;
    /**
     * 处理访问老师模块主页的条件+分页查询的请求
     * @return
     */
    @RequestMapping("/index")
    public String index(WorkerInfo workerInfo,HttpServletRequest request){

        //处理请求中的pager.offset
        handleOffset(request);
        workerInfo.setStart(this.getStart());
        request.setAttribute("workernamecondition", workerInfo.getWorkerName());
        request.setAttribute("workephonecondition", workerInfo.getWorkerPhone());
        //按条件/分页查询出所有记录
        List<WorkerInfo> wokers = workerInfoService.getWorkerInfos(workerInfo);
        request.setAttribute("workers", wokers);
        return "worker";
    }
    /**
     * 处理查询单个职员信息的请求
     * @param request
     * @return
     */
    @RequestMapping("/show")//...worker/show.d?workerId=1
    public void show(HttpServletRequest request, HttpServletResponse resp){
        try
        {
            WorkerInfo workerInfo=new WorkerInfo();
            String inputUserid =request.getSession().getAttribute("user_id").toString();
            System.out.println(inputUserid);
            workerInfo.setWorkerId(inputUserid);
            WorkerInfo workerInfo1 = workerInfoService.getWorkerByWorkerId(workerInfo);

            request.setAttribute("workerid", workerInfo.getWorkerId());
            JSONArray data = JSONArray.fromObject(workerInfo1);
            resp.setCharacterEncoding("utf-8");
            PrintWriter respWritter = resp.getWriter();
            respWritter.append(data.toString());
            System.out.println(workerInfo1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("失败");

        }

    }
    @RequestMapping("/repair_list")
    public void doPost(RepairInfo repairInfo, HttpServletResponse resp, HttpServletRequest request) {
        try {
            List<RepairInfo> list = repairService.getRepairInfos(repairInfo);
            JSONArray data = JSONArray.fromObject(list);
            resp.setCharacterEncoding("utf-8");
            PrintWriter respWritter = resp.getWriter();
            respWritter.append(data.toString());

            System.out.println(list.get(1).getClientId());
            System.out.println("成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("失败");

        }
    }
    /**
     * 根据条件查询职员信息
     * @param workerInfo {WorkerName 可能为空，可能不为空 WorkerPhone可能为空，可能不为空}
     * @return
     */
    @RequestMapping("/list")//...Worker/list.d?WorkerId=1
    public String list(WorkerInfo workerInfo, HttpSession session){
        List<WorkerInfo> list = workerInfoService.getWorkerInfos(workerInfo);
        session.setAttribute("workers", list);
        System.out.println(list);
        return "worker";
    }
    /**
     * 根据条件查询职员信息
     * @param s4 {WorkerName 可能为空，可能不为空 WorkerPhone可能为空，可能不为空}

     * @return
     */
    @RequestMapping("/rplist")//...Worker/list.d?WorkerId=1
    public void list(String s4,HttpServletResponse resp,HttpServletRequest request){
        try {
            ClientInfo clientInfo=new ClientInfo();
            clientInfo.setClientAddr(s4);
            //System.out.println(s);
            List<RepairInfo> list = clientInfoService.getRepairInfosbyAddr(clientInfo);
            //List<ClientInfo> list = clientInfoService.getRepairClientInfos(clientInfo);
            //System.out.println("id:"+list.get(1).getClientId()+"addr:");
            JSONArray data = JSONArray.fromObject(list);
            resp.setCharacterEncoding("utf-8");
            PrintWriter respWritter = resp.getWriter();
            respWritter.append(data.toString());
            System.out.println(list);
            System.out.println("成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("失败");
        }
    }
    /**
     * 处理维修人员注册的请求
     * @param workerInfo
     * @return
     */
    @RequestMapping("/doreg")//....Worker/doreg.d
    public String doReg(WorkerInfo workerInfo){
        System.out.println("执行WorkerInfoController.doReg...");
        //获取前端的输入
        workerInfoService.regWorkerInfo(workerInfo);
        return "worker";
    }
    /**
     * 处理维修人员注销的请求
     * @param workerInfo
     * @return
     */
    @RequestMapping("/dodelete")//.....Worker/dodelete.d?WorkerId=2
    public String doDelete(WorkerInfo workerInfo){
        workerInfoService.delWorkerInfo(workerInfo);
        return "worker";
    }

    /**
     * 处理维修人员信息更新的请求
     * @param workerInfo
     * @return
     */
    @RequestMapping("/doupdate")
    public String doUpdate(WorkerInfo workerInfo){

        workerInfoService.updateWorkerInfo(workerInfo);
        return "worker";
    }

}
