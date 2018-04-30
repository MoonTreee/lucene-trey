package com.njust.lucene.domain;

import java.util.Date;

/**
 * 员工的实体
 * Created by Tree on 2017/3/2.
 */
public class Employee {
    private Integer eid;
    private String ename;
    private String sex;
    private Date birthday;
    private Date joinDate;
    private String eno;

    //员工所属的部门
    private  Department department;

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setJoinDate(Date jionDate) {
        this.joinDate = jionDate;
    }

    public void setEno(String eno) {
        this.eno = eno;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getEid() {
        return eid;
    }

    public String getEname() {
        return ename;
    }

    public String getSex() {
        return sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public String getEno() {
        return eno;
    }

    public Department getDepartment() {
        return department;
    }
}
