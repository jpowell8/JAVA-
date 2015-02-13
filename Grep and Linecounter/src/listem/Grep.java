package listem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep extends Superclass implements IGrep {

	public Grep(){}

	public Map<File, List<String>> map = new HashMap<File, List<String>>();
	
	
	@Override
	public void processFile(File directory, String fileSelectionPattern,
			String substringSelectionPattern, boolean recursive) {
		List<String> stringList = new ArrayList<String>();

		try {
			Scanner scan = new Scanner(directory);
			while(scan.hasNextLine()){
				Pattern p = Pattern.compile(substringSelectionPattern);
				String next = scan.nextLine();
				Matcher m = p.matcher(next);
				if(m.find()){
					System.out.print("The Line - "+next+ "\n");
					stringList.add(next);}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(!stringList.isEmpty()){
		this.map.put(directory, stringList);}
		return;
	}

	@Override
	public Map<File, List<String>> grep(File directory,
			String fileSelectionPattern, String substringSelectionPattern,
			boolean recursive) {
		if(this.map != null)
			{this.map.clear();}
		traverse(directory, fileSelectionPattern, substringSelectionPattern, recursive);
		return map;

	}
}
