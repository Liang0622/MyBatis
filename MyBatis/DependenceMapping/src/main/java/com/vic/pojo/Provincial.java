package com.vic.pojo;

/**
 * 省份实体类
 */
public class Provincial {
    private Integer pId;//省会编号
    private String pName;//省会名称

    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    /**
     * 无参构造
     */
    public Provincial() {
    }

    /**
     * 构造函数
     * @param pId
     * @param pName
     */
    public Provincial(Integer pId, String pName) {
        this.pId = pId;
        this.pName = pName;
    }

    @Override
    public String toString() {
        return "Provincial{" +
                "pId=" + pId +
                ", pName='" + pName + '\'' +
                '}';
    }
}
