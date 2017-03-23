package projlab;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	private final static Logger logger = Logger.getLogger(Main.class.getName());
	static JatekMotor JM;

	public static void main(String[] args) {
		logger.log(Level.INFO, "Main.main() elindul");

		init();

		// tesztesetek, pl ujJatek:
		JM.ujJatek();

	}

	private static void init() {
		logger.log(Level.INFO, "Main.init()");

		// Elsõ Pályához Sin-ek, Valto-k, Allomas-ok létrehozása
		Sin s0 = new Sin("s0");
		Sin s1 = new Sin("s1");
		Sin s2 = new Sin("s2");
		Sin s3 = new Sin("s3");
		Sin s4 = new Sin("s4");
		Sin s5 = new Sin("s5");
		Sin s6 = new Sin("s6");
		Sin s7 = new Sin("s7");
		Sin s8 = new Sin("s8");
		Valto s9 = new Valto("s9 V");
		Sin s10 = new Sin("s10");
		Sin s11 = new Sin("s11");
		Valto s12 = new Valto("s12");
		Sin s13 = new Sin("s13");
		Sin s14 = new Sin("s14");
		Sin s15 = new Sin("s15");
		Sin s16 = new Sin("s16");
		Allomas s17 = new Allomas(1, "s17 A");
		Sin s18 = new Sin("s18");
		Sin s19 = new Sin("s19");
		Allomas s20 = new Allomas(2, "s20");
		Sin s21 = new Sin("s21");
		Sin s22 = new Sin("s22");
		Sin s23 = new Sin("s23");

		// Elsõ pályához Sin-eknek ArrayList létrehozása
		ArrayList<Sin> sinek1 = new ArrayList<Sin>();

		// Sin-ek összekötése
		s0.setSzomszedok(s1, null);
		s1.setSzomszedok(s0, s2);
		s2.setSzomszedok(s1, s3);
		s3.setSzomszedok(s2, s4);
		s4.setSzomszedok(s3, s5);
		s5.setSzomszedok(s4, s6);
		s6.setSzomszedok(s7, s17);
		s7.setSzomszedok(s6, s8);
		s8.setSzomszedok(s7, s9);
		s9.setSzomszedok(s8, s10, s18);
		s10.setSzomszedok(s9, s11);
		s11.setSzomszedok(s10, s12);
		s12.setSzomszedok(s11, s13, s23);
		s13.setSzomszedok(s12, s14);
		s14.setSzomszedok(s13, s15);
		s15.setSzomszedok(s14, s16);
		s16.setSzomszedok(s15, s17);
		s17.setSzomszedok(s16, s6);
		s18.setSzomszedok(s9, s19);
		s19.setSzomszedok(s18, s20);
		s20.setSzomszedok(s19, s21);
		s21.setSzomszedok(s20, s22);
		s22.setSzomszedok(s21, s23);
		s23.setSzomszedok(s22, s12);

		// sinek1 listához a Sin példányok hozzáadása
		sinek1.add(s0);
		sinek1.add(s1);
		sinek1.add(s2);
		sinek1.add(s3);
		sinek1.add(s4);
		sinek1.add(s5);
		sinek1.add(s6);
		sinek1.add(s7);
		sinek1.add(s8);
		sinek1.add(s9);
		sinek1.add(s10);
		sinek1.add(s11);
		sinek1.add(s12);
		sinek1.add(s13);
		sinek1.add(s14);
		sinek1.add(s15);
		sinek1.add(s16);
		sinek1.add(s17);
		sinek1.add(s18);
		sinek1.add(s19);
		sinek1.add(s20);
		sinek1.add(s21);
		sinek1.add(s22);
		sinek1.add(s23);

		// Jarmu-vek létrehozása, elõször a vonat1 elemei
		Mozdony m0 = new Mozdony("m0");
		Kocsi k1 = new Kocsi(2, "k1");
		Kocsi k2 = new Kocsi(1, "k2");

		// majd a vonat2 elemei
		Mozdony m3 = new Mozdony("m3");
		Kocsi k4 = new Kocsi(1, "k4");
		Kocsi k5 = new Kocsi(2, "k5");

		// vonatoknak ArrayList
		ArrayList<Jarmu> vonat1 = new ArrayList<Jarmu>();
		ArrayList<Jarmu> vonat2 = new ArrayList<Jarmu>();

		// vonatokba a Jarmu-vek elhelyezése: vonat1
		vonat1.add(m0);
		vonat1.add(k1);
		vonat1.add(k2);

		// vonatokba a Jarmu-vek elhelyezése: vonat2
		vonat2.add(m3);
		vonat2.add(k4);
		vonat2.add(k5);

		// Vonat objektumok létrehozása
		Vonat v1 = new Vonat(vonat1);
		Vonat v2 = new Vonat(vonat2);

		// Vonat-oknak ArrayList létrehozása
		ArrayList<Vonat> vonatok = new ArrayList<Vonat>();

		// vonatok hozzáadása a vonatlistához
		vonatok.add(v1);
		vonatok.add(v2);

		// 0. Pálya létrehozása
		Palya p0 = new Palya(2, 2, 100, sinek1, vonatok);

		// Palya-knak ArrayList létrehozása
		ArrayList<Palya> palyak = new ArrayList<Palya>();

		// tesztpálya hozzáadása a pályalistához
		palyak.add(p0);

		// JatekMotor létrehozása, palyak átadása
		JM = new JatekMotor(palyak);

		logger.log(Level.INFO, "Main.init() vége!");
		Reader reader = new InputStreamReader(System.in);
		try {
			int ch = reader.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
