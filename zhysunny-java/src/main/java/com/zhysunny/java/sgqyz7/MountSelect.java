package com.zhysunny.java.sgqyz7;

import com.zhysunny.io.xml.XmlReader;
import com.zhysunny.java.sgqyz7.bean.Mount;
import com.zhysunny.java.sgqyz7.bean.Person;
import com.zhysunny.java.sgqyz7.bean.Root;
import java.util.Collections;
import java.util.List;

/**
 * @author 章云
 * @date 2020/2/3 19:59
 */
public class MountSelect {
    public static void main(String[] args) throws Exception {
        Root root = Common.getRoot();
        root.setPersons(new XmlReader("sgqyz7/tmp/person.xml").read(Root.class).getPersons());
        List<Person> persons = root.getPersons();
        List<Mount> mounts = root.getMounts();
        persons.forEach(person -> {
            String code = person.getMount();
            mounts.forEach(mount -> {
                if (code.equals(mount.getCode())) {
                    mount.setAmount(mount.getAmount() - 1);
                }
            });
        });
        Collections.sort(mounts);
        mounts.forEach(mount -> {
            if (mount.getAmount() > 0) {
                System.out.println(mount.getName() + " = " + mount.getCode() + " = " + mount.getAmount());
            }
        });
        System.out.println("================================");
        mounts.forEach(mount -> {
            if (mount.getAmount() == 0) {
                System.out.println(mount.getName() + " = " + mount.getCode() + " = " + mount.getAmount());
            }
        });
        System.out.println("================================");
        mounts.forEach(mount -> {
            if (mount.getAmount() < 0) {
                System.err.println(mount.getName() + " = " + mount.getCode() + " = " + mount.getAmount());
            }
        });

    }
}
