package com.podigua.javafx.configuration;

import com.podigua.javafx.support.FxmlService;
import com.podigua.javafx.support.impl.FxmlServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: podigua
 **/
@Configuration
@ConditionalOnProperty(name = "podigua.javafx.enabled", matchIfMissing = true)
@Slf4j
public class JavafxAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public FxmlService fxmlService(ApplicationContext applicationContext) {
        return new FxmlServiceImpl(applicationContext);
    }
}
