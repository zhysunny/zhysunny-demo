package com.zhysunny.java.sgqyz7.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 武将
 * @author 章云
 * @date 2020/2/3 19:08
 */
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    /**
     * 名称
     */
    @XmlAttribute(name = "name")
    protected String name;
    /**
     * 编码
     */
    @XmlAttribute(name = "code")
    protected String code;
    /**
     * 性别 0：女，1：男
     */
    @XmlAttribute(name = "sex")
    private String sex;
    /**
     * 武力
     */
    @XmlAttribute(name = "military")
    private int military;
    /**
     * 智力
     */
    @XmlAttribute(name = "intelligence")
    private int intelligence;
    /**
     * 坐骑
     */
    @XmlAttribute(name = "mount")
    private String mount;
    /**
     * 武器
     */
    @XmlAttribute(name = "weapon")
    private String weapon;
    /**
     * 道具
     */
    @XmlAttribute(name = "prop")
    private String prop;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getMilitary() {
        return military;
    }

    public void setMilitary(int military) {
        this.military = military;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public String getMount() {
        return mount;
    }

    public void setMount(String mount) {
        this.mount = mount;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    @Override
    public String toString() {
        return "Person{" +
        "name='" + name + '\'' +
        ", code='" + code + '\'' +
        ", sex='" + sex + '\'' +
        ", military=" + military +
        ", intelligence=" + intelligence +
        ", mount='" + mount + '\'' +
        ", weapon='" + weapon + '\'' +
        ", prop='" + prop + '\'' +
        '}';
    }
}
