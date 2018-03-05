package org.lunker.matcher.repository;

import org.lunker.matcher.entity.MatchRequestMetadata;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dongqlee on 2018. 2. 17..
 */
public interface MatchRequestMetaRepository extends CrudRepository<MatchRequestMetadata, Long>{


    Iterable<MatchRequestMetadata> findByIsComplete(boolean isComplete);




}
