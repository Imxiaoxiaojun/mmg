package com.mmg.entity.admin;

import com.mmg.entity.BaseObject;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Proxy(lazy = false)
public class Admin extends BaseObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 15, name = "ID", unique = true)
    private Integer id;
    @Column(length = 30, nullable = false, name = "USERNAME")
    private String userName;
    @Column(length = 30, nullable = false, name = "PASSWORD")
    private String passWord;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        return id;
    }
}
