package onlineTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student {
	
	public Student(String tempName) {
		// TODO Auto-generated constructor stub
		name = tempName; 
		StudentPoints = new HashMap<>();
		StudentAnwers = new HashMap<>();
	}

	String name; 
	boolean StudenthasExams = false; 
	double totalPoints = 0;
	
	//HashMap<Integer, Exam> Exams = new HashMap<Integer, Exam>();
	HashMap<Integer, HashMap<Integer, String[]> > StudentAnwers = new HashMap<>();
	HashMap<Integer, HashMap<Integer, Double> > StudentPoints = new HashMap<>();
	HashMap<Integer, Double> Examscore = new HashMap<>();
	HashMap<Integer, Boolean> ExamBoolean = new HashMap<Integer, Boolean>();

	//ArrayList<AnswerTrueFalseQ> TrueFalseQ = new ArrayList<AnswerTrueFalseQ>();
	

}
