package com.mmg.entity.admin;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/** 角色
 * Created by yj on 2017/5/11.
 */
@Entity
//@Proxy(lazy = false)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId", length = 15, unique = true)
    private Integer roleId;
    @Column(name = "roleName", length = 15, unique = true,nullable = false)
    private String roleName;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
