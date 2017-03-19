package projlab;

/**
 * Allomas oszt�ly:
 * 
 * A Sin objektumnak egy lesz�rmazottja, a grafikus interface-en a s�n mellett
 * lesz egy �llom�s is kirajzolva, de logikailag a Sin-hez tartozik. Az
 * �llom�sok sz�nesek, (�s a kocsik is) �gy jel�lj�k, milyen utasok k�v�nnak oda
 * utazni (az azonos sz�n�ek).
 * 
 * Felel�ss�g:
 * 
 * Ez az objektum felel�s az utasok lesz�ll�t�s��rt, teh�t a kocsik
 * ki�r�t�s��rt. Mivel a Sin gyereke, ez�rt felel m�g a rajtalev� Jarmu
 * k�vetkez� poz�ci�j�nak megad�s��rt is. Nem lehet r� alagutat �p�teni.
 */
public class Allomas extends Sin {

	/**
	 * Attrib�tumok:
	 * 
	 * szin: Meghat�rozza az �llom�s sz�nk�dj�t.
	 * 
	 * aktiv: Azt hat�rozza meg, hogy az utasok lesz�llhatnak-e a rajta l�v�
	 * Jarmu p�ld�nyr�l, vagy sem. Akkor igaz, ha megengedj�k a lesz�ll�st,
	 * egy�bk�nt hamis.
	 */
	private int szin;
	private boolean aktiv;

	/** Konstruktor */
	Allomas(int i) {
		super();
		szin = i;
		aktiv = false;
	}

	/**
	 * Fel�l�rjuk az �s (Sin) setAlagut-j�t, hogy ne lehesssen ide alagutat
	 * �p�teni.
	 */
	public void setAlagut() {
	}

	/**
	 * Sin elfogad(Mozdony m): Igazra �ll�tja az aktiv-ot, �s megadja a
	 * k�vetkez� s�n p�ld�nyt, ahova a Jarmu ker�lni fog az �j id�pillanatban,
	 * �gy, hogy megn�zi a Mozdony elozoPozicio-j�t a getElozoPozicio
	 * met�dussal, �s a szomszedok t�mbb�l visszat�r az ett�l k�l�nb�z� �rt�kkel
	 * az els� k�t elem k�z�l. Mivel �llmo�sra nem �p�lhet alag�t, nincs m�s
	 * felt�teln�z�s. Ezut�n cs�kkentj�k a a foglalt �rt�k�t, �s n�velj�k az �j
	 * pozicio-ban l�v� Sin foglalt �rt�k�t, a setFoglalt met�dussal.
	 */
	public Sin elfogad(Mozdony m) {
		aktiv = true;

		if (m.getElozoPozicio() == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else {
			foglalt--;
			szomszedok[0].setFoglalt();
			return szomszedok[0];
		}
	}

	/**
	 * Sin elfogad(Kocsi k): �sszehasonl�tja az �llom�s �s a kocsi sz�nk�dj�t a
	 * getSzin met�dussal, Ha nem egyezik meg, hamisra �ll�tja az aktiv-ot, ha a
	 * kocsi szin attrib�tuma nem 0, teh�t ha nem �res. Ha megegyezik �s az
	 * aktiv igaz, akkor lesz�ll�tja az utasokat, teh�t ki�r�ti a kocsit a Kocsi
	 * kiurit metodus�val (0-ra rakja a szin-t). mellett megadja a k�vetkez� s�n
	 * p�ld�nyt, ahova a Jarmu ker�lni fog az �j id�pillanatban, �gy, hogy
	 * megn�zi a Mozdony elozoPozicio-j�t a getElozoPozicio met�dussal, �s a
	 * szomszedok t�mbb�l visszat�r az ett�l k�l�nb�z� �rt�kkel az els� k�t elem
	 * k�z�l. Mivel �llmo�sra nem �p�lhet alag�t, nincs m�s felt�teln�z�s.
	 * Ezut�n cs�kkentj�k a a foglalt �rt�k�t, �s n�velj�k az �j pozicio-ban
	 * l�v� Sin foglalt �rt�k�t, a setFoglalt met�dussal.
	 */
	public Sin elfogad(Kocsi k) {
		int kSzin = k.getSzin();
		if (kSzin != szin) {
			if (kSzin != 0)
				aktiv = false;
		} else if (aktiv == true)
			k.kiurit();

		if (k.getElozoPozicio() == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else {
			foglalt--;
			szomszedok[0].setFoglalt();
			return szomszedok[0];
		}
	}

}