
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.ForgeVersion;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import reifnsk.minimap.ReiMinimap;

import com.thevoxelbox.voxelmap.VoxelMap;


public class WMLL {

	public static final String wmllVersion() {
		return "Test 813"; //813
	}
	public static final String versionName() {
		return "";
	}
	public static final String getMinecraftVersion() {
		return "1.5.2";
	}
	public static final String[] autoDisable = {".*\\.oc\\.tc"};
	public static final List<Integer> blockBlackList = Arrays.asList(0, 8, 7, 9, 20, 31, 32, 39, 40, 44, 50, 130);
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
	public Minecraft mc;

	public boolean wmllOverrideF3;
	public int F3Type;
	public boolean showSeedWithCoords;
	public boolean debugClassPresent = false;
	public boolean classicOutput = false;
	public boolean autoUpdateCheck = true;
	public boolean compatDisabled = false;
	public boolean showUnderGUIs = true;

	private static final int propertiesVersion = 3;
	public static File settingsFile, outputOptionsFile;
	public static long lastUpdateCheck = 0;
	public static boolean colourLowLight = true;
	private static final Calendar calendar = Calendar.getInstance();

	private WMLLRenderer wmllRenderer;
	private WMLLF3 wmllF3;
	public boolean Rei, ReiUseMl, RadarBro, AlienRadar, ZansMinimap, ReiEnabled, AlienEnabled, ZansEnabled, forgeDetected, forgeEnabled, useForge;
	private Object alienRadar;
	private Object zansMinimap;
	public boolean satBar;
	private boolean ranInit = false;
	public boolean firstRun = true;
	private final String[] sleepstrings = {"iPeer <3 Boston", "/r/WMLL!", "Now on Reddit!", "What's My Arrow Level?", "Almost makes you wish for a nuclear winter!", "1 byte of posts!", "Kuurth for 1000!", "Paralympics!", "Olympics!", "London 2012!", "Would you kindly?", "Goodnight, PLAYERNAME!", "This is my bed. There are many like it, but this one is mine.", "If it fits, I sleeps!", "*fade to blackness*", "*water drip*", "Goodnight, Hero!", "ZzzzzZZz", "That'sssssssss a very nice bed you have there...", "That bed looks comfy!", "*snoring*", "...aaaaaannnnddd asleepness!", "Muuuuuuurrrrh", "*clank*", "*screech*"};
	private final String[] easters = {"204", "54", "273", "164", "14", "214", "124", "44"};
	private boolean sleepingStringSet = false;
	private String lightString = "Light level: 9001";
	private long lastF4Press = 0;
	private boolean wmllF3Output = false;
	private awv fontRenderer;
	public String lastWorld = "";
	public boolean worldSeedSet = false;
	public boolean warnedAboutConflicts = false;
	public String reiError = "Not found", zanError = "Not found", alienError = "Not found", forgeError = "Not found";

	public String[] updateInfo = {};

	protected WMLLCompatibility wmllCompatibility;
	public boolean showWorldName = true;

	public boolean realInit = false;
	public boolean useML = false;
	private float renderPartialTicks = 0.0f;
	private boolean renderArmourDisplay = false;
	public boolean forceAutohideObey = false;

