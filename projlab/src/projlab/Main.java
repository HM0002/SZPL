package projlab;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	// Jatekmotor létrehozása
	static JatekMotor JM;
	public static BufferedReader br = null;
	
	public static void main(String[] args) throws SecurityException, IOException {

		for (Handler handler : logger.getParent().getHandlers()) {
			logger.getParent().removeHandler(handler);
		}

		CustomRecordFormatter formatter = new CustomRecordFormatter();

		FileHandler fileHandler = new FileHandler("%h\\kimenet.txt");
		fileHandler.setFormatter(formatter);
		logger.addHandler(fileHandler);

		if (args[0].equals("K"))
			br = new BufferedReader(new InputStreamReader(System.in));
		else if (args[0].equals("F"))
			br = new BufferedReader(new FileReader(System.getProperty("user.home") + "\\teszt_bemenetek\\" + args[1]));
		else {
			System.out.println("Érvénytelen bemenet!");
			// JM.ujJatek();
			System.exit(0);
		}

		logger.setLevel(Level.OFF);

		String line;
		while ((line = br.readLine()) != null) {
			String[] lines = line.split(" ");
			commandMapping(lines);
		}

	}

	private static Palya palyaBetoltes(BufferedReader a, String id) throws IOException {

		BufferedReader reader = a;

		// vedd le a kommentet az ezalatti sorról a debug-kiíráshoz!
		// logger.setLevel(Level.INFO);

		logger.log(Level.INFO, "Pályabetöltés elindult!");

		// Pálya kép lokális változó
		String[][] palyaKep = null;

		// mezõk, sorok
		int columns, rows;

		// elemek
		int cnt = 0;

		// a konzolról is olvasni tudóhoz nem kell
		// reader = new BufferedReader(new FileReader(a));

		String line = null;
		String[] params = null;

		// pályaelemk tömbje
		ArrayList<PalyaElem> palyaElemek;

		// vonat elem
		Vonat v = null;

		// vonatok tömbje
		ArrayList<Vonat> vonatok = new ArrayList<Vonat>();

		// palyaelem
		PalyaElem pe = null;

		// local String tmp
		String tmp;

		line = reader.readLine();
		params = line.split(" ");
		columns = Integer.parseInt(params[0]);
		rows = Integer.parseInt(params[1]);
		cnt = Integer.parseInt(params[2]);

		palyaElemek = new ArrayList<PalyaElem>(cnt);
		for (int i = 0; i < cnt; i++)
			palyaElemek.add(null);

		palyaKep = new String[rows][columns];

		// palyakep, elemek létrehozása
		for (int i = 0, counter = 0; i < rows; i++) {
			line = reader.readLine();
			params = line.split(",");
			if (params.length != columns) {
				logger.setLevel(Level.INFO);
				int errorat = i + 1;
				logger.log(Level.INFO, "Nem az elsõ sorban meghatározott méretû pálya került definiálásra! Hiba a(z) "
						+ errorat + ". sorban!");
				System.exit(0);
			}

			for (int j = 0; j < columns; j++) {
				tmp = " " + params[j] + " ";
				palyaKep[i][j] = tmp;

				if (params[j].contains("S")) {
					counter++;
					if (counter > cnt) {
						logger.setLevel(Level.INFO);
						logger.log(Level.INFO, "Nem az elsõ sorban meghatározott számú pályaelem került definiálásra!");
						System.exit(0);
					}
					pe = new Sin(tmp);
					palyaElemek.set(Integer.parseInt(params[j].substring(1)), pe);
				} else if (params[j].contains("A")) {
					counter++;
					if (counter > cnt) {
						logger.setLevel(Level.INFO);
						logger.log(Level.INFO, "Nem az elsõ sorban meghatározott számú pályaelem került definiálásra!");
						System.exit(0);
					}
					pe = new Allomas(tmp);
					palyaElemek.set(Integer.parseInt(params[j].substring(1)), pe);
				} else if (params[j].contains("V")) {
					counter++;
					if (counter > cnt) {
						logger.setLevel(Level.INFO);
						logger.log(Level.INFO, "Nem az elsõ sorban meghatározott számú pályaelem került definiálásra!");
						System.exit(0);
					}
					pe = new Valto(tmp);
					palyaElemek.set(Integer.parseInt(params[j].substring(1)), pe);
				} else if (params[j].contains("K")) {
					counter++;
					if (counter > cnt) {
						logger.setLevel(Level.INFO);
						logger.log(Level.INFO, "Nem az elsõ sorban meghatározott számú pályaelem került definiálásra!");
						System.exit(0);
					}
					pe = new Keresztezodes(tmp);
					palyaElemek.set(Integer.parseInt(params[j].substring(1)), pe);
				} else if (params[j].contains("    "))
					;
				else {
					logger.setLevel(Level.INFO);
					logger.log(Level.INFO, "Nem megfelelõ szintaktikájú pályaelem! " + params[j]);
					System.exit(0);
				}
			}
		}
		// üres kell legyen
		if (!((line = reader.readLine()).isEmpty())) {
			logger.setLevel(Level.INFO);
			logger.log(Level.INFO,
					"Nem megfelelõ bemeneti FILE, a pályaelemek után üres sor kell jöjjön! Ezt találtam az üres sor helyett: "
							+ line);
			System.exit(0);
		}
		// megfelelõ mennyiségû elemnek lennie kell, nem lehet lyuk
		for (PalyaElem p : palyaElemek)
			if (p == null) {
				logger.setLevel(Level.INFO);
				logger.log(Level.INFO,
						"Nem megfelelõ bemeneti FILE, nem megfelelõ számû pályaelemet találtam! Lehet, nem sorfolytonos a számozás!");
				System.exit(0);
			}

		// Jarmuvek letrehozasa
		for (int i = 0; !((line = reader.readLine()).isEmpty()); i++) {
			params = line.split(" ");
			ArrayList<Jarmu> jarmuvek = new ArrayList<Jarmu>();
			Jarmu jarmu = null;

			if (!(params[0].contains("M"))) {
				logger.setLevel(Level.INFO);
				logger.log(Level.INFO, "Minden vonat elsõ jármûvének mozdony típusúnak kell lennie!");
				System.exit(0);
			}

			for (String s : params) {
				if (s.contains("M"))
					jarmu = new Mozdony(s);
				else if (s.contains("C"))
					jarmu = new Szeneskocsi(s);
				else if (s.contains("K"))
					jarmu = new Kocsi(Integer.parseInt(s.substring(1, 2)), s);
				else {
					logger.setLevel(Level.INFO);
					logger.log(Level.INFO, "Nem megfelelõ jármû típus! " + s);
					System.exit(0);
				}
				jarmuvek.add(jarmu);
			}
			v = new Vonat(jarmuvek, "VONAT" + i);
			vonatok.add(v);
		}

		// Szomszédság beállítása
		for (int i = 0; i < cnt; i++) {
			line = reader.readLine();
			params = line.split(" ");
			if (Integer.parseInt(params[0]) != i) {
				logger.setLevel(Level.INFO);
				logger.log(Level.INFO,
						"Nem megfelelõ szomszédság elem! Lehet, nem megfelelõ sorrendben lettek megadva, vagy kimaradt valamelyik! "
								+ palyaElemek.get(i).getID() + " szomszédaji helyett ezt találtam: " + params[0]);
				System.exit(0);
			}
			if (palyaElemek.get(i).getID().contains("S") || palyaElemek.get(i).getID().contains("A")) {
				if (params.length != 3) {
					logger.setLevel(Level.INFO);
					logger.log(Level.INFO,
							"Nem megfelelõ szomszédság elem! Sin és Allomas típusnak két szomszédja kell legyen! "
									+ palyaElemek.get(i).getID());
					System.exit(0);
				}
				palyaElemek.get(i).setSzomszedok(palyaElemek.get(Integer.parseInt(params[1])),
						palyaElemek.get(Integer.parseInt(params[2])), null, null);
			} else if (palyaElemek.get(i).getID().contains("V")) {
				if (params.length != 4) {
					logger.setLevel(Level.INFO);
					logger.log(Level.INFO,
							"Nem megfelelõ szomszédság elem! Valto típusnak három szomszédja kell legyen! "
									+ palyaElemek.get(i).getID());
					System.exit(0);
				}
				palyaElemek.get(i).setSzomszedok(palyaElemek.get(Integer.parseInt(params[1])),
						palyaElemek.get(Integer.parseInt(params[2])), palyaElemek.get(Integer.parseInt(params[3])),
						null);
			} else if (palyaElemek.get(i).getID().contains("K")) {
				if (params.length != 5) {
					logger.setLevel(Level.INFO);
					logger.log(Level.INFO,
							"Nem megfelelõ szomszédság elem! Keresztezodes típusnak öt szomszédja kell legyen! "
									+ palyaElemek.get(i).getID());
					System.exit(0);
				}
				palyaElemek.get(i).setSzomszedok(palyaElemek.get(Integer.parseInt(params[1])),
						palyaElemek.get(Integer.parseInt(params[2])), palyaElemek.get(Integer.parseInt(params[3])),
						palyaElemek.get(Integer.parseInt(params[4])));
			}
		}

		// üres kell legyen
		if (!((line = reader.readLine()).isEmpty())) {
			logger.setLevel(Level.INFO);
			logger.log(Level.INFO,
					"Nem megfelelõ bemeneti FILE, a szomszédossági beállítások után üres sor kell jöjjön! Ezt találtam az üres sor helyett: "
							+ line);
			System.exit(0);
		}

		// Allomasok beallitasa
		while (!((line = reader.readLine()).isEmpty())) {
			params = line.split(" ");
			if (palyaElemek.get(Integer.parseInt(params[0])).getID().contains("A"))
				((Allomas) (palyaElemek.get(Integer.parseInt(params[0])))).setSzin(Integer.parseInt(params[1]));
			else {
				logger.setLevel(Level.INFO);
				logger.log(Level.INFO,
						"Ez a pályaelem nem Allomas! " + palyaElemek.get(Integer.parseInt(params[0])).getID());
				System.exit(0);
			}
			// TODO szín ellenõrzése
			if (params[2].equals("TRUE"))
				((Allomas) (palyaElemek.get(Integer.parseInt(params[0])))).setVarakozoUtas(Boolean.TRUE);
			else if (params[2].equals("FALSE"))
				((Allomas) (palyaElemek.get(Integer.parseInt(params[0])))).setVarakozoUtas(Boolean.FALSE);
			else {
				logger.setLevel(Level.INFO);
				logger.log(Level.INFO,
						"Az Allomas varakozo utas beallitasa csak \"TRUE\" vagy \"FALSE\" lehet! Ezt találtam: "
								+ params[2]);
				System.exit(0);
			}
		}

		int keslelteto = Integer.parseInt(reader.readLine());

		// a konzolról is olvasni tudóhoz nem kell
		// reader.close();

		return new Palya(keslelteto, palyaElemek, vonatok, palyaKep, id);

	}

	private static void init(int mode) throws IOException {

		ArrayList<Palya> palyak = new ArrayList<Palya>();

		if (mode == 1) {
			// 1. (teszt) pálya Palya létrehozása
			Palya p1 = palyaBetoltes(new BufferedReader(new FileReader(System.getProperty("user.home") + "\\palyak\\palya_1.txt")), "Palya 1");

			// 1. (teszt) pálya hozzáadása a pályalistához
			palyak.add(p1);
		}

		else if (mode == 25) {
			// 25. pálya Palya létrehozása
			Palya p25 = palyaBetoltes(new BufferedReader(new FileReader(System.getProperty("user.home") + "\\palyak\\palya_25.txt")), "Palya 25");

			// 25. pálya hozzáadása a pályalistához
			palyak.add(p25);

		}

		// TODO több pálya mód pályáinka létrehozása, betöltése

		// JatekMotor konstruktor
		JM = new JatekMotor(palyak);

	}

	public static void draw() {

		// Változó az eredeti pályaképnek
		String[][] palyaKepX = null;

		// mezõk, sorok
		int columns, rows;

		// palya kepenek elkerese a jatekmotortól
		palyaKepX = JM.getAktualisPalya().getPalyaKepX();
		columns = palyaKepX[0].length;
		rows = palyaKepX.length;

		// Változó a kirajzolandó pályaképnek az eredeti alapján
		String[][] palyaKep = new String[rows][columns];

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
				palyaKep[i][j] = palyaKepX[i][j];
			}
		}

		// set vonatok
		logger.setLevel(Level.OFF);
		for (Vonat vonat : JM.getAktualisPalya().getVonatok())
			for (Jarmu jarmu : vonat.getJarmuvek())
				for (int i = 0; i < rows; i++)
					for (int j = 0; j < columns; j++) {
						if (jarmu.getPozicio() != null) {
							if (palyaKepX[i][j].equals(jarmu.getPozicio().getID())) {
								if (palyaKepX[i][j].equals(palyaKep[i][j]))
									palyaKep[i][j] = "(" + jarmu.getID() + ")";
								else
									palyaKep[i][j] = "CRASH!";
							}
						}
					}

		// set alagutak
		for (PalyaElem palyaElem : JM.getAktualisPalya().getElemek())
			if (palyaElem.getAlagut()) {
				for (int i = 0; i < rows; i++)
					for (int j = 0; j < columns; j++) {
						if (palyaKepX[i][j].equals(palyaElem.getID()))
							palyaKep[i][j] = " |AL| ";
					}
			}

		// draw pálya
		for (int i = 0; i < rows; i++) {
			String temp = "";
			for (int j = 0; j < columns - 1; j++)
				temp += palyaKep[i][j] + "   ";
			temp += palyaKep[i][columns - 1];
			logger.setLevel(Level.INFO);
			logger.log(Level.WARNING, temp + "\n");
		}

		// write váltó állások
		logger.log(Level.WARNING, "Váltók állása:");
		logger.setLevel(Level.OFF);
		for (PalyaElem palyaElem : JM.getAktualisPalya().getElemek())
			if (palyaElem instanceof Valto) {
				logger.setLevel(Level.INFO);
				logger.log(Level.WARNING, palyaElem.getID() + ":" + ((Valto) palyaElem).getAllas()[0].getID() + "<-->"
						+ ((Valto) palyaElem).getAllas()[1].getID());
			}

		// write állomás színek, várakozó utasok
		logger.log(Level.WARNING, "\nÁllomások színe:");
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

	public static void commandMapping(String s[]) throws IOException {
		Level tmp = logger.getLevel();
		
		

		switch (s[0]) {

		case "loadPalya":
			init(Integer.parseInt(s[1]));
			break;
			
		case "Palya":
			ArrayList<Palya> palyak = new ArrayList<Palya>();
			Palya p_user = palyaBetoltes(br,"Palya "+s[1]);
			palyak.add(p_user);
			JM = new JatekMotor(palyak);
			break;

		case "vonatInditas":
			JM.vonatInditas();
			break;

		case "kirajzolas":
			draw();
			break;

		case "kirajzolasStart":
			logger.setLevel(Level.INFO);
			break;

		case "step":
			JM.idoEltelt();
			break;

		case "switchValto":
			tmp = logger.getLevel();
			logger.setLevel(Level.OFF);
			for (PalyaElem palyaElem : JM.getAktualisPalya().getElemek())
				if (palyaElem.getID().equals(" " + s[1] + " ")) {
					logger.setLevel(tmp);
					((Valto) palyaElem).atallit();
				}
			break;

		case "setAlagut":
			tmp = logger.getLevel();
			logger.setLevel(Level.OFF);
			for (PalyaElem palyaElem : JM.getAktualisPalya().getElemek())
				if (palyaElem.getID().equals(" " + s[1] + " ")) {
					logger.setLevel(tmp);
					((Sin) palyaElem).setAlagut();
				}
			break;

		case "setKezdoPozicio":
			tmp = logger.getLevel();
			logger.setLevel(Level.OFF);
			PalyaElem p = null;
			PalyaElem ep = null;
			for (PalyaElem palyaElem : JM.getAktualisPalya().getElemek())
				if (palyaElem.getID().equals(" " + s[2] + " ")) {
					logger.setLevel(tmp);
					p = palyaElem;
				}

			tmp = logger.getLevel();
			logger.setLevel(Level.OFF);
			for (PalyaElem palyaElem : JM.getAktualisPalya().getElemek())
				if (palyaElem.getID().equals(" " + s[3] + " ")) {
					logger.setLevel(tmp);
					ep = palyaElem;
				}

			tmp = logger.getLevel();
			logger.setLevel(Level.OFF);
			for (Vonat vonat : JM.getAktualisPalya().getVonatok())
				for (Jarmu jarmu : vonat.getJarmuvek())
					if (jarmu.getID().equals(" " + s[1] + " ")) {
						logger.setLevel(tmp);
						jarmu.setKezdoPoziciok(p, ep);
					}
			break;

		case "kiurit":
			tmp = logger.getLevel();
			logger.setLevel(Level.OFF);
			for (Vonat vonat : JM.getAktualisPalya().getVonatok())
				for (Jarmu jarmu : vonat.getJarmuvek())
					if (jarmu.getID().equals(" " + s[1] + " ")) {
						logger.setLevel(tmp);
						((Kocsi) jarmu).kiurit();
					}
			break;

		case "utkozesEllenorzes":
			JM.utkozesEllenorzes();
			break;

		case "setVarakouoUtas":
			boolean temp;
			if (s[2].equals("true"))
				temp = true;
			else
				temp = false;

			tmp = logger.getLevel();
			logger.setLevel(Level.OFF);
			for (PalyaElem palyaElem : JM.getAktualisPalya().getElemek())
				if (palyaElem.getID().equals(" " + s[1] + " ")) {
					logger.setLevel(tmp);
					((Allomas) palyaElem).setVarakozoUtas(temp);
				}
			break;

		case "exit":
			JM.kilepes();
			break;

		case "gyozelemEllenorzes":
			JM.gyozelemEllenorzes();
			break;

		case "tesztVege":
			System.exit(0);
			break;
		}
	}
}