
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;


public class WMLLUpdateCheck extends Thread implements Runnable {

	public boolean running;

	public void start() {
		running = true;
		(new Thread(this)).start();
	}

	public void stop1() {
		running = false;
		WMLL.lastUpdateCheck = 0L;
	}

	@Override
	public void run() {
		while (running) {
			checkForUpdates();			
			try {
				Thread.sleep(3600000); // Sleep for one hour.
			} catch (InterruptedException e) {
				stop1();
				e.printStackTrace();
			}
			
		}
	}
	
	private void checkForUpdates() {
		try {
			WMLL.lastUpdateCheck = System.currentTimeMillis();
			URL updateURL = new URL("http://dl.dropbox.com/u/21719562/Minecraft/Mods/WMLL/ver.txt");
			InputStream in = updateURL.openStream();
			Scanner scanner = new Scanner(in);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.equals("EOF"))
					break;
				String modver = WMLL.wmllVersion();
				String[] version = line.split(",");
				String newver = version[2];
				String mcVersion = version[1];
				System.out.println("[WMLL] Version: "+modver+", "+newver);
				if (!newver.equals(modver) && !WMLL.hasUpdateBeenAnnounced(newver)) {
					WMLL wmll = WMLL.i;
					wmll.displayUpdateString(newver, mcVersion);
					wmll.setUpdateAsAnnounced(newver);
					in.close();
				}
			}
		}
		catch (Exception e) { }
	}
}
