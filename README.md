<h1 align="center">
  fumeng-staging
</h1>

<p align="center">

  <a href="https://spring.io/" rel="nofollow">
  <img src="https://img.shields.io/badge/spring%20boot-2.2.6.RELEASE-green" alt="spring boot" data-canonical-src="https://img.shields.io/badge/spring%20boot-2.2.6.RELEASE-green" style="max-width:100%;">
  </a>
  
  <a href="https://spring.io/" rel="nofollow">
  <img src="https://img.shields.io/badge/jpa-2.2.6-green" alt="jpa" data-canonical-src="https://img.shields.io/badge/jpa-2.2.6-green" style="max-width:100%;">
  </a>
  
  <a href="https://mybatis.plus/" rel="nofollow">
  <img src="https://img.shields.io/badge/license-MIT-lightgrey.svg" alt="LISENCE" data-canonical-src="https://img.shields.io/badge/license-MIT-lightgrey.svg" style="max-width:100%;">
  </a>
  
</p>

<blockquote align="center">
 该项目是我个人为平日开发轻量的 SpringBoot 单体项目而搭建的一个较为简单的脚手架工程, 集成了一些 web 开发中常用的依赖与工具, 喜欢、感兴趣的小伙伴可以点点 star 鸭, 不胜感激~~~
</blockquote>

<p align="center">
  <a href="##简介">简介</a>&nbsp;|&nbsp;
  <a href="##如何使用">如何使用</a> |
  <a href="##致谢">致谢</a>
</p>

## 简介

该项目是我个人为平日开发轻量的 SpringBoot 单体项目而搭建的一个较为简单的脚手架工程, 集成了一些 web 开发中常用的依赖与工具, 喜欢、感兴趣的小伙伴可以点点 star 鸭, 不胜感激~~~

主要集成了以下方面:
- 使用上比较方便简洁的全局异常统一处理
- 使用上比较方便简洁的统一 API 响应结果封装
- 使用上比较方便简洁的分页数据结果统一封装
- 结合以上的全局异常统一处理、统一 API 响应结果封装, 将业务消息码与对应的提示信息抽取到了配置文件中, 避免了在代码中硬编码, 具体使用可看项目中的图书 API
- 一些比较通用的工具类
- logback 日志集成
- Knife4j 集成
- 跨域配置
- 文件上传模块(可扩展的模式, 目前实现了本地上传和七牛云上传, 参考自 [lin-cms-springboot](https://github.com/TaleLin/lin-cms-spring-boot) 实现)

项目搭建时的环境和使用的主要技术如下:
- SpringBoot 2.2.6
- JPA
- MySQL8
- JDK 1.8

## 如何使用

1. 如果你需要指定 MySQL 依赖(`mysql-connector-java`)的版本, 则先进入 pom.xml 文件指定一下 MySQL 依赖的版本。
2. 进入 src/main/resources, 依次做以下事项
    1. 更改 application.yml 中的 knife4j 配置为你自己所需要的配置。
    2. 更改 application-dev.yml 和 application-prod.yml 中的数据源配置、端口号配置和项目自定义配置项为你自己所需要的配置。
3. 进入 src/main/java 中的 cn.xilikeli.staging.extension.file 包, 打开 config.yml 配置你自己的文件上传配置(七牛云或本地的配置), 接着在该包下的 `UploaderConfiguration` 类中切换为相应的文件上传(七牛云或本地)
4. 进入项目根目录下的 profile/sql 目录打开 schema.sql 文件将其中的数据表导入到你的数据库中。
5. 运行项目, 打开浏览器输入本地的 Knife4j 访问地址测试图书相关接口是否正常。
    - 访问地址: `http://localhost:端口号/doc.html`
6. 以上步骤顺利进行之后即可开始愉悦地使用这个目前还比较简便的脚手架工程编写代码了~
7. 项目的内容可通过源码中的注释查阅, 项目中的代码提供了较丰富的注释。
8. 提示: cn.xilikeli.staging.common.interceptor 包中的 .gitkeep 文件为填充文件, 往这个包增加新类时将这个文件删除即可。 

## 致谢

- 本项目的整体思路以及一些模块的设计学习、参考于慕课网的 [七月老师](https://www.imooc.com/t/4294850) 的 [Java全栈工程师课程](https://class.imooc.com/sale/javafullstack) 以及 [七月老师](https://www.imooc.com/t/4294850) 的团队 [林间有风](https://github.com/TaleLin) 中的开源项目 [lin-cms-springboot](https://github.com/TaleLin/lin-cms-spring-boot), 在此致谢!!!
