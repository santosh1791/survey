package org.survey.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.survey.domain.Answers;
import org.survey.domain.Questions;
import org.survey.domain.User;
import org.survey.repository.AnswersRepository;
import org.survey.repository.QuestionsRepository;
import org.survey.repository.UserRepository;
import org.survey.web.form.SurveyForm;

@Controller
public class HomeController {
	@Autowired
	QuestionsRepository questionsRepository;
	
	@Autowired
	AnswersRepository answersRepository;
	
	@Autowired
	UserRepository userRepository;
	
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    
    @RequestMapping("/")
    String index() {
       return "home/homeNotSignedIn";
    }
    
    
    @RequestMapping("/login")
    String loginPage( Model model) {
       
        return "home/homeNotSignedIn";
    }

    @PostMapping("/api/login")
    @ResponseBody
    String loginPage() {
        return "success";
    }

    @RequestMapping(value={"/dashboard/home"})
    String home(RedirectAttributes redirectAttrs, Authentication authentication, HttpServletRequest request) {
    	UserDetails u = (UserDetails)authentication.getPrincipal();
    	log.debug("USERNAME :::: {}",u.getUsername());
			return "home/homeSignedIn";
    }
    

    @RequestMapping(value = "/student/survey")
    String studentSurvey(Model model) {
 	   log.debug("studentSurvey studentSurvey studentSurvey");
 	   model.addAttribute("questionsList", questionsRepository.findAll());
 	   model.addAttribute("surveyForm", new SurveyForm());
 	   //model.addAttribute("answers", new SurveyForm());
 	   return "home/surveyForm";
    }
    
    @PostMapping(value = "/student/survey")
    String studentSurveyPost(@ModelAttribute SurveyForm surveyForm,
    		@ModelAttribute(value = "answers") HashMap<String, String> answers,Model model,  Authentication authentication) {
 	   log.debug("studentSurveyPost studentSurveyPost studentSurveyPost");
 	   log.debug("!!!!!>>>>>>{}",surveyForm.getAnswers());
 	  UserDetails u = (UserDetails)authentication.getPrincipal();
 	 User user = userRepository.findByLogin(u.getUsername());
 	   //for(Answers aInList : ) {
		/*
		 * for(int i=0;i<surveyForm.getAnswers().size();i++) { Answers a = new
		 * Answers(); Questions q =
		 * questionsRepository.getOne(surveyForm.getAnswers().); a.setQuestions(q);
		 * a.setAnswerValue(answerValue);
		 * 
		 * }
		 */
 	  answersRepository.deleteSubmissions(user);
 	   
 	 surveyForm.getAnswers().forEach((k, v) -> {
 		  
 			System.out.println("Key: " + k + ", Value: " + v);
 			Answers a = new Answers();
 	 		 Questions q = questionsRepository.getOne(k);
 	 		 a.setQuestions(q);
 	 		 a.setAnswerValue(v);
 	 		 a.setUser(user);
 			answersRepository.save(a);
 	  });
 	   
 	   
 	   //model.addAttribute("questionsList", questionsRepository.findAll());
 	   return "home/surveyForm";
    }
    
    
    @RequestMapping(value = "/principal/report")
    String principalReport(Model model) {
 	   log.debug("principalReport principalReport principalReport");
 	   
 	   model.addAttribute("submissions",answersRepository.getSubmissions());
 	   
 	   
 	   return "home/submissionsReport";
    }
    
    

    @RequestMapping(value={"/pms/documentation"})
    String documentation() {
        return "pms/documentation";
    }

    @GetMapping(value={"/pms/settings"})
    String settings() {
       return "pms/settings";
    }

    

    @RequestMapping("/userForm")
    String usernew() {
        return "users/forms/userForm";
    }
    @RequestMapping("/groupForm")
    String groupnew() {
        return "groups/forms/groupForm";
    }
    
    
    @RequestMapping("/success")
    String success() {
        return "fragments/success";
    }
    
    
	
	
	
}

