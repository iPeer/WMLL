import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import net.minecraft.client.Minecraft;

import org.lwjgl.input.Keyboard;

import reifnsk.minimap.ReiMinimap;


public class WMLL {

	public static final String WMLLVER = "Stable 14";
	public static final List<Integer> blockBlackList = Arrays.asList(0,8,9,44,20);

	public static WMLL i = new WMLL();
	public static boolean Enabled = true;
	public static int WMLLI;
	public static boolean debugActive;
	public static int F4Key;
	public static int TextColour;
	public static Properties options;
	public static Properties outputOptions;
	public static int outputLocation;
	public static boolean useImages;
	public static int clockSetting;
	public static boolean optionsOpen = false;
	public static int[] playerPos;
	public static boolean militaryclock;
	public static boolean debugClassPresent;

	private static final int propertiesVersion = 1;
	private static File settingsFile, outputOptionsFile;

	private WMLLRenderer wmllRenderer;
	private Minecraft mc;
	private boolean Rei, ReiUseMl;
	private boolean ranInit = false;
	private boolean firstRun = true;
	private final String[] sleepstrings = {"Goodnight, PLAYERNAME!", "This is my bed. There are many like it, but this one is mine.", "If it fits, I sleeps!", "*fade to blackness*", "*water drip*", "Goodnight, Hero!", "ZzzzzZZz", "That'sssssssss a very nice bed you have there...", "That bed looks comfy!", "*snoring*", "...aaaaaannnnddd asleepness!", "Muuuuuuurrrrh", "*clank*", "*screech*"};
	private boolean sleepingStringSet = false;
	private String lightString = "Dat Easter Egg";
	private long lastF4Press = 0;

	public WMLL() {
		debug("[WMLL] Initializing WMLL "+WMLLVER);
		Rei = ReiUseMl = false;
		debugClassPresent = false;
		settingsFile = new File(Minecraft.a("minecraft"), "WMLL.properties");
		outputOptionsFile = new File(Minecraft.a("minecraft"), "WMLLOutput.properties");
		debug("[WMLL] Settings file: "+settingsFile);
		loadOptions();
		if (getClass().getClassLoader().getResource("WMLLDebug.class") != null) {
			debugClassPresent = true;
		}
		if (getClass().getClassLoader().getResource("ReiMinimap.class") != null) {
			Rei = true;
			ReiUseMl = ReiMinimap.instance.useModloader;
		}
		debug("[WMLL] Can run debug: "+debugClassPresent);
		debug("[WMLL] Rei's Minimap: "+Rei+" ("+ReiUseMl+")");
	}

