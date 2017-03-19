package projlab;

import java.util.ArrayList;

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

	/**
	 * Attrib�tumok:
	 * 
	 * elemek: Itt t�roljuk, hogy mely Sin p�ld�nyok tartoznak a p�ly�hoz.
	 * vonatok: Itt t�roljuk, hogy mely Vonat p�ld�nyok tartoznak a p�ly�hoz.
	 * 
	 * vonatSzam: Tartalmazza, hogy az adott p�ly�hoz h�ny vonat tartozik, ennyi
	 * vonatot kell majd ind�tani.
	 * 
	 * kocsiSzam: Tartalmazza, hogy h�ny kocsi tartozik ezekhez a vonatokhoz.
	 * 
	 * alagutSzam: Itt t�roljuk, hogy az elemek-ben h�ny Sin p�ld�nynak igaz az
	 * alagut v�ltoz�ja.
	 * 
	 * keslelteto: Azt t�roljuk itt, hogy a p�lya mennyi id�k�z�nk�nt(tick)
	 * enged be �j vonatot.
	 */
	private ArrayList<Sin> elemek;
	private ArrayList<Vonat> vonatok;
	private int vonatSzam;
	private int kocsiSzam;
	private int alagutSzam;
	private int keslelteto;

	/** Konstruktor */
	Palya(int vSz, int kSz, int k, ArrayList<Sin> e, ArrayList<Vonat> v) {
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
		return vonatSzam;
	}

	/** Visszat�r a kocsiSzam �rt�k�vel. */
	public int getKocsiSzam() {
		return kocsiSzam;
	}

	/** Visszat�r az alagutSzam �rt�k�vel. */
	public int getAlagutSzam() {
		return alagutSzam;
	}

	/** Hozz�ad i-t az alagutSzam �rt�k�hez. */
	public void setAlagutSzam(int i) {
		alagutSzam += i;
	}

	/** Visszat�r a keslelteto �rt�k�vel. */
	public int getKeslelteto() {
		return keslelteto;
	}

	public ArrayList<Sin> getElemek() {
		if (elemek.isEmpty())
			// throw exception
			return null;
		else
			return elemek;
	}

	public ArrayList<Vonat> getVonatok() {
		if (vonatok.isEmpty())
			// throw exception
			return null;
		else
			return vonatok;
	}

	public Sin alagut(Sin s) {
		for (Sin sin : elemek) {
			if (sin.getAlagut() == true && sin != s)
				return sin;
		}

		// this should never happen
		return null;
	}

}
