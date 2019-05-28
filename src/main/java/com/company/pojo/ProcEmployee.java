package com.company.pojo;

import java.util.Date;

public class ProcEmployee {

    public ProcEmployee() {
    }

    public ProcEmployee(String realName, int total, Date exeDate) {
        this.realName = realName;
        this.total = total;
        this.exeDate = exeDate;
    }

    private String realName;
    private int total;
    private Date exeDate;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getExeDate() {
        return exeDate;
    }

    public void setExeDate(Date exeDate) {
        this.exeDate = exeDate;
    }
}
