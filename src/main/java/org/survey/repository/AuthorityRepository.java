package org.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.survey.domain.Authority;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
