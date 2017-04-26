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
	 * "Java-Struktúra" a kép és orientáció egységbe zárására
	 */
	class KepOrientacio {
		public Image kep;
		public double or;
	}

	/**
	 * Az elem alap képének forgatás értéke
	 */
	double alapOrientacio = 0.0;

	/**
	 * Az elemhez tartozó modellbeli pályaelem sorszáma
	 */
	String id = "Ures";

	/**
	 * Az elemre rárajzolt képek forgatás értéke és képe, Vector, mert az
	 * alapból syncronized
	 */
	Vector<KepOrientacio> raRajzoltak;

	/**
	 * A létrehozáskor beállított keret
	 */
	Border originalBorder;

	/**
	 * Az alap képe a cellának
	 */
	Image alapKep;

	/**
	 * Controller az esemény kezeléshez
	 */
	static Controller ctrl;

	/**
	 * Auto generated
	 */
	private static final long serialVersionUID = 7342308389908249672L;

	Cell(Controller ctrlhere) {
		super();
		ctrl = ctrlhere;

		// Vector konstruktor 5,5 paraméterrel, 5 mérettel nyisson, 5-ösével
		// inkrementáljon, az elvileg mindig elég.
		raRajzoltak = new Vector<KepOrientacio>(5, 5);

		// Méret beállítása
		setMaximumSize(new Dimension(50, 50));
		setMinimumSize(new Dimension(50, 50));
		setPreferredSize(new Dimension(50, 50));

		// Egér esemény kezelõ, piros keretet csinál a mezõnek, ha felette van
		// az egér
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				originalBorder = getBorder();
				setBorder(BorderFactory.createLineBorder(Color.red));
				repaint();
			}
		});

		// Egér esemény kezelõ, visszarakja az eredeti keretet a mezõnek, ha
		// elment felõle az egér
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(originalBorder);
				repaint();
			}
		});

		// Eseménykezõ, ha rákattintottunk, meghívja a controller kattintottElem
		// függvényét
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Cell cell = (Cell) e.getSource();
				ctrl.kattintottElem(cell.getID());
			}
		});

	}

	public void paintComponent(Graphics g) {

		// õs kirajzolás elõször
		super.paintComponent(g);

		// Új graphics objektum, hogy a borderek ne forogjanak, majd Graphics 2D
		// alakítás
		Graphics2D g2d = (Graphics2D) g.create();

		AffineTransform trans = g2d.getTransform();

		// Antialias beálítás
		RenderingHints renderingHint = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		renderingHint.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		// Antialias hozzáadás
		g2d.setRenderingHints(renderingHint);

		// Forgatás az ablak közepén
		int w2 = getWidth() / 2;
		int h2 = getHeight() / 2;

		// alap kép elforgatása, kirajzolása
		g2d.rotate(Math.toRadians(alapOrientacio), w2, h2);
		g2d.drawImage(alapKep, 0, 0, null);

		// rárajzolt kép elforgatása, kirajzolása
		synchronized (raRajzoltak) {

			// ha nincs rárajzolandó elem, kész
			if (raRajzoltak.size() != 0) {

				// rárajzolandók rárajzolása
				for (KepOrientacio kepor : raRajzoltak) {
					// transzformáció visszaállítása az õstõl kapottra
					g2d.setTransform(trans);

					// rárajzolások, elforgatások, kirajzolások
					g2d.rotate(Math.toRadians(kepor.or), w2, h2);
					g2d.drawImage(kepor.kep, 0, 0, null);

				}
			}
		}
		// Eldobás, miután nem használjuk, fontos!
		g2d.dispose();
	}

	/**
	 * Az elem alap orientációjának beállítása
	 */
	void setAlapOrientation(Double tmp) {
		alapOrientacio = tmp;
	}

	/**
	 * Az elem alap képének beállítása
	 */
	public void setImage(Image img) {
		alapKep = img;
	}

	/**
	 * Az eredeti kép visszaállítása (ne rajzoljon rá új réteget)
	 */
	public void restoreAlapImage() {
		raRajzoltak.clear();
	}

	/**
	 * A rárajzolnadó kép beállítása
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
