package spell;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SpellCorrector implements ISpellCorrector {

	private Set<String> set = new TreeSet();
	public Trie trie = new Trie();
	
	public static void SpellCorrector(){
	};

	@Override
	public void useDictionary(String dictionaryFileName) throws IOException {
		try{
		trie = new Trie();
		Scanner Scan = new Scanner(Paths.get(dictionaryFileName));
		while(Scan.hasNext()){
		trie.add(Scan.next().toLowerCase());
		}Scan.close();
		}
		catch(Exception e){
			throw new IOException();
		}

	}

	@Override
	public String suggestSimilarWord(String inputWord)
			throws NoSimilarWordFoundException {
		inputWord = inputWord.toLowerCase();
		if(trie.contains(inputWord.toLowerCase())){return inputWord.toLowerCase();}
		else{
			Set<String> firstSet = editWordDistOne(inputWord.toLowerCase());
			removeGibberish(firstSet);
			Iterator<String> itr = firstSet.iterator();
			String best = "";
			int number = 0;
			if(firstSet.size() < 1){
				Set<String>	newFirstSet = editWordDistOne(inputWord.toLowerCase());
				Set<String> secondSet = editWordDistTwo(newFirstSet);
			Set<String> tree = new TreeSet<String>();
			tree = removeGibberish(secondSet);
			itr = tree.iterator();
			}
			while(itr.hasNext()){
				String string = itr.next();
				if(getCount(string) > number){
					best = string;
					number = getCount(string);
				}
			}
			if(best.equals("")){throw new NoSimilarWordFoundException();}
		return (best);	
		}
	}
	
	private Set<String> editWordDistOne(String word){
		StringBuilder builder = new StringBuilder(word);
		Set<String> D1 = new HashSet();
		D1.addAll(DD(builder));
		D1.addAll(TD(builder));
		D1.addAll(AD(builder));
		D1.addAll(ID(builder));
		return D1;
	}
	private Set<String> editWordDistTwo(Set<String> D1){
		Set<String> D2 = new HashSet();
		Iterator itr = D1.iterator();
		while(itr.hasNext()){
		StringBuilder builder = new StringBuilder((String)itr.next());//Possible error with casting
		D2.addAll(DD(builder));
		D2.addAll(TD(builder));
		D2.addAll(AD(builder));
		D2.addAll(ID(builder));
		}
		return D2;
	}
	private int getCount(String string){
		if(trie.contains(string)){
		return (trie.find(string)).getValue();
		}
		else return 0;
	}
	
	private Set<String> removeGibberish(Set<String> HS){
		Iterator itr = HS.iterator();
		while(itr.hasNext()){
			if(!trie.contains((String)itr.next())){
			itr.remove();	
			}
		}
		Set<String> TS = new TreeSet<String>();
		TS.addAll(HS);
		return TS;
	}

	private Set<String> DD(StringBuilder word){
		Set<String> dd = new HashSet<String>();
		int length = word.length();
		for(int i = 0; i < length; i++){
			StringBuilder build = new StringBuilder(word.toString());
			String string = (build.deleteCharAt(i)).toString();
			if(string.length()>0){
			dd.add(string);}
		}
		return dd;}
	
	private Set<String> TD(StringBuilder word){
		Set<String> td = new HashSet<String>();
		int length = word.length();
		for(int i = 0; i < length-1; i++){//delete -1
			StringBuilder build = new StringBuilder(word.toString());
			char one = build.charAt(i);
			char two = build.charAt(i+1);
			build.setCharAt(i, two);
			build.setCharAt(i+1, one);
			td.add(build.toString());
		}
		return td;}
	
	private Set<String> AD(StringBuilder word){
		Set<String> ad = new HashSet<String>();
		int length = word.length();
		for(int i = 0; i < length; i++){
			for(int j = 'a'; j < 'z'+1; j++){//then checks children recursively
				if((char)j!=word.charAt(i)){
					StringBuilder build = new StringBuilder(word.toString());
					build.setCharAt(i, (char)j);
					String newWord = build.toString();// insert((char)j, i)).deleteCharAt(i+1)).toString();
					ad.add(newWord);
				}
			}
		}
		return ad;}
	
	private Set<String> ID(StringBuilder word){
		Set<String> id = new HashSet<String>();
		int length = word.length();
		for(int i = 0; i < length; i++){//delete -1
			for(int j = 'a'; j < 'z'+1; j++){//then checks children recursively
				StringBuilder build = new StringBuilder(word.toString());
				build.insert(i, (char)j);
				String newWord = build.toString();// insert((char)j, i)).deleteCharAt(i+1)).toString();
				id.add(newWord);
			}
		}
		for(int j = 'a'; j < 'z'+1; j++){//then checks children recursively
			StringBuilder build = new StringBuilder(word.toString());
			build.append((char)j);
			String newWord = build.toString();// insert((char)j, i)).deleteCharAt(i+1)).toString();
			id.add(newWord);
		}
		return id;}
}
