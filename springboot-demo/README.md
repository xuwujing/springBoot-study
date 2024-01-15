## springBoot-demo

springBoot-demo 是一个SpringBoot的基础框架项目，可以在此项目上进行扩展。

## 开发环境

-  Java 8+
-   Maven 3.5.4+
-    SpringBoot 2.3.12.RELEASE
-  mysql5.7+
-  ant1.9+ (非必须，打包可用)


## 快速开始

**使用**

-  git clone https://github.com/xuwujing/springBoot-study/springBoot-demo.git

**使用**

秉着开箱即用的原则，更改相应的配置(MySql、Es、Redis等地址配置)，运行main方法，即可启动！

**注意**

    EasyCode模板生成的xml若是没有逗号，则需要将全局模版velocityHasNext修改为foreach.hasNext。

## 项目结构

```
springBoot-demo
├── pom.xml
├── README.md
├── build-dev.xml
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── pancm