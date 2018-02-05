package org.lunker.matcher.repository;

import org.lunker.matcher.model.OauthToken;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dongqlee on 2018. 2. 2..
 */
public interface AuthRepository extends CrudRepository<OauthToken, Long> {


}
