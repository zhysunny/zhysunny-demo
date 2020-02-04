package com.zhysunny.java.sgqyz7;

import com.zhysunny.io.xml.XmlReader;
import com.zhysunny.io.xml.XmlWriter;
import com.zhysunny.java.sgqyz7.bean.Person;
import com.zhysunny.java.sgqyz7.bean.Root;
import java.util.List;

/**
 * @author 章云
 * @date 2020/2/3 20:10
 */
public class PersonInit {
    public static void main(String[] args) throws Exception {
        Root root = new Root();
        root.setPersons(new XmlReader("sgqyz7/20200202/person.xml").read(Root.class).getPersons());
        List<Person> persons = root.getPersons();
        persons.forEach(person -> {
            person.setWeapon("");
            person.setMount("");
            person.setProp("");
        });
        root.setName("武将");
        XmlWriter writer = new XmlWriter(
        "F:\\WorkingZhysunny\\zhysunny-demo\\zhysunny-java\\src\\main\\resources\\sgqyz7\\20200203\\person.xml");
        writer.write(root);
    }
}
