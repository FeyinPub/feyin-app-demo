feyin-app-demo
=====

### 概述
1、本工程是飞印应用的demo，为开发者与飞印云进行对接样例。

2、本工程基于java8，springboot2.0.1实现。


### 使用说明
1、导入工程到开发工具后，需要修改feyin-app-demo/src/main/resources/application.properties文件，输入相应的参数。

2、使用maven对工程进行编译。

3、详细使用请查看代码中的注析，建议参考飞印官网的api同步观看，如有疑惑请联系客服。

### 代码说明
1、net.feyin.app.demo为demo主代码，仅供参考使用；net.feyin.openapi为飞印开放api的jdk，可直接导入使用

2、作为样例，本工程没有使用数据持久化对用户的数据进行永久性储存，开发者需自行实现该逻辑。

3、FeyinCallbackController为飞印应用的回调地址入口，FeyinIndexController为飞印应用登录地址入口。
FeyinTestController为飞印应用demo测试入口，用户配置好application.properties文件，在本地运行FeyinAppDemoApplication启动应用后，可通过访问http://127.0.0.1:8080/feyin/test/index，测试demo的运行。
注：使用测试时，会刷新飞印的token，如项目已正常上线，谨慎使用