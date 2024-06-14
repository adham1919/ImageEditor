package imageProject;

import java.awt.image.BufferedImage;

public class SmoothingFilter implements Filter {
         public BufferedImage apply(BufferedImage ig)
         {
        		int height,width;
				height=ig.getHeight();
				width=ig.getWidth();
				BufferedImage ig2= new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
				int a[][] = new int[width][height];
				int r[][] = new int[width][height];
				int g[][] = new int[width][height];
				int b[][] = new int[width][height];
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
				
				for(int i=2;i<width-2;i++)
				{   
					for(int j=2;j<height-2;j++)
						
					{   int pixel=0;
						int alpha=0;
						int red=0;
						int green=0;
						int blue=0;
						for(int k=0;k<3;k++)
						{
							for(int h=0;h<3;h++)
							{
								alpha+=a[i+k][j+h]+a[i-k][j-h];
								red+=r[i+k][j+h]+r[i-k][j-h];
								green+=g[i+k][j+h]+g[i-k][j-h];
								blue+=b[i+k][j+h]+b[i-k][j-h];
							}
						}
						for(int v=1;v<3;v++) {
							for(int w=1;w<3;w++) {
						alpha+=a[i+v][j-w]+a[i-v][j+w];
						red+=r[i+v][j-w]+r[i-v][j+w];
						green+=g[i+v][j-w]+g[i-v][j+w];
						blue+=b[i+v][j-w]+b[i-v][j+w];
							}}
						alpha-=a[i][j];
						red-=r[i][j];
						green-=g[i][j];
						blue-=b[i][j];
						alpha/=25;
						red/=25;
						green/=25;
						blue/=25;
						pixel= (alpha<<24) | (red<<16) | (green<<8) | blue;
						ig2.setRGB(i, j, pixel);
					}
					
				}
				return ig2;
         }
}
