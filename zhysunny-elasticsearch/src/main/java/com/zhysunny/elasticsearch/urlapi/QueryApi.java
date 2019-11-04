package com.zhysunny.elasticsearch.urlapi;

import com.zhysunny.elasticsearch.client.HttpConnection;

public class QueryApi {

    public static void main(String[] args) {
        String esUrl = "http://10.45.154.217:9200/";
        // name中包含scala或java
        //        String json = "{\"query\": {\"match\": {\"name\": \"scala java\" }}}";
        //        System.out.println(new HttpConnection(esUrl + "search_test/_search/?pretty", "GET").send(json));

        // match_phrase匹配短语，把java scala看做一个整体
        //        String json = "{\"query\": {\"match_phrase\": {\"name\": \"java scala\" }}}";
        //        System.out.println(new HttpConnection(esUrl + "search_test/_search/?pretty", "GET").send(json));

        // bool查询
        // match条件和上面一样，must表示两个match条件是且关系
        //        String json = "{\"query\": {\"bool\": {\"must\": [{\"match\": {\"name\": \"scala java\" }},{\"match\": {\"name\": \"hadoop\" }}] }}}";
        //        System.out.println(new HttpConnection(esUrl + "search_test/_search/?pretty", "GET").send(json));

        // should表示两个match条件是且关系
        //        String json = "{\"query\": {\"bool\": {\"should\": [{\"match\": {\"name\": \"scala java\" }},{\"match\": {\"name\": \"hadoop\" }}] }}}";
        //        System.out.println(new HttpConnection(esUrl + "search_test/_search/?pretty", "GET").send(json));

        // must_not表示两个match条件都不能满足，相当于对should取非
        //        String json = "{\"query\": {\"bool\": {\"must_not\": [{\"match\": {\"name\": \"scala java\" }},{\"match\": {\"name\": \"hadoop\" }}] }}}";
        //        System.out.println(new HttpConnection(esUrl + "search_test/_search/?pretty", "GET").send(json));

        // 组合
        //        String json = "{\"query\": {\"bool\": {\"must\": [{\"match\": {\"age\": 97 }}],\"should\": [{\"match\": {\"name\": \"java\" }}],\"must_not\": [{\"match\": {\"name\": \"hadoop\" }}]}}}";
        //        System.out.println(new HttpConnection(esUrl + "search_test/_search/?pretty", "GET").send(json));

        // 过滤
        //        String json = "{\"query\": {\"bool\": {\"must\": [{\"match_all\": {}}],\"filter\": {\"range\": {\"age\": {\"lte\":99,\"gt\":95}}}}}}";
        //        System.out.println(new HttpConnection(esUrl + "search_test/_search/?pretty", "GET").send(json));

        // 聚合
        // select name,avg(age) avg_age from table group by name order by avg_age desc
        //        String json = "{\"size\":0,\"aggs\":{\"group_by_name\":{\"terms\":{\"field\":\"name.keyword\",\"order\":{\"average_age\":\"desc\"}},\"aggs\":{\"average_age\":{\"avg\":{\"field\":\"age\"}}}}}}";
        //        System.out.println(new HttpConnection(esUrl + "search_test/_search/?pretty", "GET").send(json));

        //首先按照age范围分组，再对每个范围求平均数
        //        String json = "{\"size\":0,\"aggs\":{\"group_by_age\":{\"range\":{\"field\":\"age\",\"ranges\":[{\"from\":0,\"to\":30},{\"from\":30,\"to\":60},{\"from\":60,\"to\":90},{\"from\":90,\"to\":100}]},\"aggs\":{\"group_by_age\":{\"terms\":{\"field\":\"age.keyword\"},\"aggs\":{\"average_age\":{\"avg\":{\"field\":\"age\"}}}}}}}}";
        //        System.out.println(new HttpConnection(esUrl + "search_test/_search/?pretty", "GET").send(json));

        String json = "{"
        + "  \"query\": {"
        + "    \"bool\": {"
        + "      \"must\": {"
        + "        \"range\": {"
        + "          \"OpTime\": {"
        + "            \"gt\": \"2019-09-30 09:26:48\","
        + "            \"lt\": \"2019-10-30 09:30:48\""
        + "          }"
        + "        }"
        + "      },"
        + "      \"must\": {"
        + "        \"range\": {"
        + "          \"OpTime\": {"
        + "            \"gt\": \"2019-09-30 09:26:48\","
        + "            \"lt\": \"2019-09-30 09:30:48\""
        + "          }"
        + "        }"
        + "      }"
        + "    }"
        + "  }"
        + "}";

        System.out.println(new HttpConnection(esUrl + "reid_fss_data_n_project_v1_2/_search/?pretty", "GET").send(json));
    }

}
