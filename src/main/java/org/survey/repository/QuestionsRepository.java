package org.survey.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.survey.domain.Questions;


/**
 * Spring Data  repository for the Questions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long> {

}
