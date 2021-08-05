package com.podigua.javafx.support.impl;

import com.podigua.javafx.support.SplashScreen;

/**
 * @author: podigua
 * @create: 2021-08-02 11:30
 **/
public class DefaultSplashScreen implements SplashScreen {
    private final static String IMAGE_PATH = "podigua/splash-screen.png";
    @Override
    public boolean visible() {
        return true;
    }

    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }
}
