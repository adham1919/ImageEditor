package imageProject;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class PNGIOImage implements ImageIOInterface {
	public BufferedImage  load(String path)  
	{ 
		File imageFile =new File(path);
		
		BufferedImage currentImage;
		try {
			currentImage = ImageIO.read(imageFile);
			return currentImage;
		} catch (IOException e) {
			
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	public boolean search(String path)
	{   File imageFile =new File(path);
		if(path.endsWith(".png") && imageFile.exists())
		return true;
		else return false;
	}
	public void save(BufferedImage image,String parentPath) 
	{  
    
    	int fileNumber=1; 
    	while(true){
    	File tempFile= new File(parentPath+"\\image_Tool_app("+fileNumber+").png");
    	if(!tempFile.exists()) break;
    	fileNumber++;
    	}
		try {
			File imageFile= new File(parentPath+"\\image_Tool_app("+fileNumber+").png");
			ImageIO.write(image,"png",imageFile);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
