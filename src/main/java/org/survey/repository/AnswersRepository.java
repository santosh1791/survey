package org.survey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.survey.domain.Answers;
import org.survey.domain.User;

/**
 * Spring Data  repository for the Answers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnswersRepository extends JpaRepository<Answers, Long> {

    @Query("select answers from Answers answers where answers.user.login = ?#{principal.username}")
    List<Answers> findByUserIsCurrentUser();
    
    @Query(value = "select q.question,a.questions_id,count(a.id), sum(a.answer_value) from \n" + 
    		"answers a,\n" + 
    		"questions q \n" + 
    		"where a.questions_id = q.id \n" + 
    		"group by a.questions_id,q.question,q.id "
    		+ " order by q.id", nativeQuery = true)
    List<Object[]> getSubmissions();
    
    @Transactional
    @Modifying
   @Query("delete from Answers where user=:user")
   void deleteSubmissions(@Param("user") User user);
    
}
