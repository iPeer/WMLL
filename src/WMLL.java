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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.client.Minecraft;

import org.lwjgl.input.Keyboard;

import reifnsk.minimap.ReiMinimap;


public class WMLL {

	public static final String wmllVersion() {
		return "Stable 26";
	}
	public static final List<Integer> blockBlackList = Arrays.asList(0,8,7,9,44,20);
	public static final Map<String, String> fieldNames = new HashMap<String, String>();
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
	public static boolean debugClassPresent = false;
	public static boolean militaryclock;
	public String worldNameDebug = "";

	public boolean wmllOverrideF3;
	public int F3Type;
	public boolean showSeedWithCoords;

	private static final int propertiesVersion = 3;
	public static File settingsFile, outputOptionsFile;
	public static long lastUpdateCheck = 0;
	private static final WMLLUpdateCheck wmllUpdateCheck = new WMLLUpdateCheck();
	private static final Calendar calendar = Calendar.getInstance();

	private WMLLRenderer wmllRenderer;
	private WMLLF3 wmllF3;
	private Minecraft mc;
	private boolean Rei, ReiUseMl, RadarBro;
	private boolean ranInit = false;
	private boolean firstRun = true;
	private final String[] sleepstrings = {"Goodnight, PLAYERNAME!", "This is my bed. There are many like it, but this one is mine.", "If it fits, I sleeps!", "*fade to blackness*", "*water drip*", "Goodnight, Hero!", "ZzzzzZZz", "That'sssssssss a very nice bed you have there...", "That bed looks comfy!", "*snoring*", "...aaaaaannnnddd asleepness!", "Muuuuuuurrrrh", "*clank*", "*screech*"};
	private boolean sleepingStringSet = false;
	private String lightString = "Light level: 9001";
	private long lastF4Press = 0;
	private boolean wmllF3Output = false;
	private aou fontRenderer;

	protected WMLLCompatibility wmllCompatibility;

	public WMLL() {
		debug("[WMLL] Initializing WMLL "+wmllVersion());
		fieldNames.put("sendQueue", "i");
		fieldNames.put("netManager", "g");
		fieldNames.put("remoteSocketAddress", "j");
		fieldNames.put("SPremoteSocketAddress", "a");
		fieldNames.put("genNetherBridge", "c");
		fieldNames.put("SpawnListEntry", "a");
		fieldNames.put("localServerWorldName", "b");
		fieldNames.put("worldSeed", "a");
		Rei = ReiUseMl = RadarBro = false;
		settingsFile = new File(Minecraft.a("minecraft"), "WMLL.properties");
		outputOptionsFile = new File(Minecraft.a("minecraft"), "WMLLOutput.properties");
		wmllCompatibility = new WMLLCompatibility();
		debug("[WMLL] Settings file: "+settingsFile);
		loadOptions();
		try {
			debugClassPresent = !(WMLL.class.getProtectionDomain().getCodeSource().getLocation().getPath()).endsWith(".jar");
		}
		catch (Exception e1) { }
		if (getClass().getClassLoader().getResource("mod_ReiMinimap.class") != null) {
			Rei = true;
			ReiUseMl = ReiMinimap.instance.useModloader;
		}
		if (getClass().getClassLoader().getResource("RadarBro.class") != null) {
			RadarBro = true;
		}
		if (debugClassPresent)
			RadarBro = false;
		debug("[WMLL] Can run debug: "+debugClassPresent);
		debug("[WMLL] Rei's Minimap: "+Rei+" ("+ReiUseMl+")");
		debug("[WMLL] RadarBro: "+RadarBro);
	}

