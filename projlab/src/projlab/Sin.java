package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sin osztály:
 * 
 * A PalyaElem objektumnak egy leszármazottja. Itt valósítjuk meg az ugynevezett
 * egyszerû síneket, és csak az ilyen egyszerû sínekre lehet alagutat építeni.
 * 
 * Felelõsség:
 * 
 * Felelõssége az alagút építés helyes mûködése. Mivel a PalyaElem gyereke,
 * ezért felel még a rajtalevõ Jarmu következõ pozíciójának megadásáért is.
 * Lehet rá alagutat építeni (csak rá lehet).
 */
public class Sin extends PalyaElem {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/** Konstruktor */
	Sin(String id, int[] p) {
		super(id, p);
	}

	/**
	 * Visszatér egy PalyaElem példánnyal, mely a paraméterül kapott Mozdony
	 * pozicio-ja lesz. Megnézi, hogy ez a PalyaElem, amin a Mozdony van,
	 * alagút-e, tehát az alagut értéke igaz-e. Ha igen, és az elõzõ pozíció nem
	 * volt alagút, lekéri a palya-tól az alagutak számát a getAlagutSzam
	 * metódussal. Ha ez 2, felhívja a palya alagut metódusát, mely visszatér a
	 * másik alagúttal, ami a pályán van. Ennek visszatérünk az elsõ
	 * szomszédjával. Ha nincs 2 alagút építve, nem számít, hogy van-e itt
	 * alagút vagy sincs, ugyanúgy kezeljük mind kettõ esetet. Lekérjük a kapott
	 * Mozdony elõzõ pozícióját a getElozoPozicio metódussal, majd ezt
	 * komparáljuk a szomszedok tömb elsõ 2 elemével. Amelyikkel nem egyezik
	 * meg, azzal térünk vissza, majd felhívjuk a visszatért PalyaElem
	 * setFoglalt metódusát, mely növeli a foglalt értékét 1-el. Ezután
	 * csökkentjük ennek a foglalt változójának értékét 1-el.
	 */
	public PalyaElem elfogad(Mozdony m) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + m.getID() + ")");

		PalyaElem elozoPozicio = m.getElozoPozicio();

		if (alagut == true && !elozoPozicio.getAlagut())
			if (palya.getAlagutSzam() == 2) {
				foglalt--;
				PalyaElem pe = palya.alagut(this);
				pe.setFoglalt();
				return pe;
			}

		if (elozoPozicio == szomszedok[0]) {
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
	 * Visszatér egy PalyaElem példánnyal, mely a paraméterül kapott Kocsi
	 * pozicio-ja lesz. Megnézi, hogy ez a PalyaElem, amin a Kocsi van,
	 * alagút-e, tehát az alagut értéke igaz-e. Ha igen, és az elõzõ pozíció nem
	 * volt alagút, lekéri a palya-tól az alagutak számát a getAlagutSzam
	 * metódussal. Ha ez 2, felhívja a palya alagut metódusát, mely visszatér a
	 * másik alagúttal, ami a pályán van. Ennek visszatérünk az elsõ
	 * szomszédjával. Ha nincs 2 alagút építve, nem számít, hogy van-e itt
	 * alagút vagy sincs, ugyanúgy kezeljük mind kettõ esetet. Lekérjük a kapott
	 * Kocsi elõzõ pozícióját a getElozoPozicio metódussal, majd ezt komparáljuk
	 * a szomszedok tömb elsõ 2 elemével. Amelyikkel nem egyezik meg, azzal
	 * térünk vissza, majd felhívjuk a visszatért PalyaElem setFoglalt
	 * metódusát, mely növeli a foglalt értékét 1-el. Ezután csökkentjük ennek a
	 * foglalt változójának értékét 1-el.
	 */
	public PalyaElem elfogad(Kocsi k) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + k.getID() + ")");

		PalyaElem elozoPozicio = k.getElozoPozicio();

		if (alagut == true && !elozoPozicio.getAlagut())
			if (palya.getAlagutSzam() == 2) {
				foglalt--;
				PalyaElem pe = palya.alagut(this);
				pe.setFoglalt();
				return pe;
			}

		if (elozoPozicio == szomszedok[0]) {
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
	 * Visszatér egy PalyaElem példánnyal, mely a paraméterül kapott
	 * Szenecskocsi pozicio-ja lesz. Megnézi, hogy ez a PalyaElem, amin a
	 * Szenecskocsi van, alagút-e, tehát az alagut értéke igaz-e. Ha igen, és az
	 * elõzõ pozíció nem volt alagút, lekéri a palya-tól az alagutak számát a
	 * getAlagutSzam metódussal. Ha ez 2, felhívja a palya alagut metódusát,
	 * mely visszatér a másik alagúttal, ami a pályán van. Ennek visszatérünk az
	 * elsõ szomszédjával. Ha nincs 2 alagút építve, nem számít, hogy van-e itt
	 * alagút vagy sincs, ugyanúgy kezeljük mind kettõ esetet. Lekérjük a kapott
	 * Szenecskocsi elõzõ pozícióját a getElozoPozicio metódussal, majd ezt
	 * komparáljuk a szomszedok tömb elsõ 2 elemével. Amelyikkel nem egyezik
	 * meg, azzal térünk vissza, majd felhívjuk a visszatért PalyaElem
	 * setFoglalt metódusát, mely növeli a foglalt értékét 1-el. Ezután
	 * csökkentjük ennek a foglalt változójának értékét 1-el.
	 */
	public PalyaElem elfogad(Szeneskocsi sz) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + sz.getID() + ")");

		PalyaElem elozoPozicio = sz.getElozoPozicio();

		if (alagut == true && !elozoPozicio.getAlagut())
			if (palya.getAlagutSzam() == 2) {
				foglalt--;
				PalyaElem pe = palya.alagut(this);
				pe.setFoglalt();
				return pe;
			}

		if (elozoPozicio == szomszedok[0]) {
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
	 * Megnézzük, van-e Jarmu ezen a PalyaElem-en, mert ha igen, akkor nem lehet
	 * alagutat építeni. Ha nem, tehát ha a foglalt értéke 0, lekérjük a
	 * palya-tól az alagutak számát a getAlagutSzam metódussal. Ha ez 2,
	 * megnézzük, hogy ezen a PalyaElem-en van e alagút, tehát az alagut változó
	 * értékét. Ha nincs, szóval az alagut hamis, visszatérünk, mert nem lehet 3
	 * alagutat építeni. Ha az alagut igaz, akkor lebontjuk ezt az alagutat,
	 * azaz beállítjuk az alagut értékét hamisra, majd meghívjuk a palya
	 * setAlagutSzam megtódusát -1 et paraméterül adva, ezután visszatérünk. Ha
	 * nem volt 2 alagút építve, akkor megnézzük, hogy van-e egy olyan másik
	 * pályaelem, ami alagút és éppen foglalt, mert ha van akkor nem lehet ide
	 * alagutat építeni. Egyébként invertáljuk az alagut értékét, majd attól
	 * függõen, hogy az alagut az invertálás után hamis vagy igaz lett,
	 * felhívjuk a palya setAlagutSzam metódusát 1 vagy -1 -et paraméterül adva,
	 * majd visszatérünk. Ezzel a megoldással az alagutSzam sose mehet 0 alá, és
	 * 2 felé.
	 */
	public void setAlagut() {
		logger.log(Level.INFO, this.getID() + ".setAlagut()");

		if (foglalt == 0) {
			if (palya.getAlagutSzam() == 2) {
				if (alagut == false) {
					logger.log(Level.INFO, this.getID() + ": Harmadik alagút építése nem engedélyezett!");
					return;
				} else {
					alagut = false;
					palya.setAlagutSzam(-1);
					return;
				}
			}

			if (palya.getAlagutSzam() == 1 && alagut == false) {
				PalyaElem tmp = palya.alagut(this);
				if (tmp.getFoglalt() != 0) {
					logger.log(Level.INFO,
							this.getID() + ": Nem lehet alagutat építeni, mert a másik alagút foglalt:" + tmp.getID());
					return;
				}
			}

			alagut = !alagut;
			if (alagut == false) {
				palya.setAlagutSzam(-1);
				return;
			}

			palya.setAlagutSzam(1);
			return;
		}

		logger.log(Level.INFO, this.getID() + ": A sín foglalt, nem lehet alagutat építeni");
	}

}