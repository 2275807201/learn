package com.liangh.classloader;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;

@Slf4j
public class MyClassLoader extends ClassLoader{

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {

		File f = new File("/Users/liangh/study/learn/target/classes/", name.replace(".", "/").concat(".class"));

		try {
			FileInputStream fis = new FileInputStream(f);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			boolean var5 = false;

			int b;
			while((b = fis.read()) != -1) {
				baos.write(b);
			}

			baos.flush();
			byte[] bytes = baos.toByteArray();
			baos.close();
			fis.close();
			return this.defineClass(name, bytes, 0, bytes.length);
		} catch (Exception var7) {
			var7.printStackTrace();
			return super.findClass(name);
		}
	}

	public static void main(String[] args) throws Exception {
		ClassLoader l = new MyClassLoader();
		Class clazz = l.loadClass("java.lang.Object");
		System.out.println(clazz.getClassLoader());
		Object o = clazz.newInstance();
		int i = o.hashCode();
		log.info("重写object的hashCode方法，hashCode = {}",i);
	}
}
