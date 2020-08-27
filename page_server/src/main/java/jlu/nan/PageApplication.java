package jlu.nan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName: PageApplication
 * @Description:
 * @author: Nan
 * @date: 2020/4/15 20:45
 * @version: V1.0
 */

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class PageApplication {
    public static void main(String[] args) {
        SpringApplication.run(PageApplication.class);
    }
}
