package org.nr.tour.api.vo;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
public class InsuranceVO extends BaseVO {

    private String id;

    private String name;
    
    private Double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
