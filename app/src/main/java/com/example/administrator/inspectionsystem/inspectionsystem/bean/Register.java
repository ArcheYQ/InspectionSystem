package com.example.administrator.inspectionsystem.inspectionsystem.bean;

@TableName("register")
public class Register {
    /**
     * 编号
     */
    @TableColumn(field = "id", autoIncrease = true, primary = true)
    private int id;

    /**
     * 设备ID
     */
    @TableColumn(field = "device_id")
    private int deviceId;

    /**
     * 温度
     */
    @TableColumn(field = "temperature")
    private String temperature;

    /**
     * 压强
     */
    @TableColumn(field = "pressure")
    private String pressure;

    /**
     * 登记时间
     */
    @TableColumn(field = "time")
    private long time;

    /**
     * 操作人账号
     */
    @TableColumn(field = "operatorAccount")
    private String operatorAccount;

    /**
     * 操作人姓名
     */
    @TableColumn(field = "operatorName")
    private String operatorName;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 描述
     */
    @TableColumn(field = "comment")
    private String comment;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getOperatorAccount() {
        return operatorAccount;
    }

    public void setOperatorAccount(String operatorAccount) {
        this.operatorAccount = operatorAccount;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
