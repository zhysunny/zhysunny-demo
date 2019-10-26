package com.zhysunny.pattern.other.filter;

import java.util.ArrayList;
import java.util.List;

import com.zhysunny.pattern.other.filter.impl.*;

/**
 * 过滤器模式测试类
 * @author 章云
 * @date 2019/6/18 21:54
 */
public class Main {

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person("Robert", "男", "1993-11-28"));
        persons.add(new Person("John", "男", "1994-1-2"));
        persons.add(new Person("Laura", "女", "1993-10-28"));
        persons.add(new Person("Diana", "女", "1995-11-28"));
        persons.add(new Person("Mike", "男", "1995-11-28"));
        persons.add(new Person("Bobby", "女", "1996-11-28"));
        FilterRule manFilterRule = new FilterRuleManImpl();
        FilterRule womanFilterRule = new FilterRuleWomanImpl();
        FilterRule filterRule1993 = new FilterRule1993Impl();
        FilterRule filterRule1995 = new FilterRule1995Impl();
        FilterRule andFilterRule = new FilterRuleANDImpl(manFilterRule, filterRule1993);
        FilterRule orFilterRule = new FilterRuleORImpl(womanFilterRule, filterRule1995);
        System.out.println("=======男======");
        print(manFilterRule.filter(persons));
        System.out.println("=======女======");
        print(womanFilterRule.filter(persons));
        System.out.println("=======1993======");
        print(filterRule1993.filter(persons));
        System.out.println("=======1995======");
        print(filterRule1995.filter(persons));
        System.out.println("=======AND======");
        print(andFilterRule.filter(persons));
        System.out.println("=======OR======");
        print(orFilterRule.filter(persons));
    }

    private static void print(List<Person> persons) {
        for (Person person : persons) {
            System.out.println(person);
        }
    }

}
