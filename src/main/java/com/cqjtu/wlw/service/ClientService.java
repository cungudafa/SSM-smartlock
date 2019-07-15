package com.cqjtu.wlw.service;

import com.cqjtu.wlw.pojo.ClientInfo;

import java.util.List;

public interface ClientService {
    /**
     * client注册
     * @param clientInfo
     */
    public void regClientInfo(ClientInfo clientInfo);

    /**
     * 学生注销
     * @param clientInfo
     */
    public void delClientInfo(ClientInfo clientInfo);

    /**
     * 学生信息更新
     * @param clientInfo
     */
    public void updateClientInfo(ClientInfo clientInfo);

    /**
     * 根据条件查询学生信息
     * @param clientInfo
     * @return
     */
    public List<ClientInfo> getClientInfos(ClientInfo clientInfo);
}
