package projlab;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JatekMotor oszt�ly:
 * 
 * Ez a legfels� objektum, ez kezeli a j�t�k fut�s�t, helyes menet�t.
 * 
 * Felel�ss�g:
 * 
 * Felel, hogy a p�ly�ra �j vonatok �rkezzenek, n�zi, ha vesz�t vagy nyer a
 * j�t�kos, �j p�ly�t t�lt be, ha sz�ks�ges. Kezeli az id� m�l�s�t. Tov�bb�
 * felel az �tk�z�sek ellen�rz�s��rt, ez�ltal tudja, hogy ha vesz�t a j�t�kos.
 */
public class JatekMotor {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	// Attrib�tumok:

	/**
	 * palyak: Itt t�roljuk az �sszes Palya p�ld�nyunkat. A 0. elemben t�roljuk
	 * az aktu�lis p�ly�t, amin �ppen j�tszunk. P�lya v�lt�sn�l shiftelj�k.
	 */
	private ArrayList<Palya> palyak;

	/**
	 * prevTime: Itt t�roljuk, mikor mozgattuk utolj�ra a Jarmu objektumainkat.
	 */
	private long prevTime;

	/**
	 * ujVonat: Itt t�roljuk, hogy utolj�ra h�ny id�pillanat(tick) �ta indult �j
	 * Vonat-ot.
	 */
	private int ujVonat;

	/**
	 * vonatSzamlalo: Ebben t�roljuk, hogy h�ny vonatot ind�tottunk el az �ppen
	 * fut�sban l�v� p�ly�n.
	 */
	private int vonatSzamlalo;

	/**
	 * Konstruktor, az ujVonat kezd��rt�ke egy nagy sz�m, mely biztosan nagyobb,
	 * mint a p�ly�k keslelteto �rt�ke, ez�ltal biztosan elindul az els� vonat.
	 */
	JatekMotor(ArrayList<Palya> p) {
		logger.log(Level.INFO, "JM konstruktora elindult");
		palyak = p;
		prevTime = 0;
		ujVonat = 500;
		vonatSzamlalo = 0;
	}

	/**
	 * Vizsg�ljuk az eltelt id�t. Ha eltelt egy adott id� (1 m�sodperc),
	 * felhivjuk az idoEltelt met�dus, majd visszat�r�nk igazzal, teh�t hogy
	 * eltelt a m�sodperc. Ha nem telt el, hamissal t�r�nk vissza.
	 */
	public boolean idoMeres() {
		// logger.log(Level.INFO, "JM.idoMeres()");
		if (Math.abs(System.currentTimeMillis() - prevTime) > 1000) {
			idoEltelt();
			return true;
		}
		return false;
	}

	/**
	 * Lek�ri az aktu�lis p�lya (palyak 0. eleme) vonatait a getVonatok
	 * met�dussal, majd ezeknek a vonatoknak a j�rm�veit a getJarmuvek
	 * met�dussal. Ha a jarm� m�r a p�ly�n van, teh�t ha a getPozicio met�dus
	 * nem null-al t�r vissza, akkor a j�rm�nek ezut�n megh�vjuk a tick
	 * met�dus�t, mely felel a mozgat�s��rt. Ezt megcsin�ljuk az �sszes j�rm�re,
	 * �rt�k�l adjuk a jelenlegi id�t a prevTime-nak.
	 */
	public void idoEltelt() {
		for (Vonat vonat : palyak.get(0).getVonatok()) {
			logger.log(Level.INFO, "\nElindul a ciklus a " + vonat.getID() + "-re:\n");
			for (Jarmu jarmu : vonat.getJarmuvek()) {
				if (jarmu.getPozicio() != null) {
					logger.log(Level.INFO, "\t" + jarmu.getID() + " l�ptet�se:");
					jarmu.tick();
				}
			}
			logger.log(Level.INFO, "\nCiklus v�ge.");
		}
		prevTime = System.currentTimeMillis();
	}

