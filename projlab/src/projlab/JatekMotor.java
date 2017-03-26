package projlab;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JatekMotor osztály:
 * 
 * Ez a legfelsõ objektum, ez kezeli a játék futását, helyes menetét.
 * 
 * Felelõsség:
 * 
 * Felel, hogy a pályára új vonatok érkezzenek, nézi, ha veszít vagy nyer a
 * játékos, új pályát tölt be, ha szükséges. Kezeli az idõ múlását. Továbbá
 * felel az ütközések ellenõrzéséért, ezáltal tudja, hogy ha veszít a játékos.
 */
public class JatekMotor {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	// Attribútumok:

	/**
	 * palyak: Itt tároljuk az összes Palya példányunkat. A 0. elemben tároljuk
	 * az aktuális pályát, amin éppen játszunk. Pálya váltásnál shifteljük.
	 */
	private ArrayList<Palya> palyak;

	/**
	 * prevTime: Itt tároljuk, mikor mozgattuk utoljára a Jarmu objektumainkat.
	 */
	private long prevTime;

	/**
	 * ujVonat: Itt tároljuk, hogy utoljára hány idõpillanat(tick) óta indult új
	 * Vonat-ot.
	 */
	private int ujVonat;

	/**
	 * vonatSzamlalo: Ebben tároljuk, hogy hány vonatot indítottunk el az éppen
	 * futásban lévõ pályán.
	 */
	private int vonatSzamlalo;

	/**
	 * Konstruktor, az ujVonat kezdõértéke egy nagy szám, mely biztosan nagyobb,
	 * mint a pályák keslelteto értéke, ezáltal biztosan elindul az elsõ vonat.
	 */
	JatekMotor(ArrayList<Palya> p) {
		logger.log(Level.INFO, "JM konstruktora elindult");
		palyak = p;
		prevTime = 0;
		ujVonat = 500;
		vonatSzamlalo = 0;
	}

	/**
	 * Vizsgáljuk az eltelt idõt. Ha eltelt egy adott idõ (1 másodperc),
	 * felhivjuk az idoEltelt metódus, majd visszatérünk igazzal, tehát hogy
	 * eltelt a másodperc. Ha nem telt el, hamissal térünk vissza.
	 */
	public boolean idoMeres() {
		// logger.log(Level.INFO, "JM.idoMeres()");
		if (Math.abs(System.currentTimeMillis() - prevTime) > 1000) {
			idoEltelt();
			return true;
		}
		return false;
	}

	/**
	 * Lekéri az aktuális pálya (palyak 0. eleme) vonatait a getVonatok
	 * metódussal, majd ezeknek a vonatoknak a jármûveit a getJarmuvek
	 * metódussal. Ha a jarmû már a pályán van, tehát ha a getPozicio metódus
	 * nem null-al tér vissza, akkor a jármûnek ezután meghívjuk a tick
	 * metódusát, mely felel a mozgatásáért. Ezt megcsináljuk az összes jármûre,
	 * értékül adjuk a jelenlegi idõt a prevTime-nak.
	 */
	public void idoEltelt() {
		for (Vonat vonat : palyak.get(0).getVonatok()) {
			logger.log(Level.INFO, "\nElindul a ciklus a " + vonat.getID() + "-re:\n");
			for (Jarmu jarmu : vonat.getJarmuvek()) {
				if (jarmu.getPozicio() != null) {
					logger.log(Level.INFO, "\t" + jarmu.getID() + " léptetése:");
					jarmu.tick();
				}
			}
			logger.log(Level.INFO, "\nCiklus vége.");
		}
		prevTime = System.currentTimeMillis();
	}

	/**
	 * Elindítja az idõt(beállítja a prevTime értékét), majd meghívja a run
	 * metódust.
	 */
	public void ujJatek() {
		logger.log(Level.INFO, "JM.ujJatek()");
		prevTime = System.currentTimeMillis();
		run();
	}

	/** Kilép az alkalmazásból. */
	public void kilepes() {
		logger.log(Level.INFO, "JM.kilepes()");
		// exit
	}

	/**
	 * Lekéri az aktuális pálya (palyak 0. eleme) getVonatSzam metódusával, hogy
	 * hány vonatot kell küldeni összesen, és ezt komparálja a
	 * vonatSzamlalo-val. Ha a vonatSzamlalo kisebb, lekéri a pályától, hogy
	 * mennyi idõközönként(tick) indíthat új vonatot, a getKeslelteto
	 * metódussal. Ezt komparálja az ujVonat-tal, melyben az elõzõ vonatindítás
	 * óta eltelt tick-ek száma van tárolva. Ha az ujVonat értéke kisebb,
	 * növeljük 1-el. Ha nagyobb vagy egyenlõ, lekérjük a pályától a
	 * vonatSzamlalo-adik vonat jármûveit, ugyanis egy rendezett listában vannak
	 * a vonatok, és a vonatSzamlalo-adik vonat az, ami még nem indult el.
	 * Ezután az így lekért jármûvek setKezdoPoziciok metódusával beállítjuk a
	 * helyes kezdõ pozíciókat, majd növeljök a vonatSzamlalo-t, és nullázzuk az
	 * ujVonat-ot.
	 */
	public void vonatInditas() {
		logger.log(Level.INFO, "JM.vonatInditas()");
		if (vonatSzamlalo < palyak.get(0).getVonatSzam()) {
			if (ujVonat >= palyak.get(0).getKeslelteto()) {
				int k = palyak.get(0).getKocsiSzam();
				ArrayList<Jarmu> j = palyak.get(0).getVonatok().get(vonatSzamlalo).getJarmuvek();
				ArrayList<PalyaElem> e = palyak.get(0).getElemek();
				logger.log(Level.INFO, "\nElindul a ciklus:\n");
				for (int i = 0; i < k + 1; i++) {
					logger.log(Level.INFO, "\t" + j.get(i).getID() + " elindítása:");
					j.get(i).setKezdoPoziciok(e.get(5 - i), e.get(4 - i));
				}
				logger.log(Level.INFO, "\nCiklus vége.\n");
				ujVonat = 0;
				vonatSzamlalo++;
			} else
				ujVonat++;
		}
	}

