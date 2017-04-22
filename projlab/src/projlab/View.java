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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
	JButton ujJatekGomb;
	JButton exitGomb;
	MyPanel jatekPanel;
	Controller ctrl;
	JLabel statusz;
	JLabel palya;

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
		exitGomb = new JButton("Kilépés");
		gombPanel.add(exitGomb);

		// panel az ablakoknak
		jatekPanel = new MyPanel(JM);
		enAblakom.add(jatekPanel, BorderLayout.CENTER);
		jatekPanel.setMaximumSize(new Dimension(500, 500));
		jatekPanel.setPreferredSize(new Dimension(500, 500));

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
		
		//Sinek lekérése
		ArrayList<PalyaElem> sinek=JM.getAktualisPalya().getElemek();

		//Pálya nevének kiírása
		palya.setText(JM.getAktualisPalya().getID());

		//Pálya elemek orientációjának beállítása
		jatekPanel.setLayout(new GridBagLayout());

		//GridBagConstraints elem az elrendezéshez
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Ciklus végigmenni az elemeken
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				
				//GridBagConstraints beállítása
				gbc.gridx = j;
				gbc.gridy = i;

				//Üres cella létrehozása
				Cell cell = new Cell();
				
				//üres határoló
				Border border = null;
				
				//határolók beállítása
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
				
				//Határoló átadása a cellának
				cell.setBorder(border);
				
				//Cellakép beállítása
				//Cellakép, ha keresztezõdés
				if(palyaKep[i][j].contains("K"))
					try {
						cell.setImage(ImageIO.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\keresztezodes.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				//cellakép, ha egyenes sín elem
				else if(palyaKep[i][j].contains("S")){
					
					//ha egyenes, akkor a megfelelõ kép
					try {
						cell.setImage(ImageIO.read(new File(System.getProperty("user.home") + "\\elemek_kepei\\egyenes.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					//Cellakép orientáció beállítása
					
					//szomszédok lekérése
					PalyaElem[] szomszedok=sinek.get(Integer.parseInt(palyaKep[i][j].trim().substring(1))).szomszedok;
					//megnézi, függõlegesen egy vonalban vannak-e
					if (szomszedok[0].getPoz()[1]==szomszedok[1].getPoz()[1])
						//ha igen, elforgatja a sínt
						cell.setOrientation(90.0);
					
					
					
					
				}
			
				
				
				
				
				
				jatekPanel.add(cell, gbc);
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

}
