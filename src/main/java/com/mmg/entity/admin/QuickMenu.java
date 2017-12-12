package com.mmg.entity.admin;

import com.mmg.entity.BaseObject;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by yj on 2017/5/14.
 */
@Entity
@Table(name = "m_sys_quickmenu", uniqueConstraints = { @UniqueConstraint(columnNames = { "adminId", "ruleId" }) })
public class QuickMenu extends BaseObject {
	private static final long serialVersionUID = -1467987974346546L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 15, unique = true)
	private Integer id;
	@ManyToOne()
	@JoinColumn(name = "adminId")
	private Admin admin;
	@ManyToOne()
	@JoinColumn(name = "ruleId")
	private Rule rule;
	@Column(name = "createTime", length = 30)
	private Timestamp createTime;
	@Column(name = "lastUpdateTime", length = 30)
	private Timestamp lastUpdateTime;

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

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	@Override
	public String toString() {
		return "QuickMenu [id=" + id + ", admin=" + admin + ", rule=" + rule + ", createTime=" + createTime
				+ ", lastUpdateTime=" + lastUpdateTime + "]";
	}

	@Override
	public Serializable realId() {
		return id;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

}
