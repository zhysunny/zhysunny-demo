## 责任链模式(chainOfResponsibility)

短信提取流程需要经历四步，关键字过滤，ckm提取，正则提取，字段翻译

com.zhysunny.pattern.behaviour.chainOfResponsibility.Extract 四个步骤的公共接口

com.zhysunny.pattern.behaviour.chainOfResponsibility.AbstractExtract 对Extract接口的封装，主要用于可变责任链

com.zhysunny.pattern.behaviour.chainOfResponsibility.impl.{CkmExtractChain,FilterExtractChain,RegexExtractChain,TranslateExtractChain} 四个步骤的具体实现

com.zhysunny.pattern.behaviour.chainOfResponsibility.mutableChain **可变责任链**

即步骤顺序可变，在业务流程中指定每个步骤的下一个步骤

com.zhysunny.pattern.behaviour.chainOfResponsibility.immutableChain **不可变责任链**

即定义一个步骤顺序固定的类，业务部分直接调用这个类的方法
