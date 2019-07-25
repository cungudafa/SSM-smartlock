package com.cqjtu.wlw.service.impl;

import com.cqjtu.wlw.dao.WorkerDao;
import com.cqjtu.wlw.pojo.WorkerInfo;
import com.cqjtu.wlw.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerDao workerDao;
    /**
     * 用户注册
     */
    @Override
    public void regWorkerInfo(WorkerInfo workerInfo) {
        System.out.println("注册...");
        workerDao.addWorkerInfo(workerInfo);
    }

    @Override
    public void delWorkerInfo(WorkerInfo workerInfo) {
        workerDao.deleteWorkerInfo(workerInfo);

    }

    @Override
    public WorkerInfo updateWorkerInfo(WorkerInfo workerInfo) {
        workerDao.updateWorkerInfo(workerInfo);
        return workerDao.getWorkerByWorkerId(workerInfo);
    }
    /**
     * 张	--》   %张%
     * 10	--》   %10%
     * @return
     */
    @Override
    public List<WorkerInfo> getWorkerInfos(WorkerInfo workerInfo) {
        if(workerInfo.getWorkerName()!= null &&!workerInfo.getWorkerName().equals("")){
            workerInfo.setWorkerName("%" + workerInfo.getWorkerName() + "%");
        }
        if(workerInfo.getWorkerAddr() != null &&!workerInfo.getWorkerAddr().equals("")){
            workerInfo.setWorkerAddr("%" + workerInfo.getWorkerAddr() + "%");
        }
        if(workerInfo.getWorkerPhone() != null &&
                !workerInfo.getWorkerPhone().equals("")){
            workerInfo.setWorkerPhone("%" +workerInfo.getWorkerPhone() + "%");
        }
        return workerDao.getWorkerInfos(workerInfo);
    }

    @Override
    public WorkerInfo getWorkerById(WorkerInfo workerInfo) {
        List<WorkerInfo> workers = workerDao.getWorkerInfos(workerInfo);
        if(workers != null && workers.size() == 1){
            return workers.get(0);
        }
        return null;
    }

    /**
     * login
     */
    @Override
    public WorkerInfo getWorkerByWorkerId(WorkerInfo workerInfo){
        return workerDao.getWorkerByWorkerId(workerInfo);
    }

    @Override
    public List<WorkerInfo> getAllWorkers(){
        List<WorkerInfo> list = workerDao.getWorkers();
        System.out.println(list.get(0).getWorkerName());
        return list;
    }
}