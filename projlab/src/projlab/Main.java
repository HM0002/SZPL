package projlab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
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

	public static void main(String[] args) {
		for (Handler handler : logger.getParent().getHandlers()) {
			logger.getParent().removeHandler(handler);
		}
		CustomRecordFormatter formatter = new CustomRecordFormatter();
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(formatter);
		logger.addHandler(consoleHandler);

		int testCase = 20;

		while (testCase != 15) {
			System.out.println("0: Inicializálások\n1: Új játék kezdése\n2: Új vonat indítása\n3: Vonat mozgása"
					+ "\n4: Alagút építés\n5: Alagút lebontás\n6: Alagút belépés / kilépés"
					+ "\n7: Váltó átállítása\n8: Állomás aktiválása\n9: Utasok le / fel szállása\n10: Ütkozés ellenõrzés"
					+ "\n11: Pálya megnyerése, új pálya inicializálása\n12: Kilépés\n15: Kilépés a tesztelésbõl"
					+ "\n\nAdja meg a kívánt teszt esetet: ");

			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				testCase = Integer.parseInt(reader.readLine());
				switch (testCase) {
				case 0:
					init();
					break;
				case 1:
					logger.setLevel(Level.OFF);
					init();
					logger.setLevel(Level.INFO);
					JM.ujJatek();
					break;
				case 2:
					logger.setLevel(Level.OFF);
					init();
					logger.setLevel(Level.INFO);
					JM.vonatInditas();
					break;
				case 3:
					logger.setLevel(Level.OFF);
					init();
					JM.vonatInditas();
					logger.setLevel(Level.INFO);
					JM.idoMeres();
					break;
				case 4:
					logger.setLevel(Level.OFF);
					init();
					logger.setLevel(Level.INFO);
					e10.setAlagut();
					break;
				case 5:
					logger.setLevel(Level.OFF);
					init();
					e10.setAlagut();
					logger.setLevel(Level.INFO);
					e10.setAlagut();
					break;
				case 6:
					logger.setLevel(Level.OFF);
					init();
					JM.vonatInditas();
					e6.setAlagut();
					e10.setAlagut();
					JM.idoMeres();
					logger.setLevel(Level.INFO);
					JM.idoMeres();
					break;
				case 7:
					logger.setLevel(Level.OFF);
					init();
					logger.setLevel(Level.INFO);
					e12.atallit();
					break;
				case 8:
					logger.setLevel(Level.OFF);
					init();
					m1.setKezdoPoziciok(e17, e16);
					logger.setLevel(Level.INFO);
					JM.idoMeres();
					break;
				case 9:
					logger.setLevel(Level.OFF);
					init();
					m1.setKezdoPoziciok(e17, e16);
					sz1.setKezdoPoziciok(e14, e13);
					k1.setKezdoPoziciok(e15, e14);
					k2.setKezdoPoziciok(e16, e15);
					JM.idoMeres();
					logger.setLevel(Level.INFO);
					JM.idoMeres();
					break;
				case 10:
					logger.setLevel(Level.OFF);
					init();
					k1.setKezdoPoziciok(e6, e5);
					k2.setKezdoPoziciok(e6, e5);
					logger.setLevel(Level.INFO);
					JM.utkozesEllenorzes();
					break;
				case 11:
					logger.setLevel(Level.OFF);
					init();
					JM.vonatInditas();
					JM.vonatInditas();
					JM.vonatInditas();
					JM.vonatInditas();
					JM.vonatInditas();
					JM.vonatInditas();
					JM.vonatInditas();
					k1.kiurit();
					k2.kiurit();
					k3.kiurit();
					k4.kiurit();
					logger.setLevel(Level.INFO);
					JM.gyozelemEllenorzes();
					break;
				case 12:
					logger.setLevel(Level.OFF);
					init();
					logger.setLevel(Level.INFO);
					JM.kilepes();
					break;
				case 15:
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static void init() {
		logger.log(Level.INFO, "Játek elemeinek inicializálása: \n");

		// Sin konstuktorok
		e0 = new Sin("sin0");
		e1 = new Sin("sin1");
		e2 = new Sin("sin2");
		e3 = new Sin("sin3");
		e4 = new Sin("sin4");
		e5 = new Sin("sin5");
		e6 = new Sin("sin6");
		e7 = new Sin("sin7");
		e8 = new Sin("sin8");
		e9 = new Keresztezodes("keresztezodes9");
		e10 = new Sin("sin10");
		e11 = new Sin("sin11");
		e12 = new Valto("valto12");
		e13 = new Sin("sin13");
		e14 = new Sin("sin14");
		e15 = new Sin("sin15");
		e16 = new Sin("sin16");
		e17 = new Allomas(1, true, "allomas17");
		e18 = new Sin("sin18");
		e19 = new Valto("valto19");
		e20 = new Allomas(2, false, "allomas20");
		e21 = new Sin("sin21");
		e22 = new Sin("sin22");
		e23 = new Sin("sin23");
		e24 = new Sin("sin24");
		e25 = new Sin("sin25");
		e26 = new Sin("sin26");
		e27 = new Sin("sin27");
		e28 = new Sin("sin28");

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
		e9.setSzomszedok(e8, e10, e18, e28);
		e10.setSzomszedok(e9, e11, null, null);
		e11.setSzomszedok(e10, e12, null, null);
		e12.setSzomszedok(e11, e13, e23, null);
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
		m1 = new Mozdony("mozdony1");
		sz1 = new Szeneskocsi("szenekocsi1");
		k1 = new Kocsi(2, "kocsi1");
		k2 = new Kocsi(1, "kocsi2");

		m2 = new Mozdony("mozdony2");
		sz2 = new Szeneskocsi("szenekocsi2");
		k3 = new Kocsi(1, "kocsi3");
		k4 = new Kocsi(2, "kocsi4");

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
		p0 = new Palya(2, 3, 5, palyaElemek1, vonatok1, "palya1");
		p1 = new Palya(2, 3, 5, palyaElemek1, vonatok1, "palya2");

		palyak = new ArrayList<Palya>();

		// Elsõ hozzáadása a pályalistához
		palyak.add(p0);
		palyak.add(p1);

		// JatekMotor konstruktor
		JM = new JatekMotor(palyak);

	}
}
