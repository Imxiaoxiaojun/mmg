package com.mmg.entity.admin;

import com.mmg.entity.BaseObject;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by yj on 2017/5/11.
 */
@Entity
//@Proxy(lazy = false)
public class Role_Rule extends BaseObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "roleId", foreignKey = @ForeignKey(name = "ROLE_ID_FK"))
    private Role role;

    @ManyToOne
    @JoinColumn(name = "ruleId", foreignKey = @ForeignKey(name = "RULE_ID_FK"))
    private Rule rule;

    @Column(name = "lastUpdateTime", length = 30, nullable = false)
    private Timestamp lastUpdateTime;
    @Column(name = "createTime", length = 30, nullable = false)
    private Timestamp createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public Serializable realId() {
        return id;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Role_Rule{" +
                "id=" + id +
                ", role=" + role +
                ", rule=" + rule +
                ", lastUpdateTime=" + lastUpdateTime +
                ", createTime=" + createTime +
                '}';
    }
}
