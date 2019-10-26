package com.zhysunny.pattern.other.filter.impl;

import com.zhysunny.pattern.other.filter.FilterRule;
import com.zhysunny.pattern.other.filter.Person;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 过滤性别为男的元素集合
 * @author 章云
 * @date 2019/5/13 17:06
 */
public class FilterRuleManImpl implements FilterRule {

    @Override
    public List<Person> filter(List<Person> persons) {
        return persons.stream().filter(person -> "男".equals(person.getGender())).collect(Collectors.toList());
    }

}
