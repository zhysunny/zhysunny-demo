package com.zhysunny.java.sgqyz7.bean;

import com.zhysunny.io.xml.XmlBean;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * xml root
 * @author 章云
 * @date 2020/2/3 19:02
 */
@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class Root implements XmlBean {

    @XmlAttribute(name = "id")
    private String id;
    @XmlAttribute(name = "name")
    private String name;
    @XmlElement(name = "city")
    private List<City> citys;
    @XmlElement(name = "consumable")
    private List<Consumable> consumables;
    @XmlElement(name = "mount")
    private List<Mount> mounts;
    @XmlElement(name = "person")
    private List<Person> persons;
    @XmlElement(name = "prop")
    private List<Prop> props;
    @XmlElement(name = "weapon")
    private List<Weapon> weapons;

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

    public List<City> getCitys() {
        return citys;
    }

    public void setCitys(List<City> citys) {
        this.citys = citys;
    }

    public List<Consumable> getConsumables() {
        return consumables;
    }

    public void setConsumables(List<Consumable> consumables) {
        this.consumables = consumables;
    }

    public List<Mount> getMounts() {
        return mounts;
    }

    public void setMounts(List<Mount> mounts) {
        this.mounts = mounts;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Prop> getProps() {
        return props;
    }

    public void setProps(List<Prop> props) {
        this.props = props;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }
}
