package controller.similaritycheck;

public class LowerUpperCheck {
	
	private String lowName1;
	private String lowName2;
		
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
	public boolean isSimilarWord(String name1, String name2){
		
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
}
