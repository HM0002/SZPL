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
	private final static Logger logger = Logger.getLogger(Main.class.getName());

	Valto(String id) {
		super(id);
		logger.log(Level.INFO, "Valto() param�ter n�lk�li konstruktor elindult.");
	}

	/**
	 * �rt�k�l adja s0-t a szomszedok 0. elem�nek, s1-et a szomszedok 1.
	 * elem�nek, �s s2-t a szomszedok 3. elem�nek.
	 */
	public void setSzomszedok(Sin s0, Sin s1, Sin s2) {
		logger.log(Level.INFO, "Valto.setSzomszedok(Sin, Sin, Sin), rajta: "+this.getID()+", szomsz�dok:"+ s0.getID()+","+s1.getID()+","+s2.getID());
		szomszedok[0] = s0;
		szomszedok[1] = s1;
		szomszedok[2] = s2;
	}

	/**
	 * Fel�l�rjuk az �s (Sin) setAlagut-j�t, hogy ne lehesssen ide alagutat
	 * �p�teni.
	 */
	public void setAlagut() {
		logger.log(Level.INFO, "Valto.setAlagut() ezen:"+this.getID());
	}

	/**
	 * A szomszedok t�mb elemeit kicser�li, a 0. a m�sodik, az 1. a nulladik, �s
	 * a 2. elem az els� helyre ker�l. Mindig a 0. �s az 1. elem van �sszek�tve.
	 */
	public void atallit() {
		logger.log(Level.INFO, "Valto.atallit() ezen."+this.getID());
		Sin temp = szomszedok[0];
		szomszedok[0] = szomszedok[1];
		szomszedok[1] = szomszedok[2];
		szomszedok[2] = temp;
	}
}