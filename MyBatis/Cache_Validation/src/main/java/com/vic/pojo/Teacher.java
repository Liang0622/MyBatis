package com.vic.pojo;

public class Teacher {
    private Integer id;
    private String tName;
    private Integer tId;

    public Teacher() {
    }

    public Teacher(Integer id, String tName, Integer tId) {
        this.id = id;
        this.tName = tName;
        this.tId = tId;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", tName='" + tName + '\'' +
                ", tId=" + tId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }
}
