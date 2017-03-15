package projlab;

import java.util.ArrayList;

public class JatekMotor {
	
	private int prevtime;
	private int ujVonat;
	private int vonatSzamlalo;
	private ArrayList<Vonat> vonatok;
	private ArrayList<Palya> palyak;
	
	JatekMotor() {
		vonatok = new ArrayList<Vonat>();
		palyak = new ArrayList<Palya>();
		prevtime = 0;
		ujVonat = 0;
		vonatSzamlalo = 0;
	}
	
	public void /*boolean*/ idoMeres() {}
	
	public void ujJatek() {
		//konstruktor hivasok
		run();
	}
	
	public void kilepes() {
		//exit
	}
	
	public void vonatInditas() {
		//logika
		vonatSzamlalo++;
	}

	public boolean utkozesEllenorzes() {
		ArrayList<Sin> temp = new ArrayList<Sin>();
		
		for (Vonat vonat : vonatok) {
			for (Jarmu jarmu : vonat.getVonat()) {
				if (temp.contains(jarmu.getPozicio()))
					return true;
				temp.add(jarmu.getPozicio());
			}
		}
		return false;
	}
	
	public boolean gyozelemEllenorzes() {
		if (palyak.get(0).getVonatSzam() == vonatSzamlalo) {
			for (Vonat vonat : vonatok) {
				for (Jarmu jarmu : vonat.getVonat())
					if (jarmu.getSzin() != 0)
						return false;
			}
			return true;
		}
		return false;
	}
	
	public void run() {
		while (!utkozesEllenorzes() && !gyozelemEllenorzes())
			utkozesEllenorzes();
			gyozelemEllenorzes();
			idoMeres();
			vonatInditas();
	}
	
}