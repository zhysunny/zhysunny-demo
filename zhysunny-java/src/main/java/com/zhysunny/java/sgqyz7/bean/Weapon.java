package com.zhysunny.java.sgqyz7.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 武器
 * @author 章云
 * @date 2020/2/3 19:08
 */
@XmlRootElement(name = "weapon")
@XmlAccessorType(XmlAccessType.FIELD)
public class Weapon implements Comparable<Weapon> {
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
     * 效果
     */
    @XmlAttribute(name = "effect")
    private String effect;
    /**
     * 武器类型 1：剑类，2：扇类，3：刀类，4：枪类，5：弓类
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

    public String getEffect() {
        return effect.trim();
    }

    public void setEffect(String effect) {
        this.effect = effect.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Weapon{" +
        "name='" + name + '\'' +
        ", code='" + code + '\'' +
        ", amount=" + amount +
        ", level=" + level +
        ", physical=" + physical +
        ", skill=" + skill +
        ", military=" + military +
        ", intelligence=" + intelligence +
        ", speed=" + speed +
        ", effect='" + getEffect() + '\'' +
        ", type='" + type + '\'' +
        '}';
    }

    @Override
    public int compareTo(Weapon weapon) {
        return weapon.getIntelligence() - this.intelligence;
    }
}
