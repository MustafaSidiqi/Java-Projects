package model;

public class HeadingElement extends TagElement {

	int HTMLlevel; 
	
	String att; 
	String finalHTML;
	
	TextElement element; 
	
	
	public HeadingElement(Element Element, int level, String attributes) {
		//super("h",true, Element, attributes);
		HTMLlevel = level; 
		element = (TextElement)Element;
		
		if (attributes != null && !attributes.isEmpty()) {
			att = attributes;
		} else {
			att = "";
		}
	}
	
@Override
	public String genHTML(int indentation) {
		// TODO Auto-generated method stub
		
		String emptySpace = ""; 
		String HTMLreturn; 
		
		for(int i = 0; i < indentation; i++) {
			emptySpace += " ";
		}
		
		HTMLreturn = emptySpace + "<h" + HTMLlevel  + ">" + element.genHTML(0) + "</h" + HTMLlevel + ">"  ;
		
		return HTMLreturn;
	}
	
}