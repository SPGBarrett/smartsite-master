# 开发文档-智慧工地大屏模块

## 模块需求总述

可以通过模板选择设定工地大屏显示的内容，针对每个模板都可以设置相应的参数。工地LED大屏的参数如下：

* LED大屏尺寸：5.76m x 3.2m

* LED大屏长宽比： 16:9

* LED大屏配套显示器分辨率：1280 x 720



## 模块功能介绍

模块基本功能如下：

* 同步功能：即直接通过复制电脑屏幕的方式，或者扩展电脑屏幕的方式进行显示，大屏显示的内容为电脑当前屏幕显示的内容，或者电脑扩展屏幕显示的内容。
* 定时播放功能：通过时间段设定，实现不同时间播放不同内容的功能。

模块实现的显示页面有：

* 欢迎词显示
* 文字显示
* 多媒体图片及视频轮播
* 监控视频显示
* 工地一张图展示
* 工地首页展示
* 数据展板页面
* BIM模型图片及视频显示
* 门禁打卡实时推送显示
* 预警信息显示



## 模块环境依赖

### Web服务器

* IIS或者Nginx

### 数据库

* MySQL

### 运行环境

* JDK 1.8 64bit

### 其他

* Nginx：反向代理
* Nginx RTMP模块：RTMP流服务
* ffmpeg：视频解码及编码



## 技术依赖

### 前端

* npm：包管理工具，代码构建工具
* cnpm：npm的国内taobao镜像
* Bower：包管理工具
* Node.js：包管理
* jQuery：前端构建框架
* Vue.js：前端构建框架
* Axios.js：Vue的Ajax功能框架
* bootstrap：UI框架
* element-ui：Vue的UI框架
* font-awesome： 字体框架
* dropzone.js：文件上传控件
* file-upload.js：文件上传控件
* video.js： RTMP视频播放框架
* m3u8-parser： m3u8文件解码
* xlxs.js： 输入输出xlxs表格文件

### 后端

* Spring Cloud 框架及组件
* Spring Boot 框架及组件
* fastjson：json操作工具
* pagehelper：数据库分页工具
* SwaggerUI：网页API工具
* commons-codec：base64解码编码
* Netty：websocket实现
* log4j：log工具
* slf4j： log工具
* mybatis： 持久层映射框架



## 代码结构

### 前端

SmartSiteGeneral

|—— SmartSitePatrol ：隐患模块前端页面文件夹

|—— SmartSiteRecord ：隐患录入功能前端页面文件夹

|—— gongdi：

​	|—— aboutNews.html：文字信息显示页面

​	|—— carNews.html：车辆信息显示页面

​	|—— dataShow.html：数据展板显示页面

​	|—— livebroadcast.html：多媒体图片及视频播放页面

​	|—— model-display.html：BIM模型展示页面

​	|—— videoShow.html：监控视频展示页面

​	|—— welcome.html：欢迎词页面

|—— data-params.html：设置文字显示页面参数的页面

|—— **index.html**：大屏模板选择主页

|—— index-dispatcher-with-popper.html：提供弹出工人刷脸信息的大屏调度页面

|—— index-map-display.html：LED大屏位置地图展示页面

|—— index-monitor-room.html：监控中心大屏配置页面（预留）

|—— index-screen-dispatcher.html：大屏页面展示调度页面

|—— index-small-screen.html：LED小屏幕配置页面（预留）

|—— index-warning.html：预警信息弹框页面

|—— map-params.html：设置工地一张图参数的页面（暂时禁用）

|—— monitor-params.html：设置监控视频参数的页面（暂时禁用）

|—— multi-params.html：设置多媒体播放参数的页面

|—— multi-play.html：RTMP视频播放页面

|—— welcome-params.html：设置欢迎大屏参数的页面



### 后端

smartsite-master

|—— eureka-service： 微服务注册中心组件

|—— zuul-gateway： 微服务网关组件

|—— config-server： 微服务配置中心组件

|—— websocket-service： websocket组件

|—— ai-alert-service： AI预警模块后端

|—— face-detect-service：人脸识别门禁、考勤后端

|—— patrolmodule-service：巡检隐患配置台账后端

|—— **smartsite-service：** 大屏模块后端





## 模块部署流程

### 1. 环境安装

为系统安装MySQL，JDK，IIS与Nginx。

### 2. 数据库建表

使用Navicat或者MySQL WorkBench创建如下数据库：

```sql
site_screen_params
smart_site_patrol
smartsite_ai_root
```

数据库字符集选择**utf8**，排序规则选择**utf8_general_ci** ，然后各个数据库分别运行预先导出的对应的sql文件，自动建立数据库表结构并导入一些初始数据。

### 3. 新建文件夹

在系统**E盘**根目录下，新建名为**uploadFiles**的文件夹，用于存放上传的多媒体文件。

### 4. 依赖项拷贝

在系统**E盘**根目录下，新建名为**smartSite**的文件夹，并将**ffmpeg工具**文件夹到**smartSite**文件夹中。

### 5. 反向代理配置

反向代理采用Nginx实现，需要在Nginx的配置文件中设置对代理规则。Nginx.conf文件中，加入如下配置：

```xml

```

### 6. IIS发布网页并配置虚拟目录

* 新建网页，物理路径为SmartSiteGeneral文件夹的路径，网页端口号为2200
* 对该网页新建名为spc-screen的虚拟目录，

### 7. 后端服务启动

双击预先做好的.bat文件，即可启动所有jar包。也可通过cmd，定位到jar所在文件夹，用命令的方式逐一启动jar包，命令如下：

```cmd
java -jar smartsite-service.jar
```

启动jar的顺序为：eureka-service注册中心->zuul-gateway网关->其他服务



## 接口说明

打开浏览器，输入http://localhost:12001/swagger-ui.html查看各个模块详细的接口说明文档。



## 待优化项目

* 提升网页端监听Websocket消息的稳定性
* 后端Websocket功能剥离
* 大屏模块加入鉴权功能
* 响应式
* 门禁打卡信息实时推送新增信息种类
* 党建家功能模块实现
* 超图代码吸收及重构
* 多个视频轮播功能







