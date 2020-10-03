package com.brogrammers.jonosokti.bean;

import java.io.Serializable;
import java.util.List;

public class Location implements Serializable {
    List<String> list;

    public Location() {
    }

    public Location(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
