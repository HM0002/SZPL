package projlab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	// Jatekmotor l�trehoz�sa
	static JatekMotor JM;

	// Els� p�ly�hoz PalyaElem-eknek ArrayList l�trehoz�sa
	static ArrayList<PalyaElem> palyaElemek1;

	// Jarmuveknek ArrayList
	static ArrayList<Jarmu> vonat1;
	static ArrayList<Jarmu> vonat2;

	// 1. P�lya l�trehoz�sa(p0)
	static Palya p0;
	static Palya p1;

	// Vonatoknak ArrayList
	static ArrayList<Vonat> vonatok1;

	// Palyaknak ArrayList
	static ArrayList<Palya> palyak;

	// Vonat objektumok l�trehoz�sa
	static Vonat v1;
	static Vonat v2;

	// Els� P�ly�hoz Sin-ek, Valto-k, Allomas-ok l�trehoz�sa
	static Sin e0;
	static Sin e1;
	static Sin e2;
	static Sin e3;
	static Sin e4;
	static Sin e5;
	static Sin e6;
	static Sin e7;
	static Sin e8;
	static Keresztezodes e9;
	static Sin e10;
	static Sin e11;
	static Valto e12;
	static Sin e13;
	static Sin e14;
	static Sin e15;
	static Sin e16;
	static Allomas e17;
	static Sin e18;
	static Valto e19;
	static Allomas e20;
	static Sin e21;
	static Sin e22;
	static Sin e23;
	static Sin e24;
	static Sin e25;
	static Sin e26;
	static Sin e27;
	static Sin e28;

	// Jarmu-vek l�trehoz�sa, el�sz�r a vonat1 elemei
	static Mozdony m1;
	static Szeneskocsi sz1;
	static Kocsi k1;
	static Kocsi k2;

	// majd a vonat2 elemei
	static Mozdony m2;
	static Szeneskocsi sz2;
	static Kocsi k3;
	static Kocsi k4;

	public static void main(String[] args) throws SecurityException, IOException {
		for (Handler handler : logger.getParent().getHandlers()) {
			logger.getParent().removeHandler(handler);
		}
		CustomRecordFormatter formatter = new CustomRecordFormatter();
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(formatter);
		logger.addHandler(consoleHandler);

		FileHandler fileHandler = new FileHandler("%h\\kimenet.txt");
		fileHandler.setFormatter(formatter);

		System.out.println("0: Inicializ�l�sok\n1: �j j�t�k kezd�se\n2: �j vonat ind�t�sa\n3: Vonat mozg�sa"
				+ "\n4: Alag�t �p�t�s\n5: Alag�t lebont�s\n6: Alag�t bel�p�s / kil�p�s"
				+ "\n7: V�lt� �t�ll�t�sa\n8: Kisikl�s a v�lt�n\n9: �llom�s aktiv�l�sa"
				+ "\n10: Utasok lesz�ll�sa (+ szeneskocsi helyes m�k�d�se)\n11: Utasok felsz�ll�sa"
				+ "\n12: Utas nem sz�ll le\n13: Keresztez�d�sen �thalad�s v�zszintesen"
				+ "\n14: Keresztez�d�sen �thalad�s f�gg�legesen\n15: �tkoz�s ellen�rz�s"
				+ "\n16: P�lya megnyer�se, �j p�lya inicializ�l�sa\n17: Kil�p�s"
				+ "\n18: Foglalt v�lt� �t�ll�t�sa\n19: Foglalt p�lyaelemre alag�t �p�t�s"
				+ "\n20: Harmadik alag�t �p�t�se nem t�rt�nik meg"
				+ "\n21: Alag�t �p�t�s nem t�rt�nik meg, ha l�tezik egy m�sik alag�t, ami �ppen foglalt"
				+ "\n\nAdja meg a k�v�nt teszt esetet: ");

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			int testCase = Integer.parseInt(reader.readLine());
			switch (testCase) {
            // Inicializ�l�sok
			case 0:
				logger.addHandler(fileHandler);
				init();
				break;
            // �j j�t�k kezd�se
			case 1:
				init();
				logger.addHandler(fileHandler);
				logger.setLevel(Level.INFO);
				draw();
				//JM.ujJatek();
				break;
            // �j vonat ind�t�sa
			case 2:
				logger.setLevel(Level.OFF);
				init();
				logger.addHandler(fileHandler);
				logger.setLevel(Level.INFO);
				JM.vonatInditas();
				break;
            // Vonat mozg�sa
			case 3:
				logger.setLevel(Level.OFF);
				init();
				JM.vonatInditas();
				logger.addHandler(fileHandler);
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				break;
            // Alag�t �p�t�s
			case 4:
				logger.setLevel(Level.OFF);
				init();
				logger.addHandler(fileHandler);
				logger.setLevel(Level.INFO);
				draw();
				e10.setAlagut();
				draw();
				break;
            // Alag�t lebont�s
			case 5:
				logger.setLevel(Level.OFF);
				init();
				e10.setAlagut();
				logger.addHandler(fileHandler);
				logger.setLevel(Level.INFO);
				draw();
				e10.setAlagut();
				draw();
				break;
            // Alag�t bel�p�s / kil�p�s
			case 6:
				logger.setLevel(Level.OFF);
				init();
				JM.vonatInditas();
				e6.setAlagut();
				e10.setAlagut();
				logger.addHandler(fileHandler);
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				break;
            // V�lt� �t�ll�t�sa
			case 7:
				logger.setLevel(Level.OFF);
				init();
				logger.addHandler(fileHandler);
				logger.setLevel(Level.INFO);
				draw();
				e12.atallit();
				draw();
				e12.atallit();
				draw();
				e12.atallit();
				draw();
				break;
            // Kisikl�s a v�lt�n
			case 8:
				logger.setLevel(Level.OFF);
				init();
				m1.setKezdoPoziciok(e10, e9);
				logger.addHandler(fileHandler);
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				JM.utkozesEllenorzes();
				break;
            // �llom�s aktiv�l�sa
			case 9:
				logger.setLevel(Level.OFF);
				init();
				m1.setKezdoPoziciok(e15, e14);
				logger.addHandler(fileHandler);
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				break;
            // Utasok lesz�ll�sa (+ szeneskocsi helyes m�k�d�se)
			case 10:
				logger.setLevel(Level.OFF);
				init();
				m1.setKezdoPoziciok(e22, e23);
				sz1.setKezdoPoziciok(e23, e12);
				k1.setKezdoPoziciok(e12, e13);
				logger.addHandler(fileHandler);
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				break;
            // Utasok felsz�ll�sa
			case 11:
				logger.setLevel(Level.OFF);
				init();
				m1.setKezdoPoziciok(e15, e14);
				sz1.setKezdoPoziciok(e14, e13);
				k2.kiurit();
				k2.setKezdoPoziciok(e13, e12);
				logger.addHandler(fileHandler);
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				break;
            // Utas nem sz�ll le
			case 12:
				logger.setLevel(Level.OFF);
				init();
				m1.setKezdoPoziciok(e15, e14);
				sz1.setKezdoPoziciok(e14, e13);
				k1.setKezdoPoziciok(e13, e12);
				k2.setKezdoPoziciok(e12, e23);
				logger.addHandler(fileHandler);
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				break;
            // Keresztez�d�sen �thalad�s v�zszintesen
			case 13:
				logger.setLevel(Level.OFF);
				init();
				m1.setKezdoPoziciok(e7, e6);
				logger.addHandler(fileHandler);
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				break;
            // Keresztez�d�sen �thalad�s f�gg�legesen
			case 14:
				logger.setLevel(Level.OFF);
				init();
				m1.setKezdoPoziciok(e11, e12);
				logger.addHandler(fileHandler);
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				break;
            // �tk�z�s ellen�rz�s
			case 15:
				logger.setLevel(Level.OFF);
				init();
				k1.setKezdoPoziciok(e15, e14);
				k2.setKezdoPoziciok(e3, e2);
				logger.addHandler(fileHandler);
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				JM.utkozesEllenorzes();
				break;
            // P�lya megnyer�se, �j p�lya inicializ�l�sa
			case 16:
				logger.setLevel(Level.OFF);
				init();
				int i = 0;
				while (i < 14) {
					JM.vonatInditas();
					i++;
				}
				e17.setVarakozoUtas(false);
				k1.kiurit();
				k2.kiurit();
				k3.kiurit();
				k4.kiurit();
				logger.addHandler(fileHandler);
				logger.setLevel(Level.INFO);
				JM.gyozelemEllenorzes();
				break;
            // Kil�p�s
			case 17:
				logger.setLevel(Level.OFF);
				init();
				logger.addHandler(fileHandler);
				logger.setLevel(Level.INFO);
				JM.kilepes();
				break;
            // Foglalt v�lt� �t�ll�t�sa
			case 18:
				logger.setLevel(Level.OFF);
				init();
				m1.setKezdoPoziciok(e12, e23);
				logger.addHandler(fileHandler);
				logger.setLevel(Level.INFO);
				e12.atallit();
				draw();
				break;
            // Foglalt p�lyaelemre alag�t �p�t�s
			case 19:
				logger.setLevel(Level.OFF);
				init();
				m1.setKezdoPoziciok(e6, e5);
				logger.addHandler(fileHandler);
				logger.setLevel(Level.INFO);
				e6.setAlagut();
				draw();
				break;
            // Harmadik alag�t �p�t�se nem t�rt�nik meg
			case 20:
				logger.setLevel(Level.OFF);
				init();
				e6.setAlagut();
				e10.setAlagut();
				logger.addHandler(fileHandler);
				logger.setLevel(Level.INFO);
				draw();
				e14.setAlagut();
				draw();
				break;
            // Alag�t �p�t�s nem t�rt�nik meg, ha l�tezik egy m�sik alag�t, ami �ppen foglalt
			case 21:
				logger.setLevel(Level.OFF);
				init();
				e6.setAlagut();
				sz1.setKezdoPoziciok(e3, e2);
				m1.setKezdoPoziciok(e4, e3);
				logger.addHandler(fileHandler);
				JM.idoEltelt();
				JM.idoEltelt();
				e10.setAlagut();
				JM.idoEltelt();
				break;
			default:
				logger.log(Level.INFO, "Nincs ilyen teszteset!");
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void init() {
		logger.log(Level.INFO, "J�tek elemeinek inicializ�l�sa: \n");

		// Sin konstuktorok
		e0 = new Sin(" S00 ");
		e1 = new Sin(" S01 ");
		e2 = new Sin(" S02 ");
		e3 = new Sin(" S03 ");
		e4 = new Sin(" S04 ");
		e5 = new Sin(" S05 ");
		e6 = new Sin(" S06 ");
		e7 = new Sin(" S07 ");
		e8 = new Sin(" S08 ");
		e9 = new Keresztezodes(" K09 ");
		e10 = new Sin(" S10 ");
		e11 = new Sin(" S11 ");
		e12 = new Valto(" V12 ");
		e13 = new Sin(" S13 ");
		e14 = new Sin(" S14 ");
		e15 = new Sin(" S15 ");
		e16 = new Sin(" S16 ");
		e17 = new Allomas(1, true, " A17 ");
		e18 = new Sin(" S18 ");
		e19 = new Valto(" V19 ");
		e20 = new Allomas(2, false, " A20 ");
		e21 = new Sin(" S21 ");
		e22 = new Sin(" S22 ");
		e23 = new Sin(" S23 ");
		e24 = new Sin(" S24 ");
		e25 = new Sin(" S25 ");
		e26 = new Sin(" S26 ");
		e27 = new Sin(" S27 ");
		e28 = new Sin(" S28 ");

		palyaElemek1 = new ArrayList<PalyaElem>();

		// Sin-ek �sszek�t�se
		e0.setSzomszedok(e1, e1, null, null);
		e1.setSzomszedok(e0, e2, null, null);
		e2.setSzomszedok(e1, e3, null, null);
		e3.setSzomszedok(e2, e4, null, null);
		e4.setSzomszedok(e3, e5, null, null);
		e5.setSzomszedok(e4, e6, null, null);
		e6.setSzomszedok(e7, e17, null, null);
		e7.setSzomszedok(e6, e8, null, null);
		e8.setSzomszedok(e7, e9, null, null);
		e9.setSzomszedok(e8, e18, e10, e28);
		e10.setSzomszedok(e9, e11, null, null);
		e11.setSzomszedok(e10, e12, null, null);
		e12.setSzomszedok(e23, e13, e11, null);
		e13.setSzomszedok(e12, e14, null, null);
		e14.setSzomszedok(e13, e15, null, null);
		e15.setSzomszedok(e14, e16, null, null);
		e16.setSzomszedok(e15, e17, null, null);
		e17.setSzomszedok(e16, e6, null, null);
		e18.setSzomszedok(e9, e19, null, null);
		e19.setSzomszedok(e18, e20, e24, null);
		e20.setSzomszedok(e19, e21, null, null);
		e21.setSzomszedok(e20, e22, null, null);
		e22.setSzomszedok(e21, e23, null, null);
		e23.setSzomszedok(e12, e22, null, null);
		e24.setSzomszedok(e19, e25, null, null);
		e25.setSzomszedok(e24, e26, null, null);
		e26.setSzomszedok(e25, e17, null, null);
		e27.setSzomszedok(e26, e18, null, null);
		e28.setSzomszedok(e27, e9, null, null);

		// palyaElemek1 list�hoz a Sin p�ld�nyok hozz�ad�sa
		palyaElemek1.add(e0);
		palyaElemek1.add(e1);
		palyaElemek1.add(e2);
		palyaElemek1.add(e3);
		palyaElemek1.add(e4);
		palyaElemek1.add(e5);
		palyaElemek1.add(e6);
		palyaElemek1.add(e7);
		palyaElemek1.add(e8);
		palyaElemek1.add(e9);
		palyaElemek1.add(e10);
		palyaElemek1.add(e11);
		palyaElemek1.add(e12);
		palyaElemek1.add(e13);
		palyaElemek1.add(e14);
		palyaElemek1.add(e15);
		palyaElemek1.add(e16);
		palyaElemek1.add(e17);
		palyaElemek1.add(e18);
		palyaElemek1.add(e19);
		palyaElemek1.add(e20);
		palyaElemek1.add(e21);
		palyaElemek1.add(e22);
		palyaElemek1.add(e23);
		palyaElemek1.add(e24);
		palyaElemek1.add(e25);
		palyaElemek1.add(e26);
		palyaElemek1.add(e27);
		palyaElemek1.add(e28);

		// Jarmu (Mozdony, Kocsi �s Szeneskocsi) konstruktorok
		m1 = new Mozdony("M01");
		sz1 = new Szeneskocsi("C01");
		k1 = new Kocsi(2, "K01");
		k2 = new Kocsi(1, "K02");

		m2 = new Mozdony("M02");
		sz2 = new Szeneskocsi("C02");
		k3 = new Kocsi(1, "K03");
		k4 = new Kocsi(2, "K04");

		vonat1 = new ArrayList<Jarmu>();
		vonat2 = new ArrayList<Jarmu>();

		// vonatokba a Jarmu-vek elhelyez�se: vonat1
		vonat1.add(m1);
		vonat1.add(sz1);
		vonat1.add(k1);
		vonat1.add(k2);

		// vonatokba a Jarmu-vek elhelyez�se: vonat2
		vonat2.add(m2);
		vonat2.add(sz2);
		vonat2.add(k3);
		vonat2.add(k4);

		// Vonat konstruktorok
		v1 = new Vonat(vonat1, "vonat1");
		v2 = new Vonat(vonat2, "vonat2");

		vonatok1 = new ArrayList<Vonat>();

		// vonatok hozz�ad�sa a vonatlist�hoz
		vonatok1.add(v1);
		vonatok1.add(v2);

		// Palya konstruktor
		p1 = new Palya(2, 3, 12, palyaElemek1, vonatok1, "palya2");
		p0 = new Palya(2, 3, 12, palyaElemek1, vonatok1, "palya1");

		palyak = new ArrayList<Palya>();

		// Els� hozz�ad�sa a p�lyalist�hoz
		palyak.add(p0);
		palyak.add(p1);

		// JatekMotor konstruktor
		JM = new JatekMotor(palyak);

	}

	public static void draw() {

		/* @formatter:off */
		String palyaKep[][] = new String[][] { { "     ", " S15 ", " S14 ", " S13 ", " V12 ", " S23 ", " S22 " },
				{ "     ", " S16 ", "     ", "     ", " S11 ", "     ", " S21 " },
				{ "     ", " A17 ", "     ", "     ", " S10 ", "     ", " A20 " },
				{ " S05 ", " S06 ", " S07 ", " S08 ", " K09 ", " S18 ", " V19 " },
				{ "     ", "     ", "     ", "     ", " S28 ", "     ", " S24 " },
				{ "     ", "     ", "     ", "     ", " S27 ", " S26 ", " S25 " } };

		String palyaKepX[][] = new String[][] { { "     ", " S15 ", " S14 ", " S13 ", " V12 ", " S23 ", " S22 " },
				{ "     ", " S16 ", "     ", "     ", " S11 ", "     ", " S21 " },
				{ "     ", " A17 ", "     ", "     ", " S10 ", "     ", " A20 " },
				{ " S05 ", " S06 ", " S07 ", " S08 ", " K09 ", " S18 ", " V19 " },
				{ "     ", "     ", "     ", "     ", " S28 ", "     ", " S24 " },
				{ "     ", "     ", "     ", "     ", " S27 ", " S26 ", " S25 " } };
		/* @formatter:on */

		// set vonatok
		logger.setLevel(Level.OFF);
		for (Vonat vonat : JM.getAktualisPalya().getVonatok())
			for (Jarmu jarmu : vonat.getJarmuvek())
				for (int i = 0; i < 6; i++)
					for (int j = 0; j < 7; j++) {
						if (jarmu.getPozicio() != null) {
							if (palyaKepX[i][j].equals(jarmu.getPozicio().getID())) {
								if (palyaKepX[i][j].equals(palyaKep[i][j]))
									palyaKep[i][j] = "(" + jarmu.getID() + ")";
								else
									palyaKep[i][j] = "CRASH";
							}
						}
					}

		// set alagutak
		logger.setLevel(Level.OFF);
		for (PalyaElem palyaElem : JM.getAktualisPalya().getElemek())
			if (palyaElem.getAlagut()) {
				for (int i = 0; i < 6; i++)
					for (int j = 0; j < 7; j++) {
						if (palyaKepX[i][j].equals(palyaElem.getID()))
							palyaKep[i][j] = " |A| ";
					}
			}

		// draw p�lya
		for (int i = 0; i < 6; i++) {
			String temp = "";
			for (int j = 0; j < 7; j++)
				temp += palyaKep[i][j] + "   ";
			logger.setLevel(Level.INFO);
			logger.log(Level.INFO, temp + "\n");
		}

		// write v�lto �ll�sok
		logger.log(Level.INFO, "V�lt�k �ll�sa:");
		logger.setLevel(Level.OFF);
		for (PalyaElem palyaElem : JM.getAktualisPalya().getElemek())
			if (palyaElem instanceof Valto) {
				logger.setLevel(Level.INFO);
				logger.log(Level.INFO, palyaElem.getID() + ":" + ((Valto) palyaElem).getAllas()[0].getID() + "<-->"
						+ ((Valto) palyaElem).getAllas()[1].getID());
			}

		// write �llom�s sz�nek, v�rakoz� utasok
		logger.log(Level.INFO, "\n�llom�sok sz�ne:");
		logger.setLevel(Level.OFF);
		for (PalyaElem palyaElem : JM.getAktualisPalya().getElemek())
			if (palyaElem instanceof Allomas) {
				logger.setLevel(Level.INFO);
				String tmp;
				if (((Allomas) palyaElem).getSzin() == 1)
					tmp = palyaElem.getID() + ": k�k";
				else
					tmp = palyaElem.getID() + ": piros";
				if (((Allomas) palyaElem).getVarakozoUtas())
					tmp = tmp + "\t V�rakoz� utas : van";
				else
					tmp = tmp + "\t V�rakoz� utas : nincs";

				logger.log(Level.INFO, tmp);
			}

		// write kocsi sz�nek, utasok
		logger.log(Level.INFO, "\nKocsik sz�ne:");
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
					logger.log(Level.INFO, tmp);
					logger.setLevel(Level.OFF);
				}
		logger.setLevel(Level.INFO);

		logger.log(Level.INFO, "");
	}
}
