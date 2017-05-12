package com.mmg.entity.admin;
/** 管理员
 * Created by yj on 2017/5/11.
 */
import com.mmg.entity.BaseObject;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Proxy(lazy = false)
public class Admin extends BaseObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminId", length = 15, unique = true)
    private Integer adminId;
    @Column(name = "userName", length = 30, nullable = false)
    private String userName;
    @Column(name = "passWord", length = 30, nullable = false)
    private String passWord;
    @ManyToOne
    @JoinColumn(name = "roleId",foreignKey = @ForeignKey(name = "ROLE_ID_FK"))
    private Role role;


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
}
