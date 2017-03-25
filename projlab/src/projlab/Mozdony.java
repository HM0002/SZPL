package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mozdony oszt�ly:
 * 
 * A Jarmu lesz�rmazottja, a vonatunk els� j�rm�ve, nem lehet rajta utas.
 * t�roljuk a poz�ci�j�t �s az el�z� pozic��j�t, melyek PalyaElem t�pus�ak.
 * Amelyik PalyaElem-en Mozdony van (teh�t a Mozdony pozici�ja ez a PalyaElem),
 * oda nem lehet alagutat �p�teni.
 * 
 * Felel�ss�g:
 * 
 * Felel a Mozdony mozg�s��rt.Az id� m�l�s�nak f�ggv�ny�ben megl�togatja a
 * poz�ci�j�ban t�rolt PalyaElem p�ld�nyt,melynek �tadja �nmag�t.Ez visszat�r az
 * �j poz�ci�val,�gy mozog a Mozdony-unk.
 */
public class Mozdony extends Jarmu {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/** Konstruktor */
	Mozdony(String id) {
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