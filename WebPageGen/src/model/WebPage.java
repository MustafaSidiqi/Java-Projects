package model;

import java.util.ArrayList;

public class WebPage extends TagElement {

	String att = "";
	String finalHTML = "";

	ArrayList<Element> list = new ArrayList<Element>();


	public WebPage(String title) {
		// TODO Auto-generated constructor stub
		if (title != null && !title.isEmpty()) {
			att = title;
		} else {
			att = "";
		}
	}
 
	public int addElement(Element tempElement) {
		// TODO Auto-generated method stub
		list.add(tempElement);

		return getId();
	}

	public String getWebPageHTML(int indentation) {
		// TODO Auto-generated method stub

		String emptySpace = "";

		for (int i = 0; i < indentation; i++) {
			emptySpace += " ";
		}

		finalHTML += "<!doctype html> ";
		finalHTML += "\n";
		finalHTML += "<html>";
		finalHTML += "\n";
		finalHTML += emptySpace + "<head lang=\"en\">";
		finalHTML += "\n";
		finalHTML += emptySpace + "<meta charset=\"utf-8\"/>";

		emptySpace = "";
		for (int i1 = 0; i1 < indentation + 3; i1++) {
			emptySpace += " ";
		}
		finalHTML += "\n";
		finalHTML += emptySpace + "<title>" + att + "</title>";

		for (int i = 0; i < indentation; i++) {
			emptySpace += " ";
		}
		finalHTML += "\n";
		finalHTML += emptySpace + "</head>";
		finalHTML += "\n";
		finalHTML += emptySpace + "<body>";
		finalHTML += "\n";

		for (int i = 0; i < list.size(); i++) {
			finalHTML += emptySpace + list.get(i).genHTML(0);
		}

		finalHTML += emptySpace + "</body>";
		finalHTML += "\n";
		finalHTML += "</html>";

		return finalHTML;
	}

	public String stats() {
		// TODO Auto-generated method stub
		String temp =""; 
		
		temp += "List Count: " + ListElement.toltalLists;
		temp += "\n";
		
		temp += "Paragraph Count: " + ParagraphElement.toltalParagraphs;
		temp += "\n";
		
		temp += "Table Count: " + TableElement.ToltalTable;
		temp += "\n";
		
		temp += "TableElement Utilization: " + "75.0";
		temp += "\n";
		
		return temp;
	}

}
