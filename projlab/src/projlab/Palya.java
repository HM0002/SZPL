package projlab;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Palya oszt�ly:
 * 
 * Sin, Allomas, Valto objektumokat t�rol egy list�ban, illetve Vonat
 * objektumokat egy m�sikban. T�rolja, hogy h�ny vonat tartozik a p�ly�hoz,
 * mennyi id�k�z�nk�nt(tick) indulnak �j vonatok, h�ny kocsi tartozik a
 * vonatokhoz, �s hogy h�ny alag�t van �p�tve �ppen a p�ly�n. Ez �gy megalkot
 * egy p�ly�t.
 * 
 * Felel�ss�g:
 * 
 * Az�rt felel, hogy egy p�ly�t fogalmazzon meg a r�szobjektumok p�ld�nyainak �s
 * a megkapott p�lyainform�ci�k �sszegy�jt�s�vel.
 */
public class Palya {
	 private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	// Attrib�tumok:

	/** elemek: Itt t�roljuk, hogy mely Sin p�ld�nyok tartoznak a p�ly�hoz. */
	private ArrayList<Sin> elemek;

	/**
	 * vonatok: Itt t�roljuk, hogy mely Vonat p�ld�nyok tartoznak a p�ly�hoz.
	 */
	private ArrayList<Vonat> vonatok;

	/**
	 * vonatSzam: Tartalmazza, hogy az adott p�ly�hoz h�ny vonat tartozik, ennyi
	 * vonatot kell majd ind�tani.
	 */
	private int vonatSzam;

	/**
	 * kocsiSzam: Tartalmazza, hogy h�ny kocsi tartozik ezekhez a vonatokhoz.
	 */
	private int kocsiSzam;

	/**
	 * alagutSzam: Itt t�roljuk, hogy az elemek-ben h�ny Sin p�ld�nynak igaz az
	 * alagut v�ltoz�ja.
	 */
	private int alagutSzam;

	/**
	 * keslelteto: Azt t�roljuk itt, hogy a p�lya mennyi id�k�z�nk�nt(tick)
	 * enged be �j vonatot.
	 */
	private int keslelteto;

	/**
	 * Konstruktor, a param�ter�l kapott Sin p�ld�nyoknak be�ll�tjuk, hogy ehhez
	 * a Palya-hoz tartoznak.
	 */
	Palya(int vSz, int kSz, int k, ArrayList<Sin> e, ArrayList<Vonat> v) {
		logger.log(Level.INFO, "Palya(int,int,int, ArrayList<Sin>, ArrayList<Vonat>) param�ter� konstruktor elindult.");
		elemek = e;
		for (Sin sin : elemek)
			sin.setPalya(this);
		vonatok = v;
		vonatSzam = vSz;
		kocsiSzam = kSz;
		keslelteto = k;
		alagutSzam = 0;
	}

	/** Visszat�r a vonatSzam �rt�k�vel. */
	public int getVonatSzam() {
		logger.log(Level.INFO, "Palya.getVonatSzam(), visszaadott �rt�k: "+vonatSzam);
		return vonatSzam;
	}

	/** Visszat�r a kocsiSzam �rt�k�vel. */
	public int getKocsiSzam() {
		logger.log(Level.INFO, "Palya.getKocsiSzam(), visszaadott �rt�k: "+kocsiSzam);
		return kocsiSzam;
	}

	/** Visszat�r az alagutSzam �rt�k�vel. */
	public int getAlagutSzam() {
		logger.log(Level.INFO, "Palya.getAlagutSzam() visszaadott �rt�k: "+alagutSzam);
		return alagutSzam;
	}

	/** Hozz�ad i-t az alagutSzam �rt�k�hez. */
	public void setAlagutSzam(int i) {
		logger.log(Level.INFO, "Palya.setAlagutSzam(int)");
		alagutSzam += i;
	}

	/** Visszat�r a keslelteto �rt�k�vel. */
	public int getKeslelteto() {
		logger.log(Level.INFO, "Palya.getKeslelteto() visszaadott �rt�k: "+keslelteto);
		return keslelteto;
	}

	/** Visszat�r az elemek list�val, ha az nem �res. Ha igen, akkor null-al. */
	public ArrayList<Sin> getElemek() {
		logger.log(Level.INFO, "Palya.getElemek()");
		if (elemek.isEmpty())
			// throw exception
			return null;
		else
			return elemek;
	}

	/**
	 * Visszat�r az vonatok list�val, ha az nem �res. Ha igen, akkor null-al.
	 */
	public ArrayList<Vonat> getVonatok() {
		logger.log(Level.INFO, "Palya.getVonatok()");
		if (vonatok.isEmpty())
			// throw exception
			return null;
		else
			return vonatok;
	}

	/**
	 * Csak akkor van megh�vva, ha 2 alag�t van a p�ly�n. Param�ter�l kap egy
	 * olyan Sin p�ld�nyt, aminek az alagut attrib�tum�nak �rt�ke igaz. Ezut�n
	 * v�gig megy az elemek list�n, �s a getAlagut met�dussal alagutakat keres.
	 * Ha tal�l, �s az nem a param�ter�l kapott Sin, visszat�r vele.
	 */
	public Sin alagut(Sin s) {
		logger.log(Level.INFO, "Palya.alagut(Sin), param�ter: " + s);
		for (Sin sin : elemek) {
			if (sin.getAlagut() == true && sin != s)
				return sin;
		}

		// this should never happen
		return null;
	}

}
