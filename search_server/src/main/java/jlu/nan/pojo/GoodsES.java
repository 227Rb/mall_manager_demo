package jlu.nan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName: GoodsES
 * @Description:
 * @author: Nan
 * @date: 2020/4/7 23:59
 * @version: V1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "cm", type = "goods" ,shards = 1)
public class GoodsES {

    @Field(type = FieldType.Long)
    @Id
    private Long id;

//    包含商品名,品牌,分类,统一搜索
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String allSearch;
    @Field(type = FieldType.Keyword)
    private String title;


    @Field(type = FieldType.Keyword)
    private String imgUrl;

    @Field(type = FieldType.Long)
    private Long basePrice;

//    用于条件过滤

    private Map<String,Object>specs ;


    @Field(type = FieldType.Keyword)
    private String brand;

    @Field(type = FieldType.Keyword)
    private String category;

    private String lastUpdateTime;
}
