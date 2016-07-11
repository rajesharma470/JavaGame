package com.game.utilities;

import javax.swing.*;
import java.awt.*;

/**
 * Created by rkesavalalji on 11/7/16.
 */
public class FullScreenTest extends JFrame{

    public static void main(String[] args) {
        DisplayMode displayMode;

        if(args.length == 3){
            displayMode = new DisplayMode(Integer.parseInt(args[0]),
                    Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]),
                    DisplayMode.REFRESH_RATE_UNKNOWN);

        }else{
            displayMode = new DisplayMode(800, 600, 32, DisplayMode.REFRESH_RATE_UNKNOWN);
        }
        FullScreenTest fullScreenTest = new FullScreenTest();
        fullScreenTest.run(displayMode);

    }

    private static final long DEMO_TIME =  5000;

    @Override
    public void paint(Graphics g) {
        g.drawString("Hello World!", 20, 50);
    }

    private void run(DisplayMode displayMode) {
        setBackground(Color.blue);
        setForeground(Color.white);
        setFont(new Font("Dialog",Font.PLAIN, 24));
        SimpleScreenManager screen = new SimpleScreenManager();
        try{
            screen.setFullScreen(displayMode,this);
            try{
                Thread.sleep(DEMO_TIME);
            }catch (InterruptedException ie){
                System.out.println("Interrupted Exception");
            }
        }finally {
            screen.restoreScreen();
        }
    }


}
