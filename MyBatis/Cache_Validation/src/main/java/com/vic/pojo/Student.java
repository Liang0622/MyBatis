package com.vic.pojo;

import java.util.Set;

public class Student {
    private Integer sId;
    private String sName;
    private Integer age;

    public Student() {
    }

    public Student(Integer sId, String sName, Integer age) {
        this.sId = sId;
        this.sName = sName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sId=" + sId +
                ", sName='" + sName + '\'' +
                ", age=" + age +
                '}';
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

}