	public void updategui(Minecraft h) {
		if (Rei && !ReiUseMl)
			ReiMinimap.instance.onTickInGame(h);

		if (!ranInit) {
			this.mc = h;
			wmllRenderer = new WMLLRenderer(mc, this);
			ranInit = true;
		}
		if (mcDebugOpen()) {
			drawStringUsingPixels("WMLL "+WMLLVER, 2, 52, 0xffffff);
		}
		else {
			if (debugClassPresent)
				WMLLDebug.onGuiTick();
			if (WMLLDebugActive()) {
				int x = getPlayerCoordinates()[0];
				int y = getPlayerCoordinates()[1];
				int z = getPlayerCoordinates()[2];
				drawDebug(getWorldName()+" ("+isMultiplayer()+")", (getWindowSize().a() - (getFontRenderer().a(getWorldName()+" ("+isMultiplayer()+")") + 1)), 0, 0xffffff);
				drawDebug(Integer.toString(getDimension()), (getWindowSize().a() - (getFontRenderer().a(Integer.toString(getDimension())) + 1)), 1, 0xffffff);
				drawDebug(Boolean.toString(isPlayerSleeping()), (getWindowSize().a() - (getFontRenderer().a(Boolean.toString(isPlayerSleeping())) + 1)), 2, 0xffffff);
				drawDebug(Integer.toString(WMLLI), (getWindowSize().a() - (getFontRenderer().a(Integer.toString(WMLLI)) + 1)), 3, 0xffffff);
				int blockID = getBlockID(getPlayerCoordinates()[0], getPlayerCoordinates()[1] - 1, getPlayerCoordinates()[2]);
				drawDebug(Integer.toString(blockID), (getWindowSize().a() - (getFontRenderer().a(Integer.toString(blockID)) + 1)), 4, 0xffffff);
				try {
					drawDebug(mc.s.toString(), (getWindowSize().a() - (getFontRenderer().a(mc.s.toString()) + 1)), 5, 0xffffff);
				}
				catch (NullPointerException e) {
					drawDebug("null", (getWindowSize().a() - (getFontRenderer().a("null") + 1)), 5, 0xffffff);
				}
				drawDebug(Boolean.toString(canSlimesSpawnHere(x, z)), (getWindowSize().a() - (getFontRenderer().a(Boolean.toString(canSlimesSpawnHere(x, z))) + 1)), 6, 0xffffff);
			}

			WMLLCheckKeys();
			// 0 = x, 1 = y, 2 = z, 3 = f
			int[] playerPos = getPlayerCoordinates();
			int light = getLightLevel(playerPos[0], playerPos[1], playerPos[2]);
			int blockLight = getSavedBlockLight(playerPos[0], playerPos[1], playerPos[2]);
			if (isPlayerSleeping()) {
				if (!sleepingStringSet) {
					lightString = sleepstrings[new Random().nextInt(sleepstrings.length)].replaceAll("PLAYERNAME", playerEntity().b);
					sleepingStringSet = true;
				}
			}
			else {
				lightString = generateLightString();
				sleepingStringSet = false;			
			}
			if (WMLLI < 4) {
				if (!isPlayerSleeping() && useImages)
					drawLightImage(light);
				else
					drawString(lightString, 2, 0, 0xffffff);
			}


			// Compass

			if (Arrays.asList(3, 6).contains(WMLLI)) {
				drawString("X: "+roundCoord(thePlayer().o), 2, 2, 0xffffff);
				drawString("Y: "+roundCoord(thePlayer().p), 2, 3, 0xffffff);
				drawString("Z: "+roundCoord(thePlayer().q), 2, 4, 0xffffff);
				if (!isMultiplayer())
					drawString("Seed: "+getWorldSeed(), 2, 5, 0xffffff);
				drawString("Facing: "+getPlayerDirection(playerPos[3]), 2, 1, 0xffffff);
				drawString("Biome: "+getBiome()+" (T: "+getTemperature()+", H: "+getHumidity()+")", 2, isMultiplayer() ? 5 : 6, 0xffffff);
			}

			// Indicators

			if (Arrays.asList(1, 4).contains(WMLLI)) {
				if (useImages) {
					wmllRenderer.renderIndicatorImages(light, getBlockID(playerPos[0], playerPos[1] - 1, playerPos[2]), getDimension(), canSlimesSpawnHere(playerPos[0], playerPos[2]), canBlockSeeTheSky(playerPos[0], playerPos[1] - 1, playerPos[2]));
					return;
				}
				String[] labels = {"Mobs", "Animals", "Trees", "Crops", "Mushrooms", "Slimes", "Ghasts", "Pigmen", "Blaze", "Endermen"};
				if (getDimension() == -1) { // Nether

					if (!isBlockInBlacklist(getBlockID(playerPos[0], playerPos[1] - 1, playerPos[2]))) {
						drawString("\247a"+labels[6], 2, 1, 0xffffff); // Ghasts
						drawString("\247a"+labels[7], 2, 2, 0xffffff); // Pigmen
						if (light < 13)
							drawString("\247a"+labels[8], 2, 3, 0xffffff); // Blaze
						else
							drawString("\247c"+labels[8], 2, 3, 0xffffff); // Blaze
						drawString("\247a"+labels[5], 2, 4, 0xffffff); // Slimes
					}
					else {
						drawString("\247c"+labels[6], 2, 1, 0xffffff); // Ghasts
						drawString("\247c"+labels[7], 2, 2, 0xffffff); // Pigmen
						drawString("\247c"+labels[8], 2, 3, 0xffffff); // Blaze
						drawString("\247c"+labels[5], 2, 4, 0xffffff); // Slimes
					}




				}
				else if (getDimension() == 1) { // End
					if (light < 7 && !isBlockInBlacklist(getBlockID(playerPos[0], playerPos[1] - 1, playerPos[2])))
						drawString("\247a"+labels[9], 2, 1, 0xffffff);
					else
						drawString("\247c"+labels[9], 2, 1, 0xffffff);
				}
				else { // Normal world

					// Hostiles
					if ((light < 8 && !isBlockInBlacklist(getBlockID(playerPos[0], playerPos[1] - 1, playerPos[2]))) && (getBlockID(playerPos[0], playerPos[1] - 1, playerPos[2]) != 110 && !getBiome().startsWith("MushroomIsland")))
						drawString("\247a"+labels[0], 2, 1, 0xffffff);
					else
						drawString("\247c"+labels[0], 2, 1 , 0xffffff);

					// Animals
					if (playerIsStandingOnBlock(2))
						if (light < 9)
							drawString((!canBlockSeeTheSky(playerPos[0], playerPos[1], playerPos[2]) ? "\247c" : "\247e")+labels[1], 2, 2, 0xffffff);
						else
							drawString("\247a"+labels[1], 2, 2, 0xffffff);
					else
						drawString("\247c"+labels[1], 2, 2, 0xffffff);

					// Slimes
					if (!isMultiplayer())
						if (canSlimesSpawnHere(playerPos[0], playerPos[2]))
							if ((playerPos[1] - 1) <= 40)
								drawString("\247a"+labels[5], 2, 3, 0xffffff);
							else
								drawString("\247e"+labels[5], 2, 3, 0xffffff);
						else
							drawString("\247c"+labels[5], 2, 3, 0xffffff);

				}

				// Crops
				if (playerIsStandingOnBlock(60) && (light > 8 || canBlockSeeTheSky(playerPos[0], playerPos[1], playerPos[2])))
					drawString("\247a"+labels[3], getDimension() == 1 ? 55 : 40, 1, 0xffffff);
				else
					drawString("\247c"+labels[3], getDimension() == 1 ? 55 : 40, 1, 0xffffff);

				// Trees
				if ((playerIsStandingOnBlock(2) || playerIsStandingOnBlock(3)) && (light > 8 || canBlockSeeTheSky(playerPos[0], playerPos[1], playerPos[2])))
					drawString("\247a"+labels[2], getDimension() == 1 ? 2 : 40, 2, 0xffffff);
				else
					drawString("\247c"+labels[2], getDimension() == 1 ? 2 : 40, 2, 0xffffff);

				// Mushrooms
				if ((playerIsStandingOnBlock(110) || light < 13) && !isBlockInBlacklist(getBlockID(playerPos[0], playerPos[1] - 1, playerPos[2])))
					drawString("\247a"+labels[4], getDimension() == 1 ? 55 : isMultiplayer() ? 2 : 40, getDimension() == 1 ? 2 : 3, 0xffffff);
				else
					drawString("\247c"+labels[4], getDimension() == 1 ? 55 : isMultiplayer() ? 2 : 40, getDimension() == 1 ? 2 : 3, 0xffffff);

			}

			if (Arrays.asList(2, 5).contains(WMLLI))
				drawString(getFPSString(),2, 1, 0xffffff);
		}		

	}

