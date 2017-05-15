package com.mmg.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mmg.service.AdminService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:appContext_base.xml","classpath:appContext_hibernate.xml"})
public class AdminServiceImplTest {
	@Autowired
	private AdminService adminService;

	@Test
	public void assertTru() {
//		Assert.assertSame(adminService.checkLogin("admin", "123123"),true);
//		Assert.assertSame(adminService.checkLogin("admin", "123123"),true);
//		Assert.assertSame(adminService.checkLogin("admin", "1231232"),false);
//		Assert.assertSame(adminService.checkLogin("sysadmin", "123123"),true);
//		Assert.assertSame(adminService.checkLogin("sysadmin", "123123"),true);
//		Assert.assertSame(adminService.checkLogin("sysadmin", "1231231"),false);
		
		
		Assert.assertSame(adminService.getUser2RoleList("1"),true);	
		Assert.assertSame(adminService.getUser2RoleList("2"),true);	
	}
}
