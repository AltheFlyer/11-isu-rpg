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
    double maxTime; //seconds

    /**
     * [Clock]
     * @param maxTime the maximum time between each time check in seconds
     * updates the clock to the current time
     * @return void
     */
    public Clock(double maxTime) {
        lastTimeCheck=System.nanoTime();
        elapsedTime=0;
        this.maxTime = maxTime;
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

    /**
     * [updateElapsed]
     * updates elapsedTime since last time check, also caps elapsedTime to solve lag problems
     * @return void
     */
    public void updateElapsed() {
        long currentTime = System.nanoTime();  //if the computer is fast you need more precision
        elapsedTime=currentTime - lastTimeCheck;
        if (elapsedTime/1.0E9 > maxTime) {
            elapsedTime = (long)(maxTime*1.0E9);
        }
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

    /**
     * [getElapsedMilli]
     * gets the elapsed time between last time check and current time in milliseconds
     * @return double elapsedTime/1.0E6, the elapsed time between the last time check and current time in milliseconds
     */
    public double getElapsedMilli() {
        return elapsedTime/1.0E6;
    }
}