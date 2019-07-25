package com.cqjtu.wlw.service.impl;

import com.cqjtu.wlw.dao.FamilyDao;
import com.cqjtu.wlw.pojo.FamilyInfo;
import com.cqjtu.wlw.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyDao familyDao;

    /**
     * 用户注册
     */
    @Override
    public void regFamilyInfo(FamilyInfo FamilyInfo) {
        System.out.println("注册...");
        familyDao.addFamilyInfo(FamilyInfo);
    }

    @Override
    public void delFamilyInfo(FamilyInfo FamilyInfo) {
        familyDao.deleteFamilyInfo(FamilyInfo);
    }

    @Override
    public void updateFamilyInfo(FamilyInfo FamilyInfo) {
        familyDao.updateFamilyInfo(FamilyInfo);
    }
    /**
     * 张	--》   %张%
     * 10	--》   %10%
     * @return
     */
    @Override
    public List<FamilyInfo> getFamilyInfos(FamilyInfo familyInfo) {
        if(familyInfo.getMemberName()!= null &&!familyInfo.getMemberName().equals("")){
            familyInfo.setMemberName("%" + familyInfo.getMemberName() + "%");
        }
        if(familyInfo.getMemberPhoto() != null &&!familyInfo.getMemberPhoto().equals("")){
            familyInfo.setMemberPhoto("%" + familyInfo.getMemberPhoto() + "%");
        }
        return familyDao.getFamilyInfos(familyInfo);
    }


    @Override
    public List<FamilyInfo> getFamilyByClientId(FamilyInfo familyInfo) {

        List<FamilyInfo> list=familyDao.getFamilyInfosbyClientId(familyInfo);
        if(list.size()==0)
        {
            System.out.println("无成员信息");
            return null;
        }
        else
        {
            System.out.println("返回成员信息");
            return  list;
        }
    }
}
