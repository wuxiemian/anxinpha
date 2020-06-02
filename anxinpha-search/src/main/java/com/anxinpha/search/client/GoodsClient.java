package com.anxinpha.search.client;

import com.anxinpha.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 尹硕硕
 * @description
 **/
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {
}
