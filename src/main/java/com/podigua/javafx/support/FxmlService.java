package com.podigua.javafx.support;

import javafx.scene.Parent;

/**
 * @author: podigua
 **/
public interface FxmlService {
    /**
     * 加载
     *
     * @param path fxml 路径
     * @param <T>  返回值类型
     * @return Parent
     */
    <T extends Parent> T load(String path);
}
