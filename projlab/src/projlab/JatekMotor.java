package projlab;

import java.util.ArrayList;

/**
 * JatekMotor osztály:
 * 
 * Ez a legfelsõ objektum, ez kezeli a játék futását, helyes menetét.
 * 
 * Felelõsség:
 * 
 * Felel, hogy a pályára új vonatok érkezzenek, nézi, ha veszít vagy nyer a
 * játékos, új pályát tölt be, ha szükséges. Kezeli az idõ múlását. Továbbá
 * felel az ütközések ellenõrzéséért, ezáltal tudja, hogy ha veszít a játékos.
 */
public class JatekMotor {

	// Attribútumok:

	/**
	 * palyak: Itt tároljuk az összes Palya példányunkat. A 0. elemben tároljuk
	 * az aktuális pályát, amin éppen játszunk. Pálya váltásnál shifteljük.
	 */
	private ArrayList<Palya> palyak;

	/**
	 * prevTime: Itt tároljuk, mikor mozgattuk utoljára a Jarmu objektumainkat.
	 */
	private long prevTime;

	/**
	 * ujVonat: Itt tároljuk, hogy utoljára hány idõpillanat(tick) óta indult új
	 * Vonat-ot.
	 */
	private int ujVonat;

	/**
	 * vonatSzamlalo: Ebben tároljuk, hogy hány vonatot indítottunk el az éppen
	 * futásban lévõ pályán.
	 */
	private int vonatSzamlalo;

	/**
	 * Konstruktor, az ujVonat kezdõértéke egy nagy szám, mely biztosan nagyobb,
	 * mint a pályák keslelteto értéke, ezáltal biztosan elindul az elsõ vonat.
	 */
	JatekMotor(ArrayList<Palya> p) {
		palyak = p;
		prevTime = 0;
		ujVonat = 500;
		vonatSzamlalo = 0;
	}

	/**
	 * Vizsgáljuk az eltelt idõt. Ha eltelt egy adott idõ (1 másodperc), lekéri
	 * az aktuális pálya (palyak 0. eleme) vonatait a getVonatok metódussal,
	 * majd ezeknek a vonatoknak a jármûveit a getJarmuvek metódussal. Az összes
	 * jármûnek ezután meghívjuk a tick metódusát, mely felel a mozgatásukért.
	 * Ezután reseteljük az eltelt idõt.
	 */
	public void idoMeres() {
		if (Math.abs(System.nanoTime() - prevTime) > 1000000000) {
			for (Vonat vonat : palyak.get(0).getVonatok()) {
				for (Jarmu jarmu : vonat.getJarmuvek())
					jarmu.tick();
			}
			prevTime = System.nanoTime();
		}
	}

	/**
	 * Elindítja az idõt(eállítja a prevTime értékét), majd meghívja a run
	 * metódust.
	 */
	public void ujJatek() {
		prevTime = System.nanoTime();
		run();
	}

	/** Kilép az alkalmazásból. */
	public void kilepes() {
		// exit
	}

	/**
	 * Lekéri az aktuális pálya (palyak 0. eleme) getVonatSzam metódusával, hogy
	 * hány vonatot kell küldeni összesen, és ezt komparálja a
	 * vonatSzamlalo-val. Ha a vonatSzamlalo kisebb, lekéri a pályától, hogy
	 * mennyi idõközönként(tick) indíthat új vonatot, a getKeslelteto
	 * metódussal. Ezt komparálja az ujVonat-tal, melyben az elõzõ vonatindítás
	 * óta eltelt tick-ek száma van tárolva. Ha az ujVonat értéke kisebb,
	 * növeljük 1-el. Ha nagyobb vagy egyenlõ, lekérjük a pályától a
	 * vonatSzamlalo-adik vonat jármûveit, ugyanis egy rendezett listában vannak
	 * a vonatok, és a vonatSzamlalo-adik vonat az, ami még nem indult el.
	 * Ezután az így lekért jármûvek setPozicio metódusával beállítjuk a helyes
	 * kezdõpozíciókat, majd növeljök a vonatSzamlalo-t, és nullázzuk az
	 * ujVonat-ot.
	 */
	public void vonatInditas() {
		if (vonatSzamlalo < palyak.get(0).getVonatSzam()) {
			if (ujVonat >= palyak.get(0).getKeslelteto()) {
				for (Jarmu jarmu : palyak.get(0).getVonatok().get(vonatSzamlalo).getJarmuvek())
					for (int i = 0; i < palyak.get(0).getKocsiSzam() + 1; i++)
						jarmu.setPozicio(palyak.get(0).getElemek().get(5 - i));
				ujVonat = 0;
				vonatSzamlalo++;
			} else
				ujVonat++;
		}
	}

