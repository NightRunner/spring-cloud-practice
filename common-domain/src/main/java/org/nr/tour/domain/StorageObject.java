package org.nr.tour.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Entity
@Table(name = "storage_object")
public class StorageObject extends IDEntity {

    @Column(name = "url", length = 512)
    private String url;

    @Column(name = "create_time")
    private Date createTime;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
