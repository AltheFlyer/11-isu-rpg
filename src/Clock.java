class Clock {
    long elapsedTime;
    long lastTimeCheck;

    public Clock() {
        lastTimeCheck=System.nanoTime();
        elapsedTime=0;
    }

    public void update() {
        long currentTime = System.nanoTime();  //if the computer is fast you need more precision
        elapsedTime=currentTime - lastTimeCheck;
        lastTimeCheck=currentTime;
    }

    public void updateElapsed() {
        long currentTime = System.nanoTime();  //if the computer is fast you need more precision
        elapsedTime=currentTime - lastTimeCheck;
    }

    //return elapsed time in milliseconds
    public double getElapsedTime() {
        return elapsedTime/1.0E9;
    }

    public double getElapsedMilli() {
        return elapsedTime/1.0E6;
    }
}