	/**
	 * Elind�tja az id�t(be�ll�tja a prevTime �rt�k�t), majd megh�vja a run
	 * met�dust.
	 */
	public void ujJatek() {
		logger.log(Level.INFO, "JM.ujJatek()");
		prevTime = System.currentTimeMillis();
		run();
	}

	/** Kil�p az alkalmaz�sb�l. */
	public void kilepes() {
		logger.log(Level.INFO, "JM.kilepes()");
		// exit
	}

	/**
	 * Lek�ri az aktu�lis p�lya (palyak 0. eleme) getVonatSzam met�dus�val, hogy
	 * h�ny vonatot kell k�ldeni �sszesen, �s ezt kompar�lja a
	 * vonatSzamlalo-val. Ha a vonatSzamlalo kisebb, lek�ri a p�ly�t�l, hogy
	 * mennyi id�k�z�nk�nt(tick) ind�that �j vonatot, a getKeslelteto
	 * met�dussal. Ezt kompar�lja az ujVonat-tal, melyben az el�z� vonatind�t�s
	 * �ta eltelt tick-ek sz�ma van t�rolva. Ha az ujVonat �rt�ke kisebb,
	 * n�velj�k 1-el. Ha nagyobb vagy egyenl�, lek�rj�k a p�ly�t�l a
	 * vonatSzamlalo-adik vonat j�rm�veit, ugyanis egy rendezett list�ban vannak
	 * a vonatok, �s a vonatSzamlalo-adik vonat az, ami m�g nem indult el.
	 * Ezut�n az �gy lek�rt j�rm�vek setKezdoPoziciok met�dus�val be�ll�tjuk a
	 * helyes kezd� poz�ci�kat, majd n�velj�k a vonatSzamlalo-t, �s null�zzuk az
	 * ujVonat-ot.
	 */
	public void vonatInditas() {
		logger.log(Level.INFO, "JM.vonatInditas()");
		if (vonatSzamlalo < palyak.get(0).getVonatSzam()) {
			if (ujVonat >= palyak.get(0).getKeslelteto()) {
				int k = palyak.get(0).getKocsiSzam();
				ArrayList<Jarmu> j = palyak.get(0).getVonatok().get(vonatSzamlalo).getJarmuvek();
				ArrayList<PalyaElem> e = palyak.get(0).getElemek();
				logger.log(Level.INFO, "\nElindul a ciklus:\n");
				for (int i = 0; i < k + 1; i++) {
					logger.log(Level.INFO, "\t" + j.get(i).getID() + " elind�t�sa:");
					j.get(i).setKezdoPoziciok(e.get(5 - i), e.get(4 - i));
				}
				logger.log(Level.INFO, "\nCiklus v�ge.\n");
				ujVonat = 0;
				vonatSzamlalo++;
			} else
				ujVonat++;
		}
	}

	/**
	 * Megn�zi, hogy minden PalyaElem p�ld�nyon csak egy j�rm� van-e, ez�ltal
	 * eld�nti, hogy van-e �tk�z�s vagy sem. Lek�ri az aktu�lis p�lya (palyak 0.
	 * eleme) PalyaElem elemeit, a getElemek met�dussal, ezut�n lek�ri, hogy
	 * h�ny j�rm� van ezeken az elemeken, a getFoglalt met�dussal. Ha ez t�bb
	 * mint 1, utk�z�s t�rt�nt, �s visszat�r�nk igazzal, egy�bk�nt nem �s
	 * visszat�r�nk hamissal.
	 */
	public boolean utkozesEllenorzes() {
		logger.log(Level.INFO, "JM.utkozesEllenorzes()");
		for (PalyaElem e : palyak.get(0).getElemek())
			if (e.getFoglalt() > 1)
				return true;

		return false;
	}

