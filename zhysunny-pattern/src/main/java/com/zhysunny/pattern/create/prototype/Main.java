package com.zhysunny.pattern.create.prototype;

/**
 * @author 章云
 * @date 2019/11/26 14:37
 */
public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        Prototype pro = new ConcretePrototype();
        // 浅拷贝
        System.out.println("======= 浅拷贝 =======");
        Prototype pro2 = pro.clone();
        System.out.println(pro);
        System.out.println(pro2);
        pro2.setName("Shallow");
        pro2.setAge(25);
        // 集合引用同一个对象
        pro2.getHobby().add("hobby3");
        System.out.println(pro);
        System.out.println(pro2);
        // 深拷贝
        System.out.println("======= 深拷贝 =======");
        Prototype pro3 = pro.deepClone();
        System.out.println(pro);
        System.out.println(pro3);
        pro3.setName("Deep");
        pro3.setAge(25);
        pro3.getHobby().add("hobby3");
        System.out.println(pro);
        System.out.println(pro3);
    }

}
