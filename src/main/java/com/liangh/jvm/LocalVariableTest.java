package com.liangh.jvm;

public class LocalVariableTest {


	String hello(String s){

		for (int i = 0; i < 10; i++) {
			int m = 0;
			String str = "hello";
			long k = 1L;
		}

		return "hello";
	}


	int  exceptionTest(){
		int i = 0;
		try {
			i = 1;
			throw new RuntimeException("");
		}catch (Exception e){
			e.printStackTrace();
			i = -1;
		}
		return i;
	}

}
