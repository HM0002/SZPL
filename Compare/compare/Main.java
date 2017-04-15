package compare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		switch (args[0]) {

		case "0":
			compare("0_Inicializálások.txt");
			break;
		case "1":
			compare("1_Czirjak.txt");
			break;
		case "2":
			compare("2_Új vonat indítása.txt");
			break;
		case "3":
			compare("3_Vonat mozgása.txt");
			break;
		case "4":
			compare("4_Alagút építés.txt");
			break;
		case "5":
			compare("5_Alagút lebontás.txt");
			break;
		case "6":
			compare("6_Alagút belépés-kilépés.txt");
			break;
		case "7":
			compare("7_Váltó átállítása.txt");
			break;
		case "8":
			compare("8_Kisiklás a váltón.txt");
			break;
		case "9":
			compare("9_Állomás aktiválása.txt");
			break;
		case "10":
			compare("10_Utasak leszállása(+ szeneskocsi helyes mûködése).txt");
			break;
		case "11":
			compare("11_Utasok felszállása.txt");
			break;
		case "12":
			compare("12_Utas nem száll le.txt");
			break;
		case "13":
			compare("13_Keresztezõdésen áthaladás vízszintesen.txt");
			break;
		case "14":
			compare("14_Keresztezõdésen áthaladás függõlegesen.txt");
			break;
		case "15":
			compare("15_Ütközés ellenõrzés.txt");
			break;
		case "16":
			compare("16_Pálya megnyerése, új pálya inicializálása.txt");
			break;
		case "17":
			compare("17_Kilépés.txt");
			break;
		case "18":
			compare("18_Foglalt váltó átállítása.txt");
			break;
		case "19":
			compare("19_Foglalt pályaelemre alagút építés.txt");
			break;
		case "20":
			compare("20_Harmadik alagút építése nem történik meg.txt");
			break;
		case "21":
			compare("21_Alagút építés nem történik meg, ha létezik egy másik alagút, ami éppen foglalt.txt");
			break;
		}

	}

	public static void compare(String s) throws IOException {
		try (BufferedReader br1 = new BufferedReader(
				new FileReader(System.getProperty("user.home") + "\\elvart_teszt_kimenetek\\" + s));
				BufferedReader br2 = new BufferedReader(
						new FileReader(System.getProperty("user.home") + "\\kimenet.txt"));) {
			String line1;
			String line2;
			int c = 0;

			while ((line2 = br2.readLine()) != null) {
				line1 = br1.readLine();
				c++;
				if (!line2.equals(line1)) {
					System.out
							.println("A " + c + ". sor különbözik: \nkimenet: " + line2 + "\nelvárt kimenet: " + line1);
					br1.close();
					br2.close();
					System.exit(0);
				}
			}

			if (c == 0)
				System.out.println("A kimenet.txt üres!.");
			else
				System.out.println("A teszt kimenete megegyezik az elvárt kimenettel.");
		}
	}
}
