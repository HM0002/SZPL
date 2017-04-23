package projlab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class Cell extends JLabel {

	/**
	 * A forgat�s �rt�ke
	 */
	double orientacio = 0.0;
	double eredetiOrientacio = 0.0;

	/**
	 * A l�trehoz�skor be�ll�tott keret
	 */
	Border originalBorder;

	/**
	 * A l�trehoz�skor be�ll�tott eredeti k�p
	 */
	Image eredetiKep;

	/**
	 * A Label k�pe
	 */
	Image kep;

	/**
	 * Auto generated
	 */
	private static final long serialVersionUID = 7342308389908249672L;

	Cell() {
		super();

		setMaximumSize(new Dimension(50, 50));
		setPreferredSize(new Dimension(50, 50));

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				originalBorder = getBorder();
				setBorder(BorderFactory.createLineBorder(Color.red));
				repaint();
			}
		});

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(originalBorder);
				repaint();
			}
		});
	}

	public void paintComponent(Graphics g) {

		// �s kirajzol�s el�sz�r
		super.paintComponent(g);

		// Graphics 2D alak�t�s
		Graphics2D g2d = (Graphics2D) g;

		// Antialias be�l�t�s
		RenderingHints renderingHint = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		renderingHint.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		// Antialias hozz�ad�s
		g2d.setRenderingHints(renderingHint);

		// Forgat�s az ablak k�zep�n
		int w2 = getWidth() / 2;
		int h2 = getHeight() / 2;
		g2d.rotate(Math.toRadians(orientacio), w2, h2);

		g.drawImage(kep, 0, 0, null);
	}

	/**
	 * Setter az orient�ci� v�ltoz�hoz
	 */
	void setOrientation(Double tmp) {
		orientacio = tmp;
	}

	public void setImage(Image img) {
		kep = img;
	}

	public void setEredetiImage() {
		eredetiKep = kep;
		eredetiOrientacio = orientacio;
	}

	public void restoreEredetiImage() {
		kep = eredetiKep;
		orientacio = eredetiOrientacio;
	}
}
