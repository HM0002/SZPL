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

	// Jatekmotor l�trehoz�sa
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
			System.out.println("�rv�nytelen bemenet!");
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

		// vedd le a kommentet az ezalatti sorr�l a debug-ki�r�shoz!
		// logger.setLevel(Level.INFO);

		logger.log(Level.INFO, "P�lyabet�lt�s elindult!");

		// P�lya k�p lok�lis v�ltoz�
		String[][] palyaKep = null;

		// mez�k, sorok
		int columns, rows;

		// elemek
		int cnt = 0;

		// a konzolr�l is olvasni tud�hoz nem kell
		// reader = new BufferedReader(new FileReader(a));

		String line = null;
		String[] params = null;

		// p�lyaelemk t�mbje
		ArrayList<PalyaElem> palyaElemek;

		// vonat elem
		Vonat v = null;

		// vonatok t�mbje
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

		// palyakep, elemek l�trehoz�sa
		for (int i = 0, counter = 0; i < rows; i++) {
			line = reader.readLine();
			params = line.split(",");
			if (params.length != columns) {
				logger.setLevel(Level.INFO);
				int errorat = i + 1;
				logger.log(Level.INFO, "Nem az els� sorban meghat�rozott m�ret� p�lya ker�lt defini�l�sra! Hiba a(z) "
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
								+ palyaElemek.get(i).getID() + " szomsz�daji helyett ezt tal�ltam: " + params[0]);
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
			// TODO sz�n ellen�rz�se
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

		// a konzolr�l is olvasni tud�hoz nem kell
		// reader.close();

		return new Palya(keslelteto, palyaElemek, vonatok, palyaKep, id);

	}

	private static void init(int mode) throws IOException {

		ArrayList<Palya> palyak = new ArrayList<Palya>();

		if (mode == 1) {
			// 1. (teszt) p�lya Palya l�trehoz�sa
			Palya p1 = palyaBetoltes(new BufferedReader(new FileReader(System.getProperty("user.home") + "\\palyak\\palya_1.txt")), "Palya 1");

			// 1. (teszt) p�lya hozz�ad�sa a p�lyalist�hoz
			palyak.add(p1);
		}

		else if (mode == 25) {
			// 25. p�lya Palya l�trehoz�sa
			Palya p25 = palyaBetoltes(new BufferedReader(new FileReader(System.getProperty("user.home") + "\\palyak\\palya_25.txt")), "Palya 25");

			// 25. p�lya hozz�ad�sa a p�lyalist�hoz
			palyak.add(p25);

		}

		// TODO t�bb p�lya m�d p�ly�inka l�trehoz�sa, bet�lt�se

		// JatekMotor konstruktor
		JM = new JatekMotor(palyak);

	}

	public static void draw() {

		// V�ltoz� az eredeti p�lyak�pnek
		String[][] palyaKepX = null;

		// mez�k, sorok
		int columns, rows;

		// palya kepenek elkerese a jatekmotort�l
		palyaKepX = JM.getAktualisPalya().getPalyaKepX();
		columns = palyaKepX[0].length;
		rows = palyaKepX.length;

		// V�ltoz� a kirajzoland� p�lyak�pnek az eredeti alapj�n
		String[][] palyaKep = new String[rows][columns];

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

		// draw p�lya
		for (int i = 0; i < rows; i++) {
			String temp = "";
			for (int j = 0; j < columns - 1; j++)
				temp += palyaKep[i][j] + "   ";
			temp += palyaKep[i][columns - 1];
			logger.setLevel(Level.INFO);
			logger.log(Level.WARNING, temp + "\n");
		}

		// write v�lt� �ll�sok
		logger.log(Level.WARNING, "V�lt�k �ll�sa:");
		logger.setLevel(Level.OFF);
		for (PalyaElem palyaElem : JM.getAktualisPalya().getElemek())
			if (palyaElem instanceof Valto) {
				logger.setLevel(Level.INFO);
				logger.log(Level.WARNING, palyaElem.getID() + ":" + ((Valto) palyaElem).getAllas()[0].getID() + "<-->"
						+ ((Valto) palyaElem).getAllas()[1].getID());
			}

		// write �llom�s sz�nek, v�rakoz� utasok
		logger.log(Level.WARNING, "\n�llom�sok sz�ne:");
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