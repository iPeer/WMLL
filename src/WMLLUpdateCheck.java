import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;


public class WMLLUpdateCheck extends Thread implements Runnable {

	public void run() {
		try {
			URL updateURL = new URL("http://dl.dropbox.com/u/21719562/Minecraft/Mods/WMLL/version.txt");
			InputStream in = updateURL.openStream();
			Scanner scanner = new Scanner(in, "UTF-8");
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.equals("EOF"))
					break;
				int modver = Integer.parseInt(WMLL.WMLLVER.split(" ")[1]);
				int newver = Integer.parseInt(line);
				System.out.println("[WMLL] Version: "+modver+", "+newver);
				if (newver > modver) {
					WMLL wmll = WMLL.i;
					wmll.displayUpdateString(newver);
					in.close();
				}
			}
		}
		catch (Exception e) { }
	}
}
