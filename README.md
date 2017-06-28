#Spring-Cloud-Practice

项目运行步骤(需先运行rabbit-mq):
```
mvn clean package

java -Xmx512m -jar platform-eureka-server/target/platform-eureka-server-1.0.0-SNAPSHOT.jar >eureka-server.log &
java -Xmx512m -jar platform-zuul/target/platform-zuul-1.0.0-SNAPSHOT.jar  >zuul.log &
java -Xmx512m -jar platform-zipkin-server/target/platform-zipkin-server-1.0.0-SNAPSHOT.jar >zipkin-server.log &
java -Xmx512m -jar platform-admin-dashboard/target/platform-admin-dashboard-1.0.0-SNAPSHOT.jar >admin-dashboard.log &
java -Xmx512m -jar platform-config-server/target/platform-config-server-1.0.0-SNAPSHOT.jar >config-server.log &

java -Xmx512m -jar ui-admin/target/admin-1.0.0-SNAPSHOT.jar >admin.log &
java -Xmx512m -jar service-hotel/target/hotel-service-1.0.0-SNAPSHOT.jar >hotel-service.log &
java -Xmx512m -jar service-dict/target/dict-service-1.0.0-SNAPSHOT.jar  >dict-service.log &
java -Xmx512m -jar service-line/target/line-service-1.0.0-SNAPSHOT.jar  >line-service.log &
java -Xmx512m -jar service-scenery/target/scenery-service-1.0.0-SNAPSHOT.jar  >scenery-service.log &
java -Xmx512m -jar service-member/target/member-service-1.0.0-SNAPSHOT.jar  >member-service.log &

```