	public WMLL() { 
		debug("[WMLL] Initializing WMLL "+wmllVersion());
		fieldNames.put("sendQueue", "i");
		fieldNames.put("netManager", "g");
		fieldNames.put("remoteSocketAddress", "k");
		fieldNames.put("SPremoteSocketAddress", "a");
		fieldNames.put("genNetherBridge", "c");
		fieldNames.put("SpawnListEntry", "a");
		fieldNames.put("localServerWorldName", "b");
		fieldNames.put("worldSeed", "a");
		fieldNames.put("chatLines", "c");
		Rei = ReiUseMl = RadarBro = false;
		this.debugClassPresent = (getClass().getClassLoader().getResource("ipeer_wmll_debug") != null);
		//debugActive = this.debugClassPresent;
		settingsFile = new File("./mods/WMLL");
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
		if (getClass().getClassLoader().getResource("net/minecraftforge/common/ForgeHooks.class") != null || useML) {
			useForge = forgeDetected = true;
			forgeError = "";
		}
		if (getClass().getClassLoader().getResource("mod_ReiMinimap.class") != null) {
			try {
				Rei = true;
				ReiUseMl = ReiMinimap.instance.useModloader;
				reiError = "";
			}
			catch(VerifyError e) {
				shitBricks(2, e);
			}
		}
		if (getClass().getClassLoader().getResource("MotionTracker.class") != null) {
			try {
				AlienRadar = true;
				alienRadar = new MotionTracker();
				alienError = "";
			}
			catch (VerifyError e) {
				shitBricks(3, e);
				AlienRadar = false;
			}
		}
		if (getClass().getClassLoader().getResource("com/thevoxelbox/voxelmap/VoxelMap.class") != null) {
			try {
				ZansMinimap = true;
				zansMinimap = new VoxelMap(true); // Dear VoxelMap creators: STOP CHANGING THIS CRAP.
				zanError = "";
			}
			catch (VerifyError e) {
				shitBricks(4, e);
				ZansMinimap = false;
			}
			catch (NoClassDefFoundError e) {
				shitBricks(4, e);
				ZansMinimap = false;
			}
		}
		if (getClass().getClassLoader().getResource("RadarBro.class") != null) {
			RadarBro = true;
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
		if (forgeDetected)
			debug("[WMLL] WMLL is running in Forge Compatibility mode");
		if (RadarBro)
			debug("[WMLL] WMLL is running in RadarBro Compatibility mode");
		if (satBar)
			debug("[WMLL] WMLL is running in Saturation Bar Compatibility mode");
		if (AlienRadar)
			debug("[WMLL] WMLL is running in Alien Radar Compatibility mode");
		if (ZansMinimap)
			debug("[WMLL] WMLL is running in VoxelMap Compatibility mode.");
		debug("[WMLL] Updater: "+autoUpdateCheck);
		debug("[WMLL] Debug Data:");
		try { debug("\t* "+wmllCompatibility.toString()); }
		catch (Exception e) { debug("\t* null"); }
		debug("\t* "+this.toString());
		try {
			debug("\t* "+settingsFile.getCanonicalPath());
		} catch (IOException e) { }
		for (String a : fieldNames.keySet())
			debug("\t* "+a+": "+fieldNames.get(a));
		debug("[WMLL] WMLL "+wmllVersion()+" initialized.");
	}

	public void updategui(Minecraft h) throws WMLLException {
		updategui(h, h.w);
	}

	public void updategui(Minecraft h, aww w) throws WMLLException {
		throw new WMLLException("Deprecated Initialisation Method! Use updategui(mc, float, guiingame) instead.");
	}

	public void updategui(Minecraft mc2, float f) {
		updategui(mc2, f, mc2.w);
	}

	public void updategui(Minecraft h, float renderPartialTicks, aww w) {
		this.renderPartialTicks = renderPartialTicks;
		if (getWorld() != null && !wmllUpdateCheck.running && autoUpdateCheck) {
			wmllUpdateCheck.start();
		}
		else if (getWorld() == null && wmllUpdateCheck.running) {
			System.out.println("[WMLL] FATAL: World == NULL! -- Stopping update thread.");
			wmllUpdateCheck.stop1();
		}
		if (getWorld() != null && !getWorldName().equals(lastWorld) && autoSeed) {
			worldSeedSet = false;
			lastWorld = getWorldName();
			//			if (!options.containsKey("Seed:"+getWorldName().toLowerCase()) && !isMultiplayer())
			//				entityPlayer().d("/seed");
			if (!isMultiplayer())
				debug("[WMLL] World name differs, re-acquiring seed...");
			boolean[] b = {Rei && ReiEnabled, ZansMinimap && ZansEnabled, AlienRadar && AlienEnabled};
			if (atLeast2True(b) && !warnedAboutConflicts) {
				ReiEnabled = ZansEnabled = AlienEnabled = false;
				sendChatMessage("[\2472WMLL\247f] \247cWMLL has detected that you have multiple minimap mods installed and enabled. To prevent crashes");
				sendChatMessage("[\2472WMLL\247f] \247cWMLL has disabled them all temporarily. Please go into WMLL's config and press \"Compatibility Settings\" and enable the one you wish to use.");
				//sendChatMessage("[\2472WMLL\247f] \247cand enable the one you wish to use.");
			}
			if (!isEnabled())
				entityPlayer().a("[\2472WMLL\247f] \247cWMLL has been disabled on this "+(isMultiplayer() ? "server" : "world")+".");
		}
		if (!worldSeedSet && getWorld() != null && !isMultiplayer()) {
			try {
				if (options.getProperty("Seed:"+getWorldName().toLowerCase()) != null) {
					worldSeedSet = true;
					this.worldSeed = Long.valueOf(options.getProperty("Seed:"+getWorldName().toLowerCase()));
				}
				else {
					worldSeedSet = true;
					this.worldSeed = ((aab)MinecraftServer.D().a(0)).G();
					/*Object obj = awq.b();
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
						aut d = (aut)c.next();
						String b = d.a(); //d.a();
						if (b.startsWith("Seed: ")) {
							//aow.b().a();
							//if (!isMultiplayer())
								//a.remove(e - 1);
								c.remove();
							long worldSeed = Long.parseLong(b.split("Seed: ")[1]);
							worldSeedSet = true;
							this.worldSeed = worldSeed;
							debug("[WMLL] Seed set to "+worldSeed);
							break;
						}

					}*/
				}
				debug("[WMLL] Seed set to "+this.worldSeed);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//skip;
		}
		if (firstRun && getWorld() != null) {
			firstRun = false;
			wmllRenderer.firstRun = true;
		}
		if (!ranInit) {
			this.mc = h;
			realInit = true;
			wmllRenderer = new WMLLRenderer(mc, this);
			wmllF3 = new WMLLF3(mc, this);
			ranInit = true;
			this.fontRenderer = h.q;
			if (debugClassPresent)
				entityPlayer().a("Test");
			//(new Thread(wmllUpdateCheck)).start();
		}
		if (useML && getWorld() == null)
			return;
		if (!useML && h != null) {
			if (Rei && !ReiUseMl && ReiEnabled)
				ReiMinimap.instance.onTickInGame(renderPartialTicks, h);
			if (AlienRadar && AlienEnabled && alienRadar != null && getWorld() != null)
				((MotionTracker)alienRadar).onTickInGame(h);
			if (ZansMinimap && ZansEnabled && zansMinimap != null && getWorld() != null)
				((VoxelMap)zansMinimap).onTickInGame(h);
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
				a = "CP: "+getChunkProvider().toString();
				drawDebug(a, (getWindowSize().a() - (getFontRenderer().a(a) + 1)), 12, 0xffffff);
				a = Minecraft.a("minecraft").getAbsolutePath();
				drawDebug(a, (getWindowSize().a() - (getFontRenderer().a(a) + 1)), 11, 0xffffff);
				a = "S: "+canBlockSeeTheSky(x, getPlayerCoordinates()[1], z);
				drawDebug(a, (getWindowSize().a() - (getFontRenderer().a(a) + 1)), 13, 0xffffff);
				a = "WS: "+getWindowSize().a()+"x"+getWindowSize().b();
				drawDebug(a, (getWindowSize().a() - (getFontRenderer().a(a) + 1)), 14, 0xffffff);
				a = getPlayerName();
				drawDebug(a, (getWindowSize().a() - (getFontRenderer().a(a) + 1)), 15, 0xffffff);
				a = getLocalTime(0)+" / "+getLocalTime(1);
				drawDebug(a, (getWindowSize().a() - (getFontRenderer().a(a) + 1)), 16, 0xffffff);
				a = (mc.s != null ? mc.s.toString() : "null");
				drawDebug(a, (getWindowSize().a() - (getFontRenderer().a(a) + 1)), 17, 0xffffff);
			}
			else if (!WMLLDebugActive() && debugClassPresent && shouldShow()) {
				String a = "WMLL "+wmllVersion()+" (DEBUG MODE)";
				drawDebug(a, (getWindowSize().a() - (getFontRenderer().a(a) + 1)), 0, 0xffffff);
				if (forgeDetected) {
					a = "Forge: "+ForgeVersion.getVersion();
					drawDebug(a, (getWindowSize().a() - (getFontRenderer().a(a) + 1)), 1, 0xffffff);
				}
				if (Rei) {
					a = "Rei's Minimap: "+ReiMinimap.MOD_VERSION;
					drawDebug(a, (getWindowSize().a() - (getFontRenderer().a(a) + 1)), 2, 0xffffff);
				}
				if (ZansMinimap) {
					a = "VoxelMap: "+((VoxelMap)zansMinimap).zmodver; 
					drawDebug(a, (getWindowSize().a() - (getFontRenderer().a(a) + 1)), 3, 0xffffff);
				}

			}
			WMLLCheckKeys();
			//if (mc.s instanceof axl && !(mc.s instanceof WMLL_InGameMenu)/* && !(mc.s instanceof EllianGuiOptionScreen)*/ && useML)
			if ((getGUIClassName().equals("axl") /* Vanilla */ || getGUIClassName().equals("net.minecraft.client.gui.GuiIngameMenu") /* Forge */) && useML)
				mc.a(new WMLL_InGameMenu());
			if (!Enabled || !shouldShow() || (WMLLI == 11 && classicOutput))
				return;
			if (renderArmourDisplay && !isCreative()) {
				int gameHeight = getWindowSize().b();
				int gameWidth = getWindowSize().a();
				int armourX = (gameWidth / 2 - 93) + 0 * 16;
				for (int x = getPlayerArmour().length - 1; x >= 0; x--) {
					if (getPlayerArmour()[x] != null) {
						renderItemOnScreen(getPlayerArmour()[x], armourX, gameHeight - 65);
						armourX += 15;
					}
				}

			}
			// 0 = x, 1 = y, 2 = z, 3 = f
			int[] playerPos = getPlayerCoordinates();
			int light = getLightLevel(playerPos[0], playerPos[1], playerPos[2]);
			if (isPlayerSleeping()) {
				if (getCalendarDate().equals("66")) // My birthday
					lightString = "Happy birthday, iPeer!";
				else if (getCalendarDate().equals("243")) // Roxy's birthday (<3)
					lightString = "Happy birthday, Roxy!";
				else if (getCalendarDate().equals("202")) // WMLL's "birthday"
					lightString = "Happy birthday, WMLL!";
				else if (getCalendarDate().equals(easters[(getYear() > 2014 ? getYear() - 2014 : 0)])) // Easter Sunday
					lightString = "Happy Easter!";
				else if (getCalendarDate().equals("2512")) // Christmas Day
					lightString = "Why are you playing Minecraft on Christmas Day?";
				else if (getCalendarDate().equals("11")) // New Year
					lightString = "Happy New Year!";
				else if (getCalendarDate().equals("3110")) // Halloween
					lightString = "Happy Halloween! WoooOOOoooOOoooO!";
				else if (getCalendarDate().equals("31")) // Millie <3 RIP, honey.
					lightString = "Millie <3";
				else if (getCalendarDate().equals("32")) { // Roxy and I's anniversary
					int now = getYear();
					int years = now-2007;
					lightString = years+" years today! <3 Roxy!";
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
					if ((isSeedSet()) || isMultiplayer() || (isMultiplayer() && (getDimension() == -1 || getDimension() == 1)))
						out++;
					double[] coordinates = getPlayerCoordinatesAsDouble();
					NumberFormat d = new DecimalFormat("#0.00");
					String coords = "("+d.format(coordinates[0])+", "+d.format(coordinates[1])+", "+d.format(coordinates[2])+", "+getPlayerDirection()+")";
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
		double yfeet = getPlayerCoordinatesAsDouble()[3];
		int a = getLightLevel(x, y, z);
		Pattern SkyLight = Pattern.compile("%skylight%", Pattern.CASE_INSENSITIVE);
		Pattern BlockLight = Pattern.compile("%blocklight%", Pattern.CASE_INSENSITIVE);
		Pattern RawLight = Pattern.compile("%rawlight%", Pattern.CASE_INSENSITIVE);
		Pattern Light = Pattern.compile("%LightLevel%", Pattern.CASE_INSENSITIVE);
		Pattern sunLight = Pattern.compile("%sunlight%", Pattern.CASE_INSENSITIVE);
		Pattern Biome = Pattern.compile("%Biome%", Pattern.CASE_INSENSITIVE);
		Pattern FPS = Pattern.compile("%fps%", Pattern.CASE_INSENSITIVE);
		Pattern FPS_noCU = Pattern.compile("%fps2%", Pattern.CASE_INSENSITIVE);
		Pattern fullCompass = Pattern.compile("%fullcompass%", Pattern.CASE_INSENSITIVE);
		Pattern chunkUpdates = Pattern.compile("%(cu|chunkupdates)%", Pattern.CASE_INSENSITIVE);
		Pattern heading = Pattern.compile("%heading%", Pattern.CASE_INSENSITIVE);
		Pattern fx = Pattern.compile("%fx%", Pattern.CASE_INSENSITIVE);
		Pattern fy = Pattern.compile("%fy%", Pattern.CASE_INSENSITIVE);
		Pattern feety = Pattern.compile("%feety%", Pattern.CASE_INSENSITIVE);
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
		Pattern entities = Pattern.compile("%entities%", Pattern.CASE_INSENSITIVE);
		Pattern entities2 = Pattern.compile("%fullentities%", Pattern.CASE_INSENSITIVE);
		String lightLevel = (a < 8 && colourLowLight ? "\247c" : "")+Integer.toString(a)+"\247r";
		a = getSavedBlockLight(x, y, z);
		String blockLight = (a < 8 && colourLowLight ? "\247c" : "")+Integer.toString(a)+"\247r";
		a = getRawLightLevel(x, y, z);
		String rawLight = (a < 8 && colourLowLight ? "\247c" : "")+Integer.toString(a)+"\247r";
		a = getSunLight(x, y, z);
		String skyLight = (a < 8 && colourLowLight ? "\247c" : "")+Integer.toString(a)+"\247r";
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
		m = feety.matcher(s);
		s = m.replaceAll(n.format(yfeet));
		Pattern coordsZ = Pattern.compile("%z%", Pattern.CASE_INSENSITIVE);
		m = coordsZ.matcher(s);
		s = m.replaceAll(n.format(z1));
		Pattern indicators = Pattern.compile("%ind:([\\p{Alnum}\\p{Punct}&&[^\\\\ ]]+)%", Pattern.CASE_INSENSITIVE);
		m = indicators.matcher(s);
		while (m.find()) {
			String ind = m.group().replaceAll("%ind:|%", "").toLowerCase();
			s = s.replaceAll("%ind:"+ind+"%", getIdenString(ind)+"\247r");
		}
		m = localTime.matcher(s);
		s = m.replaceAll(getLocalTime(0));
		m = localTime12h.matcher(s);
		s = m.replaceAll(getLocalTime(1));
		m = sunLight.matcher(s);
		s = m.replaceAll(Integer.toString(getSunLight(x, y, z)));
		m = entities.matcher(s);
		s = m.replaceAll(mc.n().split(" ")[1].split("/")[0]);
		m = entities2.matcher(s);
		s = m.replaceAll(mc.n().split(" ")[1].replaceAll("\\.", ""));
		Pattern debug = Pattern.compile("%debug:([\\p{Alnum}\\p{Punct}&&[^\\\\ ]]+)%", Pattern.CASE_INSENSITIVE);
		m = debug.matcher(s);
		while (m.find()) {
			String dval = m.group().replaceAll("%debug:|%", "").toLowerCase();
			s = s.replaceAll("%debug:"+dval+"%", debugValue(dval)+"\247r");
		}
		Pattern count = Pattern.compile("%count:([\\p{Alnum}\\p{Punct}&&[^\\\\ ]]+)%", Pattern.CASE_INSENSITIVE);
		m = count.matcher(s);
		while (m.find()) {
			String bdata = m.group().replaceAll("%count:|%", "").toLowerCase();
			s = s.replaceAll("%count:"+bdata+"%", formatCount(bdata)+"\247r");
		}

		Pattern formats = Pattern.compile("&([0123456789abcdefklmnor])", Pattern.CASE_INSENSITIVE);
		m = formats.matcher(s);
		while (m.find()) {
			String c = m.group().replaceAll("&", "").toLowerCase();
			s = s.replaceAll("&"+c, "\247"+c);
		}

		return s;
	}

	public String formatCount(String name) {
		wm[] inventory = getPlayerInventory().a;
		if (name.startsWith("slot")) {
			int slot = Integer.valueOf(name.replaceAll("slot", ""));
			wm item = inventory[slot];
			String itemName = "";
			if ((itemName = getItemName(item)).equals(""))
				return "Nothing in slot "+slot;
			return itemName+": "+getNumItemsInSlot(slot);
		}
		int id = -1;
		try {
			id = Integer.valueOf(name);
		}
		catch (NumberFormatException e) { }
		for (wm item : inventory) {
			if (item != null && (id > -1 && getItemId(item) == id) || getInternalNameForItem(item).equals(name) || getItemName(item, true).contains(name)) {
				return getItemName(item)+": "+getItemQuantity(getInternalItemName(item));
			}
		}
		return "";
	}

	public int getItemQuantity(String internalName) {
		wm[] inventory = getPlayerInventory().a;
		int count = 0;
		for (int x = 0; x < inventory.length; x++)
			if (getInternalItemName(inventory[x]).equals(internalName))
				count += getNumItemsInSlot(x);
		return count;
	}

	public wm getItemInSlot(int slot) {
		try {
			return getPlayerInventory().a[slot];
		}
		catch (NullPointerException e) {
			return null;
		}
	}

	public int getNumItemsInSlot(int slot) {
		try {
			return getPlayerInventory().a[slot].a;
		}
		catch (NullPointerException e) {
			return 0;
		}
	}

	public String getItemName(wm item) {
		return getItemName(item, false);
	}

	public String getItemName(wm item, boolean lower) {
		try {
			if (lower)
				return item.s().toLowerCase();
			else
				return item.s();
		}
		catch (NullPointerException e) { 
			return ""; 
		}
	}

	public int getItemId(wm item) {
		return item.c;
	}

	public String getInternalItemName(wm item) {
		return getInternalNameForItem(item);
	}

	public String getInternalNameForItem(wm item) {
		try {
			return item.a();
		} 
		catch (NullPointerException e) { return ""; }
	}

	public wm getHeldItem() {
		return getPlayerInventory().h();
	}

	public int getHeldItemID() {
		try {
			return getHeldItem().c;
		}
		catch (NullPointerException e) { return -1; }
	}

	public so getPlayerInventory() {
		return entityPlayer().bK;
	}

	public boolean itemHasEnchant(int enchantID) {
		return itemHasEnchant(enchantID, getHeldItem());
	}

	public wm[] getPlayerArmour() {
		return entityPlayer().ad();
	}

	public boolean itemHasEnchant(int enchantID, wm wg) {
		return zb.a(51, wg) > 0;
	}

	public String getInternalItemNameForSlot(int slot) {
		return getPlayerInventory().a[slot].a();
	}

	public boolean isSlotEmpty(int slot) {
		return getPlayerInventory().a[slot] == null;
	}

	private String debugValue(String v) {
		if (v.equals("arrows")) {
			if (getHeldItemID() != 261)
				return "Not holding a bow.";
			wm[] items = getPlayerInventory().a;
			int arrows = 0;
			String arr = "Arrows: ";
			if (itemHasEnchant(51, getHeldItem()) || isCreative())
				return arr+"Unlimited";
			for (int x1 = 0; x1 < items.length; x1++) {
				if (!isSlotEmpty(x1) && getInternalItemNameForSlot(x1).equals("item.arrow"))
					arrows += items[x1].a;
			}
			return arr+arrows;
		}
		else if (v.equals("armour")) {
			wm[] armour = getPlayerArmour();
			String o = "";
			for (int i = armour.length-1; i > -1; i--) {
				if (armour[i] != null)
					o = o+(o.length() > 0 ? ", " : "")+armour[i].s();
			}
			if (o.equals(""))
				return "no armour.";
			return o;
		}
		else if (v.equals("helditem")) {
			try {
				wm itemStack = getHeldItem();
				if (itemStack == null)
					return "Nothing";
				// Item internal name: a()
				// Item name: r()
				// Stack quantity: a
				// Item ID: c
				// Item durability: k()
				// Item used durability: j()
				// Item data: i()
				// Has been used: h()
				// Is Enchanted or Named: o()
				String itemName = itemStack.s();
				int itemMaxDur = itemStack.l();
				int itemCurrDur = (itemMaxDur - itemStack.k());
				int itemData = itemStack.l();
				int itemID = itemStack.c;
				boolean hasData = itemStack.g();
				if (debugClassPresent && debugActive)
					return "N: "+itemName+", MaxDur: "+itemMaxDur+", CurrDur: "+itemCurrDur+", Data: "+itemData+", ID: "+itemID+", HasData: "+hasData;
				if (itemMaxDur > 0) {
					return itemName+": "+new DecimalFormat("##").format(((double)itemCurrDur/itemMaxDur)*100.00)+"% durability, "+itemCurrDur+" uses.";
				}
				return itemName +" ("+itemID+(hasData ? ":"+itemData : "")+")";
			}
			catch (Exception e) { return e.toString(); }
		}

		return "Invalid";
	}

	private String getLocalTime(int mode) {
		calendar.setTime(new Date(System.currentTimeMillis()));
		String time = calendar.getTime().toString().split(" ")[3];
		boolean PM = false;
		if (mode == 1) {
			int a = Integer.valueOf(time.split(":")[0]);
			if (a > 12) {
				a -= 12;
				PM = true;
			}
			return a+":"+time.split(":")[1]+":"+time.split(":")[2]+" "+(PM ? "PM" : "AM");
		}
		return calendar.getTime().toString().split(" ")[3];
	}

	private axr getGUI() {
		return mc.s;
	}
	
	private String getGUIClassName() {
		return (mc.s != null ? mc.s.toString().split("@")[0] : "null");
	}
	
	private bds getWorld() {
		try {
			return mc.e;
		}
		catch (NullPointerException n) {
			return null;
		}
	}

	public bjg sspServer() {
		return mc.D();
	}

	public String getWorldName() {
		if (!isMultiplayer())
			return sspServer().K();
		try {
			SocketAddress serverIP = entityPlayer().a.f().c();
			String serverAddress = serverIP.toString().split("/")[0];
			String serverPort = serverIP.toString().split(":")[1];
			if (serverAddress.length() == 0)
				return serverIP.toString().substring(1);
			return serverAddress+":"+serverPort;

		}
		catch (Exception e) {
			return "Unknown - "+e.toString();
		}
	}

	//	public String worldName() {
	//		return worldInstance().m()+", "+worldInstance().n();
	//	}

	public awv getFontRenderer() {
		return this.fontRenderer;
	}

	public axs getWindowSize() {
		return new axs(mc.z, mc.c, mc.d);
	}

	private boolean mcDebugOpen() {
		return getGameSettings().ab;
	}

	private avy getGameSettings() {
		return mc.z;
	}

	public boolean isMultiplayer() {
		if (useML)
			return !ModLoader.getMinecraftInstance().B();
		return !mc.B();
	}

	public int getSavedBlockLight(int x, int y, int z) {
		if (y < 0 || y > 255) 
			return 0;
		return getChunk(x, z).a(aam.b, x & 0xf, y, z & 0xf);
	}

	public int getRawLightLevel(int x, int y, int z) {
		if (y < 0 || y > 255) 
			return 0;
		return getChunk(x, z).c(x & 0xf, y, z & 0xf, 0);
	}

	public int getSunLight(int x, int y, int z) {
		if (y < 0 || y > 255)
			return 0;
		return getChunk(x, z).a(aam.a, x & 0xf, y, z & 0xf);
	}

	public int getBlockLight(int i, int j, int k) {
		if (j < 0 || j > 255)
			return 0;
		return getChunk(i, k).a(aam.b, i & 0xf, j, k & 0xf);
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

	private aba getBiomeGenBase() {
		return getWorld().u();
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
		return getWorld().l(x, y, z);
	}

	public void sendChatMessage(String t) {
		entityPlayer().a(t);
	}

	public bdv entityPlayer() {
		return mc.g;
	}

	public ng thePlayer() {
		return mc.h;
	}

	public beu playerEntity() {
		return mc.j;
	}

	public String getPlayerName() {
		return entityPlayer().am();
	}

	public bdr getPlayerController() {
		return mc.b;
	}

	public boolean isCreative() {
		return !getPlayerController().b();
	}

	public ajv worldInfo() {
		return getWorld().x;
	}

	protected Minecraft getMCInstance() {
		return mc;
	}

	private abw getChunk(int x, int z) {
		return getWorld().d(x, z);
	}

	private int getDimension() {
		return getWorldProvider().h;
	}

	private boolean canSlimesSpawnHere(int x, int z) {
		if (isSeedSet()) {
			abw chunk = getChunk(x, z);
			int g = chunk.g;
			int h = chunk.h;
			return new Random(getWorldSeed() + (long)(g * g * 0x4c1906) + (long)(g * 0x5ac0db) + (long)(h * h) * 0x4307a7L + (long)(h * 0x5f24f) ^ 0x3ad8025fL).nextInt(10) == 0;
		}
		return (getChunk(x, z).a(0x3ad8025fL).nextInt(10) == 0 && getWorldSeed() != 0L)/* || (getBiome(x, z).startsWith("Swamp") && getLightLevel(x, getPlayerCoordinates()[1], x) < 8)*/;
	}

	private acn getWorldProvider() {
		return getWorld().t;
	}

	private abt getChunkProvider() {
		return getWorldProvider().c();
	}

	public long getWorldTime() {
		return getWorld().I();
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
		return thePlayer().bz();
	}

	public int[] getPlayerCoordinates() {
		int[] a = {kx.c(thePlayer().u), kx.c(thePlayer().v - 1), kx.c(thePlayer().w), kx.c((double)((thePlayer().A * 4F) / 360F) + 0.5D) & 3, (int)thePlayer().u, (int)thePlayer().v, (int)thePlayer().w};
		return a;
	}

	public double[] getPlayerCoordinatesAsDouble() {
		double[] a = {thePlayer().u, thePlayer().v, thePlayer().w, thePlayer().E.b, kx.c((double)((thePlayer().A * 4F) / 360F) + 0.5D) & 3};
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
		return mc.L;
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
		//int textpos = (WMLLI > 5 && classicOutput ? -8 : 2);
		int textpos = 2;
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
			options.setProperty("Compat-Rei", Boolean.toString(ReiEnabled));
			options.setProperty("Compat-Zans", Boolean.toString(ZansEnabled));
			options.setProperty("Compat-Alien", Boolean.toString(AlienEnabled));
			options.setProperty("Compat-Forge", Boolean.toString(forgeEnabled));
			options.setProperty("showUnderGUIs", Boolean.toString(showUnderGUIs));
			options.setProperty("showWorldName", Boolean.toString(showWorldName));
			options.setProperty("renderArmourDisplay", Boolean.toString(renderArmourDisplay));
			options.setProperty("colourLowLight", Boolean.toString(colourLowLight));
			options.setProperty("forceAutohideObey", Boolean.toString(forceAutohideObey));
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
			TextColour = Integer.parseInt(options.getProperty("TextColour", "-1"));
			F4Key = Integer.parseInt(options.getProperty("F4", "62"));
			clockSetting = Integer.parseInt(options.getProperty("clockSetting", "2"));
			outputLocation = Integer.parseInt(options.getProperty("OutputLocation", "0"));
			wmllOverrideF3 = Boolean.parseBoolean(options.getProperty("OverrideIngameF3", "false")); // Now defaults to false due to Minecraft's native Shift + F3
			F3Type = Integer.parseInt(options.getProperty("F3Type", "0"));
			showSeedWithCoords = Boolean.parseBoolean(options.getProperty("showSeedWithCoords", "true"));
			classicOutput = Boolean.parseBoolean(options.getProperty("classicOutput", "false"));
			autoUpdateCheck = Boolean.parseBoolean(options.getProperty("autoUpdateCheck", "true"));
			satBar = Boolean.parseBoolean(options.getProperty("SaturationBar", "false"));
			ReiEnabled = Boolean.valueOf(options.getProperty("Compat-Rei", "true"));
			AlienEnabled = Boolean.valueOf(options.getProperty("Compat-Alien", "true"));
			ZansEnabled = Boolean.valueOf(options.getProperty("Compat-Zans", "true"));
			forgeEnabled = Boolean.valueOf(options.getProperty("Compat-Forge", "true"));
			showUnderGUIs = Boolean.valueOf(options.getProperty("showUnderGUIs", "true"));
			showWorldName = Boolean.valueOf(options.getProperty("showWorldName", "true"));
			renderArmourDisplay = Boolean.valueOf(options.getProperty("renderArmourDisplay", "false"));
			colourLowLight = Boolean.valueOf(options.getProperty("colourLowLight", "true"));
			forceAutohideObey = Boolean.valueOf(options.getProperty("forceAutohideObey", "false"));
			debug("[WMLL] Loaded options.");
			//debug(options.toString()+"\n"+outputOptions.toString());
			if (firstRun || updatedFormat)
				saveOptions();
		}
		catch (Exception e) { debug("[WMLL] Unable to load options: "+e.getMessage()); }
	}

	public String getPlayerDirection() {
		return getPlayerDirection(getPlayerCoordinates()[3]);
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
		if (Keyboard.isKeyDown(F4Key) && System.currentTimeMillis() - lastF4Press > 250) {
			lastF4Press = System.currentTimeMillis();
			if (Keyboard.isKeyDown(29) && mc.s == null)
				mc.a(new WMLLOptionsMenu(this));
			else
				if (getGUI() == null) {
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
		getGameSettings().ab = false;
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

	public int getYear() {
		String a = getCalendarDate(2);
		return Integer.parseInt(a.substring(a.length() - 4));
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

	@SuppressWarnings("unused")
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
		Boolean v = blockBlackList.contains(b) || apa.w[b]; // getBlock
		String s = "\247cInvalid indicator! Valid types are: \247omobs, animals, slimes, crops, trees \247r\247cor\247o mushrooms\247r\247c.";
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
				if ((c && y + 1 > 40) || (l > 7 && bi.startsWith("Swamp")) && !v)
					s = "\247eSlimes";
				else if (c && y + 1 < 41 && v)
					s = "\247cSlimes";
				else if ((c && y + 1 < 41) || (l < 8 && bi.startsWith("Swamp")) && !v)
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
		//		else if (i.equals("wither") && debugClassPresent) { // Disabled for end-users, not working.
		//			//TODO
		//			if (d > -1)
		//				s = "\247cWither Skeletons";
		//			else {
		//				try {
		//					aaz cp = (aaz)getChunkProvider();
		//					yc structureGen = (yc)cp.c;
		//					boolean a = structureGen.a(x >> 4, z >> 4);
		//					//boolean a = canStructureSpawnHere(netherStructure.b, x, z);
		//					//					s = (a ? "\247a" : "\247c")+"Wither Skeletons";
		//					s = "\247c"+a+", "+x+", "+(y - 1)+", "+z+" | "+getWorldSeed();
		//				}
		//				catch (NullPointerException e) {
		//					s = "\247cWither Skeletons (NPE)";
		//					//e.printStackTrace();
		//				}
		//				catch (Exception e) {
		//					s = "\247cWither Skeletons (GE)";
		//				}
		//			}
		//		}
		return s+(debugActive ? ", "+l+", "+v+", "+sk : "");
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
			reiError = e.toString();
			break;
		case 3:
			System.err.println("[WMLL] Error creating Alien Radar Compatibility.");
			AlienRadar = false;
			printStackTrace(e);
			alienError = e.toString();
			break;
		case 4:
			System.err.println("[WMLL] Error creating Zan's Minimap Compatibility.");
			ZansMinimap = false;
			printStackTrace(e);
			zanError = e.toString();
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

	public boolean atLeast2True(boolean[] b) {
		int x = 0;
		for (int i = 0; i < b.length; i++)
			if (b[i])
				x++;
		return x > 1;
	}

	public void deleteSettingsFile() {
		settingsFile.delete();
	}

	public boolean shouldShow() {
		if (showUnderGUIs && (!useML || useML && forceAutohideObey))
			return true;
		else
			return (useML && (mc.s == null));
	}

	public void renderItemOnScreen(wm item, int x, int y) {
		GL11.glEnable(32826);
		avb.c();
		// I hate having to directly copy Minecraft's code.
		bhi b = new bhi();
		if(item == null)
		{
			return;
		}
		float f2 = (float)item.b - this.renderPartialTicks;
		if(f2 > 0.0F)
		{
			GL11.glPushMatrix();
			float f3 = 1.0F + f2 / 5F;
			GL11.glTranslatef(x + 8, y + 12, 0.0F);
			GL11.glScalef(1.0F / f3, (f3 + 1.0F) / 2.0F, 1.0F);
			GL11.glTranslatef(-(x + 8), -(y + 12), 0.0F);
		}
		b.b(mc.q, mc.p, item, x, y);
		if(f2 > 0.0F)
		{
			GL11.glPopMatrix();
		}
		b.c(mc.q, mc.p, item, x, y);
		avb.a();
		GL11.glDisable(32826);
	}

	public boolean canStructureSpawnHere(int x, int z) {
		return canStructureSpawnHere(new Random(), x, z);
	}

	public boolean canStructureSpawnHere(Random a, int x, int z) {
		//Random a = r;
		int cx = x >> 4;
						int cz = z >> 4;
			a.setSeed((long)(cx ^ cz << 4) ^ getWorldSeed());
			a.nextInt();
			return a.nextInt(3) != 0 ? false : (x != (cx << 4) + 4 + a.nextInt(8) ? false : z == (cz << 4) + 4 + a.nextInt(8));
	}

}
