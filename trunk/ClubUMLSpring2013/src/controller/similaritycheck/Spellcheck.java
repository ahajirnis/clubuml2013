package controller.similaritycheck;

/**
 * SimilaritySpellcheck
 * CSYE7945 Spring 2013 
 * @author CSERRANO
 */

public class Spellcheck {

	/**
	 * Spellcheck
	 * 
	 * @param name1
	 *            the name of first element
	 * @param name2
	 *            the name of second element
	 *            
	 * @return true if the names spell similar
	 */
	public static boolean isSimilarSpelling(String name1, String name2) {
		// Precondition: name1 and name2 are different strings
			
		// Check for the same length of strings
		if (name1.length() != name2.length()) {
			return false;
		}
			
		// Check for just one character difference
		if (isOneCharOff(name1, name2)) {
			return true;
		}
			
		// Check for two letters transposed
		if (twoLettersTransposed(name1,name2)) {
			return true;
		}
			
		return false;
	}
		
	/**
	 * 
	 * @param name1
	 *            the name of first element
	 * @param name2
	 *            the name of second element
	 *            
	 * @return true if the two names have only one different character
	 */
	private static boolean isOneCharOff(String name1, String name2) {
		// Precondition: name1 and name2 are same length
			
		int numCharsDifferent = 0;
			
		// Iterate through characters of input Strings
		for (int i = 0; i < name1.length(); i++){
			char char1 = name1.charAt(i);        
			char char2 = name2.charAt(i);
			    
			if (char1 != char2) {
			    numCharsDifferent += 1;
			}
			    
			// Check for >1 character different
			if (numCharsDifferent > 1) {
			    return false;
			}
		}
			
		// Passed the test
		return true;
	}

	/**
	 * 
	 * @param name1
	 *            the name of first element
	 * @param name2
	 *            the name of second element
	 *            
	 * @return true if there is a transposed mistake between the two names
	 */
	private static boolean twoLettersTransposed(String name1, String name2) {
		// Precondition: name1 and name2 are same length
			
		// flag for if a character is different for name1 and name2
		boolean diffFlag = false;
		// flag for if two letters were transposed
		boolean transposed = false;
			
		char lastChar1 = 0;
		char lastChar2 = 0;
			
		// Iterate through characters of input Strings
		for (int i = 0; i < name1.length(); i++){
			char char1 = name1.charAt(i);        
			char char2 = name2.charAt(i);
			    
			// If there was a difference in the last two characters,
			// Check if these two characters are opposite those
			// Ex: If last iteration char1 = a, char2 = b,
			// check if this iteration, char1 = b, char2 = a
			if (diffFlag) {
			    if (char1 == lastChar2 && char2 == lastChar1) {
			    	diffFlag = false;
			    	transposed = true;
			    } else {
			    	// Failed the test
			    	return false;
			    }
			} else if (char1 != char2) {
			    // Fail if there was already a set of transposed letters
			    if (transposed == true) {
			    	return false;
			    }
			    diffFlag = true;
			    lastChar1 = char1;
			    lastChar2 = char2;
			}
		}
			
		// Passed the test
		return true;
	}
}
