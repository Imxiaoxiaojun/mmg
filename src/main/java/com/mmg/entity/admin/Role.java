package com.mmg.entity.admin;

import com.mmg.entity.BaseObject;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 角色
 * Created by yj on 2017/5/11.
 */
@Entity
//@Proxy(lazy = false)
public class Role extends BaseObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId", length = 15, unique = true)
    private Integer roleId;
    @Column(name = "roleName", length = 15, unique = true, nullable = false)
    private String roleName;
    @Column(name = "createTime", length = 30, nullable = false)
    private Timestamp createTime;
    @Column(name = "lastUpdateTime", length = 30, nullable = false)
    private Timestamp lastUpdateTime;

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

    public Serializable realId() {
        return roleId;
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

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
