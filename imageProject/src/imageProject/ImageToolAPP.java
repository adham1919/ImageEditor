package imageProject;

import java.awt.EventQueue;

public class ImageToolAPP {
       public static void main(String arg[])
       {
    	   EventQueue.invokeLater(new Runnable()
   		{
   			public void run()
   			{
   				try
   				{
   					AppGUI window = new AppGUI();
   					
   				} 
   				
   				catch (Exception e) 
   				{
   					e.printStackTrace();
   				}
   			}
    	   
   		});
    	   }
       }
