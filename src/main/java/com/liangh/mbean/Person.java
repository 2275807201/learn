package com.liangh.mbean;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2020/12/15 11:05
 */
public class Person  extends NotificationBroadcasterSupport implements PersonMBean{

    private String name;

    private int sequenceNumber;

    @Override
    public String getName() {
        return name;
    }


    @Override
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void modifyName(String name) {

        Notification n = new AttributeChangeNotification(this,
                sequenceNumber++, System.currentTimeMillis(),
                "name change，liangh", "name", "String",
                this.name, name);

        this.name = name;

        sendNotification(n);
    }


//    @Override
//    public MBeanNotificationInfo[] getNotificationInfo() {
//        String[] types = new String[]{
//                AttributeChangeNotification.ATTRIBUTE_CHANGE
//        };
//
//        String name = AttributeChangeNotification.class.getName();
//        String description = "An attribute of this MBean has changed";
//        MBeanNotificationInfo info =
//                new MBeanNotificationInfo(types, name, description);
//        return new MBeanNotificationInfo[]{info};
//    }


    @Override
    public String getOtherName(String otherName) {
        if(otherName != null){
            return otherName;
        }
        return name;
    }
}
