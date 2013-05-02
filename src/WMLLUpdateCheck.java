
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
			checkForUpdates();	
//			try {
//				Thread.sleep(3600000); // Sleep for one hour.
//			} catch (InterruptedException e) {
//				stop1();
//				e.printStackTrace();
//			}

	}

	private void checkForUpdates() {
		try {
			WMLL.lastUpdateCheck = System.currentTimeMillis();
			URL updateURL = new URL("https://dl.dropboxusercontent.com/u/21719562/Minecraft/Mods/WMLL/ver.txt");
			InputStream in = updateURL.openStream();
			Scanner scanner = new Scanner(in);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String modver = WMLL.wmllVersion();
				String[] version = line.split(",");
				String newver = version[2];
				String mcVersion = version[1];
				System.out.println("[WMLL] Version: "+modver+", "+newver);
				if (!newver.equals(modver)) {
					WMLL wmll = WMLL.i;
					if (!WMLL.hasUpdateBeenAnnounced(newver)) {
						wmll.displayUpdateString(newver, mcVersion);
						wmll.setUpdateAsAnnounced(newver);
					}
					String[] a = new String[2];
					a[0] = newver;
					a[1] = mcVersion;
					wmll.updateInfo = a;
					in.close();
					break;
				}
			}
		}
		catch (Exception e) { }
	}
}
