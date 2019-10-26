package com.zhysunny.pattern.other.filter.impl;

import com.zhysunny.pattern.other.filter.FilterRule;
import com.zhysunny.pattern.other.filter.Person;

import java.util.List;

/**
 * 过滤规则且条件
 * @author 章云
 * @date 2019/5/13 17:15
 */
public class FilterRuleANDImpl implements FilterRule {

    private FilterRule[] arrays;

    public FilterRuleANDImpl(FilterRule... arrays) {
        this.arrays = arrays;
    }

    @Override
    public List<Person> filter(List<Person> persons) {
        List<Person> result = persons;
        for (FilterRule filterRule : arrays) {
            result = filterRule.filter(result);
        }
        return result;
    }

}
