package imageProject;
import java.awt.image.BufferedImage;
public interface ImageIOInterface {
	boolean search(String path);
	BufferedImage  load(String path);
	void save(BufferedImage image,String parentPath);
}
