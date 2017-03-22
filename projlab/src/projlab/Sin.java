package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sin osztály:
 * 
 * A Valto és az Allomas õse. Itt valósítjuk meg a síneket és speciálís
 * síneket(váltó, állomás), melyeken a vonatok majd mozognak. A nem speciális
 * sínek lehetnek alagutak, ezt egy boolean változóban tároljuk. Tároljuk még a
 * szomszédos Sin példányokat, egy három elemû tömbben. A harmadik elem null, ha
 * csak 2 szomszéd van. E mellett tároljuk, hogy melyik pályához tartozik ez a
 * Sin, és azt is, hogy jelenleg van e rajta jármû.
 * 
 * Felelõsség:
 * 
 * Felelõssége az alagút építés helyes mûködése, és a vonatok mozgásához a
 * helyes következõ pozíció visszaadása(algút esetén is) a szomszédok tömb
 * segítségével.
 */
public class Sin {
	private final static Logger logger = Logger.getLogger(Main.class.getName());

	// Attribútumok:

	/**
	 * szomszedok: A konstruktorban 3 elemûként van létrehozva. Tartalmazza a
	 * szomszédos Sin példányokat, az elsõ két elemben vannak az érvényes
	 * szomszédok, tehát azok vannak összekötve, a harmadik elem üres (null). A
	 * Valto leszármazott rak a harmadik elembe is egy példányt, és ahogyan
	 * váltogat a felhasználó, úgy cserélõdik ebben a tömbben a sorrend.
	 */
	protected Sin[] szomszedok;

	// debuggoláshoz, hogy tudjuk kicsoda
	private String id;

	/** alagut: Igaz, ha alagút van építve ezen a példányon, egyébként hamis. */
	protected boolean alagut;

	/** foglalt: Tároljuk, hogy hány Jarmu pozicio-ja ez a Sin példány. */
	protected int foglalt;

	/** palya: Tárolja, melyik Palya példányhoz tartozik ez a Sin példány. */
	protected Palya palya;

	/** Konstruktor */
	Sin(String id) {
		logger.log(Level.INFO, "Sin paraméter nélküli konstruktor elindult");
		szomszedok = new Sin[3];
		alagut = false;
		palya = null;
		foglalt = 0;
		this.id = id;
	}

	/** Értékül adjda p-t a palya-nak. */
	public void setPalya(Palya p) {
		palya = p;
	}

	/**
	 * Értékül adja s0-t a szomszedok 0. elemének, s1-et a szomszedok 1.
	 * elemének, és null-t a szomszedok 3. elemének.
	 */
	public void setSzomszedok(Sin s0, Sin s1) {
		szomszedok[0] = s0;
		szomszedok[1] = s1;
		szomszedok[2] = null;
		logger.log(Level.INFO,
				"Sin.setSzomszedok(), rajta:" + this.getID() + " paraméterek: " + szomszedok[0] + ", " + szomszedok[1]);
	}

