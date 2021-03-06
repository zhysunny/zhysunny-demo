## 单例模式(singleton)

单例对象（Singleton）是一种常用的设计模式。在Java应用中，单例对象能保证在一个JVM中，该对象只有一个实例存在。
这样的模式有几个好处：
* 某些类创建比较频繁，对于一些大型的对象，这是一笔很大的系统开销。
* 省去了new操作符，降低了系统内存的使用频率，减轻GC压力。
* 有些类如交易所的核心交易引擎，控制着交易流程，如果该类可以创建多个的话，系统完全乱了。（比如一个军队出现了多个司令员同时指挥，肯定会乱成一团），所以只有使用单例模式，才能保证核心交易服务器独立控制整个流程。

### 饿汉式 com.admin.create.singleton.Singleton1
* 静态常量(可用)
* 静态代码块(可用)

优点：写法比较简单，在类装载时完成实例化，避免线程同步问题

缺点：没有懒加载效果，如果这个实力没有用到，则会浪费内存

### 懒汉式 com.admin.create.singleton.Singleton2
* 线程不安全(不可用)
* 线程安全，同步方法(效率低，不推荐用)
* 线程不安全，同步代码块(不可用)
* 线程安全，双重检查(线程安全，延迟加载，效率高，推荐用)

### 静态内部类(线程安全，延迟加载，效率高，推荐用) 
com.admin.create.singleton.Singleton3

### 枚举类(线程安全，延迟加载，效率高，推荐用) 
com.admin.create.singleton.Singleton4

