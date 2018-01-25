package com.vic.pojo;

/**
 * 用户实体类
 */
public class User {
    private Integer  id;//主键
    private String  userName;//用户名
    private String  password;//密码

    /**
     * 重写toString方法
     * @return
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(Integer id, String userName, String password) {

        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    /**
     * 构造函数
     * @param userName  用户名
     * @param password  用户密码
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * 无参构造
     */
    public User() {

    }
}
