package com.liangh.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFileTest {


	public static void main(String[] args) {
		//生成$Proxy0的class文件
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

		Dog dog = (Dog) Proxy.newProxyInstance(Dog.class.getClassLoader(),
				new Class[]{Dog.class, Cat.class},
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						return args[0];
					}
				});
		String food= dog.eat("骨头");
		System.out.println("狗吃的事务：" +  food);
	}


}
