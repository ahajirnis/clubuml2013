package controller.similaritycheck;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Use a public API from words.bighugelabs.com
 * find out if the two names are synonyms for each other
 * 
 * @author Dong Guo
 */

public class SynonymCheck {
	
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
    public static boolean isSimilarThesaurus(String name1, String name2) {

    	//read the response line by line
        String line;
        //one big string that store the response entirely
        String responseString;
        //store the words we need from the response
        Hashtable responseTable = new Hashtable();

        //connection vars 
        BufferedReader rd;
        StringBuilder API_response = new StringBuilder();

        name1 = name1.toLowerCase();
        name2 = name2.toLowerCase();

        try {

            // API key: eb23eace35633b1274abdfeabc1b4753
            URL url = new URL("http://words.bighugelabs.com/api/2/eb23eace35633b1274abdfeabc1b4753/" + name1 + "/json");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            // Get the response
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line = rd.readLine()) != null) {
                API_response.append(line);
            }

            //Store the response as one big string
            responseString = API_response.toString();

            //Find out the words between two quotation marks, put them into a hushatble
            Pattern p = Pattern.compile("\"(.*?)\"");
            Matcher m = p.matcher(responseString);
            while (m.find()) {
                responseTable.put(m.group(), true);
            }

            //Find out if name2 is in those keys
            name2 = "\"" + name2 + "\"";
            if (responseTable.containsKey(name2)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            //System.out.println(e.toString());
        }
        return false;
    }
}