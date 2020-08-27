package jlu.nan.client;

import jlu.nan.item.ClientAPI.DetailApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName: DetailClient
 * @Description:
 * @author: Nan
 * @date: 2020/4/16 0:59
 * @version: V1.0
 */

@FeignClient("item-service")
public interface DetailClient extends DetailApi {
}
