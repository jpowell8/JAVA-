package listem;

import java.io.File;
//import java.util.List;
//import java.util.Map;
import java.util.regex.*;

public abstract class Superclass {

	
	public void traverse(File directory,
			String fileSelectionPattern, String substringSelectionPattern,
			boolean recursive){
		
		
		for(File f: directory.listFiles()){
			
			if(f.isDirectory()){
				if(recursive){
					traverse(f, fileSelectionPattern, substringSelectionPattern, recursive);
				}
			}
			else if(f.isFile()){
				Pattern p = Pattern.compile(fileSelectionPattern);
				Matcher m = p.matcher(f.getName());
				if(m.matches()){
					System.out.print("FILENAME "+f.getName()+"\n");
					processFile(f, fileSelectionPattern, substringSelectionPattern, recursive);
				}
			}
		}		
	}

	
	abstract void processFile(File directory,
			String fileSelectionPattern, String substringSelectionPattern,
			boolean recursive);

}