package com.zhysunny.elasticsearch.javaapi;

import com.zhysunny.elasticsearch.client.EsUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

public class Search {
    public static void main(String[] args) throws Exception {
        TransportClient client = EsUtils.getEsTransportClient("lv217.dct-znv.com-es", "10.45.154.217");
        search(client);
        client.close();
    }

    public static void search(TransportClient client) throws Exception {
        SearchResponse response = client.prepareSearch("person_list_data_n_project_v1_2")
                .setTypes("person_list")
                .addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)
                .setScroll(new TimeValue(60000))//为了使用 scroll，初始搜索请求应该在查询中指定 scroll 参数，告诉 Elasticsearch 需要保持搜索的上下文环境多长时间（滚动时间）
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                .setQuery(QueryBuilders.termQuery("gender", "woman"))// Query 查询条件
//                .setPostFilter(QueryBuilders.rangeQuery("test").from(12).to(18)) // Filter 过滤
                .setFrom(0).setSize(100).setExplain(true)
                .get();
        for (SearchHit searchHit : response.getHits()) {
            System.out.println(searchHit.getSource());
        }
        do {
            for (SearchHit searchHit : response.getHits().getHits()) {
                System.out.println(searchHit.getSource());
            }
            response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
        } while (response.getHits().getHits().length != 0);
    }
}
