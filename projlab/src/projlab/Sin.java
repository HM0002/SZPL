package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sin oszt�ly:
 * 
 * A Valto �s az Allomas �se. Itt val�s�tjuk meg a s�neket �s speci�l�s
 * s�neket(v�lt�, �llom�s), melyeken a vonatok majd mozognak. A nem speci�lis
 * s�nek lehetnek alagutak, ezt egy boolean v�ltoz�ban t�roljuk. T�roljuk m�g a
 * szomsz�dos Sin p�ld�nyokat, egy h�rom elem� t�mbben. A harmadik elem null, ha
 * csak 2 szomsz�d van. E mellett t�roljuk, hogy melyik p�ly�hoz tartozik ez a
 * Sin, �s azt is, hogy jelenleg van e rajta j�rm�.
 * 
 * Felel�ss�g:
 * 
 * Felel�ss�ge az alag�t �p�t�s helyes m�k�d�se, �s a vonatok mozg�s�hoz a
 * helyes k�vetkez� poz�ci� visszaad�sa(alg�t eset�n is) a szomsz�dok t�mb
 * seg�ts�g�vel.
 */
public class Sin {
	private final static Logger logger = Logger.getLogger(Main.class.getName());

	// Attrib�tumok:

	/**
	 * szomszedok: A konstruktorban 3 elem�k�nt van l�trehozva. Tartalmazza a
	 * szomsz�dos Sin p�ld�nyokat, az els� k�t elemben vannak az �rv�nyes
	 * szomsz�dok, teh�t azok vannak �sszek�tve, a harmadik elem �res (null). A
	 * Valto lesz�rmazott rak a harmadik elembe is egy p�ld�nyt, �s ahogyan
	 * v�ltogat a felhaszn�l�, �gy cser�l�dik ebben a t�mbben a sorrend.
	 */
	protected Sin[] szomszedok;

	// debuggol�shoz, hogy tudjuk kicsoda
	private String id;

	/** alagut: Igaz, ha alag�t van �p�tve ezen a p�ld�nyon, egy�bk�nt hamis. */
	protected boolean alagut;

	/** foglalt: T�roljuk, hogy h�ny Jarmu pozicio-ja ez a Sin p�ld�ny. */
	protected int foglalt;

	/** palya: T�rolja, melyik Palya p�ld�nyhoz tartozik ez a Sin p�ld�ny. */
	protected Palya palya;

	/** Konstruktor */
	Sin(String id) {
		logger.log(Level.INFO, "Sin param�ter n�lk�li konstruktor elindult");
		szomszedok = new Sin[3];
		alagut = false;
		palya = null;
		foglalt = 0;
		this.id = id;
	}

	/** �rt�k�l adjda p-t a palya-nak. */
	public void setPalya(Palya p) {
		palya = p;
	}

	/**
	 * �rt�k�l adja s0-t a szomszedok 0. elem�nek, s1-et a szomszedok 1.
	 * elem�nek, �s null-t a szomszedok 3. elem�nek.
	 */
	public void setSzomszedok(Sin s0, Sin s1) {
		szomszedok[0] = s0;
		szomszedok[1] = s1;
		szomszedok[2] = null;
		logger.log(Level.INFO,
				"Sin.setSzomszedok(), rajta:" + this.getID() + " param�terek: " + szomszedok[0] + ", " + szomszedok[1]);
	}

