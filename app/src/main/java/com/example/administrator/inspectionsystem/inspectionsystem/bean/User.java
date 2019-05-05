package com.example.administrator.inspectionsystem.inspectionsystem.bean;

public class User {

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
    private Role role;

    public User(){
        this.role = Role.INSPECTOR;
    }


    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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
