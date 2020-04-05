package com.github.peacetrue.vocabulary;

import com.github.peacetrue.associate.AssociatedSourceBuilder;
import com.github.peacetrue.associate.AssociatedSourceBuilderImpl;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Objects;

/**
 * @author xiayx
 */
@Configuration
@EnableConfigurationProperties(ServiceVocabularyProperties.class)
@MapperScan(basePackageClasses = ServiceVocabularyAutoConfiguration.class, annotationClass = Mapper.class)
@ComponentScan(basePackageClasses = ServiceVocabularyAutoConfiguration.class)
@PropertySource("classpath:/application-vocabulary-service.properties")
public class ServiceVocabularyAutoConfiguration {

    private ServiceVocabularyProperties properties;

    public ServiceVocabularyAutoConfiguration(ServiceVocabularyProperties properties) {
        this.properties = Objects.requireNonNull(properties);
    }

    @Bean
    public AssociatedSourceBuilder associatedSourceBuilder() {
        return new AssociatedSourceBuilderImpl();
    }

}
