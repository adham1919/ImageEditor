package imageProject;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
//////////////////////////***********libraries********************///////////////////////////////////////////////////
public class AppGUI extends JFrame {
		   /**********************attributes*********************/
		   private JButton   resizeButton =new JButton("resize");
		   private  JButton   cropButton =new JButton("crop");
		   private  JButton   rotateButton =new JButton("rotate");
		   private   JButton   loadButton =new JButton("load");
		   private  JButton   saveButton =new JButton("save");
		   private  JButton BlackNWhiteButton=new JButton("black and white");
		   private  JButton sharpenButton=new JButton("sharpen");
		   private  JButton smoothingButton=new JButton("smoothing");
		   private    JButton edgeButton= new JButton("edge");
		   private   JButton ASCIIButton= new JButton("create ASCII Writer");
		   private   JTextField text1=new JTextField();
		   private   JTextField text2=new JTextField();
		   private    JTextField text3=new JTextField();
		   private    JPanel panel=new JPanel();
		   private   JPanel imagePanel=new JPanel();
		   private   JLabel imageLabel= new JLabel();
		   private    int locVer,locHor;
		   private    JScrollBar hbar=new JScrollBar(JScrollBar.HORIZONTAL, 0, 20, 0, 950);
		   private   JScrollBar vbar=new JScrollBar(JScrollBar.VERTICAL, 0, 40, 0, 950);
		   private    JMenu menu = new JMenu("choose direction");
		   private   ImageIcon imageIcon=new ImageIcon();
		   private  MyImage chosenImage=new MyImage();
		   private   PreviousImage storage=new PreviousImage();
		   /**********************attributes*********************/
		   /**********************constructor***********************/
		   public AppGUI()
		   {
			   setTitle("image Toolbox");
			   setSize(1370,730);
			   JFrame j = new JFrame();
			   setLocation(0,2);
			   setLayout(new BorderLayout( ));
			   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			   createWindow();
		       hbar.addAdjustmentListener(new MyAdjustmentHorListener( ));
		       vbar.addAdjustmentListener(new MyAdjustmentVerListener( ));
		       panel.setPreferredSize(new Dimension(1365, 62));
			   add(vbar, BorderLayout.EAST);
			   add(hbar,BorderLayout.SOUTH);
			   add(panel,BorderLayout.NORTH);
			   add(imagePanel,BorderLayout.CENTER);
			   setVisible(true);
		   }
		   /**********************constructor***********************/
		   /********************scroll Adjuster***********************/
		   class MyAdjustmentVerListener implements AdjustmentListener {
		       public void adjustmentValueChanged(AdjustmentEvent e) throws NullPointerException {
		    	   locVer=e.getValue();
		    	   imagePanel.remove(0);
		    	   imageLabel.setBounds(250-locHor, 0-locVer, chosenImage.getImage().getWidth(), chosenImage.getImage().getHeight());
		    	   imagePanel.add(imageLabel);
		    	   imagePanel.updateUI(); 
		       }
		   }
		   class MyAdjustmentHorListener implements AdjustmentListener  {
		       public void adjustmentValueChanged(AdjustmentEvent e) throws NullPointerException {
		    	   locHor=e.getValue();
		    	   imagePanel.remove(0);
		    	   imageLabel.setBounds(250-locHor, 0-locVer, chosenImage.getImage().getWidth(), chosenImage.getImage().getHeight());
		    	   imagePanel.add(imageLabel);
		    	   imagePanel.updateUI(); 
		       }
		   }
		   /********************scroll Adjuster***********************/
		   /*********************window builder**********************/
		   private void createWindow()
		   {   
			   
			   JMenuBar menuBar= new JMenuBar();
			   JButton undo=new JButton("undo");
			   JButton filter=new JButton("filter");
			   panel.setLayout(null);
			   imagePanel.setLayout(null);
			   menuBar.setBounds(0, 0, 1600, 30);
			   loadButton.addActionListener(new LoadButtonListener());
			   menuBar.add(loadButton);
			   saveButton.addActionListener(new SaveButtonListener());
			   menuBar.add(saveButton);
			   rotateButton.addActionListener(new RotateButtonListener());
			   menuBar.add(rotateButton);
			   resizeButton.addActionListener(new ResizeButtonListener());
			   menuBar.add(resizeButton);
			   cropButton.addActionListener(new CropButtonListener());
			   menuBar.add(cropButton);
			   filter.addActionListener(new FilterButtonListener());
			   menuBar.add(filter);
			   undo.addActionListener(new UndoButtonListener());
			   menuBar.add(undo);
			   ASCIIButton.addActionListener(new ASCIILisenter());
			   menuBar.add(ASCIIButton);
			   panel.add(menuBar);
			   JMenuBar menuBar2= new JMenuBar();
			   menuBar2.setBounds(0, 31,1600, 30);
			   panel.add(menuBar2);
			   imageLabel.setBounds(0, 0, 1600, 1000);
		       imagePanel.add(imageLabel);
		   }
		   /*********************window builder**********************/
		/************************************************load****************************************************/
		   private class ASCIILisenter implements ActionListener
		   {
				public void actionPerformed(ActionEvent e)  throws NullPointerException
				{   ASCIIWriter writer =new ASCIIWriter();
					if(storage.getimgCount()>0)
					{
						JOptionPane.showMessageDialog(null, "- Zoom out to see the result.\n- Looks best on a black backgorund with white letters.\n"
								+ "- Temporarily inverting your colors from the control panel is recommended.\n- Text file saved to the same folder with writer.txt please rename the file so it wont be overwrited the next time you use the ascii writer\n Thanks for using :).", "Message", JOptionPane.INFORMATION_MESSAGE);
						writer.WriteTXT(chosenImage.getImage(), chosenImage.getParentPath()+"\\writer.png" );
					}
					else
					{
						errorMenu("error Load an image first! ");
					}
				} 
		   }
		   private class LoadButtonListener implements ActionListener
		   {
			   public void actionPerformed(ActionEvent e)  throws NullPointerException
			   {   
				  openPathMenu();
			   }  
		   }
		   private void openPathMenu()
		   {
			   JLabel textLabel=new JLabel(" enter the path");
			   JButton loadPerformer =new JButton("go"); 
			   JButton choosePerformer =new JButton("choose file");
			   JMenuBar loadMenuBar= new JMenuBar();
			   loadMenuBar.setLayout(null);
			   loadMenuBar.setBounds(0, 31,1600, 30);
			   textLabel.setBounds(0, 0, 83, 28);
			   text1.setBounds(85, 0, 318, 28);
			   loadPerformer.setBounds(405, 0, 60, 28);
			   choosePerformer.setBounds(466, 0, 102, 28);
			   loadMenuBar.add(textLabel);
			   loadMenuBar.add(text1);
			   loadMenuBar.add(choosePerformer);
			   choosePerformer.addActionListener(new choosePerformerListener() );
			   loadPerformer.addActionListener(new LoadPerformerListener()); 
			   loadMenuBar.add(loadPerformer);
			   panel.remove(1);
			   panel.add( loadMenuBar);
			   panel.updateUI(); 
		   }
		   
