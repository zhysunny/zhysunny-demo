package com.zhysunny.java.sgqyz7;

import com.zhysunny.io.xml.XmlReader;
import com.zhysunny.java.sgqyz7.bean.Mount;
import com.zhysunny.java.sgqyz7.bean.Person;
import com.zhysunny.java.sgqyz7.bean.Prop;
import com.zhysunny.java.sgqyz7.bean.Root;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 章云
 * @date 2020/2/3 19:59
 */
public class PropSelect {
    public static void main(String[] args) throws Exception {
        Root root = Common.getRoot();
        root.setPersons(new XmlReader("sgqyz7/tmp/person.xml").read(Root.class).getPersons());
        List<Person> persons = root.getPersons();
        List<Prop> props = root.getProps();
        persons.forEach(person -> {
            String code = person.getProp();
            props.forEach(prop -> {
                if (code.equals(prop.getCode())) {
                    prop.setAmount(prop.getAmount() - 1);
                }
            });
        });
        Collections.sort(props);
        props.forEach(prop -> {
            if (prop.getAmount() > 0) {
                System.out.println(prop.getName() + " = " + prop.getCode() + " = " + prop.getAmount());
            }
        });
        System.out.println("================================");
        props.forEach(prop -> {
            if (prop.getAmount() == 0) {
                System.out.println(prop.getName() + " = " + prop.getCode() + " = " + prop.getAmount());
            }
        });
        System.out.println("================================");
        props.forEach(prop -> {
            if (prop.getAmount() < 0) {
                System.err.println(prop.getName() + " = " + prop.getCode() + " = " + prop.getAmount());
            }
        });
    }
}
