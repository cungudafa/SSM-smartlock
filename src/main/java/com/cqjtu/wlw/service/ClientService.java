package com.cqjtu.wlw.service;

import com.cqjtu.wlw.pojo.ClientInfo;
import com.cqjtu.wlw.pojo.FaceRecord;
import com.cqjtu.wlw.pojo.RepairInfo;

import java.sql.SQLException;
import java.util.List;

public interface ClientService {
    /**
     * client注册、添加
     * @param clientInfo
     */
    void regClientInfo(ClientInfo clientInfo);

    /**
     * 学生注销
     * @param clientInfo
     */
    void delClientInfo(ClientInfo clientInfo);

    /**
     * 学生信息更新
     * @param clientInfo
     * @return
     */
    ClientInfo updateClientInfo(ClientInfo clientInfo);

    /**
     * 根据条件查询多条用户信息
     * @param clientInfo
     * @return
     */
    List<ClientInfo> getClientInfos(ClientInfo clientInfo);
    /**
     * 根据条件查询多条用户信息
     * @param clientInfo
     * @return
     */
    List<ClientInfo> getRepairClientInfos(ClientInfo clientInfo);
    /**
     * 根据id查询学生记录
     * @param clientInfo
     * @return
     */
    ClientInfo getClientById(ClientInfo clientInfo);
    /**
     * 根据条件查询多条维修记录信息
     * @param clientInfo
     * @return
     */
    List<RepairInfo> getRepairInfosbyAddr(ClientInfo clientInfo);

    /**
     * 用于用户登录
     * @param clientInfo
     * @return
     */
    ClientInfo getClientByClientId(ClientInfo clientInfo);

    ClientInfo updateClientPwd(ClientInfo clientInfo);
    /**
     * 查询所有访客记录信息
     * @param faceRecord
     * @return
     */
    List<FaceRecord> getAllFaceRecords(FaceRecord faceRecord);

    /**
     * 查询所有已知访客记录
     * @param faceRecord
     * @return
     */
    List<FaceRecord> getKnownFaceRecords(FaceRecord faceRecord) ;

    /**
     * 模糊查询部分已知访客记录
     * @param faceRecord
     * @return
     */
    List<FaceRecord> getKnownFaceRecordByName(FaceRecord faceRecord);

    /**
     * 查询所有未知访客记录
     * @param faceRecord
     * @return
     */
    List<FaceRecord> getUnknownFaceRecords(FaceRecord faceRecord);

    /**
     * 模糊查询部分未知访客记录
     * @param faceRecord
     * @return
     */
    List<FaceRecord> getUnknownFaceRecordByName(FaceRecord faceRecord);
    /**
     * 模糊查询部分未知访客记录
     * @param faceRecord
     * @return
     */
    List<FaceRecord> getAllFaceRecordsByName(FaceRecord faceRecord);
}
