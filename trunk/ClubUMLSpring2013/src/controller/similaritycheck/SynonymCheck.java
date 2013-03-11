package controller.similaritycheck;

public class SynonymCheck {
	
	private String lowName1;
	private String lowName2;
	
	/**
	 * Check if the two names are synonyms for each other.
	 * 
	 * @param name1
	 *            the name of first element
	 * @param name2
	 *            the name of second element
	 *            
	 * @return true if the two names are synonyms for each other
	 */
	public boolean isSimilarThesaurus(String name1, String name2){
		
		lowName1 = name1.toLowerCase();
		lowName2 = name2.toLowerCase();
		/*
		 * 
		 */
		return true;
	}
}
