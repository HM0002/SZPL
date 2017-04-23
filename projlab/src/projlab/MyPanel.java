package projlab;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

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

		// Teszt mouse event
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				// setBackground(Color.BLUE);
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

	}

}
