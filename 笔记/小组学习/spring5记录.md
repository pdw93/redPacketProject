# FacoryBean

- 想要获取Spring容器中FactoryBean类型的bean时需要在beanName前➕&,不加获取的是FactoryBean.getObject()返回的对象

  ```java
  context.getBean("&beanName")
  ```



- 设置自定义属性编辑器

    1、使用自定义属性编辑器，通过继承 PropertyEditorSupport，重写 setAsText方法
    2、配置到CustomEditorConfigurer属性customEditors中
