package controller.similaritycheck;

import java.util.*;
import java.util.regex.*;

/**
 * Transforms words from singular to plural
 * Some special cases like man -> men will be consided in SpellCheck
 * So we don't need to handle them here
 * Other special cases like mice or "~oes" are not consider right now
 * 
 * @author Dong Guo
 */

public class PluralInflector {

    //Can't think of a better name, but this is needed to avoid the price of initializing the pattern on each call.
    private static final Pattern UNDERSCORE_PATTERN_1 = Pattern.compile("([A-Z]+)([A-Z][a-z])");
    private static final Pattern UNDERSCORE_PATTERN_2 = Pattern.compile("([a-z\\d])([A-Z])");

    private static List<RuleAndReplacement> plurals = new ArrayList<RuleAndReplacement>();

    private static PluralInflector instance; // (Pseudo-)Singleton instance.
    
    //constructor
    private PluralInflector() {
        initialize();
    }
    
    //regex patterns
    private void initialize() {
        plural("$", "s");
        plural("(s|z|x|ch|ss|sh)$", "$1es");
		plural("([^aeiouy]|qu)y$", "$1ies");
        plural("(?:([^f])fe|([lr])f)$", "$1$2ves");
        plural("(ox)$", "$1en");
		plural("(child)$", "$1ren");
    }

    //Factory function
    public static PluralInflector getInstance() {
        if (instance == null) {
            instance = new PluralInflector();
        }
        return instance;
    }

    //public API, the one that will be invoked
    public String pluralize(String word) {
        return replaceWithFirstRule(word, plurals);
    }

    //process function
    private String replaceWithFirstRule(String word, List<RuleAndReplacement> ruleAndReplacements) {

        for (RuleAndReplacement rar : ruleAndReplacements) {
            String rule = rar.getRule();
            String replacement = rar.getReplacement();

            // Return if we find a match.
            Matcher matcher = Pattern.compile(rule, Pattern.CASE_INSENSITIVE).matcher(word);
            if (matcher.find()) {
                return matcher.replaceAll(replacement);
            }
        }
        return word;
    }
    
    public static void plural(String rule, String replacement) {
        plurals.add(0, new RuleAndReplacement(rule, replacement));
    }

	public static Pattern getUnderscorePattern1() {
		return UNDERSCORE_PATTERN_1;
	}

	public static Pattern getUnderscorePattern2() {
		return UNDERSCORE_PATTERN_2;
	}
    
}

//content class
class RuleAndReplacement {

    private String rule;
    private String replacement;
	
    //constructor
    public RuleAndReplacement(String rule, String replacement) {
        this.rule = rule;
        this.replacement = replacement;
    }
    
	/**
	 * Get the part that will replace the original
	 * 
	 * @return replacement String
	 */
    public String getReplacement() {
        return replacement;
    }
    
	/**
	 * Set the part that will replace the original
	 * 
	 * @param replacement
	 */
    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }
    
	/**
	 * Get the String that will be changed
	 * 
	 * @return rule String
	 */
    public String getRule() {
        return rule;
    }
    
	/**
	 * Set the String that will be changed
	 * 
	 * @param rule
	 */
    public void setRule(String rule) {
        this.rule = rule;
    }
}
