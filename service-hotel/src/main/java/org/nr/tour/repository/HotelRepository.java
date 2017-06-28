package org.nr.tour.repository;

import org.nr.tour.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@RepositoryRestResource(collectionResourceRel = "hotel", path = "hotel")
public interface HotelRepository extends JpaRepository<Hotel, String> {
}
