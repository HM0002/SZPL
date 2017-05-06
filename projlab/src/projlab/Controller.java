package projlab;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	int leptetes_ideje = 700;

	Controller() {

		/**
		 * Beágyazott idõzített feladat osztály, külön szálon fog futni
		 */
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				if (jatekFut) {
					gameRun();
				}
			}
		};

		/**
		 * Az idõzítõ beállítása, elindítása.
		 */
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, leptetes_ideje / 10);
	}

	/**
	 * Aktuális JatekMotor referenciája.
	 */
	JatekMotor JM;

	/** A játék futó/várakozó állapotát tárolja */
	Boolean jatekFut = false;

	/**
	 * Aktuális kirajzoló objektum.
	 */
	View view = null;

	/**
	 * A függvény kívülrõl van idõzítve adott idõnként hívva. Két segéd
	 * változónk van, az utkozes és a gyozelem, melyeket a függvény elején
	 * kiértékelünk, és csak akkor léptetünk az esményeken (idoMeres), ha nincs
	 * ütközés és nincs gyõzelem. Ha ez teljesült meghívjuk az idoMeres
	 * metódust, és ha eltelt 1 másodperc, az visszatér igazzal (valamint
	 * lépteti is a játékmotort), egyébként nem csinálunk semmit. Ez után ismét
	 * meghívjuk a gyozelemEllenorzes metódust, ami ha igazzal tér vissza,
	 * megnézzük, hogy van-e még pálya hátra a palyak listában. Ha nincs,
	 * végigvittük a játékot, errõl értesítjük a felhasználót és visszatérünk a
	 * menübe. Ha van még pálya hátra, felhívjuk a palyaBetoltes metódust, ami
	 * betölti az új pályát, valamint érvénytelenítjük az elõzõ pálya ablakát és
	 * csinálunk új View-t az új pályának. A gyozelemEllenorzes után felhívjuk a
	 * vonatInditas metódust, és kirajzoljuk a view-al az aktuális játékállást.
	 */
	void gameRun() {
		logger.log(Level.INFO, "Controller.gameRun()");

		boolean utkozes = JM.utkozesEllenorzes();
		boolean gyozelem = JM.gyozelemEllenorzes();

		if (!utkozes && !gyozelem) {
			if (idoMeres()) {

				if (JM.utkozesEllenorzes()) {
					// utkozes popup
					logger.log(Level.INFO, "Controller.gameRun() metódusban ütközés történt!");
					setJatekFutas();
					view.tajekoztatUser("Ütközés történt, vesztettél!", JM);
					view.draw(JM);
				}

				// GUI input kezelések
				else if (JM.gyozelemEllenorzes()) {
					if (JM.getPalyaSzam() < 2) {
						logger.log(Level.INFO, "Megnyertük a játékot!");
						setJatekFutas();
						view.tajekoztatUser("Gyõztél!", JM);
						view.draw(JM);
						return;
					} else {
						logger.log(Level.INFO, "Megnyertük a pályát!");
						JM.palyaBetoltes();
						view.dispose();
						view = new View(this, JM);
					}
				}
				JM.vonatInditas();
				view.draw(JM);
			}
		}
	}

	/**
	 * Vizsgáljuk az eltelt idõt. Ha eltelt egy adott idõ (1 másodperc),
	 * felhivjuk az idoEltelt metódus, majd visszatérünk igazzal, tehát hogy
	 * eltelt a másodperc. Ha nem telt el, hamissal térünk vissza.
	 */
	public boolean idoMeres() {
		if (Math.abs(System.currentTimeMillis()) - JM.getPrevTime() > leptetes_ideje) {
			view.draw(JM);
			JM.idoEltelt();
			return true;
		}
		return false;
	}

	/**
	 * Elindítja az idõt(beállítja a prevTime értékét), majd meghívja a gameRun
	 * metódust.
	 */
	public void ujJatek() {
		logger.log(Level.INFO, "Controller.ujJatek()");
		JM.setPrevTime(System.currentTimeMillis());
		jatekFut = true;
		gameRun();
	}

	/**
	 * A felhasználói parancsokat értelmezõ és végrehajtó függvény.
	 */
	void commandMapping(String s[], BufferedReader br) throws IOException {
		Level tmp = logger.getLevel();

		switch (s[0]) {

		case "loadPalya":
			init();
			break;

		case "Palya":
			ArrayList<Palya> palyak = new ArrayList<Palya>();
			Palya p_user = palyaBetoltes(br, "Palya " + s[1]);
			palyak.add(p_user);
			JM = new JatekMotor(palyak);
			if (view != null)
				view.dispose();
			view = new View(this, JM);
			break;

		case "vonatInditas":
			JM.vonatInditas();
			break;

		case "kirajzolas":
			view.draw(JM);
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
					if (jarmu.getID().equals(s[1])) {
						logger.setLevel(tmp);
						jarmu.setKezdoPoziciok(p, ep);
					}
			break;

		case "kiurit":
			tmp = logger.getLevel();
			logger.setLevel(Level.OFF);
			for (Vonat vonat : JM.getAktualisPalya().getVonatok())
				for (Jarmu jarmu : vonat.getJarmuvek())
					if (jarmu.getID().equals(s[1])) {
						logger.setLevel(tmp);
						((Kocsi) jarmu).kiurit();
					}
			break;

		case "utkozesEllenorzes":
			JM.utkozesEllenorzes();
			break;

		case "setVarakozoUtas":
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

		case "setVonatSzamlalo":
			JM.setVonatSzamlalo();
			break;

		case "palyaBetoltes":
			JM.palyaBetoltes();
			view.dispose();
			view = new View(this, JM);
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

	/**
	 * A pályák listáját állítja elõ elõre definiált forrásokból. a
	 * palyaBetoltes fuggvény segítsegevel. Ezután létrehozza a JatekMotor-t és
	 * a View-t.
	 */
	private void init() throws IOException {

		ArrayList<Palya> palyak = new ArrayList<Palya>();

		Palya p2 = palyaBetoltes(new BufferedReader(new FileReader("palyak\\palya_2.txt")), "Palya 2");
		palyak.add(p2);

		Palya p1 = palyaBetoltes(new BufferedReader(new FileReader("palyak\\palya_1.txt")), "Palya 1");
		palyak.add(p1);
		/*
		 * Palya p2 = palyaBetoltes( new BufferedReader(new
		 * FileReader("palyak\\palya_2.txt")), "Palya 1"); palyak.add(p2);
		 */

		Palya p25 = palyaBetoltes(new BufferedReader(new FileReader("palyak\\palya_25.txt")), "Palya 25");
		palyak.add(p25);

		JM = new JatekMotor(palyak);
		view = new View(this, JM);

	}

	/**
	 * Létrehoz majd visszaad egy palya objektumot a paraméterül kapott
	 * BufferedReader adatforrásból az id azonosítóval.
	 */
	private Palya palyaBetoltes(BufferedReader a, String id) throws IOException {

		BufferedReader reader = a;

		logger.log(Level.INFO, "Pályabetöltés elindult!");

		String[][] palyaKep = null;
		int columns, rows;
		int cnt = 0;

		String line = null;
		String[] params = null;

		ArrayList<PalyaElem> palyaElemek;
		Vonat v = null;
		ArrayList<Vonat> vonatok = new ArrayList<Vonat>();

		PalyaElem pe = null;

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
				logger.log(Level.INFO, "Nem az elsõ sorban meghatározott méretû pálya került definiálásra! Hiba a(z) "
						+ (i + 1) + ". sorban!");
				System.exit(0);
			}

			for (int j = 0; j < columns; j++) {
				tmp = " " + params[j] + " ";
				palyaKep[i][j] = tmp;
				int[] poz = { j, i };

				if (params[j].contains("S")) {
					counter++;
					if (counter > cnt) {
						logger.setLevel(Level.INFO);
						logger.log(Level.INFO, "Nem az elsõ sorban meghatározott számú pályaelem került definiálásra!");
						System.exit(0);
					}
					pe = new Sin(tmp, poz);
					palyaElemek.set(Integer.parseInt(params[j].substring(1)), pe);

				} else if (params[j].contains("A")) {
					counter++;
					if (counter > cnt) {
						logger.setLevel(Level.INFO);
						logger.log(Level.INFO, "Nem az elsõ sorban meghatározott számú pályaelem került definiálásra!");
						System.exit(0);
					}
					pe = new Allomas(tmp, poz);
					palyaElemek.set(Integer.parseInt(params[j].substring(1)), pe);

				} else if (params[j].contains("V")) {
					counter++;
					if (counter > cnt) {
						logger.setLevel(Level.INFO);
						logger.log(Level.INFO, "Nem az elsõ sorban meghatározott számú pályaelem került definiálásra!");
						System.exit(0);
					}
					pe = new Valto(tmp, poz);
					palyaElemek.set(Integer.parseInt(params[j].substring(1)), pe);

				} else if (params[j].contains("K")) {
					counter++;
					if (counter > cnt) {
						logger.setLevel(Level.INFO);
						logger.log(Level.INFO, "Nem az elsõ sorban meghatározott számú pályaelem került definiálásra!");
						System.exit(0);
					}
					pe = new Keresztezodes(tmp, poz);
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
								+ palyaElemek.get(i).getID() + " szomszédjai helyett ezt találtam: " + params[0]);
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

		return new Palya(keslelteto, palyaElemek, vonatok, palyaKep, id);

	}

	/**
	 * Új játék gomb megnyomás esemény kezelése
	 */
	public void ujJatekKezdes() throws IOException {
		jatekFut = false;
		init();
	}

	public void setJatekFutas() {
		jatekFut = !jatekFut;
	}

	public Boolean getJatekFut() {
		return jatekFut;
	}

	/**
	 * Cella hívja, hogy erre az ID-jû elemre történt kattintás
	 */
	public void kattintottElem(String id) {
		if (id.contains("S")) {
			for (PalyaElem palyaElem : JM.getAktualisPalya().getElemek())
				if (palyaElem.getID().equals(id)) {
					((Sin) palyaElem).setAlagut();
				}
			view.draw(JM);
		} else if (id.contains("V")) {
			for (PalyaElem palyaElem : JM.getAktualisPalya().getElemek())
				if (palyaElem.getID().equals(id)) {
					((Valto) palyaElem).atallit();
				}
			view.draw(JM);

		}
	}

	/** Beállítja a léptetés idõközét */
	public void setLetetesIdeje(int i) {
		leptetes_ideje = i;
	}
	
	/** Visszatér a léptetés aktuális idõközével */
	public int getLetetesIdeje() {
		return leptetes_ideje;
	}
}
