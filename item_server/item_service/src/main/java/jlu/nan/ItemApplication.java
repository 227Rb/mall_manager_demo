package jlu.nan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * @ClassName: ZuulApplication
 * @Description:
 * @author: Nan
 * @date: 2020/3/15 0:20
 * @version: V1.0
 */

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("jlu.nan.item.mapper")
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class);
    }
}
