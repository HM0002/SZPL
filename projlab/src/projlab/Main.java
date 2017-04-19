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
 * Main oszt�ly:
 * 
 * Inputok/Outputok kezel�se, inicializ�l�s, kirajzol�s, bemeneti nyelv
 * �rt�lemez�se
 * 
 * Felel�ss�g: Kezeli az Inputokat/Outputokat, inicializ�lja a p�ly�t
 * (p�lyaelemeket �s vonatokat is) a kiv�lasztott forr�sb�l, majd ezekkel
 * inicializ�lja a j�t�kmotort. Kirajzolja az aktu�lis j�t�k�ll�st. �rtelmezi a
 * bemeneti nyelvet, v�grehajtja a kapott utas�t�sokat.
 */
public class Main {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Az inputok forr�sa
	 */
	public static BufferedReader br = null;

	/**
	 * L�trehoz �s be�ll�t a logol�shoz, kirajzol�shoz egy statikus Logger
	 * p�ld�nyt. Be�ll�tja a parancssori argumentumok alapj�n a BufferedReader
	 * adatforr�s�t. Ezut�n egy ciklusban elkezdi soronk�nt feldolgozni a
	 * bemenetet a commandMapping f�ggv�ny seg�ts�g�vel.
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
			System.out.println("�rv�nytelen bemenet!");
			// JM.ujJatek();
			// Majd a k�sz j�t�kban.
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