# 项目说明

本文档对 EmarketMall 项目进行简要介绍，供新成员参考。

## 功能概览

- **用户管理**：登录、注册及用户信息的增删改查。
- **异步日志**：通过 `AsyncManager` 及 `AsyncFactory` 记录登录行为。
- **工具类**：封装了字符串、日期、Servlet 相关的常用操作。
- **页面展示**：JSP 配合 Bootstrap-table 实现基本的前端界面。

## 代码结构

```
src/main/java/org/example/emarketmall
├── common      # 基础类和公共组件
├── controller  # Servlet 控制层
├── dao         # 数据访问接口及实现
├── entity      # 实体类
├── service     # 业务逻辑处理
├── utils       # 工具包
└── filter      # 过滤器等
```

数据库连接配置位于 `src/main/resources/db.properties`，请根据实际环境调整。

## 学习建议

1. 从 `controller` 层入手，结合 JSP 页面理解请求与响应流程。
2. 阅读 `service` 与 `dao` 的实现，掌握数据库操作方式。
3. 熟悉 `utils` 包中的工具类，这些在项目中被频繁使用。
4. 构建项目时使用 `./gradlew build`，如需执行测试可运行 `./gradlew test`（环境需预先配置好）。

