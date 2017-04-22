package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allomas osztály:
 * 
 * A PalyaElem objektumnak egy leszármazottja, a grafikus interface-en a sín
 * mellett lesz egy állomás is kirajzolva, de logikailag a PalyaElem-hez
 * tartozik. Az állomások színesek, (és a kocsik is) így jelöljük, milyen utasok
 * kívánnak oda utazni (az azonos színûek).
 * 
 * Felelõsség:
 * 
 * Az utasok le- és felszállításáért felelõs. Felel még a rajtalevõ Jarmu
 * következõ pozíciójának megadásáért is. Felelõs azért is, hogy utas csak
 * sorrendben lévõ kocsikról szállhasson le, színegyezés esetén. Nem lehet rá
 * alagutat építeni. Nyilvántartja a színét.
 */
public class Allomas extends PalyaElem {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	// Attribútumok:

	/** szin: Meghatározza az állomás színkódját. */
	private int szin;

	/**
	 * aktiv: Azt határozza meg, hogy az utasok leszállhatnak-e a rajta lévõ
	 * Jarmu példányról, vagy sem. Akkor igaz, ha megengedjük a leszállást,
	 * egyébként hamis.
	 */
	private boolean aktiv;

	/**
	 * varakozoUtas: Itt tároljuk, hogy van-e várakozó utas az állomáson.
	 */
	private boolean varakozoUtas;

	/**
	 * Konstruktor, meghívjuk az õs konstruktorát, és kiegészítjük a szin, és az
	 * aktiv értékadásával.
	 */
	Allomas(int i, boolean b, String id, int[] p) {
		super(id, p);
		szin = i;
		aktiv = false;
		varakozoUtas = b;
		logger.log(Level.INFO, "paraméterei: szin = " + i + ", varakozoUtas = " + b);
	}

	/**
	 * Egyszerûsített Konstruktor, meghívjuk az õs konstruktorát, és
	 * alapértelmezett atribútumokkal létrehozzuk az állomást
	 */
	Allomas(String id, int[] p) {
		super(id, p);
		szin = 1;
		aktiv = false;
		varakozoUtas = false;
		logger.log(Level.INFO, "paraméterei: szin = " + szin + ", varakozoUtas = " + varakozoUtas);
	}

	/**
	 * Igazra állítja az aktiv-ot, és megadja a következõ PalyaElem példányt,
	 * ahova a Mozdony kerülni fog az új idõpillanatban, úgy, hogy megnézi a
	 * Mozdony elozoPozicio-ját a getElozoPozicio metódussal, és a szomszedok
	 * tömbbõl visszatér az ettõl különbözõ értékkel az elsõ két elem közül.
	 */
	public PalyaElem elfogad(Mozdony m) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + m.getID() + "), aktiv = true;");

		aktiv = true;

		if (m.getElozoPozicio() == szomszedok[0]) {
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
	 * Összehasonlítja az állomás és a kocsi színkódját, melyet a Kocsi getSzin
	 * metódusával kér le. Ha nem egyezik meg, hamisra állítja az aktiv-ot, ha a
	 * kocsi szin attribútuma nem 0, tehát ha nem üres. Ha megegyezik és az
	 * aktiv igaz, akkor leszállítja az utasokat, tehát kiüríti a kocsit a Kocsi
	 * kiurit metodusával (0-ra rakja a szin-t). Ezután megnézzük, hogy van e
	 * várakozó utas az állomáson, tehát hogy a varakozoUtas értéke igaz-e. Ha
	 * nem, nincs felszállás, viszont ha igen, megnézzük a kocsi színét a
	 * getSzin metódussal. Ha 0, tehát ha üres a kocsi, megnézzük, hogy alapból
	 * milyen színû volt, a getEredetiSzin metódussal. Ha az eredetiSzin
	 * megegyezik az allomásunk szin-ével, akkor felszállítjuk a várakozó
	 * utasokat, tehát meghívjuk a Kocsi setSzin metudusát, és hamisra állítjuk
	 * a várakozó utasokat. E mellett megadja a következõ PalyaElem példányt,
	 * ahova a Kocsi kerülni fog az új idõpillanatban, úgy, hogy megnézi a Kocsi
	 * elozoPozicio-ját a getElozoPozicio metódussal, és a szomszedok tömbbõl
	 * visszatér az ettõl különbözõ értékkel az elsõ két elem közül.
	 */
	public PalyaElem elfogad(Kocsi k) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + k.getID() + ")");

		int kSzin = k.getSzin();

		if (kSzin == szin && aktiv == true) {
			k.kiurit();
		} else if (kSzin != 0)
			aktiv = false;

		if (varakozoUtas == true) {
			logger.log(Level.INFO, "Van várakozó utas");
			if (k.getSzin() == 0 && k.getEredetiSzin() == szin) {
				logger.log(Level.INFO, "Utas felszáll, mert üres a kocsi");
				k.setSzin();
				varakozoUtas = false;
			}
		}

		if (k.getElozoPozicio() == szomszedok[0]) {
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
	 * Megadja a következõ PalyaElem példányt, ahova a Szeneskocsi kerülni fog
	 * az új idõpillanatban, úgy, hogy megnézi a Szeneskocsi elozoPozicio-ját a
	 * getElozoPozicio metódussal, és a szomszedok tömbbõl visszatér az ettõl
	 * különbözõ értékkel az elsõ két elem közül.
	 */
	public PalyaElem elfogad(Szeneskocsi sz) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + sz.getID() + ")");

		if (sz.getElozoPozicio() == szomszedok[0]) {
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
	 * Visszatér a szin változó értékével (állomás színe).
	 */
	public int getSzin() {
		return szin;
	}

	/**
	 * Beállítja a szin változó értékét (állomás színe).
	 */
	public void setSzin(int i) {
		szin = i;
	}

	/**
	 * Visszatér igazzal, ha van várakozó utas, egyébként hamissal.
	 */
	public boolean getVarakozoUtas() {
		return varakozoUtas;
	}

	/**
	 * Értékül adja a paraméterül kapott boolean értéket a varakozoUtas-nak.
	 */
	public void setVarakozoUtas(boolean b) {
		varakozoUtas = b;
	}

}