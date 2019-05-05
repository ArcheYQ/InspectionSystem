package com.example.administrator.inspectionsystem.inspectionsystem.bean;

public enum Role {
    INSPECTOR("巡检员",0), ADMIN("管理员",1);

    public final String name;
    public final int value;

    Role(String name,int value){
        this.name = name;
        this.value = value;
    }

    public static Role getRole(int value){
        for (Role role : Role.values()){
            if (role.value == value){
                return role;
            }
        }
        throw new RuntimeException("找不到该角色");
    }
}
