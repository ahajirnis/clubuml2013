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
	
	SimilarityCheck(String name1, String name2){
		setElementName1(name1);
		setElementName2(name2);
	}
	
	/* The process function
	 * Package all the check functions in it
	 */
	public boolean doSimilarityCheck(){
		
		LowerUpperCheck firstCheck = new LowerUpperCheck();
		
		//If there is only a lower/Uppercase problem,
		//we don't need to do other checks any more
		if(firstCheck.isSimilarWord(elementName1, elementName2)){
			return true;
		}else{
			/*invoke more check functions
			 * isSimilarSpelling();
			 * isSimilarThesaurus();
			 * etc.
			 */
		}
		return false;
	}

	public String getElementName2() {
		return elementName2;
	}

	public void setElementName2(String elementName2) {
		this.elementName2 = elementName2;
	}

	public String getElementName1() {
		return elementName1;
	}

	public void setElementName1(String elementName1) {
		this.elementName1 = elementName1;
	}
	
}