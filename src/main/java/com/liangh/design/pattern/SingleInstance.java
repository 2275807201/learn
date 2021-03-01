package com.liangh.design.pattern;

/**
 * 利用jvm类加载机制创建单例
 */
public class SingleInstance {


	private SingleInstance(){
		System.out.println("单例被创建了");
	}


	// 单例对象持有类
	static class SingleInstanceHolder{
		// 单例对象
		private final static SingleInstance singleInstance = new SingleInstance();
	}

	public static SingleInstance newInstance(){
		return SingleInstanceHolder.singleInstance;
	}

	public static void main(String[] args) {
		Class clazz = SingleInstance.class;

		SingleInstance singleInstance = SingleInstance.newInstance();
		SingleInstance singleInstance2 = SingleInstance.newInstance();
	}

}
