单例模式：
   - 构造器循环依赖

       创建bean前执行下面这个方法  
       beforeSingletonCreation这个方法的作用是检查bean是否在创建中，如果是抛出异常BeanCurrentlyInCreationException  
       createBeanInstance创建bean实例，如果形成了 A - B - A
       则在beforeSingletonCreation中抛出异常

   - setter循环依赖

       ```java
       // org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean
       // 为了避免后期循环依赖，可以在bean初始化完成前将创建实例的ObjectFactory加入工厂
       addSingletonFactory(beanName,
         // 主要用于解决循环引用问题，对bean再一次依赖引用，主要应用SmartInstantiationAware BeanPostProcessor
         // 其中我们熟知的AOP就是在这里将advice动态织入bean中，若没有则直接返回bean，不做任何处理。
         () -> getEarlyBeanReference(beanName, mbd, bean)
       );
       ```

        setter循环依赖如下图，当出现A - B -
        A的时候getBean（A）就会调用上面的getEarlyBeanReference并不会重新创建bean。参考：
       
        ```java
       org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean ->
       org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(java.lang.String)
        ```


![setter处理循环依赖](/Users/souche/Library/Preferences/IntelliJIdea2019.3/scratches/shns/work/小组学习/setter处理循环依赖.png)

   原型模式：Spring容器不缓存"prototype"作用域的bean，所以无法提前暴露一个创建中的bean，不能解决循环依赖。