		   private class choosePerformerListener implements ActionListener
		   {  
			   public void actionPerformed(ActionEvent e)  throws NullPointerException
			   {
				  FileDialog chooser = new FileDialog(AppGUI.this);
				  chooser.setFile("C:\\Users\\theda");
				  chooser.setVisible(true);
				  text1.setText(chooser.getDirectory()+chooser.getFile()); 
			   }
		}
		     
		   private class LoadPerformerListener implements ActionListener
		   {  
			   public void actionPerformed(ActionEvent e) 
			   { try {
				   ImageIOInterface imageio = new PNGIOImage();
				 if(!imageio.search(text1.getText())) 
					 {errorMenu("sorry either the file doesnt exist or not of PNG format");
					  return;
					 }
				 File tempFile = new File(text1.getText());
				 chosenImage.setParentPath(tempFile.getParent());
				 storage.clear();
				 chosenImage.setImage(imageio.load(text1.getText()));			
				 storage.add(chosenImage.getImage());
				 storage.setClearImage(chosenImage.getImage());
				 display();}
			   catch(NullPointerException n)
			   {
				   System.out.println("error, please wait");
			   }
				 
			   }  
		   }
		   /************************************************load****************************************************/ 
		   /************************************************save****************************************************/ 
		   private class SaveButtonListener implements ActionListener
		   {
			   
