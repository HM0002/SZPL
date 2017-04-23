package projlab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class Cell extends JLabel {

	/**
	 * Az elem alap k�p�nek forgat�s �rt�ke
	 */
	double alapOrientacio = 0.0;
	
	/**
	 * Az elemhez tartoz� modellbeli p�lyaelem sorsz�ma
	 */
	String id="Ures";

	/**
	 * Az elemre r�rajzolt k�p (tipikusan a j�rm�vek) forgat�s �rt�ke
	 */
	double raRajzoltOrientacio = 0.0;

	/**
	 * A l�trehoz�skor be�ll�tott keret
	 */
	Border originalBorder;

	/**
	 * Az alap k�pe a cell�nak
	 */
	Image alapKep;

	/**
	 * A Label k�pe
	 */
	Image raRajzoltKep = null;
	
	/**
	 * Controller az esem�ny kezel�shez
	 */
	static Controller ctrl;

	/**
	 * Auto generated
	 */
	private static final long serialVersionUID = 7342308389908249672L;

	Cell(Controller ctrlhere) {
		super();
		ctrl=ctrlhere;

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
		
		addMouseListener(new MouseAdapter() {
			   public void mousePressed(MouseEvent e) {
			       Cell cell = (Cell)e.getSource();
			       ctrl.kattintottElem(cell.getID());
			   }
		});
		
		
	}

	public void paintComponent(Graphics g) {
	
		// �s kirajzol�s el�sz�r
		super.paintComponent(g);

		// Graphics 2D alak�t�s
		Graphics2D g2d = (Graphics2D) g;
		
		AffineTransform trans = g2d.getTransform();

		// Antialias be�l�t�s
		RenderingHints renderingHint = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		renderingHint.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		// Antialias hozz�ad�s
		g2d.setRenderingHints(renderingHint);

		// Forgat�s az ablak k�zep�n
		int w2 = getWidth() / 2;
		int h2 = getHeight() / 2;

		// alap k�p elforgat�sa, kirajzol�sa
		g2d.rotate(Math.toRadians(alapOrientacio), w2, h2);
		g.drawImage(alapKep, 0, 0, null);
		
		// r�rajzolt k�p elforgat�sa, kirajzol�sa
		if (raRajzoltKep != null) {
			//transzform�ci� vissza�ll�t�sa
			g2d.setTransform(trans);

			g2d.rotate(Math.toRadians(raRajzoltOrientacio), w2, h2);
			g.drawImage(raRajzoltKep, 0, 0, null);
		}
	}

	/**
	 * Setter az orient�ci� v�ltoz�hoz
	 */
	void setAlapOrientation(Double tmp) {
		alapOrientacio = tmp;
	}

	public void setImage(Image img) {
		alapKep = img;
	}

	public void restoreAlapImage() {
		raRajzoltOrientacio = 0.0;
		raRajzoltKep = null;
	}

	public void setRaRajzoltOrientacio(Double or) {
		raRajzoltOrientacio = or;
	}

	public void setRaRajzoltKep(Image img) {
		raRajzoltKep = img;
	}
	
	public void setID(String id) {
		this.id=id;
	}
	
	public String getID() {
		return id;
	}


}
