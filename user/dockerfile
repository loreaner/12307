# 第一阶段：构建阶段
FROM maven:3.8.6-jdk-11 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
# 显式指定最终JAR文件名（避免通配符匹配问题）
RUN mvn package -DskipTests && mv /app/target/*.jar /app/target/app.jar

# 第二阶段：运行时阶段
FROM openjdk:11-jre-slim
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime
# 使用确定性的JAR路径
COPY --from=builder /app/target/app.jar /app.jar
RUN addgroup --system appuser && adduser --system --no-create-home --ingroup appuser appuser
USER appuser
EXPOSE 8080
# 修改: 使用 -jar 参数直接运行 JAR 文件
ENTRYPOINT ["java", "-jar", "/app.jar"]