package imageProject;
import java.awt.image.BufferedImage;
public class Cropper {
	int startX;
	int startY;
	int endX;
	int endY;
	
	public Cropper(int x1,int y1,int x2,int y2)
	{
		if(x1>x2) 
		{	endX=x1; startX=x2;  }
		else 
		{	endX=x2; startX=x1;  }
		if(y1>y2)
		{	endY=y1; startY=y2;  }
		else 
		{	endY=y2; startY=y1;  }
	}
	public BufferedImage crop(BufferedImage ig)
	{   
	try {	
		   int newWidth=endX-startX;
		   int newHeight=endY-startY;
		BufferedImage newImage= new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_ARGB);
	        
		for(int i2=0,i=startX;i<endX && i2<newWidth; i++,i2++)
			{for(int j2=0, j=startY;j<endY && j2<newHeight;j++,j2++)
				newImage.setRGB(i2, j2, ig.getRGB(i, j));}
		return newImage; }
	catch(NullPointerException n)
	{
		System.out.println("exception occured");
		return null;
	}
	}

}
