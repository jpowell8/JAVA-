import java.util.Scanner;

public class Photo {

	public static void main(String[] args){
		Scanner scan = IO.loadImage(args[0]);
		Image myImage = new Image(scan);
		
		switch (args[2]) {
		case "grayscale": myImage.grayscale();
			break;
		case "invert": myImage.invert();
			break;
		case "emboss": myImage.emboss();
			break;
		case "motionblur": myImage.motionblur(args[3]);
			break;
		default : 
			break;
		}
		
		IO.imageWriter(myImage, args[1]);
	}

	
	
}
