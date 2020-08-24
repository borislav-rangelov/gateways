package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.Assert;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.TimeZone;

@Configuration
public class AppConfig {

    public AppConfig() {
        Locale.setDefault(Locale.ENGLISH);
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        // cannot override encoding in Spring at runtime as some strings have already been read
        Assert.isTrue("UTF-8".equals(System.getProperty("file.encoding")), "File encoding is not UTF-8");

        Charset charset = Charset.defaultCharset();
        Assert.isTrue(charset.equals(StandardCharsets.UTF_8), "Default charset is not UTF-8");
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {

        var source = new ReloadableResourceBundleMessageSource();
        source.setBasenames("classpath:locale/gateway");
        source.setBasenames("classpath:locale/device");
        source.setUseCodeAsDefaultMessage(true);
        source.setDefaultEncoding("UTF-8");
        return source;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        var bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
