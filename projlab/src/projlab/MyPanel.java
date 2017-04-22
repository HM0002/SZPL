package projlab;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	
	/**
	 * Auto generated UID 
	 */
	private static final long serialVersionUID = -7013948884089450492L;



	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);



	JatekMotor JM;

	MyPanel(JatekMotor jm) {
		super();
		JM = jm;

		 //Teszt mouse event
		  addMouseListener(new MouseAdapter() {
		  
		  @Override public void mouseEntered(MouseEvent e) {
		  //setBackground(Color.BLUE); 
		  } });
		  
		  
		  
		 
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		JatekKirajzol(g);

	}

	private void JatekKirajzol(Graphics g) {
		if (JM == null)
			return;
		logger.setLevel(Level.INFO);
		logger.log(Level.WARNING,"Debug info");



		
	}

}