	private void drawLightImage(int light) {
		wmllRenderer.renderLightImage(light);
		drawString(Integer.toString(light), 6, 0, 0xffffff);
	}
	
	public String generateLightString() {
		return generateLightString(outputOptions.getProperty("lightString", "Light level: %LightLevel%"));
	}
	
	public String generateLightString(String s) {
		int x = getPlayerCoordinates()[0];
		int y = getPlayerCoordinates()[1];
		int z = getPlayerCoordinates()[2];
		int highlightSky = Integer.parseInt(outputOptions.getProperty("highlightSky", "8"));
		int highlightBlock = Integer.parseInt(outputOptions.getProperty("highlightBlock", "8"));
		int highlightRaw = Integer.parseInt(outputOptions.getProperty("highlightRaw", "8"));
		int highlightLight = Integer.parseInt(outputOptions.getProperty("highlightLight", "8"));
		int a = getLightLevel(x, y, z);
		String lightLevel = (a < highlightLight ? "\247c" : "")+Integer.toString(a)+"\247"+Integer.toHexString(TextColour);
		a = getSavedBlockLight(x, y, z);
		String blockLight = (a < highlightBlock ? "\247c" : "")+Integer.toString(a)+"\247"+Integer.toHexString(TextColour);
		a = getRawLightLevel(x, y, z);
		String rawLight = (a < highlightRaw ? "\247c" : "")+Integer.toString(a)+"\247"+Integer.toHexString(TextColour);
		a = getSkyLight(1.0f);
		String skyLight = (a < highlightSky ? "\247c" : "")+Integer.toString(a)+"\247"+Integer.toHexString(TextColour);
		String b = s.replaceAll("%LightLevel%", lightLevel).replaceAll("%BlockLight%", blockLight).replaceAll("%RawLight%", rawLight).replaceAll("%SkyLight%", skyLight).replaceAll("%Biome%", getBiome());	
		if (clockSetting < 3)
			b = b+" ("+getFormattedWorldTime()+")";
		return b;
	}

