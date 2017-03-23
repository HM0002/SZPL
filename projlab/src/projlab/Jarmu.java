package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Jarmu oszt�ly:
 * 
 * A Mozdony �s a Kocsi objektumok �se. Ez egy abstract oszt�ly, ugyanis
 * j�rm�veket nem p�ld�nyos�tunk, csak kocsikat vagy mozdonyokat. T�roljuk
 * ezeknek a poz�ci�jukat �s az el�z� pozic��jukat, melyek Sin t�pus�ak. Amelyik
 * Sin-en Jarmu van (teh�t a Jarmu pozici�ja ez a Sin), oda nem lehet alagutat
 * �p�teni.
 * 
 * Felel�ss�g:
 * 
 * Ez az objektum felel a mozg� mozdonyok �s kocsik csoportos�t�s��rt. Az id�
 * m�l�s�nak f�ggv�ny�ben (tick) megl�togatja a poz�ci�j�ban t�rolt Sin
 * p�ld�nyt, melynek �tadja �nmag�t. Ez visszat�r az �j poz�ci�val, �gy mozog a
 * Jarmu-v�nk.
 */
public abstract class Jarmu {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	// Attrib�tumok:

	/**
	 * pozicio: Itt t�roljuk, hogy a j�rm� melyik Sin p�ld�nyon helyezkedik el.
	 */
	private Sin pozicio;

	/**
	 * elozoPozicio: Itt t�roljuk, az egy �temmel (tick) kor�bbi Sin p�ld�nyt,
	 * amin elhelyezkedett.
	 */
	private Sin elozoPozicio;

	// debuggol�shoz, hogy tudjuk kicsoda
	private String id;

	/** Konstruktor */
	Jarmu(String id) {
		logger.log(Level.INFO, "Jarmu param�ter n�lk�li konstruktor elindult");
		pozicio = null;
		elozoPozicio = null;
		this.id = id;
	}

	/** Megh�vja a latogat met�dust, ez�ltal jelezve, hogy mozogni kell. */
	public void tick() {
		logger.log(Level.INFO, "Jarmu.tick()");
		latogat();
	}

	/**
	 * A Sin t�pus� pozicio attrib�tum elfogad met�dus�t h�vja meg, saj�t mag�t
	 * param�ter�l �tadva, hogy az elfogad met�dus visszat�rhessen �j poz�ci�val
	 * (Sin p�ld�nnyal).
	 */
	protected void latogat() {
		logger.log(Level.INFO, "Jarmu.latogat() ezen p�ld�ny:" + this.getID());
		Sin temp = pozicio;
		logger.log(Level.INFO, "Jarmu.latogat() hivas adatai:" + pozicio.getID());
		pozicio = pozicio.elfogad(this);
		elozoPozicio = temp;
	}

	/**
	 * Visszat�r a pozicio-val ami egy Sin objektum, ezzel jelezve, hogy a Jarmu
	 * itt tartozkodik.
	 */
	public Sin getPozicio() {
		logger.log(Level.INFO, "Jarmu.getPozicio(), ezen az elemen:" + this.getID());
		return pozicio;
	}

	/**
	 * Visszat�r az elozopozicio-val ami egy Sin objektum, ezzel jelezve, hogy a
	 * Jarmu itt tartozkodott az elozo idopillanatban.
	 */
	public Sin getElozoPozicio() {
		logger.log(Level.INFO, "Jarmu.getElozoPozicio() ezen az elemen:" + this.getID());
		return elozoPozicio;
	}

	/**
	 * �rt�k�l adja a kapott poz�ci�t (sP) a pozicio-nak, �s a kapott el�z�
	 * poz�ci�t (sEP) az elozoPozicio-nak. Ezut�n be�ll�tja az Sp Sin-t
	 * foglaltra, a setFoglalt met�dussal.
	 */
	public void setKezdoPoziciok(Sin sP, Sin sEP) {
		logger.log(Level.INFO, "Jarmu.setKezdoPoziciok(), be�ll�tand� poz�ci�: " + sP.getID()
				+ " beall�tand� kezd�poz�ci�: " + sEP.getID() + " ezen a Jarmu-n h�vva:" + this.getID());
		elozoPozicio = sEP;
		pozicio = sP;
		sP.setFoglalt();
	}

	/** Abstract, a Mozdony �s Kocsi-ban ezt implement�ljuk. */
	public abstract int getSzin();

	// debug method
	public String getID() {
		return id;
	}
}