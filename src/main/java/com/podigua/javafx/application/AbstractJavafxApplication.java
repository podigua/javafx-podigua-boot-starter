package com.podigua.javafx.application;

import com.podigua.javafx.State;
import com.podigua.javafx.event.StageReadyEvent;
import com.podigua.javafx.support.BeanHomeFactory;
import com.podigua.javafx.support.FxmlService;
import com.podigua.javafx.support.SplashScreen;
import com.podigua.javafx.support.impl.DefaultSplashScreen;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.util.Assert;

import java.awt.*;
import java.sql.Statement;
import java.util.concurrent.CompletableFuture;

/**
 * @author: podigua
 **/
public abstract class AbstractJavafxApplication extends Application implements ApplicationListener<StageReadyEvent> {
    /**
     * 状态
     */
    private final static State state = State.getInstance();
    /**
     * 启动画面
     */
    private final CompletableFuture<Runnable> screenCompletableFuture = new CompletableFuture<>();
    /**
     * 启动画面
     */
    private static SplashScreen splashScreen;

    @Override
    public void init() {
        CompletableFuture.supplyAsync(() ->
                SpringApplication.run(this.getClass(), state.getArgs())
        ).whenComplete((context, throwable) -> {
            if (throwable != null) {
                Platform.runLater(() ->
                        error(throwable)
                );
            } else {
                state.setContext(context);
            }
        }).thenAcceptBothAsync(screenCompletableFuture, ((context, runnable) ->
                Platform.runLater(runnable)
        ));
    }

    /**
     * 错误提示
     *
     * @param throwable
     */
    private void error(Throwable throwable) {
        Alert alert = new Alert(Alert.AlertType.ERROR, throwable.getMessage());
        alert.showAndWait().ifPresent((response) ->
                Platform.exit()
        );
    }

    @Override
    public void start(Stage stage) {
        state.setStage(stage);
        if (SystemTray.isSupported()) {
            state.setSystemTray(SystemTray.getSystemTray());
        }
        state.setHostServices(this.getHostServices());
        Stage splashStage = new Stage(StageStyle.TRANSPARENT);
        if (splashScreen.visible()) {
            Scene splashScene = new Scene(splashScreen.getParent(), Color.TRANSPARENT);
            splashStage.setScene(splashScene);
            splashStage.initStyle(StageStyle.TRANSPARENT);
            splashStage.show();
        }
        screenCompletableFuture.complete(() -> {
            if (splashScreen.visible()) {
                splashStage.close();
            }
            state.getContext().publishEvent(new StageReadyEvent(stage));
        });
    }

    /**
     * 使用默认动画启动
     *
     * @param appClass appClass
     * @param args 参数
     */
    public static void launch(Class<? extends Application> appClass, String... args) {
        launch(appClass, new DefaultSplashScreen(), args);
    }

    /**
     * 自定义方法启用
     *
     * @param appClass appClass
     * @param screen 动画
     * @param args 参数
     */
    public static void launch(Class<? extends Application> appClass, SplashScreen screen, String... args) {
        Assert.notNull(screen, "splash screen is required");
        splashScreen = screen;
        state.setArgs(args);
        Application.launch(appClass, args);
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        ready(event.getStage());
    }

    @Override
    public void stop() {
        Platform.exit();
        state.getContext().close();
        System.exit(0);
    }

    /**
     * 启动方法
     *
     * @param stage  舞台
     */
    protected abstract void ready(Stage stage);

    /**
     * 获取状态
     *
     * @return 状态
     */
    protected State getState() {
        return state;
    }
}
