
package com.cqjtu.wlw;

import com.cqjtu.wlw.dao.FaceRecordDao;
import com.cqjtu.wlw.pojo.FaceRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:config/application-*.xml")
public class FaceRecordDaoTest {

    @Autowired
    private FaceRecordDao faceRecordDao;

    @Test
    public void test1(){
        FaceRecord faceRecord=new FaceRecord();
        faceRecord.setLockId(1001);
        //faceRecord.setFaceRecordName("1");
        List<FaceRecord> faceRecords = null;
        faceRecords = faceRecordDao.getKnownFaceRecords(faceRecord);
        for(FaceRecord f:faceRecords){
            System.out.println("known_name = " + f.getFaceRecordName());
        }
        faceRecords = faceRecordDao.getUnKnownFaceRecords(faceRecord);
        for(FaceRecord f:faceRecords){
            System.out.println("unknown_name = " + f.getFaceRecordName());
        }
    }
    @Test
    public void test2(){
        FaceRecord faceRecord=new FaceRecord();
        faceRecord.setLockId(1001);
        //faceRecord.setFaceRecordName("1");
        List<FaceRecord> faceRecords = null;
        faceRecords = faceRecordDao.getUnKnownFaceRecords(faceRecord);
        for(FaceRecord f:faceRecords){
            System.out.println("unknown_name = " + f.getFaceRecordName());
        }
    }

}