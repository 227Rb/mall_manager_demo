package jlu.nan.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: Stock
 * @Description:
 * @author: Nan
 * @date: 2020/4/1 18:11
 * @version: V1.0
 */

@Data
@Table(name = "tb_stock")
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    private Long skuId;
    private Integer stock;
    private Integer activityStock;
    private Integer activityTotal;

    public Stock(Long skuId, Integer stock) {
        this.skuId = skuId;
        this.stock = stock;
    }
}
