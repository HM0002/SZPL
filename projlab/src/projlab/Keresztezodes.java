package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Kereszteztodes oszt�ly:
 * 
 * A PalyaElem objektum lesz�rmazottja. Abban k�l�nb�zik, hogy n�gy szomsz�dos
 * eleme van, nem kett�. Ebb�l a n�gyb�l mindig �sszek�ti a megfelel� kett�t.
 * 
 * Felel�ss�g:
 * 
 * Felel az�rt, hogy a keresztez�d�s PalyaElem p�ld�nyai k�z�l p�ronk�nt kett�t
 * �sszek�ss�n, �s ez�ltal a helyes �j pozicio-val t�rjen vissza. Mivel a
 * PalyaElem gyereke, ez�rt felel m�g a rajtalev� Jarmu k�vetkez� poz�ci�j�nak
 * megad�s��rt is. Nem lehet r� alagutat �p�teni.
 */
public class Keresztezodes extends PalyaElem {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/** Konstruktor */
	Keresztezodes(String id) {
		super(id);
	}

	/**
	 * Megadja a k�vetkez� PalyaElem p�ld�nyt, ahova a Szeneskocsi ker�lni fog
	 * az �j id�pillanatban, �gy, hogy megn�zi a Szeneskocsi elozoPozicio-j�t a
	 * getElozoPozicio met�dussal, �s a szomszedok t�mbb�l visszat�r az els�
	 * elemmel, ha 0 volt, a nulladik elemmel ha 1 volt, illetve a harmadik
	 * elemmel, ha 2 volt �s egy�bk�nt a m�sodik elemmel.
	 */
	public PalyaElem elfogad(Mozdony m) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + m.getID() + "), aktiv = true;");

		PalyaElem e = m.getElozoPozicio();

		if (e == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else if (e == szomszedok[1]) {
			foglalt--;
			szomszedok[0].setFoglalt();
			return szomszedok[0];
		} else if (e == szomszedok[2]) {
			foglalt--;
			szomszedok[3].setFoglalt();
			return szomszedok[3];
		} else {
			foglalt--;
			szomszedok[2].setFoglalt();
			return szomszedok[2];
		}
	}

	/**
	 * Megadja a k�vetkez� PalyaElem p�ld�nyt, ahova a Szeneskocsi ker�lni fog
	 * az �j id�pillanatban, �gy, hogy megn�zi a Szeneskocsi elozoPozicio-j�t a
	 * getElozoPozicio met�dussal, �s a szomszedok t�mbb�l visszat�r az els�
	 * elemmel, ha 0 volt, a nulladik elemmel ha 1 volt, illetve a harmadik
	 * elemmel, ha 2 volt �s egy�bk�nt a m�sodik elemmel.
	 */
	public PalyaElem elfogad(Kocsi k) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + k.getID() + "), aktiv = true;");

		PalyaElem e = k.getElozoPozicio();

		if (e == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else if (e == szomszedok[1]) {
			foglalt--;
			szomszedok[0].setFoglalt();
			return szomszedok[0];
		} else if (e == szomszedok[2]) {
			foglalt--;
			szomszedok[3].setFoglalt();
			return szomszedok[3];
		} else {
			foglalt--;
			szomszedok[2].setFoglalt();
			return szomszedok[2];
		}
	}

	/**
	 * Megadja a k�vetkez� PalyaElem p�ld�nyt, ahova a Szeneskocsi ker�lni fog
	 * az �j id�pillanatban, �gy, hogy megn�zi a Szeneskocsi elozoPozicio-j�t a
	 * getElozoPozicio met�dussal, �s a szomszedok t�mbb�l visszat�r az els�
	 * elemmel, ha 0 volt, a nulladik elemmel ha 1 volt, illetve a harmadik
	 * elemmel, ha 2 volt �s egy�bk�nt a m�sodik elemmel.
	 */
	public PalyaElem elfogad(Szeneskocsi sz) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + sz.getID() + "), aktiv = true;");

		PalyaElem e = sz.getElozoPozicio();

		if (e == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else if (e == szomszedok[1]) {
			foglalt--;
			szomszedok[0].setFoglalt();
			return szomszedok[0];
		} else if (e == szomszedok[2]) {
			foglalt--;
			szomszedok[3].setFoglalt();
			return szomszedok[3];
		} else {
			foglalt--;
			szomszedok[2].setFoglalt();
			return szomszedok[2];
		}
	}

}
