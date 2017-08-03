package org.nr.tour.api.service;

import org.nr.tour.api.vo.*;
import org.nr.tour.domain.*;

import java.util.Collection;
import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public interface VoBuilderService {

    LineItemVO buildItemVO(Line line);

    HotelItemVO buildItemVO(Hotel hotel);

    SceneryItemVO buildItemVO(Scenery scenery);

    LineSearchItemVO buildSearchItemVO(Line line);

    <T> PageVO<T> buildPage(PageImplWrapper<T> pageImplWrapper);

    <T> PageVO<T> buildPage(Collection<T> content, Boolean hasMore);

    HotelDetailVO buildDetailVO(Hotel hotel);

    SceneryDetailVO buildDetailVO(Scenery scenery);

    LineDetailVO buildDetailVO(Line line);

    LineOrderInfoVO buildVO(LineOrder lineOrder, List<Insurance> insurances);

    MemberIndexVO buildMemberIndexVO(Member member);

    MemberIndexVO buildEmptyMemberIndexVO();

    TicketOrderInfoVO buildVO(TicketOrder ticketOrder);

    TicketOrderDetailVO buildOrderDetail(TicketOrder ticketOrder);

    RoomOrderDetailVO buildOrderDetail(RoomOrder roomOrder);

    RoomOrderInfoVO buildVO(RoomOrder roomOrder);

    LineOrderDetailVO buildOrderDetail(LineOrder lineOrder);

}
