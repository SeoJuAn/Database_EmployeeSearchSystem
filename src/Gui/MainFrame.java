package Gui;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	public MainFrame(String title, int w, int h ) {
		super(title); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(w,h);
		setVisible(true);
	}

}
