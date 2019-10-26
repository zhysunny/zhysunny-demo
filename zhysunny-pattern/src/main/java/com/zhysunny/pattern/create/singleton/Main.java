package com.zhysunny.pattern.create.singleton;

/**
 * 单例模式测试类
 * @author 章云
 * @date 2019/6/18 21:54
 */
public class Main {
    public static void main(String[] args) {
        //饿汉式
        Singleton1 singleton11 = Singleton1.getInstance1();
        Singleton1 singleton12 = Singleton1.getInstance1();
        compare(singleton11, singleton12);
        Singleton1 singleton21 = Singleton1.getInstance2();
        Singleton1 singleton22 = Singleton1.getInstance2();
        compare(singleton21, singleton22);
        //懒汉式
        Singleton2 instance1 = Singleton2.getInstance1();
        Singleton2 instance2 = Singleton2.getInstance2();
        compare(instance1, instance2);
        Singleton2 instance3 = Singleton2.getInstance3();
        compare(instance2, instance3);
        Singleton2 instance4 = Singleton2.getInstance4();
        compare(instance3, instance4);
        //静态内部类
        Singleton3 staticInstance1 = Singleton3.getInstance();
        Singleton3 staticInstance2 = Singleton3.getInstance();
        compare(staticInstance1, staticInstance2);
        //枚举类
        Singleton4 enumInstance1 = Singleton4.INSTANCE;
        Singleton4 enumInstance2 = Singleton4.INSTANCE;
        compare(enumInstance1, enumInstance2);
    }

    private static void compare(Object obj1, Object obj2) {
        if (obj1 == obj2) {
            System.out.println("实例相同");
        } else {
            System.out.println("实例不同");
        }
    }
}
