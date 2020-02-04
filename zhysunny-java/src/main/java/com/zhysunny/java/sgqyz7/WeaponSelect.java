package com.zhysunny.java.sgqyz7;

import com.zhysunny.io.xml.XmlReader;
import com.zhysunny.java.sgqyz7.bean.Person;
import com.zhysunny.java.sgqyz7.bean.Root;
import com.zhysunny.java.sgqyz7.bean.Weapon;
import java.util.Collections;
import java.util.List;

/**
 * @author 章云
 * @date 2020/2/3 19:59
 */
public class WeaponSelect {
    public static void main(String[] args) throws Exception {
        Root root = Common.getRoot();
        root.setPersons(new XmlReader("sgqyz7/20200203/person.xml").read(Root.class).getPersons());
        List<Person> persons = root.getPersons();
        List<Weapon> weapons = root.getWeapons();
        persons.forEach(person -> {
            String code = person.getWeapon();
            weapons.forEach(weapon -> {
                if (code.equals(weapon.getCode())) {
                    weapon.setAmount(weapon.getAmount() - 1);
                }
            });
        });
        Collections.sort(weapons);
        weapons.forEach(weapon -> {
            if (weapon.getAmount() > 0) {
                System.out.println(weapon.getName() + " = " + weapon.getCode() + " = " + weapon.getAmount());
            }
        });
        System.out.println("================================");
        weapons.forEach(weapon -> {
            if (weapon.getAmount() == 0) {
                System.out.println(weapon.getName() + " = " + weapon.getCode() + " = " + weapon.getAmount());
            }
        });
        System.out.println("================================");
        weapons.forEach(weapon -> {
            if (weapon.getAmount() < 0) {
                System.err.println(weapon.getName() + " = " + weapon.getCode() + " = " + weapon.getAmount());
            }
        });

    }
}
