package com.cqjtu.wlw.pojo;

public class FaceRecord {
    private Integer faceRecordId;
    private String faceRecordName;
    private String faceRecordUrl;
    private Integer lockId;
    public Integer getFaceRecordId() {
        return faceRecordId;
    }
    public void setFaceRecordId(Integer faceRecordId) {
        this.faceRecordId = faceRecordId;
    }
    public String getFaceRecordName() {
        return faceRecordName;
    }
    public void setFaceRecordName(String faceRecordName) {
        this.faceRecordName = faceRecordName;
    }
    public String getFaceRecordUrl() {
        return faceRecordUrl;
    }
    public void setFaceRecordUrl(String faceRecordUrl) {
        this.faceRecordUrl = faceRecordUrl;
    }

    public Integer getLockId() {
        return lockId;
    }

    public void setLockId(Integer lockId) {
        this.lockId = lockId;
    }
}