	private String roundCoord(double i) {
		return new DecimalFormat("#0.00").format(i);
	}

	private wz getWorld() {
		return mc.f;
	}

	public String getWorldName() {
		return worldInfo().j();
	}

	public ni getFontRenderer() {
		return mc.q;
	}

	public afy getWindowSize() {
		return new afy(mc.A, mc.d, mc.e);
	}

	private boolean mcDebugOpen() {
		return getGameSettings().F;
	}

	private hr getGameSettings() {
		return mc.A;
	}

	public boolean isMultiplayer() {
		return getWorld().F;
	}

	public int getSavedBlockLight(int x, int y, int z) {
		int[] playerPos = getPlayerCoordinates();
		return getChunk(playerPos[0], playerPos[2]).a(wh.b, playerPos[0] & 0xf, playerPos[1], playerPos[2] & 0xf);
	}
	
	public int getRawLightLevel(int x, int y, int z) {
		int[] playerPos = getPlayerCoordinates();
		return getChunk(playerPos[0], playerPos[2]).a(wh.a, playerPos[0] & 0xf, playerPos[1], playerPos[2] & 0xf);
	}
	
	public int getLightLevel(int j, int k, int l) {
		if (k < 0 || k > 255)
			return 0;
		return getWorld().d(j >> 4, l >> 4).c(j & 0xf, k, l & 0xf, getSkyLight(1.0F));
	}

	public int getSkyLight(float f) {
		return getWorld().a(f);
	}

	public String getBiome() {
		int x = getPlayerCoordinates()[0];
		int z = getPlayerCoordinates()[2];
		return getChunk(x, z).a(x & 0xf, z & 0xf, getBiomeGenBase()).y;
	}

	private String getTemperature() {
		return NumberFormat.getPercentInstance().format(getBiomeGenBase().a(getPlayerCoordinates()[0], getPlayerCoordinates()[2]).F);
	}

	private String getHumidity() {
		return NumberFormat.getPercentInstance().format(getBiomeGenBase().a(getPlayerCoordinates()[0], getPlayerCoordinates()[2]).G);
	}

	private ro getBiomeGenBase() {
		return getWorld().i();
	}

