[TOC]



# 基础

## java8新特性

- 声明函数式接口

```java
/**
接口有且仅有一个抽象方法
 * 允许定义静态方法
 * 允许定义默认方法
 * 允许java.lang.Object中的public方法
 * 该注解不是必须的，如果一个接口符合"函数式接口"定义，那么加不加该注解都没有影响。加上该注解能够更好地让编译器进行检查。如果编写的不是函数式接口，但是加上了@FunctionInterface，那么编译器会报错
 *
 */
@FunctionalInterface
public interface Test {
  void testMethod(int a, int b);
  // 通过实现类调用
  default int defaultMethod(int a, int b) {
     return a + b;
  }
  // 静态方法通过接口调用
  static int staticMethod(int a, int b) {
    return a + b;
  }
}
```

- lambda表达式使用局部变量

  在Lambda表达式中捕获局部变量时，会将变量当成final的，在Lambda表达式中不能修改那些捕获的变量。

- 方法引用 ::

  被引用方法的<u>**参数列表和返回值类型**必须</u>与函数式接口方法参数列表和方法返回值类型一致。

- 

  