package com.liangh.debug;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2020/12/30 16:14
 */
public class Debugger {

    public static void main(String[] args) {


        Debugger d = new Debugger();

//        d.test2();

        d.test();


    }

    private void test2() {
        while (true){
            System.out.println(1);
            System.out.println(2);
            System.out.println(3);
        }
    }

    private void test() {

        try {
            Dog dog = new Dog();
            dog.bite();
            dog.run();
            dog.eat();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
