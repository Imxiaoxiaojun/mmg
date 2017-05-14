package com.mmg.entity.admin;

import com.mmg.entity.BaseObject;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/** 权限菜单
 * Created by yj on 2017/5/11.
 */
@Entity
//@Proxy(lazy = false)
public class Rule extends BaseObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ruleId", length = 15, unique = true)
    private Integer ruleId;
    @Column(name = "ruleName", length = 35, unique = true,nullable = false)
    private String ruleName;
    @Column(name = "level", length = 2, nullable = false)
    private Integer level;//菜单级别
    @Column(name = "parentId", length = 5, nullable = false)
    private Integer parentId;//上级菜单id
    @Column(name = "createTime", length = 30, nullable = false)
    private Timestamp createTime;
    @Column(name = "lastUpdateTime", length = 30, nullable = false)
    private Timestamp lastUpdateTime;
    @Column(name = "picSrc", length = 50, nullable = false)
    private String picSrc;
    @Column(name = "targetUrl", length = 50)
    private String targetUrl;


    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Serializable realId() {
        return ruleId;
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

    public String getPicSrc() {
        return picSrc;
    }

    public void setPicSrc(String picSrc) {
        this.picSrc = picSrc;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "ruleId=" + ruleId +
                ", ruleName='" + ruleName + '\'' +
                ", level=" + level +
                ", parentId=" + parentId +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", picSrc='" + picSrc + '\'' +
                ", targetUrl='" + targetUrl + '\'' +
                '}';
    }
}
