package model;

public class ImageElement extends TagElement {

	String name; 
	String alt;
	String att;
	
	int width;
	int height;

	
	
	public ImageElement(String string, int w, int h, String a, String attributes) {
		// TODO Auto-generated constructor stub
		name = string;
		width = w; 
		height = h; 
		alt = a;
		att = attributes;
		
	}

	public String getImageURL() {
		return alt;
		// TODO Auto-generated constructor stub
	}
	
	public String genHTML(int indentation) {
		// TODO Auto-generated method stub
		String finalHTML =""; 
		
		String emptySpace = ""; 
		
		for(int i = 0; i < indentation; i++) {
			emptySpace += " ";
		}
		
		finalHTML += emptySpace + "<img ";
		//   <img id="img1" src="testudo.jpg" width="84" height="111" alt="Testudo Image">
		// 	ImageElement element = new ImageElement("testudo.jpg", width, height, alt, attributes);
		boolean temp = getenableID();
		if(temp) {
			finalHTML += "id="+ "\""+"img" + getId() +"\"";
		} 
		
		finalHTML += " src=" +"\"" + name + "\"" + " width="  +"\""+ width +"\""+ " height=" + "\""+height +"\""+ " alt=" + "\""+alt+"\""+  ">";

		
		
		return finalHTML;
	}
}
