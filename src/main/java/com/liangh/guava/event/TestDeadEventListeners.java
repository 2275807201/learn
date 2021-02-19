package com.liangh.guava.event;

import com.google.common.eventbus.EventBus;

public class TestDeadEventListeners {


    public static void main(String[] args) {
       
        EventBus eventBus = new EventBus("test");
        DeadEventListener deadEventListener = new DeadEventListener();  
        eventBus.register(deadEventListener);  

        eventBus.post(new TestEvent(200));         
        eventBus.post(new TestEvent(300));        
       
        System.out.println("deadEvent:"+deadEventListener.isNotDelivered());

    }  
}

////输出信息
//event message:200
//event message:300
//deadEvent:true