	/**
	 * Visszat�r egy Sin p�ld�nnyal, mely a param�ter�l kapott Jarmu pozicio-ja
	 * lesz. Megn�zi, hogy ez a Sin, amin a Jarmu van, alag�t-e, teh�t az alagut
	 * �rt�ke igaz-e. Ha igen, lek�ri a palya-t�l az alagutak sz�m�t a
	 * getAlagutSzam met�dussal. Ha ez 2, felh�vja a palya alagut met�dus�t,
	 * mely visszat�r a m�sik alag�ttal, ami a p�ly�n van. Ennek visszat�r�nk az
	 * els� szomsz�dj�val. Ha nincs 2 alag�t �p�tve, nem sz�m�t, hogy van-e itt
	 * alag�t vagy sincs, ugyan�gy kezelj�k mind kett� esetet. Lek�rj�k a kapott
	 * Jarmu el�z� poz�ci�j�t a getElozoPozicio met�dussal, majd ezt kompar�ljuk
	 * a szomszedok t�mb els� 2 elem�vel. Amelyikkel nem egyezik meg, azzal
	 * t�r�nk vissza, majd felh�vjuk a visszat�rt Sin setFoglalt met�dus�t, mely
	 * n�veli a foglalt �rt�k�t 1-el. Ezut�n cs�kkentj�k ennek a foglalt
	 * v�ltoz�j�nak �rt�k�t 1-el.
	 */
	public Sin elfogad(Jarmu j) {
		logger.log(Level.INFO, "Sin.elfogad() met�dus, a k�vetkez� s�n elemen:" + this.getID());
		if (alagut == true)
			if (palya.getAlagutSzam() == 2)
				return palya.alagut(this).getFirstSzomszed();

		if (j.getElozoPozicio() == szomszedok[0]) {
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
	 * Megn�zz�k, van-e Jarmu ezen a Sin-en, mert ha igen, akkor nem lehet
	 * alagutat �p�teni. Ha nem, teh�t ha a foglalt �rt�ke 0, lek�rj�k a
	 * palya-t�l az alagutak sz�m�t a getAlagutSzam met�dussal. Ha ez 2,
	 * megn�zz�k, hogy ezen a Sin-en van e alag�t, teh�t az alagut v�ltoz�
	 * �rt�k�t. Ha nincs, sz�val az alagut hamis, visszat�r�nk, mert nem lehet 3
	 * alagutat �p�teni. Ha az alagut igaz, akkor lebontjuk ezt az alagutat,
	 * azaz be�ll�tjuk az alagut �rt�k�t hamisra, majd megh�vjuk a palya
	 * setAlagutSzam megt�dus�t -1 et param�ter�l adva, ezut�n visszat�r�nk. Ha
	 * nem volt 2 alag�t �p�tve, akkor invert�ljuk az alagut �rt�k�t, majd att�l
	 * f�gg�en, hogy az alagut az invert�l�s ut�n hamis vagy igaz lett,
	 * felh�vjuk a palya setAlagutSzam met�dus�t 1 vagy -1 -et param�ter�l adva.
	 * Ezzel a megold�ssal az alagutSzam sose mehet 0 al�, �s 2 fel�.
	 */
	public void setAlagut() {
		logger.log(Level.INFO, "Sin.setAlagut() ezen az elemen:" + this.getID());
		if (foglalt == 0) {
			if (palya.getAlagutSzam() == 2) {
				if (alagut == false)
					// throw exception, nem lehet 2 nel tobb alagut
					return;
				else {
					alagut = false;
					palya.setAlagutSzam(-1);
					return;
				}
			}

			alagut = !alagut;
			if (alagut == false) {
				palya.setAlagutSzam(-1);
				return;
			}

			palya.setAlagutSzam(1);
		}
	}

	/** Visszat�r az alagut �rt�k�vel. */
	public boolean getAlagut() {
		logger.log(Level.INFO, "Sin.getAlagut() rajta:"+this.getID()+" �rt�k: " + alagut);
		return alagut;
	}

	/**
	 * A foglalt v�ltoz� �rt�k�t adja vissza, jelezve, hogy a Sin-en h�ny Jarmu
	 * tart�zkodik.
	 */
	public int getFoglalt() {
		logger.log(Level.INFO, "Sin.getFoglalt() rajta:"+this.getID()+" �rt�ke: " + foglalt);
		return foglalt;
	}

	/**
	 * A foglalt v�ltoz� �rt�k�t n�veli, jelezve, hogy a Sin plusz egy Jarmu
	 * pozicio-ja lett.
	 */
	public void setFoglalt() {
		logger.log(Level.INFO, "Sin.setFoglalt()");
		foglalt++;
	}

	/**
	 * Cs�kkenti a foglalt �rt�k�t 1-el, majd n�veli a szomsz�dok 0. elem�nek
	 * foglalt �rt�k�t 1-el a setFoglalt met�dus seg�ts�g�vel. Ezut�n visszat�r
	 * a szomszedok t�mb 0. elem�vel.
	 */
	public Sin getFirstSzomszed() {
		logger.log(Level.INFO, "Sin.getFirstSzomszed() rajta" + this.getID() + " visszaadott s�n: " + szomszedok[0]);
		foglalt--;
		szomszedok[0].setFoglalt();
		return szomszedok[0];
	}

	// debug method
	public String getID() {
		return id;
	}
}