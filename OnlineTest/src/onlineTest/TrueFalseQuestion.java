package onlineTest;

public class TrueFalseQuestion {
	
	public TrueFalseQuestion(String studentName, int examId, int questionNumber, boolean answer2, double tempPoints) {
		// TODO Auto-generated constructor stub
		number = questionNumber; 
		points = tempPoints; 
		answer = answer2;
		question = null;
	}
	
	public TrueFalseQuestion () {
		
	}
	
	int number;
	String question;
	double points;
	double studentPoints;
	boolean answer;
}

