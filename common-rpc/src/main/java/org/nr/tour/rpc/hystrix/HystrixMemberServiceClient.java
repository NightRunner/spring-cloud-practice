package org.nr.tour.rpc.hystrix;

import com.google.common.collect.Lists;
import org.nr.tour.domain.Member;
import org.nr.tour.domain.PageImplWrapper;
import org.nr.tour.rpc.MemberServiceClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Service
public class HystrixMemberServiceClient implements MemberServiceClient {

    @Autowired
    private MemberServiceClient memberServiceClient;

    @Override
    @HystrixCommand(fallbackMethod = "deleteFallBackCall")
    public Member deleteById(String id) {
        return memberServiceClient.deleteById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "saveFallBackCall")
    public Member save(String dtoJson) {
        return memberServiceClient.save(dtoJson);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getByIdFallBackCall")
    public Member getById(String id) {
        return memberServiceClient.getById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getPageFallBackCall")
    public PageImplWrapper<Member> getPage(Integer page, Integer size, List<String> sort) {
        return memberServiceClient.getPage(page, size, sort);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getListFallBackCall")
    public List<Member> getList() {
        return memberServiceClient.getList();
    }

    public Member deleteFallBackCall(String id) {
        Member hotel = new Member();
        hotel.setLoginName("FAILED HOTEL SERVICE CALL! - FALLING BACK" + id);
        return hotel;
    }

    public Member getByIdFallBackCall(String id) {
        Member hotel = new Member();
        hotel.setLoginName("FAILED HOTEL SERVICE CALL! - FALLING BACK" + id);
        return hotel;
    }

    public Member saveFallBackCall(String name) {
        Member hotel = new Member();
        hotel.setLoginName("FAILED HOTEL SERVICE CALL! - FALLING BACK" + name);
        return hotel;
    }

    public PageImplWrapper<Member> getPageFallBackCall(Integer page, Integer size, List<String> sort) {
        return new PageImplWrapper<Member>(Lists.<Member>newArrayList());
    }

    public List<Member> getListFallBackCall() {
        return Lists.newArrayList();
    }
}

