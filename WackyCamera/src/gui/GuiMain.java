package gui;

import javax.swing.SwingUtilities;

import com.alee.laf.WebLookAndFeel;

public class GuiMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		View gui = new View();

		 SwingUtilities.invokeLater ( new Runnable ()
	        {
	            public void run ()
	            {
	                // Install WebLaF as application L&F
	                WebLookAndFeel.install ();
	                gui.InitGui();

	                // Create you Swing application here
	                // JFrame frame = ...
	            }
	        } );


	}

}
