package com.zhysunny.pattern.create.builder;

import java.util.List;
import java.util.Map;

/**
 * 建造类接口，拆分每个输出类型的步骤
 * @author 章云
 * @date 2019/7/15 11:29
 */
public interface Builder {

    /**
     * 文件名
     * @param fileName
     */
    void setFileName(String fileName);

    /**
     * 标题
     * @param title
     */
    void setTitle(String title);

    /**
     * 数据
     * @param dataList
     */
    void setData(List<Map<String, String>> dataList);

}
