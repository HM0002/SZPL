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

	// Elsõ pályához Sin-eknek ArrayList létrehozása
	static ArrayList<Sin> sinek1;

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
	static Sin s0;
	static Sin s1;
	static Sin s2;
	static Sin s3;
	static Sin s4;
	static Sin s5;
	static Sin s6;
	static Sin s7;
	static Sin s8;
	static Valto s9;
	static Sin s10;
	static Sin s11;
	static Valto s12;
	static Sin s13;
	static Sin s14;
	static Sin s15;
	static Sin s16;
	static Allomas s17;
	static Sin s18;
	static Sin s19;
	static Allomas s20;
	static Sin s21;
	static Sin s22;
	static Sin s23;

	// Jarmu-vek létrehozása, elõször a vonat1 elemei
	static Mozdony m0;
	static Kocsi k1;
	static Kocsi k2;

	// majd a vonat2 elemei
	static Mozdony m3;
	static Kocsi k4;
	static Kocsi k5;

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
					+ "\n7: Váltó átállítása\n8: Állomás aktiválása\n9: Utasok leszállása\n10: Ütkozés ellenõrzés"
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
					s10.setAlagut();
					break;
				case 5:
					logger.setLevel(Level.OFF);
					init();
					s10.setAlagut();
					logger.setLevel(Level.INFO);
					s10.setAlagut();
					break;
				case 6:
					logger.setLevel(Level.OFF);
					init();
					JM.vonatInditas();
					s6.setAlagut();
					s10.setAlagut();
					JM.idoMeres();
					logger.setLevel(Level.INFO);
					JM.idoMeres();
					break;
				case 7:
					logger.setLevel(Level.OFF);
					init();
					logger.setLevel(Level.INFO);
					s9.atallit();
					break;
				case 8:
					logger.setLevel(Level.OFF);
					init();
					m0.setKezdoPoziciok(s17, s16);
					logger.setLevel(Level.INFO);
					JM.idoMeres();
					break;
				case 9:
					logger.setLevel(Level.OFF);
					init();
					m0.setKezdoPoziciok(s17, s16);
					k1.setKezdoPoziciok(s15, s14);
					k2.setKezdoPoziciok(s16, s15);
					JM.idoMeres();
					logger.setLevel(Level.INFO);
					JM.idoMeres();
					break;
				case 10:
					logger.setLevel(Level.OFF);
					init();
					k1.setKezdoPoziciok(s6, s5);
					k2.setKezdoPoziciok(s6, s5);
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
					k1.kiurit();
					k2.kiurit();
					k4.kiurit();
					k5.kiurit();
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
		s0 = new Sin("sin0");
		s1 = new Sin("sin1");
		s2 = new Sin("sin2");
		s3 = new Sin("sin3");
		s4 = new Sin("sin4");
		s5 = new Sin("sin5");
		s6 = new Sin("sin6");
		s7 = new Sin("sin7");
		s8 = new Sin("sin8");
		s9 = new Valto("valto9");
		s10 = new Sin("sin10");
		s11 = new Sin("sin11");
		s12 = new Valto("valto12");
		s13 = new Sin("sin13");
		s14 = new Sin("sin14");
		s15 = new Sin("sin15");
		s16 = new Sin("sin16");
		s17 = new Allomas(1, "allomas17");
		s18 = new Sin("sin18");
		s19 = new Sin("sin19");
		s20 = new Allomas(2, "allomas20");
		s21 = new Sin("sin21");
		s22 = new Sin("sin22");
		s23 = new Sin("sin23");

		sinek1 = new ArrayList<Sin>();

		// Sin-ek összekötése
		s0.setSzomszedok(s1, s1);
		s1.setSzomszedok(s0, s2);
		s2.setSzomszedok(s1, s3);
		s3.setSzomszedok(s2, s4);
		s4.setSzomszedok(s3, s5);
		s5.setSzomszedok(s4, s6);
		s6.setSzomszedok(s7, s17);
		s7.setSzomszedok(s6, s8);
		s8.setSzomszedok(s7, s9);
		s9.setSzomszedok(s8, s10, s18);
		s10.setSzomszedok(s9, s11);
		s11.setSzomszedok(s10, s12);
		s12.setSzomszedok(s11, s13, s23);
		s13.setSzomszedok(s12, s14);
		s14.setSzomszedok(s13, s15);
		s15.setSzomszedok(s14, s16);
		s16.setSzomszedok(s15, s17);
		s17.setSzomszedok(s16, s6);
		s18.setSzomszedok(s9, s19);
		s19.setSzomszedok(s18, s20);
		s20.setSzomszedok(s19, s21);
		s21.setSzomszedok(s20, s22);
		s22.setSzomszedok(s21, s23);
		s23.setSzomszedok(s22, s12);

		// sinek1 listához a Sin példányok hozzáadása
		sinek1.add(s0);
		sinek1.add(s1);
		sinek1.add(s2);
		sinek1.add(s3);
		sinek1.add(s4);
		sinek1.add(s5);
		sinek1.add(s6);
		sinek1.add(s7);
		sinek1.add(s8);
		sinek1.add(s9);
		sinek1.add(s10);
		sinek1.add(s11);
		sinek1.add(s12);
		sinek1.add(s13);
		sinek1.add(s14);
		sinek1.add(s15);
		sinek1.add(s16);
		sinek1.add(s17);
		sinek1.add(s18);
		sinek1.add(s19);
		sinek1.add(s20);
		sinek1.add(s21);
		sinek1.add(s22);
		sinek1.add(s23);

		// Jarmu (Kocsi és Mozdony) konstruktorok
		m0 = new Mozdony("mozdony0");
		k1 = new Kocsi(2, "kocsi1");
		k2 = new Kocsi(1, "kocsi2");
		m3 = new Mozdony("mozdony3");
		k4 = new Kocsi(1, "kocsi4");
		k5 = new Kocsi(2, "kocsi5");

		vonat1 = new ArrayList<Jarmu>();
		vonat2 = new ArrayList<Jarmu>();

		// vonatokba a Jarmu-vek elhelyezése: vonat1
		vonat1.add(m0);
		vonat1.add(k1);
		vonat1.add(k2);

		// vonatokba a Jarmu-vek elhelyezése: vonat2
		vonat2.add(m3);
		vonat2.add(k4);
		vonat2.add(k5);

		// Vonat konstruktorok
		v1 = new Vonat(vonat1, "vonat1");
		v2 = new Vonat(vonat2, "vonat2");

		vonatok1 = new ArrayList<Vonat>();

		// vonatok hozzáadása a vonatlistához
		vonatok1.add(v1);
		vonatok1.add(v2);

		// Palya konstruktor
		p0 = new Palya(2, 2, 4, sinek1, vonatok1, "palya1");
		p1 = new Palya(2, 2, 4, sinek1, vonatok1, "palya2");

		palyak = new ArrayList<Palya>();

		// Elsõ hozzáadása a pályalistához
		palyak.add(p0);
		palyak.add(p1);

		// JatekMotor konstruktor
		JM = new JatekMotor(palyak);

	}
}
