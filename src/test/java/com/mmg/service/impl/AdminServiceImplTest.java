package com.mmg.service.impl;

import com.mmg.common.Page;
import com.mmg.entity.admin.Rule;
import com.mmg.service.AdminService;
import com.mmg.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:appContext_base.xml","classpath:appContext_hibernate.xml","classpath:appContext_wsclient.xml"})
@SuppressWarnings("unused")
public class AdminServiceImplTest {
	private Log log = LogFactory.getLog(this.getClass());
	@Autowired
	private AdminService adminService;
	@Test
	public void assertTru() {
		log.debug(StringUtil.getMd5Stri("123123"));
		int count = adminService.getObjectCount(Rule.class);
    	Page<Rule> page = new Page();
    	page.setCount(count);
    	page.setCurPage(2);
    	page.setPageSize(2);
		List<Rule> list = adminService.getPageList(Rule.class,page);
//		List<Role> roleList = adminService.getAllRoleList(Role.class,"1");
//		List<Role> roleList1 = adminService.getAllRoleList(Role.class,"1");
//		List<Rule> ruleList = adminService.getAllRuleList(Rule.class,"1");
//		List<Admin> adminList = adminService.getAllAdminList(Admin.class);
//		List<Admin> adminList2 = adminService.getAllAdminList(Admin.class);
		
//		List<Role> roleList = adminService.getAllRoleList(Role.class);
//		List<Role> roleList1 = adminService.getAllRoleList(Role.class);
//		List<Rule> ruleList = adminService.getAllRuleList(Rule.class);
//		List<Admin> adminList = adminService.getAllAdminList(Admin.class);
//		List<Admin> adminList2 = adminService.getAllAdminList(Admin.class);
		/*Rule rule = new Rule();
		rule.setRuleId("jnlmanage");
		rule.setRuleName("日志管理");
		rule.setCreateTime(new Timestamp(System.currentTimeMillis()));
		rule.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
		rule.setLevel(2);
		rule.setPicSrc("");
		rule.setParentId(1);
		rule.setRuleType("1");
		adminService.addObject(rule);*/
//		List<Rule> ruleList2 = adminService.getAllRuleList(Rule.class);
//		Admin admin = adminService.getAdminInfo("admin");
//		List<Role> roleList = adminService.getRoleList(admin.getId());
//		List<QuickMenu> quickMenuList =adminService.getQuickMenuList(admin.getId());
//		List<Object> roleIdlist = CommonUtil.getFieldList(roleList,Role.class,"","id");
//		List<Rule> ruleList = adminService.getRuleList(roleIdlist);
//		Set ruleList = new HashSet(CommonUtil.getFieldList(role_rules,Role_Rule.class,"rule",""));
//		adminService.getAllRuleList();
//		List<Rule> ruleList = adminService.getAllRuleList();
		log.fatal("-----------------------------------------------------tset-------------------------------------------------------");
//		Assert.assertSame(adminService.checkLogin("admin", "123123"),true);
//		Assert.assertSame(adminService.getAdminInfo("admin").getPassword(),"123123");
//		Assert.assertSame(adminService.getAdminInfo("admin").getPassword(),"123123");
//		for (Object rule: ruleList) {
//			log.fatal(rule);
//		}
//		Assert.assertSame(adminService.getUser2RoleList("1"),true);
//		Assert.assertSame(adminService.getUser2RoleList("2"),true);
		log.fatal("-----------------------------------------------------end-------------------------------------------------------");
	}
}
