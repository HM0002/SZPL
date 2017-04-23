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

	/** Haszn�lhat� sz�nek sz�ma */
	private final int szinSzam = 3;

	/** Gui elemek t�rol�i */
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

	// V�ltoz� az eredeti p�lyak�pnek
	private String[][] palyaKep = null;

	// mez�k, sorok
	private int columns, rows;

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
		jatekPanel = new JPanel();
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
		


		try {
			// K�pek bet�lt�se
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

			// �llom�sk�pek t�mb felt�lt�se
			allomasKepek.add(null);
			for (int i = 1; i <= szinSzam; i++) {
				allomasKepek.add(ImageIO
						.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\allomas_" + i + ".png")));
			}
			// Kocsi k�pek t�mb felt�lt�se
			for (int i = 0; i <= szinSzam; i++) {
				kocsiKepek.add(ImageIO
						.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\kocsi_" + i + ".png")));
			}
			egyenesValtoSinKep=ImageIO.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\valto_egyenes.png"));
			kanyarValtoKep=ImageIO.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\valto_kanyar.png"));
			
		} catch (IOException e) {
			logger.setLevel(Level.INFO);
			logger.log(Level.INFO, "Nem siker�lt a k�peket beolvasni!");
			System.exit(0);
		}

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
				Cell cell = new Cell(ctrl);

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
					cell.setID(palyaKep[i][j]);
					// ha a oldalt sz�len van
					if (sinek.get(0).getPoz()[0] == 0 || sinek.get(0).getPoz()[0] == columns)
						cell.setImage(egyenesSinKep);

					else {
						cell.setImage(egyenesSinKep);
						cell.setAlapOrientation(90.0);
					}
				}

				// Cellak�p, ha keresztez�d�s
				else if (palyaKep[i][j].contains("K")) {
					cell.setID(palyaKep[i][j]);
					cell.setImage(keresztezoSinKep);
				}
				// cellak�p, ha egyenes s�n elem
				else if (palyaKep[i][j].contains("S")) {
					cell.setID(palyaKep[i][j]);

					// Elem lek�r�se
					PalyaElem sin = sinek.get(Integer.parseInt(palyaKep[i][j].trim().substring(1)));

					// szomsz�dok lek�r�se
					PalyaElem[] szomszedok = sin.szomszedok;

					// ha egyenes, akkor a megfelel� k�p
					if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0]
							|| szomszedok[0].getPoz()[1] == szomszedok[1].getPoz()[1]) {
						cell.setImage(egyenesSinKep);

						// Cellak�p orient�ci� be�ll�t�sa

						// megn�zi, f�gg�legesen egy vonalban vannak-e, mert
						// akkor forgatni kell a k�pen
						if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0])
							// ha igen, elforgatja a s�nt
							cell.setAlapOrientation(90.0);
					}
					// Ha nem egyenes a s�n, akkor tuti kanyar, hiszen S
					else {
						cell.setImage(kanyarKep);

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
							cell.setAlapOrientation(0.0);
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
							cell.setAlapOrientation(180.0);
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
							cell.setAlapOrientation(270.0);
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
							cell.setAlapOrientation(90.0);
						}
					}
				}
				//V�lt� p�lyak�p
					else if (palyaKep[i][j].contains("V")) {
						cell.setID(palyaKep[i][j]);

						// Elem lek�r�se
						PalyaElem sin = sinek.get(Integer.parseInt(palyaKep[i][j].trim().substring(1)));

						// szomsz�dok lek�r�se
						PalyaElem[] szomszedok = sin.szomszedok;

						// ha egyenes, akkor a megfelel� k�p
						if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0]
								|| szomszedok[0].getPoz()[1] == szomszedok[1].getPoz()[1]) {
							cell.setImage(egyenesValtoSinKep);

							// Cellak�p orient�ci� be�ll�t�sa

							// megn�zi, f�gg�legesen egy vonalban vannak-e, mert
							// akkor forgatni kell a k�pen
							if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0])
								// ha igen, elforgatja a s�nt
								cell.setAlapOrientation(90.0);
						}
						// Ha nem egyenes a s�n, akkor tuti kanyar, hiszen S
						else {
							cell.setImage(kanyarValtoKep);

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
								cell.setAlapOrientation(0.0);
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
								cell.setAlapOrientation(180.0);
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
								cell.setAlapOrientation(270.0);
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
								cell.setAlapOrientation(90.0);
							}
						}

					}

				 else if (palyaKep[i][j].contains("A")) {
					cell.setID(palyaKep[i][j]);
					// Elem lek�r�se
					PalyaElem sin = sinek.get(Integer.parseInt(palyaKep[i][j].trim().substring(1)));

					// szomsz�dok lek�r�se
					PalyaElem[] szomszedok = sin.szomszedok;

					// ha egyenes, akkor a megfelel� k�p
					if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0]
							|| szomszedok[0].getPoz()[1] == szomszedok[1].getPoz()[1]) {
						cell.setImage(allomasKepek.get(((Allomas) sin).getSzin()));

						// Cellak�p orient�ci� be�ll�t�sa

						// megn�zi, f�gg�legesen egy vonalban vannak-e, mert
						// akkor forgatni kell a k�pen
						if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0])
							// ha igen, elforgatja a s�nt
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

		// Ultimate �tlet, rajzoljon ide is oda is
		konzolDraw(JM);
		guiDraw(JM);
	}

	private void guiDraw(JatekMotor JM) {

		// Cell�k eredeti k�pre vissza�ll�t�sa
		for (Cell cell : cells)
			cell.restoreAlapImage();

		Cell celltmp = null;

		// V�lt� �ll�sok r�rajzol�sa
		// Sinek lek�r�se
		ArrayList<PalyaElem> sinek = JM.getAktualisPalya().getElemek();
		for (PalyaElem sin : sinek) {

			if (sin.getID().contains("V")) {
				Cell cell = cells.get(Integer.parseInt(sin.getID().trim().substring(1)));

				// szomsz�dok lek�r�se
				PalyaElem[] szomszedok = sin.szomszedok;

				// ha egyenes, akkor a megfelel� k�p
				if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0]
						|| szomszedok[0].getPoz()[1] == szomszedok[1].getPoz()[1]) {
					cell.setRaRajzoltKep(egyenesValtoSinKep);
					cell.setRaRajzoltOrientacio(0.0);

					// Cellak�p orient�ci� be�ll�t�sa

					// megn�zi, f�gg�legesen egy vonalban vannak-e, mert
					// akkor forgatni kell a k�pen
					if (szomszedok[0].getPoz()[0] == szomszedok[1].getPoz()[0])
						// ha igen, elforgatja a s�nt
						cell.setRaRajzoltOrientacio(90.0);
				}
				// Ha nem egyenes a s�n, akkor tuti kanyar, hiszen S
				else {
					cell.setRaRajzoltKep(kanyarValtoKep);

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
						cell.setRaRajzoltOrientacio(0.0);
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
						cell.setRaRajzoltOrientacio(180.0);
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
						cell.setRaRajzoltOrientacio(270.0);
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

		// Vonatok r�rajzol�sa
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

						// Orient�ci� be�ll�t�sa

						// S000 speci�lis elem
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
						// X egyezik, Y n� --> vonat lefel� megy
						else if (jarmu.getPozicio().getPoz()[0] == jarmu.getElozoPozicio().getPoz()[0]
								&& jarmu.getPozicio().getPoz()[1] > jarmu.getElozoPozicio().getPoz()[1])
							celltmp.setRaRajzoltOrientacio(270.0);

						// X cs�kken, Y nem v�ltozik --> vonat balra megy
						else if (jarmu.getPozicio().getPoz()[0] > jarmu.getElozoPozicio().getPoz()[0]
								&& jarmu.getPozicio().getPoz()[1] == jarmu.getElozoPozicio().getPoz()[1])
							celltmp.setRaRajzoltOrientacio(180.0);

						// X nem v�ltozik, Y cs�kken --> vonat felfel� megy
						else if (jarmu.getPozicio().getPoz()[0] == jarmu.getElozoPozicio().getPoz()[0]
								&& jarmu.getPozicio().getPoz()[1] < jarmu.getElozoPozicio().getPoz()[1])
							celltmp.setRaRajzoltOrientacio(90.0);
					}
				}

		// Crash rajzol�sa
		for (PalyaElem pe : JM.getAktualisPalya().getElemek())
			if (pe.getFoglalt() > 1)
				try {
					cells.get(Integer.parseInt(pe.getID().trim().substring(1))).setRaRajzoltKep(boomKep);
					cells.get(Integer.parseInt(pe.getID().trim().substring(1))).setRaRajzoltOrientacio(0.0);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		//Komplett panel �jrarajzol�sa a be�ll�t�sok alapj�n
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

	/**
	 * A st�tusz-b�rt kezel� f�ggv�ny, a Controller h�vja.
	 * */
	public void tajekoztatUser(int event) {
		if (event == 1)
			statusz.setText("Gy�zt�l!");
		else if (event == 2)
			statusz.setText("�tk�z�s t�rt�nt, vesztett�l!");
		enAblakom.repaint();
	}

	/**
	 * Ablak megsemmis�t�se.
	 * */
	public void dispose() {
		enAblakom.dispose();

	}

}
