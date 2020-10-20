# inspire-demo
日常代码练习demo

### <a href="https://github.com/GenshenWang/inspire-demo/tree/master/Ch1_ExecutorChainPattern">Ch1:职责链设计模式的应用</a>
### <a href="https://github.com/GenshenWang/inspire-demo/tree/master/Ch2_SmartApplicationListener">Ch2:Spring SmartApplicationListener事件发布与监听代码实战</a>
### <a href="https://github.com/GenshenWang/inspire-demo/tree/master/Ch3_InterfaceAuth">Ch3:接口鉴权实现(应用DDD领域建模代码实战)</a>
### <a href="https://github.com/GenshenWang/inspire-demo/tree/master/Ch4_CodeDesignStyle">Ch4:设计模式代码实现</a>
### <a href="https://github.com/GenshenWang/inspire-demo/tree/master/Ch5_KafkaDemoLearning">Ch5-Kafka消息发送与消费模型代码示例)</a>
### <a href="https://github.com/GenshenWang/inspire-demo/tree/master/Ch6_AlgorithmsExe">Ch6-数据结构与算法</a>
* <a href="https://github.com/GenshenWang/inspire-demo/tree/master/Ch6_AlgorithmsExe/src/main/java/com/wgs/algorithms/%E6%A0%88">栈的实现</a>
  - (1)栈的两种实现: 数组和链表
  - (2)栈的编程练习题: 使用栈实现表达式计算、
  -    《剑指offer》获取栈的最小数(双栈使用)
* <a href="https://github.com/GenshenWang/inspire-demo/tree/master/Ch6_AlgorithmsExe/src/main/java/com/wgs/algorithms/%E9%98%9F%E5%88%97">队列的实现</a>
  - (1)队列的两种实现: 数组和链表
  - (2)循环队列实现
  - (3)阻塞队列实现
  - (4)《剑指offer》用两个栈实现队列
### <a href="https://github.com/GenshenWang/inspire-demo/tree/master/Ch7_SentinelExe">Ch7-单机版Http接口限流框架实现</a>
* V1版本:
    - (1)限流规则配置: 暂时只支持Yaml配置;
    - (2)限流算法:只支持固定时间窗口算法;
    - (3)限流模式:只支持单机模式;
    - (4)接口类型:只支持Http接口;

* V2版本:
    - (1) 优化RateLimitAlg,提供抽象限流算法接口;
    - (2) 优化RateLimitRule, 提供抽象规则查找;
    - (3) 限流规则读取优化;
    
### <a href="https://github.com/GenshenWang/inspire-demo/tree/master/Ch10_ParrotExe/src/main/java/com/wgs/parrot">Ch10-项目代码实战</a>
* 延时队列 - 时间轮实现
    - (1)<a href="https://github.com/GenshenWang/inspire-demo/tree/master/Ch10_ParrotExe/src/main/java/com/wgs/parrot/%E5%BB%B6%E6%97%B6%E6%B6%88%E6%81%AF/%E6%97%B6%E9%97%B4%E8%BD%AE">代码链接</a>
    - (2)支持添加任务, 支持与Spring结合, 支持实时查看任务执行情况
    - (3)时间轮算法应用：监测多用户TCP是否在线
*  分布式锁 - Spring Data Redis分布式锁实现
    - (1)<a href="https://github.com/GenshenWang/inspire-demo/tree/master/Ch10_ParrotExe/src/main/java/com/wgs/parrot/distributed_lock">代码链接</a>
    - (2)Redis分布式锁实现，本地锁 + Redis
    - (3)测试用例模拟秒杀场景   

