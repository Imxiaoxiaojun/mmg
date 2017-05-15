package com.mmg.entity.admin;

import com.mmg.entity.BaseObject;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "adminId", "roleId" }) })
public class User_Role extends BaseObject {
	private static final long serialVersionUID = -465487654987657642L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 15, unique = true)
	private Integer id;
	@ManyToOne()
	@JoinColumn(name = "adminId")
	private Admin admin;
	@ManyToOne()
	@JoinColumn(name = "roleId")
	private Role role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public Serializable realId() {
		return id;
	}

	@Override
	public String toString() {
		return "User_Role [id=" + id + ", admin=" + admin + ", role=" + role + "]";
	}

}
