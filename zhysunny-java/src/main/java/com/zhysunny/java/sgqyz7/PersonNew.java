package com.zhysunny.java.sgqyz7;

import com.zhysunny.io.xml.XmlReader;
import com.zhysunny.io.xml.XmlWriter;
import com.zhysunny.java.sgqyz7.bean.Person;
import com.zhysunny.java.sgqyz7.bean.Root;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 章云
 * @date 2020/2/3 22:36
 */
public class PersonNew {
    public static void main(String[] args) throws Exception {
        List<Person> olds = new XmlReader("sgqyz7/20200202/person.xml").read(Root.class).getPersons();
        List<Person> news = new XmlReader("sgqyz7/20200203/person.xml").read(Root.class).getPersons();
        System.out.println(olds.size());
        System.out.println(news.size());
        Map<String, Person> oldMap = olds.stream().collect(Collectors.toMap(person -> person.getCode(), person -> person));
        Map<String, Person> newMap = news.stream().collect(Collectors.toMap(person -> person.getCode(), person -> person));
        List<Person> result = oldMap.entrySet().stream().map(entry -> {
            String code = entry.getKey();
            Person person = entry.getValue();
            person.setWeapon(newMap.get(code).getWeapon());
            person.setMount(newMap.get(code).getMount());
            return person;
        }).collect(Collectors.toList());
        Root root = new Root();
        root.setPersons(result);
        root.setId("PERSON");
        XmlWriter writer = new XmlWriter(
        "F:\\WorkingZhysunny\\zhysunny-demo\\zhysunny-java\\src\\main\\resources\\sgqyz7\\new\\person.xml");
        writer.write(root);
    }
}