	/**
	 * Megnézi, hogy minden PalyaElem példányon csak egy jármû van-e, ezáltal
	 * eldönti, hogy van-e ütközés vagy sem. Lekéri az aktuális pálya (palyak 0.
	 * eleme) PalyaElem elemeit, a getElemek metódussal, ezután lekéri, hogy
	 * hány jármû van ezeken az elemeken, a getFoglalt metódussal. Ha ez több
	 * mint 1, utközés történt, és visszatérünk igazzal, egyébként nem és
	 * visszatérünk hamissal.
	 */
	public boolean utkozesEllenorzes() {
		logger.log(Level.INFO, "JM.utkozesEllenorzes()");
		for (PalyaElem e : palyak.get(0).getElemek())
			if (e.getFoglalt() > 1)
				return true;

		return false;
	}

	/**
	 * Lekéri az aktuális pálya (palyak 0. eleme) getVonatSzam metódusával, hogy
	 * hány vonatot kell küldeni összesen, és ezt komparálja a
	 * vonatSzamlalo-val. Ha a két érték különbözõ, visszatérünk hamissal,
	 * ugyanis az azt jelenti, hogy még nem indult el az összes vonatunk, ami a
	 * pályához tartozik. Ha a két értek megegyezik, lekérjük a pályától a
	 * vonatokat a getVonatok metódussal, majd a vonatknak a jármûveit a
	 * getJarmuvek metódussal. Ezután lekérjük az összes jármûvünknek a színét,
	 * a Jarmu getSzin metódusával. Amint az egyiknek nem 0 (üres szín),
	 * visszatérünk hamissal. Ha lekértük az összes jármû színét, és mindegyik
	 * 0, tehát üres, akkor ez a pálya sikeresen teljesítve van, és visszatérünk
	 * igazzal.
	 */
	public boolean gyozelemEllenorzes() {
		logger.log(Level.INFO, "JM.gyozelemEllenorzes()");
		if (palyak.get(0).getVonatSzam() == vonatSzamlalo) {
			for (Vonat vonat : palyak.get(0).getVonatok()) {
				for (Jarmu jarmu : vonat.getJarmuvek())
					if (jarmu.getSzin() != 0)
						return false;
			}
			logger.log(Level.INFO, "Nyertünk a pályán, következõ betöltése!");
			return true;
		}
		return false;
	}

	/**
	 * Egy segéd változónk van, az utkozes. Értéke hamis, és egy loop-ban
	 * vagyunk, amíg ez így is marad. A loop-ban meghívjuk az idoMeres metódust,
	 * és ha eltelt 1 másodperc, az visszatér igazzal, egyébként nem csinálunk
	 * semmit. Ha eltelt az 1 másodperc, meghívjuk az utkozesEllenorzes
	 * metódust, melynek a visszatérését értékül adjuk az utkozes-nek. Ez után
	 * meghíjuk a gyozelemEllenorzes metódust, ami ha igazzal tér vissza,
	 * megnézzük, hogy van-e még pálya hátra a palyak listában. Ha nincs,
	 * végigvittük a játékot, errõl értesítjük a felhasználót és visszatérünk a
	 * menübe. Ha van még pálya hátra, töröljük a palyak 0. elemét, és a többi
	 * elemét balra shifteljuk az ArrayList remove metódusával. E mellett
	 * újrakezdjük az idõ mérését, és beállítjuk a vonatSzamlalo-t nullára,
	 * illetve az ujVonat-ot egy nagy számra(500, egy akkora szám kell, ami
	 * nagyobb, mint a pályáknak a keslelteto értékei, hogy elinduljon az elsõ
	 * vonat). A gyozelemEllenorzes után felhívjuk a vonatInditas metódust.
	 */
	private void run() {
		logger.log(Level.INFO, "JM.run()");

		boolean utkozes = false;

		while (!utkozes) {
			if (idoMeres()) {
				utkozes = utkozesEllenorzes();
				// GUI input kezelések
				if (gyozelemEllenorzes()) {
					if (palyak.size() < 2) {
						logger.log(Level.INFO, "Megnyertük a játékot!");
						// gyozelem popup
						return;
					} else {
						// kiszedi a 0. elemet, és a többi elemet shifteli balra
						palyak.remove(0);
						prevTime = System.currentTimeMillis();
						ujVonat = 500;
						vonatSzamlalo = 0;
					}
				}
				vonatInditas();
			}
		}
		// utkozes popup
		logger.log(Level.INFO, "JM.run() metódusban ütközés történt!");
	}

}