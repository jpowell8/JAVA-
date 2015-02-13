package listem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineCounter extends Superclass implements ILineCounter{

	public LineCounter(){}
	
	public Map<File, Integer> map = new HashMap<File, Integer>();

	@Override
	public Map<File, Integer> countLines(File directory,
			String fileSelectionPattern, boolean recursive) {
		if(this.map != null){this.map.clear();}
		traverse(directory, fileSelectionPattern, fileSelectionPattern, recursive);
		return this.map;
	}
	
	public void processFile(File directory, String fileSelectionPattern,
			String substringSelectionPattern, boolean recursive) {
				
		
		Pattern myPattern = Pattern.compile(fileSelectionPattern);
		Matcher m = myPattern.matcher(fileSelectionPattern);
		if(m.matches()){
			List<String> lines;
			try {
				lines = Files.readAllLines(directory.toPath(), Charset.defaultCharset());
				int noOfLines = lines.size();
				if(noOfLines > 0){
				this.map.put(directory, noOfLines);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return;
	}
}
