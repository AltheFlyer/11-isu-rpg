/**
 * [Clock.java]
 * Class for system clock
 * @version 1.1
 * @author Jasmine Chu & Ethan Kwan
 * @since May 31, 2019
 */
class Clock {
    long elapsedTime;
    long lastTimeCheck;

    public Clock() {
        lastTimeCheck=System.nanoTime();
        elapsedTime=0;
    }

    /**
     * [update]
     * updates the clock to the current time
     * @return void
     */
    public void update() {
        long currentTime = System.nanoTime();  //if the computer is fast you need more precision
        elapsedTime=currentTime - lastTimeCheck;
        lastTimeCheck=currentTime;
    }

    /**
     * [resetElapsed]
     * sets elapsedTime to 0 while also updating the time
     * @return void
     */
    public void resetElapsed() {
        long currentTime = System.nanoTime();  //if the computer is fast you need more precision
        lastTimeCheck=currentTime;
        elapsedTime=currentTime - lastTimeCheck;
    }

    public void updateElapsed() {
        long currentTime = System.nanoTime();  //if the computer is fast you need more precision
        elapsedTime=currentTime - lastTimeCheck;
    }

    //return elapsed time in milliseconds
    /**
     * [getElapsedTime]
     * gets the elapsed time between last time check and current time
     * @return double elapsedTime/1.0E9, the elapsed time between the last time check and current time in seconds
     */
    public double getElapsedTime() {
        return elapsedTime/1.0E9;
    }

    public double getElapsedMilli() {
        return elapsedTime/1.0E6;
    }
}