			   public void actionPerformed(ActionEvent e) throws NullPointerException
			   {   if(storage.getimgCount()==0) { errorMenu("please load an image first"); return; }
				  ImageIOInterface imageio = new PNGIOImage();
				  imageio.save(chosenImage.getImage(), chosenImage.getParentPath());
				  JOptionPane.showMessageDialog(null, "File have been saved in the same location "
				  		+ "thanks for using :)");
			   }  
		   }
		  /************************************************save****************************************************/ 
		   /************************************************resize****************************************************/
		   private class ResizeButtonListener implements ActionListener
		   {
			   public void actionPerformed(ActionEvent e)   throws NullPointerException
			   {   if(storage.getimgCount()==0) { errorMenu("please load an image first"); return; }
				   openResizeMenu();
			   }  
		   }
		   
		   private void openResizeMenu()
		   {
			   JLabel textLabel1 = new JLabel("enter the new width ");
			   JLabel textLabel2 = new JLabel("enter the new height ");
			   JButton resizeChangeButton = new JButton("change");
			   JMenuBar resizeMenuBar= new JMenuBar();
			   resizeMenuBar.setLayout(null);
			   resizeMenuBar.setBounds(0, 31,1600, 30);
			   textLabel1.setBounds(0, 0, 120, 28);
			   text3.setBounds(123, 0, 70, 28);
			   textLabel2.setBounds(303, 0, 122, 28);
			   text2.setBounds(425, 0, 70, 28);
			   resizeChangeButton.setBounds(540, 0, 77, 28);
			   if(resizeChangeButton.getActionListeners().length<1)
			   resizeChangeButton.addActionListener(new ResizeChangeListener());
			   resizeMenuBar.add(textLabel1);
			   resizeMenuBar.add(text3);
			   resizeMenuBar.add(textLabel2);
			   resizeMenuBar.add(text2);
			   resizeMenuBar.add(resizeChangeButton);
			   panel.remove(1);
			   panel.add(resizeMenuBar);
			   panel.updateUI(); 
		   }
		   
		   private class ResizeChangeListener implements ActionListener
		   {
			   public void actionPerformed(ActionEvent e) 
			   {   try {  
				  String width,height;
				  width=text3.getText();
				  height=text2.getText();
				  int w=Integer.parseInt(width);
				  int h=Integer.parseInt(height);
				  if(w>=1920|| h>=1920 || h<=0 || w<=0) 
					  {errorMenu("sorry you should choose dimension between 1920 and 0");
					  return;
					  }
				  Resizer resizer= new Resizer();
				  chosenImage.setImage(resizer.resize(chosenImage.getImage(), w, h));
				  storage.add(chosenImage.getImage());
				  storage.setClearImage(resizer.resize(storage.getClearImage(), w, h));
				  display();}
			   catch(NullPointerException n)
			   {
				   System.out.println("error, please wait");
			   }
			   }  
		   }
		   /************************************************resize****************************************************/
		   /************************************************rotate****************************************************/
		   private class RotateButtonListener implements ActionListener
		   {   
			   public void actionPerformed(ActionEvent e)  throws NullPointerException
			   {   if(storage.getimgCount()==0) { errorMenu("please load an image first"); return; }
				   openRotateMenu();
			   }  
		   }
		   
		   private void openRotateMenu()
		   {  
			   JMenuBar rotateMenuBar= new JMenuBar();
			   rotateMenuBar.setBounds(0, 31,1600, 30);
			   JMenuItem left = new JMenuItem("rotate left");
			   JMenuItem right = new JMenuItem("rotate right");
			   if(left.getActionListeners().length<1)
			   left.addActionListener(new RotateLeftListener());
			   if(right.getActionListeners().length<1)
			   right.addActionListener(new RotateRightListener());;
			   if(menu.getMenuComponentCount()==0)
			   {
				   menu.add(left);
				   menu.add(right);
			   }
			   menu.setVisible(true);
			   rotateMenuBar.add(menu);
			   panel.remove(1);
			   panel.add(rotateMenuBar);
			   panel.updateUI(); 
		   }
		   
