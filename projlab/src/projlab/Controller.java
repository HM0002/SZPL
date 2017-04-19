package projlab;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Aktu�lis JatekMotor referenci�ja.
	 */
	JatekMotor JM;

	/**
	 * Aktu�lis kirajzol� objektum.
	 */
	View view=new View(this, JM);

	/**
	 * Egy seg�d v�ltoz�nk van, az utkozes. �rt�ke hamis, �s egy loop-ban
	 * vagyunk, am�g ez �gy is marad. A loop-ban megh�vjuk az idoMeres met�dust,
	 * �s ha eltelt 1 m�sodperc, az visszat�r igazzal, egy�bk�nt nem csin�lunk
	 * semmit. Ha eltelt az 1 m�sodperc, megh�vjuk az utkozesEllenorzes
	 * met�dust, melynek a visszat�r�s�t �rt�k�l adjuk az utkozes-nek. Ez ut�n
	 * megh�juk a gyozelemEllenorzes met�dust, ami ha igazzal t�r vissza,
	 * megn�zz�k, hogy van-e m�g p�lya h�tra a palyak list�ban. Ha nincs,
	 * v�gigvitt�k a j�t�kot, err�l �rtes�tj�k a felhaszn�l�t �s visszat�r�nk a
	 * men�be. Ha van m�g p�lya h�tra, felh�vjuk a palyaBetoltes met�dust, ami
	 * bet�lti az �j p�ly�t. A gyozelemEllenorzes ut�n felh�vjuk a vonatInditas
	 * met�dust.
	 */
	private void run() {
		logger.log(Level.INFO, "Controller.run()");

		boolean utkozes = false;

		while (!utkozes) {
			if (idoMeres()) {
				utkozes = JM.utkozesEllenorzes();
				// GUI input kezel�sek
				if (JM.gyozelemEllenorzes()) {
					if (JM.getPalyaSzam() < 2) {
						logger.log(Level.INFO, "Megnyert�k a j�t�kot!");
						// gyozelem popup
						return;
					} else {
						logger.log(Level.INFO, "Megnyert�k a p�ly�t!");
						JM.palyaBetoltes();
					}
				}
				JM.vonatInditas();
			}
		}
		// utkozes popup
		logger.log(Level.INFO, "Controller.run() met�dusban �tk�z�s t�rt�nt!");
	}

	/**
	 * Vizsg�ljuk az eltelt id�t. Ha eltelt egy adott id� (1 m�sodperc),
	 * felhivjuk az idoEltelt met�dus, majd visszat�r�nk igazzal, teh�t hogy
	 * eltelt a m�sodperc. Ha nem telt el, hamissal t�r�nk vissza.
	 */
	public boolean idoMeres() {
		if (Math.abs(System.currentTimeMillis()) - JM.getPrevTime() > 1000) {
			JM.idoEltelt();
			view.draw(JM);
			return true;
		}
		return false;
	}

	/**
	 * Elind�tja az id�t(be�ll�tja a prevTime �rt�k�t), majd megh�vja a run
	 * met�dust.
	 */
	public void ujJatek() {
		logger.log(Level.INFO, "Controller.ujJatek()");
		JM.setPrevTime(System.currentTimeMillis());
		run();
	}

	/**
	 * A felhaszn�l�i parancsokat �rtelmez� �s v�grehajt� f�ggv�ny.
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
	 * A p�ly�k list�j�t �ll�tja el� el�re defini�lt forr�sokb�l. a
	 * palyaBetoltes fuggv�ny seg�tsegevel.
	 */
	private void init() throws IOException {

		ArrayList<Palya> palyak = new ArrayList<Palya>();

		Palya p1 = palyaBetoltes(
				new BufferedReader(new FileReader(System.getProperty("user.home") + "\\palyak\\palya_1.txt")),
				"Palya 1");
		palyak.add(p1);

		Palya p25 = palyaBetoltes(
				new BufferedReader(new FileReader(System.getProperty("user.home") + "\\palyak\\palya_25.txt")),
				"Palya 25");
		palyak.add(p25);

		JM = new JatekMotor(palyak);

	}

	/**
	 * L�trehoz majd visszaad egy palya objektumot a param�ter�l kapott
	 * BufferedReader adatforr�sb�l az id azonos�t�val.
	 */
	private Palya palyaBetoltes(BufferedReader a, String id) throws IOException {

		BufferedReader reader = a;

		logger.log(Level.INFO, "P�lyabet�lt�s elindult!");

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

		// palyakep, elemek l�trehoz�sa
		for (int i = 0, counter = 0; i < rows; i++) {
			line = reader.readLine();
			params = line.split(",");
			if (params.length != columns) {
				logger.setLevel(Level.INFO);
				logger.log(Level.INFO, "Nem az els� sorban meghat�rozott m�ret� p�lya ker�lt defini�l�sra! Hiba a(z) "
						+ (i + 1) + ". sorban!");
				System.exit(0);
			}

			for (int j = 0; j < columns; j++) {
				tmp = " " + params[j] + " ";
				palyaKep[i][j] = tmp;

				if (params[j].contains("S")) {
					counter++;
					if (counter > cnt) {
						logger.setLevel(Level.INFO);
						logger.log(Level.INFO, "Nem az els� sorban meghat�rozott sz�m� p�lyaelem ker�lt defini�l�sra!");
						System.exit(0);
					}
					pe = new Sin(tmp);
					palyaElemek.set(Integer.parseInt(params[j].substring(1)), pe);

				} else if (params[j].contains("A")) {
					counter++;
					if (counter > cnt) {
						logger.setLevel(Level.INFO);
						logger.log(Level.INFO, "Nem az els� sorban meghat�rozott sz�m� p�lyaelem ker�lt defini�l�sra!");
						System.exit(0);
					}
					pe = new Allomas(tmp);
					palyaElemek.set(Integer.parseInt(params[j].substring(1)), pe);

				} else if (params[j].contains("V")) {
					counter++;
					if (counter > cnt) {
						logger.setLevel(Level.INFO);
						logger.log(Level.INFO, "Nem az els� sorban meghat�rozott sz�m� p�lyaelem ker�lt defini�l�sra!");
						System.exit(0);
					}
					pe = new Valto(tmp);
					palyaElemek.set(Integer.parseInt(params[j].substring(1)), pe);

				} else if (params[j].contains("K")) {
					counter++;
					if (counter > cnt) {
						logger.setLevel(Level.INFO);
						logger.log(Level.INFO, "Nem az els� sorban meghat�rozott sz�m� p�lyaelem ker�lt defini�l�sra!");
						System.exit(0);
					}
					pe = new Keresztezodes(tmp);
					palyaElemek.set(Integer.parseInt(params[j].substring(1)), pe);

				} else if (params[j].contains("    "))
					;

				else {
					logger.setLevel(Level.INFO);
					logger.log(Level.INFO, "Nem megfelel� szintaktik�j� p�lyaelem! " + params[j]);
					System.exit(0);
				}
			}
		}

		// �res kell legyen
		if (!((line = reader.readLine()).isEmpty())) {
			logger.setLevel(Level.INFO);
			logger.log(Level.INFO,
					"Nem megfelel� bemeneti FILE, a p�lyaelemek ut�n �res sor kell j�jj�n! Ezt tal�ltam az �res sor helyett: "
							+ line);
			System.exit(0);
		}

		// megfelel� mennyis�g� elemnek lennie kell, nem lehet lyuk
		for (PalyaElem p : palyaElemek)
			if (p == null) {
				logger.setLevel(Level.INFO);
				logger.log(Level.INFO,
						"Nem megfelel� bemeneti FILE, nem megfelel� sz�m� p�lyaelemet tal�ltam! Lehet, nem sorfolytonos a sz�moz�s!");
				System.exit(0);
			}

		// Jarmuvek letrehozasa
		for (int i = 0; !((line = reader.readLine()).isEmpty()); i++) {
			params = line.split(" ");
			ArrayList<Jarmu> jarmuvek = new ArrayList<Jarmu>();
			Jarmu jarmu = null;

			if (!(params[0].contains("M"))) {
				logger.setLevel(Level.INFO);
				logger.log(Level.INFO, "Minden vonat els� j�rm�v�nek mozdony t�pus�nak kell lennie!");
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
					logger.log(Level.INFO, "Nem megfelel� j�rm� t�pus! " + s);
					System.exit(0);
				}
				jarmuvek.add(jarmu);
			}

			v = new Vonat(jarmuvek, "VONAT" + i);
			vonatok.add(v);
		}

		// Szomsz�ds�g be�ll�t�sa
		for (int i = 0; i < cnt; i++) {
			line = reader.readLine();
			params = line.split(" ");
			if (Integer.parseInt(params[0]) != i) {
				logger.setLevel(Level.INFO);
				logger.log(Level.INFO,
						"Nem megfelel� szomsz�ds�g elem! Lehet, nem megfelel� sorrendben lettek megadva, vagy kimaradt valamelyik! "
								+ palyaElemek.get(i).getID() + " szomsz�djai helyett ezt tal�ltam: " + params[0]);
				System.exit(0);
			}
			if (palyaElemek.get(i).getID().contains("S") || palyaElemek.get(i).getID().contains("A")) {
				if (params.length != 3) {
					logger.setLevel(Level.INFO);
					logger.log(Level.INFO,
							"Nem megfelel� szomsz�ds�g elem! Sin �s Allomas t�pusnak k�t szomsz�dja kell legyen! "
									+ palyaElemek.get(i).getID());
					System.exit(0);
				}
				palyaElemek.get(i).setSzomszedok(palyaElemek.get(Integer.parseInt(params[1])),
						palyaElemek.get(Integer.parseInt(params[2])), null, null);
			} else if (palyaElemek.get(i).getID().contains("V")) {
				if (params.length != 4) {
					logger.setLevel(Level.INFO);
					logger.log(Level.INFO,
							"Nem megfelel� szomsz�ds�g elem! Valto t�pusnak h�rom szomsz�dja kell legyen! "
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
							"Nem megfelel� szomsz�ds�g elem! Keresztezodes t�pusnak �t szomsz�dja kell legyen! "
									+ palyaElemek.get(i).getID());
					System.exit(0);
				}
				palyaElemek.get(i).setSzomszedok(palyaElemek.get(Integer.parseInt(params[1])),
						palyaElemek.get(Integer.parseInt(params[2])), palyaElemek.get(Integer.parseInt(params[3])),
						palyaElemek.get(Integer.parseInt(params[4])));
			}
		}

		// �res kell legyen
		if (!((line = reader.readLine()).isEmpty())) {
			logger.setLevel(Level.INFO);
			logger.log(Level.INFO,
					"Nem megfelel� bemeneti FILE, a szomsz�doss�gi be�ll�t�sok ut�n �res sor kell j�jj�n! Ezt tal�ltam az �res sor helyett: "
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
						"Ez a p�lyaelem nem Allomas! " + palyaElemek.get(Integer.parseInt(params[0])).getID());
				System.exit(0);
			}
			if (params[2].equals("TRUE"))
				((Allomas) (palyaElemek.get(Integer.parseInt(params[0])))).setVarakozoUtas(Boolean.TRUE);
			else if (params[2].equals("FALSE"))
				((Allomas) (palyaElemek.get(Integer.parseInt(params[0])))).setVarakozoUtas(Boolean.FALSE);
			else {
				logger.setLevel(Level.INFO);
				logger.log(Level.INFO,
						"Az Allomas varakozo utas beallitasa csak \"TRUE\" vagy \"FALSE\" lehet! Ezt tal�ltam: "
								+ params[2]);
				System.exit(0);
			}
		}

		int keslelteto = Integer.parseInt(reader.readLine());

		return new Palya(keslelteto, palyaElemek, vonatok, palyaKep, id);

	}
	
	public void ujJatekKezdes() throws IOException{
		init();
		view=new View(this, JM);
		view.draw(JM);
	}
}
