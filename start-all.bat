@echo off

REM 启动脚本 - 考试系统所有服务
REM 此脚本会依次启动所有后端服务和前端应用

REM 设置JAVA_HOME环境变量
SET JAVA_HOME=C:\Program Files\Java\jdk-23
SET PATH=%JAVA_HOME%\bin;%PATH%

REM 设置JVM参数，允许访问内部API
SET JVM_ARGS=--add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED

echo 正在启动考试系统服务...
echo 1. 启动Eureka服务注册中心...
start "Eureka服务" cmd /k "cd /d %~dp0module-eureka && mvn spring-boot:run -Dspring-boot.run.jvmArguments="%JVM_ARGS%""

echo 等待5秒后启动认证授权服务...
timeout /t 5 /nobreak >nul

echo 2. 启动认证授权服务...
start "认证授权服务" cmd /k "cd /d %~dp0module-auth && mvn spring-boot:run -Dspring-boot.run.jvmArguments="%JVM_ARGS%""

echo 等待5秒后启动用户管理服务...
timeout /t 5 /nobreak >nul

echo 3. 启动用户管理服务...
start "用户管理服务" cmd /k "cd /d %~dp0module-user && mvn spring-boot:run -Dspring-boot.run.jvmArguments="%JVM_ARGS%""

echo 等待5秒后启动基础信息服务...
timeout /t 5 /nobreak >nul

echo 4. 启动基础信息服务...
start "基础信息服务" cmd /k "cd /d %~dp0module-baseinfo && mvn spring-boot:run -Dspring-boot.run.jvmArguments="%JVM_ARGS%""

echo 等待5秒后启动试卷管理服务...
timeout /t 5 /nobreak >nul

echo 5. 启动试卷管理服务...
start "试卷管理服务" cmd /k "cd /d %~dp0module-paper && mvn spring-boot:run -Dspring-boot.run.jvmArguments="%JVM_ARGS%""

echo 等待5秒后启动考试管理服务...
timeout /t 5 /nobreak >nul

echo 6. 启动考试管理服务...
start "考试管理服务" cmd /k "cd /d %~dp0module-exam && mvn spring-boot:run -Dspring-boot.run.jvmArguments="%JVM_ARGS%""

echo 等待5秒后启动API网关服务...
timeout /t 5 /nobreak >nul

echo 7. 启动API网关服务...
start "API网关服务" cmd /k "cd /d %~dp0module-gateway && mvn spring-boot:run -Dspring-boot.run.jvmArguments="%JVM_ARGS%""

echo 等待10秒后启动前端应用...
timeout /t 10 /nobreak >nul

echo 8. 启动前端Vue应用...
start "前端应用" cmd /k "cd /d %~dp0..\exam-vue && npm install && npm run dev"

echo 所有服务启动完成！
echo 请检查各个终端窗口，确保所有服务正常运行。
echo 前端应用访问地址: http://localhost:8080
echo Eureka服务访问地址: http://localhost:8762

echo 按任意键退出...
pause >nul
