[TOC]

## [参考博客](https://www.cnblogs.com/hiver/p/7850954.html)

## [maven跳过单元测试-maven.test.skip和skipTests的区别](https://www.cnblogs.com/javabg/p/8026881.html)

-DskipTests，不执行测试用例，但编译测试用例类生成相应的class文件至target/test-classes下。

-Dmaven.test.skip=true，不执行测试用例，也不编译测试用例类。



## maven打包参数

| 参数 | 全称                   | 说明                                                         |
| ---- | ---------------------- | :----------------------------------------------------------- |
| -pl  | --projects             | 选项后可跟随{groupId}:{artifactId}或者所选模块的相对路径(多个模块以逗号分隔) |
| -am  | --also-make            | 表示同时处理选定模块所依赖的模块                             |
| -amd | --also-make-dependents | 表示同时处理依赖选定模块的模块                               |
| -N   | --Non-recursive        | 表示不递归子模块                                             |
| -rf  | --resume-from          | 表示从指定模块开始继续处理                                   |

