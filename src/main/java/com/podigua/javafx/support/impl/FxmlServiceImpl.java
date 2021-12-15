package com.podigua.javafx.support.impl;

import com.podigua.javafx.support.FxmlService;
import com.podigua.javafx.support.ResourceBundleLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author: podigua
 **/
public class FxmlServiceImpl implements FxmlService {
    private final ApplicationContext applicationContext;
    private final ResourceBundleLoader resourceBundleLoader;

    public FxmlServiceImpl(ApplicationContext applicationContext, ResourceBundleLoader resourceBundleLoader) {
        this.applicationContext = applicationContext;
        this.resourceBundleLoader = resourceBundleLoader;
    }

    @Override
    public <T extends Parent> T load(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(new ClassPathResource(path).getURL());
            loader.setResources(resourceBundleLoader.get());
            loader.setControllerFactory(applicationContext::getBean);
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
