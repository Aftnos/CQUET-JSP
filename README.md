# EmarketMall JSP 应用

**EmarketMall** 是一个基于 Java Server Pages (JSP) 的示例电商项目，代码中集成了 Spring 工具类、MySQL 数据库以及常见的工具库，适合初学者学习 JSP 与 Servlet 开发流程。

## 项目目录结构

```
CQUET-JSP/
├── build.gradle           # Gradle 构建脚本
├── settings.gradle        # Gradle 设置
├── libs/                  # 依赖的第三方 JAR 包
├── server/                # MySQL 数据库初始化脚本
├── src/
│   ├── main/
│   │   ├── java/          # Java 源代码
│   │   ├── resources/     # 配置文件，如 db.properties
│   │   └── webapp/        # JSP 页面及静态资源
│   └── test/java/         # 单元测试
```

### 主要 Java 包

- `org.example.emarketmall.common` – 提供通用基类和异常处理等工具。
- `org.example.emarketmall.common.constant` – 定义常量和异步日志工具。
- `org.example.emarketmall.controller` – Web 层控制器，例如用户登录 `LoginController`【F:src/main/java/org/example/emarketmall/controller/user/LoginController.java†L27-L62】。
- `org.example.emarketmall.dao` 与 `dao.impl` – 数据访问接口及其实现，使用 `DataAccessImpl` 执行通用 SQL【F:src/main/java/org/example/emarketmall/dao/impl/DataAccessImpl.java†L14-L36】。
- `org.example.emarketmall.entity` – 与数据库表对应的实体类，例如所有实体都继承自 `BaseEntity`，包含常见字段【F:src/main/java/org/example/emarketmall/common/BaseEntity.java†L15-L36】。
- `org.example.emarketmall.service` – 业务逻辑层，封装 DAO 操作。
- `org.example.emarketmall.utils` – 如 `ServletUtils`、`DateUtils` 等工具类。

`src/main/webapp` 下包含用户登录、注册等 JSP 页面，数据库结构示例位于 `server/dump-emarket_mall-*.sql`。

## 构建与运行

1. 本项目基于 Gradle，使用 `./gradlew` 可自动下载所需的 Gradle 版本。
2. 运行 `./gradlew build` 完成编译，`./gradlew test` 执行单元测试（需要 JDK8）。
3. 数据库配置位于 `src/main/resources/db.properties`，修改其中的连接地址和账号后可连接本地 MySQL【F:src/main/resources/db.properties†L1-L8】。
4. 构建完成后会生成 `build/libs` 下的 WAR 包，可部署到 Tomcat 等 Servlet 容器。

## 开发指南

- 首次克隆项目后，可导入至 IntelliJ IDEA 或 Eclipse，选择以 Gradle 项目打开即可。
- 确保本地已启动 MySQL，并按 `server` 目录中的 SQL 脚本初始化数据库。
- 代码遵循 Java 包小写命名，业务实体统一继承 `BaseEntity`，可在其中看到通用字段与 `getUUID` 方法的实现【F:src/main/java/org/example/emarketmall/common/BaseEntity.java†L15-L45】。
- `TestDemo` 类提供了多个示例测试用例，演示 DAO 与工具类的使用方式【F:src/test/java/org/example/emarketmall/TestDemo.java†L27-L165】。

## 许可

仓库未包含开源许可证，如需发布请自行补充适当的 LICENSE 文件。
