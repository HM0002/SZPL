package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Szeneskocsi oszt�ly:
 * 
 * A Jarmu lesz�rmazottja, nem lehet rajta utas. t�roljuk a poz�ci�j�t �s az
 * el�z� pozic��j�t, melyek PalyaElem t�pus�ak. Amelyik PalyaElem-en Szeneskocsi
 * van (teh�t a Szeneskocsi pozici�ja ez a PalyaElem), oda nem lehet alagutat
 * �p�teni.
 * 
 * Felel�ss�g:
 * 
 * Felel a Szeneskocsi mozg�s��rt.Az id� m�l�s�nak f�ggv�ny�ben megl�togatja a
 * poz�ci�j�ban t�rolt PalyaElem p�ld�nyt,melynek �tadja �nmag�t.Ez visszat�r az
 * �j poz�ci�val,�gy mozog a Szeneskocsink.
 */
public class Szeneskocsi extends Jarmu {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/** Konstruktor */
	Szeneskocsi(String id) {
		super(id);
	}

	/**
	 * A PalyaElem t�pus� pozicio elfogad met�dus�t h�vja meg, saj�t mag�t
	 * param�ter�l �tadva, hogy az elfogad met�dus visszat�rhessen �j poz�ci�val
	 * (PalyaElem p�ld�nnyal).
	 */
	protected void latogat() {
		logger.log(Level.INFO, this.getID() + ".latogat(" + pozicio.getID() + ")");

		PalyaElem temp = pozicio;
		pozicio = pozicio.elfogad(this);
		elozoPozicio = temp;
	}
}
