package com.mmg.entity.admin;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/** 权限菜单
 * Created by yj on 2017/5/11.
 */
@Entity
//@Proxy(lazy = false)
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ruleId", length = 15, unique = true)
    private Integer ruleId;
    @Column(name = "ruleName", length = 35, unique = true,nullable = false)
    private String ruleName;
    @Column(name = "level", length = 2, nullable = false)
    private Integer level;//菜单级别


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
}
