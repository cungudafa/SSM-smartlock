package com.cqjtu.wlw;

import com.cqjtu.wlw.dao.ClientDao;
import com.cqjtu.wlw.pojo.ClientInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:config/application-*.xml")

public class ClientDaoTest {
    @Autowired
    private ClientDao clientDao;

    @Test
    public void test1() {
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setClientId(1);

        clientInfo = clientDao.getClientInfoById(clientInfo);
        System.out.println(clientInfo.getClientName());
        System.out.println(clientInfo.getClientPhone());
    }

    @Test
    public void test2() {
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setClientId(121);
        clientInfo.setClientName("王五");
        clientInfo.setClientPassword("123456");
        clientInfo.setClientSex("1");
        clientInfo.setClientPhone("1852345678");
        clientInfo.setClientAddr("慧园711");
        clientInfo.setClientPhoto("重庆南岸区");
        clientInfo.setLockId(1001);//目前只有1001型号
        clientDao.addClientInfo(clientInfo);
    }

//	@Test
//	public void test3(){
//		List<ClientInfo> list = ClientDao.getClientInfos();
//		for(ClientInfo s : list){
//			System.out.println("s.name = " + s.getClientName());
//			System.out.println("s.number = " + s.getClientNumber());
//		}
//	}

    @Test
    public void test4() {
        ClientInfo ClientInfo = new ClientInfo();
        ClientInfo.setClientName("%2%");
        ClientInfo.setClientPhone("2%");
        List<ClientInfo> list = clientDao.getClientInfos(ClientInfo);
        for (ClientInfo s : list) {
            System.out.println("client.name = " + s.getClientName());
            System.out.println("client.phone = " + s.getClientPhone());
        }
    }
}