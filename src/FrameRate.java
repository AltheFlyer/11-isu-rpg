import javax.swing.*;
import java.awt.*;


/**
 * [FrameRate.java]
 * Class for framerate
 * @version 1.1
 * @author Jasmine Chu & Ethan Kwan
 * @since May 31, 2019
 */
class FrameRate {

    String frameRate; //to display the frame rate to the screen
    long lastTimeCheck; //store the time of the last time the time was recorded
    long deltaTime; //to keep the elapsed time between current time and last time
    int frameCount; //used to count how many frame occurred in the elapsed time (fps)

    public FrameRate() {
        lastTimeCheck = System.currentTimeMillis();
        frameCount=0;
        frameRate="0 fps";
    }

    /**
     * [update]
     * calculates and updates the frameRate
     * @return void
     */
    public void update() {
        long currentTime = System.currentTimeMillis();  //get the current time
        deltaTime += currentTime - lastTimeCheck; //add to the elapsed time
        lastTimeCheck = currentTime; //update the last time var
        if (frameCount < 200) {
            frameCount++; // every time this method is called it is a new frame
        }
        if (deltaTime>=1000) { //when a second has passed, update the string message
            frameRate = frameCount + " fps" ;
            frameCount=0; //reset the number of frames since last update
            deltaTime=0;  //reset the elapsed time     
        }
    }

    /**
     * [draw]
     * draws the frameRate
     * @param g
     * @param x the x coordinate of the text to be drawn
     * @param y the y coordinate of the text to be drawn
     * @return void
     */
    public void draw(Graphics g, int x, int y) {
        g.drawString(frameRate,x,y); //display the frameRate
    }


}