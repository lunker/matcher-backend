package org.lunker.matcher.repository;

import org.lunker.matcher.entity.MatchRequestMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by dongqlee on 2018. 2. 17..
 */
public interface MatchRequestMetaRepository extends JpaRepository<MatchRequestMetadata, Long> {

    List<MatchRequestMetadata> findByIsCompleteEquals(boolean isComplete);

    @Query(value = "select mrm.req_id from match_request_meta mrm where mrm.exercise_id=:exercise_id and mrm.is_complete=:is_complete", nativeQuery = true)
    List<Long> findByExerciseIdEqualsAndIsCompleteEquals(@Param("exercise_id") long exerciseId, @Param("is_complete") boolean isComplete);
}
