import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.net.SocketAddress;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;

import net.minecraft.client.Minecraft;

import org.lwjgl.input.Keyboard;

import reifnsk.minimap.ReiMinimap;


public class WMLL {

	public static final String WMLLVER = "Stable 19"; //641
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
	
	public boolean wmllOverrideF3;
	public int F3Type;
	public boolean showSeedWithCoords;

	private static final int propertiesVersion = 1;
	private static File settingsFile, outputOptionsFile;
	private static final WMLLUpdateCheck wmllUpdateCheck = new WMLLUpdateCheck();
	private static final Calendar calendar = Calendar.getInstance();

	private WMLLRenderer wmllRenderer;
	private WMLLF3 wmllF3;
	private Minecraft mc;
	private boolean Rei, ReiUseMl;
	private boolean ranInit = false;
	private boolean firstRun = true;
	private final String[] sleepstrings = {"Goodnight, PLAYERNAME!", "This is my bed. There are many like it, but this one is mine.", "If it fits, I sleeps!", "*fade to blackness*", "*water drip*", "Goodnight, Hero!", "ZzzzzZZz", "That'sssssssss a very nice bed you have there...", "That bed looks comfy!", "*snoring*", "...aaaaaannnnddd asleepness!", "Muuuuuuurrrrh", "*clank*", "*screech*"};
	private boolean sleepingStringSet = false;
	private String lightString = "Dat Easter Egg";
	private long lastF4Press = 0;
	private boolean wmllF3Output = false;
	private int updateCheck = 180000;

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
			debugActive = true;
		}
		if (getClass().getClassLoader().getResource("mod_ReiMinimap.class") != null) {
			Rei = true;
			ReiUseMl = ReiMinimap.instance.useModloader;
		}
		debug("[WMLL] Can run debug: "+debugClassPresent);
		debug("[WMLL] Rei's Minimap: "+Rei+" ("+ReiUseMl+")");
	}

	public void updategui(Minecraft h) {
		updateCheck--;
		if (updateCheck <= 0) {
			updateCheck = 180000;
			(new Thread(wmllUpdateCheck)).start();
		}
		if (Rei && !ReiUseMl)
			ReiMinimap.instance.onTickInGame(h);

		if (!ranInit) {
			this.mc = h;
			wmllRenderer = new WMLLRenderer(mc, this);
			wmllF3 = new WMLLF3(mc, this);
			ranInit = true;
			(new Thread(wmllUpdateCheck)).start();
		}
		if (mcDebugOpen() || wmllF3Output) {
			if (mcDebugOpen() && wmllOverrideF3)
				toggleF3Override();
			else if (mcDebugOpen() && !wmllOverrideF3)
				drawStringUsingPixels("WMLL "+WMLLVER, 2, 52, 0xffffff);
			else
				wmllF3.draw();
		}
		else {
			Enabled = Boolean.parseBoolean(options.getProperty("World-"+getWorldName(), "true"));
			if (debugClassPresent)
				WMLLDebug.onGuiTick();
			if (WMLLDebugActive()) {
				int x = getPlayerCoordinates()[0];
				int y = getPlayerCoordinates()[1];
				int z = getPlayerCoordinates()[2];
				int hr = (updateCheck / 50) / 60;
				int mi = (updateCheck / 50) % 60;
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
				String t = "Next update check: "+hr+":"+mi;
				drawDebug(t, (getWindowSize().a() - (getFontRenderer().a(t) + 1)), 7, 0xffffff);
				drawDebug(getPlayerController().toString(),  (getWindowSize().a() - (getFontRenderer().a(getPlayerController().toString()) + 1)), 8, 0xffffff);
				String a = getCalendarDate()+"/"+getCalendarDate(2);
				drawDebug(a, (getWindowSize().a() - (getFontRenderer().a(a) + 1)), 9, 0xffffff);
			}

			WMLLCheckKeys();
			if (!Enabled)
				return;
			// 0 = x, 1 = y, 2 = z, 3 = f
			int[] playerPos = getPlayerCoordinates();
			int light = getLightLevel(playerPos[0], playerPos[1], playerPos[2]);
			int blockLight = getSavedBlockLight(playerPos[0], playerPos[1], playerPos[2]);
			if (isPlayerSleeping()) {
				if (getCalendarDate().equals("66"))
					lightString = "Happy birthday, iPeer!";
				else if (getCalendarDate().equals("243"))
					lightString = "Happy birthday, Roxy!";
				else if (getCalendarDate().equals("202"))
					lightString = "Happy birthday, WMLL!";
				else if (!sleepingStringSet) {
					lightString = sleepstrings[new Random().nextInt(sleepstrings.length)].replaceAll("PLAYERNAME", playerEntity().b);
					sleepingStringSet = true;
				}
			}
			else {
				lightString = generateLightString();
				sleepingStringSet = false;			
			}
			if (WMLLI < 5) {
				if (!isPlayerSleeping() && useImages)
					drawLightImage(light);
				else
					drawString(lightString, 2, 0, 0xffffff);
			}


			// Compass

			if (Arrays.asList(3, 4, 7, 8).contains(WMLLI)) {
				int out = 1;
				if (WMLLI == 8 || WMLLI == 4)
					out = 4;
				acq player = thePlayer();
				double x = player.o;
				double y = player.p;
				double z = player.q;
				double f = gk.c((double)((player.u * 4F) / 360F) + 0.5D) & 3;
				NumberFormat d = new DecimalFormat("#0.00");
				String coords = "("+d.format(x)+", "+d.format(y)+", "+d.format(z)+", "+getPlayerDirection((int)f)+")";
				drawString(coords, 2, out, 0xffffff);
				boolean showSeed = (!isMultiplayer() || isSeedSet()) && showSeedWithCoords;
				if (showSeed)
					drawString("Seed: "+getWorldSeed(), 2, out + 1, 0xffffff);
				//drawString("Facing: "+getPlayerDirection(playerPos[3]), 2, out, 0xffffff);
				drawString("Biome: "+getBiome()+" (T: "+getTemperature()+", H: "+getHumidity()+")", 2, showSeed ? out + 2 : out + 1, 0xffffff);
			}

			// Indicators

			if (Arrays.asList(1, 4, 5, 8).contains(WMLLI)) {
				if (useImages) {
					wmllRenderer.renderIndicatorImages(light, getBlockID(playerPos[0], playerPos[1] - 1, playerPos[2]), getDimension(), canSlimesSpawnHere(playerPos[0], playerPos[2]), canBlockSeeTheSky(playerPos[0], playerPos[1] - 1, playerPos[2]));
					return;
				}
				boolean showSlimes = true;
				if (isMultiplayer())
					 showSlimes = isSeedSet();
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
					if (showSlimes)
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
					drawString("\247a"+labels[4], getDimension() == 1 ? 55 : !showSlimes ? 2 : 40, getDimension() == 1 ? 2 : 3, 0xffffff);
				else
					drawString("\247c"+labels[4], getDimension() == 1 ? 55 : !showSlimes ? 2 : 40, getDimension() == 1 ? 2 : 3, 0xffffff);

			}

			if (Arrays.asList(2, 6).contains(WMLLI))
				drawString(getFPSString(),2, 1, 0xffffff);
		}
		wmllRenderer.tick();

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
		String b = s.replaceAll("%LightLevel%", lightLevel).replaceAll("%BlockLight%", blockLight).replaceAll("%rawlight%", rawLight).replaceAll("%SkyLight%", skyLight).replaceAll("%Biome%", getBiome());	
		if (clockSetting < 3)
			b = b+" ("+getFormattedWorldTime()+")";
		return b;
	}

	private String roundCoord(double i) {
		return new DecimalFormat("#0.00").format(i);
	}

	private xd getWorld() {
		return mc.f;
	}

	public String getWorldName() {
		if (isMultiplayer()) {
			try {
				Object obj = thePlayer();
				Field f = obj.getClass().getDeclaredField("cl"); // sendQueue
				f.setAccessible(true);
				obj = f.get(obj);
				Field f1 = obj.getClass().getDeclaredField("g"); // netManager
				f1.setAccessible(true);
				obj = f1.get(obj);
				Field f2 = obj.getClass().getDeclaredField("i"); // remoteSocketAddress
				f2.setAccessible(true);
				SocketAddress a = (SocketAddress)f2.get(obj);
				String s = a.toString();
				return s.toString().split("/")[0]+":"+s.split(":")[1];	
			}
			catch (Exception e) {
				return e.getMessage();
			}
			
		}	
		return worldInfo().j();
	}

	public nl getFontRenderer() {
		return mc.q;
	}

	public agd getWindowSize() {
		return new agd(mc.A, mc.d, mc.e);
	}

	private boolean mcDebugOpen() {
		return getGameSettings().F;
	}

	private hu getGameSettings() {
		return mc.A;
	}

	public boolean isMultiplayer() {
		return getWorld().F;
	}

	public int getSavedBlockLight(int x, int y, int z) {
		if (y < 0 || y > 255) 
				return 0;
		int[] playerPos = {x, y, z};
		return getChunk(playerPos[0], playerPos[2]).a(wl.a, playerPos[0] & 0xf, playerPos[1], playerPos[2] & 0xf);
	}
	
	public int getRawLightLevel(int x, int y, int z) {
		if (y < 0 || y > 255) 
			return 0;
		int[] playerPos = {x, y, z};
		return getChunk(playerPos[0], playerPos[2]).a(wl.a, playerPos[0] & 0xf, playerPos[1], playerPos[2] & 0xf);
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

	private rs getBiomeGenBase() {
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

	public acq thePlayer() {
		return mc.i;
	}
	
	public fc playerEntity() {
		return mc.k;
	}
	
	public ki getPlayerController() {
		return mc.c;
	}
	public boolean isCreative() {
		return (getPlayerController() instanceof aff);
	}

	public wq worldInfo() {
		return getWorld().x;
	}
	
	protected Minecraft getMCInstance() {
		return mc;
	}

	private ack getChunk(int x, int z) {
		return getWorld().c(x, z);
	}

	private int getDimension() {
		return getWorldProvider().g;
	}

	private boolean canSlimesSpawnHere(int x, int z) {
		if (isMultiplayer()) {
			ack chunk = getChunk(x, z);
			int xPos = chunk.g;
			int zPos = chunk.h;
			return new Random(getWorldSeed() + (long)(xPos * xPos * 0x4c1906) + (long)(xPos * 0x5ac0db) + (long)(zPos * zPos) * 0x4307a7L + (long) (zPos * 0x5f24f) ^ 0x3ad8025f).nextInt(10) == 0;
		}
		return getChunk(x, z).a(0x3ad8025fL).nextInt(10) == 0 && getWorldSeed() != 0L;
	}

	private alb getWorldProvider() {
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
		int[] a = {gk.c(thePlayer().o), gk.c(thePlayer().p - 1), gk.c(thePlayer().q), gk.c((double)((thePlayer().u * 4F) / 360F) + 0.5D) & 3, (int)thePlayer().o, (int)thePlayer().p, (int)thePlayer().q};
		return a;
	}

	public long getWorldSeed() {
		if (isMultiplayer())
			try {
				return Long.parseLong(options.getProperty("Seed:"+getWorldName().toLowerCase(), "0"));
			}
		catch (NumberFormatException n) {
			return 0;
		}
		return getWorld().v();
	}

	public String getFPSString() {
		return mc.M;
	}

	public static boolean WMLLDebugActive() {
		return debugActive;
	}

	public void drawDebug(String t, int x, int y, int c) {
		if (WMLLI > 4)
			y++;
		drawString(t, x, y, c);
	}

	public void drawStringUsingPixels(String t, int x, int y, int c) {
		t = (c == 0xffffff ? "\247"+Integer.toHexString(TextColour) : "")+t;
		getFontRenderer().a(t, x, y, c);
	}

	public void drawString(String t, int i, int j, int k) {
		int textpos = WMLLI > 4 ? -8 : 2;
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
			if (!debugClassPresent)
				options.setProperty("WMLLD", Boolean.toString(debugActive));
			options.setProperty("FirstRun", Boolean.toString(firstRun));
			options.setProperty("version", Integer.toString(propertiesVersion));
			options.setProperty("F4", Integer.toString(F4Key));
			options.setProperty("TextColour", Integer.toString(TextColour));
			options.setProperty("clockSetting", Integer.toString(clockSetting));
			options.setProperty("useImages", Boolean.toString(useImages));
			options.setProperty("OutputLocation", Integer.toString(outputLocation));
			options.setProperty("OverrideIngameF3", Boolean.toString(wmllOverrideF3));
			options.setProperty("showSeedWithCoords", Boolean.toString(showSeedWithCoords));
			options.setProperty("F3Type", Integer.toString(F3Type));
			options.store(new FileOutputStream(settingsFile), "WMLL Config File - Do not edit unless you know what you're doing!");
			if (!outputOptions.isEmpty())
				outputOptions.store(new FileOutputStream(outputOptionsFile), "WMLL's Output Options File - only edit if you know waht you're doing!");
			debug("[WMLL] Options saved.");
			debug(options.toString());
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
			if (!debugClassPresent)
				debugActive = Boolean.parseBoolean(options.getProperty("WMLLD", "false"));
			WMLLI = Integer.parseInt(options.getProperty("WMLLI", "0"));
			useImages = Boolean.parseBoolean(options.getProperty("useImages", "false"));
			TextColour = Integer.parseInt(options.getProperty("TextColour", "15"));
			F4Key = Integer.parseInt(options.getProperty("F4", "62"));
			clockSetting = Integer.parseInt(options.getProperty("clockSetting", "2"));
			outputLocation = Integer.parseInt(options.getProperty("OutputLocation", "0"));
			wmllOverrideF3 = Boolean.parseBoolean(options.getProperty("OverrideIngameF3", "true"));
			F3Type = Integer.parseInt(options.getProperty("F3Type", "0"));
			showSeedWithCoords = Boolean.parseBoolean(options.getProperty("showSeedWithCoords", "true"));
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
/*			if (s.startsWith("[WMLLDebug]")) {
				thePlayer().b(s);
				thePlayer().
				return;
			}*/
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
/*		if (Keyboard.isKeyDown(Keyboard.KEY_G)&& debugClassPresent)
			WMLLDebug.toggleGameMode();*/
		if (Keyboard.isKeyDown(F4Key) && System.currentTimeMillis() - lastF4Press > 150) {
			lastF4Press = System.currentTimeMillis();
			if (Keyboard.isKeyDown(29) && mc.s == null)
				mc.a(new WMLLOptions(this));
			else
				if (!(mc.s instanceof WMLLOptions) && !(mc.s instanceof yf/*GuiChat*/)) {
					if (Keyboard.isKeyDown(42))
						WMLLI--;
					else
						WMLLI++;
					if (WMLLI < 0)
						WMLLI = 9;
					if (WMLLI > 9)
						WMLLI = 0;
					saveOptions();
				}
		}
	}
	
	private void toggleF3Override() {
		wmllF3Output = !wmllF3Output;
		getGameSettings().F = false;
	}
	
	public int getFPSThreshold() {
		return Integer.parseInt(options.getProperty("FPSThreshold", "60"));
	}
	
	public boolean isSeedSet() {
		if (!isMultiplayer())
			return true;
		return options.containsKey("Seed:"+getWorldName().toLowerCase());
	}

	public void displayUpdateString(int ver) {
		wmllRenderer.updateVersion = ver;
		wmllRenderer.notifyUpdate = true;
		
	}
	
	public String getCalendarDate() {
		return getCalendarDate(1);
	}
	
	public String getCalendarDate(int type) {
		if (calendar.getTime() != new Date())
			calendar.setTime(new Date());
		String c = "";
		int a = calendar.get(5);
		int b = calendar.get(2) + 1;
		if (type == 2) {
			int d = calendar.get(1);
			c = Integer.toString(a)+Integer.toString(b)+Integer.toString(d);
		}
		else
			c = Integer.toString(a)+Integer.toString(b);
		return c;
	}

}
