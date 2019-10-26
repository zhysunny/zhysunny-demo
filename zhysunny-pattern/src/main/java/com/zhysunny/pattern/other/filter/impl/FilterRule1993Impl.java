package com.zhysunny.pattern.other.filter.impl;

import com.zhysunny.pattern.other.filter.FilterRule;
import com.zhysunny.pattern.other.filter.Person;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 过滤生日为1993年的元素集合
 * @author 章云
 * @date 2019/5/13 17:09
 */
public class FilterRule1993Impl implements FilterRule {

    @Override
    public List<Person> filter(List<Person> persons) {
        return persons.stream().filter(person -> person.getBirthday().startsWith("1993")).collect(Collectors.toList());
    }

}
