package model;

public class TextElement implements Element {
	
	String HTMLtxt = ""; 
	String finalHTML = ""; 

	public TextElement(String string)  {
		// TODO Auto-generated constructor stub
		if (string != null && !string.isEmpty()) {
			HTMLtxt = string;
		} else {
			HTMLtxt = "";
		}

	}


	
	@Override
	public String genHTML(int indentation) {
		// TODO Auto-generated method stub
		
		String emptySpace = ""; 
		
		for(int i = 0; i < indentation; i++) {
			emptySpace += " ";
		}
		
		finalHTML = emptySpace + HTMLtxt;
		
		return finalHTML;
	}
	

}
