package com.liangh.guava.event;

import lombok.Data;

public class TestEvent {
    private final int message;
    public TestEvent(int message) {        
        this.message = message;
        System.out.println("event message:"+message);
    }
    public int getMessage() {
        return message;
    }
}