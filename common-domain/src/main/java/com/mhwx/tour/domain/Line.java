package org.nr.tour.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Entity
@Table(name = "line")
public class Line extends IDEntity {

    @Column(name = "name")
    private String name;
    /**
     * 副标题
     */
    @Column(name = "subtitle")
    private String subtitle;

    /**
     * 线路编号
     */
    @Column(name = "code")
    private String code;

    /**
     * 订单处理方式
     */
    @Column(name = "order_process_type")
    private int orderProcessType;

    /**
     * 起步价
     */
    @Column(name = "starting_price")
    private Double startingPrice;

    /**
     * 门市价
     */
    @Column(name = "market_price")
    private Double marketPrice;

    /**
     * 最高可获得积分
     */
    @Column(name = "highest_score")
    private Integer highestScore;

    /**
     * 建议提前报名天数
     */
    @Column(name = "recommended_days_of_advance")
    private Integer recommendedDaysOfAdvance;

    /**
     * 简要描述
     */
    @Column(name = "introduction", length = 1024)
    private String introduction;

    /**
     * 简要描述
     */
    @Column(name = "brief_description", length = 1024)
    private String briefDescription;

    /**
     * 往返交通
     */
    @Column(name = "round_trip_traffic")
    private String roundTripTraffic;

    /**
     * 支付方式, 1:百分比  2:定金
     */
    @Column(name = "pay_type")
    private Integer payType;

    /**
     * 百分比 or 定金数
     */
    @Column(name = "pay_amount")
    private Double payAmount;

    /**
     * 最多可使用积分
     */
    @Column(name = "max_use_score")
    private Integer maxUseScore;

    /**
     * 特色介绍
     */
    @Column(name = "featured_introduction", length = 1024)
    private String featuredIntroduction;

    /**
     * 预定须知
     */
    @Column(name = "booking_information", length = 1024)
    private String bookingInformation;


    /**
     * 温馨提示
     */
    @Column(name = "tips", length = 1024)
    private String tips;

    /**
     * 预订流程
     */
    @Column(name = "booking_process", length = 1024)
    private String bookingProcess;

    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getOrderProcessType() {
        return orderProcessType;
    }

    public void setOrderProcessType(int orderProcessType) {
        this.orderProcessType = orderProcessType;
    }

    public Double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(Double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(Integer highestScore) {
        this.highestScore = highestScore;
    }

    public Integer getRecommendedDaysOfAdvance() {
        return recommendedDaysOfAdvance;
    }

    public void setRecommendedDaysOfAdvance(Integer recommendedDaysOfAdvance) {
        this.recommendedDaysOfAdvance = recommendedDaysOfAdvance;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public String getRoundTripTraffic() {
        return roundTripTraffic;
    }

    public void setRoundTripTraffic(String roundTripTraffic) {
        this.roundTripTraffic = roundTripTraffic;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getMaxUseScore() {
        return maxUseScore;
    }

    public void setMaxUseScore(Integer maxUseScore) {
        this.maxUseScore = maxUseScore;
    }

    public String getFeaturedIntroduction() {
        return featuredIntroduction;
    }

    public void setFeaturedIntroduction(String featuredIntroduction) {
        this.featuredIntroduction = featuredIntroduction;
    }

    public String getBookingInformation() {
        return bookingInformation;
    }

    public void setBookingInformation(String bookingInformation) {
        this.bookingInformation = bookingInformation;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getBookingProcess() {
        return bookingProcess;
    }

    public void setBookingProcess(String bookingProcess) {
        this.bookingProcess = bookingProcess;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
