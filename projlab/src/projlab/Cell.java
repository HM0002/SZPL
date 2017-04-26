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
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class Cell extends JLabel {
	/**
	 * "Java-Strukt�ra" a k�p �s orient�ci� egys�gbe z�r�s�ra
	 */
	class KepOrientacio {
		public Image kep;
		public double or;
	}

	/**
	 * Az elem alap k�p�nek forgat�s �rt�ke
	 */
	double alapOrientacio = 0.0;

	/**
	 * Az elemhez tartoz� modellbeli p�lyaelem sorsz�ma
	 */
	String id = "Ures";

	/**
	 * Az elemre r�rajzolt k�pek forgat�s �rt�ke �s k�pe, Vector, mert az
	 * alapb�l syncronized
	 */
	Vector<KepOrientacio> raRajzoltak;

	/**
	 * A l�trehoz�skor be�ll�tott keret
	 */
	Border originalBorder;

	/**
	 * Az alap k�pe a cell�nak
	 */
	Image alapKep;

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
		ctrl = ctrlhere;

		// Vector konstruktor 5,5 param�terrel, 5 m�rettel nyisson, 5-�s�vel
		// inkrement�ljon, az elvileg mindig el�g.
		raRajzoltak = new Vector<KepOrientacio>(5, 5);

		// M�ret be�ll�t�sa
		setMaximumSize(new Dimension(50, 50));
		setMinimumSize(new Dimension(50, 50));
		setPreferredSize(new Dimension(50, 50));

		// Eg�r esem�ny kezel�, piros keretet csin�l a mez�nek, ha felette van
		// az eg�r
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				originalBorder = getBorder();
				setBorder(BorderFactory.createLineBorder(Color.red));
				repaint();
			}
		});

		// Eg�r esem�ny kezel�, visszarakja az eredeti keretet a mez�nek, ha
		// elment fel�le az eg�r
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(originalBorder);
				repaint();
			}
		});

		// Esem�nykez�, ha r�kattintottunk, megh�vja a controller kattintottElem
		// f�ggv�ny�t
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Cell cell = (Cell) e.getSource();
				ctrl.kattintottElem(cell.getID());
			}
		});

	}

	public void paintComponent(Graphics g) {

		// �s kirajzol�s el�sz�r
		super.paintComponent(g);

		// �j graphics objektum, hogy a borderek ne forogjanak, majd Graphics 2D
		// alak�t�s
		Graphics2D g2d = (Graphics2D) g.create();

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
		g2d.drawImage(alapKep, 0, 0, null);

		// r�rajzolt k�p elforgat�sa, kirajzol�sa
		synchronized (raRajzoltak) {

			// ha nincs r�rajzoland� elem, k�sz
			if (raRajzoltak.size() != 0) {

				// r�rajzoland�k r�rajzol�sa
				for (KepOrientacio kepor : raRajzoltak) {
					// transzform�ci� vissza�ll�t�sa az �st�l kapottra
					g2d.setTransform(trans);

					// r�rajzol�sok, elforgat�sok, kirajzol�sok
					g2d.rotate(Math.toRadians(kepor.or), w2, h2);
					g2d.drawImage(kepor.kep, 0, 0, null);

				}
			}
		}
		// Eldob�s, miut�n nem haszn�ljuk, fontos!
		g2d.dispose();
	}

	/**
	 * Az elem alap orient�ci�j�nak be�ll�t�sa
	 */
	void setAlapOrientation(Double tmp) {
		alapOrientacio = tmp;
	}

	/**
	 * Az elem alap k�p�nek be�ll�t�sa
	 */
	public void setImage(Image img) {
		alapKep = img;
	}

	/**
	 * Az eredeti k�p vissza�ll�t�sa (ne rajzoljon r� �j r�teget)
	 */
	public void restoreAlapImage() {
		raRajzoltak.clear();
	}

	/**
	 * A r�rajzolnad� k�p be�ll�t�sa
	 */
	public void setRaRajzolas(Double or, Image img) {
		KepOrientacio tmp = new KepOrientacio();
		tmp.kep = img;
		tmp.or = or;
		raRajzoltak.add(tmp);
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getID() {
		return id;
	}

}
