package model;

public class AnchorElement extends TagElement {

	String finalHTML = "";

	String att;
	String URL;
	String linktext;

	public AnchorElement(String url, String linkText, String attributes) {
		// TODO Auto-generated constructor stub

		URL = url;
		linktext = linkText;
		att = attributes;
	}

	public String genHTML(int indentation) {
		// TODO Auto-generated method stub

		String emptySpace = "";
		finalHTML = "";

		for (int i = 0; i < indentation; i++) {
			emptySpace += " ";
		}

		finalHTML += emptySpace + "<a ";

		boolean temp = getenableID();
		if (temp) {
			finalHTML += "id=" + "\"" + "a" + getId() + "\" ";
		}

		finalHTML += "href=" + "\"" + URL + "\"" + ">" + linktext + "</a>";
		// <a id="a2" href="http://www.cs.umd.edu">UMD</a>

		return finalHTML;
	}

	public String getUrlText() {
		// TODO Auto-generated method stub
		return URL;
	}

	public String getLinkText() {
		// TODO Auto-generated method stub
		return linktext;
	}
}
