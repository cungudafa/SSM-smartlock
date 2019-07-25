package com.cqjtu.wlw.dao;


import com.cqjtu.wlw.pojo.ManagerInfo;

public interface ManagerDao {
    /**
     * login
     * @param managerInfo
     * @return
     */
    ManagerInfo getManagerInfoById(ManagerInfo managerInfo);
}
