package org.nr.tour.domain;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@MappedSuperclass
public class IDEntity implements Serializable {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
