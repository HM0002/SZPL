package projlab;

/**
 * Allomas osztály:
 * 
 * A Sin objektumnak egy leszármazottja, a grafikus interface-en a sín mellett
 * lesz egy állomás is kirajzolva, de logikailag a Sin-hez tartozik. Az
 * állomások színesek, (és a kocsik is) így jelöljük, milyen utasok kívánnak oda
 * utazni (az azonos színûek).
 * 
 * Felelõsség:
 * 
 * Ez az objektum felelõs az utasok leszállításáért, tehát a kocsik
 * kiürítéséért. Mivel a Sin gyereke, ezért felel még a rajtalevõ Jarmu
 * következõ pozíciójának megadásáért is. Nem lehet rá alagutat építeni.
 */
public class Allomas extends Sin {

	/**
	 * Attribútumok:
	 * 
	 * szin: Meghatározza az állomás színkódját.
	 * 
	 * aktiv: Azt határozza meg, hogy az utasok leszállhatnak-e a rajta lévõ
	 * Jarmu példányról, vagy sem. Akkor igaz, ha megengedjük a leszállást,
	 * egyébként hamis.
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
	 * Felülírjuk az õs (Sin) setAlagut-ját, hogy ne lehesssen ide alagutat
	 * építeni.
	 */
	public void setAlagut() {
	}

	/**
	 * Sin elfogad(Mozdony m): Igazra állítja az aktiv-ot, és megadja a
	 * következõ sín példányt, ahova a Jarmu kerülni fog az új idõpillanatban,
	 * úgy, hogy megnézi a Mozdony elozoPozicio-ját a getElozoPozicio
	 * metódussal, és a szomszedok tömbbõl visszatér az ettõl különbözõ értékkel
	 * az elsõ két elem közül. Mivel állmoásra nem épülhet alagút, nincs más
	 * feltételnézés. Ezután csökkentjük a a foglalt értékét, és növeljük az új
	 * pozicio-ban lévõ Sin foglalt értékét, a setFoglalt metódussal.
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
	 * Sin elfogad(Kocsi k): Összehasonlítja az állomás és a kocsi színkódját a
	 * getSzin metódussal, Ha nem egyezik meg, hamisra állítja az aktiv-ot, ha a
	 * kocsi szin attribútuma nem 0, tehát ha nem üres. Ha megegyezik és az
	 * aktiv igaz, akkor leszállítja az utasokat, tehát kiüríti a kocsit a Kocsi
	 * kiurit metodusával (0-ra rakja a szin-t). mellett megadja a következõ sín
	 * példányt, ahova a Jarmu kerülni fog az új idõpillanatban, úgy, hogy
	 * megnézi a Mozdony elozoPozicio-ját a getElozoPozicio metódussal, és a
	 * szomszedok tömbbõl visszatér az ettõl különbözõ értékkel az elsõ két elem
	 * közül. Mivel állmoásra nem épülhet alagút, nincs más feltételnézés.
	 * Ezután csökkentjük a a foglalt értékét, és növeljük az új pozicio-ban
	 * lévõ Sin foglalt értékét, a setFoglalt metódussal.
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