package imageProject;
import java.awt.image.BufferedImage;
public class MyImage {
	private String parentPath;
	private BufferedImage myImage=new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
	
	public void setImage(BufferedImage anImage)
	{
		myImage=anImage;
	}
	public void setParentPath(String givenPath)
	{
		parentPath=givenPath;
	}
	public BufferedImage getImage()
	{
		return myImage;
	}
	public String getParentPath()
	{
		return parentPath;
	}
	
}
