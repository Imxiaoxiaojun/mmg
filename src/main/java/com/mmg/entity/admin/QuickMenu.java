package com.mmg.entity.admin;

import com.mmg.entity.BaseObject;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by yj on 2017/5/14.
 */
@Entity
public class QuickMenu extends BaseObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quickMenuId", length = 15, unique = true)
    private Integer quickMenuId;
    @ManyToOne
    @JoinColumn(name = "adminId", foreignKey = @ForeignKey(name = "ADMIN_ID_FK"))
    private Admin admin;
    @ManyToOne
    @JoinColumn(name = "ruleId", foreignKey = @ForeignKey(name = "RULE_ID_FK"))
    private Rule rule;
    @Column(name = "createTime", length = 30)
    private Timestamp createTime;
    @Column(name = "lastUpdateTime", length = 30)
    private Timestamp lastUpdateTime;


    public Serializable realId() {
        return quickMenuId;
    }

    public Integer getQuickMenuId() {
        return quickMenuId;
    }

    public void setQuickMenuId(Integer quickMenuId) {
        this.quickMenuId = quickMenuId;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
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
        return "QuickMenu{" +
                "quickMenuId=" + quickMenuId +
                ", admin=" + admin +
                ", rule=" + rule +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
