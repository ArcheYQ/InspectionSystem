package com.example.administrator.inspectionsystem.inspectionsystem.bean;

public class User {
    public static final int ROLE_ADMINISTRATOR = 1;
    public static final int ROLE_INSPECTOR = 0;
    /**
     * 密码
     */
    private String password;
    /**
     * 账号
     */
    private String account;
    /**
     * 姓名
     */
    private String name;
    /**
     * 角色
     */
    private int role;

    public User(){
        this.role = ROLE_INSPECTOR;
    }


    public String getPassword() {
        return password;
    }
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
