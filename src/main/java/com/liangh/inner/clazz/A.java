package com.liangh.inner.clazz;

public class A {

	public static void main(String[] args) {
		A.B b = new A.B();
	}

	static class B{
		private static String name;
	}
}
