## 简单工厂模式(simpleFactory)
缺点：类的创建依赖工厂类，也就是说，如果想要拓展程序，必须对工厂类进行修改，这违背了闭包原则，所以，从设计角度考虑，有一定的问题

分为三种：

com.admin.other.simpleFactory.general.Sender 公共接口

com.admin.other.simpleFactory.general.impl.{EMLSender,MEMSender,MMSSender} 实现类，由工厂创建

### 普通简单工厂(general)
com.admin.other.simpleFactory.general.SenderFactory 普通简单工厂

com.admin.other.simpleFactory.general.reflect.SenderFactory 基于反射创建的普通工厂

缺点：创建工厂需要传字符串，传参出错工厂创建就会出错

### 多方法简单工厂
com.admin.other.simpleFactory.moreMethod.SenderFactory 多方法简单工厂

缺点：每个实现类都需要写一个对应的方法

### 静态方法简单工厂
com.admin.other.simpleFactory.staticFactory.SenderFactory 静态方法简单工厂

不需要实例化工厂对象

