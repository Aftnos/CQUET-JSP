# EmarketMall 项目说明

## 项目简介
EmarketMall 是一个基于 Java Servlet 与 JSP 的简易商城后台管理系统示例，通过 Gradle 构建。项目提供用户注册、登录及基本的用户管理功能，并预留了商品与订单管理相关页面。代码结构遵循经典 MVC 模式：控制层处理 HTTP 请求，服务层负责业务逻辑，数据访问层与数据库交互。

## 代码结构
```
CQUET-JSP/
├── build.gradle               # Gradle 构建脚本
├── settings.gradle            # Gradle 设置
├── libs/                      # 非 Maven 依赖的第三方 Jar 包
├── server/                    # 数据库脚本
├── src/
│   ├── main/
│   │   ├── java/              # Java 源码
│   │   │   ├── common/        # 通用工具与常量
│   │   │   ├── controller/    # Servlet 控制器
│   │   │   ├── dao/           # DAO 接口及实现
│   │   │   ├── entity/        # 实体类
│   │   │   ├── filter/        # 过滤器
│   │   │   ├── service/       # 业务服务层
│   │   │   └── utils/         # 辅助工具
│   │   ├── resources/
│   │   │   └── db.properties  # 数据库连接配置
│   │   └── webapp/            # JSP 页面及静态资源
│   │       ├── WEB-INF/
│   │       │   ├── web.xml
│   │       │   └── jspf/
│   │       └── static/        # css/js 等静态文件
│   └── test/                  # JUnit 测试
└── README.md
```

### 主要模块说明
- **common**：`BaseServlet` 等公共基类及常量定义。
- **controller**：处理客户端请求，如 `LoginController`、`RegisterController` 等。
- **service**：业务逻辑层，封装登录、注册等功能。
- **dao**：数据访问接口及实现，负责与数据库交互。
- **entity**：POJO 实体类，与数据库表结构对应。
- **filter**：提供 `ServletRequestHolder` 等过滤器工具。
- **utils**：工具类集合，如 `BeanUtils`、`PageUtils`、`Md5Utils` 等。
- **webapp**：JSP 页面、静态资源及 `WEB-INF/web.xml` 配置。
- **server**：包含数据库初始化脚本 `dump-emarket_mall-202506050955.sql`。

## 环境要求
- **JDK**：建议使用 **JDK8**，以避免旧依赖与新版本 JDK 的兼容问题。
- **Gradle**：项目自带 Gradle Wrapper，可直接使用 `./gradlew`。
- **数据库**：MySQL 8.x。`src/main/resources/db.properties` 中的默认配置：

```
driverClassName=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/emarket_mall?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
username=root
password=123456
```
请根据实际环境修改数据库地址、用户名及密码。

## 构建与运行
1. **初始化数据库**：执行下列命令导入示例数据：
   ```bash
   mysql -u<用户名> -p<密码> emarket_mall < server/dump-emarket_mall-202506050955.sql
   ```
2. **编译打包**：在项目根目录执行
   ```bash
   ./gradlew clean build
   ```
   完成后将在 `build/libs/` 目录下生成 `EmarketMall.war`。
3. **部署运行**：将 WAR 包部署到 Tomcat 等 Servlet 容器，或在 IDE 中配置相应的本地服务器运行。
4. **访问入口**：默认欢迎页为 `login.jsp`，成功登录后将跳转至首页 `index.jsp`。

## 测试
执行
```bash
./gradlew test
```
可运行 JUnit 用例。若使用高版本 JDK 遇到 `Groovy` 相关错误，可将环境降至 JDK8 再尝试执行。

## 开发规范建议
- 按包名划分不同层次，保持控制层、服务层、数据访问层各司其职。
- DAO 方法命名保持统一，如 `selectXxx`、`insertXxx`、`updateXxx`、`deleteXxx`。
- 业务逻辑集中在 Service 层，Controller 仅负责参数解析与结果返回。
- JSP 页面放在 `src/main/webapp` 下，可使用 `<jsp:include>` 复用公共片段。
- 如需修改数据库配置或依赖版本，请同步更新 `db.properties` 与 `libs/` 中的 Jar。

## 结语
该项目作为学习示例，适合熟悉传统 JSP/Servlet 应用结构。在此基础上可继续扩展商品管理、购物车及订单处理等功能，或逐步迁移至 Spring Boot 等现代框架。欢迎在使用过程中提出改进建议。
