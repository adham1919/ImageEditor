package imageProject;
import java.awt.image.BufferedImage;
public class Rotator {
	public BufferedImage rotate(BufferedImage image,int direction)
	{
		
		int height=image.getHeight();
		int width=image.getWidth();
		BufferedImage temp=new BufferedImage(height,width,BufferedImage.TYPE_INT_ARGB);
		int pixelArray[][] =new int[height][width];
		for(int i=0;i<width; i++)
			for(int j=0;j<height;j++)
			 pixelArray[j][i]=image.getRGB(i, j);
		if(direction==0) {
		for(int i=0,i2=height-1;i<height; i++,i2--)
			for(int j=0,j2=width-1;j<width;j++,j2--)
				temp.setRGB(i2, j, pixelArray[i][j]);}
		else {
			for(int i=0,i2=height-1;i<height; i++,i2--)
				for(int j=0,j2=width-1;j<width;j++,j2--)
					temp.setRGB(i, j2, pixelArray[i][j]);}
		return temp;
		}
}
