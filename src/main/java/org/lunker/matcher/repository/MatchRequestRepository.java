package org.lunker.matcher.repository;

import org.lunker.matcher.entity.MatchRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by dongqlee on 2018. 3. 2..
 */
public interface MatchRequestRepository extends JpaRepository<MatchRequest, Long> {

    /*

    @Query(value = "SELECT mr from MatchRequest mr WHERE mr.city_id = :city_id AND mr.gu_id = :gu_id AND mr.from_date >= :from_date AND mr.to_date <= :to_date")
    List<MatchRequest> findByCityIdAndGuIdAndFromDateGreaterThanEqualAndToDateLessThanEqual(
            @Param("city_id") int cityId, @Param("gu_id") int guId, @Param("from_date") LocalDateTime fromDate, @Param("to_date") LocalDateTime toDate);
*/

    /*
    @Query(value = "SELECT mr from MatchRequest mr WHERE mr.city_id = :city_id AND mr.gu_id = :gu_id AND mr.from_date >= :from_date AND mr.to_date <= :to_date")
    List<MatchRequest> findByCityIdAndGuIdAndFromDateGreaterThanEqualAndToDateLessThanEqual(
            @Param("city_id") int cityId, @Param("gu_id") int guId, @Param("from_date") LocalDateTime fromDate, @Param("to_date") LocalDateTime toDate);
*/

    List<MatchRequest> findByCityIdAndGuId(@Param("city_id") int cityId, @Param("gu_id") int guId);

    @Query("select mr from MatchRequest mr where req_id in ?1 and gu_id in ?2")
    List<MatchRequest> findByReqIdInAndGuIdIn(
            List<Long> reqIds,
            List<Integer> guIds
    );
}
