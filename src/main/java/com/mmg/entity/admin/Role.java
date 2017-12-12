package com.mmg.entity.admin;

import com.mmg.entity.BaseObject;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 角色 Created by yj on 2017/5/11.
 */
@Entity
// @Proxy(lazy = false)
@Table(name = "m_sys_role", uniqueConstraints = { @UniqueConstraint(columnNames = { "roleId" }) })
public class Role extends BaseObject {
	private static final long serialVersionUID = -763787974679876798L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 15, unique = true)
	private Integer id;
	@Column(name = "roleId", length = 30, unique = true, nullable = false)
	private String roleId;
	@Column(name = "roleName", length = 50)
	private String roleName;
	@Column(name = "createTime", length = 30, nullable = false)
	private Timestamp createTime;
	@Column(name = "lastUpdateTime", length = 30, nullable = false)
	private Timestamp lastUpdateTime;
	@Column(name = "roleType",columnDefinition="varchar(5) default 1")//1-管理员的角色
	private String roleType;
	

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Serializable realId() {
		return id;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleId=" + roleId + ", roleName=" + roleName + ", createTime=" + createTime
				+ ", lastUpdateTime=" + lastUpdateTime + ", roleType=" + roleType + "]";
	}

}
