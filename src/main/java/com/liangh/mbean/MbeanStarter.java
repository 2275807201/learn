package com.liangh.mbean;

import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2020/12/15 11:07
 */
public class MbeanStarter {

    public static void main(String[] args) throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("com.liangh.mbean:type=person");
        PersonMBean mbean = new Person();
        mbs.registerMBean(mbean, name);

        mbs.addNotificationListener(name, new NotificationListener() {
            @Override
            public void handleNotification(Notification notification, Object handback) {
                System.out.println("123456");

            }
        },null,null);

        System.out.println("Waiting forever...");
        Thread.sleep(Long.MAX_VALUE);
    }

}
