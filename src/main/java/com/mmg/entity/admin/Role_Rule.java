package com.mmg.entity.admin;

import com.mmg.entity.BaseObject;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by yj on 2017/5/11.
 */
@Entity
// @Proxy(lazy = false)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "roleId", "ruleId" }) })
public class Role_Rule extends BaseObject {
	private static final long serialVersionUID = -4567041876497276723L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 15, nullable = false, unique = true)
	private Integer id;
	@ManyToOne()
	@JoinColumn(name = "roleId")
	private Role role;
	@ManyToOne()
	@JoinColumn(name = "ruleId")
	private Rule rule;
	@Column(name = "lastUpdateTime", length = 30, nullable = false)
	private Timestamp lastUpdateTime;
	@Column(name = "createTime", length = 30, nullable = false)
	private Timestamp createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Serializable realId() {
		return id;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	@Override
	public String toString() {
		return "Role_Rule [id=" + id + ", role=" + role + ", rule=" + rule + ", lastUpdateTime=" + lastUpdateTime
				+ ", createTime=" + createTime + "]";
	}

}
