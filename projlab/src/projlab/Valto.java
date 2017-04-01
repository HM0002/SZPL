package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * V�lt� oszt�ly:
 * 
 * A PalyaElem objektum lesz�rmazottja. Abban k�l�nb�zik, hogy h�rom szomsz�dos
 * eleme van, nem kett�. Ebb�l a h�romb�l mindig �sszek�t kett�t, �s lehet
 * v�ltoztatni, hogy melyik kett� az. Mivel a PalyaElem gyereke, ez�rt felel m�g
 * a rajtalev� Jarmu k�vetkez� poz�ci�j�nak megad�s��rt is. Nem lehet r�
 * alagutat �p�teni.
 * 
 * Felel�ss�g:
 * 
 * Felel az�rt, hogy az el�gaz�d�s PalyaElem p�ld�nyai k�z�l kett�t �sszek�ss�n,
 * �s ez�ltal a helyes �j pozicio-val t�rjen vissza. Mivel a PalyaElem gyereke,
 * ez�rt felel m�g a rajtalev� Jarmu k�vetkez� poz�ci�j�nak megad�s��rt is. Nem
 * lehet r� alagutat �p�teni.
 */
public class Valto extends PalyaElem {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/** Konstruktor */
	Valto(String id) {
		super(id);
	}

	/**
	 * Megadja a k�vetkez� PalyaElem p�ld�nyt, ahova a Mozdony ker�lni fog az �j
	 * id�pillanatban, �gy, hogy megn�zi a Mozdony elozoPozicio-j�t a
	 * getElozoPozicio met�dussal, �s vizsg�lja, hogy ez benne van-e a
	 * szomsz�dok t�mb els� k�t elem�ben. Ha igen visszat�r a m�sik elemmel a
	 * kett� k�z�l, viszont ha nem, az azt jelenti, hogy nem volt az el�z�
	 * elem�nk �sszek�tve a v�lt�val, �gyhogy �tk�z�st gener�lunk a setFoglalt
	 * met�dussal.
	 */
	public PalyaElem elfogad(Mozdony m) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + m.getID() + ")");

		PalyaElem e = m.getElozoPozicio();

		if (e == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else if (e == szomszedok[1]) {
			foglalt--;
			szomszedok[0].setFoglalt();
			return szomszedok[0];
		} else {
			logger.log(Level.INFO, this.getID() + " v�lt�n�l kisiklott a vonat!");
			setFoglalt();
			return null;
		}
	}

	/**
	 * Megadja a k�vetkez� PalyaElem p�ld�nyt, ahova a Kocsi ker�lni fog az �j
	 * id�pillanatban, �gy, hogy megn�zi a Mozdony elozoPozicio-j�t a
	 * getElozoPozicio met�dussal, �s vizsg�lja, hogy ez benne van-e a
	 * szomsz�dok t�mb els� k�t elem�ben. Ha igen visszat�r a m�sik elemmel a
	 * kett� k�z�l, viszont ha nem, az azt jelenti, hogy nem volt az el�z�
	 * elem�nk �sszek�tve a v�lt�val, �gyhogy �tk�z�st gener�lunk a setFoglalt
	 * met�dussal.
	 */
	public PalyaElem elfogad(Kocsi k) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + k.getID() + ")");

		PalyaElem e = k.getElozoPozicio();

		if (e == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else if (e == szomszedok[1]) {
			foglalt--;
			szomszedok[0].setFoglalt();
			return szomszedok[0];
		} else {
			// Ez tuti soha nem fut le
			logger.log(Level.INFO, "\n\n LEFUTOTT !!!!! WTF! \n\n");
			setFoglalt();
			return null;
		}
	}

	/**
	 * Megadja a k�vetkez� PalyaElem p�ld�nyt, ahova a Szeneskocsi ker�lni fog
	 * az �j id�pillanatban, �gy, hogy megn�zi a Mozdony elozoPozicio-j�t a
	 * getElozoPozicio met�dussal, �s vizsg�lja, hogy ez benne van-e a
	 * szomsz�dok t�mb els� k�t elem�ben. Ha igen visszat�r a m�sik elemmel a
	 * kett� k�z�l, viszont ha nem, az azt jelenti, hogy nem volt az el�z�
	 * elem�nk �sszek�tve a v�lt�val, �gyhogy �tk�z�st gener�lunk a setFoglalt
	 * met�dussal.
	 */
	public PalyaElem elfogad(Szeneskocsi sz) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + sz.getID() + ")");

		PalyaElem e = sz.getElozoPozicio();

		if (e == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else if (e == szomszedok[1]) {
			foglalt--;
			szomszedok[0].setFoglalt();
			return szomszedok[0];
		} else {
			// Ez tuti soha nem fut le
			logger.log(Level.INFO, "\n\n LEFUTOTT !!!!! WTF! \n\n");
			setFoglalt();
			return null;
		}
	}

	/**
	 * Ha nincs j�rm� a v�lt�n, teh�t ha a foglalt �rt�ke 0, a szomszedok t�mb
	 * elemeit kicser�li. A 0. a m�sodik, az 1. a nulladik, �s a 2. elem az els�
	 * helyre ker�l. Mindig a 0. �s az 1. elem van �sszek�tve.
	 */
	public void atallit() {
		logger.log(Level.INFO, this.getID() + ".atallit()");
		if (foglalt == 0) {
			PalyaElem temp = szomszedok[0];
			szomszedok[0] = szomszedok[1];
			szomszedok[1] = szomszedok[2];
			szomszedok[2] = temp;
		}
	}

	/**
	 * Visszat�r az els� k�t szomsz�ddal, teh�t amik �ppen �ssze vannak k�tve.
	 */
	public PalyaElem[] getAllas() {
		PalyaElem[] tmp = new PalyaElem[2];
		tmp[0] = szomszedok[0];
		tmp[1] = szomszedok[1];
		return tmp;
	}

}