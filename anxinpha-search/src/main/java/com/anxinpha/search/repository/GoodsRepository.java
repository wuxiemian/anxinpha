package com.anxinpha.search.repository;

import com.anxinpha.search.pojo.GoodsSC;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author 尹硕硕
 * @description
 **/
public interface GoodsRepository extends ElasticsearchRepository<GoodsSC,Long> {
}
