package org.survey.web.form;

import java.util.HashMap;
import java.util.Map;

public class SurveyForm {
	
	Map<Long, Integer> answers = new HashMap<Long, Integer>();

	public Map<Long, Integer> getAnswers() {
		return answers;
	}

	public void setAnswers(Map<Long, Integer> answers) {
		this.answers = answers;
	}

	
	
    	

}
