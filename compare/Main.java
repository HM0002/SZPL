package compare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		switch (args[0]) {
		
		case "0":
			compare("Inicializálások.txt");
			break;
			
		case "1":
			compare("Új játék kezdése.txt");
			break;
		case "2":
			compare("Új vonat indítása.txt");
			break;
		case "3":
			compare("Vonat mozgása.txt");
			break;
		case "4":
			compare("Alagút építés.txt");
			break;
		case "5":
			compare("Alagút lebontás.txt");
			break;
		case "6":
			compare("Alagút belépés-kilépés.txt");
			break;
		case "7":
			compare("Váltó átállítása.txt");
			break;
		case "8":
			compare("Kisiklás a váltón.txt");
			break;
		case "9":
			compare("Állomás aktiválása.txt");
			break;
		case "10":
			compare("Utasak leszállása(+ szeneskocsi helye mûködése).txt");
			break;
		case "11":
			compare("Utasok felszállása.txt");
			break;
		case "12":
			compare("Utas nem száll le.txt");
			break;
		case "13":
			compare("Keresztezõdésen áthaladás vízszintesen.txt");
			break;
		case "14":
			compare("Keresztezõdésen áthaladás függõlegesen.txt");
			break;
		case "15":
			compare("Ütközés ellenõrzés.txt");
			break;
		case "16":
			compare("Pálya megnyerése, új pálya inicializálása.txt");
			break;
		case "17":
			compare("Kilépés.txt");
			break;
		case "18":
			compare("Foglalt váltó átállítása.txt");
			break;
		case "19":
			compare("Foglalt pályaelemre alagút építés.txt");
			break;
		case "20":
			compare("Harmadik alagút építése nem történik meg.txt");
			break;
		case "21":
			compare("Alagút építés nem történik meg, ha létezik egy másik alagút, ami éppen foglalt.txt");
			break;			
		}
		
	}

	public static void compare(String s) throws IOException {
		try (BufferedReader br1 = new BufferedReader(new FileReader(
				System.getProperty("user.home") + "\\elvart_teszt_kimenetek\\"
						+ s));
				BufferedReader br2 = new BufferedReader(new FileReader(
						System.getProperty("user.home") + "\\kimenet.txt"));) {
			String line1;
			String line2;
			int c = 0;
			while ((line2 = br2.readLine()) != null) {
				line1 = br1.readLine();
				c++;
				if (!line2.equals(line1)) {
					System.out.println("A " + c
							+ ". sor különbözik: \nkimenet: " + line2
							+ "\nelvárt kimenet: " + line1);
					br1.close();
					br2.close();
					System.exit(0);
				}
			}
			System.out
					.println("A teszt kimenete megegyezik az elvárt kimenettel.");
		}
	}
}
