package com.cqjtu.wlw.service.impl;

import com.cqjtu.wlw.dao.ClientDao;
import com.cqjtu.wlw.dao.FaceRecordDao;
import com.cqjtu.wlw.pojo.ClientInfo;
import com.cqjtu.wlw.pojo.FaceRecord;
import com.cqjtu.wlw.pojo.RepairInfo;
import com.cqjtu.wlw.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;
    @Autowired
    private FaceRecordDao faceRecordDao;

    /**
     * 用户注册
     * 手机号为id：client_id==client_phone
     * 密码：client_password
     * 门锁：lock_id
     */
    @Override
    public void regClientInfo(ClientInfo clientInfo) {
        clientInfo.setClientId(clientInfo.getClientId());
        clientInfo.setClientName("null");
        clientInfo.setClientPassword(clientInfo.getClientPassword());
        clientInfo.setClientSex("null");
        clientInfo.setClientPhone(clientInfo.getClientId());
        clientInfo.setClientAddr("null");
        clientInfo.setClientPhoto("null");
        clientInfo.setLockId(clientInfo.getLockId());//目前只有1001型号
        clientDao.addClientInfo(clientInfo);
        System.out.println("注册成功");
    }

    @Override
    public void delClientInfo(ClientInfo clientInfo) {
        clientDao.deleteClientInfo(clientInfo);
    }

    @Override
    public ClientInfo updateClientInfo(ClientInfo clientInfo) {
        clientDao.updateClientInfo(clientInfo);
        return clientInfo;
    }

    /**
     * login
     * @param clientInfo
     * @return
     */
    @Override
    public  ClientInfo getClientByClientId(ClientInfo clientInfo){
        return clientDao.getClientByClientId(clientInfo);
    }

    @Override
    public ClientInfo updateClientPwd(ClientInfo clientInfo) {
        clientDao.updateClientPwd(clientInfo);
        return clientInfo;
    }

    /**
     * 张	--》   %张%
     * 10	--》   %10%
     * @return
     */
    @Override
    public List<ClientInfo> getClientInfos(ClientInfo clientInfo) {//姓名、地址、手机号
        if(clientInfo.getClientName()!= null &&!clientInfo.getClientName().equals("")){
            clientInfo.setClientName("%" + clientInfo.getClientName() + "%");
        }
        if(clientInfo.getClientAddr() != null &&!clientInfo.getClientAddr().equals("")){
            clientInfo.setClientAddr("%" + clientInfo.getClientAddr() + "%");
        }
        if(clientInfo.getClientPhone() != null &&!clientInfo.getClientPhone().equals("")){
            clientInfo.setClientPhone("%" + clientInfo.getClientPhone() + "%");
        }
        return clientDao.getClientInfos(clientInfo);
    }
    /**
     * 张	--》   %张%
     * 10	--》   %10%
     * @return
     */
    @Override
    public List<ClientInfo> getRepairClientInfos(ClientInfo clientInfo){
        if(clientInfo.getClientAddr() != null &&!clientInfo.getClientAddr().equals(""))
        {
            clientInfo.setClientAddr("%" + clientInfo.getClientAddr() + "%");
        }
        return clientDao.getRepairInfos(clientInfo);
    }
    @Override
    public ClientInfo getClientById(ClientInfo clientInfo) {
        List<ClientInfo> clients = clientDao.getClientInfos(clientInfo);
        if(clients != null && clients.size() == 1){
            return clients.get(0);
        }
        return null;
    }
    /**
     * 张	--》   %张%
     * 10	--》   %10%
     * @return
     */
    @Override
    public List<RepairInfo> getRepairInfosbyAddr(ClientInfo clientInfo) {
        
        return  clientDao.getRepairInfoByAddr(clientInfo);
    }

    @Override
    public List<FaceRecord> getAllFaceRecordsByName(FaceRecord faceRecord){

        List<FaceRecord> all = null;
        List<FaceRecord> knowns = faceRecordDao.getKnownFaceRecords(faceRecord);
        List<FaceRecord> unknowns = faceRecordDao.getUnKnownFaceRecords(faceRecord);
        for(FaceRecord known:knowns){
            all.add(known);
        }
        for(FaceRecord unknown:unknowns){
            all.add(unknown);
        }
        return all;
    }

    @Override
    public List<FaceRecord> getKnownFaceRecords(FaceRecord faceRecord){
        return faceRecordDao.getKnownFaceRecords(faceRecord);
    }
    @Override
    public List<FaceRecord> getAllFaceRecords(FaceRecord faceRecord){
        List<FaceRecord> faceRecords = null;
        List<FaceRecord> faceRecords1 = null;
        List<FaceRecord> faceRecords2 = null;
        faceRecords1 = faceRecordDao.getKnownFaceRecords(faceRecord);
        for(FaceRecord f1:faceRecords1){
            faceRecords.add(f1);
        }
        faceRecords2 = faceRecordDao.getUnKnownFaceRecords(faceRecord);
        for(FaceRecord f2:faceRecords2){
            faceRecords.add(f2);
        }
        return faceRecords;
    }
    @Override
    public List<FaceRecord> getKnownFaceRecordByName(FaceRecord faceRecord){
        return faceRecordDao.getKnownFaceRecordByName(faceRecord);
    }

    @Override
    public List<FaceRecord> getUnknownFaceRecords(FaceRecord faceRecord){
        return faceRecordDao.getUnKnownFaceRecords(faceRecord);
    }

    @Override
    public List<FaceRecord> getUnknownFaceRecordByName(FaceRecord faceRecord){
        return faceRecordDao.getUnKnownFaceRecordByName(faceRecord);
    }
}
