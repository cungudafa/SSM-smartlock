package com.cqjtu.wlw;


import com.cqjtu.wlw.dao.RepairDao;
import com.cqjtu.wlw.pojo.ClientInfo;
import com.cqjtu.wlw.pojo.RepairInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:config/application-*.xml")
public class RepairDaoTest {
    @Autowired
    private RepairDao repairDao;



    @Test
    public void test2(){
        RepairInfo repairInfo = new RepairInfo();
        ClientInfo clientInfo = new ClientInfo();

        //repairInfo.setRepairId(123);//id自增
        repairInfo.setRequestTime("2019/7/18_21:31");
        repairInfo.getClientInfo().setClientId("1");//有外键限制：1、错
        //下面可以为空
        //repairInfo.setReDetail("null");
        //repairInfo.setStartTime("null");
        //repairInfo.setReDetail("null");
        //repairInfo.setRepairGrade((float) 5);//默认
        //repairInfo.getWorkerInfo().setWorkerId("null");//有外键限制
        repairDao.addRepairInfo(repairInfo);
    }

    @Test
    public void test4(){
        RepairInfo repairInfo = new RepairInfo();
        repairInfo.setRepairId(1);//
        List<RepairInfo> list = repairDao.getRepairInfos(repairInfo);
        for(RepairInfo s : list){
            System.out.println("s.repair_id = " + s.getRepairId());
            System.out.println("s.client_id = " + s.getClientInfo().getClientId());
            System.out.println("s.client_name = " + s.getClientInfo().getClientName());
        }
    }
    //repair查询一对多
    @Test
    public void test5(){
        RepairInfo repairInfo = new RepairInfo();
        repairInfo.setRepairId(1);//
        List<RepairInfo> list = repairDao.getRepairInfos(repairInfo);
        for(RepairInfo s : list){
            System.out.println("s.repair_id = " + s.getRepairId());
            System.out.println("s.client_id = " + s.getClientInfo().getClientId());
            System.out.println("s.client_name = " + s.getClientInfo().getClientName());
        }
    }



    @Test
    public  void test7()
    {
        List<RepairInfo> list =repairDao.getMssageFromClient();
        for(RepairInfo s : list){
            System.out.println("s.repair_id = " + s.getRepairId());
            System.out.println("s.client_id = " + s.getClientInfo().getClientId());
            System.out.println("s.worker_name = " + s.getWorkerInfo().getWorkerId());
            System.out.println("s.request_time="+s.getRequestTime());
            System.out.println("s.re="+s.getReReview());
            System.out.println("s.g="+s.getRepairGrade());
        }



    }


}
