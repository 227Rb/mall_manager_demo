package jlu.nan.item.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @ClassName: Sku
 * @Description:
 * @author: Nan
 * @date: 2020/4/1 17:30
 * @version: V1.0
 */

@Data
@Table(name = "tb_sku")
public class Sku {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private Long spuGid;
    private String title;
    private String imageUrl;
    private Long price;
    private String ownSpec;
    private String indexes;

    @Transient
    private Integer stock;

    @JsonIgnore
    private String lastUpdateTime;

}
