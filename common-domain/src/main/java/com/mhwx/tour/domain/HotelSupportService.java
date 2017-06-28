package org.nr.tour.domain;

import javax.persistence.*;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Entity
@Table(name = "hotel_support_service")
public class HotelSupportService extends IDEntity {

    @JoinColumn(name = "service_id")
    @ManyToOne
    private SupportService service;

    @JoinColumn(name = "hotel_id")
    @ManyToOne
    private Hotel hotel;

    public SupportService getService() {
        return service;
    }

    public void setService(SupportService service) {
        this.service = service;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
