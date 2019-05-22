abstract class Clock {

    static long lastTime = System.nanoTime();

    static void setlastTime() {
        lastTime = System.nanoTime();
    }

    static long getLastTime() {
        return lastTime;
    }

    static double getDeltaTime() {
        long nextMark = System.nanoTime() - lastTime;
        lastTime = System.nanoTime();
        return nextMark / 1.0E9;
    }
}