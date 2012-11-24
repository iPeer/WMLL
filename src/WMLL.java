
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.SocketAddress;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
		return "Test 779";
	}
	public static final String getMinecraftVersion() {
		return "1.4.5";
	}
	public static final String[] autoDisable = {".*\\.oc\\.tc"};
	public static final List<Integer> blockBlackList = Arrays.asList(0, 8, 7, 9, 44, 20, 130);
	public static final Map<String, String> fieldNames = new HashMap<String, String>();
	public static final WMLLUpdateCheck wmllUpdateCheck = new WMLLUpdateCheck();
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
	public long worldSeed = 0L;
	public boolean autoSeed = true;
	public boolean useForge;
	public Minecraft mc;

	public boolean wmllOverrideF3;
	public int F3Type;
	public boolean showSeedWithCoords;
	public boolean debugClassPresent = false;
	public boolean classicOutput = false;
	public boolean autoUpdateCheck = true;
	public boolean compatDisabled = false;

	private static final int propertiesVersion = 3;
	public static File settingsFile, outputOptionsFile;
	public static long lastUpdateCheck = 0;
	private static final Calendar calendar = Calendar.getInstance();

	private WMLLRenderer wmllRenderer;
	private WMLLF3 wmllF3;
	private boolean Rei, ReiUseMl, RadarBro, AlienRadar, ZansMinimap;
	private Object alienRadar;
	private Object zansMinimap;
	public boolean satBar;
	private boolean ranInit = false;
	private boolean firstRun = true;
	private final String[] sleepstrings = {"Kuurth for 1000!", "Paralympics!", "Olympics!", "London 2012!", "Would you kindly?", "Goodnight, PLAYERNAME!", "This is my bed. There are many like it, but this one is mine.", "If it fits, I sleeps!", "*fade to blackness*", "*water drip*", "Goodnight, Hero!", "ZzzzzZZz", "That'sssssssss a very nice bed you have there...", "That bed looks comfy!", "*snoring*", "...aaaaaannnnddd asleepness!", "Muuuuuuurrrrh", "*clank*", "*screech*"};
	private boolean sleepingStringSet = false;
	private String lightString = "Light level: 9001";
	private long lastF4Press = 0;
	private boolean wmllF3Output = false;
	private atj fontRenderer;
	private String lastWorld = "";
	private boolean worldSeedSet = false;

	public String[] updateInfo = {};

	protected WMLLCompatibility wmllCompatibility;
	public boolean useML = false;

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
		fieldNames.put("chatLines", "c");
		Rei = ReiUseMl = RadarBro = false;
		this.debugClassPresent = (getClass().getClassLoader().getResource("ipeer_wmll_debug") != null);
		debugActive = this.debugClassPresent;
		settingsFile = Minecraft.a("minecraft\\mods\\WMLL\\");
		if (!settingsFile.exists())
			settingsFile.mkdirs();
		settingsFile = new File(settingsFile, "WMLL.properties");
		outputOptionsFile = new File(Minecraft.a("minecraft"), "WMLLOutput.properties");
		try {
			wmllCompatibility = new WMLLCompatibility();
		}
		catch (Exception e) { shitBricks(1, e); }
		catch (Error e) { shitBricks(1, e); }
		compatDisabled = (getClass().getClassLoader().getResource("WMLLCompatibility.class") == null);
		if (compatDisabled)
			debug("[WMLL] Couldn't create compatibility object (class missing).");
		loadOptions();
		this.autoSeed = Boolean.parseBoolean(options.getProperty("autoAquireSeed", "true"));
		useForge = (getClass().getClassLoader().getResource("net/minecraftforge/common/ForgeHooks.class") != null);
		if (getClass().getClassLoader().getResource("mod_ReiMinimap.class") != null) {
			try {
				Rei = true;
				ReiUseMl = ReiMinimap.instance.useModloader;
			}
			catch(VerifyError e) {
				shitBricks(2, e);
			}
		}
		if (getClass().getClassLoader().getResource("MotionTracker.class") != null) {
			try {
				AlienRadar = true;
				alienRadar = new MotionTracker();
			}
			catch (VerifyError e) {
				shitBricks(3, e);
				AlienRadar = false;
			}
		}
		if (getClass().getClassLoader().getResource("ZanMinimap.class") != null) {
			try {
				ZansMinimap = true;
				zansMinimap = new ZanMinimap();
			}
			catch (VerifyError e) {
				shitBricks(4, e);
				ZansMinimap = false;
			}
		}
		if (getClass().getClassLoader().getResource("RadarBro.class") != null) {
			RadarBro = true;
		}
		if (debugClassPresent) {
			RadarBro = false;
			useForge = false;
		}
		if (outputOptionsFile.exists()) {
			debug("[WMLL] WMLLOutput.properties exists, merging with WMLL.properties");
			try {
				Properties a = new Properties();
				a.load(new FileInputStream(outputOptionsFile));
				options.putAll(a);
				saveOptions();
				outputOptionsFile.deleteOnExit();
				debug("[WMLL] Succesfully merged files.");
			}
			catch (IOException w) {
				debug("[WMLL] Unable to merge files (IOException)\n");
				w.printStackTrace();
			}
		}
		debug("[WMLL] WMLL is"+(debugClassPresent ? "" : " not")+" running in debug mode");
		if (Rei)
			debug("[WMLL] WMLL is running in Rei's Minimap Compatibility mode");
		if (useForge)
			debug("[WMLL] WMLL is running in Forge Compatibility mode");
		if (RadarBro)
			debug("[WMLL] WMLL is running in RadarBro Compatibility mode");
		if (satBar)
			debug("[WMLL] WMLL is running in Saturation Bar Compatibility mode");
		if (AlienRadar)
			debug("[WMLL] WMLL is running in Alien Radar Compatibility mode");
		debug("[WMLL] Debug Data:");
		try { debug("\t* "+wmllCompatibility.toString()); }
		catch (Exception e) { debug("\t* null"); }
		debug("\t* "+this.toString());
		debug("\t* "+settingsFile.getAbsolutePath());
		for (String a : fieldNames.keySet())
			debug("\t* "+a+": "+fieldNames.get(a));
		debug("[WMLL] WMLL "+wmllVersion()+" initialized.");
	}

	public void updategui(Minecraft h) {
		updategui(h, h.v);
		System.err.println("Tick");
	}

	public void updategui(Minecraft h, atk atk) {
		h.I.a("WMLL");
		if (getWorld() != null && !wmllUpdateCheck.running && autoUpdateCheck) {
			wmllUpdateCheck.start();
		}
		else if (getWorld() == null && wmllUpdateCheck.running) {
			System.out.println("[WMLL] FATAL: World == NULL! -- Stopping update thread.");
			wmllUpdateCheck.stop1();
		}
		if (getWorld() != null && !getWorldName().equals(lastWorld) && !isMultiplayer() && autoSeed) {
			worldSeedSet = false;
			lastWorld = getWorldName();
			entityPlayer().c("/seed");
			debug("[WMLL] World name differs, re-acquiring seed...");
			if (!isEnabled())
				entityPlayer().a("[\2472WMLL\247f] \247cWMLL has been disabled on this "+(isMultiplayer() ? "server" : "world")+".");
		}
		if (!worldSeedSet) {
			try {
				Object obj = atk.b();
				Field f = obj.getClass().getDeclaredField(getField("chatLines"));
				f.setAccessible(true);
				obj = f.get(obj);
				@SuppressWarnings("unchecked")
				List<String> a = (List<String>)obj;
				@SuppressWarnings("rawtypes")
				Iterator c = a.iterator();
				int e = 0;
				while (c.hasNext()) {
					e++;
					arm d = (arm)c.next();
					String b = d.a(); //d.a();
					if (b.startsWith("Seed: ")) {
						//aow.b().a();
						if (!isMultiplayer())
							a.remove(e - 1);
						long worldSeed = Long.parseLong(b.split("Seed: ")[1]);
						worldSeedSet = true;
						debug("[WMLL] Seed set to "+worldSeed);
						this.worldSeed = worldSeed;
						break;
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (firstRun && getWorld() != null) {
			firstRun = false;
			wmllRenderer.firstRun = true;
		}
		if (Rei && !ReiUseMl)
			ReiMinimap.instance.onTickInGame(h);
		if (AlienRadar && alienRadar != null)
			((MotionTracker)alienRadar).onTickInGame(mc);
		if (ZansMinimap && zansMinimap != null)
			((ZanMinimap)zansMinimap).onTickInGame(mc);
		if (!ranInit) {
			this.mc = h;
			wmllRenderer = new WMLLRenderer(mc, this);
			wmllF3 = new WMLLF3(mc, this);
			ranInit = true;
			this.fontRenderer = h.p;
			if (debugClassPresent)
				entityPlayer().a("Test");
			//(new Thread(wmllUpdateCheck)).start();
		}
		if (mcDebugOpen() || wmllF3Output) {
			if (mcDebugOpen() && wmllOverrideF3)
				toggleF3Override();
			else if (mcDebugOpen() && !wmllOverrideF3)
				drawStringUsingPixels("WMLL "+wmllVersion(), 2, 52, 0xfffffe);
			else
				wmllF3.draw();
		}
		else {
			if (satBar && !compatDisabled) {
				try {
					wmllCompatibility.drawSaturationBar(mc, this);
				}
				catch (NoSuchMethodError n) { satBar = false; }
				catch (NoClassDefFoundError n1) { satBar = false; }
				catch (NoSuchFieldError n2) { satBar = false; }
				catch (NullPointerException n3) { satBar = false; } // 12W40 ERROR
				catch (Error e) { satBar = false; }
			}

			if (RadarBro && !compatDisabled)
				try {
					wmllCompatibility.RadarBroRun(mc, this);
				}
			catch (NoSuchMethodError n) { }
			catch (NoClassDefFoundError n1) { }
			catch (NoSuchFieldError n2) { }
			Enabled = isEnabled();
			if (WMLLDebugActive()) {
				mc.I.c("WMLL Debug");
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
					drawDebug(mc.r.toString(), (getWindowSize().a() - (getFontRenderer().a(mc.r.toString()) + 1)), 5, 0xffffff);
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
				a = Minecraft.a("minecraft").getAbsolutePath();
				drawDebug(a, (getWindowSize().a() - (getFontRenderer().a(a) + 1)), 11, 0xffffff);
				a = "S: "+canBlockSeeTheSky(x, getPlayerCoordinates()[1], z);
				drawDebug(a, (getWindowSize().a() - (getFontRenderer().a(a) + 1)), 13, 0xffffff);
				a = "WS: "+getWindowSize().a()+"x"+getWindowSize().b();
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
			if (WMLLI < 6 || (!useImages || !classicOutput)) {
				if (!isPlayerSleeping() && useImages)
					drawLightImage(light);
				else
					drawString(lightString, 2, 0, 0xffffff);
			}

			// Compass
			if (useImages || classicOutput) {
				if (Arrays.asList(3, 4, 5, 8, 9, 10).contains(WMLLI)) {
					int out = 1;
					if (WMLLI == 9 || WMLLI == 4) {
						out = isMultiplayer() && !isSeedSet() ? 3 : 4;
						if (getDimension() == 1)
							out--;
					}
					else if (WMLLI == 8 || WMLLI == 3 || WMLLI == 4)
						out = 0;
					else if (WMLLI == 10 || WMLLI == 5) 
						out = 1;
					if ((isSeedSet()) || isMultiplayer())
						out++;
					//				if (getDimension() == 1)
					//					out--;
					md player = thePlayer();
					double x = player.t;
					double y = player.u;
					double z = player.v;
					double f = ke.c((double)((player.z * 4F) / 360F) + 0.5D) & 3;
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
							if (getBiome().startsWith("Swamp")) {
								boolean a = isBlockInBlacklist(getBlockID(playerPos[0], playerPos[1] - 1, playerPos[2]));
								if (light < 8 && !a)
									drawString("\247a"+labels[5], 2, 3, 0xffffff);
								else if (light > 7 && !a)
									drawString("\247e"+labels[5], 2, 3, 0xffffff);
								else
									drawString("\247c"+labels[5], 2, 3, 0xffffff);

							}
							else if (canSlimesSpawnHere(playerPos[0], playerPos[2]) && !getBiome().startsWith("Swamp"))
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
		}
		wmllRenderer.tick();
		h.I.b();
	}

	private void drawLightImage(int light) {
		wmllRenderer.renderLightImage(light);
		drawString(Integer.toString(light), 6, 0, 0xffffff);
	}

	public String generateLightString() {
		return generateLightString(options.getProperty("lightString", "Light level: %LightLevel%"));
	}

	public String generateLightString(String s) { // [Roxy] Now Case insensitive
		int x = getPlayerCoordinates()[0];
		int y = getPlayerCoordinates()[1];
		int z = getPlayerCoordinates()[2];
		double x1 = getPlayerCoordinatesAsDouble()[0];
		double y1 = getPlayerCoordinatesAsDouble()[1];
		double z1 = getPlayerCoordinatesAsDouble()[2];
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
		Pattern clock = Pattern.compile("%clock%", Pattern.CASE_INSENSITIVE);
		Pattern clock2 = Pattern.compile("%12hclock%", Pattern.CASE_INSENSITIVE);
		Pattern chunkx = Pattern.compile("%chunkx%", Pattern.CASE_INSENSITIVE);
		Pattern chunkz = Pattern.compile("%chunkz%", Pattern.CASE_INSENSITIVE);
		Pattern seed = Pattern.compile("%seed%", Pattern.CASE_INSENSITIVE);
		Pattern localTime = Pattern.compile("%localtime%", Pattern.CASE_INSENSITIVE);
		Pattern localTime12h = Pattern.compile("%12hlocaltime%", Pattern.CASE_INSENSITIVE);
		String lightLevel = (a < 8 ? "\247c" : "")+Integer.toString(a)+"\247r";
		a = getSavedBlockLight(x, y, z);
		String blockLight = (a < 8 ? "\247c" : "")+Integer.toString(a)+"\247r";
		a = getRawLightLevel(x, y, z);
		String rawLight = (a < 8 ? "\247c" : "")+Integer.toString(a)+"\247r";
		a = getSkyLight(1.0f);
		String skyLight = (a > 7 ? "\247c" : "")+Integer.toString(a)+"\247r";
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
		m = clock.matcher(s);
		s = m.replaceAll(getFormattedWorldTime(2));
		m = clock2.matcher(s);
		s = m.replaceAll(getFormattedWorldTime(3));
		m = chunkx.matcher(s);
		s = m.replaceAll(Integer.toString(getChunk(x, z).g));
		m = chunkz.matcher(s);
		s = m.replaceAll(Integer.toString(getChunk(x, z).h));
		m = seed.matcher(s);
		s = m.replaceAll(""+(getWorldSeed() != 0L ? getWorldSeed() : ""));
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
		Pattern indicators = Pattern.compile("%ind:([\\p{Alnum}\\p{Punct}&&[^\\\\ ]]+)%", Pattern.CASE_INSENSITIVE);
		m = indicators.matcher(s);
		while (m.find()) {
			String ind = m.group().replaceAll("%ind:|%", "").toLowerCase();
			s = s.replaceAll("%ind:"+ind+"%", getIdenString(ind));
		}
		m = localTime.matcher(s);
		s = m.replaceAll(getLocalTime(0));
		m = localTime12h.matcher(s);
		s = m.replaceAll(getLocalTime(1));
		return s;
	}


	private String getLocalTime(int mode) {
		calendar.setTime(new Date(System.currentTimeMillis()));
		String time = calendar.getTime().toString().split(" ")[3];
		if (mode == 1) {
			int a = Integer.valueOf(time.split(":")[0]);
			if (a > 12)
				a -= 12;
			return a+":"+time.split(":")[1]+":"+time.split(":")[2]+" "+(a + 12 > 11 ? "PM" : "AM");
		}
		return calendar.getTime().toString().split(" ")[3];
	}

	private ayh getWorld() {
		try {
			return mc.e;
		}
		catch (NullPointerException n) {
			return null;
		}
	}

	public bdo sspServer() {
		return mc.C();
	}

	public String getWorldName() {
		if (!isMultiplayer())
			return sspServer().K();
		try {
			Object obj = thePlayer();
			Field f = obj.getClass().getDeclaredField("a"); // sendQueue
			f.setAccessible(true);
			obj = f.get(obj);
			Field f1 = obj.getClass().getDeclaredField(getField("netManager")); // netManager
			f1.setAccessible(true);
			obj = f1.get(obj);
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
			return server+":"+port;	
		}
		catch (Exception e) {
			return "Unknown - "+e.getMessage();
		}
	}

	//	public String worldName() {
	//		return worldInstance().m()+", "+worldInstance().n();
	//	}

	public atj getFontRenderer() {
		return this.fontRenderer;
	}

	public auf getWindowSize() {
		return new auf(mc.y, mc.c, mc.d);
	}

	private boolean mcDebugOpen() {
		return getGameSettings().U;
	}

	private asl getGameSettings() {
		return mc.y;
	}

	public boolean isMultiplayer() {
		return !mc.B();
	}

	public int getSavedBlockLight(int x, int y, int z) {
		if (y < 0 || y > 255) 
			return 0;
		int[] playerPos = {x, y, z};
		return getChunk(playerPos[0], playerPos[2]).a(yh.b, playerPos[0] & 0xf, playerPos[1], playerPos[2] & 0xf);
	}

	public int getRawLightLevel(int x, int y, int z) {
		if (y < 0 || y > 255) 
			return 0;
		int[] playerPos = {x, y, z};
		return getChunk(playerPos[0], playerPos[2]).c(x & 0xf, y, z & 0xf, 0);
	}

	public int getBlockLight (int i, int j, int k) {
		if (j < 0 || j > 255)
			return 0;
		return getChunk(i, k).a(yh.a, i & 0xf, j, k & 0xf);
	}

	public int getLightLevel(int j, int k, int l) {
		if (k < 0 || k > 255)
			return 0;
		return getChunk(j, l).c(j & 0xf, k, l & 0xf, getSkyLight(1.0f));
	}

	public int getSkyLight(float f) {
		return getWorld().a(f);
	}

	public String getBiome(int x, int z) {
		return getChunk(x, z).a(x & 0xf, z & 0xf, getBiomeGenBase()).y;
	}

	public String getBiome() {
		int x = getPlayerCoordinates()[0];
		int z = getPlayerCoordinates()[2];
		return getBiome(x, z);
	}

	private String getTemperature() {
		return NumberFormat.getPercentInstance().format(getBiomeGenBase().a(getPlayerCoordinates()[0], getPlayerCoordinates()[2]).F);
	}

	private String getHumidity() {
		return NumberFormat.getPercentInstance().format(getBiomeGenBase().a(getPlayerCoordinates()[0], getPlayerCoordinates()[2]).G);
	}

	private yw getBiomeGenBase() {
		return getWorld().t();
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
		return getWorld().k(x, y, z);
	}

	public void sendChatMessage(String t) {
		entityPlayer().a(t);
	}

	public ayk entityPlayer() {
		return mc.g;
	}

	public md thePlayer() {
		return mc.h;
	}

	public ass playerEntity() {
		return mc.j;
	}

	public String getPlayerName() {
		return playerEntity().b;
	}

	public ayg getPlayerController() {
		return mc.b;
	}

	public boolean isCreative() {
		return !getPlayerController().b();
	}

	public ahq worldInfo() {
		return getWorld().z;
	}

	protected Minecraft getMCInstance() {
		return mc;
	}

	private zs getChunk(int x, int z) {
		return getWorld().d(x, z);
	}

	private int getDimension() {
		return getWorldProvider().h;
	}

	private boolean canSlimesSpawnHere(int x, int z) {
		if (isSeedSet()) {
			zs chunk = getChunk(x, z);
			int g = chunk.g;
			int h = chunk.h;
			return new Random(getWorldSeed() + (long)(g * g * 0x4c1906) + (long)(g * 0x5ac0db) + (long)(h * h) * 0x4307a7L + (long)(h * 0x5f24f) ^ 0x3ad8025fL).nextInt(10) == 0;
		}
		return (getChunk(x, z).a(0x3ad8025fL).nextInt(10) == 0 && getWorldSeed() != 0L)/* || (getBiome(x, z).startsWith("Swamp") && getLightLevel(x, getPlayerCoordinates()[1], x) < 8)*/;
	}

	private aaj getWorldProvider() {
		return getWorld().v;
	}

	private zp getChunkProvider() {
		return getWorldProvider().c();
	}

	public long getWorldTime() {
		return getWorld().G();
	}

	private String getFormattedWorldTime(int i) {
		long a = getWorldTime();
		int h = (int)(((a + 6000L) % 24000L) / 1000L);
		int m = (int)(((a % 1000L) * 60L)  / 1000L);
		String suffix = "AM";
		String out = "00:00";
		if (i == 2) {
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
		return thePlayer().bw();
	}

	public int[] getPlayerCoordinates() {
		int[] a = {ke.c(thePlayer().t), ke.c(thePlayer().u - 1), ke.c(thePlayer().v), ke.c((double)((thePlayer().z * 4F) / 360F) + 0.5D) & 3, (int)thePlayer().t, (int)thePlayer().u, (int)thePlayer().v};
		return a;
	}

	public double[] getPlayerCoordinatesAsDouble() {
		double[] a = {thePlayer().t, thePlayer().u, thePlayer().v, ke.c((double)((thePlayer().z * 4F) / 360F) + 0.5D) & 3};
		return a;
	}

	public long getWorldSeed() {
		if (worldSeedSet)
			return this.worldSeed;
		else {
			try {
				return Long.parseLong(options.getProperty("Seed:"+getWorldName().toLowerCase(), "0"));
			}
			catch (NumberFormatException n) {
				return 0L;
			}
			catch (NullPointerException n1) {
				return 0L;
			}
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
		if (WMLLI > 5 && WMLLI < 11)
			y++;
		drawString(t, x, y, c);
	}

	public void drawStringUsingPixels(String t, int x, int y, int c) {
		if (c == 0xffffff)
			c = TextColour;
		getFontRenderer().a(t, x, y, c);
	}

	public void drawString(String t, int i, int j, int k) {
		int textpos = (WMLLI > 5 && classicOutput ? -8 : 2);
		//t = (k == 0xffffff ? "\247"+Integer.toHexString(TextColour) : "")+t;
		Pattern re = Pattern.compile("\247[0-9a-f,l-o,r]");
		int w = getWindowSize().a();
		int h = getWindowSize().b();
		t = t.replaceAll("\\\\t", (classicOutput ? " " : "    "));
		String[] lines = {};
		if (classicOutput) {
			lines = new String[1];
			lines[0] = t.replaceAll("\\\\n", " ");
		}
		else
			lines = t.split("\\\\n");
		int l = 0;
		for (String a : lines) {
			String a1 = re.matcher(a).replaceAll("");
			if (k == 0xffffff)
				k = TextColour;
			if (outputLocation == 1) // Top right
				getFontRenderer().a(a, w - (getFontRenderer().a(a1) + (i - 1)), textpos+(j*10+(10*l++)), k);
			else if (outputLocation == 2) // Bottom left
				getFontRenderer().a(a, i, h - (textpos+(j*10+(10*l++)) + 8), k);
			else if (outputLocation == 3) // Bottom Right
				getFontRenderer().a(a,  w - (getFontRenderer().a(a1) + (i - 1)), h - (textpos+(j*10+(10*l++)) + 8), k);
			else // Top Left
				getFontRenderer().a(a, i, textpos+((j*10)+(10*l++)), k);
		}
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
			options.setProperty("classicOutput", Boolean.toString(classicOutput));
			options.setProperty("autoUpdateCheck", Boolean.toString(autoUpdateCheck));
			options.setProperty("SaturationBar", Boolean.toString(satBar));
			options.store(new FileOutputStream(settingsFile), "WMLL Config File - Do not edit unless you know what you're doing!");
			//			if (!outputOptions.isEmpty())
			//				outputOptions.store(new FileOutputStream(outputOptionsFile), "WMLL's Output Options File - only edit if you know waht you're doing!");
			debug("[WMLL] Options saved.");
			//debug(options.toString()+"\n"+outputOptions.toString());
		}
		catch (Exception e) { debug("[WMLL] Unable to save options: "+e.getMessage()); e.printStackTrace(); }
	}

	public void loadOptions() {
		try {
			boolean updatedFormat = false;
			if (options == null)
				options = new Properties();
			if (outputOptions == null)
				outputOptions = new Properties();
			if (settingsFile.exists())
				options.load(new FileInputStream(settingsFile));
			else {
				File a = new File(Minecraft.a("minecraft"), "WMLL.properties");
				if (a.exists()) {
					options.load(new FileInputStream(a));
					System.err.println(">> "+a.getAbsolutePath());
					a.deleteOnExit();
					updatedFormat = true;
				}
			}
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
			classicOutput = Boolean.parseBoolean(options.getProperty("classicOutput", "false"));
			autoUpdateCheck= Boolean.parseBoolean(options.getProperty("autoUpdateCheck", "true"));
			satBar = Boolean.parseBoolean(options.getProperty("SaturationBar", "false"));
			debug("[WMLL] Loaded options.");
			//debug(options.toString()+"\n"+outputOptions.toString());
			if (firstRun || updatedFormat)
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
					if (useImages || classicOutput) {
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
	}

	private void toggleF3Override() {
		wmllF3Output = !wmllF3Output;
		getGameSettings().U = false;
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

	public void displayUpdateString(String newver, String mcVersion) {
		sendChatMessage("WMLL \247c"+newver+"\247f is now available for Minecraft \247c"+mcVersion);
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

	public boolean areAllOutputsDisabled() {
		int x = 0;
		for (; Boolean.parseBoolean(options.getProperty("Output"+x, "true")) == false && x <= 11; x++) { }	
		return x > 11;
	}

	public static boolean hasUpdateBeenAnnounced(String i) {
		return options.containsKey("Update"+i.replaceAll(" ", "_"));
	}

	public void setUpdateAsAnnounced(String i) {
		options.put("Update"+i.replaceAll(" ", "_"), "true");
		saveOptions();
	}

	public String getIdenString(String i) {
		int x = getPlayerCoordinates()[0];
		int y = getPlayerCoordinates()[1];
		int z = getPlayerCoordinates()[2];
		int l = getLightLevel(x, y, z);
		int b = getBlockID(x, y - 1, z);
		int d = getDimension();
		Boolean sk = canBlockSeeTheSky(x, y, z);
		Boolean c = canSlimesSpawnHere(x, z);
		Boolean v = blockBlackList.contains(b);
		String s = "\247cInvalid indicator!";
		String bi = getBiome();
		if (i.equals("mobs")) {
			if (d == 0) {
				if (l > 7 && !v || bi.startsWith("MushroomIsland"))
					s = "\247cMobs";
				else if (l < 8 && !v)
					s = "\247aMobs";
				else
					s = "\247cMobs";
			}
			else if (d == -1)
				if (v)
					s = "\247cGhasts";
				else
					s = "\247aGhasts";
			else
				s = (!v && l < 8 ? "\247aEndermen" : "\247cEndermen");
		}
		else if (i.equals("animals")) {
			if (d == -1)
				if (!v)
					s = "\247aPigmen";
				else
					s = "\247cPigmen";
			else if (d == 0)
				if (b == 2)
					if (l < 9 && !sk)
						s = "\247cAnimals";
					else if (l < 9 && sk)
						s = "\247eAnimals";
					else
						s = "\247aAnimals";
				else
					s = "\247cAnimals";
			else
				s = "\247cAnimals";
		}
		else if (i.equals("slimes")) {
			if (d == 0)
				if ((c && y + 1 > 40) || (l > 7 && bi.startsWith("Swamp")))
					s = "\247eSlimes";
				else if (c && y + 1 < 41 && v)
					s = "\247cSlimes";
				else if ((c && y + 1 < 41) || (l < 8 && bi.startsWith("Swamp")))
					s = "\247aSlimes";
				else
					s = "\247cSlimes";
			else if (d == -1)
				if (!v)
					s = "\247aSlimes";
				else
					s = "\247cSlimes";
			else
				s = "\247cSlimes";
		}
		else if (i.equals("crops")) {
			if (b == 60)
				s = (l > 9 ? "\247aCrops" : "\247cCrops");
			else
				s = "\247cCrops";
		}
		else if (i.equals("trees")) {
			if (Arrays.asList(2, 3).contains(b))
				s = (l > 9 ? "\247aTrees" : "\247cTrees");
			else
				s = "\247cTrees";
		}
		else if (i.equals("grass")) {
			//			Boolean a = getBlockID(x - 1, y - 1, z) == 2;
			//			Boolean e = getBlockID(x + 1, y - 1, z) == 2;
			//			Boolean f = getBlockID(x, y - 1, z + 1) == 2;
			//			Boolean g = getBlockID(x, y - 1, z - 1) == 2;
			//			Boolean h = getBlockID(x - 1, y - 1, z - 1) == 2;
			//			Boolean j = getBlockID(x + 1, y - 1, z + 1) == 2;
			if (l > 9 && b == 3/* && (a || e || f || g || h || j)*/)
				s = "\247aGrass";
			else
				s = "\247cGrass";

		}
		else if (i.equals("mushrooms")) {
			if ((l > 12 || v) && b != 110)
				s = "\247cMushrooms";
			else
				s = "\247aMushrooms";
		}
		return s+(debugActive ? ", "+l+", "+v+", "+sk : "")+"\247"+Integer.toHexString(TextColour);
	}

	public void toggleSatBar() {
		this.satBar = !this.satBar;
	}

	public void shitBricks(int id, Throwable e) {
		switch (id) {
		case 1:
			System.err.println("[WMLL] Error creating compatibility object.");
			printStackTrace(e);
			compatDisabled = true;
			break;
		case 2:
			System.err.println("[WMLL] Error creating Rei's Minimap Compatibility.");
			printStackTrace(e);
			Rei = ReiUseMl = false;
			break;
		case 3:
			System.err.println("[WMLL] Error creating Alien Radare Compatibility.");
			AlienRadar = false;
			printStackTrace(e);
			break;
		case 4:
			System.err.println("[WMLL] Error creating Alien Radar Compatibility.");
			ZansMinimap = false;
			printStackTrace(e);
			break;
		}
	}

	public void printStackTrace(Throwable e) {
		debug("[WMLL] Exception: "+e.toString());
		for (int x = 0; x < e.getStackTrace().length; x++)
			debug("\t* "+e.getStackTrace()[x]);
	}

	public boolean isEnabled() {
		//boolean a = isAutoDisabledServer();
		return /*!a && */Boolean.parseBoolean(options.getProperty("World-"+getWorldName(), "true")) && !Boolean.parseBoolean(options.getProperty("AllOutputsOff", "false"));
	}

	@SuppressWarnings("unused")
	private boolean isAutoDisabledServer() {
		for (String b : autoDisable)
			if (getWorldName().matches(b))
				return true;
		return false;
	}

}