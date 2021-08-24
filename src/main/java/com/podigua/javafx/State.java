package com.podigua.javafx;

import com.podigua.javafx.application.AbstractJavafxApplication;
import javafx.application.HostServices;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;

/**
 * @author: podigua
 **/
@Getter
@Setter
public class State {
    private final static State instance = new State();
    /**
     * 全局场景图
     */
    private Stage stage;
    /**
     * 主参数
     */
    private String[] args;
    /**
     * 系统图标
     */
    private SystemTray systemTray;
    /**
     * 主机服务
     */
    private HostServices hostServices;
    /**
     * spring 上下文
     */
    private ConfigurableApplicationContext context;

    /**
     * 获取状态实例
     *
     * @return 当前状态
     */
    public static State getInstance() {
        return instance;
    }

    /**
     * 私有构造
     */
    private State() {

    }
}
