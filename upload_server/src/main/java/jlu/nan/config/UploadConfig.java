package jlu.nan.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @ClassName: UploadConfig
 * @Description:
 * @author: Nan
 * @date: 2020/3/27 0:52
 * @version: V1.0
 */

@Data
@ConfigurationProperties(prefix = "cm.upload")
public class UploadConfig {
    private String baseUrl;
    private List<String>allowTypes;
}
