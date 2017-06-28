package org.nr.platform;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class AdminDashBoardApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminDashBoardApplication.class, args);
    }
}
