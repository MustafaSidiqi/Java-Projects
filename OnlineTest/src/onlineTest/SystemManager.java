package onlineTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.management.Query;
import javax.xml.transform.dom.DOMResult;

import org.junit.experimental.theories.PotentialAssignment;

public class SystemManager implements Manager {

	/* This is how to declare HashMap */
	HashMap<Integer, Exam> Exams = new HashMap<Integer, Exam>();
	HashMap<String, Student> Students = new HashMap<String, Student>();
	
	@Override
	public boolean addExam(int examId, String title) {
		// TODO Auto-generated method stub
		Exam newExam = new Exam(examId, title);
		newExam.ID = examId;
		newExam.title = title;
		Exams.put(examId, newExam);

		Exams.get(examId).ExamQuestions.put(examId, new HashMap<Integer, String>());
		Exams.get(examId).ExamAnwers.put(examId, new HashMap<Integer, String[]>());
		Exams.get(examId).ExamPoints.put(examId, new HashMap<Integer, Double>());

		return false;

	}

	@Override
	public void addTrueFalseQuestion(int examId, int questionNumber, String text, double points, boolean answer) {
		// TODO Auto-generated method stub
		/*
		 * exams.get(examId).questions.add(text); exams.get(examId).answer = answer;
		 * exams.get(examId).points = points;
		 */
		/*
		 * exams.get(examId).map.get(questionNumber).points = points;
		 * exams.get(examId).questions.add(text);
		 * exams.get(examId).map.get(questionNumber).answer = answer;
		 */

		/*
		 * TrueFalseQuestion tempQ = new TrueFalseQuestion();
		 * 
		 * tempQ.number = questionNumber; tempQ.answer = answer; tempQ.question = text;
		 * tempQ.points = points;
		 * 
		 * // Exams.get(examId).TrueFalseQ.add(tempQ);
		 * Exams.get(examId).TrueFalseQuestions.put(questionNumber, tempQ);
		 * Exams.get(examId).totalPoints += points;
		 */

		Exams.get(examId).ExamQuestions.get(examId).put(questionNumber, text);

		Exams.get(examId).ExamPoints.get(examId).put(questionNumber, points);

		String str = Boolean.toString(answer);
		// new String[]{"A"}

		Exams.get(examId).ExamAnwers.get(examId).put(questionNumber, new String[] { str });
		// System.out.println("EP: "+ Exams.get(examId).totalPoints);

		Exams.get(examId).numberOfQuestions++;
		Exams.get(examId).totalPoints += points;
	}

	@Override
	public void addMultipleChoiceQuestion(int examId, int questionNumber, String text, double points, String[] answer) {
		// TODO Auto-generated method stub

		/*
		 * StringQuestion tempQ = new StringQuestion();
		 * 
		 * tempQ.answer = answer; tempQ.question = text; tempQ.number = questionNumber;
		 * tempQ.points = points;
		 * 
		 * Exams.get(examId).MultipleQuestions.put(questionNumber, tempQ);
		 * Exams.get(examId).totalPoints += points;
		 */
		Exams.get(examId).ExamQuestions.get(examId).put(questionNumber, text);

		Exams.get(examId).ExamPoints.get(examId).put(questionNumber, points);

		Exams.get(examId).ExamAnwers.get(examId).put(questionNumber, answer);

		Exams.get(examId).numberOfQuestions++;
		Exams.get(examId).totalPoints += points;
	}

	@Override
	public void addFillInTheBlanksQuestion(int examId, int questionNumber, String text, double points,
			String[] answer) {
		// TODO Auto-generated method stub
		/*
		 * StringQuestion tempQ = new StringQuestion();
		 * 
		 * tempQ.answer = answer; tempQ.question = text; tempQ.number = questionNumber;
		 * tempQ.points = points;
		 * 
		 * Exams.get(examId).FillInQuestions.put(questionNumber, tempQ);
		 * Exams.get(examId).totalPoints += points;
		 */

		Exams.get(examId).ExamQuestions.get(examId).put(questionNumber, text);

		Exams.get(examId).ExamPoints.get(examId).put(questionNumber, points);

		//System.out.println(answer);
		List<String> list = Arrays.asList(answer);
		Collections.reverse(list);
		String[] str = (String[]) list.toArray();
		//System.out.println(str);

		Exams.get(examId).ExamAnwers.get(examId).put(questionNumber, str);

		Exams.get(examId).numberOfQuestions++;
		Exams.get(examId).totalPoints += points;
	}

