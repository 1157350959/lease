package com.atguigu.lease.web.admin.custom.converter;

import com.atguigu.lease.model.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * ClassName: StringToBaseEnumConverter
 * Package: com.atguigu.lease.web.admin.custom.converter
 * Description:
 *
 * @Author HL
 * @Create 11/4/2024 10:11 AM
 * @Version:
 */
@Component
public class StringToBaseEnumConverter implements ConverterFactory<String, BaseEnum> {
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new Converter<String, T>() {
          public T convert(String source) {
              T[] enumConstants = targetType.getEnumConstants();
              for (T enumConstant : enumConstants) {
                  if(enumConstant.getCode().equals(Integer.valueOf(source))) return enumConstant;
              }
              throw new IllegalArgumentException("code illegal");
          }
        };
    }
}
