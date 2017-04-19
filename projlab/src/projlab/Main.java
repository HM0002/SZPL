package projlab;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main osztály:
 * 
 * Inputok/Outputok kezelése, inicializálás, kirajzolás, bemeneti nyelv
 * értélemezése
 * 
 * Felelõsség: Kezeli az Inputokat/Outputokat, inicializálja a pályát
 * (pályaelemeket és vonatokat is) a kiválasztott forrásból, majd ezekkel
 * inicializálja a játékmotort. Kirajzolja az aktuális játékállást. Értelmezi a
 * bemeneti nyelvet, végrehajtja a kapott utasításokat.
 */
public class Main {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Az inputok forrása
	 */
	public static BufferedReader br = null;

	/**
	 * Létrehoz és beállít a logoláshoz, kirajzoláshoz egy statikus Logger
	 * példányt. Beállítja a parancssori argumentumok alapján a BufferedReader
	 * adatforrását. Ezután egy ciklusban elkezdi soronként feldolgozni a
	 * bemenetet a commandMapping függvény segítségével.
	 */
	public static void main(String[] args) throws SecurityException, IOException {
		
		Controller controller=new Controller();

		for (Handler handler : logger.getParent().getHandlers()) {
			logger.getParent().removeHandler(handler);
		}

		CustomRecordFormatter formatter = new CustomRecordFormatter();

		FileHandler fileHandler = new FileHandler("%h\\kimenet.txt");
		fileHandler.setFormatter(formatter);
		logger.addHandler(fileHandler);

		if (args[0].equals("K"))
			br = new BufferedReader(new InputStreamReader(System.in));
		else if (args[0].equals("F"))
			br = new BufferedReader(
					new FileReader(System.getProperty("user.home") + "\\teszt_bemenetek\\" + args[1] + ".txt"));
		else {
			System.out.println("Érvénytelen bemenet!");
			// JM.ujJatek();
			// Majd a kész játékban.
			System.exit(0);
		}

		logger.setLevel(Level.OFF);

		String line;
		while ((line = br.readLine()) != null) {
			String[] lines = line.split(" ");
			controller.commandMapping(lines, br);
		}

	}

	



	
}