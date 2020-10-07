package org.survey.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.survey.domain.PersistentToken;
import org.survey.domain.User;

import java.util.List;

/**
 * Spring Data JPA repository for the PersistentToken entity.
 */
public interface PersistentTokenRepository extends JpaRepository<PersistentToken, String> {

    List<PersistentToken> findByUser(User user);

    List<PersistentToken> findByTokenDateBefore(LocalDate localDate);

}
