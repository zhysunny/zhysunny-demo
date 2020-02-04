package com.zhysunny.java.sgqyz7.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 道具
 * @author 章云
 * @date 2020/2/3 19:08
 */
@XmlRootElement(name = "prop")
@XmlAccessorType(XmlAccessType.FIELD)
public class Prop implements Comparable<Prop> {
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
     * 数量
     */
    @XmlAttribute(name = "amount")
    private int amount;
    /**
     * 等级要求
     */
    @XmlAttribute(name = "level")
    private int level;
    /**
     * 体力
     */
    @XmlAttribute(name = "physical")
    private int physical;
    /**
     * 技力
     */
    @XmlAttribute(name = "skill")
    private int skill;
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
     * 速度
     */
    @XmlAttribute(name = "speed")
    private int speed;
    /**
     * 武将技
     */
    @XmlAttribute(name = "warriorSkills")
    private String warriorSkills;
    /**
     * 军师技
     */
    @XmlAttribute(name = "wiseSkills")
    private String wiseSkills;
    /**
     * 特性
     */
    @XmlAttribute(name = "speciality")
    private String speciality;
    /**
     * 道具类型 1：属性，2：武将技，3：军师技，4：特性，5：材料
     */
    @XmlAttribute(name = "type")
    private String type;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPhysical() {
        return physical;
    }

    public void setPhysical(int physical) {
        this.physical = physical;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getWarriorSkills() {
        return warriorSkills;
    }

    public void setWarriorSkills(String warriorSkills) {
        this.warriorSkills = warriorSkills;
    }

    public String getWiseSkills() {
        return wiseSkills;
    }

    public void setWiseSkills(String wiseSkills) {
        this.wiseSkills = wiseSkills;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Prop{" +
        "name='" + name + '\'' +
        ", code='" + code + '\'' +
        ", amount=" + amount +
        ", level=" + level +
        ", physical=" + physical +
        ", skill=" + skill +
        ", military=" + military +
        ", intelligence=" + intelligence +
        ", speed=" + speed +
        ", warriorSkills='" + warriorSkills + '\'' +
        ", wiseSkills='" + wiseSkills + '\'' +
        ", speciality='" + speciality + '\'' +
        ", type='" + type + '\'' +
        '}';
    }

    @Override
    public int compareTo(Prop prop) {
        return prop.getIntelligence() - this.intelligence;
    }
}
