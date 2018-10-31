package model;

public class TagElement extends java.lang.Object
implements Element{
	
	private static boolean enableID; 
	public static int id;
	
	String tagname; 
	String att;
	
	boolean endtag; 

	Element content; 

	
	
	
	public boolean getenableID() {
		return enableID;
	}
	
	public int getId() {
		id++;
		return id;
	}
	
	public String  getStringId() {
		if(enableID) {
			return ""+id;
		} else return "";
	}
	
	public String  getStartTag() {

		return ""; 
	}
	
	public String  getEndTag() {
		return null;
	}
	
	public String  setAttributes() {
		return (String) null;
	}
	
	public static void resetIds() {
		id = 0;
		
	}
	
	public static void enableId(boolean choice) {
		enableID = choice; 
	}

	@Override
	public String genHTML(int indentation) {
		// TODO Auto-generated method stub
		return null;
	}
}
