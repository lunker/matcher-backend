package org.lunker.matcher.repository;

import org.lunker.matcher.entity.MatchRequestArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by dongqlee on 2018. 2. 17..
 */
public interface MatchRequestAreaRepository extends JpaRepository<MatchRequestArea, Long> {

    Iterable<MatchRequestArea> findByCityId(int cityId);
    List<MatchRequestArea> findByCityIdAndGuId(int cityId, int guId);
}
