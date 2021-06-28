package com.liangh.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ThreadPoolTest {


	public static void main(String[] args) {

		new ThreadPoolTest().test();
	}

	private void test() {


		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,8,10, TimeUnit.SECONDS,
				new ArrayBlockingQueue<>(10,true), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy ());
		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				log.info("线程开始打印");
				try {
					Thread.sleep(1000 * 1000);
				}catch (Exception e){
					log.error("睡眠异常", e);
				}
				log.info("线程结束打印");
			}
		};

		for (int i = 0; i < 19; i++) {
			threadPoolExecutor.execute(r);
		}

	}
}
