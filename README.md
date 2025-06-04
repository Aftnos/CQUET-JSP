# EmarketMall

EmarketMall 是一个基于 JSP 的简易电商示例项目，使用 **Gradle** 构建。项目主要演示 Servlet + JSP + MySQL 的基本开发流程。

## 目录结构

- `src/main/java`：Java 源代码，按功能划分为 `controller`、`service`、`dao`、`entity`、`utils` 等包。
- `src/main/resources`：配置文件，例如数据库连接信息 `db.properties`。
- `src/main/webapp`：JSP 页面及静态资源，包含登录、注册、用户管理等界面。
- `src/test/java`：简单的 JUnit 测试示例。

## 构建与运行

1. 确保本地已安装 JDK 8+ 和 MySQL 数据库。
2. 修改 `src/main/resources/db.properties` 中的数据库配置，与本地环境保持一致。
3. 在项目根目录执行：

   ```bash
   ./gradlew build
   ```

   构建成功后可在 `build/libs` 找到生成的 `war` 包。
4. 将生成的 `war` 部署到支持 Servlet 的应用服务器（如 Tomcat）即可访问。

## 进一步阅读

项目的更多说明与目录解析见 `docs/PROJECT_OVERVIEW.md`，便于新成员快速上手。

