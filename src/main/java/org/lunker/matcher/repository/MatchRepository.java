package org.lunker.matcher.repository;

import org.lunker.matcher.model.MatchRequest;
import org.lunker.matcher.model.ProjectEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by dongqlee on 2018. 2. 3..
 */
public interface MatchRepository extends CrudRepository<MatchRequest, Long> {
    List<ProjectEntity> findByCity(String title);
}
