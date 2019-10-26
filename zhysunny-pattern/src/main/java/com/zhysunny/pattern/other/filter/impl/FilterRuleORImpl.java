package com.zhysunny.pattern.other.filter.impl;

import com.zhysunny.pattern.other.filter.FilterRule;
import com.zhysunny.pattern.other.filter.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 过滤规则或条件
 * @author 章云
 * @date 2019/5/13 17:10
 */
public class FilterRuleORImpl implements FilterRule {

    private FilterRule[] arrays;

    public FilterRuleORImpl(FilterRule... arrays) {
        this.arrays = arrays;
    }

    @Override
    public List<Person> filter(List<Person> persons) {
        List<Person> result = new ArrayList<Person>();
        for (FilterRule filterRule : arrays) {
            result.addAll(filterRule.filter(persons));
        }
        // 去重
        return result.stream().distinct().collect(Collectors.toList());
    }

}
