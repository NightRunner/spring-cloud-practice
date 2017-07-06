package org.nr.tour.rpc.hystrix;

import org.nr.tour.rpc.VerifyCodeServiceClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Service
public class HystrixVerifyCodeServiceClient implements VerifyCodeServiceClient {

    @Autowired
    private VerifyCodeServiceClient verifyCodeServiceClient;

    public Boolean verifyFallBackCall(String owner, String code, Integer type) {
        return Boolean.FALSE;
    }

    public String newOneFallBackCall(String owner, Integer type, Integer expireMills) {
        return "000000";
    }

    @Override
    @HystrixCommand(fallbackMethod = "newOneFallBackCall")
    public String newOne(String owner, Integer type, Integer expireMills) {
        return verifyCodeServiceClient.newOne(owner, type, expireMills);
    }

    @Override
    @HystrixCommand(fallbackMethod = "verifyFallBackCall")
    public Boolean verify(String owner, String code, Integer type) {
        return verifyCodeServiceClient.verify(owner, code, type);
    }
}

