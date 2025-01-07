# study_zero_backend
#2021后端技术学习  

| 目标             | 完成度 | 
|----------------|-----|
| 菜单展示           | √   |
  | 角色             | √   |
  | 权限             | √   |
  | 网关             | ×   |
  | 登录             | √   |
  | redis存储        | √   |
  | nacos配置        | ×   |
  | mybatis-plus整改 | ×   |
  | 限流             | ×   |
  | 消息队列           | ×   |
  

nginx https://blog.csdn.net/weixin_50003028/article/details/132567183
华为云安全组 https://zhuanlan.zhihu.com/p/657913148

# 微服务项目架构说明

## 项目简介
这是一个基于微服务架构的项目，采用 Spring Cloud 技术栈实现，包含用户服务、订单服务、商品服务等核心功能模块。

## 核心服务

### 1. 用户服务 (User Service)
主要功能：
- 用户注册登录
- 用户信息管理
- 权限认证

### 2. 订单服务 (Order Service)
主要功能：
- 订单创建和管理
- 订单状态追踪
- 支付集成

### 3. 商品服务 (Product Service)
主要功能：
- 商品信息管理
- 库存管理
- 价格管理

## 技术栈

### 核心组件
- **Spring Cloud**: 微服务框架
- **Nacos**: 服务注册与配置中心
- **Gateway**: API 网关
- **OpenFeign**: 服务间通信
- **Sentinel**: 服务熔断与限流
- **Seata**: 分布式事务
- **MySQL**: 数据存储
- **Redis**: 缓存
- **RabbitMQ**: 消息队列

### 项目结构
```
project-root/
├── services/
│   ├── user-service/
│   ├── order-service/
│   ├── product-service/
│   └── gateway-service/
├── common/
│   ├── common-util/
│   └── common-security/
└── docker/
    └── docker-compose.yml
```

## 主要特点

### 1. 高可用性
- 服务注册与发现
- 负载均衡
- 熔断降级

### 2. 可扩展性
- 水平扩展
- 模块化设计
- 服务解耦

### 3. 安全性
- JWT 认证
- 权限控制
- 数据加密

### 4. 可监控性
- 链路追踪
- 性能监控
- 日志收集

## 部署架构
- 使用 Docker 容器化部署
- Kubernetes 集群管理
- CI/CD 自动化部署流程

## 开发环境要求
- JDK 11+
- Maven 3.6+
- Docker
- MySQL 8.0+
- Redis 6.0+

## 快速开始
1. 克隆项目
2. 配置环境
3. 启动服务

详细的部署和开发指南请参考各服务目录下的文档。