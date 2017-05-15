package com.mmg.service.impl;

import com.mmg.entity.admin.Rule;
import com.mmg.service.AdminService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:appContext_base.xml","classpath:appContext_hibernate.xml"})
public class AdminServiceImplTest {
	private Log log = LogFactory.getLog(this.getClass());
	@Autowired
	private AdminService adminService;

	@Test
	public void assertTru() {
//		Admin admin = adminService.getAdminInfo("admin");
//		List<User_Role> user_roles = adminService.getUser2RoleList(admin.getId());
//		List<Integer> roleIdlist = CommonUtil.getFieldList(user_roles,User_Role.class,"role","id");
//		List<Role_Rule> role_rules = adminService.getRole2RuleList(roleIdlist);
//		Set ruleList = new HashSet(CommonUtil.getFieldList(role_rules,Role_Rule.class,"rule",""));
		adminService.getAllRuleList();
		List<Rule> ruleList = adminService.getAllRuleList();
		log.fatal("-----------------------------------------------------tset-------------------------------------------------------");
//		Assert.assertSame(adminService.checkLogin("admin", "123123"),true);
//		Assert.assertSame(adminService.getAdminInfo("admin").getPassword(),"123123");
//		Assert.assertSame(adminService.getAdminInfo("admin").getPassword(),"123123");
		for (Object rule: ruleList) {
			log.fatal(rule);
		}
//		Assert.assertSame(adminService.getUser2RoleList("1"),true);
//		Assert.assertSame(adminService.getUser2RoleList("2"),true);
		log.fatal("-----------------------------------------------------end-------------------------------------------------------");
	}
}
