package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mozdony oszt�ly:
 * 
 * A Jarmu lesz�rmazottja, t�roljuk a poz�ci�j�t �s az el�z� pozic��j�t, melyek
 * Sin t�pus�ak. Amelyik Sin-en Mozdony van (teh�t a Mozdony pozici�ja ez a
 * Sin), oda nem lehet alagutat �p�teni.
 * 
 * Felel�ss�g:
 * 
 * Felel a Mozdony mozg�s��rt.Az id� m�l�s�nak f�ggv�ny�ben megl�togatja a
 * poz�ci�j�ban t�rolt Sin p�ld�nyt,melynek �tadja �nmag�t.Ez visszat�r az �j
 * poz�ci�val,�gy mozog a Mozdony-unk.
 */
public class Mozdony extends Jarmu {
	 private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	Mozdony(String id) {
		super(id);
		logger.log(Level.INFO, "Mozdony() param�ter n�lk�li konstruktor elindult.");
	}

	/** Visszat�r 0-val, ugyanis a mozdony mindig �res. */
	public int getSzin() {
		logger.log(Level.INFO, "Mozdony.getSzin() ezen az elemen:"+this.getID());
		return 0;
	}

}