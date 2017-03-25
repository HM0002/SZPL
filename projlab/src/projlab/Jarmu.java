package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Jarmu oszt�ly:
 * 
 * A Mozdony �s a Kocsi objektumok �se. Ez egy abstract oszt�ly, ugyanis
 * j�rm�veket nem p�ld�nyos�tunk, csak kocsikat vagy mozdonyokat. T�roljuk
 * ezeknek a poz�ci�jukat �s az el�z� pozic��jukat, melyek PalyaElem t�pus�ak.
 * Amelyik PalyaElem-en Jarmu van (teh�t a Jarmu pozici�ja ez a PalyaElem), oda
 * nem lehet alagutat �p�teni.
 * 
 * Felel�ss�g:
 * 
 * Ez az objektum felel a mozg� mozdonyok �s kocsik csoportos�t�s��rt. Az id�
 * m�l�s�nak f�ggv�ny�ben (tick) megl�togatja a poz�ci�j�ban t�rolt PalyaElem
 * p�ld�nyt, melynek �tadja �nmag�t. Ez visszat�r az �j poz�ci�val, �gy mozog a
 * Jarmu-v�nk.
 */
public abstract class Jarmu {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	// Attrib�tumok:

	/**
	 * pozicio: Itt t�roljuk, hogy a j�rm� melyik PalyaElem p�ld�nyon
	 * helyezkedik el.
	 */
	protected PalyaElem pozicio;

	/**
	 * elozoPozicio: Itt t�roljuk, az egy �temmel (tick) kor�bbi PalyaElem
	 * p�ld�nyt, amin elhelyezkedett.
	 */
	protected PalyaElem elozoPozicio;

	/** id: Debuggol�shoz, hogy tudjuk kicsoda. */
	private String id;

	/** Konstruktor */
	Jarmu(String id) {
		pozicio = null;
		elozoPozicio = null;
		this.id = id;
		logger.log(Level.INFO, id + " konstruktora elindult");
	}

	/** Megh�vja a latogat met�dust, ez�ltal jelezve, hogy mozogni kell. */
	public void tick() {
		logger.log(Level.INFO, this.getID() + ".tick()");
		latogat();
	}

	/**
	 * A latogat met�dusunk abstract, melyet k�l�n implement�lunk a Mozdony,
	 * Kocsi, Szeneskocsi oszt�lyokban.
	 */
	protected abstract void latogat();

	/**
	 * Visszat�r a pozicio-val ami egy PalyaElem objektum, ezzel jelezve, hogy a
	 * Jarmu itt tartozkodik.
	 */
	public PalyaElem getPozicio() {
		logger.log(Level.INFO, this.getID() + ".getPozicio()");
		return pozicio;
	}

	/**
	 * Visszat�r az elozopozicio-val ami egy PalyaElem objektum, ezzel jelezve,
	 * hogy a Jarmu itt tartozkodott az elozo idopillanatban.
	 */
	public PalyaElem getElozoPozicio() {
		logger.log(Level.INFO, this.getID() + ".getElozoPozicio()");
		return elozoPozicio;
	}

	/**
	 * �rt�k�l adja a kapott poz�ci�t (sP) a pozicio-nak, �s a kapott el�z�
	 * poz�ci�t (sEP) az elozoPozicio-nak. Ezut�n be�ll�tja az Sp PalyaElem-t
	 * foglaltra, a setFoglalt met�dussal.
	 */
	public void setKezdoPoziciok(PalyaElem sP, PalyaElem sEP) {
		logger.log(Level.INFO, this.getID() + ".setKezdoPoziciok(" + sP.getID() + ", " + sEP.getID() + ") felh�vva");
		elozoPozicio = sEP;
		pozicio = sP;
		sP.setFoglalt();
	}

	/**
	 * Visszat�r�nk 0-val, mert a Mozdony �s a Szeneskocsi mindig �res. A Kocsi
	 * fel�ldefini�lja ezt a met�dust.
	 */
	public int getSzin() {
		logger.log(Level.INFO, this.getID() + ".getSzin(), visszaadott �rt�k: 0");
		return 0;
	}

	/** Visszat�r az id �rt�k�vel. */
	public String getID() {
		return id;
	}
}