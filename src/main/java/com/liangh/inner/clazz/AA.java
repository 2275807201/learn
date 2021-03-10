package com.liangh.inner.clazz;

public class AA {

	public static void main(String[] args) {
//		AA.BB bb = new AA().new BB();
	}

	BB newBBInstance(){
		return new BB();
	}

	class BB{
		private String name;
	}
}
