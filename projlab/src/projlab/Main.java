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

	// Jatekmotor létrehozása
	static JatekMotor JM;

	// Elsõ pályához PalyaElem-eknek ArrayList létrehozása
	static ArrayList<PalyaElem> palyaElemek1;

	// Jarmuveknek ArrayList
	static ArrayList<Jarmu> vonat1;
	static ArrayList<Jarmu> vonat2;

	// 1. Pálya létrehozása(p0)
	static Palya p0;
	static Palya p1;

	// Vonatoknak ArrayList
	static ArrayList<Vonat> vonatok1;

	// Palyaknak ArrayList
	static ArrayList<Palya> palyak;

	// Vonat objektumok létrehozása
	static Vonat v1;
	static Vonat v2;

	// Elsõ Pályához Sin-ek, Valto-k, Allomas-ok létrehozása
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

	// Jarmu-vek létrehozása, elõször a vonat1 elemei
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

		System.out.println("0: Inicializálások\n1: Új játék kezdése\n2: Új vonat indítása\n3: Vonat mozgása"
				+ "\n4: Alagút építés\n5: Alagút lebontás\n6: Alagút belépés / kilépés"
				+ "\n7: Váltó átállítása\n8: Kisiklás a váltón\n9: Állomás aktiválása"
				+ "\n10: Utasok leszállása (+ szeneskocsi helyes mûködése)\n11: Utasok felszállása"
				+ "\n12: Utas nem száll le\n13: Keresztezõdésen áthaladás vízszintesen"
				+ "\n14: Keresztezõdésen áthaladás függõlegesen\n15: Ütkozés ellenõrzés"
				+ "\n16: Pálya megnyerése, új pálya inicializálása\n17: Kilépés"
				+ "\n18: Foglalt váltó átállítása\n19: Foglalt pályaelemre alagút építés"
				+ "\n20: Harmadik alagút építése nem történik meg"
				+ "\n21: Alagút építés nem történik meg, ha létezik egy másik alagút, ami éppen foglalt"
				+ "\n\nAdja meg a kívánt teszt esetet: ");

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			int testCase = Integer.parseInt(reader.readLine());
			switch (testCase) {
            // Inicializálások
			case 0:
				logger.addHandler(fileHandler);
				init();
				break;
            // Új játék kezdése
			case 1:
				init();
				logger.addHandler(fileHandler);
				logger.setLevel(Level.INFO);
				draw();
				//JM.ujJatek();
				break;
            // Új vonat indítása
			case 2:
				logger.setLevel(Level.OFF);
				init();
				logger.addHandler(fileHandler);
				logger.setLevel(Level.INFO);
				JM.vonatInditas();
				break;
            // Vonat mozgása
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
            // Alagút építés
			case 4:
				logger.setLevel(Level.OFF);
				init();
				logger.addHandler(fileHandler);
				logger.setLevel(Level.INFO);
				draw();
				e10.setAlagut();
				draw();
				break;
            // Alagút lebontás
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
            // Alagút belépés / kilépés
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
            // Váltó átállítása
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
            // Kisiklás a váltón
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
            // Állomás aktiválása
			case 9:
				logger.setLevel(Level.OFF);
				init();
				m1.setKezdoPoziciok(e15, e14);
				logger.addHandler(fileHandler);
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				break;
            // Utasok leszállása (+ szeneskocsi helyes mûködése)
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
            // Utasok felszállása
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
            // Utas nem száll le
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
            // Keresztezõdésen áthaladás vízszintesen
			case 13:
				logger.setLevel(Level.OFF);
				init();
				m1.setKezdoPoziciok(e7, e6);
				logger.addHandler(fileHandler);
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				break;
            // Keresztezõdésen áthaladás függõlegesen
			case 14:
				logger.setLevel(Level.OFF);
				init();
				m1.setKezdoPoziciok(e11, e12);
				logger.addHandler(fileHandler);
				JM.idoEltelt();
				JM.idoEltelt();
				JM.idoEltelt();
				break;
            // Ütközés ellenõrzés
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
            // Pálya megnyerése, új pálya inicializálása
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
            // Kilépés
			case 17:
				logger.setLevel(Level.OFF);
				init();
				logger.addHandler(fileHandler);
				logger.setLevel(Level.INFO);
				JM.kilepes();
				break;
            // Foglalt váltó átállítása
			case 18:
				logger.setLevel(Level.OFF);
				init();
				m1.setKezdoPoziciok(e12, e23);
				logger.addHandler(fileHandler);
				logger.setLevel(Level.INFO);
				e12.atallit();
				draw();
				break;
            // Foglalt pályaelemre alagút építés
			case 19:
				logger.setLevel(Level.OFF);
				init();
				m1.setKezdoPoziciok(e6, e5);
				logger.addHandler(fileHandler);
				logger.setLevel(Level.INFO);
				e6.setAlagut();
				draw();
				break;
            // Harmadik alagút építése nem történik meg
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
            // Alagút építés nem történik meg, ha létezik egy másik alagút, ami éppen foglalt
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
		logger.log(Level.INFO, "Játek elemeinek inicializálása: \n");

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

		// Sin-ek összekötése
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

		// palyaElemek1 listához a Sin példányok hozzáadása
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

		// Jarmu (Mozdony, Kocsi és Szeneskocsi) konstruktorok
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

		// vonatokba a Jarmu-vek elhelyezése: vonat1
		vonat1.add(m1);
		vonat1.add(sz1);
		vonat1.add(k1);
		vonat1.add(k2);

		// vonatokba a Jarmu-vek elhelyezése: vonat2
		vonat2.add(m2);
		vonat2.add(sz2);
		vonat2.add(k3);
		vonat2.add(k4);

		// Vonat konstruktorok
		v1 = new Vonat(vonat1, "vonat1");
		v2 = new Vonat(vonat2, "vonat2");

		vonatok1 = new ArrayList<Vonat>();

		// vonatok hozzáadása a vonatlistához
		vonatok1.add(v1);
		vonatok1.add(v2);

		// Palya konstruktor
		p1 = new Palya(2, 3, 12, palyaElemek1, vonatok1, "palya2");
		p0 = new Palya(2, 3, 12, palyaElemek1, vonatok1, "palya1");

		palyak = new ArrayList<Palya>();

		// Elsõ hozzáadása a pályalistához
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

		// draw pálya
		for (int i = 0; i < 6; i++) {
			String temp = "";
			for (int j = 0; j < 7; j++)
				temp += palyaKep[i][j] + "   ";
			logger.setLevel(Level.INFO);
			logger.log(Level.INFO, temp + "\n");
		}

		// write válto állások
		logger.log(Level.INFO, "Váltók állása:");
		logger.setLevel(Level.OFF);
		for (PalyaElem palyaElem : JM.getAktualisPalya().getElemek())
			if (palyaElem instanceof Valto) {
				logger.setLevel(Level.INFO);
				logger.log(Level.INFO, palyaElem.getID() + ":" + ((Valto) palyaElem).getAllas()[0].getID() + "<-->"
						+ ((Valto) palyaElem).getAllas()[1].getID());
			}

		// write állomás színek, várakozó utasok
		logger.log(Level.INFO, "\nÁllomások színe:");
		logger.setLevel(Level.OFF);
		for (PalyaElem palyaElem : JM.getAktualisPalya().getElemek())
			if (palyaElem instanceof Allomas) {
				logger.setLevel(Level.INFO);
				String tmp;
				if (((Allomas) palyaElem).getSzin() == 1)
					tmp = palyaElem.getID() + ": kék";
				else
					tmp = palyaElem.getID() + ": piros";
				if (((Allomas) palyaElem).getVarakozoUtas())
					tmp = tmp + "\t Várakozó utas : van";
				else
					tmp = tmp + "\t Várakozó utas : nincs";

				logger.log(Level.INFO, tmp);
			}

		// write kocsi színek, utasok
		logger.log(Level.INFO, "\nKocsik színe:");
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
					logger.log(Level.INFO, tmp);
					logger.setLevel(Level.OFF);
				}
		logger.setLevel(Level.INFO);

		logger.log(Level.INFO, "");
	}
}
