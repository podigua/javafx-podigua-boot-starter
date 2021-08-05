package com.podigua.javafx.support.impl;

import com.podigua.javafx.support.FxmlService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author: podigua
 * @create: 2021-08-02 11:33
 **/
public class FxmlServiceImpl implements FxmlService {
    private final ApplicationContext applicationContext;

    public FxmlServiceImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public <T extends Parent> T load(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(new ClassPathResource(path).getURL());
            loader.setControllerFactory(applicationContext::getBean);
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
