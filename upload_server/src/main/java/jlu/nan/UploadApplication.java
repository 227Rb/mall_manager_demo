package jlu.nan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: UpdateApplication
 * @Description:
 * @author: Nan
 * @date: 2020/3/25 22:19
 * @version: V1.0
 */

@EnableDiscoveryClient
@SpringBootApplication
public class UploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(UploadApplication.class);
    }
}
