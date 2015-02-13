package spell;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

//import java.lang.*;
//import java.util.*;

public class Trie implements ITrie {

	Node root = new Node();
	
	public boolean contains(String word){
		StringBuilder builder = new StringBuilder(word);
		if(root.find(builder)==null){return false;}
		return true;
	}
	
	@Override
	public void add(String word) {
		word = word.toLowerCase();
		StringBuilder builder = new StringBuilder(word);
		root.add(builder);
	}

	@Override
	public INode find(String word) {
		word = word.toLowerCase();
		if(contains(word)){
			StringBuilder builder = new StringBuilder(word);
			return root.find(builder);
		}
		return null;
	}

	@Override
	public int getWordCount() {return root.getWordCount();}

	@Override
	public int getNodeCount() {return root.getNodeCount();}
	
	@Override
	public String toString() {
		Node.onFind = new TreeSet();
		Set<String> OutString = new TreeSet();
		StringBuilder segment = new StringBuilder("");
		StringBuilder AllString = new StringBuilder("");
		root.toString(segment, OutString);
		Set<String> allWords = Node.onFind;
				
		
		Iterator itr = allWords.iterator();
		while(itr.hasNext()){
			AllString.append(itr.next());
			AllString.append("\n");
		}
		return AllString.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * this.getNodeCount()+this.getWordCount() + ((root == null) ? 0 : root.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trie other = (Trie) obj;
		if (this.getNodeCount() != other.getNodeCount()){return false;}
		if (this.getWordCount() != other.getWordCount()){return false;}
		if (root == null) {
			if (other.root != null)
				return false;
		} else if (!root.equals(other.root))
			return false;
		return true;
	}
	
}