	/**
	 * Returns a string with the following information per question:<br />
	 * "Question Text: " followed by the question's text<br />
	 * "Points: " followed by the points for the question<br />
	 * "Correct Answer: " followed by the correct answer. <br />
	 * The format for the correct answer will be: <br />
	 * a. True or false question: "True" or "False"<br />
	 * b. Multiple choice question: [ ] enclosing the answer (each entry separated
	 * by commas) and in sorted order. <br />
	 * c. Fill in the blanks question: [ ] enclosing the answer (each entry
	 * separated by commas) and in sorted order. <br />
	 * 
	 * @param examId
	 * @return "Exam not found" if exam not found, otherwise the key
	 */
	@Override
	public String getKey(int examId) {
		// TODO Auto-generated method stub
		String returnText = "";
		String str = "";

		for (int i = 1; i <= Exams.get(examId).numberOfQuestions; i++) {
			returnText += "Question Text: " + Exams.get(examId).ExamQuestions.get(examId).get(i) + "\n";
			returnText += "Points: " + Exams.get(examId).ExamPoints.get(examId).get(i) + "\n";

			boolean found = false;
			for (String element : Exams.get(examId).ExamAnwers.get(examId).get(i)) {
				if (element.equals("true") || element.equals("false")) {
					found = true;

					StringBuilder builder = new StringBuilder();
					for (String value : Exams.get(examId).ExamAnwers.get(examId).get(i)) {
						builder.append(value);
					}

					str = builder.toString();

					if (str.equals("true")) {
						str = "True";
					} else if (str.equals("false")) {
						str = "False";
					}
				}
			}
			if (!found) {
				str = Arrays.toString(Exams.get(examId).ExamAnwers.get(examId).get(i));
			}
			returnText += "Correct Answer: " + str + "\n";
		}
		return returnText;
	}

	@Override
	public boolean addStudent(String name) {
		// TODO Auto-generated method stub
		Student tempStudent = new Student(name);
		Students.put(name, tempStudent);

		//Students.get(name).ExamBoolean.putAll(new HashMap<Integer, Boolean>());

		return false;
	}

