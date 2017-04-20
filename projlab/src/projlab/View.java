package projlab;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	JFrame enAblakom;
	JPanel gombPanel;
	JButton ujJatekGomb;
	JButton exitGomb;
	SajatGrafika jatekPanel;
	Controller ctrl;

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

		// �jj�t�k gomb az ablakra
		ujJatekGomb = new JButton("�j j�t�k");
		gombPanel.add(ujJatekGomb);

		// Kil�p�s gomb az ablakra
		exitGomb = new JButton("Kil�p�s");
		gombPanel.add(exitGomb);

		// panel az ablakoknak
		jatekPanel = new SajatGrafika(JM);
		jatekPanel.setSize(600, 600);
		enAblakom.add(jatekPanel, BorderLayout.CENTER);

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
		enAblakom.setVisible(true);
	}

	public void draw(JatekMotor JM) {

		// Ultimate �tlet, rajzoljon ide is oda is
		konzolDraw(JM);
		guiDraw(JM);
	}

	private void guiDraw(JatekMotor JM) {
		jatekPanel.repaint();

	}

	/**
	 * Az aktu�lis p�lya �s j�t�k �ll�s kimenetre kirajzol�s��rt felel�s
	 * f�ggv�ny.
	 */
	private void konzolDraw(JatekMotor JM) {

		// V�ltoz� az eredeti p�lyak�pnek
		String[][] palyaKep = null;

		// mez�k, sorok
		int columns, rows;

		// palya kepenek elkerese a jatekmotort�l
		palyaKep = JM.getAktualisPalya().getPalyaKep();
		columns = palyaKep[0].length;
		rows = palyaKep.length;

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

}
