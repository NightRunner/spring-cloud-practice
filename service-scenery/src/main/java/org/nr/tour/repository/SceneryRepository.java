package org.nr.tour.repository;

import org.nr.tour.domain.Scenery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenhaiyang <690732060@qq.com>
 */
@Repository
public interface SceneryRepository extends JpaRepository<Scenery, String> {

    List<Scenery> findByRecommend(Boolean bool);

    Page<Scenery> findByNameLike(String keyword, Pageable pageable);
}
