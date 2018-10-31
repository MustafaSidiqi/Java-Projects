package model;

import java.util.ArrayList;

public class ParagraphElement extends TagElement {

	String att = "";
	String finalHTML = "";

	ArrayList<Element> list = new ArrayList<Element>();
	
	public static int toltalParagraphs = 0;

	public ParagraphElement(String attributes) {
		// TODO Auto-generated constructor stub
		if (attributes != null && !attributes.isEmpty()) {
			att = attributes;
		} else {
			att = "";
		}
		
	}
 
	public void addItem(Element tempElement) {
		// TODO Auto-generated method stub
		list.add(tempElement); 
		
	}

	public String genHTML(int indentation) {
		// TODO Auto-generated method stub
		String emptySpace = "";

		for (int i = 0; i < indentation; i++) {
			emptySpace += " ";
		}
					
			finalHTML += emptySpace + "<p ";
			
			boolean temp = getenableID();
			if(temp) {
				finalHTML += "id=" + "\"" + "p" + getId() + "\" ";
			} 
		
			finalHTML += att + ">\n";
					
			emptySpace = ""; 
			for (int i = 0; i < list.size(); i++) {
			
				emptySpace = ""; 
				for (int i1 = 0; i1 < indentation+3; i1++) {
					emptySpace += " ";
				}

				finalHTML += list.get(i).genHTML(indentation*2);
				finalHTML += "\n";
			}
			emptySpace = ""; 
			for (int i1 = 0; i1 < indentation; i1++) {
				emptySpace += " ";
			}
			finalHTML += emptySpace + "</p>";
			
		return finalHTML;
	}

}
