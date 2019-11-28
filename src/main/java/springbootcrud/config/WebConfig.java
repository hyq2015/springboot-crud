package springbootcrud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springbootcrud.interceptor.TokenInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*","http://localhost:9000") // 这里是页面调用的地址
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**")
        .excludePathPatterns(
                "/webjars/**",
                "/swagger-ui.html/**",
                "/swagger-resources/**",
                "/v2/**",
                "/**/favicon.ico",
                "/login",
                "/register"
        );
    }
}
