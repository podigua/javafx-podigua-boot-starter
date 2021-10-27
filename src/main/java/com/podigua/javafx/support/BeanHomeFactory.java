package com.podigua.javafx.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * BeanHomeFactory
 *
 * @author: podigua
 **/
public class BeanHomeFactory implements ApplicationContextAware {
    private static ApplicationContext APPLICATION_CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_CONTEXT = applicationContext;
    }

    /**
     * 获取Bean
     *
     * @param clazz class
     * @param <T>   类型
     * @return T
     */
    public static <T> T getBean(Class<T> clazz) {
        return (T) APPLICATION_CONTEXT.getBean(clazz);
    }

    /**
     * 获取Bean
     *
     * @param bean bean
     * @param <T>  类型
     * @return T
     */
    public static <T> T getBean(String bean) {
        return (T) APPLICATION_CONTEXT.getBean(bean);
    }

    /**
     * 获取上下文
     *
     * @return {@link ApplicationContext}
     */
    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }
}
