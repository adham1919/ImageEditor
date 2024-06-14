package imageProject;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
public class Resizer {
	public BufferedImage resize(BufferedImage img, int newWidth, int newHeigth)
	{
		BufferedImage newImage = new BufferedImage(newWidth,newHeigth, BufferedImage.TYPE_INT_ARGB);
		Graphics2D d= newImage.createGraphics();
		d.drawImage(img, 0, 0,newWidth,newHeigth, null);
		d.dispose();
		return  newImage;
	}
}
