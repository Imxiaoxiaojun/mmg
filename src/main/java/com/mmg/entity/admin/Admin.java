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
//@Proxy(lazy = false)
public class Admin extends BaseObject{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminId", length = 15, unique = true)
    private Integer adminId;
    @Column(name = "userName", length = 30,unique = true, nullable = false)
    private String userName;
    @Column(name = "passWord", length = 30, nullable = false)
    private String passWord;
    @ManyToOne
    @JoinColumn(name = "roleId", foreignKey = @ForeignKey(name = "ROLE_ID_FK"))
    private Role role;
    @Column(name = "lastLoginTime", length = 30, nullable = false)
    private Timestamp lastLoginTime;
    @Column(name = "lastLoginIp", length = 30, nullable = false)
    private String lastLoginIp;
    @Column(name = "createTime", length = 30, nullable = false)
    private Timestamp createTime;
    @Column(name = "lastUpdateTime", length = 30, nullable = false)
    private Timestamp lastUpdateTime;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return passWord;
    }

    public void setPassword(String password) {
        this.passWord = password;
    }

    public Serializable realId() {
        return adminId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", role=" + role +
                ", lastLoginTime=" + lastLoginTime +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
