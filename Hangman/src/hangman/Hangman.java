package hangman;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Hangman implements IEvilHangmanGame {
	
	public Hangman(){}

	public Set<String> BaseDictionary = new TreeSet<String>();
	public Map sets = new HashMap<String, TreeSet<String>>();
	public int wordLength = 0;
	public List<Character> guesses = new ArrayList<Character>();
	public String CurrentKey = new String();
	
	public int numberCorrect(){
		int count = 0;
		for(char c: guesses){
			if (this.CurrentKey.contains(String.valueOf(c))){
				count++;
			}
		}
		return count;
	}
	
	public String getKey(){return this.CurrentKey;}
	
	@Override
	public void startGame(File dictionary, int wordLength) {
		this.wordLength = wordLength;
		Scanner Scan;
		try {
			Scan = new Scanner(dictionary);
			while(Scan.hasNext()){
				String str = Scan.next().toLowerCase();
				if(str.length()==wordLength){
				BaseDictionary.add(str);
				}
			}
			sets.put(firstEncode(this.wordLength), BaseDictionary);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException {
		String s = new StringBuilder().append(guess).toString();
		s.toLowerCase();
		char c = s.charAt(0);
		if(guesses.contains(c)){/*throw GuessAlreadyMadeException;*/}
		else{guesses.add(c);}
		encodeAll();
		shrinkSet();
		TreeSet<String> tempVariable = (TreeSet<String>) sets.get(CurrentKey);
		return tempVariable;
	}

	String firstEncode(int i){
		StringBuilder str = new StringBuilder("");
		for(int j = 0; j < i; j++){
			str.append('-');
		}
		this.CurrentKey = str.toString();
		return str.toString();
	}
	
	
	void encodeAll(){
		Map newSets = new HashMap<String, TreeSet<String>>();
		Set<String> oldDictionary = new TreeSet<String>();
		oldDictionary = (Set<String>) sets.get(this.CurrentKey);//currentKey - updated each guess
		for(String word: oldDictionary){
			String str = encode(word);
			Set<String> newKeys = newSets.keySet();//should only be one key
			if(!newKeys.contains(str)){
				Set<String> newDictionary = new TreeSet<String>();
				newDictionary.add(word);
				newSets.put(str, newDictionary);
			}
			else{
				Set<String> tempDictionary = (TreeSet<String>) newSets.get(str);
				tempDictionary.add(word); 
			}
		}
		this.sets = newSets;//new sets is correct here
	}
	
	
	String encode(String s){
			StringBuilder builder = new StringBuilder(s);
			int sizeBuilder = builder.length();
			for(int i = 0; i< sizeBuilder; i++){ 
				char c = builder.charAt(i);
				if(!guesses.contains(c)){builder.setCharAt(i, '-');}
			}
			return builder.toString();
		}

	
	void shrinkSet(){
		int bestCount = 0;	
		String bestString = "";
		Set<String> bestKeys = new TreeSet<String>();
			Set<String> keys = sets.keySet();
			for(String theKey: keys){
				TreeSet<String> group = (TreeSet<String>) sets.get(theKey);
				if (group.size() == bestCount){bestKeys.add(theKey);}
				else if(group.size() > bestCount){
					bestCount = group.size();
					bestKeys.clear();
					bestKeys.add(theKey);
				}
			}//by now should have a set of keys to equally sized sets
			TreeSet<String> KEYS = new TreeSet(bestKeys);
		String newKey = KEYS.first(); 
		Map newSet = new HashMap<String, TreeSet<String>>();
		boolean b = sets.containsKey(newKey);
		Set<String> allKeys = sets.keySet();
		for(String st: allKeys){
			if(newKey.equals(st)){newKey = st;}
		}
		TreeSet<String> forDebug = (TreeSet<String>) sets.get(newKey);
		newSet.put(newKey, forDebug);
		sets = newSet;
		this.CurrentKey = newKey;
	}
	
	
	public int setSize(String key){
		TreeSet<String> group = (TreeSet<String>) sets.get(key);
		return group.size();
	}
	
	int instance(String Key){
		int count = 0;
		for(int i = 0; i < this.wordLength; i++){
			if(Key.charAt(i)==(char)'-'){count++;}
		}
		return count;
	}
	
	String rightMost(Set<String> keys, int index){		Set<String> bestStrings = new TreeSet<String>();
		for(String theKey: keys){
			if(theKey.charAt(this.wordLength-1-index)==(char)'-'){bestStrings.add(theKey);}
		}
		if(bestStrings.size()==1){
			
			Set<String> allKeys = sets.keySet();
			for(String st: allKeys){
				if(bestStrings.contains(st)){return st;}
			}
			}
		else if(bestStrings.size()<1){return rightMost(keys, index+1);}
		else if(bestStrings.size()>1){return rightMost(bestStrings, index+1);}
		return null;//should never get here
	}
}