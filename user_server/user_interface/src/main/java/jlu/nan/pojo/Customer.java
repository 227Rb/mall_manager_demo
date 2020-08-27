package jlu.nan.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: Customer
 * @Description:
 * @author: Nan
 * @date: 2020/4/24 15:46
 * @version: V1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_customer")
public class Customer {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private String name;
    private String loginName;

    @JsonIgnore
    private String password;
    @JsonIgnore
    private String salt;

    private String tel;
    private Boolean sex;
    private Boolean isClose;
    private String createTime;




}
