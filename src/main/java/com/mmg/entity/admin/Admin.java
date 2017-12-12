package com.mmg.entity.admin;
/**
 * 管理员
 * Created by yj on 2017/5/11.
 */

import com.mmg.entity.BaseObject;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
// @Proxy(lazy = false)
@Table(name = "m_sys_admin", uniqueConstraints = { @UniqueConstraint(columnNames = { "adminId" }) })
public class Admin extends BaseObject {
	private static final long serialVersionUID = -4567041033458776723L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 15, unique = true)
	private Integer id;
	@Column(name = "adminId", length = 30, unique = true, nullable = false)
	private String adminId;
	@Column(name = "adminName", length = 50)
	private String adminName;
	@Column(name = "passWord", length = 50, nullable = false)
	private String passWord;
	@Column(name = "roleId", length = 500, nullable = false)
	private String roleId;
	@Column(name = "lastLoginTime", length = 30, nullable = false)
	private Timestamp lastLoginTime;
	@Column(name = "lastLoginIp", length = 30, nullable = false)
	private String lastLoginIp;
	@Column(name = "createTime", length = 30, nullable = false)
	private Timestamp createTime;
	@Column(name = "lastUpdateTime", length = 30, nullable = false)
	private Timestamp lastUpdateTime;

	public String getPassword() {
		return passWord;
	}

	public void setPassword(String password) {
		this.passWord = password;
	}

	public Serializable realId() {
		return id;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", adminId=" + adminId + ", adminName=" + adminName + ", passWord=" + passWord
				+ ", roleId=" + roleId + ", lastLoginTime=" + lastLoginTime + ", lastLoginIp=" + lastLoginIp
				+ ", createTime=" + createTime + ", lastUpdateTime=" + lastUpdateTime + "]";
	}

}
