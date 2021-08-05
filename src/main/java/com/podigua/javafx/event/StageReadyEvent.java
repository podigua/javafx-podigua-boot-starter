package com.podigua.javafx.event;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

/**
 * @author: podigua
 * @create: 2021-08-02 11:25
 **/
public class StageReadyEvent extends ApplicationEvent {


    public StageReadyEvent(Stage stage) {
        super(stage);
    }

    /**
     * 获取场景图
     *
     * @return
     */
    public Stage getStage() {
        return (Stage) getSource();
    }
}