	/**
	 * Megnézi, hogy minden Sin példányon csak egy jármû van-e, ezáltal eldönti,
	 * hogy van-e ütközés vagy sem. Lekéri az aktuális pálya (palyak 0. eleme)
	 * Sin elemeit, a getElemek metódussal, ezután lekéri, hogy hány jármû van
	 * ezeken az elemeken, a getFoglalt metódussal. Ha ez több mint 1, utközés
	 * történt, és visszatérünk igazzal, egyébként nem és visszatérünk hamissal.
	 */
	public boolean utkozesEllenorzes() {
		for (Sin sin : palyak.get(0).getElemek())
			if (sin.getFoglalt() > 1)
				return true;

		return false;
	}

	/**
	 * Lekéri az aktuális pálya (palyak 0. eleme) getVonatSzam metódusával, hogy
	 * hány vonatot kell küldeni összesen, és ezt komparálja a
	 * vonatSzamlalo-val. Ha a két érték különbözõ, visszatérünk hamissal,
	 * ugyanis az azt jelenti, hogy még nem indult el az összes vonatunk, ami a
	 * pályához tartozik. Ha a két értek megegyezik, lekérjük a pályától a
	 * vonatokat a getVonatok metódussal, majd a vonatknak a jármûveit a
	 * getJarmuvek metódussal. Ezután lekérjük az összes jármûvünknek a színét,
	 * a Jarmu getSzin metódusával. Amint az egyiknek nem 0 (üres szín),
	 * visszatérünk hamissal. Ha lekértük az összes jármû színét, és mindegyik
	 * 0, tehát üres, akkor ez a pálya sikeresen teljesítve van, és visszatérünk
	 * igazzal.
	 */
	public boolean gyozelemEllenorzes() {
		if (palyak.get(0).getVonatSzam() == vonatSzamlalo) {
			for (Vonat vonat : palyak.get(0).getVonatok()) {
				for (Jarmu jarmu : vonat.getJarmuvek())
					if (jarmu.getSzin() != 0)
						return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * Meghívja az utkozesEllenorzes metódust, és mindaddig, amíg ez nem tér
	 * vissza igazzal, egy loop-ban vagyunk. A loop-ban meghívjuk a
	 * gyozelemEllenorzes metódust, ami ha igazzal tér vissza, megnézzük, hogy
	 * van-e még pálya hátra a palyak listában. Ha nincs, végigvittük a játékot,
	 * errõl értesítjuk a felhasználót, majd visszatérünk a menübe. Ha van még
	 * pálya hátra, töröljük a palyak 0. elemét, és a többi elemét balra
	 * shifteljuk az ArrayList remove metódusával. E mellett újrakezdjük az idõ
	 * mérését, és beállítjuk a vonatSzamlalo-t nullára, illetve az ujVonat-ot
	 * egy nagy számra(500, egy akkora szám kell, ami nagyobb, mint a pályáknak
	 * a keslelteto értékei, hogy elinduljon az elsõ vonat). A
	 * gyozelemEllenorzes után felhívjuk az idoMeres és a vonatInditas
	 * metódusokat.
	 */
	private void run() {
		while (!utkozesEllenorzes()) {
			// GUI input kezelések
			if (gyozelemEllenorzes()) {
				if (palyak.size() < 2) {
					// vissza a menube, gyozelem popup
					return;
				} else {
					// kiszedi a 0. elemet, és a többi elemet shifteli balra
					palyak.remove(0);
					prevTime = System.nanoTime();
					ujVonat = 500;
					vonatSzamlalo = 0;
				}
			}
			idoMeres();
			vonatInditas();
		}
		// utkozes popup etc.
	}

}