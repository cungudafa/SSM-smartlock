package com.cqjtu.wlw.dao;

import com.cqjtu.wlw.pojo.ClientInfo;

import java.util.List;

public interface ClientDao {
    /**
     * 添加一条学生信息
     * @param clientInfo {client_id,client_name,client_sex,client_phone,client_addr,client_photo,lock_id}
     */
    public void addClientInfo(ClientInfo clientInfo);
    /**
     * 删除一条学生信息
     * @param clientInfo{clientId }
     */
    public void deleteClientInfo(ClientInfo clientInfo);
    /**
     * 更新学生信息
     * @param clientInfo {client_id,client_name,client_sex,client_phone,client_addr,client_photo,lock_id}
     */
    public void updateClientInfo(ClientInfo clientInfo);

    /**
     * 根据id查询用户信息
     * @param clientInfo{clientId }
     * @return StudentInfo类型的对象
     */
    public ClientInfo getClientInfoById(ClientInfo clientInfo);

//	/**
//	 * 查询所有学生信息
//	 * @return
//	 */
//	public List<StudentInfo> getStudentInfos();

    /**
     * 根据条件查询学生记录
     * @param studentInfo {studentName 可能为空，可能不为空 studentNumber可能为空，可能不为空}
     * 1.studentName studentNumber都为空 ——> select * from student_info
     * 2.studentName != null --> select * from student_info where student_name like ?
     * 3.studentNumber != null --> select * from student_info where student_number like ?
     * 4.studentName && studentNumber != null
     * 	-->select * from student_info where student_name = ? and student_number = ?
     * @return
     */
    public List<ClientInfo> getClientInfos(ClientInfo clientInfo);
}
