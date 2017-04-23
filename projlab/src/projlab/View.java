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

	// V�ltoz� az eredeti p�lyak�pnek
	String[][] palyaKep = null;

	// mez�k, sorok
	int columns, rows;

	/**
	 * View konstruktor
	 */
	View(Controller ctrl, JatekMotor JM) {

		this.ctrl = ctrl;

		// Az ablak
		enAblakom = new JFrame("Vonatos j�t�k by Team Delta");
		enAblakom.setSize(700, 700);

		// layout
		enAblakom.setLayout(new BorderLayout());

		// panel az ablakoknak
		gombPanel = new JPanel(new FlowLayout());
		enAblakom.add(gombPanel, BorderLayout.NORTH);

		palya = new JLabel();
		gombPanel.add(palya);
		gombPanel.add(new JLabel("        "));

		// �jj�t�k gomb az ablakra
		ujJatekGomb = new JButton("�j j�t�k");
		gombPanel.add(ujJatekGomb);

		// Kil�p�s gomb az ablakra

		startStop = new JButton();
		if (ctrl.getJatekFut() == true)
			startStop.setText("  Pause  ");
		else
			startStop.setText("  Start  ");
		gombPanel.add(startStop);

		// Kil�p�s gomb az ablakra
		exitGomb = new JButton("Kil�p�s");
		gombPanel.add(exitGomb);

		// panel az ablakoknak
		jatekPanel = new MyPanel(JM);
		enAblakom.add(jatekPanel, BorderLayout.CENTER);
		jatekPanel.setMaximumSize(new Dimension(500, 500));
		jatekPanel.setPreferredSize(new Dimension(500, 500));
		jatekPanel.setDoubleBuffered(true);

		// St�tusz ablak
		statusz = new JLabel("<Status Bar>");
		statuszPanel = new JPanel();
		statuszPanel.add(statusz);
		enAblakom.add(statuszPanel, BorderLayout.SOUTH);

		// Bez�r�s event handler ablak
		enAblakom.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		// Bez�r�s event handler kil�p�s gomb
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

		// �j j�t�k gomb event handler
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

		// palya kepenek elkerese a jatekmotort�l
		palyaKep = JM.getAktualisPalya().getPalyaKep();
		columns = palyaKep[0].length;
		rows = palyaKep.length;

		// Sinek lek�r�se
		ArrayList<PalyaElem> sinek = JM.getAktualisPalya().getElemek();

		cells = new ArrayList<Cell>();
		for (int i = 0; i < sinek.size(); i++)
			cells.add(null);

		// P�lya nev�nek ki�r�sa
		palya.setText(JM.getAktualisPalya().getID());

		// P�lya elemek orient�ci�j�nak be�ll�t�sa
		jatekPanel.setLayout(new GridBagLayout());

		// GridBagConstraints elem az elrendez�shez
		GridBagConstraints gbc = new GridBagConstraints();

		// Ciklus v�gigmenni az elemeken
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {

				// GridBagConstraints be�ll�t�sa
				gbc.gridx = j;
				gbc.gridy = i;

				// �res cella l�trehoz�sa
				Cell cell = new Cell();

				// �res hat�rol�
				Border border = null;

				// hat�rol�k be�ll�t�sa
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

				// Hat�rol� �tad�sa a cell�nak
				cell.setBorder(border);

				// Cellak�p be�ll�t�sa

				// S000 speci�lis s�n be�ll�t�sa
				if (palyaKep[i][j].contains("000")) {
					// ha a bal sz�len van
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

				// Cellak�p, ha keresztez�d�s
				else if (palyaKep[i][j].contains("K")) {
					try {
						cell.setImage(ImageIO
								.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\keresztezodes.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// cellak�p, ha egyenes s�n elem
				else if (palyaKep[i][j].contains("S") || palyaKep[i][j].contains("V")) {

					// Elem lek�r�se
					PalyaElem sin = sinek.get(Integer.parseInt(palyaKep[i][j].trim().substring(1)));

					// szomsz�dok lek�r�se
					PalyaElem[] szomszedok = sin.szomszedok;

					// ha egyenes, akkor a megfelel� k�p
					if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0]
							|| szomszedok[0].getPoz()[1] == szomszedok[1].getPoz()[1]) {
						try {
							cell.setImage(ImageIO
									.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\egyenes.png")));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// Cellak�p orient�ci� be�ll�t�sa

						// megn�zi, f�gg�legesen egy vonalban vannak-e, mert
						// akkor forgatni kell a k�pen
						if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0])
							// ha igen, elforgatja a s�nt
							cell.setOrientation(90.0);
					}
					// Ha nem egyenes a s�n, akkor tuti kanyar, hiszen S
					else {
						try {
							cell.setImage(ImageIO
									.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\kanyar.png")));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// �s akkor csak azt kell eld�nteni, merr�l merre n�zzen
						// El�sz�r szomsz�d alatta �s a m�sik t�le balra vagy
						// ford�tva
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
						// El�sz�r szomsz�d felette �s a m�sik t�le
						// jobbra vagy ford�tva
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
						// El�sz�r szomsz�d alatta �s a m�sik t�le
						// jobbra vagy ford�tva
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
						// El�sz�r szomsz�d felette �s a m�sik t�le
						// balra vagy ford�tva
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
					// Elem lek�r�se
					PalyaElem sin = sinek.get(Integer.parseInt(palyaKep[i][j].trim().substring(1)));

					// szomsz�dok lek�r�se
					PalyaElem[] szomszedok = sin.szomszedok;

					// ha egyenes, akkor a megfelel� k�p
					if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0]
							|| szomszedok[0].getPoz()[1] == szomszedok[1].getPoz()[1]) {
						try {
							cell.setImage(ImageIO.read(new File(System.getProperty("user.home")
									+ "\\elemek_kepei\\allomas_" + ((Allomas) sin).getSzin() + ".png")));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// Cellak�p orient�ci� be�ll�t�sa

						// megn�zi, f�gg�legesen egy vonalban vannak-e, mert
						// akkor forgatni kell a k�pen
						if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0])
							// ha igen, elforgatja a s�nt
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

		// Ultimate �tlet, rajzoljon ide is oda is
		konzolDraw(JM);
		guiDraw(JM);
	}

	private void guiDraw(JatekMotor JM) {

		// Cell�k eredeti k�pre vissza�ll�t�sa
		for (Cell cell : cells)
			cell.restoreEredetiImage();

		Cell celltmp = null;

		// Vonatok r�rajzol�sa
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

						// Orient�ci� be�ll�t�sa
						// X egyezik, Y n� --> vonat lefel� megy
						if (jarmu.getPozicio().getPoz()[0] == jarmu.getElozoPozicio().getPoz()[0]
								&& jarmu.getPozicio().getPoz()[1] > jarmu.getElozoPozicio().getPoz()[1])
							celltmp.setOrientation(270.0);

						// X cs�kken, Y nem v�ltozik --> vonat balra megy
						else if (jarmu.getPozicio().getPoz()[0] > jarmu.getElozoPozicio().getPoz()[0]
								&& jarmu.getPozicio().getPoz()[1] == jarmu.getElozoPozicio().getPoz()[1])
							celltmp.setOrientation(180.0);

						// X nem v�ltozik, Y cs�kken --> vonat felfel� megy
						else if (jarmu.getPozicio().getPoz()[0] == jarmu.getElozoPozicio().getPoz()[0]
								&& jarmu.getPozicio().getPoz()[1] < jarmu.getElozoPozicio().getPoz()[1])
							celltmp.setOrientation(90.0);
					}
				}

		// Crash rajzol�sa
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

		// V�lt� �ll�sok r�rajzol�sa

		// Alagutak

		jatekPanel.repaint();

	}

	/**
	 * Az aktu�lis p�lya �s j�t�k �ll�s kimenetre kirajzol�s��rt felel�s
	 * f�ggv�ny.
	 */
	private void konzolDraw(JatekMotor JM) {

		// V�ltoz� a kirajzoland� p�lyak�pnek az eredeti alapj�n
		String[][] aktualisPalyaKep = new String[rows][columns];

		// akt�v ki�r�si szint ment�se
		Level ltmp;
		ltmp = logger.getLevel();

		// P�lya nev�nek ki�r�sa
		logger.setLevel(Level.INFO);
		logger.log(Level.WARNING, JM.getAktualisPalya().getID());
		logger.log(Level.WARNING, "");
		logger.setLevel(Level.OFF);

		// palyakep alaphelyzetbe elemekkel felt�lt�se a helyes m�k�d�shez
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

		// draw p�lya
		for (int i = 0; i < rows; i++) {
			String temp = "";
			for (int j = 0; j < columns - 1; j++)
				temp += aktualisPalyaKep[i][j] + " ";
			temp += aktualisPalyaKep[i][columns - 1];
			logger.setLevel(Level.INFO);
			logger.log(Level.WARNING, temp + "\n");
		}

		// write v�lt� �ll�sok
		logger.log(Level.WARNING, "V�lt�k �ll�sa:");
		logger.setLevel(Level.OFF);
		for (PalyaElem palyaElem : JM.getAktualisPalya().getElemek())
			if (palyaElem instanceof Valto) {
				logger.setLevel(Level.INFO);
				logger.log(Level.WARNING, palyaElem.getID().trim() + ":" + ((Valto) palyaElem).getAllas()[0].getID()
						+ "<-->" + ((Valto) palyaElem).getAllas()[1].getID());
			}

		// write �llom�s sz�nek, v�rakoz� utasok
		logger.log(Level.WARNING, "\n�llom�sok sz�ne:");
		logger.setLevel(Level.OFF);
		for (PalyaElem palyaElem : JM.getAktualisPalya().getElemek())
			if (palyaElem instanceof Allomas) {
				logger.setLevel(Level.INFO);
				String tmp;
				if (((Allomas) palyaElem).getSzin() == 1)
					tmp = palyaElem.getID().trim() + ": k�k";
				else
					tmp = palyaElem.getID().trim() + ": piros";
				if (((Allomas) palyaElem).getVarakozoUtas())
					tmp = tmp + "\t V�rakoz� utas : van";
				else
					tmp = tmp + "\t V�rakoz� utas : nincs";

				logger.log(Level.WARNING, tmp);
			}

		// write kocsi sz�nek, utasok
		logger.log(Level.WARNING, "\nKocsik sz�ne:");
		logger.setLevel(Level.OFF);
		for (Vonat vonat : JM.getAktualisPalya().getVonatok())
			for (Jarmu jarmu : vonat.getJarmuvek())
				if (jarmu instanceof Kocsi) {
					String tmp;
					if (((Kocsi) jarmu).getEredetiSzin() == 1)
						tmp = jarmu.getID() + ": k�k";
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
			statusz.setText("Gy�zt�l!");
		else if (event == 2)
			statusz.setText("�tk�z�s t�rt�nt, vesztett�l!");
		enAblakom.repaint();
	}

	public void dispose() {
		enAblakom.dispose();

	}

}
