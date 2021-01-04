package com.liangh.mbean;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2020/12/15 11:04
 */
public interface PersonMBean {

    String getName();

    void setName(String name);

    void modifyName(String name);

    String getOtherName(String otherName);

}
