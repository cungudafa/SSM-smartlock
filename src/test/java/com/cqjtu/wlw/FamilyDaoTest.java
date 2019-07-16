package com.cqjtu.wlw;


import com.cqjtu.wlw.dao.FamilyDao;
import com.cqjtu.wlw.pojo.FamilyInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:config/application-*.xml")
public class FamilyDaoTest {
	@Autowired
	private FamilyDao familyDao;
	@Test
	public void test1(){
		FamilyInfo familyInfo = new FamilyInfo();
		familyInfo.setMemberId(2);//查询id为2的family_Info

		familyInfo = familyDao.getFamilyInfoById(familyInfo);
		System.out.println(familyInfo.getMemberName());
		System.out.println(familyInfo.getMemberPhoto());
	}

	@Test
	public void test2(){
		FamilyInfo familyInfo = new FamilyInfo();
		familyInfo.setMemberName("王五");
		familyInfo.setMemberPhoto("100003");
		//FamilyDao.addfamilyInfo(familyInfo);
	}

//	@Test
//	public void test3(){
//		List<familyInfo> list = FamilyDao.getfamilyInfos();
//		for(familyInfo s : list){
//			System.out.println("s.name = " + s.getMemberName());
//			System.out.println("s.number = " + s.getWorkerNumber());
//		}
//	}

	@Test
	public void test4(){
		FamilyInfo familyInfo = new FamilyInfo();
		familyInfo.setMemberName("%lei%");
		familyInfo.setMemberPhoto("1%");
		List<FamilyInfo> list = familyDao.getFamilyInfos(familyInfo);
		for(FamilyInfo s : list){
			System.out.println("s.name = " + s.getMemberName());
			System.out.println("s.photo = " + s.getMemberPhoto());
		}
	}
}
