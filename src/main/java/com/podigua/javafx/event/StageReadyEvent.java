package com.podigua.javafx.event;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

/**
 * Stage准备完成事件
 *
 * @author: podigua
 **/
public class StageReadyEvent extends ApplicationEvent {

    public StageReadyEvent(Stage stage) {
        super(stage);
    }

    /**
     * 获取Stage
     *
     * @return 舞台
     */
    public Stage getStage() {
        return (Stage) getSource();
    }
}
