package jlu.nan.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @ClassName: GrobalCorsConfig
 * @Description:
 * @author: Nan
 * @date: 2020/3/18 19:56
 * @version: V1.0
 */

@Configuration
public class GrobalCorsConfig {

    @Bean
    public CorsFilter corsFilter(){
//        创建配置器
        CorsConfiguration configuration =new CorsConfiguration();

//        定义允许访问的域
        configuration.addAllowedOrigin("http://manage.cm.com");
        configuration.addAllowedOrigin("http://www.cm.com");
        configuration.addAllowedOrigin("http://127.0.0.1:9001");
        configuration.addAllowedOrigin("http://127.0.0.1:9002");

//        设置Cookie权限
        configuration.setAllowCredentials(true);

//        设置允许的请求方式
        configuration.addAllowedMethod(HttpMethod.GET);
        configuration.addAllowedMethod(HttpMethod.POST);
        configuration.addAllowedMethod(HttpMethod.PUT);
        configuration.addAllowedMethod(HttpMethod.DELETE);
        configuration.addAllowedMethod(HttpMethod.OPTIONS);
        configuration.addAllowedMethod(HttpMethod.PATCH);
        configuration.addAllowedMethod(HttpMethod.TRACE);

//        设置允许的头信息
        configuration.addAllowedHeader("*");

//        设置最大时限
        configuration.setMaxAge(3600L);

//        添加该拦截器拦截路径,并注册拦截器
        UrlBasedCorsConfigurationSource bccs =new UrlBasedCorsConfigurationSource();
        bccs.registerCorsConfiguration("/**",configuration);



        return new CorsFilter(bccs);
    }



}
