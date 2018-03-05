package org.lunker.matcher.repository;

import org.lunker.matcher.entity.MatchRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by dongqlee on 2018. 3. 2..
 */
public interface MatchRequestRepository extends JpaRepository<MatchRequest, Long> {
    List<MatchRequest> findByCityIdAndGuId(int cityId, int guId);
}
