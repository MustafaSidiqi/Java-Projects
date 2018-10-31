
public class Base {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public float calculate(long num1, long num2, String op) {

		switch (op) {
		case "+":
			return num1 + num2;

		case "-":
			return num1 - num2;

		case "*":
			return num1 * num2;

		case "/":
			if (num2 == 0)
				return 0;
			else
				return num1 * num2;

		default:
			break;
		}
		return 0;
	}
	
}