	public void updategui(Minecraft h) {

		if (getWorld() != null && !wmllUpdateCheck.running) {
			wmllUpdateCheck.start();
		}
		else if (getWorld() == null && wmllUpdateCheck.running) {
			System.out.println("[WMLL] FATAL: World == NULL! -- Stopping update thread.");
			wmllUpdateCheck.stop1();
		}
		if (firstRun && getWorld() != null) {
			firstRun = false;
			wmllRenderer.firstRun = true;
		}
		if (Rei && !ReiUseMl)
			ReiMinimap.instance.onTickInGame(h);

		if (!ranInit) {
			this.mc = h;
			wmllRenderer = new WMLLRenderer(mc, this);
			wmllF3 = new WMLLF3(mc, this);
			ranInit = true;
			this.fontRenderer = h.p;
			(new Thread(wmllUpdateCheck)).start();
		}
		if (mcDebugOpen() || wmllF3Output) {
			if (mcDebugOpen() && wmllOverrideF3)
				toggleF3Override();
			else if (mcDebugOpen() && !wmllOverrideF3)
				drawStringUsingPixels("WMLL "+wmllVersion(), 2, 52, 0xffffff);
			else
				wmllF3.draw();
		}
		else {
			if (RadarBro)
				try {
					wmllCompatibility.RadarBroRun(mc, this);
				}
			catch (NoSuchMethodError n) { }
			catch (NoClassDefFoundError n1) { }
			catch (NoSuchFieldError n2) { }
			Enabled = Boolean.parseBoolean(options.getProperty("World-"+getWorldName(), "true")) && !Boolean.parseBoolean(options.getProperty("AllOutputsOff", "false"));
			if (WMLLDebugActive()) {
				int x = getPlayerCoordinates()[0];
				int z = getPlayerCoordinates()[2];
				String worldName = getWorldName()+" ("+isMultiplayer()+")";
				drawDebug(worldName, (getWindowSize().a() - (getFontRenderer().a(worldName) + 1)), 0, 0xffffff);
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

				long nextUpdate = ((lastUpdateCheck + 3600000) - System.currentTimeMillis()) / 1000;
				int hours = (int)(nextUpdate / 3600);
				int seconds = (int)nextUpdate % 60;
				int minutes = (int)(nextUpdate % 3600) / 60;
				String update = pad(hours)+":"+pad(minutes)+":"+pad(seconds);
				String t = "Next update check: "+update;
				drawDebug(t, (getWindowSize().a() - (getFontRenderer().a(t) + 1)), 7, 0xffffff);
				drawDebug(getPlayerController().toString(),  (getWindowSize().a() - (getFontRenderer().a(getPlayerController().toString()) + 1)), 8, 0xffffff);
				String a = getCalendarDate()+"/"+getCalendarDate(2);
				drawDebug(a, (getWindowSize().a() - (getFontRenderer().a(a) + 1)), 9, 0xffffff);
				drawDebug(Boolean.toString(isSeedSet()), (getWindowSize().a() - (getFontRenderer().a(Boolean.toString(isSeedSet())) + 1)), 10, 0xffffff);
				a = getChunkProvider().toString();
				drawDebug(a, (getWindowSize().a() - (getFontRenderer().a(a) + 1)), 12, 0xffffff);
				a = worldNameDebug;
				drawDebug(a, (getWindowSize().a() - (getFontRenderer().a(a) + 1)), 11, 0xffffff);
				a = "S: "+canBlockSeeTheSky(x, getPlayerCoordinates()[1], z);
				drawDebug(a, (getWindowSize().a() - (getFontRenderer().a(a) + 1)), 13, 0xffffff);
				a = lightString+", "+lightString.contains("\\n");
				drawDebug(a, (getWindowSize().a() - (getFontRenderer().a(a) + 1)), 14, 0xffffff);
			}
			WMLLCheckKeys();
			if (!Enabled)
				return;
			// 0 = x, 1 = y, 2 = z, 3 = f
			int[] playerPos = getPlayerCoordinates();
			int light = getLightLevel(playerPos[0], playerPos[1], playerPos[2]);
			if (isPlayerSleeping()) {
				if (getCalendarDate().equals("66")) // My birthday
					lightString = "Happy birthday, iPeer!";
				else if (getCalendarDate().equals("243")) // Roxy's birthday (<3)
					lightString = "Happy birthday, Roxy!";
				else if (getCalendarDate().equals("202")) // WMLL's birthday
					lightString = "Happy birthday, WMLL!";
				else if (getCalendarDate().equals("84")) // Easy Sunday
					lightString = "Happy Easter!";
				else if (getCalendarDate().equals("2512")) // Christmas Day
					lightString = "Why are you playing Minecraft on Christmas Day?";
				else if (getCalendarDate().equals("11")) // New Year
					lightString = "Happy New Year!";
				else if (getCalendarDate().equals("3110")) // Halloween
					lightString = "Happy Halloween! WoooOOOoooOOoooO!";
				else if (getCalendarDate().equals("31")) // Millie <3 RIP, honey.
					lightString = "Millie <3";
				else if (getCalendarDate().equals("23")) {// Roxy and I's anniversary
					String a = getCalendarDate(2);
					int now = Integer.parseInt(a.substring(a.length() - 4));
					int years = now-2007;
					lightString = years+" years today!";
				}
				else if (!sleepingStringSet) {
					lightString = sleepstrings[new Random().nextInt(sleepstrings.length)].replaceAll("PLAYERNAME", getPlayerName());
					sleepingStringSet = true;
				}
			}
			else {
				lightString = generateLightString();
				sleepingStringSet = false;			
			}
			if (WMLLI < 6) {
				if (!isPlayerSleeping() && useImages)
					drawLightImage(light);
				else
					drawString(lightString, 2, 0, 0xffffff);
			}


			// Compass

			if (Arrays.asList(3, 4, 5, 8, 9, 10).contains(WMLLI)) {
				int out = 1;
				if (WMLLI == 9 || WMLLI == 4) {
					out = isMultiplayer() && !isSeedSet() ? 3 : 4;
					if (getDimension() == 1)
						out--;
				}
				else if (WMLLI == 8 || WMLLI == 3)
					out = 0;
				else if (WMLLI == 10 || WMLLI == 5) 
					out = 1;
				if ((isSeedSet()) || isMultiplayer())
					out++;
				//				if (getDimension() == 1)
				//					out--;
				jv player = thePlayer();
				double x = player.t;
				double y = player.u;
				double z = player.v;
				double f = ig.c((double)((player.z * 4F) / 360F) + 0.5D) & 3;
				NumberFormat d = new DecimalFormat("#0.00");
				String coords = "("+d.format(x)+", "+d.format(y)+", "+d.format(z)+", "+getPlayerDirection((int)f)+")";
				drawString(coords, 2, out, 0xffffff);
				if (WMLLI != 5 && WMLLI != 10) {
					boolean showSeed = (!isMultiplayer() || isSeedSet()/* || getWorldName().equals("localServer")*/) && showSeedWithCoords;
					if (showSeed)
						drawString("Seed: "+getWorldSeed(), 2, out + 1, 0xffffff);
					//drawString("Facing: "+getPlayerDirection(playerPos[3]), 2, out, 0xffffff);
					drawString("Biome: "+getBiome()+" (T: "+getTemperature()+", H: "+getHumidity()+")", 2, showSeed ? out + 2 : out + 1, 0xffffff);
				}
			}

			// Indicators

			if (Arrays.asList(1, 4, 6, 9).contains(WMLLI)) {
				if (useImages) {
					wmllRenderer.renderIndicatorImages(light, getBlockID(playerPos[0], playerPos[1] - 1, playerPos[2]), getDimension(), canSlimesSpawnHere(playerPos[0], playerPos[2]), canBlockSeeTheSky(playerPos[0], playerPos[1] - 1, playerPos[2]));
					return;
				}
				boolean showSlimes = true;
				if (isMultiplayer())
					showSlimes = isSeedSet();
				String[] labels = {"Mobs", "Animals", "Trees", "Crops", "Mushrooms", "Slimes", "Ghasts", "Pigmen", "Blaze", "Endermen", "Grass"};
				if (getDimension() == -1) { // Nether

					if (!isBlockInBlacklist(getBlockID(playerPos[0], playerPos[1] - 1, playerPos[2]))) {
						drawString("\247a"+labels[6], 2, 1, 0xffffff); // Ghasts
						drawString("\247a"+labels[7], 2, 2, 0xffffff); // Pigmen
						if (light < 12)
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
					if (showSlimes) {
						if (canSlimesSpawnHere(playerPos[0], playerPos[2]))
							if ((playerPos[1] - 1) <= 40)
								drawString("\247a"+labels[5], 2, 3, 0xffffff);
							else
								drawString("\247e"+labels[5], 2, 3, 0xffffff);
						else
							drawString("\247c"+labels[5], 2, 3, 0xffffff);
					}

				}

				// Crops
				if (playerIsStandingOnBlock(60) && (getLightLevel(playerPos[0], playerPos[1] + 1, playerPos[2]) > 8 || canBlockSeeTheSky(playerPos[0], playerPos[1], playerPos[2])))
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
					drawString("\247a"+labels[4], getDimension() == 0 ? 40 : 40, getDimension() == 1 ? 2 : 3, 0xffffff);
				else
					drawString("\247c"+labels[4], getDimension() == 0 ? 40 : 40, getDimension() == 1 ? 2 : 3, 0xffffff);

				// Grass
				if ((playerIsStandingOnBlock(3) && light > 8))
					drawString("\247a"+labels[10], getDimension() == -1 ? 40 : 2, getDimension() == 1 ? 3 : getDimension() == -1 ? 4 : !showSlimes ? 3 : 4, 0xffffff);
				else
					drawString("\247c"+labels[10], getDimension() == -1 ? 40 : 2, getDimension() == 1 ? 3 : getDimension() == -1 ? 4 : !showSlimes ? 3 : 4, 0xffffff);

			}

			if (Arrays.asList(2, 5, 7, 10).contains(WMLLI))
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

	public String generateLightString(String s) { // [Roxy] Now Case insensitive
		int x = getPlayerCoordinates()[0];
		int y = getPlayerCoordinates()[1];
		int z = getPlayerCoordinates()[2];
		double x1 = getPlayerCoordinatesAsDouble()[0];
		double y1 = getPlayerCoordinatesAsDouble()[1];
		double z1 = getPlayerCoordinatesAsDouble()[2];
		int highlightSky = Integer.parseInt(outputOptions.getProperty("highlightSky", "8"));
		int highlightBlock = Integer.parseInt(outputOptions.getProperty("highlightBlock", "8"));
		int highlightRaw = Integer.parseInt(outputOptions.getProperty("highlightRaw", "8"));
		int highlightLight = Integer.parseInt(outputOptions.getProperty("highlightLight", "8"));
		int a = getLightLevel(x, y, z);
		Pattern SkyLight = Pattern.compile("%skylight%", Pattern.CASE_INSENSITIVE);
		Pattern BlockLight = Pattern.compile("%blocklight%", Pattern.CASE_INSENSITIVE);
		Pattern RawLight = Pattern.compile("%rawlight%", Pattern.CASE_INSENSITIVE);
		Pattern Light = Pattern.compile("%LightLevel%", Pattern.CASE_INSENSITIVE);
		Pattern Biome = Pattern.compile("%Biome%", Pattern.CASE_INSENSITIVE);
		Pattern FPS = Pattern.compile("%fps%", Pattern.CASE_INSENSITIVE);
		Pattern FPS_noCU = Pattern.compile("%fps2%", Pattern.CASE_INSENSITIVE);
		Pattern fullCompass = Pattern.compile("%fullcompass%", Pattern.CASE_INSENSITIVE);
		Pattern chunkUpdates = Pattern.compile("%(cu|chunkupdates)%", Pattern.CASE_INSENSITIVE);
		Pattern heading = Pattern.compile("%heading%", Pattern.CASE_INSENSITIVE);
		Pattern fx = Pattern.compile("%fx%", Pattern.CASE_INSENSITIVE);
		Pattern fy = Pattern.compile("%fy%", Pattern.CASE_INSENSITIVE);
		Pattern fz = Pattern.compile("%fz%", Pattern.CASE_INSENSITIVE);
		Pattern cx = Pattern.compile("%cx%", Pattern.CASE_INSENSITIVE);
		Pattern cy = Pattern.compile("%cy%", Pattern.CASE_INSENSITIVE);
		Pattern cz = Pattern.compile("%cz%", Pattern.CASE_INSENSITIVE);
		String lightLevel = (a < highlightLight ? "\247c" : "")+Integer.toString(a)+"\247"+Integer.toHexString(TextColour);
		a = getSavedBlockLight(x, y, z);
		String blockLight = (a < highlightBlock ? "\247c" : "")+Integer.toString(a)+"\247"+Integer.toHexString(TextColour);
		a = getRawLightLevel(x, y, z);
		String rawLight = (a < highlightRaw ? "\247c" : "")+Integer.toString(a)+"\247"+Integer.toHexString(TextColour);
		a = getSkyLight(1.0f);
		String skyLight = (a < highlightSky ? "\247c" : "")+Integer.toString(a)+"\247"+Integer.toHexString(TextColour);
		Matcher m = SkyLight.matcher(s);
		s = m.replaceAll(skyLight);
		m = BlockLight.matcher(s);
		s = m.replaceAll(blockLight);
		m = RawLight.matcher(s);
		s = m.replaceAll(rawLight);
		m = Light.matcher(s);
		s = m.replaceAll(lightLevel);
		m = Biome.matcher(s);
		s = m.replaceAll(getBiome());
		m = FPS.matcher(s);
		s = m.replaceAll(getFPSString());
		m = FPS_noCU.matcher(s);
		s = m.replaceAll(getFPSString().split(",")[0]);
		m = chunkUpdates.matcher(s);
		s = m.replaceAll(getFPSString().split(",")[1].substring(1));
		m = fx.matcher(s);
		s = m.replaceAll(Integer.toString((int)Math.floor(x1)));
		m = fy.matcher(s);
		s = m.replaceAll(Integer.toString((int)Math.floor(y1)));
		m = fz.matcher(s);
		s = m.replaceAll(Integer.toString((int)Math.floor(z1)));
		m = cx.matcher(s);
		s = m.replaceAll(Integer.toString((int)Math.ceil(x1)));
		m = cy.matcher(s);
		s = m.replaceAll(Integer.toString((int)Math.ceil(y1)));
		m = cz.matcher(s);
		s = m.replaceAll(Integer.toString((int)Math.ceil(z1)));
		int f1 = getPlayerCoordinates()[3];
		NumberFormat n = new DecimalFormat("#0.00");
		String coords = "("+n.format(x1)+", "+n.format(y1)+", "+n.format(z1)+", "+getPlayerDirection((int)f1)+")";
		m = fullCompass.matcher(s);
		s = m.replaceAll(coords);
		m = heading.matcher(s);
		s = m.replaceAll(getPlayerDirection((int)f1));
		Pattern coordsX = Pattern.compile("%x%", Pattern.CASE_INSENSITIVE);
		m = coordsX.matcher(s);
		s = m.replaceAll(n.format(x1));
		Pattern coordsY = Pattern.compile("%y%", Pattern.CASE_INSENSITIVE);
		m = coordsY.matcher(s);
		s = m.replaceAll(n.format(y1));
		Pattern coordsZ = Pattern.compile("%z%", Pattern.CASE_INSENSITIVE);
		m = coordsZ.matcher(s);
		s = m.replaceAll(n.format(z1));
		//String b = s.replaceAll("%LightLevel%", lightLevel).replaceAll("%BlockLight%", blockLight).replaceAll("%RawLight%", rawLight).replaceAll("%SkyLight%", skyLight).replaceAll("%Biome%", getBiome());	
		if (clockSetting < 3)
			s = s+" ("+getFormattedWorldTime()+")";
		return s;
	}

	//	private act serverInstance() {
	//		return getChunk(0 ,0).e;
	//	}

	private atc getWorld() {
		try {
			return mc.e;
		}
		catch (NullPointerException n) {
			return null;
		}
	}

	public String getWorldName() {
		try {
			Object obj = thePlayer();
			Field f = obj.getClass().getDeclaredField("a"); // sendQueue
			f.setAccessible(true);
			obj = f.get(obj);
			worldNameDebug = obj.toString();
			Field f1 = obj.getClass().getDeclaredField(getField("netManager")); // netManager
			f1.setAccessible(true);
			obj = f1.get(obj);
			worldNameDebug = worldNameDebug+", "+obj.toString();
			Field f2;
			if (!isMultiplayer())
				f2 = obj.getClass().getDeclaredField(getField("SPremoteSocketAddress")); // remoteSocketAddress

			else
				f2 = obj.getClass().getDeclaredField(getField("remoteSocketAddress"));
			f2.setAccessible(true);
			SocketAddress a = (SocketAddress)f2.get(obj);
			String s = a.toString();
			String server = s.split("/")[0].split(":")[0];
			if (server == null || server.equals(""))
				server = s.split("/")[1].split(":")[0];
			String port = s.split(":")[1];
			if (!isMultiplayer())
				return "localServer";
			return server+":"+port;	
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}

	//	public String worldName() {
	//		return worldInstance().m()+", "+worldInstance().n();
	//	}

	public aou getFontRenderer() {
		return this.fontRenderer;
	}

	public apn getWindowSize() {
		return new apn(mc.y, mc.c, mc.d);
	}

	private boolean mcDebugOpen() {
		return getGameSettings().P;
	}

	private any getGameSettings() {
		return mc.y;
	}

	public boolean isMultiplayer() {
		return !mc.B();
	}

	public int getSavedBlockLight(int x, int y, int z) {
		if (y < 0 || y > 255) 
			return 0;
		int[] playerPos = {x, y, z};
		return getChunk(playerPos[0], playerPos[2]).a(va.a, playerPos[0] & 0xf, playerPos[1], playerPos[2] & 0xf);
	}

	public int getRawLightLevel(int x, int y, int z) {
		if (y < 0 || y > 255) 
			return 0;
		int[] playerPos = {x, y, z};
		return getChunk(playerPos[0], playerPos[2]).a(va.a, playerPos[0] & 0xf, playerPos[1], playerPos[2] & 0xf);
	}

	public int getBlockLight (int i, int j, int k) {
		if (j < 0 || j > 255)
			return 0;
		return getChunk(i, k).a(va.b, i & 0xf, j, k & 0xf);
	}

	@Deprecated
	public int getLightLevel(int j, int k, int l) {
		if (k < 0 || k > 255)
			return 0;
		return getChunk(j, l).c(j & 0xf, k, l & 0xf, getSkyLight(1.0f));
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

	private vo getBiomeGenBase() {
		return getWorld().q();
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
		return getWorld().j(x, y, z);
	}

	public jv entityPlayer() {
		return mc.h;
	}

	public jv thePlayer() {
		return mc.h;
	}

	public aof playerEntity() {
		return mc.j;
	}

	public String getPlayerName() {
		return playerEntity().b;
	}

	public atb getPlayerController() {
		return mc.b;
	}

	public boolean isCreative() {
		return !getPlayerController().b();
	}

	public aec worldInfo() {
		return getWorld().A;
	}

	protected Minecraft getMCInstance() {
		return mc;
	}

	private wk getChunk(int x, int z) {
		return getWorld().d(x, z);
	}

	private int getDimension() {
		return getWorldProvider().g;
	}

	private boolean canSlimesSpawnHere(int x, int z) {
		if (isMultiplayer()) {
			wk chunk = getChunk(x, z);
			int xPos = chunk.g;
			int zPos = chunk.h;
			return new Random(getWorldSeed() + (long)(xPos * xPos * 0x4c1906) + (long)(xPos * 0x5ac0db) + (long)(zPos * zPos) * 0x4307a7L + (long) (zPos * 0x5f24f) ^ 0x3ad8025f).nextInt(10) == 0;
		}
		return getChunk(x, z).a(0x3ad8025fL).nextInt(10) == 0 && getWorldSeed() != 0L;
	}

	private xb getWorldProvider() {
		return getWorld().w;
	}

	private wh getChunkProvider() {
		return getWorldProvider().c();
	}

	public long getWorldTime() {
		return getWorld().D();
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
		return thePlayer().bn();
	}

	public int[] getPlayerCoordinates() {
		int[] a = {ig.c(thePlayer().t), ig.c(thePlayer().u - 1), ig.c(thePlayer().v), ig.c((double)((thePlayer().z * 4F) / 360F) + 0.5D) & 3, (int)thePlayer().t, (int)thePlayer().u, (int)thePlayer().v};
		return a;
	}

	public double[] getPlayerCoordinatesAsDouble() {
		double[] a = {thePlayer().t, thePlayer().u, thePlayer().v, ig.c((double)((thePlayer().z * 4F) / 360F) + 0.5D) & 3};
		return a;
	}

	public long getWorldSeed() {
		//		if (getWorldName().equals("localServer"))
		//			((ajv)thePlayer()).o.t();
		//		else {
		try {
			return Long.parseLong(options.getProperty("Seed:"+getWorldName().toLowerCase(), "0"));
		}
		catch (NumberFormatException n) {
			return 0L;
		}
		catch (NullPointerException n1) {
			return 0L;
		}
		//}
		//return getChunk(0, 0).e.x.c().b();
	}

	public String getFPSString() {
		return mc.K;
	}

	public static boolean WMLLDebugActive() {
		return debugActive;
	}

	public void drawDebug(String t, int x, int y, int c) {
		if (WMLLI > 5)
			y++;
		drawString(t, x, y, c);
	}

	public void drawStringUsingPixels(String t, int x, int y, int c) {
		t = (c == 0xffffff ? "\247"+Integer.toHexString(TextColour) : "")+t;
		getFontRenderer().a(t, x, y, c);
	}

	public void drawString(String t, int i, int j, int k) {
		int textpos = WMLLI > 5 ? -8 : 2;
		t = (k == 0xffffff ? "\247"+Integer.toHexString(TextColour) : "")+t;
		String t1 = Pattern.compile("\247[0-9a-f,l-o,r]").matcher(t).replaceAll("");
		int w = getWindowSize().a();
		int h = getWindowSize().b();
		if (t.contains("\\n")) {
			String[] lines = t.split("\\\\n");
			int l = 0;
			for (String a : lines) {
				getFontRenderer().a(a, i, textpos+((j*10)+(10*l++)), k);
			}
			return;
		}
		if (outputLocation == 1) { // Top right
			getFontRenderer().a(t, w - (getFontRenderer().a(t1) + (i - 1)), textpos+(j*10), k);
			return;
		}
		else if (outputLocation == 2) { // Bottom Left
			getFontRenderer().a(t, i, h - (textpos+(j*10) + 8), k);
			return;
		}
		else if (outputLocation == 3) { // Bottom Right
			getFontRenderer().a(t,  w - (getFontRenderer().a(t1) + (i - 1)), h - (textpos+(j*10) + 8), k);
			return;
		}
		getFontRenderer().a(t, i, textpos+(j*10), k); // Top Left
	}

	public void saveOptions() {
		debug("[WMLL] Attempting to save options...");
		try {
			if (options == null)
				options = new Properties();
			options.setProperty("WMLLI", Integer.toString(WMLLI));
			//			if (!debugClassPresent)
			//				options.setProperty("WMLLD", Boolean.toString(debugActive));
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
			debug(options.toString()+"\n"+outputOptions.toString());
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
			//			if (!debugClassPresent)
			//				debugActive = Boolean.parseBoolean(options.getProperty("WMLLD", "false"));
			WMLLI = Integer.parseInt(options.getProperty("WMLLI", "0"));
			useImages = Boolean.parseBoolean(options.getProperty("useImages", "false"));
			TextColour = Integer.parseInt(options.getProperty("TextColour", "15"));
			F4Key = Integer.parseInt(options.getProperty("F4", "62"));
			clockSetting = Integer.parseInt(options.getProperty("clockSetting", "2"));
			outputLocation = Integer.parseInt(options.getProperty("OutputLocation", "0"));
			wmllOverrideF3 = Boolean.parseBoolean(options.getProperty("OverrideIngameF3", "false")); // Now defaults to false due to Minecraft's native Shift + F3
			F3Type = Integer.parseInt(options.getProperty("F3Type", "0"));
			showSeedWithCoords = Boolean.parseBoolean(options.getProperty("showSeedWithCoords", "true"));
			debug("[WMLL] Loaded options.");
			debug(options.toString()+"\n"+outputOptions.toString());
			if (firstRun)
				saveOptions();
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
		System.out.println(s);
	}

	private void WMLLCheckKeys() {
		if (Keyboard.isKeyDown(F4Key) && System.currentTimeMillis() - lastF4Press > 150) {
			lastF4Press = System.currentTimeMillis();
			if (Keyboard.isKeyDown(29) && mc.r == null)
				mc.a(new WMLLOptions(this));
			else
				if (mc.r == null) {
					//&& !(mc.s instanceof acr/*GuiChat*/) && !(mc.s instanceof ars/*Sign Editing*/) && !(mc.s instanceof hw/*Book Editing*/)) {
					if (Keyboard.isKeyDown(42)) {
						WMLLI--;
						while (!isOutputEnabled(WMLLI))
							WMLLI--;
					}
					else {
						WMLLI++;
						while (!isOutputEnabled(WMLLI))
							WMLLI++;
					}
					saveOptions();
				}
		}
	}

	private void toggleF3Override() {
		wmllF3Output = !wmllF3Output;
		getGameSettings().P = false;
	}

	public int getFPSThreshold() {
		return Integer.parseInt(options.getProperty("FPSThreshold", "60"));
	}

	public boolean isSeedSet() {
		try {
			if (!isMultiplayer()/* || getWorldName().equals("localServer")*/)
				return true;
			return options.containsKey("Seed:"+getWorldName().toLowerCase());
		}
		catch (NullPointerException n) {
			n.printStackTrace();
			return false;
		}
	}

	public void displayUpdateString(int ver, String mcVersion) {
		wmllRenderer.updateVersion = ver;
		wmllRenderer.updateMCVersion = mcVersion;
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

	public boolean isOutputEnabled(int i) {
		try {
			if (i > 11)
				WMLLI = i = 0;
			if (i < 0)
				WMLLI = i = 11;
			return Boolean.parseBoolean(options.getProperty("Output"+i, "true"));
		}
		catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

	private String getField(String n) {
		return fieldNames.get(n);
	}

	public static String getMinecraftVersion() {
		return "1.3.1";
	}

	public boolean areAllOutputsDisabled() {
		int x = 0;
		for (; Boolean.parseBoolean(options.getProperty("Output"+x, "true")) == false && x <= 11; x++) { }	
		System.out.println(x);
		return x > 11;
	}

	public static boolean hasUpdateBeenAnnounced(int i) {
		return options.containsKey("Update"+i);
	}

	public void setUpdateAsAnnounced(int i) {
		options.put("Update"+Integer.toString(i), "true");
		saveOptions();
	}

}