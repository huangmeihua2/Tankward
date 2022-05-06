package Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestProxy {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        FatherInter fatherInter = (FatherInter) java.lang.reflect.Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                new Class[]{FatherInter.class},new Proxytest(realSubject));
        fatherInter.move();
    }
}

// fatherInter.move();执行的时候最终都会执行到传入的Handle实现类中的invoke()方法的。
// 但这样这里就可以真正的代理任何的目标对象了，传入到实现的handle接口就可以，只要实现了同一个目标接口就可以。
class Proxytest implements InvocationHandler {
    FatherInter fatherInter;

    public Proxytest(FatherInter fatherInter) {
        this.fatherInter = fatherInter;
    }

    public void before() {
        System.out.println("start....");
    }

    public void after() {
        System.out.println("end....");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object object = method.invoke(fatherInter, args);
        after();
        return object;
    }
}