	@Override
	public void answerTrueFalseQuestion(String studentName, int examId, int questionNumber, boolean answer) {
		// TODO Auto-generated method stub
		try {
			if (!Students.get(studentName).StudenthasExams ) {
				Students.get(studentName).StudentAnwers.put(examId, new HashMap<Integer, String[]>());
				Students.get(studentName).StudentPoints.put(examId, new HashMap<Integer, Double>());
				Students.get(studentName).StudenthasExams = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			double tempPoints = 0;
			String str = Exams.get(examId).ExamAnwers.get(examId).get(questionNumber)[0];

			boolean answerBoolean = Boolean.valueOf(str);

			if (answerBoolean == answer) {
				tempPoints = Exams.get(examId).ExamPoints.get(examId).get(questionNumber);
				Students.get(studentName).StudentPoints.get(examId).put(questionNumber, tempPoints);
				Students.get(studentName).totalPoints += tempPoints;

			} else {
				tempPoints = 0.0;
				Students.get(studentName).StudentPoints.get(examId).put(questionNumber, tempPoints);
			}

			String strg = Boolean.toString(answer);
			// new String[]{"A"}

			Students.get(studentName).StudentAnwers.get(examId).put(questionNumber, new String[] { strg });
		} catch (Exception e) {
			// TODO: handle exception
			
			Students.get(studentName).StudentAnwers.put(examId, new HashMap<Integer, String[]>());
			Students.get(studentName).StudentPoints.put(examId, new HashMap<Integer, Double>());
			
			double tempPoints = 0;
			String str = Exams.get(examId).ExamAnwers.get(examId).get(questionNumber)[0];

			boolean answerBoolean = Boolean.valueOf(str);

			if (answerBoolean == answer) {
				tempPoints = Exams.get(examId).ExamPoints.get(examId).get(questionNumber);
				Students.get(studentName).StudentPoints.get(examId).put(questionNumber, tempPoints);
				Students.get(studentName).totalPoints += tempPoints;

			} else {
				tempPoints = 0.0;
				Students.get(studentName).StudentPoints.get(examId).put(questionNumber, tempPoints);
			}

			String strg = Boolean.toString(answer);
			// new String[]{"A"}

			Students.get(studentName).StudentAnwers.get(examId).put(questionNumber, new String[] { strg });
			
		}

	}

	@Override
	public void answerMultipleChoiceQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		// TODO Auto-generated method stub
		try {
			if (!Students.get(studentName).StudenthasExams) {
				Students.get(studentName).StudentAnwers.put(examId, new HashMap<Integer, String[]>());
				Students.get(studentName).StudentPoints.put(examId, new HashMap<Integer, Double>());
				Students.get(studentName).StudenthasExams = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		double tempPoints = 0;

		if (Arrays.equals(answer, Exams.get(examId).ExamAnwers.get(examId).get(questionNumber))) {
			tempPoints = Exams.get(examId).ExamPoints.get(examId).get(questionNumber);
			Students.get(studentName).StudentPoints.get(examId).put(questionNumber, tempPoints);
			Students.get(studentName).totalPoints += tempPoints;
		} else {
			tempPoints = 0.0;
			Students.get(studentName).StudentPoints.get(examId).put(questionNumber, tempPoints);
		}

		Students.get(studentName).StudentAnwers.get(examId).put(questionNumber, answer);
	}

	@Override
	public void answerFillInTheBlanksQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		// TODO Auto-generated method stub
		try {
			if (!Students.get(studentName).StudenthasExams) {
				Students.get(studentName).StudentAnwers.put(examId, new HashMap<Integer, String[]>());
				Students.get(studentName).StudentPoints.put(examId, new HashMap<Integer, Double>());
				Students.get(studentName).StudenthasExams = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		double tempPoints = 0;
		
		String[] correctAnswer = Exams.get(examId).ExamAnwers.get(examId).get(questionNumber);
		
		double size = correctAnswer.length;
		double totalPoint = Exams.get(examId).ExamPoints.get(examId).get(questionNumber);
		tempPoints = totalPoint/ size;
		double pointVal = 0.0;
		
		for (String corrAns : correctAnswer) {
			for (String Ans : answer) {
				if(corrAns.equals(Ans)) {	
					Students.get(studentName).totalPoints += tempPoints;
					pointVal += tempPoints; 
				} else {
					tempPoints += 0.0;
					//Students.get(studentName).StudentPoints.get(examId).put(questionNumber, tempPoints);
				}
			}
		}
		
		Students.get(studentName).StudentPoints.get(examId).put(questionNumber, pointVal);
		Students.get(studentName).StudentAnwers.get(examId).put(questionNumber, answer);
	}

	/**
	 * Returns the score the student got for the specified exam.
	 * 
	 * @param studentName
	 * @param examId
	 * @return score
	 */
	@Override
	public double getExamScore(String studentName, int examId) {
		// TODO Auto-generated method stub
		double tempPoints = 0;

		for (int i = 1; i <= Exams.get(examId).numberOfQuestions; i++) {
			tempPoints += Students.get(studentName).StudentPoints.get(examId).get(i);
		}
		return tempPoints;
	}

	/**
	 * Generates a grading report for the specified exam. The report will include
	 * the following information for each exam question:<br />
	 * "Question #" {questionNumber} {questionScore} " points out of "
	 * {totalQuestionPoints}<br />
	 * The report will end with the following information:<br />
	 * "Final Score: " {score} " out of " {totalExamPoints};
	 * 
	 * @param studentName
	 * @param examId
	 * @return report
	 */
	@Override
	public String getGradingReport(String studentName, int examId) {
		// TODO Auto-generated method stub
		String returnString = "";
		Students.get(studentName).totalPoints = 0;
		if (!Students.get(studentName).StudentAnwers.isEmpty()) {
			for (int i = 1; i <= Exams.get(examId).numberOfQuestions; i++) {
				returnString += "Question #" + i + " " + Students.get(studentName).StudentPoints.get(examId).get(i)
						+ " points out of " + Exams.get(examId).ExamPoints.get(examId).get(i) + "\n";
				Students.get(studentName).totalPoints += Students.get(studentName).StudentPoints.get(examId).get(i);
			}
		}

		double totalStudent = Students.get(studentName).totalPoints;
		double totalExam = Exams.get(examId).totalPoints;

		returnString += "Final Score: " + totalStudent + " out of " + totalExam;
		return returnString;
	}
	
	/**
	 * Sets the cutoffs for letter grades.  For example, a typical curve we will have
	 * new String[]{"A","B","C","D","F"}, new double[] {90,80,70,60,0}.  Anyone with a 90 or
	 * above gets an A, anyone with an 80 or above gets a B, etc.  Notice we can have different
	 * letter grades and cutoffs (not just the typical curve).
	 * @param letterGrades
	 * @param cutoffs
	 */
	@Override
	public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getCourseNumericGrade(String studentName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCourseLetterGrade(String studentName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCourseGrades() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getMaxScore(int examId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMinScore(int examId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAverageScore(int examId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void saveManager(Manager manager, String fileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public Manager restoreManager(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

}
