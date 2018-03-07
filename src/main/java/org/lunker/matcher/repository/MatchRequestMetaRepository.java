package org.lunker.matcher.repository;

import org.lunker.matcher.entity.MatchRequestMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by dongqlee on 2018. 2. 17..
 */
public interface MatchRequestMetaRepository extends JpaRepository<MatchRequestMetadata, Long> {
    List<MatchRequestMetadata> findByEqualsIsComplete(boolean isComplete);
}
