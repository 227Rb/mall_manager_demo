package jlu.nan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @ClassName: Business
 * @Description:
 * @author: Nan
 * @date: 2020/3/22 12:09
 * @version: V1.0
 */


@Data
@Table(name = "tb_business")
@NoArgsConstructor
@AllArgsConstructor
public class Business {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private String name;
    private String loginName;
    private String password;
    private String tel;
    private Boolean sex;
    private String role;
    private String imgUrl;
    private Boolean isClose;
    private String datetime;


}
