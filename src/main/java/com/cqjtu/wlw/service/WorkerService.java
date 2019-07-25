package com.cqjtu.wlw.service;

import com.cqjtu.wlw.pojo.WorkerInfo;

import java.util.List;

public interface WorkerService {
    /**
     * worker注册
     */
    void regWorkerInfo(WorkerInfo workerInfo);

    /**
     * 学生注销
     */
    void delWorkerInfo(WorkerInfo workerInfo);

    /**
     * 学生信息更新
     */
    WorkerInfo updateWorkerInfo(WorkerInfo workerInfo);

    /**
     * 根据条件查询多条学生信息
     */
    List<WorkerInfo> getWorkerInfos(WorkerInfo workerInfo);
    /**
     * 根据id查询记录
     */
    WorkerInfo getWorkerById(WorkerInfo workerInfo);
    /**
     * 用于登录
     */
    WorkerInfo getWorkerByWorkerId(WorkerInfo workerInfo);

    List<WorkerInfo> getAllWorkers();
}
