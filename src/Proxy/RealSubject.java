package Proxy;

public class RealSubject implements FatherInter {
    @Override
    public void move() {
        System.out.println("RealSubject excute method");
    }
}
