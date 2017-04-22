package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sin oszt�ly:
 * 
 * A PalyaElem objektumnak egy lesz�rmazottja. Itt val�s�tjuk meg az ugynevezett
 * egyszer� s�neket, �s csak az ilyen egyszer� s�nekre lehet alagutat �p�teni.
 * 
 * Felel�ss�g:
 * 
 * Felel�ss�ge az alag�t �p�t�s helyes m�k�d�se. Mivel a PalyaElem gyereke,
 * ez�rt felel m�g a rajtalev� Jarmu k�vetkez� poz�ci�j�nak megad�s��rt is.
 * Lehet r� alagutat �p�teni (csak r� lehet).
 */
public class Sin extends PalyaElem {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/** Konstruktor */
	Sin(String id, int[] p) {
		super(id, p);
	}

	/**
	 * Visszat�r egy PalyaElem p�ld�nnyal, mely a param�ter�l kapott Mozdony
	 * pozicio-ja lesz. Megn�zi, hogy ez a PalyaElem, amin a Mozdony van,
	 * alag�t-e, teh�t az alagut �rt�ke igaz-e. Ha igen, �s az el�z� poz�ci� nem
	 * volt alag�t, lek�ri a palya-t�l az alagutak sz�m�t a getAlagutSzam
	 * met�dussal. Ha ez 2, felh�vja a palya alagut met�dus�t, mely visszat�r a
	 * m�sik alag�ttal, ami a p�ly�n van. Ennek visszat�r�nk az els�
	 * szomsz�dj�val. Ha nincs 2 alag�t �p�tve, nem sz�m�t, hogy van-e itt
	 * alag�t vagy sincs, ugyan�gy kezelj�k mind kett� esetet. Lek�rj�k a kapott
	 * Mozdony el�z� poz�ci�j�t a getElozoPozicio met�dussal, majd ezt
	 * kompar�ljuk a szomszedok t�mb els� 2 elem�vel. Amelyikkel nem egyezik
	 * meg, azzal t�r�nk vissza, majd felh�vjuk a visszat�rt PalyaElem
	 * setFoglalt met�dus�t, mely n�veli a foglalt �rt�k�t 1-el. Ezut�n
	 * cs�kkentj�k ennek a foglalt v�ltoz�j�nak �rt�k�t 1-el.
	 */
	public PalyaElem elfogad(Mozdony m) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + m.getID() + ")");

		PalyaElem elozoPozicio = m.getElozoPozicio();

		if (alagut == true && !elozoPozicio.getAlagut())
			if (palya.getAlagutSzam() == 2) {
				foglalt--;
				PalyaElem pe = palya.alagut(this);
				pe.setFoglalt();
				return pe;
			}

		if (elozoPozicio == szomszedok[0]) {
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
	 * Visszat�r egy PalyaElem p�ld�nnyal, mely a param�ter�l kapott Kocsi
	 * pozicio-ja lesz. Megn�zi, hogy ez a PalyaElem, amin a Kocsi van,
	 * alag�t-e, teh�t az alagut �rt�ke igaz-e. Ha igen, �s az el�z� poz�ci� nem
	 * volt alag�t, lek�ri a palya-t�l az alagutak sz�m�t a getAlagutSzam
	 * met�dussal. Ha ez 2, felh�vja a palya alagut met�dus�t, mely visszat�r a
	 * m�sik alag�ttal, ami a p�ly�n van. Ennek visszat�r�nk az els�
	 * szomsz�dj�val. Ha nincs 2 alag�t �p�tve, nem sz�m�t, hogy van-e itt
	 * alag�t vagy sincs, ugyan�gy kezelj�k mind kett� esetet. Lek�rj�k a kapott
	 * Kocsi el�z� poz�ci�j�t a getElozoPozicio met�dussal, majd ezt kompar�ljuk
	 * a szomszedok t�mb els� 2 elem�vel. Amelyikkel nem egyezik meg, azzal
	 * t�r�nk vissza, majd felh�vjuk a visszat�rt PalyaElem setFoglalt
	 * met�dus�t, mely n�veli a foglalt �rt�k�t 1-el. Ezut�n cs�kkentj�k ennek a
	 * foglalt v�ltoz�j�nak �rt�k�t 1-el.
	 */
	public PalyaElem elfogad(Kocsi k) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + k.getID() + ")");

		PalyaElem elozoPozicio = k.getElozoPozicio();

		if (alagut == true && !elozoPozicio.getAlagut())
			if (palya.getAlagutSzam() == 2) {
				foglalt--;
				PalyaElem pe = palya.alagut(this);
				pe.setFoglalt();
				return pe;
			}

		if (elozoPozicio == szomszedok[0]) {
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
	 * Visszat�r egy PalyaElem p�ld�nnyal, mely a param�ter�l kapott
	 * Szenecskocsi pozicio-ja lesz. Megn�zi, hogy ez a PalyaElem, amin a
	 * Szenecskocsi van, alag�t-e, teh�t az alagut �rt�ke igaz-e. Ha igen, �s az
	 * el�z� poz�ci� nem volt alag�t, lek�ri a palya-t�l az alagutak sz�m�t a
	 * getAlagutSzam met�dussal. Ha ez 2, felh�vja a palya alagut met�dus�t,
	 * mely visszat�r a m�sik alag�ttal, ami a p�ly�n van. Ennek visszat�r�nk az
	 * els� szomsz�dj�val. Ha nincs 2 alag�t �p�tve, nem sz�m�t, hogy van-e itt
	 * alag�t vagy sincs, ugyan�gy kezelj�k mind kett� esetet. Lek�rj�k a kapott
	 * Szenecskocsi el�z� poz�ci�j�t a getElozoPozicio met�dussal, majd ezt
	 * kompar�ljuk a szomszedok t�mb els� 2 elem�vel. Amelyikkel nem egyezik
	 * meg, azzal t�r�nk vissza, majd felh�vjuk a visszat�rt PalyaElem
	 * setFoglalt met�dus�t, mely n�veli a foglalt �rt�k�t 1-el. Ezut�n
	 * cs�kkentj�k ennek a foglalt v�ltoz�j�nak �rt�k�t 1-el.
	 */
	public PalyaElem elfogad(Szeneskocsi sz) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + sz.getID() + ")");

		PalyaElem elozoPozicio = sz.getElozoPozicio();

		if (alagut == true && !elozoPozicio.getAlagut())
			if (palya.getAlagutSzam() == 2) {
				foglalt--;
				PalyaElem pe = palya.alagut(this);
				pe.setFoglalt();
				return pe;
			}

		if (elozoPozicio == szomszedok[0]) {
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
	 * Megn�zz�k, van-e Jarmu ezen a PalyaElem-en, mert ha igen, akkor nem lehet
	 * alagutat �p�teni. Ha nem, teh�t ha a foglalt �rt�ke 0, lek�rj�k a
	 * palya-t�l az alagutak sz�m�t a getAlagutSzam met�dussal. Ha ez 2,
	 * megn�zz�k, hogy ezen a PalyaElem-en van e alag�t, teh�t az alagut v�ltoz�
	 * �rt�k�t. Ha nincs, sz�val az alagut hamis, visszat�r�nk, mert nem lehet 3
	 * alagutat �p�teni. Ha az alagut igaz, akkor lebontjuk ezt az alagutat,
	 * azaz be�ll�tjuk az alagut �rt�k�t hamisra, majd megh�vjuk a palya
	 * setAlagutSzam megt�dus�t -1 et param�ter�l adva, ezut�n visszat�r�nk. Ha
	 * nem volt 2 alag�t �p�tve, akkor megn�zz�k, hogy van-e egy olyan m�sik
	 * p�lyaelem, ami alag�t �s �ppen foglalt, mert ha van akkor nem lehet ide
	 * alagutat �p�teni. Egy�bk�nt invert�ljuk az alagut �rt�k�t, majd att�l
	 * f�gg�en, hogy az alagut az invert�l�s ut�n hamis vagy igaz lett,
	 * felh�vjuk a palya setAlagutSzam met�dus�t 1 vagy -1 -et param�ter�l adva,
	 * majd visszat�r�nk. Ezzel a megold�ssal az alagutSzam sose mehet 0 al�, �s
	 * 2 fel�.
	 */
	public void setAlagut() {
		logger.log(Level.INFO, this.getID() + ".setAlagut()");

		if (foglalt == 0) {
			if (palya.getAlagutSzam() == 2) {
				if (alagut == false) {
					logger.log(Level.INFO, this.getID() + ": Harmadik alag�t �p�t�se nem enged�lyezett!");
					return;
				} else {
					alagut = false;
					palya.setAlagutSzam(-1);
					return;
				}
			}

			if (palya.getAlagutSzam() == 1 && alagut == false) {
				PalyaElem tmp = palya.alagut(this);
				if (tmp.getFoglalt() != 0) {
					logger.log(Level.INFO,
							this.getID() + ": Nem lehet alagutat �p�teni, mert a m�sik alag�t foglalt:" + tmp.getID());
					return;
				}
			}

			alagut = !alagut;
			if (alagut == false) {
				palya.setAlagutSzam(-1);
				return;
			}

			palya.setAlagutSzam(1);
			return;
		}

		logger.log(Level.INFO, this.getID() + ": A s�n foglalt, nem lehet alagutat �p�teni");
	}

}