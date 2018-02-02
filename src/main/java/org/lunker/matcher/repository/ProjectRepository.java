package org.lunker.matcher.repository;

import org.lunker.matcher.model.ProjectEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by dongqlee on 2018. 1. 9..
 */

public interface ProjectRepository extends CrudRepository<ProjectEntity, Long>{

    List<ProjectEntity> findByTitle(String title);

}
