//import java.io.*;
import java.util.Scanner;
import java.lang.Math;

public class Image {

	public Pixel[][] pixelArray;
	public int width;
	public int height;
	public int colorScheme;
	public String P3;  
	
	int r, g, b;
	Pixel p = new Pixel( r, g, b);
	
	public Image(Scanner scan){
		P3 = scan.next();
		width = scan.nextInt(); 
		height = scan.nextInt();
		colorScheme = scan.nextInt();
		pixelArray = new Pixel[height][width];
	
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				p = new Pixel();
				p.setRed(scan.nextInt());
				p.setGreen(scan.nextInt());
				p.setBlue(scan.nextInt());
				pixelArray[i][j] = p;
			}
		}
		scan.close();
	}
	public void grayscale(){
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				int gray = (pixelArray[i][j].getRed() + pixelArray[i][j].getGreen() + pixelArray[i][j].getBlue())/3;
				pixelArray[i][j].setRed(gray);
				pixelArray[i][j].setGreen(gray);
				pixelArray[i][j].setBlue(gray);
			}
		}
	} 
	public void invert(){
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				pixelArray[i][j].setRed(255 - pixelArray[i][j].getRed());
				pixelArray[i][j].setGreen(255 - pixelArray[i][j].getGreen());
				pixelArray[i][j].setBlue(255 - pixelArray[i][j].getBlue());
			}
		}
	}
	public void emboss(){
		
		for(int i = height-1; i > 0; i--){
			for(int j = width-1; j > 0; j--){
				int redDiff = pixelArray[i][j].getRed() - pixelArray[i-1][j-1].getRed();
				int greenDiff = pixelArray[i][j].getGreen() - pixelArray[i-1][j-1].getGreen();
				int blueDiff = pixelArray[i][j].getBlue() - pixelArray[i-1][j-1].getBlue();
				int Max = getMax(redDiff, greenDiff, blueDiff);
				int v = 128 + Max;
				
				if(v < 0){v = 0;}
				if(v > 255){v = 255;}
				pixelArray[i][j].setRed(v);
				pixelArray[i][j].setGreen(v);
				pixelArray[i][j].setBlue(v);
			}
		}
		int V = 128;
		for(int i = 0; i < height; i++){
			pixelArray[i][0].setRed(V);
			pixelArray[i][0].setGreen(V);
			pixelArray[i][0].setBlue(V);
		}
		for(int i = 0; i < width; i++){
			pixelArray[0][i].setRed(V);
			pixelArray[0][i].setGreen(V);
			pixelArray[0][i].setBlue(V);
		}		
	}
	public void motionblur(String blurLength){
		int blur = Integer.parseInt(blurLength);
		if (blur <= 1){return;}
		if (blur > width){blur = width;}
		
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				int redAvg = pixelArray[i][j].getRed();
				int greenAvg = pixelArray[i][j].getGreen();
				int blueAvg = pixelArray[i][j].getBlue();
				int divisionCounter = 1;
				for(int n = 1; n < Math.min(blur, (width - j)) ; n++){
					divisionCounter++;
					redAvg += pixelArray[i][j+n].getRed();
					greenAvg += pixelArray[i][j+n].getGreen();
					blueAvg += pixelArray[i][j+n].getBlue();
				}
				pixelArray[i][j].setRed(redAvg / divisionCounter);
				pixelArray[i][j].setGreen(greenAvg / divisionCounter);
				pixelArray[i][j].setBlue(blueAvg / divisionCounter);
			}
		}
	}
	
	public static int getMax(int a, int b, int c){
		if(Math.abs(a)>=Math.abs(b)&&Math.abs(a)>=Math.abs(c)){return a;}
		else if(Math.abs(b)>Math.abs(a)&&Math.abs(b)>=Math.abs(c)){return b;}
		else {return c;}
	}
	
}
