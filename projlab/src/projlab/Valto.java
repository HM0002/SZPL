package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * V�lt� oszt�ly:
 * 
 * A Sin objektum lesz�rmazottja. Abban k�l�nb�zik, hogy 3 szomsz�dos s�nje van,
 * nem 2. Az�rt felel, hogy ebb�l a h�romb�l mindig �sszek�ss�n kett�t, �s
 * lehessen v�ltoztatni, hogy melyik kett� az. Mivel a Sin gyereke, ez�rt felel
 * m�g a rajtalev� Jarmu k�vetkez� poz�ci�j�nak megad�s��rt is. Nem lehet r�
 * alagutat �p�teni.
 * 
 * Felel�ss�g:
 * 
 * Felel az�rt, hogy az el�gaz�d�s Sin p�ld�nyai k�z�l kett�t �sszek�ss�n. Mivel
 * a Sin gyereke, ez�rt felel m�g a rajtalev� Jarmu k�vetkez� poz�ci�j�nak
 * megad�s��rt is. Nem lehet r� alagutat �p�teni.
 */
public class Valto extends Sin {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/** Konstruktor */
	Valto(String id) {
		super(id);
	}

	/**
	 * �rt�k�l adja s0-t a szomszedok 0. elem�nek, s1-et a szomszedok 1.
	 * elem�nek, �s s2-t a szomszedok 3. elem�nek.
	 */
	public void setSzomszedok(Sin s0, Sin s1, Sin s2) {
		szomszedok[0] = s0;
		szomszedok[1] = s1;
		szomszedok[2] = s2;
		logger.log(Level.INFO,
				this.getID() + ".setSzomszedok(" + s0.getID() + ", " + s1.getID() + ", " + s2.getID() + ") felh�vva");
	}

	/**
	 * Fel�l�rjuk az �s (Sin) setAlagut-j�t, hogy ne lehesssen ide alagutat
	 * �p�teni.
	 */
	public void setAlagut() {
		logger.log(Level.INFO, "Valto.setAlagut() ezen:" + this.getID());
	}

	/**
	 * Sin elfogad(Jarmu j): Megadja a k�vetkez� s�n p�ld�nyt, ahova a Jarmu
	 * ker�lni fog az �j id�pillanatban, �gy, hogy megn�zi a Mozdony
	 * elozoPozicio-j�t a getElozoPozicio met�dussal, �s vizsg�lja, hogy ez
	 * benne van-e a szomsz�dok t�mb els� k�t elem�ben. Ha igen visszat�r a
	 * m�sik elemmel a kett� k�z�l, viszont ha nem, az azt jelenti, hogy nem
	 * volt az el�z� s�n�nk �sszek�tve a v�lt�val, �gyhogy �tk�z�st gener�lunk a
	 * setFoglalt met�dussal.
	 */
	public Sin elfogad(Jarmu j) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + j.getID() + ")");
		if (j.getElozoPozicio() == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else if (j.getElozoPozicio() == szomszedok[1]) {
			foglalt--;
			szomszedok[0].setFoglalt();
			return szomszedok[0];
		} else {
			setFoglalt();
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
			Sin temp = szomszedok[0];
			szomszedok[0] = szomszedok[1];
			szomszedok[1] = szomszedok[2];
			szomszedok[2] = temp;
		}
	}
}