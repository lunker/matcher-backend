package org.lunker.matcher.repository;

import org.lunker.matcher.model.MatchRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by dongqlee on 2018. 2. 3..
 */
public interface MatchRepository extends CrudRepository<MatchRequest, Long> {

}
