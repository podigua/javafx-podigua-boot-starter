package com.podigua.javafx.support.impl;

import com.podigua.javafx.support.SplashScreen;

/**
 * @author: podigua
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
