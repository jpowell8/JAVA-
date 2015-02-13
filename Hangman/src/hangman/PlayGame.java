package hangman;

import hangman.IEvilHangmanGame.GuessAlreadyMadeException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PlayGame {

public static void main(String [] args){
	
	Hangman game = new Hangman();
	File dictionary = new File(args[0]);//dictionary
	int number = Integer.parseInt(args[1]);//wordlength
	game.startGame(dictionary, number);
	int numGuesses = Integer.parseInt(args[2]);
	int numGuessesToStart = Integer.parseInt(args[2]);
	List<Character> guesses = new ArrayList<Character>();
	boolean winner = false;
	boolean validGuess = false;
	char c = 'j';
	Set<String> BaseDictionary;




while(numGuesses > 0 && winner == false){
	
	
	try {
		String guess = new BufferedReader(new InputStreamReader(System.in)).readLine();
		String s = new StringBuilder().append(guess).toString();
		 s = s.toLowerCase();
		if(s.length()==1){
			char ch = s.charAt(0);
			if(!Character.isLetter(ch)) {
				System.out.print("Alphabetic characters only");
				System.out.print('\n');
	        }
			else if(guesses.contains(ch)){
				System.out.print("You already guessed the letter ");
				System.out.print(ch);
				System.out.print('\n');
				}
			else{guesses.add(ch);
			try {
				BaseDictionary = game.makeGuess(ch);
				numGuesses = numGuessesToStart - (guesses.size() - game.numberCorrect());
				System.out.print('\n');
				System.out.print("Already Guessed: ");
				System.out.print(guesses.toString());
				System.out.print('\n');
				System.out.print("Set Size is ");
				System.out.print(BaseDictionary.size());
				System.out.print('\n');
				System.out.print("Number of remaining guesses ");
				System.out.print(numGuesses);
				System.out.print('\n');
				System.out.print(game.getKey());
				winner = checkWin(BaseDictionary, game.getKey());
			} catch (GuessAlreadyMadeException e) {
				e.printStackTrace();
			}
			}
		}
		else{
			System.out.print("Guess a letter");
			System.out.print('\n');
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	
	
	
	
}
if(winner){
	System.out.print("YOU WIN!!");
	System.out.print('\n');
}
if(!winner){
	System.out.print(" _You Dead.. \n");
			System.out.print("| .__________))______| \n");
System.out.print("| | / /      || \n");
System.out.print("| |/ /       ||\n");
System.out.print("| | /        ||.-''.\n");
System.out.print("| |/         |/  _  \\ \n");
System.out.print("| |          ||  `/,|\n");
System.out.print("| |          (\\`_.'\n");
		System.out.print("| |         .-`--'.\n");
System.out.print("| |        /Y . . Y\\\n");
System.out.print("| |       // |   | \\\n");
System.out.print("| |      //  | . |  \\\n");
System.out.print("| |     ')   |   |   (`\n");
System.out.print("| |          ||'||\n");
System.out.print("| |          || ||\n");
System.out.print("| |          || ||\n");
System.out.print("| |          || ||\n");
System.out.print("| |         / | | \\\n");
System.out.print("''''''''''|_`-' `-' |'''''|\n");
System.out.print("|'|'''''''\\ \\       ''|'|\n");
System.out.print("| |        \\ \\        | |\n");
System.out.print(": :         \\ \\       : :  sk\n");
System.out.print(". .          `'       . . \n");
	System.out.print('\n');
}
}

static boolean checkWin(Set<String> BaseDictionary, String key){
	if(BaseDictionary.size() > 1){return false;}
	else if(BaseDictionary.size() == 1){
		int numDashes = 1;
		
			numDashes = instance(key);
			if(numDashes == 0){
				return true;}
			else{return false;}
		
	}
	return false;
}


static int instance(String Key){
	int count = 0;
	for(int i = 0; i < Key.length(); i++){
		if(Key.charAt(i)==(char)'-'){count++;}
	}
	return count;
}

}