		   private class RotateLeftListener implements ActionListener
		   { 
			   public void actionPerformed(ActionEvent e)  throws NullPointerException
			   {   try {
				   
				  Rotator rotator = new Rotator();
				  chosenImage.setImage(rotator.rotate(chosenImage.getImage(), 1));
				  storage.add(chosenImage.getImage());
				  storage.setClearImage(rotator.rotate(storage.getClearImage(), 1));
				  display();}
			   catch(NullPointerException n)
			   {
				   System.out.println("error,please wait");
			   }
			   }  
		   }
		   
		   
		   private class RotateRightListener implements ActionListener
		   {
			   public void actionPerformed(ActionEvent e)  
			   { try {   
				       Rotator rotator = new Rotator();
					  chosenImage.setImage(rotator.rotate(chosenImage.getImage(), 0));
					  storage.add(chosenImage.getImage());
					  storage.setClearImage(rotator.rotate(storage.getClearImage(), 0));
					  display();}
			   catch(NullPointerException n)
			   {
				   System.out.println("error,please wait");
			   }
			   }  
		   }
		   /************************************************rotate****************************************************/
		   /************************************************crop****************************************************/
		   private class CropButtonListener implements ActionListener
		   {
			   public void actionPerformed(ActionEvent e)   
			   { try {  if(storage.getimgCount()==0) { errorMenu("please load an image first"); return; }
				   JLabel textLabel1 = new JLabel("please click on the top left corner and right bottom corner of the wanted "
				   		+ "cropped region   ");
				   JButton cropPerform= new JButton("perform");
				   JMenuBar cropMenuBar= new JMenuBar();
				   cropMenuBar.setBounds(0, 31,1600, 30);
				   cropMenuBar.add(textLabel1);
				   mouseClick c=new mouseClick();
				   imageLabel.addMouseListener(c);
				   if(cropPerform.getActionListeners().length<1)
				   cropPerform.addActionListener(new CropPerformListener(c));
				   cropMenuBar.add(cropPerform);
				   panel.remove(1);
				   panel.add(cropMenuBar);
				   panel.updateUI(); }
			   catch(NullPointerException n)
			   {
				   System.out.println("please wait");
			   }
			   }
		   }
		   
		   private class CropPerformListener implements ActionListener
		   {    mouseClick clicks= new mouseClick();
		      public CropPerformListener(mouseClick c2)
		      {
		    	  clicks=c2;
		      }
			   public void actionPerformed(ActionEvent e)  
			   {  
				 try {
				   int x1=clicks.xloc1;
				   int y1=clicks.yloc1;
				   int x2=clicks.xloc2;
				   int y2=clicks.yloc2;
				   Cropper cropper= new Cropper(x1, y1, x2, y2);
				   chosenImage.setImage(cropper.crop(chosenImage.getImage()));
				   storage.add(chosenImage.getImage());
				   storage.setClearImage(cropper.crop(storage.getClearImage()));
				   display();
				   clicks.setClick(0);}
				 catch(NullPointerException n)
				 {
					 System.out.println("error,please wait");
				 }
			   }  
		   }
		   
		   private class mouseClick extends MouseAdapter
		   {  int click;
		      int xloc1;
		      int yloc1;
		      int xloc2;
		      int yloc2;

		  public 	mouseClick()
			{
				click=0;
			}
			public void mouseClicked(MouseEvent e)  throws NullPointerException {
				click++;
				if(click==1)
					{
					   xloc1=e.getX();
					   yloc1=e.getY();
					   
					}
				
					
				if(click==2) {
					   xloc2=e.getX();
					   yloc2=e.getY();
				}
			}
			public void setClick(int n)     { click=n;          }
			public int getClick()           { return click;       }   
		   }
		   
