package controller.similaritycheck;

/**
 * public classes that check similarities between 
 * names of classes/methods/attributes or other elements of a diagram
 * 
 * @author Dong Guo
 * 
 */

public class SimilarityCheck {
	
	private String lowName1;
	private String lowName2;
	
	SimilarityCheck(){
		lowName1 = "";
		lowName2 = "";
	}
	
	/**
	 * Lowercase and uppercase check.
	 * 
	 * @param name1
	 *            the name of first element
	 * @param name2
	 *            the name of second element
	 *            
	 * @return true if names match else false
	 */
	public boolean isSimilarCase(String name1, String name2){
		
		lowName1 = name1.toLowerCase();
		lowName2 = name2.toLowerCase();
		
		try{
			if(lowName1.equals(lowName2)){
			return true;
			}
		}catch(Exception e) {
			return false;
		}
		return false;
	}
	
	/**
	 * Check if the two names are synonym for each other.
	 * 
	 * @param name1
	 *            the name of first element
	 * @param name2
	 *            the name of second element
	 *            
	 * @return true if the two names are synonym for each other
	 */
	
	/*
	public boolean isSimilarThesaurus(String name1, String name2){
		
		lowName1 = name1.toLowerCase();
		lowName2 = name2.toLowerCase();
		
		
	}
	*/
}