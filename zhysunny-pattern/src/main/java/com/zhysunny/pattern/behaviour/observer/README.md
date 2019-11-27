## 观察者模式(observer)

定义对象间的一种**一对多**的依赖关系,当一个对象的状态发生改变时,所有依赖于它的对象都得到通知并被自动更新。

### 监听器模式(一对一)(拓展)
* com.zhysunny.pattern.behaviour.observer.MapObserver 对map的监听接口
* com.zhysunny.pattern.behaviour.observer.listener.{DeleteObserver,InsertObserver,UpdateObserver} 分别对map键值对的删除，添加，更新的触发做监听实现
* com.zhysunny.pattern.behaviour.observer.listener.ListenerMap 继承HashMap，对map键值对的删除，添加，更新设置监听

### 使用场景
* 对配置进行监听，当配置改变时触发监听器


### 观察者模式(一对多)
需求：为实现当获得一个随机数时，打印这个数，并且打印以这个数为个数，*为元素组成的字符串

* com.zhysunny.pattern.behaviour.observer.PrintObserver 打印的观察者接口
* com.zhysunny.pattern.behaviour.observer.observer.{DigitObserver,GraphObserver} 分别实现上面两种功能
* com.zhysunny.pattern.behaviour.observer.AbstractNumberGenerator 存储观察者集合
* com.zhysunny.pattern.behaviour.observer.observer.RandomNumberGenerator 随机数生成器