	private boolean playerIsStandingOnBlock(int id) {
		int[] coords = getPlayerCoordinates();
		return getBlockID(coords[0], coords[1] - 1, coords[2]) == id;
	}

	private int getBlockID(int x, int y, int z) {

		return getWorld().a(x, y, z);
	}


	private boolean isBlockInBlacklist(int id) {
		return blockBlackList.contains(id);
	}

	private boolean canBlockSeeTheSky(int x, int y, int z) {
		return getWorld().m(x, y, z);
	}

	public vm thePlayer() {
		return mc.h;
	}
	
	public ey playerEntity() {
		return mc.k;
	}

	public wm worldInfo() {
		return getWorld().x;
	}

	private acf getChunk(int x, int z) {
		return getWorld().c(x, z);
	}

	private int getDimension() {
		return getWorldProvider().g;
	}

	private boolean canSlimesSpawnHere(int x, int z) {
		return getChunk(x, z).a(0x3ad8025fL).nextInt(10) == 0 && getWorldSeed() != 0L;
	}

	private akv getWorldProvider() {
		return getWorld().t;
	}

	public long getWorldTime() {
		return worldInfo().f();
	}

	private String getFormattedWorldTime() {
		long a = getWorldTime();
		int h = (int)(((a + 6000L) % 24000L) / 1000L);
		int m = (int)(((a % 1000L) * 60L)  / 1000L);
		String suffix = "AM";
		String out = "00:00";
		if (clockSetting == 2) {
			out = pad(h)+":"+pad(m);
		}
		else {
			if (h >= 12) {
				suffix = "PM";
				h -= 12;
			}
			if (h == 0) {
				h = 12;
			}
			out = pad(h)+":"+pad(m)+" "+suffix;
		}
		return out;
	}

	private String pad(int i) {
		return (i < 10 ? "0"+i : i).toString();
	}

	private boolean isPlayerSleeping() {
		return thePlayer().az();
	}

	public int[] getPlayerCoordinates() {
		int[] a = {gh.c(mc.h.o), gh.c(mc.h.p - 1), gh.c(mc.h.q), gh.c((double)((mc.h.u * 4F) / 360F) + 0.5D) & 3, (int)thePlayer().o, (int)thePlayer().p, (int)thePlayer().q};
		return a;
	}

	public long getWorldSeed() {
		return getWorld().v();
	}

	public String getFPSString() {
		return mc.M;
	}

	public static boolean WMLLDebugActive() {
		return debugActive;
	}

	public void drawDebug(String t, int x, int y, int c) {
		if (WMLLI > 3)
			y++;
		drawString(t, x, y, c);
	}

	public void drawStringUsingPixels(String t, int x, int y, int c) {
		t = (c == 0xffffff ? "\247"+Integer.toHexString(TextColour) : "")+t;
		getFontRenderer().a(t, x, y, c);
	}

	public void drawString(String t, int i, int j, int k) {
		int textpos = WMLLI > 3 ? -8 : 2;
				t = (k == 0xffffff ? "\247"+Integer.toHexString(TextColour) : "")+t;
				int w = getWindowSize().a();
				int h = getWindowSize().b();
				if (outputLocation == 1) {
					getFontRenderer().a(t, w - (getFontRenderer().a(t) + (i - 2)), textpos+(j*10), k);
					return;
				}
				else if (outputLocation == 2) {
					getFontRenderer().a(t, i, h - (textpos+(j*10) + 8), k);
					return;
				}
				else if (outputLocation == 3) {
					getFontRenderer().a(t,  w - (getFontRenderer().a(t) + (i - 2)), h - (textpos+(j*10) + 8), k);
					return;
				}
				getFontRenderer().a(t, i, textpos+(j*10), k);
	}

