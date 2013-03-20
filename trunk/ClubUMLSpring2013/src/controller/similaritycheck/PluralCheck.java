package controller.similaritycheck;

/**
 * Use inflector to transform the shorter word
 * then compare, if they are the same, return true
 * else false
 * 
 * @author Dong Guo
 */


public class PluralCheck {
	
	/**
	 * Check if the one name is plural of another.
	 * 
	 * @param name1
	 *            the name of first element
	 * @param name2
	 *            the name of second element
	 *            
	 * @return true if the two names are similar
	 */
	public static boolean isSimilarNoun(String name1, String name2){
		
		name1 = name1.toLowerCase();
		name2 = name2.toLowerCase();
		
		if(name1.length() < name2.length()){
			name1 = PluralInflector.getInstance().pluralize(name1);
		}else{
			name2 = PluralInflector.getInstance().pluralize(name2);
		}
		
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