import java.util.Scanner;
import java.io.*;
//import java.lang.Object;
//import java.nio.file.Path;
import java.nio.file.Paths;

public class IO {

	public IO(){
	}
	
	public static Scanner loadImage(String fileName){
		//Scanner Scan = new Scanner(BufferedInputStream(FileInputStream(File(fileName))));
		Scanner Scan = null;
		try {
			Scan = new Scanner(Paths.get(fileName));
			Scan.useDelimiter("(\\s+)(#[^\\n]*\\n)?(\\s*)|(#[^\\n]*\\n)(\\s*)");
			return Scan;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Failed in IO 22");
		}
		return Scan;
	}
	
	public static void imageWriter(Image myImage, String outFile){
		StringBuilder builder = new StringBuilder();
		builder.append(myImage.P3);
		builder.append(" ");
		builder.append(Integer.toString(myImage.width));
		builder.append(" ");
		builder.append(Integer.toString(myImage.height));
		builder.append(" ");
		builder.append("255");
		builder.append(" ");
		for(int i = 0; i < myImage.height; i++){
			for(int j = 0; j < myImage.width; j++){
				builder.append(Integer.toString(myImage.pixelArray[i][j].getRed()));
				builder.append(" ");
				builder.append(Integer.toString(myImage.pixelArray[i][j].getGreen()));
				builder.append(" ");
				builder.append(Integer.toString(myImage.pixelArray[i][j].getBlue()));
				builder.append(" ");
			}
		}
		String completedString = builder.toString();
		PrintWriter PW;
		try {
			PW = new PrintWriter(outFile);
			PW.write(completedString);
			PW.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