	public void saveOptions() {
		debug("[WMLL] Attempting to save options...");
		try {
			if (options == null)
				options = new Properties();
			options.setProperty("WMLLI", Integer.toString(WMLLI));
			options.setProperty("WMLLD", Boolean.toString(debugActive));
			options.setProperty("FirstRun", Boolean.toString(firstRun));
			options.setProperty("version", Integer.toString(propertiesVersion));
			options.setProperty("F4", Integer.toString(F4Key));
			options.setProperty("TextColour", Integer.toString(TextColour));
			options.setProperty("clockSetting", Integer.toString(clockSetting));
			options.setProperty("useImages", Boolean.toString(useImages));
			options.setProperty("OutputLocation", Integer.toString(outputLocation));
			options.store(new FileOutputStream(settingsFile), "WMLL Config File - Do not edit unless you know what you're doing!");
			if (!outputOptions.isEmpty())
				outputOptions.store(new FileOutputStream(outputOptionsFile), "WMLL's Output Options File - only edit if you know waht you're doing!");
			debug("[WMLL] Options saved.");
		}
		catch (Exception e) { debug("[WMLL] Unable to save options: "+e.getMessage()); e.printStackTrace(); }
	}

	public void loadOptions() {
		try {
			if (options == null)
				options = new Properties();
			if (outputOptions == null)
				outputOptions = new Properties();
			if (settingsFile.exists())
				options.load(new FileInputStream(settingsFile));
			if (outputOptionsFile.exists())
				outputOptions.load(new FileInputStream(outputOptionsFile));
			firstRun = Integer.parseInt(options.getProperty("version", "0")) < propertiesVersion ? true : false;
			debugActive = Boolean.parseBoolean(options.getProperty("WMLLD", "false"));
			WMLLI = Integer.parseInt(options.getProperty("WMLLI", "0"));
			useImages = Boolean.parseBoolean(options.getProperty("useImages", "false"));
			TextColour = Integer.parseInt(options.getProperty("TextColour", "15"));
			F4Key = Integer.parseInt(options.getProperty("F4", "62"));
			clockSetting = Integer.parseInt(options.getProperty("clockSetting", "2"));
			outputLocation = Integer.parseInt(options.getProperty("OutputLocation", "0"));
			debug("[WMLL] Loaded options.");
			debug(options.toString());
		}
		catch (Exception e) { debug("[WMLL] Unable to load options: "+e.getMessage()); }
	}

	public String getPlayerDirection(int f) {
		switch (f) {
		case 1:
			return "West";
		case 2:
			return "North";
		case 3:
			return "East";
		default:
			return "South";
		}
	}

	public static void toggleDebug() {
		debugActive = !debugActive;
	}

	public void debug(String s) {
		try {
			if (WMLLDebugActive() || s.startsWith("[WMLLDebug]")) {
				thePlayer().b(s);
				return;
			}
			System.out.println(s);
		}
		catch (NullPointerException e) {
			System.out.println(s);
		}
	}

	private void WMLLCheckKeys() {
		if (Keyboard.isKeyDown(Keyboard.KEY_F7) && debugClassPresent)
			if (Keyboard.isKeyDown(42))
				WMLLDebug.toggleTimeLock();
			else
				WMLLDebug.setTimeToNight();
		if (Keyboard.isKeyDown(Keyboard.KEY_F9) && debugClassPresent)
			WMLLDebug.setTimeToDay();
		if (Keyboard.isKeyDown(F4Key) && System.currentTimeMillis() - lastF4Press > 150) {
			lastF4Press = System.currentTimeMillis();
			if (Keyboard.isKeyDown(29) && mc.s == null)
				mc.a(new WMLLOptions(this));
			else
				if (!(mc.s instanceof WMLLOptions) && !(mc.s instanceof yb/*GuiChat*/)) {
					if (Keyboard.isKeyDown(42))
						WMLLI--;
					else
						WMLLI++;
					if (WMLLI < 0)
						WMLLI = 7;
					if (WMLLI > 7)
						WMLLI = 0;
					saveOptions();
				}
		}
	}

}
