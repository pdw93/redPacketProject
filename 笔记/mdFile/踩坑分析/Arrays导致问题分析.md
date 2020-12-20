## Arrays.asList(T... a) 引出 throw new UnsupportedOperationException()

- 问题描述：

使用java.util.Arrays#asList创建的Collection，在使用java.util.Collection#removeIf 时抛UnsupportedOperationException

```java
List<Integer> list = Arrays.asList(1, 2);
list.removeIf(integer -> 1 == integer);
```



- 问题分析

  Arrays#asList创建的ArrayList是一个内部类，没有复写Collection#removeIf

  <img src="/Users/souche/Library/Application Support/typora-user-images/image-20200520174837476.png" alt="image-20200520174837476" style="zoom:20%;" /><img src="/Users/souche/Library/Application Support/typora-user-images/image-20200520174641293.png" alt="image-20200520174641293" style="zoom:50%;" />

当使用list.removeIf调用的是Collection#removeIf

<img src="/Users/souche/Library/Application Support/typora-user-images/image-20200520175302678.png" alt="image-20200520175302678" style="zoom:50%;" /><img src="/Users/souche/Library/Application Support/typora-user-images/image-20200520175357624.png" alt="image-20200520175357624" style="zoom:40%;" />

java.util.Arrays.ArrayList没有实现java.util.Collection#iterator，所以调用的是父类java.util.AbstractList#iterator

<img src="/Users/souche/Library/Application Support/typora-user-images/image-20200520175714054.png" alt="image-20200520175714054" style="zoom:50%;" /><img src="/Users/souche/Library/Application Support/typora-user-images/image-20200520175858236.png" alt="image-20200520175858236" style="zoom:30%;" />

each.remove()  调用的是java.util.AbstractList.Itr#remove ，而java.util.Arrays.ArrayList并没有覆写父类java.util.AbstractList#remove

<img src="/Users/souche/Library/Application Support/typora-user-images/image-20200520180625320.png" alt="image-20200520180625320" style="zoom:50%;" />

