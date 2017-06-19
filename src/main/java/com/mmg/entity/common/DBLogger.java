package com.mmg.entity.common;

import com.mmg.entity.BaseObject;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by yj on 2017/6/17.
 */
@Entity
public class DBLogger extends BaseObject{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 15, unique = true)
    private Long id;
    @Column(name = "operationuser", length = 30, nullable = false)
    private String operationuser;
    @Column(name = "ipAddress", length = 30, nullable = false)
    private String ipAddress;
    @Column(name = "macAddress", length = 30,  nullable = false)
    private String macAddress;
    @Column(name = "operationTime", length = 30,  nullable = false)
    private Timestamp operationTime;
    @Column(name = "description", length = 50,  nullable = false)
    private String description;
    @Column(name = "content", length = 100,  nullable = false)
    private String content;

    @Override
    public Serializable realId() {
        return id;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperationuser() {
        return this.operationuser;
    }

    public void setOperationuser(String operationuser) {
        this.operationuser = operationuser;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Timestamp getOperationTime() {
        return this.operationTime;
    }

    public void setOperationTime(Timestamp operationTime) {
        this.operationTime = operationTime;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
