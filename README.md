# EmarketMall JSP Application

This project is a Java Server Pages (JSP) web application named **EmarketMall**. The code base uses Gradle for build management and provides a simple e‑commerce example with user management features. It demonstrates how to build a servlet-based application with Spring utilities and a MySQL backend.

## Project Structure

```
CQUET-JSP/
├── build.gradle           # Gradle build script
├── settings.gradle        # Gradle settings
├── libs/                  # Third‑party jar dependencies
├── server/                # MySQL database dump
├── src/
│   ├── main/
│   │   ├── java/          # Java source code
│   │   ├── resources/     # Configuration files (e.g. db.properties)
│   │   └── webapp/        # JSP pages and static assets
│   └── test/java/         # Unit tests
```

### Java Packages

- `org.example.emarketmall.common` – Common base classes, exceptions and helpers.
- `org.example.emarketmall.common.constant` – Application constants and asynchronous logging helpers.
- `org.example.emarketmall.controller` – Web controllers (e.g. `ProductInfoController`).
- `org.example.emarketmall.dao` – DAO interfaces for data access.
- `org.example.emarketmall.dao.impl` – DAO implementations using JDBC helpers.
- `org.example.emarketmall.entity` – Entity classes mapped to database tables.
- `org.example.emarketmall.filter` – Servlet filters.
- `org.example.emarketmall.service` – Business services and login helpers.
- `org.example.emarketmall.utils` – Utility classes such as `SpringUtils`, `DateUtils`, and `Md5Utils`.

The web resources under `src/main/webapp` contain JSP pages for user management along with static CSS/JS assets. The database schema is provided in `server/dump-emarket_mall-*.sql`.

## Building and Running

The project uses Gradle 6.x. The wrapper script `gradlew` downloads the required Gradle distribution. Typical tasks include:

```bash
./gradlew build      # Compile the project
./gradlew test       # Run unit tests (requires JDK 8)
```

Running the application requires a servlet container such as Tomcat. Configure the database connection in `src/main/resources/db.properties` and deploy the generated WAR file under `build/libs` to your container.

## Coding Conventions

- Package names follow standard Java lowercase conventions.
- Entities extend `BaseEntity` which provides common fields like `createBy` and `delFlag`【F:src/main/java/org/example/emarketmall/common/BaseEntity.java†L11-L43】.
- DAOs expose simple CRUD methods. Implementations use helper class `DataAccessImpl` for executing SQL queries【F:src/main/java/org/example/emarketmall/dao/impl/DataAccessImpl.java†L14-L31】.
- Service classes encapsulate business logic (e.g. `UserInfoServiceImpl`) and should not depend on the web layer.
- Constants are centralized under the `constant` package for reuse across the application.

## Extending the Project

1. **Database** – Modify or extend the schema in `server/dump-emarket_mall-*.sql`. Keep entity classes in sync with table definitions.
2. **DAO Layer** – Add new DAO interfaces and implementations for additional tables. Use `IDataAccess` as a base for common queries.
3. **Service Layer** – Implement services that orchestrate DAO operations and include validation or business rules.
4. **Web Layer** – Create new servlets or JSPs under `src/main/webapp` for user interaction. Reuse the existing common utilities and constants.
5. **Testing** – Place JUnit test cases in `src/test/java`. Existing examples demonstrate DAO and service testing【F:src/test/java/org/example/emarketmall/TestDemo.java†L27-L76】.

## License

No license information is provided in the repository. If you plan to distribute or open-source this project, ensure a suitable license file is added.

