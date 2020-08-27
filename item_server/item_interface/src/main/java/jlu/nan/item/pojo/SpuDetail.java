package jlu.nan.item.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: SpuDetail
 * @Description:
 * @author: Nan
 * @date: 2020/4/1 16:41
 * @version: V1.0
 */

@Data
@Table(name = "tb_spu_detail")
public class SpuDetail {

    @Id
    private Long gid;

    private String subTitle;
    private String descHtml;
    private String genericSpec;
    private String specialSpec;
    private Integer extraServiceId;
    private Boolean enable;

    @JsonIgnore
    private String lastUpdateTime;




}
