package com.zhysunny.java.base;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Java 如何有效地避免OOM：善于利用软引用和弱引用
 * @author 章云
 * @date 2019/6/14 10:47
 */
public class Reference {
    public static void main(String[] args) {
        strongReference();
        softReference();
        weakReference();
        phantomReference();
    }

    /**
     * 强引用
     * 当运行至Object[] objArr = new Object[1000];这句时，如果内存不足，JVM会抛出OOM错误也不会回收object指向的对象。
     * 不过要注意的是，当strongReference运行完之后，object和objArr都已经不存在了，所以它们指向的对象都会被JVM回收。
     * 如果想中断强引用和某个对象之间的关联，可以显示地将引用赋值为null，这样一来的话，JVM在合适的时间就会回收该对象。
     */
    public static void strongReference() {
        Object object = new Object();
        Object[] objArr = new Object[1000];
    }

    /**
     * 软引用
     * 对于软引用关联着的对象，只有在内存不足的时候JVM才会回收该对象。
     * 因此，这一点可以很好地用来解决OOM的问题，并且这个特性很适合用来实现缓存：比如网页缓存、图片缓存等。
     */
    public static void softReference() {
        SoftReference<String> sr = new SoftReference<String>(new String("hello"));
        System.out.println(sr.get());
    }

    /**
     * 弱引用
     * 弱引用也是用来描述非必需对象的，当JVM进行垃圾回收时，无论内存是否充足，都会回收被弱引用关联的对象
     */
    public static void weakReference() {
        WeakReference<String> sr = new WeakReference<String>(new String("hello"));
        System.out.println(sr.get());
        //通知JVM的gc进行垃圾回收
        System.gc();
        System.out.println(sr.get());
    }

    /**
     * 虚引用
     * 如果一个对象与虚引用关联，则跟没有引用与之关联一样，在任何时候都可能被垃圾回收器回收
     */
    public static void phantomReference() {
        ReferenceQueue<String> queue = new ReferenceQueue<String>();
        PhantomReference<String> pr = new PhantomReference<String>(new String("hello"), queue);
        System.out.println(pr.get());
    }
}
