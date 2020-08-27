package jlu.nan.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: SpecParam
 * @Description:
 * @author: Nan
 * @date: 2020/3/27 14:58
 * @version: V1.0
 */

@Data
@Table(name = "tb_specParam")
public class SpecParam {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer cid;
    private Integer gid;
    private String name;
    private Boolean isNumber;
    private String unit;
    private Boolean isGeneric;
    private Boolean searching;
    private String segments;






}
