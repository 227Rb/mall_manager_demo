package jlu.nan.client;

import jlu.nan.item.ClientAPI.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName: GoodsClient
 * @Description:
 * @author: Nan
 * @date: 2020/4/10 13:05
 * @version: V1.0
 */

@FeignClient("item-service")
public interface GoodsClient extends GoodsApi{

}
