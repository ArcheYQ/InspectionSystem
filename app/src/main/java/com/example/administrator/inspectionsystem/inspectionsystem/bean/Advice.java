package com.example.administrator.inspectionsystem.inspectionsystem.bean;

public class Advice {

    /**
     * 编号
     */
    private String number;
    /**
     * 主键ID
     */
    private String id;
    /**
     * 设备名称
     */
    private String name;
    /**
     * 设备位置
     */
    private String location;
    /**
     * 添加时间
     */
    private long addTime;
    /**
     * 添加账号
     */
    private String admAccount;
    /**
     * 是否公开
     */
    private int isPublic;
    public Advice(){
        isPublic = 1;
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public String getAdmAccount() {
        return admAccount;
    }

    public void setAdmAccount(String admAccount) {
        this.admAccount = admAccount;
    }

    public boolean isPublic() {
        return isPublic == 1 ? true :false ;
    }
    public int getIsPublic() {
        return isPublic;
    }

    public void setPublic(int isPublic) {
        this.isPublic = isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic? 1 : 0 ;
    }


}
