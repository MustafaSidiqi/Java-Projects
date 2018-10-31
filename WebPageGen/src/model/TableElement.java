package model;

import java.util.ArrayList;

public class TableElement extends TagElement {

	private int totalRows;
	private int totalCol;

	String att;
	String finalHTML = "";

	ArrayList<Element> list = new ArrayList<Element>();

	Element[][] ElementArr;
	
	public static int ToltalTable = 0;


	public TableElement(int i, int j, String attributes) {
		// TODO Auto-generated constructor stub
		
		ToltalTable++;
		totalRows = i;
		totalCol = j;

		ElementArr = new Element[totalRows][totalCol];

		if (attributes != null && !attributes.isEmpty()) {
			att = attributes;
		} else {
			att = "";
		}
		
	}

	public void addItem(int i, int j, Element elementItem) {
		// TODO Auto-generated method stub
		ElementArr[i][j] = elementItem;
	}

	public String genHTML(int indentation) {
		// TODO Auto-generated method stub
		String emptySpace = "";

		for (int i = 0; i < indentation; i++) {
			emptySpace += " ";
		}
 
		finalHTML += emptySpace + "<table";

		boolean temp = getenableID();
		if (temp) {
			finalHTML += " id=" + "\"" + "table" + getId() + "\" " + att + ">";
		} else finalHTML += ">";

		finalHTML += "\n";

		try {
			for (int j = 0; j <= ElementArr.length-1; j++) {
				finalHTML += emptySpace + "<tr>";
				for (int k = 0; k <= ElementArr.length-1; k++) {
					if (ElementArr[j][k] != null) {
						finalHTML += "<td>" + ElementArr[j][k].genHTML(0) + "</td>";

					} else {
						finalHTML += "<td>" + "</td>";
					}
					
				}
				finalHTML += "</tr>";
				finalHTML += "\n";
			}
			

		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();

		}

		finalHTML += "</table>";
		finalHTML += "\n";

		return finalHTML;
	}

}
