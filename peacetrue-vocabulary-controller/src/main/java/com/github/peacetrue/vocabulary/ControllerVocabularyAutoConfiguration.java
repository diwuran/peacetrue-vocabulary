package com.github.peacetrue.vocabulary;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * @author xiayx
 */
@Configuration
@EnableConfigurationProperties(ControllerVocabularyProperties.class)
@ComponentScan(basePackageClasses = ControllerVocabularyAutoConfiguration.class)
@PropertySource("classpath:/application-vocabulary-controller.properties")
public class ControllerVocabularyAutoConfiguration {

}
