package jlu.nan.item.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: SkuVo
 * @Description:
 * @author: Nan
 * @date: 2020/4/2 15:31
 * @version: V1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkuVo {

    private Long id;
    private String title;
    private String imageUrl;
    private Long price;
    private String indexes;
    private Integer stock;

}
