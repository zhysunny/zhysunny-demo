package com.zhysunny.java.sgqyz7;

import com.zhysunny.io.xml.XmlReader;
import com.zhysunny.java.sgqyz7.bean.Root;

/**
 * @author 章云
 * @date 2020/2/3 18:59
 */
public class Common {
    public static void main(String[] args) throws Exception {
        Root root = getRoot();
        System.out.println(root.getCitys().get(0));
        System.out.println(root.getConsumables().get(0));
        System.out.println(root.getMounts().get(0));
        System.out.println(root.getPersons().get(0));
        System.out.println(root.getProps().get(0));
        System.out.println(root.getWeapons().get(0));
    }

    public static Root getRoot() throws Exception {
        Root root = new Root();
        root.setCitys(new XmlReader("sgqyz7/20200202/city.xml").read(Root.class).getCitys());
        root.setConsumables(new XmlReader("sgqyz7/20200202/consumable.xml").read(Root.class).getConsumables());
        root.setMounts(new XmlReader("sgqyz7/20200202/mount.xml").read(Root.class).getMounts());
        root.setPersons(new XmlReader("sgqyz7/20200202/person.xml").read(Root.class).getPersons());
        root.setProps(new XmlReader("sgqyz7/20200202/prop.xml").read(Root.class).getProps());
        root.setWeapons(new XmlReader("sgqyz7/20200202/weapon.xml").read(Root.class).getWeapons());
        return root;
    }

}
