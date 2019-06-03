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
     * [getElapsedTime]
     * gets the elapsed time between last time check and current time
     * @return double elapsedTime/1.0E9, the elapsed time in seconds
     */
    public double getElapsedTime() {
        return elapsedTime/1.0E9;
    }
}