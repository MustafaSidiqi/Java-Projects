package onlineTest;

import java.util.ArrayList;
import java.util.HashMap;

public class Exam {
	/*
	 * public Exam(Exam f) { this. = new HashMap<String, Bar>();
	 * this.barDictionary.putAll(f.barDictionary); }
	 */

	public Exam(int examId, String title2) {
		// TODO Auto-generated constructor stub
		ID = examId; 
		title = title2;
		ExamQuestions = new HashMap<>(); 
		ExamAnwers = new HashMap<>();
		ExamPoints = new HashMap<>();
	}

	String title;
	int ID;
	double totalPoints = 0;
	int numberOfQuestions = 0; 
	
	HashMap<Integer, HashMap<Integer, String>> ExamQuestions = new HashMap<>();
	HashMap<Integer, HashMap<Integer, String[]>> ExamAnwers = new HashMap<>();
	HashMap<Integer, HashMap<Integer, Double>> ExamPoints = new HashMap<>();
	/*
	 * HashMap<Integer, TrueFalseQuestion> TrueFalseQuestions = new HashMap<Integer,
	 * TrueFalseQuestion>(); HashMap<Integer, StringQuestion> FillInQuestions = new
	 * HashMap<Integer, StringQuestion>(); HashMap<Integer, StringQuestion>
	 * MultipleQuestions = new HashMap<Integer, StringQuestion>();
	 */
}
