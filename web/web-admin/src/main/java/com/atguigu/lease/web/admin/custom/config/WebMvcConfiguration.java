package com.atguigu.lease.web.admin.custom.config;

import com.atguigu.lease.web.admin.custom.converter.StringToBaseEnumConverter;
import com.atguigu.lease.web.admin.custom.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: WebMvcConfiguration
 * Package: com.atguigu.lease.web.admin.custom.config
 * Description:
 *
 * @Author HL
 * @Create 11/4/2024 10:00 AM
 * @Version:
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
//    @Autowired
//    private StringToItemTypeConverter stringToItemTypeConverter;

    @Autowired
    private StringToBaseEnumConverter stringToBaseEnumConverter;

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(stringToItemTypeConverter);
        registry.addConverterFactory(this.stringToBaseEnumConverter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.authenticationInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/login/**");
    }
}