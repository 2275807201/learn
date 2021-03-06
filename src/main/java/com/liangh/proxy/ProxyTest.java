package com.liangh.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import lombok.extern.slf4j.Slf4j;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2021/01/11 16:14
 */
@Slf4j
public class ProxyTest {


    public static void main(String[] args) {

        try {
            // 被代理接口
            Class<Dog> clz = Dog.class;
            Class<Cat> catClass = Cat.class;
            ClassLoader classLoader = clz.getClassLoader();
            Class<?>[] interfaces = {clz, catClass};

            Object proxy =  Proxy.newProxyInstance(classLoader, interfaces, (proxyObj, method, args1) -> {

                // proxy 对象就是外层的proxyDog

                String name = method.getDeclaringClass().getName() + "Impl";
                Class<?> aClass = Class.forName(name);
                Constructor<?> constructor = aClass.getConstructor();
                Object target = constructor.newInstance();

                Method method1 = aClass.getMethod(method.getName(), method.getParameterTypes());
                Object result = method1.invoke(target, args1);

                return result;
            });

            String result = ((Dog)proxy).eat("骨头");
            log.info("狗吃饭的结果：{}", result);

            ((Cat)proxy).bite();
            log.info("猫已经吃完了");

        }catch (Throwable throwable){
            log.error("捕获抛出类", throwable);
        }

    }

}
