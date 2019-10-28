package com.zhysunny.java.jmx;

import java.time.LocalTime;
import java.util.Date;

/**
 * @author 章云
 * @date 2019/10/28 11:14
 */
public interface UserMBean {

    public void setId(Integer id);

    public Integer getId();

    public void setName(String name);

    public String getName();

    public void setBirthDate(Date date);

    public Date getBirthDate();

    public void setTime(LocalTime time);

    public LocalTime getTime();

    public void printUserInfo();

    public Date currentDate();

    public void setTest(Test test);

    public Test getTest();

}

