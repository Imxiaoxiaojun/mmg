package com.mmg.entity.admin;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * Created by yj on 2017/5/11.
 */
@Entity
@Proxy(lazy = false)
public class Role_Rule {
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "roleId",foreignKey = @ForeignKey(name = "ROLE_ID_FK"))
    private Role role;

    @ManyToOne
    @JoinColumn(name = "ruleId",foreignKey = @ForeignKey(name = "RULE_ID_FK"))
    private Rule rule;

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
}
