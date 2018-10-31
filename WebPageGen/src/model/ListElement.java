package model;

import java.util.ArrayList;

public class ListElement extends TagElement {

	boolean orderedList;
	String att = "";
	String finalHTML = "";

	public static int toltalLists = 0;

	ArrayList<Element> list = new ArrayList<Element>();

	public ListElement(boolean ordList, String attributes) {
		// TODO Auto-generated constructor stub
		toltalLists++;
		orderedList = ordList;
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

	public void countUp() {
		// TODO Auto-generated method stub

	}

	public String genHTML(int indentation) {
		// TODO Auto-generated method stub
		String emptySpace = "";

		for (int i = 0; i < indentation; i++) {
			emptySpace += " ";
		}
		
		boolean temp = getenableID();
		if (orderedList) {
			
			
			finalHTML = emptySpace + "<ol ";
			if(temp) {
				finalHTML += "id=" + "\"" + "ol" + getId() + "\" " ;
			} 
			finalHTML +=  att + ">\n"; 
			
			emptySpace = ""; 
			for (int i = 0; i < list.size(); i++) {
				
				emptySpace = ""; 
				for (int i1 = 0; i1 < indentation+3; i1++) {
					emptySpace += " ";
				}
				
				finalHTML += emptySpace+"<li>\n" ;
				finalHTML += list.get(i).genHTML(indentation*2);
				finalHTML += "\n"+emptySpace+"</li>\n";
			}
			emptySpace = ""; 
			for (int i1 = 0; i1 < indentation; i1++) {
				emptySpace += " ";
			}
			finalHTML += emptySpace + "</ol>";
		} else if (!orderedList) {
			
			emptySpace = ""; 
			finalHTML = emptySpace + "<ul ";
			if(temp) {
				finalHTML += "id=" + "\"" + "ul" + getId() + "\" " ;
			} 
			finalHTML +=  att + ">\n"; 
			
			for (int i = 0; i < list.size(); i++) {
				
				emptySpace = ""; 
				for (int i1 = 0; i1 < indentation+3; i1++) {
					emptySpace += " ";
				}
				
				finalHTML += emptySpace+"<li>\n" ;
				finalHTML += list.get(i).genHTML(indentation*2+3);
				finalHTML += "\n"+emptySpace+"</li>\n";
			}
			emptySpace = ""; 
			for (int i1 = 0; i1 < indentation; i1++) {
				emptySpace += " ";
			}
			finalHTML += emptySpace + "</ul>";
		}

		return finalHTML;
	}

}
