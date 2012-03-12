import net.minecraft.client.Minecraft;


public class WMLLDebug {

	public static WMLLDebug instance;
	private static long lastCommandTime = 0L;
	private static boolean timeLocked = false;
	private static long currentWorldTime = 0L;

	public static void setTimeToNight() {
		if (System.currentTimeMillis() - lastCommandTime > 150L) {
			if (timeLocked)
				toggleTimeLock();
			WMLL wmll = WMLL.i;
			wmll.worldInfo().a(14000L);
			wmll.debug("[WMLLDebug] Setting time to 14000 ticks");
			lastCommandTime = System.currentTimeMillis();
		}
	}

	public static void setTimeToDay() {
		if (System.currentTimeMillis() - lastCommandTime > 150L) {
			if (timeLocked)
				toggleTimeLock();
			WMLL wmll = WMLL.i;
			wmll.worldInfo().a(0L);
			wmll.debug("[WMLLDebug] Setting time to 0 ticks");
			lastCommandTime = System.currentTimeMillis();
		}
	}
	
	public static void toggleTimeLock() {
		if (System.currentTimeMillis() - lastCommandTime > 150L) {
			WMLL wmll = WMLL.i;
			currentWorldTime = wmll.getWorldTime();
			timeLocked = !timeLocked;
			wmll.debug("[WMLLDebug] "+(timeLocked ? "Locking time at "+currentWorldTime : "Time unlocked"));
			lastCommandTime = System.currentTimeMillis();
		}
	}
	
	public static void onGuiTick() {
		if (timeLocked)
			WMLL.i.worldInfo().a(currentWorldTime);
		displayDebugInfo();
		             
	}

	public static void displayDebugInfo() {
		WMLL wmll = WMLL.i;
		wmll.drawDebug("Time locked? "+timeLocked+" ("+currentWorldTime+")", 1, 6, 0xffffff);
	}

}
