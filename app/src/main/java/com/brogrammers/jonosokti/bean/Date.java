package com.brogrammers.jonosokti.bean;

public class Date {
    boolean isClicked;
    long timeInMilliseconds;

    public Date(boolean isClicked,long timeInMilliseconds) {
        this.isClicked = isClicked;
        this.timeInMilliseconds = timeInMilliseconds;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public void setTimeInMilliseconds(long timeInMilliseconds) {
        this.timeInMilliseconds = timeInMilliseconds;
    }
}
