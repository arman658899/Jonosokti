package com.brogrammers.jonosokti.bean;

public class Time {
    boolean isClicked;
    String time;

    public Time(boolean isClicked, String time) {
        this.isClicked = isClicked;
        this.time = time;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
