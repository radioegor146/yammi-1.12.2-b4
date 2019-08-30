package ru.yammi.helpers;

public class MSTimer {

    private long currentMS = 0L;
    private long pause = 0L;

    public MSTimer(long l) {
        this.pause = l;
        this.currentMS = System.currentTimeMillis();
    }

    public boolean checkMS() {
        if (this.currentMS <= System.currentTimeMillis()) {
            this.currentMS = System.currentTimeMillis() + this.pause;
            return true;
        }
        return false;
    }
}
