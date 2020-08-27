package jlu.nan.item.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: GoodsVO
 * @Description:
 * @author: Nan
 * @date: 2020/3/30 12:39
 * @version: V1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVO {
    private Long id;

    private String name;
    private String brand;

    private String categoryPath;
    private Integer cid1;
    private Integer cid2;
    private Integer cid3;

    private Boolean saleable;

    private String imgUrl;
    private Long basePrice;


    private String lastUpdateTime;

}
