package org.survey.repository;

import org.springframework.stereotype.Repository;
import org.survey.domain.UserExtra;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UserExtra entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserExtraRepository extends JpaRepository<UserExtra,Long> {
    UserExtra findByUserId(Long id);
}