	/**
	 * Lek�ri az aktu�lis p�lya (palyak 0. eleme) getVonatSzam met�dus�val, hogy
	 * h�ny vonatot kell k�ldeni �sszesen, �s ezt kompar�lja a
	 * vonatSzamlalo-val. Ha a k�t �rt�k k�l�nb�z�, visszat�r�nk hamissal,
	 * ugyanis az azt jelenti, hogy m�g nem indult el az �sszes vonatunk, ami a
	 * p�ly�hoz tartozik. Ha a k�t �rtek megegyezik, lek�rj�k a p�ly�t�l a
	 * vonatokat a getVonatok met�dussal, majd a vonatknak a j�rm�veit a
	 * getJarmuvek met�dussal. Ezut�n lek�rj�k az �sszes j�rm�v�nknek a sz�n�t,
	 * a Jarmu getSzin met�dus�val. Amint az egyiknek nem 0 (�res sz�n),
	 * visszat�r�nk hamissal. Ha lek�rt�k az �sszes j�rm� sz�n�t, �s mindegyik
	 * 0, teh�t �res, akkor ez a p�lya sikeresen teljes�tve van, �s visszat�r�nk
	 * igazzal.
	 */
	public boolean gyozelemEllenorzes() {
		logger.log(Level.INFO, "JM.gyozelemEllenorzes()");
		if (palyak.get(0).getVonatSzam() == vonatSzamlalo) {
			for (Vonat vonat : palyak.get(0).getVonatok()) {
				for (Jarmu jarmu : vonat.getJarmuvek())
					if (jarmu.getSzin() != 0)
						return false;
			}
			logger.log(Level.INFO, "Nyert�nk a p�ly�n, k�vetkez� bet�lt�se!");
			return true;
		}
		return false;
	}

	/**
	 * Egy seg�d v�ltoz�nk van, az utkozes. �rt�ke hamis, �s egy loop-ban
	 * vagyunk, am�g ez �gy is marad. A loop-ban megh�vjuk az idoMeres met�dust,
	 * �s ha eltelt 1 m�sodperc, az visszat�r igazzal, egy�bk�nt nem csin�lunk
	 * semmit. Ha eltelt az 1 m�sodperc, megh�vjuk az utkozesEllenorzes
	 * met�dust, melynek a visszat�r�s�t �rt�k�l adjuk az utkozes-nek. Ez ut�n
	 * megh�juk a gyozelemEllenorzes met�dust, ami ha igazzal t�r vissza,
	 * megn�zz�k, hogy van-e m�g p�lya h�tra a palyak list�ban. Ha nincs,
	 * v�gigvitt�k a j�t�kot, err�l �rtes�tj�k a felhaszn�l�t �s visszat�r�nk a
	 * men�be. Ha van m�g p�lya h�tra, t�r�lj�k a palyak 0. elem�t, �s a t�bbi
	 * elem�t balra shifteljuk az ArrayList remove met�dus�val. E mellett
	 * �jrakezdj�k az id� m�r�s�t, �s be�ll�tjuk a vonatSzamlalo-t null�ra,
	 * illetve az ujVonat-ot egy nagy sz�mra(500, egy akkora sz�m kell, ami
	 * nagyobb, mint a p�ly�knak a keslelteto �rt�kei, hogy elinduljon az els�
	 * vonat). A gyozelemEllenorzes ut�n felh�vjuk a vonatInditas met�dust.
	 */
	private void run() {
		logger.log(Level.INFO, "JM.run()");

		boolean utkozes = false;

		while (!utkozes) {
			if (idoMeres()) {
				utkozes = utkozesEllenorzes();
				// GUI input kezel�sek
				if (gyozelemEllenorzes()) {
					if (palyak.size() < 2) {
						logger.log(Level.INFO, "Megnyert�k a j�t�kot!");
						// gyozelem popup
						return;
					} else {
						// kiszedi a 0. elemet, �s a t�bbi elemet shifteli balra
						palyak.remove(0);
						prevTime = System.currentTimeMillis();
						ujVonat = 500;
						vonatSzamlalo = 0;
					}
				}
				vonatInditas();
			}
		}
		// utkozes popup
		logger.log(Level.INFO, "JM.run() met�dusban �tk�z�s t�rt�nt!");
	}

}