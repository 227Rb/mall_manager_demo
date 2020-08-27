package jlu.nan.item.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @ClassName: Goods
 * @Description:
 * @author: Nan
 * @date: 2020/3/29 20:50
 * @version: V1.0
 */

@Data
@Table(name = "tb_goods")
public class Goods {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private String name;
    private String brand;

    private Integer cid1;
    private Integer cid2;
    private Integer cid3;

    private Boolean saleable;
    private Boolean vatid;

    private String imgUrl;
    private Long basePrice;

    @JsonIgnore
    private String createTime;
    @JsonIgnore
    private String lastUpdateTime;


}
