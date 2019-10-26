package com.zhysunny.pattern.other.filter;

import java.util.Objects;

/**
 * 人员信息(过滤集合的元素)
 * @author 章云
 * @date 2019/5/13 17:11
 */
public class Person {
    /***
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String gender;
    /**
     * 生日
     */
    private String birthday;

    public Person(String name, String gender, String birthday) {
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(gender, person.gender) &&
                Objects.equals(birthday, person.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, birthday);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
