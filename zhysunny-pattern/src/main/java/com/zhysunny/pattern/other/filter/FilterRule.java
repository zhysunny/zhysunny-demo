package com.zhysunny.pattern.other.filter;

import java.util.List;

/**
 * 过滤规则接口
 * @author 章云
 * @date 2019/5/13 17:04
 */
public interface FilterRule {

    /**
     * 过滤规则方法
     * @param persons 需要过滤的集合
     * @return 返回符合过滤规则的集合
     */
    List<Person> filter(List<Person> persons);

}
