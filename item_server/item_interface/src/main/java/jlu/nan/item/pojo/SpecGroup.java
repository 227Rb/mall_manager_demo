package jlu.nan.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: SpecGroup
 * @Description:
 * @author: Nan
 * @date: 2020/3/27 14:54
 * @version: V1.0
 */

@Data
@Table(name = "tb_specGroup")
public class SpecGroup {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer cid;
    private String name;




}