		   /************************************************crop****************************************************/
		   /************************************************filters****************************************************/
		   private class FilterButtonListener implements ActionListener
		   {
			   public void actionPerformed(ActionEvent e)  throws NullPointerException
			   {   
				   if(storage.getimgCount()==0) { errorMenu("please load an image first"); return; }
				   JMenuBar filterMenuBar= new JMenuBar();
				   JButton clearFilter= new JButton("clear filter");
				   filterMenuBar.setBounds(0, 31,1600, 30);
				   if(sharpenButton.getActionListeners().length<1)
				   sharpenButton.addActionListener(new SharpenButtonListener());
				   filterMenuBar.add(sharpenButton);
				   if(smoothingButton.getActionListeners().length<1)
				   smoothingButton.addActionListener(new SmoothingButtonListener());
				   filterMenuBar.add(smoothingButton);
				   if(BlackNWhiteButton.getActionListeners().length<1)
				   BlackNWhiteButton.addActionListener(new BlackNWhiteButtonListener());
				   filterMenuBar.add(BlackNWhiteButton);
				   if(edgeButton.getActionListeners().length<1)
				   edgeButton.addActionListener( new EdgeButtonListener()); 
				   filterMenuBar.add(edgeButton);
				   if(clearFilter.getActionListeners().length<1)
				   clearFilter.addActionListener(new  ClearButtonListener());
				   filterMenuBar.add(clearFilter);
				   panel.remove(1);
				   panel.add(filterMenuBar);
				   panel.updateUI(); 
			   }  
		   }
		   /************************************************sharpen filter****************************************************/
		   private class SharpenButtonListener implements ActionListener
		   {
			   public void actionPerformed(ActionEvent e)
			   {   
				   Filter filter = new SharpenFilter();
				   applyFilterOnImage(filter);
				   
			   }  
		   }
		   /************************************************smoothing filter****************************************************/ 
		   private class SmoothingButtonListener implements ActionListener
		   {
			   public void actionPerformed(ActionEvent e)
			   {   
				   Filter filter = new SmoothingFilter();
				   applyFilterOnImage(filter);
			   }  
		   }
		   /************************************************black and white filters****************************************************/  
		   private class BlackNWhiteButtonListener implements ActionListener
		   {
			   public void actionPerformed(ActionEvent e)
			   {   
				   Filter filter = new BlackNWhiteFilter();
				   applyFilterOnImage(filter);
			   }  
		   }
		   /************************************************edge detection filter****************************************************/  
		   private class EdgeButtonListener implements ActionListener
		   {
			   public void actionPerformed(ActionEvent e)
			   {   
				   Filter filter = new EdgeDetectionFilter();   
				   applyFilterOnImage(filter);
			   }  
		   }
		   /************************************************clear filter****************************************************/
		   private class ClearButtonListener implements ActionListener
		   {
			   public void actionPerformed(ActionEvent e)
			   {   
				   chosenImage.setImage(storage.getClearImage());
				   storage.add(chosenImage.getImage());
				   display();
			   }  
		   }
		   
		   /************************************************filters****************************************************/
		   /************************************************undo****************************************************/
		   
		   private class UndoButtonListener implements ActionListener
		   {
			   public void actionPerformed(ActionEvent e)
			   {  try { 
					if(storage.checkList())
					{   BufferedImage imageTaken= new  BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
					    imageTaken=storage.undo();
						chosenImage.setImage( imageTaken);
						 storage.setClearImage(imageTaken);
						display();
					}
					else errorMenu("you haven't done a modifcation yet ");}
			   catch(NullPointerException n)
			   {
				   System.out.println("error ,please wait");
			   }
			   }  
		   }
		  
		   /**************************************error menu*****************************************/
		   
		   private void errorMenu(String error)
		   {
		       JOptionPane.showMessageDialog(null, error);
		   }
		   /***************************apply Filter on Image**********************/
		   private void applyFilterOnImage(Filter filter)
		   { try {
			   chosenImage.setImage(filter.apply(chosenImage.getImage()));
			   storage.add(chosenImage.getImage());
			   display();
			   }
		   catch(NullPointerException n)
		   {
			   System.out.println("error,please wait");
		   }
		   }
		   
		   /***************************apply Filter on Image**********************/
		   /****************************display function*********************************/
		   private void display()  throws NullPointerException
		   {   
		try {	  if( hbar.getValue()!=0) {hbar.setValue(0); hbar.updateUI();}
			  if( vbar.getValue()!=0) { vbar.setValue(0); vbar.updateUI();}
			   BufferedImage imageTaken= new  BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
			   imageTaken=chosenImage.getImage();
			   imageIcon.setImage(imageTaken);
			   imageLabel.setIcon(imageIcon);
			   imagePanel.remove(0);
			   imageLabel.setBounds(250, 0, chosenImage.getImage().getWidth(), chosenImage.getImage().getHeight());
			   imagePanel.setBounds(250, 0, chosenImage.getImage().getWidth(), chosenImage.getImage().getHeight());
			   imagePanel.add(imageLabel);
			   imagePanel.updateUI();
	}
		catch(NullPointerException n)
		{
			System.out.println("error please wait");
		}
			   
			   }
		   		   
}
