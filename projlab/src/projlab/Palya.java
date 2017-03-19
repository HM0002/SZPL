package projlab;

import java.util.ArrayList;

/**
 * Palya osztály:
 * 
 * Sin, Allomas, Valto objektumokat tárol egy listában, illetve Vonat
 * objektumokat egy másikban. Tárolja, hogy hány vonat tartozik a pályához,
 * mennyi idõközönként(tick) indulnak új vonatok, hány kocsi tartozik a
 * vonatokhoz, és hogy hány alagút van építve éppen a pályán. Ez így megalkot
 * egy pályát.
 * 
 * Felelõsség:
 * 
 * Azért felel, hogy egy pályát fogalmazzon meg a részobjektumok példányainak és
 * a megkapott pályainformációk összegyûjtésével.
 */
public class Palya {

	/**
	 * Attribútumok:
	 * 
	 * elemek: Itt tároljuk, hogy mely Sin példányok tartoznak a pályához.
	 * vonatok: Itt tároljuk, hogy mely Vonat példányok tartoznak a pályához.
	 * 
	 * vonatSzam: Tartalmazza, hogy az adott pályához hány vonat tartozik, ennyi
	 * vonatot kell majd indítani.
	 * 
	 * kocsiSzam: Tartalmazza, hogy hány kocsi tartozik ezekhez a vonatokhoz.
	 * 
	 * alagutSzam: Itt tároljuk, hogy az elemek-ben hány Sin példánynak igaz az
	 * alagut változója.
	 * 
	 * keslelteto: Azt tároljuk itt, hogy a pálya mennyi idõközönként(tick)
	 * enged be új vonatot.
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

	/** Visszatér a vonatSzam értékével. */
	public int getVonatSzam() {
		return vonatSzam;
	}

	/** Visszatér a kocsiSzam értékével. */
	public int getKocsiSzam() {
		return kocsiSzam;
	}

	/** Visszatér az alagutSzam értékével. */
	public int getAlagutSzam() {
		return alagutSzam;
	}

	/** Hozzáad i-t az alagutSzam értékéhez. */
	public void setAlagutSzam(int i) {
		alagutSzam += i;
	}

	/** Visszatér a keslelteto értékével. */
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
