package jlu.nan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName: UserApplication
 * @Description:
 * @author: Nan
 * @date: 2020/3/22 12:23
 * @version: V1.0
 */

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(value = {"jlu.nan.Business.mapper",
                     "jlu.nan.Customer.mapper"})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
    }
}
