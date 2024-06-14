package imageProject;
import java.awt.image.BufferedImage;
public class SharpenFilter implements Filter {
     public BufferedImage apply(BufferedImage ig)
     {
    	    int height,width;
			height=ig.getHeight();
			width=ig.getWidth();
			BufferedImage ig2= new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
			BufferedImage ig3= new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
			int a[][] = new int[width][height];
			int r[][] = new int[width][height];
			int g[][] = new int[width][height];
			int b[][] = new int[width][height];
			int as[][] = new int[width][height];
			int rs[][] = new int[width][height];
			int gs[][] = new int[width][height];
			int bs[][] = new int[width][height];
			
			for(int i=0;i<width;i++)
			{   
				for(int j=0;j<height;j++)
				{
					int p=ig.getRGB(i, j);
					a[i][j]= (p>>24) & 0xff;
					r[i][j]= (p>>16) & 0xff;
					g[i][j]= (p>>8) & 0xff;
					b[i][j]= (p) & 0xff;
				}
			}
			Filter filter = new SmoothingFilter();
			ig2=filter.apply(ig);
			for(int i=0;i<width;i++)
			{   
				for(int j=0;j<height;j++)
				{
					int p=ig2.getRGB(i, j);
					as[i][j]= (p>>24) & 0xff;
					rs[i][j]= (p>>16) & 0xff;
					gs[i][j]= (p>>8) & 0xff;
					bs[i][j]= (p) & 0xff;
				}
				
			}
			
			for(int i=0;i<width;i++)
			{   
				for(int j=0;j<height;j++)
				{       int alpha=0;
			            int red=0;
			            int green=0;
			            int blue=0;
			            int pixel= 0;
				     alpha=2*a[i][j]-as[i][j];
				     red=2*r[i][j]-rs[i][j];
				     green=2*g[i][j]-gs[i][j];
				     blue=2*b[i][j]-bs[i][j];
				     if(red>255) { red=255;}
				     else if(red<0) {red=0;}
				     
				     if(green>255) { green=255;}
				     else  if(green<0) {green=0;}
				     
				     if(blue>255) { blue=255;}
				     else if(blue<0) {blue=0;}
				     
				     pixel= (alpha<<24) | (red<<16) | (green<<8) | blue;
					ig3.setRGB(i, j, pixel);
				    
				}
			}
			return ig3;
     }
}
