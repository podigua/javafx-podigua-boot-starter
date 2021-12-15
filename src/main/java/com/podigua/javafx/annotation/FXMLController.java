package com.podigua.javafx.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author: podigua
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Scope("prototype")
public @interface FXMLController {
    /**
     * bean
     * @return bean名称
     */
    @AliasFor(annotation = Component.class)
    String value() default "";
}
