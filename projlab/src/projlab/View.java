package projlab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	JFrame enAblakom;
	JPanel gombPanel;
	JPanel statuszPanel;
	JButton ujJatekGomb;
	JButton exitGomb;
	JButton startStop;
	MyPanel jatekPanel;
	Controller ctrl;
	JLabel statusz;
	JLabel palya;
	ArrayList<Cell> cells;

	// Változó az eredeti pályaképnek
	String[][] palyaKep = null;

	// mezõk, sorok
	int columns, rows;

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
		jatekPanel = new MyPanel(JM);
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
				Cell cell = new Cell();

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
					// ha a bal szélen van
					if (sinek.get(0).getPoz()[0] == 0)
						try {
							cell.setImage(ImageIO
									.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\egyenes.png")));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					else {
						try {
							cell.setImage(ImageIO
									.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\egyenes.png")));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						cell.setOrientation(90.0);
					}
				}

				// Cellakép, ha keresztezõdés
				else if (palyaKep[i][j].contains("K")) {
					try {
						cell.setImage(ImageIO
								.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\keresztezodes.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// cellakép, ha egyenes sín elem
				else if (palyaKep[i][j].contains("S") || palyaKep[i][j].contains("V")) {

					// Elem lekérése
					PalyaElem sin = sinek.get(Integer.parseInt(palyaKep[i][j].trim().substring(1)));

					// szomszédok lekérése
					PalyaElem[] szomszedok = sin.szomszedok;

					// ha egyenes, akkor a megfelelõ kép
					if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0]
							|| szomszedok[0].getPoz()[1] == szomszedok[1].getPoz()[1]) {
						try {
							cell.setImage(ImageIO
									.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\egyenes.png")));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// Cellakép orientáció beállítása

						// megnézi, függõlegesen egy vonalban vannak-e, mert
						// akkor forgatni kell a képen
						if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0])
							// ha igen, elforgatja a sínt
							cell.setOrientation(90.0);
					}
					// Ha nem egyenes a sín, akkor tuti kanyar, hiszen S
					else {
						try {
							cell.setImage(ImageIO
									.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\kanyar.png")));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

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
							cell.setOrientation(0.0);
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
							cell.setOrientation(180.0);
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
							cell.setOrientation(270.0);
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
							cell.setOrientation(90.0);
						}
					}

				} else if (palyaKep[i][j].contains("A")) {
					// Elem lekérése
					PalyaElem sin = sinek.get(Integer.parseInt(palyaKep[i][j].trim().substring(1)));

					// szomszédok lekérése
					PalyaElem[] szomszedok = sin.szomszedok;

					// ha egyenes, akkor a megfelelõ kép
					if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0]
							|| szomszedok[0].getPoz()[1] == szomszedok[1].getPoz()[1]) {
						try {
							cell.setImage(ImageIO.read(new File(System.getProperty("user.home")
									+ "\\elemek_kepei\\allomas_" + ((Allomas) sin).getSzin() + ".png")));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// Cellakép orientáció beállítása

						// megnézi, függõlegesen egy vonalban vannak-e, mert
						// akkor forgatni kell a képen
						if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0])
							// ha igen, elforgatja a sínt
							cell.setOrientation(90.0);
					}
				} else if (palyaKep[i][j].contains("    ")) {
					try {
						cell.setImage(
								ImageIO.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\ures.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				cell.setEredetiImage();
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
			cell.restoreEredetiImage();

		Cell celltmp = null;

		// Vonatok rárajzolása
		for (Vonat vonat : JM.getAktualisPalya().getVonatok())
			for (Jarmu jarmu : vonat.getJarmuvek())
				if (jarmu.getPozicio() != null) {
					celltmp = cells.get(Integer.parseInt(jarmu.getPozicio().getID().trim().substring(1)));
					{
						// Ha mozdony, rajzoljunk azt
						if (jarmu.getID().contains("M")) {
							try {
								celltmp.setImage(ImageIO.read(
										new File(System.getProperty("user.home") + "\\elemek_kepei\\mozdony.png")));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						// szeneskocsi
						else if (jarmu.getID().contains("C")) {
							try {
								celltmp.setImage(ImageIO.read(
										new File(System.getProperty("user.home") + "\\elemek_kepei\\szeneskocsi.png")));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						// kocsi
						else if (jarmu.getID().contains("K")) {
							try {
								celltmp.setImage(ImageIO.read(new File(System.getProperty("user.home")
										+ "\\elemek_kepei\\kocsi_" + jarmu.getSzin() + ".png")));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						// Orientáció beállítása
						// X egyezik, Y nõ --> vonat lefelé megy
						if (jarmu.getPozicio().getPoz()[0] == jarmu.getElozoPozicio().getPoz()[0]
								&& jarmu.getPozicio().getPoz()[1] > jarmu.getElozoPozicio().getPoz()[1])
							celltmp.setOrientation(270.0);

						// X csökken, Y nem változik --> vonat balra megy
						else if (jarmu.getPozicio().getPoz()[0] > jarmu.getElozoPozicio().getPoz()[0]
								&& jarmu.getPozicio().getPoz()[1] == jarmu.getElozoPozicio().getPoz()[1])
							celltmp.setOrientation(180.0);

						// X nem változik, Y csökken --> vonat felfelé megy
						else if (jarmu.getPozicio().getPoz()[0] == jarmu.getElozoPozicio().getPoz()[0]
								&& jarmu.getPozicio().getPoz()[1] < jarmu.getElozoPozicio().getPoz()[1])
							celltmp.setOrientation(90.0);
					}
				}

		// Crash rajzolása
		for (PalyaElem pe : JM.getAktualisPalya().getElemek())
			if (pe.getFoglalt() > 1)
				try {
					cells.get(Integer.parseInt(pe.getID().trim().substring(1))).setImage(
							ImageIO.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\boom.png")));
					cells.get(Integer.parseInt(pe.getID().trim().substring(1))).setOrientation(0.0);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		// Váltó állások rárajzolása

		// Alagutak

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

	public void tajekoztatUser(int event) {
		if (event == 1)
			statusz.setText("Gyõztél!");
		else if (event == 2)
			statusz.setText("Ütközés történt, vesztettél!");
		enAblakom.repaint();
	}

	public void dispose() {
		enAblakom.dispose();

	}

}