	/**
	 * Visszatér egy Sin példánnyal, mely a paraméterül kapott Jarmu pozicio-ja
	 * lesz. Megnézi, hogy ez a Sin, amin a Jarmu van, alagút-e, tehát az alagut
	 * értéke igaz-e. Ha igen, lekéri a palya-tól az alagutak számát a
	 * getAlagutSzam metódussal. Ha ez 2, felhívja a palya alagut metódusát,
	 * mely visszatér a másik alagúttal, ami a pályán van. Ennek visszatérünk az
	 * elsõ szomszédjával. Ha nincs 2 alagút építve, nem számít, hogy van-e itt
	 * alagút vagy sincs, ugyanúgy kezeljük mind kettõ esetet. Lekérjük a kapott
	 * Jarmu elõzõ pozícióját a getElozoPozicio metódussal, majd ezt komparáljuk
	 * a szomszedok tömb elsõ 2 elemével. Amelyikkel nem egyezik meg, azzal
	 * térünk vissza, majd felhívjuk a visszatért Sin setFoglalt metódusát, mely
	 * növeli a foglalt értékét 1-el. Ezután csökkentjük ennek a foglalt
	 * változójának értékét 1-el.
	 */
	public Sin elfogad(Jarmu j) {
		logger.log(Level.INFO, "Sin.elfogad() metódus, a következõ sín elemen:" + this.getID());
		if (alagut == true)
			if (palya.getAlagutSzam() == 2)
				return palya.alagut(this).getFirstSzomszed();

		if (j.getElozoPozicio() == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else {
			foglalt--;
			szomszedok[0].setFoglalt();
			return szomszedok[0];
		}
	}

	/**
	 * Megnézzük, van-e Jarmu ezen a Sin-en, mert ha igen, akkor nem lehet
	 * alagutat építeni. Ha nem, tehát ha a foglalt értéke 0, lekérjük a
	 * palya-tól az alagutak számát a getAlagutSzam metódussal. Ha ez 2,
	 * megnézzük, hogy ezen a Sin-en van e alagút, tehát az alagut változó
	 * értékét. Ha nincs, szóval az alagut hamis, visszatérünk, mert nem lehet 3
	 * alagutat építeni. Ha az alagut igaz, akkor lebontjuk ezt az alagutat,
	 * azaz beállítjuk az alagut értékét hamisra, majd meghívjuk a palya
	 * setAlagutSzam megtódusát -1 et paraméterül adva, ezután visszatérünk. Ha
	 * nem volt 2 alagút építve, akkor invertáljuk az alagut értékét, majd attól
	 * függõen, hogy az alagut az invertálás után hamis vagy igaz lett,
	 * felhívjuk a palya setAlagutSzam metódusát 1 vagy -1 -et paraméterül adva.
	 * Ezzel a megoldással az alagutSzam sose mehet 0 alá, és 2 felé.
	 */
	public void setAlagut() {
		logger.log(Level.INFO, "Sin.setAlagut() ezen az elemen:" + this.getID());
		if (foglalt == 0) {
			if (palya.getAlagutSzam() == 2) {
				if (alagut == false)
					// throw exception, nem lehet 2 nel tobb alagut
					return;
				else {
					alagut = false;
					palya.setAlagutSzam(-1);
					return;
				}
			}

			alagut = !alagut;
			if (alagut == false) {
				palya.setAlagutSzam(-1);
				return;
			}

			palya.setAlagutSzam(1);
		}
	}

	/** Visszatér az alagut értékével. */
	public boolean getAlagut() {
		logger.log(Level.INFO, "Sin.getAlagut() rajta:"+this.getID()+" érték: " + alagut);
		return alagut;
	}

	/**
	 * A foglalt változó értékét adja vissza, jelezve, hogy a Sin-en hány Jarmu
	 * tartózkodik.
	 */
	public int getFoglalt() {
		logger.log(Level.INFO, "Sin.getFoglalt() rajta:"+this.getID()+" értéke: " + foglalt);
		return foglalt;
	}

	/**
	 * A foglalt változó értékét növeli, jelezve, hogy a Sin plusz egy Jarmu
	 * pozicio-ja lett.
	 */
	public void setFoglalt() {
		logger.log(Level.INFO, "Sin.setFoglalt()");
		foglalt++;
	}

	/**
	 * Csökkenti a foglalt értékét 1-el, majd növeli a szomszédok 0. elemének
	 * foglalt értékét 1-el a setFoglalt metódus segítségével. Ezután visszatér
	 * a szomszedok tömb 0. elemével.
	 */
	public Sin getFirstSzomszed() {
		logger.log(Level.INFO, "Sin.getFirstSzomszed() rajta" + this.getID() + " visszaadott sín: " + szomszedok[0]);
		foglalt--;
		szomszedok[0].setFoglalt();
		return szomszedok[0];
	}

	// debug method
	public String getID() {
		return id;
	}
}