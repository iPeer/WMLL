import net.minecraft.client.Minecraft;


/* 
 * Do not use this with an actual installation of WMLL. 
 * This class should NOT be distributed with any version of WMLL. 
 * These commands are purely for debug and should not be available to the end user. 
 */

public class WMLLDebug {

	public static WMLLDebug instance;
	private static long lastCommandTime = 0L;
	private static boolean timeLocked = false;
	private static long currentWorldTime = 0L;

	public static void sendChatCommand(String s) {
		WMLL wmll = WMLL.i;
		//System.out.println(s);
		wmll.entityPlayer().d(s);
	}
	
	public static void setTimeToNight() {
		if (System.currentTimeMillis() - lastCommandTime > 150L) {
			sendChatCommand("/time set 14000");
			lastCommandTime = System.currentTimeMillis();
		}
	}

	public static void setTimeToDay() {
		if (System.currentTimeMillis() - lastCommandTime > 150L) {
			sendChatCommand("/time set 0");
			lastCommandTime = System.currentTimeMillis();
		}
	}
	
	public static void toggleGameMode() {
		if (System.currentTimeMillis() - lastCommandTime < 150L)
			return;
		WMLL wmll = WMLL.i;
		Minecraft mc = wmll.getMCInstance();
		if (mc.s != null) 
			return;
		if (!wmll.isCreative())
			sendChatCommand("/gamemode 1 "+wmll.getPlayerName());
		else
			sendChatCommand("/gamemode 0 "+wmll.getPlayerName());
		lastCommandTime = System.currentTimeMillis();	
	}

	public static void onGuiTick() {
		displayDebugInfo();

	}

	public static void displayDebugInfo() {
		WMLL wmll = WMLL.i;
		wmll.drawDebug("Time locked? "+timeLocked+" ("+currentWorldTime+")", 1, 10, 0xffffff);
		wmll.drawDebug("Creative: "+wmll.isCreative(), 1, 11, 0xffffff);
	}

}
