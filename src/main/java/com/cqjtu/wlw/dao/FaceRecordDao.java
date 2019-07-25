package com.cqjtu.wlw.dao;

import com.cqjtu.wlw.pojo.FaceRecord;

import java.sql.SQLException;
import java.util.List;

public interface FaceRecordDao {
    /**
     * 根据lock_id查询所有已知访客记录
     * @param faceRecord
     * @return
     */
    List<FaceRecord> getKnownFaceRecords(FaceRecord faceRecord);

    /**
     * 根据known_name查询某一已知访客记录
     * @param faceRecord
     * @return
     */
    List<FaceRecord> getKnownFaceRecordByName(FaceRecord faceRecord);

    /**
     * 根据lock_id查询所有未知访客记录
     * @return
     * @param faceRecord
     */
    List<FaceRecord> getUnKnownFaceRecords(FaceRecord faceRecord);

    /**
     * 根据unknown_name查询某一陌生访客记录
     * @return
     */
    List<FaceRecord> getUnKnownFaceRecordByName(FaceRecord faceRecord);
}