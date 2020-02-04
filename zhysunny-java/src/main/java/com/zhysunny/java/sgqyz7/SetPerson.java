package com.zhysunny.java.sgqyz7;

import com.zhysunny.io.xml.XmlReader;
import com.zhysunny.io.xml.XmlWriter;
import com.zhysunny.java.sgqyz7.bean.Person;
import com.zhysunny.java.sgqyz7.bean.Root;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 章云
 * @date 2020/2/4 10:45
 */
public class SetPerson {

    public static void main(String[] args) throws Exception {
        Root root = new Root();
        root.setPersons(new XmlReader("sgqyz7/tmp/person.xml").read(Root.class).getPersons());
        List<Person> persons = root.getPersons();
        String prop = "734";
        final AtomicInteger count = new AtomicInteger(0);
        persons.forEach(person -> {
            if (!"".equals(person.getWeapon())) {
                if (person.getIntelligence() >= 0) {
                    if (person.getMilitary() >= 0) {
                        if ("".equals(person.getProp())) {
                            System.out.println(person.getName());
                            person.setProp(prop);
                            count.incrementAndGet();
                        }
                    }
                }
            }
        });
        System.out.println(count.get());
        XmlWriter writer = new XmlWriter(
        "F:\\WorkingZhysunny\\zhysunny-demo\\zhysunny-java\\src\\main\\resources\\sgqyz7\\tmp\\person.xml");
        writer.write(root);
    }

}
