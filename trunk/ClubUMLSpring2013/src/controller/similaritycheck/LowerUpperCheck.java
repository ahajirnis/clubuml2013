package controller.similaritycheck;

public class LowerUpperCheck {
		
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
	public static boolean isSimilarWord(String name1, String name2){
		
		name1 = name1.toLowerCase();
		name2 = name2.toLowerCase();
		
		try{
			if(name1.equals(name2)){
			return true;
			}
		}catch(Exception e) {
			return false;
		}
		return false;
	}
}
