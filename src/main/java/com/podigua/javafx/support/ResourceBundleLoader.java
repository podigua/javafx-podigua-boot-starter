package com.podigua.javafx.support;

import java.util.ResourceBundle;

/**
 * @author: podigua
 **/
public interface ResourceBundleLoader {
    /**
     * 加载 国际化配置
     *
     * @return 国际化
     */
    ResourceBundle get();
}
