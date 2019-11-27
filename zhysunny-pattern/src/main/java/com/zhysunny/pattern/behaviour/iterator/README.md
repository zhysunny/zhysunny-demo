## 迭代器模式(iterator)

* com.zhysunny.pattern.behaviour.iterator.bean.Book 书对象，存储书的属性信息 ，需要遍历的元素
* com.zhysunny.pattern.behaviour.iterator.BookShelf 书架接口，可迭代
* com.zhysunny.pattern.behaviour.iterator.bean.{BookShelfArray,BookShelfList,BookShelfVector} 书架对象，实现BookShelf，存储书的集合，需要遍历的集合
* com.zhysunny.pattern.behaviour.iterator.SelfIterator<E> 书架的迭代器(通用)
* com.zhysunny.pattern.behaviour.iterator.Iterable<E> 迭代器接口(通用)

### 使用场景
* 访问一个集合对象的内容而无需暴露它的内部表示
* 为遍历不同集合的结构提供一个统一的接口

### 优点
* 分离了集合对象的遍历行为
* 封装性良好，用户只需要得到迭代器就可以遍历，而对于遍历算法不用关心

### 缺点
* 类的个数成对增加

### 迭代器模式与传统的for循环比较
* 集合默认是数组，如果集合换成List，或者Vector
    * 传统的for循环：此时业务逻辑就需要变动，for循环代码也需要变动
    * 迭代器模式：只需要变更BookShelfArray类，或者通过BookShelf接口的形式增加不同集合的实现类，在Main中直接修改实例即可。