package spell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//import spell.ISpellCorrector.NoSimilarWordFoundException;

/**
 * A simple main class for running the spelling corrector. This class is not
 * used by the passoff program.
 */
public class Main {
	
	/**
	 * Give the dictionary file name as the first argument and the word to correct
	 * as the second argument.
	 * @throws spell.ISpellCorrector.NoSimilarWordFoundException 
	 */
	public static void main(String[] args) throws NoSimilarWordFoundException, IOException, spell.ISpellCorrector.NoSimilarWordFoundException {
		
		System.out.print("Type the path of the .txt file you want to use as a dictionary \n");
		String dictionaryFileName = new BufferedReader(new InputStreamReader(System.in)).readLine();

		System.out.print("Enter Spell Ckeck input:\n");
		String inputWord = new BufferedReader(new InputStreamReader(System.in)).readLine();
		
		/**text.txt
bbig
		 * Create an instance of your corrector here
		 */
		ISpellCorrector corrector = new SpellCorrector();
		
		corrector.useDictionary(dictionaryFileName);
		String suggestion = corrector.suggestSimilarWord(inputWord);
		
		System.out.println("Suggestion is: " + suggestion);
	}

}