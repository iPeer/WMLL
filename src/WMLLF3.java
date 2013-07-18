
import java.text.DecimalFormat;


public class WMLLF3 {
	
	protected ats mc;
	protected WMLL wmll;
	boolean showSeed;
	
	private String MINECRAFT_VERSION = "";

	public WMLLF3(ats h, WMLL wmll) {
		this.mc = h;
		this.wmll = wmll;
		MINECRAFT_VERSION = WMLL.getMinecraftVersion();
	}
	
	public void draw() {
		
		int sw = wmll.getWindowSize().a();
		
		// Memory
		long mm = Runtime.getRuntime().maxMemory();
		long tm = Runtime.getRuntime().totalMemory();
		long fm = Runtime.getRuntime().freeMemory();
		long um = tm - fm;
		String used = "Used: "+Long.toString((um * 100L) / mm)+"%, "+(um / 1024L / 1024L)+"MB/"+(mm / 1024L / 1024L)+"MB";
		String allocated = "Allocated: "+(tm * 100L) / mm+"% ("+(tm / 1024L / 1024L)+"MB)";
		
		wmll.drawStringUsingPixels(used, sw - wmll.getFontRenderer().a(used) - 2, 2, 0xffffff);
		
		wmll.drawStringUsingPixels(allocated, sw - wmll.getFontRenderer().a(allocated) - 2, 12, 0xffffff);
		
		// Minecraft version
		wmll.drawStringUsingPixels("Minecraft "+MINECRAFT_VERSION, 2, 2, 0xffffff);
		
		if (wmll.F3Type == 1) {
			
		}
		else {
		
		// FPS & Chunk Updates
		int col = 15;
		int fps = Integer.parseInt(wmll.getFPSString().split(" ")[0]);
		if (fps < wmll.getFPSThreshold())
			col = 12;
		wmll.drawStringUsingPixels("\247"+Integer.toHexString(col)+wmll.getFPSString(), 2, 12, 0xffffff);
		
		// Particles, entities, etc.
		
		wmll.drawStringUsingPixels(mc.l(), 2, 32, 0xffffff);
		wmll.drawStringUsingPixels(mc.m()/*.replaceAll("E:", "Entities:")*/, 2, 42, 0xffffff);
		wmll.drawStringUsingPixels(mc.o()/*.replaceAll("P:", "Particles:")*/, 2, 52, 0xffffff);
		wmll.drawStringUsingPixels(mc.n(), 2, 62, 0xffffff);
		
		// WMLL Version
		wmll.drawStringUsingPixels("WMLL "+WMLL.wmllVersion(), 2, 72, 0xffffff);
		
		// Coordinates
		oe player = wmll.thePlayer();
		double x = player.t;
		double y = player.u;
		double z = player.v;
		double f = lr.c((double)((player.z * 4F) / 360F) + 0.5D) & 3;
		String roundingFormat = "#0";
		DecimalFormat d = new DecimalFormat(roundingFormat);
		String coords = "("+d.format(x)+", "+d.format(y)+", "+d.format(z)+", "+wmll.getPlayerDirection(Integer.parseInt(d.format(f)))+")";
		
		wmll.drawStringUsingPixels(coords, 2, 92, 0xffffff);
		
		// Seed
		showSeed = (!wmll.isMultiplayer() && wmll.isSeedSet()) || wmll.showSeedWithCoords;
		if (showSeed)
			wmll.drawStringUsingPixels("Seed: "+wmll.getWorldSeed(), 2, 112, 0xffffff);
		}
		int[] coords = wmll.getPlayerCoordinates();
		int x = coords[0];
		int y = coords[1];
		int z = coords[2];
		int lightA = wmll.getLightLevel(x, y, z);
		int lightB = wmll.getLightLevel(x, y+1, z);
		
		wmll.drawStringUsingPixels("\247nLight levels", 2, showSeed ? 132 : 112, 0xffffff);
		wmll.drawStringUsingPixels("Head: "+lightB, 2, showSeed ? 142 : 122, 0xffffff);
		wmll.drawStringUsingPixels("Feet: "+lightA, 2, showSeed ? 152 : 132, 0xffffff);
		
	}
	
}
