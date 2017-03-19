package projlab;

import java.util.ArrayList;

public class Main {
	
	static JatekMotor JM;
	
	public static void main(String[] args) {
				
		init();
		
		//tesztesetek, pl ujJatek:
		JM.ujJatek();
		
	}	

	private static void init() {

		//Pályákhoz Sin-ek létrehozása
		Sin s0 = new Sin();
		Sin s1 = new Sin();
		Sin s2 = new Sin();
		Sin s3 = new Sin();
		Sin s4 = new Sin();
		Sin s5 = new Sin();
		
		//Váltó Sin
		Valto s6 = new Valto();
		
		//Állomás Sin
		Allomas s7 = new Allomas(2);
		
		Sin s8 = new Sin();
		Sin s9 = new Sin();
		Sin s10 = new Sin();
		Sin s11 = new Sin();
		Sin s12 = new Sin();
		Sin s13 = new Sin();
		Sin s14 = new Sin();
		Sin s15 = new Sin();
		Sin s16 = new Sin();
		
		//Állomás Sin
		Allomas s17 = new Allomas(1);
		
		//Sin-eknek ArrayList létrehozása
		ArrayList<Sin> sinek = new ArrayList<Sin>();
			
		//Sin-ek összekötése
		s0.setSzomszedok(null, s1);
		s1.setSzomszedok(s0, s2);
		s2.setSzomszedok(s1, s3);
		s3.setSzomszedok(s2, s4);
		s4.setSzomszedok(s3, s5);
		s5.setSzomszedok(s4, s6);
		s6.setSzomszedok(s5, s17, s7);
		s7.setSzomszedok(s6, s8);
		s8.setSzomszedok(s7, s9);
		s9.setSzomszedok(s8, s10);
		s10.setSzomszedok(s9, s11);
		s11.setSzomszedok(s10, s12);
		s12.setSzomszedok(s11, s13);
		s13.setSzomszedok(s12, s14);
		s14.setSzomszedok(s13, s15);
		s15.setSzomszedok(s14, s16);
		s16.setSzomszedok(s15, s17);
		s17.setSzomszedok(s16, s6);
			
		//sinek listához a Sin példányok hozzáadása
		sinek.add(s0);
		sinek.add(s1);
		sinek.add(s2);
		sinek.add(s3);
		sinek.add(s4);
		sinek.add(s5);
		sinek.add(s6);
		sinek.add(s7);
		sinek.add(s8);
		sinek.add(s9);
		sinek.add(s10);
		sinek.add(s11);
		sinek.add(s12);
		sinek.add(s13);
		sinek.add(s14);
		sinek.add(s15);
		sinek.add(s16);
		sinek.add(s17);
		
		//Jarmu-vek létrehozása, elõször a vonat1 elemei
		Mozdony m0 = new Mozdony();
		Kocsi k1 = new Kocsi(2);
		Kocsi k2 = new Kocsi(1);
		
		//majd a vonat2 elemei
		Mozdony m3 = new Mozdony();
		Kocsi k4 = new Kocsi(1);
		Kocsi k5 = new Kocsi(2);
		
		//vonatoknak ArrayList
		ArrayList<Jarmu> vonat1 = new ArrayList<Jarmu>();
		ArrayList<Jarmu> vonat2 = new ArrayList<Jarmu>();
		
		//vonatokba a Jarmu-vek elhelyezése: vonat1
		vonat1.add(m0);
		vonat1.add(k1);
		vonat1.add(k2);
		
		//vonatokba a Jarmu-vek elhelyezése: vonat2
		vonat2.add(m3);
		vonat2.add(k4);
		vonat2.add(k5);
						
		//Vonat objektumok létrehozása
		Vonat v1 = new Vonat(vonat1);
		Vonat v2 = new Vonat(vonat2);
		
		//Vonat-oknak ArrayList létrehozása
		ArrayList<Vonat> vonatok = new ArrayList<Vonat>();
		
		//vonatok hozzáadása a vonatlistához
		vonatok.add(v1);
		vonatok.add(v2);
		
		//0. Pálya létrehozása
		Palya p0 = new Palya(2, 2, 10, sinek, vonatok);
		
		//Palya-knak ArrayList létrehozása
		ArrayList<Palya> palyak = new ArrayList<Palya>();
				
		//tesztpálya hozzáadása a pályalistához
		palyak.add(p0);
		
			
		//JatekMotor létrehozása, palyak átadása
		JM = new JatekMotor(palyak);
	}
}
