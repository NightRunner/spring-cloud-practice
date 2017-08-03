package org.nr.tour.common.service;

import org.nr.tour.domain.PageImplWrapper;
import org.nr.tour.domain.RoomOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface RoomOrderServiceDefinition {

    @RequestMapping(value = "/findByMemberId", method = RequestMethod.GET)
    PageImplWrapper<RoomOrder> findByMemberId(@RequestParam("memberId") String memberId, @RequestParam("page") Integer page, @RequestParam("size") Integer size);

    @RequestMapping(value = "/createByRoomIdAndMemberId", method = RequestMethod.GET)
    RoomOrder createByRoomIdAndMemberId(@RequestParam("roomId") String ticketId, @RequestParam("memberId") String memberId);

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    RoomOrder getById(@RequestParam("id") String id);

    @RequestMapping(value = "/getBySn", method = RequestMethod.GET)
    RoomOrder getBySn(@RequestParam("sn") String sn);

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    RoomOrder save(@RequestParam("dtoJson") String dtoJson);

    @RequestMapping(value = "/updateGuests", method = RequestMethod.POST)
    Boolean updateGuests(@RequestParam("orderId") String orderId, @RequestParam("guestsJson") String dtoJson);

    @RequestMapping(value = "/findPageByMemberId", method = RequestMethod.GET)
    PageImplWrapper<RoomOrder> findPageByMemberId(@RequestParam("memberId") String memberId,
                                                  @RequestParam("pageNo") Integer pageNo,
                                                  @RequestParam("pageSize") Integer pageSize);

    @RequestMapping(value = "/findPageByMemberIdAndStatues", method = RequestMethod.GET)
    PageImplWrapper<RoomOrder> findPageByMemberIdAndStatues(@RequestParam("memberId") String memberId,
                                                            @RequestParam("statues") Integer[] statues,
                                                            @RequestParam("page") Integer page,
                                                            @RequestParam("size") Integer size);

    @RequestMapping(value = "/findByMemberIdAndStatues", method = RequestMethod.GET)
    List<RoomOrder> findByMemberIdAndStatues(@RequestParam("memberId") String memberId,
                                             @RequestParam("statues") Integer[] statues);
}
