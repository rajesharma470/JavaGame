package com.game.utilities;

import javax.swing.*;
import java.awt.*;

/**
 * Created by rkesavalalji on 11/7/16.
 */
public class SimpleScreenManager {

    private GraphicsDevice graphicsDevice;

    public SimpleScreenManager() {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
    }

    public void setFullScreen(DisplayMode displayMode, JFrame window){
        window.setUndecorated(true);
        window.setResizable(false);
        graphicsDevice.setFullScreenWindow(window);
        if(null != displayMode && graphicsDevice.isDisplayChangeSupported()){
            try{
                graphicsDevice.setDisplayMode(displayMode);
            }catch (IllegalArgumentException iae){
                System.out.println("Exception while setting full screen");
            }
        }
    }

    public Window getFullScreenWindow(){
        return graphicsDevice.getFullScreenWindow();
    }

    public void restoreScreen(){
        Window window = graphicsDevice.getFullScreenWindow();
        if(null != window){
            window.dispose();
        }
        graphicsDevice.setFullScreenWindow(null);
    }
}
