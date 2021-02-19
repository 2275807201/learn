package com.liangh.guava.event;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeadEventListener {
    boolean notDelivered = false;  
       
    @Subscribe
    public void listen(DeadEvent event) {
        
        notDelivered = true;
        log.info("死信事件：{}", event);
    }  
   
    public boolean isNotDelivered() {  
        return notDelivered;  
    }  
}