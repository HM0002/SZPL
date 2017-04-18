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

/**
 * Main oszt�ly:
 * 
 * Inputok/Outputok kezel�se, inicializ�l�s, kirajzol�s, bemeneti nyelv
 * �rt�lemez�se
 * 
 * Felel�ss�g: Kezeli az Inputokat/Outputokat, inicializ�lja a p�ly�t
 * (p�lyaelemeket �s vonatokat is) a kiv�lasztott forr�sb�l, majd ezekkel
 * inicializ�lja a j�t�kmotort. Kirajzolja az aktu�lis j�t�k�ll�st. �rtelmezi a
 * bemeneti nyelvet, v�grehajtja a kapott utas�t�sokat.
 */
public class Main {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	/**
	 * Aktu�lis JatekMotor referenci�ja.
	 */
	static JatekMotor JM;

	/**
	 * Az inputok forr�sa
	 */
	public static BufferedReader br = null;

	/**
	 * L�trehoz �s be�ll�t a logol�shoz, kirajzol�shoz egy statikus Logger
	 * p�ld�nyt. Be�ll�tja a parancssori argumentumok alapj�n a BufferedReader
	 * adatforr�s�t. Ezut�n egy ciklusban elkezdi soronk�nt feldolgozni a
	 * bemenetet a commandMapping f�ggv�ny seg�ts�g�vel.
	 */
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
			br = new BufferedReader(
					new FileReader(System.getProperty("user.home") + "\\teszt_bemenetek\\" + args[1] + ".txt"));
		else {
			System.out.println("�rv�nytelen bemenet!");
			// JM.ujJatek();
			// Majd a k�sz j�t�kban.
			System.exit(0);
		}

		logger.setLevel(Level.OFF);

		String line;
		while ((line = br.readLine()) != null) {
			String[] lines = line.split(" ");
			commandMapping(lines);
		}

	}

	/**
	 * L�trehoz majd visszaad egy palya objektumot a param�ter�l kapott
	 * BufferedReader adatforr�sb�l az id azonos�t�val.
	 */
	private static Palya palyaBetoltes(BufferedReader a, String id) throws IOException {

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

	/**
	 * A p�ly�k list�j�t �ll�tja el� el�re defini�lt forr�sokb�l. a
	 * palyaBetoltes fuggv�ny seg�tsegevel.
	 */
	private static void init() throws IOException {

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
	 * Az aktu�lis p�lya �s j�t�k �ll�s kimenetre kirajzol�s��rt felel�s
	 * f�ggv�ny.
	 */
	private static void draw() {

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

	/**
	 * A felhaszn�l�i parancsokat �rtelmez� �s v�grehajt� f�ggv�ny.
	 */
	private static void commandMapping(String s[]) throws IOException {
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
}