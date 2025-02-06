# 项目结构

## 整体架构 
download-video/
├── common/ # 公共模块
│ ├── common-core/ # 核心公共功能
│ │ ├── src/main/java/
│ │ │ └── com/scccy/downloadvideo/common/core/
│ │ │ ├── config/ # 配置类
│ │ │ ├── handler/ # 全局处理器
│ │ │ ├── model/ # 公共模型
│ │ │ └── utils/ # 工具类
│ └── common-download/ # 下载功能公共模块
├── platform-services/ # 平台服务
│ ├── gateway-service/ # 网关服务
│ │ └── src/main/
│ │ ├── java/
│ │ └── resources/
│ └── douyin-service/ # 抖音服务
│ └── src/main/
│ ├── java/
│ │ └── com/scccy/downloadvideo/platform/douyin/
│ │ ├── controller/ # 控制器
│ │ ├── service/ # 服务层
│ │ ├── mapper/ # 数据访问层
│ │ └── model/ # 数据模型
│ └── resources/
│ ├── mapper/ # MyBatis 映射文件
│ └── application.yml # 应用配置
├── logs/ # 日志目录
│ └── douyin-service/
│ ├── debug.log
│ ├── error.log
│ └── warn.log
└── docs/ # 文档目录
├── conversation.md # 开发对话记录
└── roadmap.md # 项目结构图
主要功能模块
1. 网关服务 (gateway-service)
路由管理
统一认证
跨域处理
抖音服务 (douyin-service)
视频下载
直播间签名
配置管理
公共模块 (common)
核心功能 (common-core)
下载工具 (common-download)
技术栈
Spring Boot 3.2.5
Spring Cloud 2023.0.1
Spring Cloud Alibaba 2023.0.1.0
MyBatis-Plus 3.5.10
Knife4j 4.3.0
WebFlux
Log4j2