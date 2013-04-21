package controller.similaritycheck;

/**
 * public API that check similarities between 
 * names of classes/methods/attributes or other elements of a diagram
 * 
 * @author Dong Guo
 * 
 */

public class SimilarityCheck {

	private String elementName1;
	private String elementName2;
	
	/**
	 * Constructor to initialize necessary class members
	 *
	 * @param name1
	 * 			The name of element1
	 * @param name2
	 * 			The name of element2
	 */
	public SimilarityCheck(String name1, String name2){
		setElementName1(name1);
		setElementName2(name2);
	}
	
	/* The process function
	 * Package all the check functions in it
	 */
	public boolean doSimilarityCheck(/*String elementName1, String elementName2*/){
		
		// If the two Strings are exactly the same,
		// they are not "similar" (would be redundant)
		if (elementName1.equals(elementName2)) {
			return false;
		}
		
		//If there is only a lower/Uppercase problem,
		//we don't need to do other checks any more
		if(LowerUpperCheck.isSimilarWord(elementName1, elementName2)){
			return true;
		}else{
			/*invoke more check functions
			 * isSimilarSpelling() and isSimilarNoun();
			 * if all return false
			 * do isSimilarThesaurus();
			 */
			if(SpellCheck.isSimilarSpelling(elementName1, elementName2)
					|| PluralCheck.isSimilarNoun(elementName1, elementName2)){
				return true;
			}else{
				return SynonymCheck.isSimilarThesaurus(elementName1, elementName2);
			}
		}
	}

	/**
	 * Get the name of element2
	 * 
	 * @return elementName2 String
	 */
	public String getElementName2() {
		return elementName2;
	}

	/**
	 * Set elementName2
	 * 
	 * @param elementName2
	 * 			The name of element2
	 */
	public void setElementName2(String elementName2) {
		this.elementName2 = elementName2;
	}

	/**
	 * Get the name of element1
	 * 
	 * @return elementName1 String
	 */
	public String getElementName1() {
		return elementName1;
	}

	/**
	 * Set elementName1
	 * 
	 * @param elementName1
	 * 			The name of element1
	 */
	public void setElementName1(String elementName1) {
		this.elementName1 = elementName1;
	}
}