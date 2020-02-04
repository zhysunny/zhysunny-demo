package com.zhysunny.java.sgqyz7.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 消耗品
 * @author 章云
 * @date 2020/2/3 19:08
 */
@XmlRootElement(name = "consumable")
@XmlAccessorType(XmlAccessType.FIELD)
public class Consumable {
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
     * 效果
     */
    @XmlAttribute(name = "effect")
    private String effect;
    /**
     * 消耗品类型 1：兵符，2：阵法书，3：属性，4：锻造书
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
        return "Consumable{" +
        "name='" + name + '\'' +
        ", code='" + code + '\'' +
        ", effect='" + getEffect() + '\'' +
        ", type='" + type + '\'' +
        '}';
    }
}
