package controller.similaritycheck;

/**
 * Testing class for all the similarity check functions
 * 
 * @author Dong Guo
 *
 */

public class Testing {

    public static void main(String[] args) throws Exception {
        
        final int a = 100;
        String[] input = new String[a];

        try {
        	
        	//name couples:
            input[0] = "car";
            input[1] = "CAR";
            
            input[2] = "woman";
            input[3] = "women";

            input[4] = "Northeastern";
            input[5] = "Mortheastern";
            
            input[6] = "child";
            input[7] = "children";
            
            input[8] = "project";
            input[9] = "program";
            
            input[10] = "project";
            input[11] = "human";

            for (int i = 0; i < input.length; i += 2) {
            	
            	//Invoking example:
                boolean flag = new SimilarityCheck(input[i], input[i+1]).doSimilarityCheck();
                
                System.out.println("Now we are comparing: " + input[i] + " and " + input[i+1]);
                
                if (flag == true) {
                    System.out.println("Yes, they are similar!" + "\n");
                } else {
                    System.out.println("No, they are not similar!" + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
