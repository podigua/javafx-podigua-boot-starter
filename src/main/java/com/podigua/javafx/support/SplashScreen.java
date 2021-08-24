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
 **/
public interface SplashScreen {
    /**
     * 是否显示
     *
     * @return 是否显示
     */
    boolean visible();

    /**
     * 图片路径
     *
     * @return 图片路径
     */
    String getImagePath();

    /**
     * 获取图片
     *
     * @return 组件
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
