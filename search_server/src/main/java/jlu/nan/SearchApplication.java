package jlu.nan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName: SearchApplication
 * @Description:
 * @author: Nan
 * @date: 2020/4/15 16:58
 * @version: V1.0
 */

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class);
    }
}
