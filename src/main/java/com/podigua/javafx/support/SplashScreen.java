package com.podigua.javafx.support;

import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @author: podigua
 * @create: 2021-08-02 11:27
 **/
public interface SplashScreen {
    /**
     * 是否显示
     *
     * @return
     */
    boolean visible();

    /**
     * 图片路径
     *
     * @return
     */
    String getImagePath();

    /**
     * 获取图片
     *
     * @return
     */
    default Parent getParent() {
        try {
            Resource resource = new ClassPathResource(getImagePath());
            ImageView image = new ImageView(new Image(resource.getInputStream()));
            ProgressBar progressBar = new ProgressBar();
            progressBar.setPrefWidth(image.getImage().getWidth());
            VBox vbox = new VBox();
            vbox.getChildren().addAll(image, progressBar);
            return vbox;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
