package com.zhysunny.scala.learn

object Functions {
  def main(args: Array[String]): Unit = {
    // 方法


    // 推荐加 =
    def f1(x: Int) = { x*x }
    // 隐藏错误:没有'='它是一个单元返回过程;造成严重破坏
    def f2(x: Int) { x*x }
    println(f1(3))
    println(f2(4))

    // 参数必须加类型，否则报错
    //  def f3(x) = println(x)
    def f4(x: Any) = println(x)
    f4("hello")

    // 类型别名
    type R = Double
    def f5(x: R) = { x*x }
    def f6(x: => R) = { x*x }
    println(f5(3))
    println(f6(4))

    // 匿名函数
//    (x:R) => x*x
    println((1 to 5).map(_*2))
    // 下划线是位置匹配的参数
    println((1 to 5).reduceLeft( _+_ ))

    // 默认2*_
    println((1 to 5).map(2*))
    // 报错
//    println((1 to 5).map(*2))

    // 匿名函数:流水线风格,等价于下面方法的调用
    println((1 to 5) filter {_%2 == 0} map {_*2})
    println((1 to 5).filter(_%2 != 0).map(2*))

    // 匿名函数:要传入多个块
    def compose(g:R=>R, h:R=>R) = (x:R) => g(h(x))
    // g h 都是函数，由下面传入方法体
    val f7 = compose({_*2}, {_-1})
    println(f7(5))

    val zscore = (mean:R, sd:R) => (x:R) => (x-mean)/sd
    val f8 = zscore(5,2)
    println(f8(20))

    def mapmake[T](g:T=>T)(seq: List[T]) = seq.map(g)
    def test(x:Int)={x*x}
    val f9 = mapmake(test _)((1 to 5).toList)
    println(f9)

    // 可变参数
    def sum(args: Int*) = args.reduceLeft(_+_)
    println(sum(1,2,3,4))
  }

}
