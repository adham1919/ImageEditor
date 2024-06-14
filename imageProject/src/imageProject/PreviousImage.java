package imageProject;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class PreviousImage {
	private BufferedImage[] history = new BufferedImage[1000];
	private int imgCount = 0;
    private  BufferedImage clearImage=new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
	public void add(BufferedImage img)
	{
	
		history[imgCount]=img;
		imgCount++;
	}
	
	public void clear()
	{
		imgCount = 0;
	}
	
	public int getimgCount()
	{
		return imgCount;
	}
	
	public BufferedImage undo()
	{
		
			imgCount--;
			return history[imgCount-1];
	
	}
	
	public void setClearImage(BufferedImage m)
	{
		clearImage=m;
	}
	
	public BufferedImage getClearImage()
	{
		return clearImage;
	}
	public boolean checkList()
	{
		if(imgCount > 1)
			return true;
		else return false;
	}
}
