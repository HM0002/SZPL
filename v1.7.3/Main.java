package projlab;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

	// Elsõ pályához PalyaElem-eknek ArrayList létrehozása
	static ArrayList<PalyaElem> palyaElemek0;

	// Jarmuveknek ArrayList
	static ArrayList<Jarmu> vonat0;
	static ArrayList<Jarmu> vonat1;

	// 1. Pálya létrehozása(p0)
	static Palya p0;
	static Palya p1;

	// Vonatoknak ArrayList
	static ArrayList<Vonat> vonatok0;

	// Palyaknak ArrayList
	static ArrayList<Palya> palyak;

	// Vonat objektumok létrehozása
	static Vonat v0;
	static Vonat v1;

	// Elsõ Pályához Sin-ek, Valto-k, Allomas-ok létrehozása
	static Sin e0;
	static Sin e1;
	static Sin e2;
	static Sin e3;
	static Keresztezodes e4;
	static Sin e5;
	static Sin e6;
	static Valto e7;
	static Sin e8;
	static Sin e9;
	static Sin e10;
	static Sin e11;
	static Allomas e12;
	static Sin e13;
	static Valto e14;
	static Allomas e15;
	static Sin e16;
	static Sin e17;
	static Sin e18;
	static Sin e19;
	static Sin e20;
	static Sin e21;
	static Sin e22;
	static Sin e23;

	// Jarmu-vek létrehozása, elõször a vonat1 elemei
	static Mozdony m1;
	static Szeneskocsi sz1;
	static Szeneskocsi sz2;
	static Kocsi k1;
	static Kocsi k2;

	// majd a vonat2 elemei
	static Mozdony m2;
	static Szeneskocsi sz3;
	static Kocsi k3;
	static Kocsi k4;

	static String palyaKep[][];
	static String palyaKepX[][];

	
	public static void main(String[] args) throws SecurityException, IOException {

		for (Handler handler : logger.getParent().getHandlers()) {
			logger.getParent().removeHandler(handler);
		}

		CustomRecordFormatter formatter = new CustomRecordFormatter();

		FileHandler fileHandler = new FileHandler("kimenet.txt");
		fileHandler.setFormatter(formatter);
		logger.addHandler(fileHandler);

		BufferedReader br = null;

		if (args[0].equals("K"))
			br = new BufferedReader(new InputStreamReader(System.in));
		else if (args[0].equals("F"))
			br = new BufferedReader(new FileReader("teszt_bemenetek\\" + args[1]));
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

		/*
		try {
			init2();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}

	private static void init2() throws Exception{
		logger.log(Level.INFO, "Játek elemeinek inicializálása: \n");

		ArrayList<Palya> palyak = new ArrayList<Palya>();
		int palyakSzama = 1;
		for(int palyaSorszam = 1; palyaSorszam <= palyakSzama; palyaSorszam++){
			
			BufferedReader br = new BufferedReader(new FileReader("palyak\\p" + palyaSorszam + ".txt"));
			
			// Pálya méretek
			String line = br.readLine();
			String[] lines = line.split(" ");
			
			// Szélesség, magasság, elemek száma
			int x = Integer.parseInt(lines[0]);
			int y = Integer.parseInt(lines[1]);
			int n = Integer.parseInt(lines[2]);

			//logger.setLevel(Level.OFF);
			
			palyaElemek0 = new ArrayList<PalyaElem>(n);
			for(int i = 0; i<n; i++)
				palyaElemek0.add(new Sin("S_init_" + i));

			
			//logger.setLevel(Level.OFF);
			//logger.setLevel(Level.INFO);
			//logger.log(Level.INFO, "########## Pályaelemek létrehozása ##########");
			
			palyaKep = new String[y][x];
			palyaKepX = new String[y][x];
			
			// Pályaelemek létrehozása + pályakép
			for(int i = 0; i < y; i++){
				line = br.readLine();
				lines = line.split(",");
				for(int j =0; j < x; j++){
					palyaKep[i][j] = " " + lines[j] + " ";
					palyaKepX[i][j] = " " + lines[j] + " ";
					int k;
					switch(lines[j].charAt(0)){
					case ' ':
						break;
					// Sín elem
					case 'S':
						k = Integer.parseInt(lines[j].substring(1));
						palyaElemek0.remove(k);
						palyaElemek0.add(k, new Sin(" " + lines[j] + " "));
						break;
					case 'V':
						k = Integer.parseInt(lines[j].substring(1));
						palyaElemek0.remove(k);
						palyaElemek0.add(k, new Valto(" " + lines[j] + " "));			
						break;
					case 'A':
						// A pálya nyelvébõl kifolyólag csak késõbb kerül létrehozásra
						break;
					case 'K':
						k = Integer.parseInt(lines[j].substring(1));
						palyaElemek0.remove(k);
						palyaElemek0.add(k, new Keresztezodes(" " + lines[j] + " "));			
						break;
					}
				}
			}
			
			/*
			logger.setLevel(Level.OFF);
			logger.setLevel(Level.INFO);
			logger.log(Level.INFO, "########## Vonatok létrehozása ##########");*/
			
			// Üres sor következik
			line = br.readLine();
			
			ArrayList<Vonat> vonatok = new ArrayList<Vonat>(); 
			
			// Vonatok
			int vID = 0;
			while(!(line = br.readLine()).isEmpty()){
				lines = line.split("\\s+");
				ArrayList<Jarmu> vonat = new ArrayList<Jarmu>();
				// Mozdony
				vonat.add(new Mozdony(lines[0]));
				// Kocsi
				for(int i=1; i<(lines.length); i++){
					switch(lines[i].charAt(0)){
					case 'C':
						vonat.add(new Szeneskocsi(lines[i]));
						break;
					case 'K':
						vonat.add(new Kocsi(Character.getNumericValue(lines[i].charAt(1)), lines[i]));
						break;
					}
				}
				vonatok.add(new Vonat(vonat, "VONAT" + vID));
				vID++;
			}
			
			/*
			logger.setLevel(Level.OFF);
			logger.setLevel(Level.INFO);
			logger.log(Level.INFO, "########## Állomások létrehozása ##########");*/
			
			// Állomások létreehozása
			while(!(line = br.readLine()).isEmpty()){
				lines = line.split("\\s+");
				int i = Integer.parseInt(lines[0]);
				int szin = Integer.parseInt(lines[1]);
				boolean varakozok;
				if(lines[2].equals("TRUE"))
					varakozok = true;
				else
					varakozok = false;
				palyaElemek0.remove(i);
				palyaElemek0.add(i, new Allomas(szin, varakozok," " + "A" + lines[0] + " "));
			}
			
			/*
			logger.setLevel(Level.OFF);
			logger.setLevel(Level.INFO);
			logger.log(Level.INFO, "########## Szomszédok beállítása ##########");*/
			
			// Szomszédok beállítása
			while(!(line = br.readLine()).isEmpty()){
				lines = line.split("\\s+");
				int i = Integer.parseInt(lines[0]);
				switch(lines.length){
				case 3:
					palyaElemek0.get(i).setSzomszedok(
							palyaElemek0.get(Integer.parseInt(lines[1])), 
							palyaElemek0.get(Integer.parseInt(lines[2])), 
							null, 
							null);
					break;
				case 4:
					palyaElemek0.get(i).setSzomszedok(
							palyaElemek0.get(Integer.parseInt(lines[1])), 
							palyaElemek0.get(Integer.parseInt(lines[2])), 
							palyaElemek0.get(Integer.parseInt(lines[3])), 
							null);
					break;
				case 5:
					palyaElemek0.get(i).setSzomszedok(
							palyaElemek0.get(Integer.parseInt(lines[1])), 
							palyaElemek0.get(Integer.parseInt(lines[2])), 
							palyaElemek0.get(Integer.parseInt(lines[3])), 
							palyaElemek0.get(Integer.parseInt(lines[4])));
					break;
				}
			}

			/*
			logger.setLevel(Level.OFF);
			logger.setLevel(Level.INFO);
			logger.log(Level.INFO, "########## Pálya létrehozása ##########");*/
			
			line = br.readLine();
			int keslelteto = Integer.parseInt(line);
			
			Palya p = new Palya(keslelteto, palyaElemek0, vonatok, "PALYA" + palyaSorszam);
					
		    br.close();
		    palyak.add(p);
		}
		JM = new JatekMotor(palyak);
	}
	
	
	private static void init() {
		logger.log(Level.INFO, "Játek elemeinek inicializálása: \n");

		// Sin konstuktorok
		e0 = new Sin(" S000 ");
		e1 = new Sin(" S001 ");
		e2 = new Sin(" S002 ");
		e3 = new Sin(" S003 ");
		e4 = new Keresztezodes(" K004 ");
		e5 = new Sin(" S005 ");
		e6 = new Sin(" S006 ");
		e7 = new Valto(" V007 ");
		e8 = new Sin(" S008 ");
		e9 = new Sin(" S009 ");
		e10 = new Sin(" S010 ");
		e11 = new Sin(" S011 ");
		e12 = new Allomas(1, true, " A012 ");
		e13 = new Sin(" S013 ");
		e14 = new Valto(" V014 ");
		e15 = new Allomas(2, false, " A015 ");
		e16 = new Sin(" S016 ");
		e17 = new Sin(" S017 ");
		e18 = new Sin(" S018 ");
		e19 = new Sin(" S019 ");
		e20 = new Sin(" S020 ");
		e21 = new Sin(" S021 ");
		e22 = new Sin(" S022 ");
		e23 = new Sin(" S023 ");

		palyaElemek0 = new ArrayList<PalyaElem>();

		// Sin-ek összekötése
		e0.setSzomszedok(e1, e1, null, null);
		e1.setSzomszedok(e12, e2, null, null);
		e2.setSzomszedok(e1, e3, null, null);
		e3.setSzomszedok(e2, e4, null, null);
		e4.setSzomszedok(e3, e13, e5, e23);
		e5.setSzomszedok(e4, e6, null, null);
		e6.setSzomszedok(e5, e7, null, null);
		e7.setSzomszedok(e8, e18, e6, null);
		e8.setSzomszedok(e7, e9, null, null);
		e9.setSzomszedok(e8, e10, null, null);
		e10.setSzomszedok(e9, e11, null, null);
		e11.setSzomszedok(e10, e12, null, null);
		e12.setSzomszedok(e11, e1, null, null);
		e13.setSzomszedok(e4, e14, null, null);
		e14.setSzomszedok(e13, e15, e19, null);
		e15.setSzomszedok(e14, e16, null, null);
		e16.setSzomszedok(e15, e17, null, null);
		e17.setSzomszedok(e16, e18, null, null);
		e18.setSzomszedok(e17, e7, null, null);
		e19.setSzomszedok(e14, e20, null, null);
		e20.setSzomszedok(e19, e21, null, null);
		e21.setSzomszedok(e20, e22, null, null);
		e22.setSzomszedok(e21, e23, null, null);
		e23.setSzomszedok(e22, e4, null, null);

		// palyaElemek1 listához a Sin példányok hozzáadása
		palyaElemek0.add(e0);
		palyaElemek0.add(e1);
		palyaElemek0.add(e2);
		palyaElemek0.add(e3);
		palyaElemek0.add(e4);
		palyaElemek0.add(e5);
		palyaElemek0.add(e6);
		palyaElemek0.add(e7);
		palyaElemek0.add(e8);
		palyaElemek0.add(e9);
		palyaElemek0.add(e10);
		palyaElemek0.add(e11);
		palyaElemek0.add(e12);
		palyaElemek0.add(e13);
		palyaElemek0.add(e14);
		palyaElemek0.add(e15);
		palyaElemek0.add(e16);
		palyaElemek0.add(e17);
		palyaElemek0.add(e18);
		palyaElemek0.add(e19);
		palyaElemek0.add(e20);
		palyaElemek0.add(e21);
		palyaElemek0.add(e22);
		palyaElemek0.add(e23);

		// Jarmu (Mozdony, Kocsi és Szeneskocsi) konstruktorok
		m1 = new Mozdony("M000");
		sz1 = new Szeneskocsi("C001");
		sz2 = new Szeneskocsi("C002");
		k1 = new Kocsi(2, "K203");
		k2 = new Kocsi(1, "K104");

		m2 = new Mozdony("M010");
		k3 = new Kocsi(1, "K111");
		sz3 = new Szeneskocsi("C012");
		k4 = new Kocsi(2, "K213");

		vonat0 = new ArrayList<Jarmu>();
		vonat1 = new ArrayList<Jarmu>();

		// vonatokba a Jarmu-vek elhelyezése: vonat1
		vonat0.add(m1);
		vonat0.add(sz1);
		vonat0.add(sz2);
		vonat0.add(k1);
		vonat0.add(k2);

		// vonatokba a Jarmu-vek elhelyezése: vonat2
		vonat1.add(m2);
		vonat1.add(sz3);
		vonat1.add(k3);
		vonat1.add(k4);

		// Vonat konstruktorok
		v0 = new Vonat(vonat0, "VONAT0");
		v1 = new Vonat(vonat1, "VONAT1");

		vonatok0 = new ArrayList<Vonat>();

		// vonatok hozzáadása a vonatlistához
		vonatok0.add(v0);
		vonatok0.add(v1);

		// Palya konstruktor
		p1 = new Palya(12, palyaElemek0, vonatok0, "palya2");
		p0 = new Palya(12, palyaElemek0, vonatok0, "palya1");

		palyak = new ArrayList<Palya>();

		// Elsõ hozzáadása a pályalistához
		palyak.add(p0);
		palyak.add(p1);

		// JatekMotor konstruktor
		JM = new JatekMotor(palyak);

	}

	public static void draw() {

		/* @formatter:off */
		/*String palyaKep[][] = new String[][] { 
				{ "      ", " S010 ", " S009 ", " S008 ", " V007 ", " S018 ", " S017 " },
				{ "      ", " S011 ", "      ", "      ", " S006 ", "      ", " S016 " },
				{ "      ", " A012 ", "      ", "      ", " S005 ", "      ", " A015 " },
				{ " S000 ", " S001 ", " S002 ", " S003 ", " K004 ", " S013 ", " V014 " },
				{ "      ", "      ", "      ", "      ", " S023 ", "      ", " S019 " },
				{ "      ", "      ", "      ", "      ", " S022 ", " S021 ", " S020 " } };

		String palyaKepX[][] = new String[][] { 
				{ "      ", " S010 ", " S009 ", " S008 ", " V007 ", " S018 ", " S017 " },
				{ "      ", " S011 ", "      ", "      ", " S006 ", "      ", " S016 " },
				{ "      ", " A012 ", "      ", "      ", " S005 ", "      ", " A015 " },
				{ " S000 ", " S001 ", " S002 ", " S003 ", " K004 ", " S013 ", " V014 " },
				{ "      ", "      ", "      ", "      ", " S023 ", "      ", " S019 " },
				{ "      ", "      ", "      ", "      ", " S022 ", " S021 ", " S020 " } };*/
		
		for(int i = 0; i < palyaKepX.length; i++){
			for(int j = 0; j < palyaKepX[0].length; j++){
				palyaKep[i][j] = palyaKepX[i][j];
			}
		}
		
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
									palyaKep[i][j] = "CRASH!";
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
							palyaKep[i][j] = " |AL| ";
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

	public static void commandMapping(String s[]) {
		Level tmp = logger.getLevel();

		switch (s[0]) {

		case "loadPalya":
			try {
				init2();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			JM.vonatInditas();
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
			
		case "test":
			System.out.println(JM.getAktualisPalya().getVonatok().get(0).getJarmuvek().get(0).getPozicio().getID());
			break;
		}
	}
}
