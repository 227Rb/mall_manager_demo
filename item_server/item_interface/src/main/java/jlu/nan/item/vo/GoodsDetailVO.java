package jlu.nan.item.vo;

import jlu.nan.item.pojo.Goods;
import jlu.nan.item.pojo.Sku;
import jlu.nan.item.pojo.SpuDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: GoodsDetailVO
 * @Description:
 * @author: Nan
 * @date: 2020/4/1 18:02
 * @version: V1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDetailVO {


    private Goods goods;
    private SpuDetail spuDetail;
    private List<Sku> skus;



}
