package projlab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class View {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/** Használható színek száma */
	private final int szinSzam = 3;

	/** Gui elemek tárolói */
	private JFrame enAblakom;
	private JPanel gombPanel;
	private JPanel statuszPanel;
	private JButton ujJatekGomb;
	private JButton exitGomb;
	private JButton startStop;
	private JPanel jatekPanel;
	private Controller ctrl;
	private JLabel statusz;
	private JLabel palya;
	private ArrayList<Cell> cells;
	private Image uresKep;
	private ArrayList<Image> kocsiKepek;
	private ArrayList<Image> allomasKepek;
	private Image mozdonyKep;
	private Image szenesKocsiKep;
	private Image egyenesSinKep;
	private Image kanyarKep;
	private Image egyenesValtoSinKep;
	private Image kanyarValtoKep;
	private Image keresztezoSinKep;
	private Image boomKep;
	private Image alagutKep;

	// Változó az eredeti pályaképnek
	private String[][] palyaKep = null;

	// mezõk, sorok
	private int columns, rows;

	/**
	 * View konstruktor
	 */
	View(Controller ctrl, JatekMotor JM) {

		this.ctrl = ctrl;

		// Az ablak
		enAblakom = new JFrame("Vonatos játék by Team Delta");
		enAblakom.setSize(700, 700);

		// layout
		enAblakom.setLayout(new BorderLayout());

		// panel az ablakoknak
		gombPanel = new JPanel(new FlowLayout());
		enAblakom.add(gombPanel, BorderLayout.NORTH);

		palya = new JLabel();
		gombPanel.add(palya);
		gombPanel.add(new JLabel("        "));

		// Újjáték gomb az ablakra
		ujJatekGomb = new JButton("Új játék");
		gombPanel.add(ujJatekGomb);

		// Kilépés gomb az ablakra

		startStop = new JButton();
		if (ctrl.getJatekFut() == true)
			startStop.setText("  Pause  ");
		else
			startStop.setText("  Start  ");
		gombPanel.add(startStop);

		// Kilépés gomb az ablakra
		exitGomb = new JButton("Kilépés");
		gombPanel.add(exitGomb);

		// panel az ablakoknak
		jatekPanel = new JPanel();
		enAblakom.add(jatekPanel, BorderLayout.CENTER);
		jatekPanel.setMaximumSize(new Dimension(500, 500));
		jatekPanel.setPreferredSize(new Dimension(500, 500));
		jatekPanel.setDoubleBuffered(true);

		// Státusz ablak
		statusz = new JLabel("<Status Bar>");
		statuszPanel = new JPanel();
		statuszPanel.add(statusz);
		enAblakom.add(statuszPanel, BorderLayout.SOUTH);

		// Bezárás event handler ablak
		enAblakom.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		// Bezárás event handler kilépés gomb
		exitGomb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});

		// Start/Stop gomb event handler
		startStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ctrl.setJatekFutas();
				if (ctrl.getJatekFut() == true)
					startStop.setText("  Pause  ");
				else
					startStop.setText("  Start  ");
			}

		});

		// Új játék gomb event handler
		ujJatekGomb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					ctrl.ujJatekKezdes();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				enAblakom.dispose();
			}
		});
		


		try {
			// Képek betöltése
			uresKep = ImageIO.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\ures.png"));
			kocsiKepek = new ArrayList<Image>();
			allomasKepek = new ArrayList<Image>();
			mozdonyKep = ImageIO.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\mozdony.png"));
			szenesKocsiKep = ImageIO
					.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\szeneskocsi.png"));
			egyenesSinKep = ImageIO.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\egyenes.png"));
			kanyarKep = ImageIO.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\kanyar.png"));
			keresztezoSinKep = ImageIO
					.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\keresztezodes.png"));
			boomKep = ImageIO.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\boom.png"));
			alagutKep = ImageIO.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\alagut.png"));

			// Állomásképek tömb feltöltése
			allomasKepek.add(null);
			for (int i = 1; i <= szinSzam; i++) {
				allomasKepek.add(ImageIO
						.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\allomas_" + i + ".png")));
			}
			// Kocsi képek tömb feltöltése
			for (int i = 0; i <= szinSzam; i++) {
				kocsiKepek.add(ImageIO
						.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\kocsi_" + i + ".png")));
			}
			egyenesValtoSinKep=ImageIO.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\valto_egyenes.png"));
			kanyarValtoKep=ImageIO.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\valto_kanyar.png"));
			
		} catch (IOException e) {
			logger.setLevel(Level.INFO);
			logger.log(Level.INFO, "Nem sikerült a képeket beolvasni!");
			System.exit(0);
		}

		// palya kepenek elkerese a jatekmotortól
		palyaKep = JM.getAktualisPalya().getPalyaKep();
		columns = palyaKep[0].length;
		rows = palyaKep.length;

		// Sinek lekérése
		ArrayList<PalyaElem> sinek = JM.getAktualisPalya().getElemek();

		cells = new ArrayList<Cell>();
		for (int i = 0; i < sinek.size(); i++)
			cells.add(null);

		// Pálya nevének kiírása
		palya.setText(JM.getAktualisPalya().getID());

		// Pálya elemek orientációjának beállítása
		jatekPanel.setLayout(new GridBagLayout());

		// GridBagConstraints elem az elrendezéshez
		GridBagConstraints gbc = new GridBagConstraints();

		// Ciklus végigmenni az elemeken
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {

				// GridBagConstraints beállítása
				gbc.gridx = j;
				gbc.gridy = i;

				// Üres cella létrehozása
				Cell cell = new Cell(ctrl);

				// üres határoló
				Border border = null;

				// határolók beállítása
				if (i < rows - 1) {
					if (j < columns - 1) {
						border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
					} else {
						border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
					}
				} else {
					if (j < columns - 1) {
						border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
					} else {
						border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
					}
				}

				// Határoló átadása a cellának
				cell.setBorder(border);

				// Cellakép beállítása

				// S000 speciális sín beállítása
				if (palyaKep[i][j].contains("000")) {
					cell.setID(palyaKep[i][j]);
					// ha a oldalt szélen van
					if (sinek.get(0).getPoz()[0] == 0 || sinek.get(0).getPoz()[0] == columns)
						cell.setImage(egyenesSinKep);

					else {
						cell.setImage(egyenesSinKep);
						cell.setAlapOrientation(90.0);
					}
				}

				// Cellakép, ha keresztezõdés
				else if (palyaKep[i][j].contains("K")) {
					cell.setID(palyaKep[i][j]);
					cell.setImage(keresztezoSinKep);
				}
				// cellakép, ha egyenes sín elem
				else if (palyaKep[i][j].contains("S")) {
					cell.setID(palyaKep[i][j]);

					// Elem lekérése
					PalyaElem sin = sinek.get(Integer.parseInt(palyaKep[i][j].trim().substring(1)));

					// szomszédok lekérése
					PalyaElem[] szomszedok = sin.szomszedok;

					// ha egyenes, akkor a megfelelõ kép
					if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0]
							|| szomszedok[0].getPoz()[1] == szomszedok[1].getPoz()[1]) {
						cell.setImage(egyenesSinKep);

						// Cellakép orientáció beállítása

						// megnézi, függõlegesen egy vonalban vannak-e, mert
						// akkor forgatni kell a képen
						if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0])
							// ha igen, elforgatja a sínt
							cell.setAlapOrientation(90.0);
					}
					// Ha nem egyenes a sín, akkor tuti kanyar, hiszen S
					else {
						cell.setImage(kanyarKep);

						// És akkor csak azt kell eldönteni, merrõl merre nézzen
						// Elõször szomszéd alatta és a másik tõle balra vagy
						// fordítva
						if ((szomszedok[0].getPoz()[0] == sin.getPoz()[0]
								&& szomszedok[0].getPoz()[1] == sin.getPoz()[1] + 1
								&& szomszedok[1].getPoz()[0] == sin.getPoz()[0] - 1
								&& szomszedok[1].getPoz()[1] == sin.getPoz()[1])
								|| (szomszedok[1].getPoz()[0] == sin.getPoz()[0]
										&& szomszedok[1].getPoz()[1] == sin.getPoz()[1] + 1
										&& szomszedok[0].getPoz()[0] == sin.getPoz()[0] - 1
										&& szomszedok[0].getPoz()[1] == sin.getPoz()[1])) {
							cell.setAlapOrientation(0.0);
						}
						// Elõször szomszéd felette és a másik tõle
						// jobbra vagy fordítva
						else if ((szomszedok[0].getPoz()[0] == sin.getPoz()[0] + 1
								&& szomszedok[0].getPoz()[1] == sin.getPoz()[1]
								&& szomszedok[1].getPoz()[0] == sin.getPoz()[0]
								&& szomszedok[1].getPoz()[1] == sin.getPoz()[1] - 1)
								|| (szomszedok[1].getPoz()[0] == sin.getPoz()[0] + 1
										&& szomszedok[1].getPoz()[1] == sin.getPoz()[1]
										&& szomszedok[0].getPoz()[0] == sin.getPoz()[0]
										&& szomszedok[0].getPoz()[1] == sin.getPoz()[1] - 1)) {
							cell.setAlapOrientation(180.0);
						}
						// Elõször szomszéd alatta és a másik tõle
						// jobbra vagy fordítva
						else if ((szomszedok[0].getPoz()[0] == sin.getPoz()[0]
								&& szomszedok[0].getPoz()[1] == sin.getPoz()[1] + 1
								&& szomszedok[1].getPoz()[0] == sin.getPoz()[0] + 1
								&& szomszedok[1].getPoz()[1] == sin.getPoz()[1])
								|| (szomszedok[1].getPoz()[0] == sin.getPoz()[0]
										&& szomszedok[1].getPoz()[1] == sin.getPoz()[1] + 1
										&& szomszedok[0].getPoz()[0] == sin.getPoz()[0] + 1
										&& szomszedok[0].getPoz()[1] == sin.getPoz()[1])) {
							cell.setAlapOrientation(270.0);
						}
						// Elõször szomszéd felette és a másik tõle
						// balra vagy fordítva
						else if ((szomszedok[0].getPoz()[0] == sin.getPoz()[0]
								&& szomszedok[0].getPoz()[1] == sin.getPoz()[1] - 1
								&& szomszedok[1].getPoz()[0] == sin.getPoz()[0] - 1
								&& szomszedok[1].getPoz()[1] == sin.getPoz()[1])
								|| (szomszedok[1].getPoz()[0] == sin.getPoz()[0]
										&& szomszedok[1].getPoz()[1] == sin.getPoz()[1] - 1
										&& szomszedok[0].getPoz()[0] == sin.getPoz()[0] - 1
										&& szomszedok[0].getPoz()[1] == sin.getPoz()[1])) {
							cell.setAlapOrientation(90.0);
						}
					}
				}
				//Váltó pályakép
					else if (palyaKep[i][j].contains("V")) {
						cell.setID(palyaKep[i][j]);

						// Elem lekérése
						PalyaElem sin = sinek.get(Integer.parseInt(palyaKep[i][j].trim().substring(1)));

						// szomszédok lekérése
						PalyaElem[] szomszedok = sin.szomszedok;

						// ha egyenes, akkor a megfelelõ kép
						if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0]
								|| szomszedok[0].getPoz()[1] == szomszedok[1].getPoz()[1]) {
							cell.setImage(egyenesValtoSinKep);

							// Cellakép orientáció beállítása

							// megnézi, függõlegesen egy vonalban vannak-e, mert
							// akkor forgatni kell a képen
							if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0])
								// ha igen, elforgatja a sínt
								cell.setAlapOrientation(90.0);
						}
						// Ha nem egyenes a sín, akkor tuti kanyar, hiszen S
						else {
							cell.setImage(kanyarValtoKep);

							// És akkor csak azt kell eldönteni, merrõl merre nézzen
							// Elõször szomszéd alatta és a másik tõle balra vagy
							// fordítva
							if ((szomszedok[0].getPoz()[0] == sin.getPoz()[0]
									&& szomszedok[0].getPoz()[1] == sin.getPoz()[1] + 1
									&& szomszedok[1].getPoz()[0] == sin.getPoz()[0] - 1
									&& szomszedok[1].getPoz()[1] == sin.getPoz()[1])
									|| (szomszedok[1].getPoz()[0] == sin.getPoz()[0]
											&& szomszedok[1].getPoz()[1] == sin.getPoz()[1] + 1
											&& szomszedok[0].getPoz()[0] == sin.getPoz()[0] - 1
											&& szomszedok[0].getPoz()[1] == sin.getPoz()[1])) {
								cell.setAlapOrientation(0.0);
							}
							// Elõször szomszéd felette és a másik tõle
							// jobbra vagy fordítva
							else if ((szomszedok[0].getPoz()[0] == sin.getPoz()[0] + 1
									&& szomszedok[0].getPoz()[1] == sin.getPoz()[1]
									&& szomszedok[1].getPoz()[0] == sin.getPoz()[0]
									&& szomszedok[1].getPoz()[1] == sin.getPoz()[1] - 1)
									|| (szomszedok[1].getPoz()[0] == sin.getPoz()[0] + 1
											&& szomszedok[1].getPoz()[1] == sin.getPoz()[1]
											&& szomszedok[0].getPoz()[0] == sin.getPoz()[0]
											&& szomszedok[0].getPoz()[1] == sin.getPoz()[1] - 1)) {
								cell.setAlapOrientation(180.0);
							}
							// Elõször szomszéd alatta és a másik tõle
							// jobbra vagy fordítva
							else if ((szomszedok[0].getPoz()[0] == sin.getPoz()[0]
									&& szomszedok[0].getPoz()[1] == sin.getPoz()[1] + 1
									&& szomszedok[1].getPoz()[0] == sin.getPoz()[0] + 1
									&& szomszedok[1].getPoz()[1] == sin.getPoz()[1])
									|| (szomszedok[1].getPoz()[0] == sin.getPoz()[0]
											&& szomszedok[1].getPoz()[1] == sin.getPoz()[1] + 1
											&& szomszedok[0].getPoz()[0] == sin.getPoz()[0] + 1
											&& szomszedok[0].getPoz()[1] == sin.getPoz()[1])) {
								cell.setAlapOrientation(270.0);
							}
							// Elõször szomszéd felette és a másik tõle
							// balra vagy fordítva
							else if ((szomszedok[0].getPoz()[0] == sin.getPoz()[0]
									&& szomszedok[0].getPoz()[1] == sin.getPoz()[1] - 1
									&& szomszedok[1].getPoz()[0] == sin.getPoz()[0] - 1
									&& szomszedok[1].getPoz()[1] == sin.getPoz()[1])
									|| (szomszedok[1].getPoz()[0] == sin.getPoz()[0]
											&& szomszedok[1].getPoz()[1] == sin.getPoz()[1] - 1
											&& szomszedok[0].getPoz()[0] == sin.getPoz()[0] - 1
											&& szomszedok[0].getPoz()[1] == sin.getPoz()[1])) {
								cell.setAlapOrientation(90.0);
							}
						}

					}

				 else if (palyaKep[i][j].contains("A")) {
					cell.setID(palyaKep[i][j]);
					// Elem lekérése
					PalyaElem sin = sinek.get(Integer.parseInt(palyaKep[i][j].trim().substring(1)));

					// szomszédok lekérése
					PalyaElem[] szomszedok = sin.szomszedok;

					// ha egyenes, akkor a megfelelõ kép
					if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0]
							|| szomszedok[0].getPoz()[1] == szomszedok[1].getPoz()[1]) {
						cell.setImage(allomasKepek.get(((Allomas) sin).getSzin()));

						// Cellakép orientáció beállítása

						// megnézi, függõlegesen egy vonalban vannak-e, mert
						// akkor forgatni kell a képen
						if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0])
							// ha igen, elforgatja a sínt
							cell.setAlapOrientation(90.0);
					}
				} else if (palyaKep[i][j].contains("    ")) {
					cell.setImage(uresKep);
				}

				jatekPanel.add(cell, gbc);

				if (!palyaKep[i][j].trim().isEmpty())
					cells.set(Integer.parseInt(palyaKep[i][j].trim().substring(1)), cell);
			}
		}

		enAblakom.pack();
		enAblakom.setLocationRelativeTo(null);
		enAblakom.setVisible(true);

	}

	public void draw(JatekMotor JM) {

		// Ultimate ötlet, rajzoljon ide is oda is
		konzolDraw(JM);
		guiDraw(JM);
	}

	private void guiDraw(JatekMotor JM) {

		// Cellák eredeti képre visszaállítása
		for (Cell cell : cells)
			cell.restoreAlapImage();

		Cell celltmp = null;

		// Váltó állások rárajzolása
		// Sinek lekérése
		ArrayList<PalyaElem> sinek = JM.getAktualisPalya().getElemek();
		for (PalyaElem sin : sinek) {

			if (sin.getID().contains("V")) {
				Cell cell = cells.get(Integer.parseInt(sin.getID().trim().substring(1)));

				// szomszédok lekérése
				PalyaElem[] szomszedok = sin.szomszedok;

				// ha egyenes, akkor a megfelelõ kép
				if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0]
						|| szomszedok[0].getPoz()[1] == szomszedok[1].getPoz()[1]) {
					cell.setRaRajzoltKep(egyenesValtoSinKep);
					cell.setRaRajzoltOrientacio(0.0);

					// Cellakép orientáció beállítása

					// megnézi, függõlegesen egy vonalban vannak-e, mert
					// akkor forgatni kell a képen
					if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0])
						// ha igen, elforgatja a sínt
						cell.setRaRajzoltOrientacio(90.0);
				}
				// Ha nem egyenes a sín, akkor tuti kanyar, hiszen S
				else {
					cell.setRaRajzoltKep(kanyarValtoKep);

					// És akkor csak azt kell eldönteni, merrõl merre nézzen
					// Elõször szomszéd alatta és a másik tõle balra vagy
					// fordítva
					if ((szomszedok[0].getPoz()[0] == sin.getPoz()[0]
							&& szomszedok[0].getPoz()[1] == sin.getPoz()[1] + 1
							&& szomszedok[1].getPoz()[0] == sin.getPoz()[0] - 1
							&& szomszedok[1].getPoz()[1] == sin.getPoz()[1])
							|| (szomszedok[1].getPoz()[0] == sin.getPoz()[0]
									&& szomszedok[1].getPoz()[1] == sin.getPoz()[1] + 1
									&& szomszedok[0].getPoz()[0] == sin.getPoz()[0] - 1
									&& szomszedok[0].getPoz()[1] == sin.getPoz()[1])) {
						cell.setRaRajzoltOrientacio(0.0);
					}
					// Elõször szomszéd felette és a másik tõle
					// jobbra vagy fordítva
					else if ((szomszedok[0].getPoz()[0] == sin.getPoz()[0] + 1
							&& szomszedok[0].getPoz()[1] == sin.getPoz()[1]
							&& szomszedok[1].getPoz()[0] == sin.getPoz()[0]
							&& szomszedok[1].getPoz()[1] == sin.getPoz()[1] - 1)
							|| (szomszedok[1].getPoz()[0] == sin.getPoz()[0] + 1
									&& szomszedok[1].getPoz()[1] == sin.getPoz()[1]
									&& szomszedok[0].getPoz()[0] == sin.getPoz()[0]
									&& szomszedok[0].getPoz()[1] == sin.getPoz()[1] - 1)) {
						cell.setRaRajzoltOrientacio(180.0);
					}
					// Elõször szomszéd alatta és a másik tõle
					// jobbra vagy fordítva
					else if ((szomszedok[0].getPoz()[0] == sin.getPoz()[0]
							&& szomszedok[0].getPoz()[1] == sin.getPoz()[1] + 1
							&& szomszedok[1].getPoz()[0] == sin.getPoz()[0] + 1
							&& szomszedok[1].getPoz()[1] == sin.getPoz()[1])
							|| (szomszedok[1].getPoz()[0] == sin.getPoz()[0]
									&& szomszedok[1].getPoz()[1] == sin.getPoz()[1] + 1
									&& szomszedok[0].getPoz()[0] == sin.getPoz()[0] + 1
									&& szomszedok[0].getPoz()[1] == sin.getPoz()[1])) {
						cell.setRaRajzoltOrientacio(270.0);
					}
					// Elõször szomszéd felette és a másik tõle
					// balra vagy fordítva
					else if ((szomszedok[0].getPoz()[0] == sin.getPoz()[0]
							&& szomszedok[0].getPoz()[1] == sin.getPoz()[1] - 1
							&& szomszedok[1].getPoz()[0] == sin.getPoz()[0] - 1
							&& szomszedok[1].getPoz()[1] == sin.getPoz()[1])
							|| (szomszedok[1].getPoz()[0] == sin.getPoz()[0]
									&& szomszedok[1].getPoz()[1] == sin.getPoz()[1] - 1
									&& szomszedok[0].getPoz()[0] == sin.getPoz()[0] - 1
									&& szomszedok[0].getPoz()[1] == sin.getPoz()[1])) {
						cell.setRaRajzoltOrientacio(90.0);
					}
				}
			}
		}

		// Alagutak
		if (JM.getAktualisPalya().getAlagutSzam() > 0)
			for (PalyaElem pe : JM.getAktualisPalya().getElemek())
				if (pe.getAlagut()) {
					cells.get(Integer.parseInt(pe.getID().trim().substring(1))).setRaRajzoltKep(alagutKep);
					cells.get(Integer.parseInt(pe.getID().trim().substring(1))).setRaRajzoltOrientacio(0.0);
				}

		// Vonatok rárajzolása
		for (Vonat vonat : JM.getAktualisPalya().getVonatok())
			for (Jarmu jarmu : vonat.getJarmuvek())
				if (jarmu.getPozicio() != null) {
					celltmp = cells.get(Integer.parseInt(jarmu.getPozicio().getID().trim().substring(1)));
					{
						// Ha mozdony, rajzoljunk azt
						if (jarmu.getID().contains("M")) {

							celltmp.setRaRajzoltKep(mozdonyKep);

						}
						// szeneskocsi
						else if (jarmu.getID().contains("C")) {
							celltmp.setRaRajzoltKep(szenesKocsiKep);
						}
						// kocsi
						else if (jarmu.getID().contains("K")) {
							celltmp.setRaRajzoltKep(kocsiKepek.get(jarmu.getSzin()));
						}

						// Orientáció beállítása

						// S000 speciális elem
						if (jarmu.getPozicio().getID().contains("S000")) {
							if (jarmu.getPozicio().getPoz()[1] == 0)
								celltmp.setRaRajzoltOrientacio(270.0);
							else if (jarmu.getPozicio().getPoz()[1] == rows)
								celltmp.setRaRajzoltOrientacio(90.0);
							else if (jarmu.getPozicio().getPoz()[0] == 0)
								celltmp.setRaRajzoltOrientacio(180.0);
							else if (jarmu.getPozicio().getPoz()[0] == columns)
								celltmp.setRaRajzoltOrientacio(0.0);
						}

						if (jarmu.getPozicio().getID().contains("S001")) {
							if (jarmu.getPozicio().getPoz()[1] == 1)
								celltmp.setRaRajzoltOrientacio(270.0);
							else if (jarmu.getPozicio().getPoz()[1] == rows - 1)
								celltmp.setRaRajzoltOrientacio(90.0);
							else if (jarmu.getPozicio().getPoz()[0] == 1)
								celltmp.setRaRajzoltOrientacio(180.0);
							else if (jarmu.getPozicio().getPoz()[0] == columns - 1)
								celltmp.setRaRajzoltOrientacio(0.0);
						}
						// X egyezik, Y nõ --> vonat lefelé megy
						else if (jarmu.getPozicio().getPoz()[0] == jarmu.getElozoPozicio().getPoz()[0]
								&& jarmu.getPozicio().getPoz()[1] > jarmu.getElozoPozicio().getPoz()[1])
							celltmp.setRaRajzoltOrientacio(270.0);

						// X csökken, Y nem változik --> vonat balra megy
						else if (jarmu.getPozicio().getPoz()[0] > jarmu.getElozoPozicio().getPoz()[0]
								&& jarmu.getPozicio().getPoz()[1] == jarmu.getElozoPozicio().getPoz()[1])
							celltmp.setRaRajzoltOrientacio(180.0);

						// X nem változik, Y csökken --> vonat felfelé megy
						else if (jarmu.getPozicio().getPoz()[0] == jarmu.getElozoPozicio().getPoz()[0]
								&& jarmu.getPozicio().getPoz()[1] < jarmu.getElozoPozicio().getPoz()[1])
							celltmp.setRaRajzoltOrientacio(90.0);
					}
				}

		// Crash rajzolása
		for (PalyaElem pe : JM.getAktualisPalya().getElemek())
			if (pe.getFoglalt() > 1)
				try {
					cells.get(Integer.parseInt(pe.getID().trim().substring(1))).setRaRajzoltKep(boomKep);
					cells.get(Integer.parseInt(pe.getID().trim().substring(1))).setRaRajzoltOrientacio(0.0);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		//Komplett panel újrarajzolása a beállítások alapján
		jatekPanel.repaint();

	}

	/**
	 * Az aktuális pálya és játék állás kimenetre kirajzolásáért felelõs
	 * függvény.
	 */
	private void konzolDraw(JatekMotor JM) {

		// Változó a kirajzolandó pályaképnek az eredeti alapján
		String[][] aktualisPalyaKep = new String[rows][columns];

		// aktív kiírási szint mentése
		Level ltmp;
		ltmp = logger.getLevel();

		// Pálya nevének kiírása
		logger.setLevel(Level.INFO);
		logger.log(Level.WARNING, JM.getAktualisPalya().getID());
		logger.log(Level.WARNING, "");
		logger.setLevel(Level.OFF);

		// palyakep alaphelyzetbe elemekkel feltöltése a helyes mûködéshez
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				aktualisPalyaKep[i][j] = palyaKep[i][j];
			}
		}

		// set vonatok
		logger.setLevel(Level.OFF);
		for (Vonat vonat : JM.getAktualisPalya().getVonatok())
			for (Jarmu jarmu : vonat.getJarmuvek())
				for (int i = 0; i < rows; i++)
					for (int j = 0; j < columns; j++) {
						if (jarmu.getPozicio() != null) {
							if (palyaKep[i][j].equals(jarmu.getPozicio().getID())) {
								if (palyaKep[i][j].equals(aktualisPalyaKep[i][j]))
									aktualisPalyaKep[i][j] = "(" + jarmu.getID() + ")";
								else
									aktualisPalyaKep[i][j] = "CRASH!";
							}
						}
					}

		// set alagutak
		for (PalyaElem palyaElem : JM.getAktualisPalya().getElemek())
			if (palyaElem.getAlagut()) {
				for (int i = 0; i < rows; i++)
					for (int j = 0; j < columns; j++) {
						if (palyaKep[i][j].equals(palyaElem.getID()))
							aktualisPalyaKep[i][j] = " |AL| ";
					}
			}

		// draw pálya
		for (int i = 0; i < rows; i++) {
			String temp = "";
			for (int j = 0; j < columns - 1; j++)
				temp += aktualisPalyaKep[i][j] + " ";
			temp += aktualisPalyaKep[i][columns - 1];
			logger.setLevel(Level.INFO);
			logger.log(Level.WARNING, temp + "\n");
		}

		// write váltó állások
		logger.log(Level.WARNING, "Váltók állása:");
		logger.setLevel(Level.OFF);
		for (PalyaElem palyaElem : JM.getAktualisPalya().getElemek())
			if (palyaElem instanceof Valto) {
				logger.setLevel(Level.INFO);
				logger.log(Level.WARNING, palyaElem.getID().trim() + ":" + ((Valto) palyaElem).getAllas()[0].getID()
						+ "<-->" + ((Valto) palyaElem).getAllas()[1].getID());
			}

		// write állomás színek, várakozó utasok
		logger.log(Level.WARNING, "\nÁllomások színe:");
		logger.setLevel(Level.OFF);
		for (PalyaElem palyaElem : JM.getAktualisPalya().getElemek())
			if (palyaElem instanceof Allomas) {
				logger.setLevel(Level.INFO);
				String tmp;
				if (((Allomas) palyaElem).getSzin() == 1)
					tmp = palyaElem.getID().trim() + ": kék";
				else
					tmp = palyaElem.getID().trim() + ": piros";
				if (((Allomas) palyaElem).getVarakozoUtas())
					tmp = tmp + "\t Várakozó utas : van";
				else
					tmp = tmp + "\t Várakozó utas : nincs";

				logger.log(Level.WARNING, tmp);
			}

		// write kocsi színek, utasok
		logger.log(Level.WARNING, "\nKocsik színe:");
		logger.setLevel(Level.OFF);
		for (Vonat vonat : JM.getAktualisPalya().getVonatok())
			for (Jarmu jarmu : vonat.getJarmuvek())
				if (jarmu instanceof Kocsi) {
					String tmp;
					if (((Kocsi) jarmu).getEredetiSzin() == 1)
						tmp = jarmu.getID() + ": kék";
					else
						tmp = jarmu.getID() + ": piros";
					if (((Kocsi) jarmu).getSzin() == 0)
						tmp = tmp + "\t Utas : nincs";
					else
						tmp = tmp + "\t Utas : van";
					logger.setLevel(Level.INFO);
					logger.log(Level.WARNING, tmp);
					logger.setLevel(Level.OFF);
				}
		logger.setLevel(Level.INFO);

		logger.log(Level.WARNING, "");
		logger.setLevel(ltmp);
	}

	/**
	 * A státusz-bárt kezelõ függvény, a Controller hívja.
	 * */
	public void tajekoztatUser(int event) {
		if (event == 1)
			statusz.setText("Gyõztél!");
		else if (event == 2)
			statusz.setText("Ütközés történt, vesztettél!");
		enAblakom.repaint();
	}

	/**
	 * Ablak megsemmisítése.
	 * */
	public void dispose() {
		enAblakom.dispose();

	}

}
