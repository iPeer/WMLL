import java.text.DecimalFormat;

import net.minecraft.client.Minecraft;


public class WMLLF3 {
	
	protected Minecraft mc;
	protected WMLL wmll;
	
	private final String MINECRAFT_VERSION = "1.2.3";

	public WMLLF3(Minecraft mc, WMLL wmll) {
		this.mc = mc;
		this.wmll = wmll;
	}
	
	public void draw() {
		
		int sw = wmll.getWindowSize().a();
		int ch = wmll.getWindowSize().b();
		
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
		
		wmll.drawStringUsingPixels(mc.m(), 2, 32, 0xffffff);
		wmll.drawStringUsingPixels(mc.n()/*.replaceAll("E:", "Entities:")*/, 2, 42, 0xffffff);
		wmll.drawStringUsingPixels(mc.p()/*.replaceAll("P:", "Particles:")*/, 2, 52, 0xffffff);
		wmll.drawStringUsingPixels(mc.o(), 2, 62, 0xffffff);
		
		// WMLL Version
		wmll.drawStringUsingPixels("WMLL "+WMLL.WMLLVER, 2, 72, 0xffffff);
		
		// Coordinates
		vm player = wmll.thePlayer();
		double x = player.o;
		double y = player.p;
		double z = player.q;
		double f = gh.c((double)((player.u * 4F) / 360F) + 0.5D) & 3;
		String roundingFormat = "#0";
		DecimalFormat d = new DecimalFormat(roundingFormat);
		String coords = "("+d.format(x)+", "+d.format(y)+", "+d.format(z)+", "+wmll.getPlayerDirection(Integer.parseInt(d.format(f)))+")";
		
		wmll.drawStringUsingPixels(coords, 2, 92, 0xffffff);
		
		// Seed
		if (!wmll.isMultiplayer())
			wmll.drawStringUsingPixels("Seed: "+wmll.getWorldSeed(), 2, 112, 0xffffff);
		}
		
	}
	
}
