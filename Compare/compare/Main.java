package compare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		switch (args[0]) {

		case "0":
			compare("0_Inicializ�l�sok.txt");
			break;
		case "1":
			compare("1_Czirjak.txt");
			break;
		case "2":
			compare("2_�j vonat ind�t�sa.txt");
			break;
		case "3":
			compare("3_Vonat mozg�sa.txt");
			break;
		case "4":
			compare("4_Alag�t �p�t�s.txt");
			break;
		case "5":
			compare("5_Alag�t lebont�s.txt");
			break;
		case "6":
			compare("6_Alag�t bel�p�s-kil�p�s.txt");
			break;
		case "7":
			compare("7_V�lt� �t�ll�t�sa.txt");
			break;
		case "8":
			compare("8_Kisikl�s a v�lt�n.txt");
			break;
		case "9":
			compare("9_�llom�s aktiv�l�sa.txt");
			break;
		case "10":
			compare("10_Utasak lesz�ll�sa(+ szeneskocsi helyes m�k�d�se).txt");
			break;
		case "11":
			compare("11_Utasok felsz�ll�sa.txt");
			break;
		case "12":
			compare("12_Utas nem sz�ll le.txt");
			break;
		case "13":
			compare("13_Keresztez�d�sen �thalad�s v�zszintesen.txt");
			break;
		case "14":
			compare("14_Keresztez�d�sen �thalad�s f�gg�legesen.txt");
			break;
		case "15":
			compare("15_�tk�z�s ellen�rz�s.txt");
			break;
		case "16":
			compare("16_P�lya megnyer�se, �j p�lya inicializ�l�sa.txt");
			break;
		case "17":
			compare("17_Kil�p�s.txt");
			break;
		case "18":
			compare("18_Foglalt v�lt� �t�ll�t�sa.txt");
			break;
		case "19":
			compare("19_Foglalt p�lyaelemre alag�t �p�t�s.txt");
			break;
		case "20":
			compare("20_Harmadik alag�t �p�t�se nem t�rt�nik meg.txt");
			break;
		case "21":
			compare("21_Alag�t �p�t�s nem t�rt�nik meg, ha l�tezik egy m�sik alag�t, ami �ppen foglalt.txt");
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
							.println("A " + c + ". sor k�l�nb�zik: \nkimenet: " + line2 + "\nelv�rt kimenet: " + line1);
					br1.close();
					br2.close();
					System.exit(0);
				}
			}

			if (c == 0)
				System.out.println("A kimenet.txt �res!.");
			else
				System.out.println("A teszt kimenete megegyezik az elv�rt kimenettel.");
		}
	}
}
