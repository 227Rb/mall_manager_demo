package jlu.nan.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: Category
 * @Description:
 * @author: Nan
 * @date: 2020/3/19 23:14
 * @version: V1.0
 */

@Data
@Table(name="tb_category")
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String name;
    private Integer parentId;
    private Boolean isParent;
    private Integer sort;
}
