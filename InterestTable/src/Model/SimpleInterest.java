package Model;

import java.util.ArrayList;

public class SimpleInterest {
	
	public String SimpleInterest (float years, float principal, float rate) {
		String tempVal = ""; 
	    tempVal += "Principle $"+principal + ", Rate: " + rate + "\n";
	    tempVal += "Year,    Simple Interest " + "\n";
	    float amount = 0;
	    for (int i = 1; i <= years; i++) {
	    	amount = (float) principal + (principal * (rate/100) * i);
	    	tempVal += i + "-->" + "$" + amount + "\n";
		}
		return tempVal;		
	}
}
