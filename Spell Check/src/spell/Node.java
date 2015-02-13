package spell;

import java.util.Arrays;
//import java.lang.*;
//import java.util.TreeSet;
import java.util.Set;
import java.util.TreeSet;

public class Node implements ITrie.INode{
	
	Node(){
		count = 0;
		nodes = new Node[128];//initialized array to fit all ASCII characters, will leave unused blank
	};

	//Class Members
	private Node[] nodes;
	private int count;
	private String word = null;
	public static Set<String> onFind = new TreeSet();
	//Class Getters and Setters
	public int getCount(){return count;}
	public void countPlusPlus(){count++;}
	@Override
	public int getValue(){return count;}
	
	//Recursive Methods
	public int getWordCount(){//return UNIQUE word count
		int uniqueWords = 0;
		if(this.count > 0){uniqueWords++;}//increments for each word
		for(int i = 'a'; i < 'z'+1; i++){//then checks children recursively
			if(this.nodes[i]==null){}
			else{uniqueWords += this.nodes[i].getWordCount();}
		}
		return uniqueWords;
	}
	
	public int getNodeCount(){//return number of nodes including root node
		int numNodes = 1;
		for(int i = 'a'; i < 'z'+1; i++){//then checks children recursively
			if(this.nodes[i]==null){}
			else{numNodes += this.nodes[i].getNodeCount();}
		}
		return numNodes;
	}
	
	public void add(StringBuilder builder){
		if(builder.length() == 0){//checks if the String Builder is empty, so that it can increment the word count
			this.count++;
			return;}
		else{char c = builder.charAt(0);
		builder.deleteCharAt(0);
			if(this.nodes[c] == null){this.nodes[c] = new Node();}//checks for child, and creates
			this.nodes[c].add(builder);//calls add on the remaining characters of the word;
			return;
		}
	}
	public Node find(StringBuilder builder){
		if(builder.length() == 0){//checks if the String Builder is empty, so that it can increment the word count
			if(this.count == 0){return null;}
			return this;}
		else{char c = builder.charAt(0);
		builder.deleteCharAt(0);
			if(this.nodes[c] == null){return null;}//checks for child, and creates
			return this.nodes[c].find(builder);//calls add on the remaining characters of the word;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + Arrays.hashCode(nodes);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)//compares pointers
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (count != other.count)
			return false;
		if (!Arrays.equals(nodes, other.nodes))
			return false;
		return true;
	}

	public void toString(StringBuilder segment, Set<String> all) {//this should return a list of stringbuilders representing each branch
		
		for(int i = 'a'; i < 'z'+1; i++){//then checks children recursively
			StringBuilder builder = new StringBuilder(segment.toString());
			StringBuilder addition = new StringBuilder("");
			if(this.nodes[i]!=null){
				builder.append((char)i);
				this.nodes[i].toString(builder, all);}
		}
		if(this.count > 0){
			onFind.add(segment.toString());
		}
		return;
	}
}