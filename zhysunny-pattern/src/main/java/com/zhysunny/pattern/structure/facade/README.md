## 外观模式(facade)

外观模式是为了解决类与类之家的依赖关系的，像 spring 一样，可以将类和类之间的关系配置到配置文件中，而外观模式就是将他们的关系放在一个 Facade 类中，降低了类类之间的耦合度，该模式中没有涉及到接口