#Spring-Cloud-Practice


##作者想说的

    本项目是我对于Spring-Cloud 的一个尝试，围绕一个B2C的旅游项目展开，包括后台管理、接口两大部分，尽量覆盖到各种场景。

    另外，本项目中包含了一些thymeleaf fragment的用法，小小安利一下:

[点击跳转到代码处](http://git.oschina.net/nightrunner/spring-cloud-practice/tree/master/ui-admin/src/main/resources/templates/fragments)

    最后，我的联系方式690732060@qq.com，欢迎骚扰。

    欢迎评论&拍砖
  
##项目启动步骤

启动前的准备:

1.安装并运行rabbit-mq

2.安装jdk8

3.安装maven 3.x 

4.安装并运行mysql(创建数据库tour,并执行/script/tour.sql:进入到tour数据库后执行`source tour.sql绝对路径`  命令即可)

5.修改数据库密码(password)位置如下:
```
platform-config-server/src/main/resources:
                dict-service.yml
                line-service.yml
                hotel-service.yml
                member-service.yml
                oss-service.yml
                scenery-service.yml
                sms-service.yml
                verify-code-service.yml
                visa-service.yml
```
        



windows项目运行步骤:
```

cd script

call install-jars.bat

cd ..

mvn clean package

start /b java -Xmx256m -jar platform-eureka-server/target/platform-eureka-server-1.0.0-SNAPSHOT.jar >.\log\eureka-server.log &
start /b java -Xmx256m -jar platform-zipkin-server/target/platform-zipkin-server-1.0.0-SNAPSHOT.jar >.\log\zipkin-server.log &
start /b java -Xmx256m -jar platform-admin-dashboard/target/platform-admin-dashboard-1.0.0-SNAPSHOT.jar >.\log\admin-dashboard.log &
start /b java -Xmx256m -jar platform-config-server/target/platform-config-server-1.0.0-SNAPSHOT.jar >.\log\config-server.log &

start /b java -Xmx256m -jar ui-admin/target/admin-ui-1.0.0-SNAPSHOT.jar >.\log\admin.log &
start /b java -Xmx256m -jar api/target/api-1.0.0-SNAPSHOT.jar >.\log\api.log &
start /b java -Xmx256m -jar service-hotel/target/hotel-service-1.0.0-SNAPSHOT.jar >.\log\hotel-service.log &
start /b java -Xmx256m -jar service-dict/target/dict-service-1.0.0-SNAPSHOT.jar  >.\log\dict-service.log &
start /b java -Xmx256m -jar service-line/target/line-service-1.0.0-SNAPSHOT.jar  >.\log\line-service.log &
start /b java -Xmx256m -jar service-scenery/target/scenery-service-1.0.0-SNAPSHOT.jar  >.\log\scenery-service.log &
start /b java -Xmx256m -jar service-member/target/member-service-1.0.0-SNAPSHOT.jar  >.\log\member-service.log &
start /b java -Xmx256m -jar service-visa\target\visa-service-1.0.0-SNAPSHOT.jar  >.\log\visa-service.log &
start /b java -Xmx256m -jar service-verify-code\target\verify-code-service-1.0.0-SNAPSHOT.jar  >.\log\verify-code-service.log &
start /b java -Xmx256m -jar service-sms\target\sms-service-1.0.0-SNAPSHOT.jar  >.\log\sms-service.log &
start /b java -Xmx256m -jar service-oss\target\oss-service-1.0.0-SNAPSHOT.jar  >.\log\oss-service.log &

```

linux
```

cd script

sh ./install-jars.sh

cd ..

mvn clean package

java -Xmx256m -jar platform-eureka-server\target\platform-eureka-server-1.0.0-SNAPSHOT.jar >./log/eureka-server.log &
java -Xmx256m -jar platform-zipkin-server\target\platform-zipkin-server-1.0.0-SNAPSHOT.jar >./log/zipkin-server.log &
java -Xmx256m -jar platform-admin-dashboard\target\platform-admin-dashboard-1.0.0-SNAPSHOT.jar >./log/admin-dashboard.log &
java -Xmx256m -jar platform-config-server\target\platform-config-server-1.0.0-SNAPSHOT.jar >./log/config-server.log &

java -Xmx256m -jar ui-admin\target\admin-ui-1.0.0-SNAPSHOT.jar >./log/admin.log &
java -Xmx256m -jar api\target\api-1.0.0-SNAPSHOT.jar >./log/api.log &
java -Xmx256m -jar service-hotel\target\hotel-service-1.0.0-SNAPSHOT.jar >./log/hotel-service.log &
java -Xmx256m -jar service-dict\target\dict-service-1.0.0-SNAPSHOT.jar  >./log/dict-service.log &
java -Xmx256m -jar service-line\target\line-service-1.0.0-SNAPSHOT.jar  >./log/line-service.log &
java -Xmx256m -jar service-scenery\target\scenery-service-1.0.0-SNAPSHOT.jar  >./log/scenery-service.log &
java -Xmx256m -jar service-member\target\member-service-1.0.0-SNAPSHOT.jar  >./log/member-service.log &
java -Xmx256m -jar service-visa\target\visa-service-1.0.0-SNAPSHOT.jar  >./log/visa-service.log &
java -Xmx256m -jar service-verify-code\target\verify-code-service-1.0.0-SNAPSHOT.jar  >./log/visa-service.log &
java -Xmx256m -jar service-sms\target\sms-service-1.0.0-SNAPSHOT.jar  >./log/sms-service.log &
java -Xmx256m -jar service-oss\target\oss-service-1.0.0-SNAPSHOT.jar  >./log/oss-service.log &

```

