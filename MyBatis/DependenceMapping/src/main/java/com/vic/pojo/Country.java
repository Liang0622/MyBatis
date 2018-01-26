package com.vic.pojo;

import java.util.Set;

/**
 * 国家实体类
 */
public class Country {
    private Integer cId;//国家编号
    private String cName;//国家名称
    private Set<Provincial> provincials;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Set<Provincial> getProvincials() {
        return provincials;
    }

    public void setProvincials(Set<Provincial> provincials) {
        this.provincials = provincials;
    }

    /**
     * 无参构造
     */
    public Country() {
    }

    /**
     * 构造函数
     * @param cId
     * @param cName
     * @param provincials
     */
    public Country(Integer cId, String cName, Set<Provincial> provincials) {
        this.cId = cId;
        this.cName = cName;
        this.provincials = provincials;
    }
}
