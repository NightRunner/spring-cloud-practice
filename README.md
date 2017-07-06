#Spring-Cloud-Practice

启动前的准备:
1.安装并运行mysql(创建数据库tour,并执行tour.sql:进入到tour数据库后执行`source tour.sql绝对路径`  命令即可)
2.安装并运行rabbit-mq
3.安装jdk8
4.安装maven 3.x (在项目根目录运行命令: `mvn clean package`)

linux项目运行步骤:
```
mvn clean package

java -Xmx256m -jar platform-eureka-server/target/platform-eureka-server-1.0.0-SNAPSHOT.jar >eureka-server.log &
java -Xmx256m -jar platform-zuul/target/platform-zuul-1.0.0-SNAPSHOT.jar  >zuul.log &
java -Xmx256m -jar platform-zipkin-server/target/platform-zipkin-server-1.0.0-SNAPSHOT.jar >zipkin-server.log &
java -Xmx256m -jar platform-admin-dashboard/target/platform-admin-dashboard-1.0.0-SNAPSHOT.jar >admin-dashboard.log &
java -Xmx256m -jar platform-config-server/target/platform-config-server-1.0.0-SNAPSHOT.jar >config-server.log &

java -Xmx256m -jar ui-admin/target/admin-1.0.0-SNAPSHOT.jar >admin.log &
java -Xmx256m -jar service-hotel/target/hotel-service-1.0.0-SNAPSHOT.jar >hotel-service.log &
java -Xmx256m -jar service-dict/target/dict-service-1.0.0-SNAPSHOT.jar  >dict-service.log &
java -Xmx256m -jar service-line/target/line-service-1.0.0-SNAPSHOT.jar  >line-service.log &
java -Xmx256m -jar service-scenery/target/scenery-service-1.0.0-SNAPSHOT.jar  >scenery-service.log &
java -Xmx256m -jar service-member/target/member-service-1.0.0-SNAPSHOT.jar  >member-service.log &

```

windows
```
mvn clean package

java -Xmx256m -jar platform-eureka-server\target\platform-eureka-server-1.0.0-SNAPSHOT.jar >eureka-server.log &
java -Xmx256m -jar platform-zuul\target\platform-zuul-1.0.0-SNAPSHOT.jar  >zuul.log &
java -Xmx256m -jar platform-zipkin-server\target\platform-zipkin-server-1.0.0-SNAPSHOT.jar >zipkin-server.log &
java -Xmx256m -jar platform-admin-dashboard\target\platform-admin-dashboard-1.0.0-SNAPSHOT.jar >admin-dashboard.log &
java -Xmx256m -jar platform-config-server\target\platform-config-server-1.0.0-SNAPSHOT.jar >config-server.log &

java -Xmx256m -jar ui-admin\target\admin-1.0.0-SNAPSHOT.jar >admin.log &
java -Xmx256m -jar service-hotel\target\hotel-service-1.0.0-SNAPSHOT.jar >hotel-service.log &
java -Xmx256m -jar service-dict\target\dict-service-1.0.0-SNAPSHOT.jar  >dict-service.log &
java -Xmx256m -jar service-line\target\line-service-1.0.0-SNAPSHOT.jar  >line-service.log &
java -Xmx256m -jar service-scenery\target\scenery-service-1.0.0-SNAPSHOT.jar  >scenery-service.log &
java -Xmx256m -jar service-member\target\member-service-1.0.0-SNAPSHOT.jar  >member-service.log &
java -Xmx256m -jar service-visa\target\visa-service-1.0.0-SNAPSHOT.jar  >visa-service.log &
java -Xmx256m -jar service-verify-code\target\verify-code-service-1.0.0-SNAPSHOT.jar  >visa-service.log &
java -Xmx256m -jar service-sms\target\sms-service-1.0.0-SNAPSHOT.jar  >sms-service.log &

```

