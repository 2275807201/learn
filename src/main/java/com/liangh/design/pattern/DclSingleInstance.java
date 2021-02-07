package com.liangh.design.pattern;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 即使不加volatile关键字，也很难看到指令重排的现象
 */
@Slf4j
public class DclSingleInstance {

	private int initCount;

	private volatile static DclSingleInstance instance;

	private DclSingleInstance()  {
		// 赋初始值
		for (int i = 0; i < 1000000000; i++) {
			initCount ++ ;
		}

	}

	public static DclSingleInstance getInstance(){
		if(instance != null){
			return instance;
		}

		synchronized (DclSingleInstance.class){
			if(instance == null){
				instance = new DclSingleInstance();
			}
		}
		return instance;
	}

	public static void main(String[] args) throws Exception {

		new Thread(() -> {
			DclSingleInstance.getInstance();
		}).start();

		Thread.sleep(1);
		new Thread(() -> {
			int initCount = DclSingleInstance.getInstance().initCount;
			log.info("查看变量是默认值0还是初始值，实际结果：{}", initCount);
		}).start();

		System.in.read();
	}

}
