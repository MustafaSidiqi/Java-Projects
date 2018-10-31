package onlineTest;

public class StringQuestion {
	public StringQuestion(String studentName, int examId, int questionNumber, String[] answer2, double tempPoints) {
		// TODO Auto-generated constructor stub
		number = questionNumber; 
		points = tempPoints; 
		answer = answer2;
		question = null;
	}
	
	public StringQuestion() {
		
	}
	
	int number;
	String question;
	double points;
	double studentPoints;
	String[] answer;
}

