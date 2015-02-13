
public class Pixel {

	public int R;
	public int G;
	public int B;
	
	public Pixel(int r, int g, int b){
		R = r;
		G = g;
		B = b;
	}
	public Pixel(){
		R = 0;
		G = 0;
		B = 0;
	}
	
	
	public void setRed(int r){R = r;}
	public void setGreen(int g){G = g;}
	public void setBlue(int b){B = b;}
	public int getRed(){return R;}
	public int getGreen(){return G;}
	public int getBlue(){return B;}
}
