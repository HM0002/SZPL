package projlab;

/**
 * Jarmu oszt�ly:
 * 
 * A Mozdony �s a Kocsi objektumok �se. Ez egy abstract oszt�ly, ugyanis
 * j�rm�veket nem p�ld�nyos�tunk, csak kocsikat vagy mozdonyokat. T�roljuk
 * ezeknek a poz�ci�jukat �s az el�z� pozic��jukat, melyek Sin t�pus�ak. Amelyik
 * Sin-en Jarmu van (teh�t a Jarmu pozici�ja ez a Sin), oda nem lehet alagutat
 * �p�teni.
 * 
 * Felel�ss�g:
 * 
 * Ez az objektum felel a mozg� mozdonyok �s kocsik csoportos�t�s��rt. Az id�
 * m�l�s�nak f�ggv�ny�ben (tick) megl�togatja a poz�ci�j�ban t�rolt Sin
 * p�ld�nyt, melynek �tadja �nmag�t. Ez visszat�r az �j poz�ci�val, �gy mozog a
 * Jarmu-v�nk.
 */
public abstract class Jarmu {

	// Attrib�tumok:

	/**
	 * pozicio: Itt t�roljuk, hogy a j�rm� melyik Sin p�ld�nyon helyezkedik el.
	 */
	private Sin pozicio;

	/**
	 * elozoPozicio: Itt t�roljuk, az egy �temmel (tick) kor�bbi Sin p�ld�nyt,
	 * amin elhelyezkedett.
	 */
	private Sin elozoPozicio;

	/** Konstruktor */
	Jarmu() {
		pozicio = null;
		elozoPozicio = null;
	}

	/** Megh�vja a latogat met�dust, ez�ltal jelezve, hogy mozogni kell. */
	public void tick() {
		latogat();
	}

	/**
	 * A Sin t�pus� pozicio attrib�tum elfogad met�dus�t h�vja meg, saj�t mag�t
	 * param�ter�l �tadva, hogy az elfogad met�dus visszat�rhessen �j poz�ci�val
	 * (Sin p�ld�nnyal).
	 */
	protected void latogat() {
		Sin temp = pozicio;
		pozicio = pozicio.elfogad(this);
		elozoPozicio = temp;
	}

	/**
	 * Visszat�r a pozicio-val ami egy Sin objektum, ezzel jelezve, hogy a Jarmu
	 * itt tartozkodik.
	 */
	public Sin getPozicio() {
		return pozicio;
	}

	/**
	 * Visszat�r az elozopozicio-val ami egy Sin objektum, ezzel jelezve, hogy a
	 * Jarmu itt tartozkodott az elozo idopillanatban.
	 */
	public Sin getElozoPozicio() {
		return elozoPozicio;
	}

	/** �rt�k�l adja a pozicio-t az elozoPozicio-nak, s-et a pozicio-nak. */
	public void setPozicio(Sin s) {
		elozoPozicio = pozicio;
		pozicio = s;
	}

	/** Abstract, a Mozdony �s Kocsi-ban ezt implement�ljuk. */
	public abstract int getSzin();

}