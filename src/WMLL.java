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

public class WMLL 
{

	public WMLL() 
	{
		System.out.println("[WMLL] Attempting to load options from "+PropFile+"...");
		loadOptions();
		F4Key = Integer.parseInt(options.getProperty("F4", "62"));
		WMLLI = Integer.parseInt(options.getProperty("WMLLI", "0"));
		WMLLD = Integer.parseInt(options.getProperty("WMLLD", "0"));
		FirstRun = Integer.parseInt(options.getProperty("FirstRun", "0"));
		TextColour = Integer.parseInt(options.getProperty("TextColour", "15"));
		clockSetting = Integer.parseInt(options.getProperty("clockSetting", "2"));
		useImages = Boolean.parseBoolean(options.getProperty("useImages", "false"));
		location = Integer.parseInt(options.getProperty("OutputLocation", "0"));
		if (WMLLD > 0) {
			showlight = false;
		}
		showlight = WMLLI > 4 ? false : true;
		DebugActive = WMLLD == 1 ? true : false;
		lastKeyPressTime = 0;
		try {
			Rei = ReiMinimap.instance != null ? true : false;
			ReiUseMl = ReiMinimap.instance.useModloader;
		}
		catch (NoClassDefFoundError e) {
			System.out.println("[WMLL] Rei's Minimap class(es) not found");
			Rei = ReiUseMl = false;
		}
		catch (VerifyError v) {
			System.out.println("[WMLL] Unable to verify Rei's Minimap");
			Rei = ReiUseMl = false;
		}
		System.out.println("[WMLL] Rei's Minimap: "+Rei+" ("+ReiUseMl+")");
	}

	public void doInit(Minecraft minecraft) {
		mc = minecraft;
		hasInit = true;
		wmllRenderer = new WMLLRenderer(minecraft, i);
	}

	public void updategui(Minecraft minecraft) 
	{
		if (!hasInit) { doInit(minecraft); }
		if (FirstRun < 2) {
			SendMessageToChat("You can configure WMLL by pressing "+Keyboard.getKeyName(29)+" + "+Keyboard.getKeyName(F4Key), 'e');
			FirstRun = 2;
			saveOptions();
		}
		if (Rei && !ReiUseMl)
			ReiMinimap.instance.onTickInGame(minecraft);
		if (drawtext == null) drawtext = minecraft.q;	
		if (minecraft.A.F) {
			drawtext.a((new StringBuilder()).append("WMLL: ").append(WMLLVER).toString(), 2, 52, 0xffffff);
		}
		else {
			if (Keyboard.isKeyDown(F4Key) && Keyboard.isKeyDown(29) && System.currentTimeMillis() - lastKeyPressTime > 150L && minecraft.s == null) {
				//SendMessageToChat("Please press the key you wish to bind the data key to.", 'f');
				lastKeyPressTime = System.currentTimeMillis();
				optionsOpen = true;
				minecraft.a(new WMLLOptions(i));
			}
			else if (Keyboard.isKeyDown(F4Key) && System.currentTimeMillis() - lastKeyPressTime > 150L && WMLLD == 0 && minecraft.s == null) {
				lastKeyPressTime = System.currentTimeMillis();
				if (WMLLI < moi+1) { 
					if (Keyboard.isKeyDown(42)) {
						WMLLI = (WMLLI - 1);
						if (WMLLI < 0)
							WMLLI = 9;
					}
					else {
						WMLLI = (WMLLI + 1);
					}
				}
				else WMLLI = 1;
				showlight = WMLLI > 4 ? false : true;
						saveOptions();
			}
			if (world != getWorld()) 
				world = minecraft.f;
			if (size == null || size != getGameWindowDimensions()) {
				size = getGameWindowDimensions();
			}
			double[] pC = {minecraft.h.o, minecraft.h.p, minecraft.h.q, minecraft.h.u};
			playerX = gh.c(pC[0]);
			playerY = gh.c(pC[1] - 1.0D);
			playerYBase = gh.c(pC[1]);
			playerYN2 = gh.c(pC[1] - 2.0D);
			playerZ = gh.c(pC[2]);
			playerF = gh.c((double)((pC[3] * 4F) / 360F) + 0.5D) & 3;
			playerXF = (int)Math.floor(playerX);
			playerYF = (int)Math.floor(playerY);
			playerZF = (int)Math.floor(playerZ);
			//Light = minecraft.f.a(playerX, playerY, playerZ, true);
			//Light = world.c(i >> 4, l >> 4).c(i & 0xf, j, l & 0xf, 0);
			skylight = minecraft.f.a(1.0F);
			if (playerYF < 0 || playerYF > 255)
				Light = 0;
			else
				//Light = world.b(playerX, playerZ);
				//Light = minecraft.f.a(playerX, playerY, playerZ, true);
				Light = world.d(playerXF >> 4, playerZF >> 4).c(playerXF & 0xf, playerYF, playerZF & 0xf, skylight);
			//Light = world.n(playerXF, playerYF, playerZF);
			//Light = minecraft.f.c(playerXF >> 4, playerZF >> 4).c(playerXF & 0xf, playerYF, playerZF & 0xf, skylight);
			// r.f.y.c
			realm = world.t.g;
			isNether = realm == -1 ? true : false;
			isEnder = realm == 1 ? true : false;
			isNormalWorld = realm == 0 ? true : false;
			StandingOnID = minecraft.f.a(playerX, playerYN2, playerZ);
			biome = world.i().a((int)playerX, (int)playerZ).y;
			//temp = percent.format(world.i().a(playerX, playerY, playerZ));
			temp = percent.format(world.i().a((int)playerX, (int)playerZ).F);
			humid = percent.format(world.i().a((int)playerX, (int)playerZ).G);
			sleeping = minecraft.h.az();
			worldName = getWorldName();
			worldTime = world.x.f();
			worldTime1 = formatTime(worldTime);
			CanBlockSeeTheSky = world.m(playerX, playerY, playerZ);
			acd slime = world.c(playerX, playerZ);
			Enabled = Boolean.parseBoolean(options.getProperty("World-"+getWorldName(), "true"));
			boolean SWS = slime.a(0x3ad8025fL).nextInt(10) == 0;
			if (WMLLD == 1) {
				drawtext((new StringBuilder()).append("SOID: "+StandingOnID).toString(), 2, 2, 0xffffff);
				drawtext((new StringBuilder()).append("WMLLI: "+WMLLI).toString(), 2, 3, 0xffffff);
				drawtext((new StringBuilder()).append("VER: "+WMLLVER).toString(), 2, 4, 0xffffff);
				drawtext((new StringBuilder()).append("LIGHT: "+Light).toString(), 2, 5, 0xffffff);
				drawtext((new StringBuilder()).append("WORLDTIME: "+worldTime+" ("+worldTime1+")").toString(), 2, 6, 0xffffff);
				drawtext((new StringBuilder()).append("CTM: "+System.currentTimeMillis()).toString(), 2, 7, 0xffffff);
				drawtext((new StringBuilder()).append("SLEEPING?: "+sleeping).toString(), 2, 8, 0xffffff);
				skylight = minecraft.f.a(1.0F);
				drawtext((new StringBuilder()).append("SKYLIGHT: "+skylight).toString(), 2, 9, 0xffffff);
				drawtext((new StringBuilder()).append("CANSEESKY?: "+CanBlockSeeTheSky).toString(), 2, 10, 0xffffff);
				drawtext((new StringBuilder()).append("PROPFILE: "+PropFile.getAbsolutePath()).toString(), 2, 11, 0xffffff);
				drawtext((new StringBuilder()).append("ISNETHER?: "+isNether).toString(), 2, 12, 0xffffff);
				drawtext((new StringBuilder()).append("ISEND?: "+isEnder).toString(), 2, 13, 0xffffff);
				drawtext((new StringBuilder()).append("DIM: "+realm).toString(), 2, 14, 0xffffff);
				drawtext((new StringBuilder()).append("BINDING?: "+WMLLBindingMode).toString(), 2, 15, 0xffffff);
				drawtext((new StringBuilder()).append("KEY: "+Keyboard.getEventKey()).append(" / ").append(Keyboard.getKeyName(Keyboard.getEventKey())).toString(), 2, 16, 0xffffff);
				drawtext("REIMINIMAP: "+Rei+" ("+ReiUseMl+")", 2, 17, 0xffffff);
				drawtext("SLIMES?: "+SWS, 2, 18, 0xffffff);
				drawtext("TEXTPOS: "+textpos, 2, 19, 0xffffff);
				drawtext("SIZE: "+minecraft.d+"/"+minecraft.e, 2, 20, 0xffffff);
				drawtext("BIOME: "+biome, 2, 21, 0xffffff);
				drawtext.a("TL", 2, 2, 0xffffff);
				drawtext.a("BL", 2, size.b() - 10, 0xffffff);
				drawtext.a("TR", size.a() - drawtext.a("TR"), 2, 0xffffff);
				drawtext.a("BR", size.a() - drawtext.a("BR"), size.b() - 10, 0xffffff);
			}
			if (WMLLI < 9 && !DebugActive && Enabled) {
				if (WMLLI == 3 || WMLLI == 6) { 
					try {
						drawtext("X: "+getShortPos(pC[0]), 2, 2, 0xffffff);
						drawtext("Y: "+getShortPos(pC[1]), 2, 3, 0xffffff);
						drawtext("Z: "+getShortPos(pC[2]), 2, 4, 0xffffff);
						drawtext("Seed: "+world.v(), 2, 5, 0xffffff);
						drawtext("Biome: "+biome+" ("+temp+", "+humid+")",2, 6, 0xffffff);
						drawtext("Facing: "+GetPlayerFacing(playerF), 2, 1, 0xffffff); 
					}
					catch (Exception e) {
						drawtext("\247cUnable to display the requested information. - "+e.getMessage(), 2, 1, 0xffffff);
						drawtext("\247cDebug: "+pC[0]+" "+pC[1]+" "+pC[02]+" "+getShortPos(pC[0])+" "+getShortPos(pC[1])+" "+getShortPos(pC[2])+" "+biome+" "+playerF, 2, 2, 0xffffff);
					}
				}
				if (Arrays.asList(2, 5).contains(WMLLI)) { drawtext(minecraft.M, 2, 1, 0xffffff); }
				//if (WMLLI == 4 || WMLLI == 7) { drawtext("World Seed: "+world.v(), 2, 1, 0xffffff); }
				if (WMLLI < 4) {
					textpos = 2;
					if (useImages && !sleeping) {
						wmllRenderer.renderLightImage(Light/*, StandingOnID, realm, minecraft*/);
						drawtextUsingPixels((Light < 8 ? "\247c" : "")+Integer.toString(Light), 8, 3, 0xffffff);
					}
					else {
						if (sleeping) {
							if (!sleepingstringset)
								lightstring = sleepstrings[new Random().nextInt(sleepstrings.length)];
								sleepingstringset = true;
						}
						else {
							sleepingstringset = false;
							lightstring = "Light level: "+(Light < 8 ? "\247c" : "")+Light+(clockSetting < 3 ? " \247"+Integer.toHexString(TextColour)+"("+worldTime1+")" : "");
						}
						drawtext(lightstring, 2, 0, 0xffffff);
						//drawtext((new StringBuilder()).append(Light < 0 ? 0 : Light).toString(), 60, 0, Light > 7 ? 0xffffff : 0xff0000);
						//drawtext("("+biome+")", drawtext.a(lightstring) + 5, 0, 0xffffff);
					}
				}
				else {
					textpos = -8;
				}
				if (WMLLI == 1 || WMLLI == 4) {
					int mobs = 0xff0000, slimes = 0xff0000, trees = 0xff0000, crops = 0xff0000, animals = 0xff0000, blaze = 0xff0000;
					if (useImages) {
						wmllRenderer.renderIndicatorImages(Light, StandingOnID, realm, SWS, CanBlockSeeTheSky);
					}
					else {
						if (isNormalWorld) 
						{
							LabelMobs = "Mobs";
							//CanBlockSeeTheSky = world.l(playerX, playerY, playerZ);
							drawtext("Mushrooms", 40, 3, ((Light < 13 || StandingOnID == 110) && !blockblist.contains(StandingOnID) ? colourg : colourr));
							// INDENTATION IS IMPORTANT!11!!
							if (playerYBase < 41 && SWS) {
								slimes = colourg;
							}
							else if (playerYBase > 40 && SWS) {
								slimes = coloury;
							}
							if((!blockblist.contains(StandingOnID)) && (!biome.startsWith("MushroomIsland"))) {
								mobs = Light > 7 ? colourr : colourg;
							}
							if (StandingOnID == 2) // this else if was causing "else without if" errors without the brackets...
							{
								trees = Light > 8 ? colourg : colourr;
						if (!CanBlockSeeTheSky) 
							animals = Light > 8 ? colourg : colourr;
						else     
							animals = Light < 9 ? coloury : colourg;
							}
							if (StandingOnID == 60) 
								crops = Light > 8 ? colourg : colourr;

						if (StandingOnID == 3) 
							trees = Light > 8 ? colourg : colourr;

						}
						else if (isNether) 
						{
							SWS = true;
							CanBlockSeeTheSky = false;
							blaze = Light < 13 ? colourg : colourr;
							mobs = colourg;
							animals = colourg;
							slimes = colourg;
							LabelMobs = "Ghasts";
						}
						else if (isEnder) {
							CanBlockSeeTheSky = false;
							mobs = colourg;
							LabelMobs = "Endermen";
						}
						if (!isEnder) drawtext("Crops", 40, 1, crops);
						if (!isEnder) drawtext("Trees", 40, 2, trees);
						drawtext(LabelMobs, 2, 1, mobs);
						if (!isEnder) drawtext("Slimes", isNether ? 2 : 2, 3, slimes);
						if (isNether)
							drawtext("Blaze", 40, 3, blaze);
						if (!isEnder) drawtext(isNether ? "Pigmen" : "Animals", 2, 2, animals);

					}
				}
			}
		}
	}

	private void drawtextUsingPixels(String t, int i, int j, int k) {
		drawtext.a((k == 0xffffff ? "\247"+Integer.toHexString(TextColour) : "")+t, i, j, k);
	}

	public void drawtext (String t, int i, int j, int k) {
		t = (k == 0xffffff ? "\247"+Integer.toHexString(TextColour) : "")+t;
		if (location == 1) {
			drawtext.a(t, size.a() - (drawtext.a(t) + (i - 2)), textpos+(j*10), k);
			return;
		}
		else if (location == 2) {
			drawtext.a(t, i, size.b() - (textpos+(j*10) + 8), k);
			return;
		}
		else if (location == 3) {
			drawtext.a(t,  size.a() - (drawtext.a(t) + (i - 2)), size.b() - (textpos+(j*10) + 8), k);
			return;
		}
		drawtext.a(t, i, textpos+(j*10), k);
	}

	public String GetPlayerFacing(int f) {
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

	public void saveAndReloadOptions() {
		saveOptions();
		loadOptions();
	}

	public void saveOptions() {
		System.out.println("[WMLL] Attempting to save options...");
		try {
			options.setProperty("WMLLI", Integer.toString(WMLLI));
			options.setProperty("WMLLD", Integer.toString(WMLLD));
			options.setProperty("FirstRun", Integer.toString(FirstRun));
			options.setProperty("F4", Integer.toString(F4Key));
			options.setProperty("TextColour", Integer.toString(TextColour));
			options.setProperty("clockSetting", Integer.toString(clockSetting));
			options.setProperty("useImages", Boolean.toString(useImages));
			options.setProperty("OutputLocation", Integer.toString(location));
			options.store(new FileOutputStream(PropFile), "WMLL Config File - Do not edit unless you know what you're doing!");
			System.out.println("[WMLL] Options saved.");
		}
		catch (Exception e) { System.out.println("[WMLL] Unable to save options: "+e.getMessage()); }
	}

	public void loadOptions() {
		if (PropFile.exists()) {
			try {
				options.load(new FileInputStream(PropFile));
				System.out.println("[WMLL] Loaded options.");
			}
			catch (Exception e) { System.out.println("[WMLL] Unable to load options: "+e.getMessage()); }
		}
		else {
			System.out.println("[WMLL] Options file does not exist, running with defaults.");
			/*options.setProperty("WMLLI", Integer.toString(1));
			options.setProperty("WMLLD", Integer.toString(0));
			options.setProperty("FirstRun", Integer.toString(0));
			options.setProperty("F4", Integer.toString(62));
			options.setProperty("TextColour", Integer.toString(15));
			options.setProperty("24hrClock", Boolean.toString(true));
			System.out.println("[WMLL] Options file not found, default options set.");
			saveOptions();*/
		}
	}

	public void SendMessageToChat(String ChatMessage, char TextColour)
	{
		if (ChatMessage == null) ChatMessage = "WMLL: NO_CHAT_MESSAGE_ENETERED";
		mc.h.b((new StringBuilder()).append("\247").append(TextColour).append(ChatMessage).toString());
	}

	private wx getWorld() { return mc.f; }

	private String getShortPos(double p) {
		NumberFormat n = new DecimalFormat("#.00");
		return n.format(p);
	}

	public static void toggleDebug() {
		if (DebugActive) {
			DebugActive = false; 
			WMLLD--;
			//WMLLI = Integer.parseInt(i.options.getProperty("WMLLI"));
			//showlight = true;
		}
		else {
			DebugActive = true;
			//showlight = false;
			//WMLLI = 9;
			WMLLD++;
		}

	}

	public String formatTime(long time) {
		int h = (int)(((time + 6000L) % 24000L) / 1000L);
		int m = (int)(((time % 1000L) * 60L)  / 1000L);
		String AP = "AM";
		String out = "00:00";
		if (clockSetting == 2) {
			out = padToTwo(h)+":"+padToTwo(m);
		}
		else {
			if (h >= 12) {
				AP = "PM";
				h -= 12;
			}
			if (h == 0) {
				h = 12;
			}
			out = padToTwo(h)+":"+padToTwo(m)+" "+AP;
		}
		return out;

	}

	public String padToTwo(int i) {
		return (i < 10 ? "0"+i : i).toString();
	}
	
	public afw getGameWindowDimensions() {
		return new afw(mc.A, mc.d, mc.e);
	}
	
	public String getWorldName() {
		return mc.f.x.j();
	}

	public int skylight;
	public int Light;
	public int StandingOnID;
	public boolean SWS;
	public static int WMLLI;
	public static int WMLLD;
	long lastKeyPressTime;
	public static final String WMLLVER = "Test 502";
	public static File PropFile = new File(Minecraft.a("minecraft"), "WMLL.properties");
	public static int F4Key = 62;
	@SuppressWarnings("unused")
	private int colourw = 0xffffff, colourr = 0xff0000, coloury = 0xffff00, colourg = 0x00ff00;
	public boolean CanBlockSeeTheSky;
	public static int playerX, playerY, playerYBase, playerYN2, playerZ, playerF, playerXF, playerYF, playerZF;
	public boolean isNether = false, sleeping = false;
	public static nh drawtext;
	private boolean isEnder = false;
	public static boolean WMLLBindingMode = false;
	private Minecraft mc;
	private boolean hasInit = false;
	public static boolean DebugActive = false;
	private static int FirstRun = 0;
	private String LabelMobs = "Mobs";
	public boolean isNormalWorld = true;
	public static List<Integer> blockblist = Arrays.asList(0,8,9,20,44);
	public static WMLL i = new WMLL();
	private static boolean Rei, ReiUseMl;
	private static boolean showlight = true;
	private int textpos = 2;
	private int moi = 8, realm = 0;
	private String biome = "Unknown", lightstring= "Light level: 0", temp, humid;
	private wx world;
	public static boolean optionsOpen = false;
	Properties options = new Properties();
	public static int TextColour;
	long worldTime;
	String worldTime1 = "00:00";
	public static int clockSetting;
	public static boolean useImages;
	private WMLLRenderer wmllRenderer;
	public int location;
	public afw size;
	public String worldName = "Unknown";
	public boolean Enabled = true;
	private String[] sleepstrings = {"Goodnight, Hero!", "ZzzzzZZz", "That'sssssssss a very nice bed you have there...", "That bed looks comfy!", "My name is Kurt, and I will see you next time!", "...aaaaaannnnddd asleepness!", "Muuuuuuurrrrh", "*clank*", "*screech*"};
	private boolean sleepingstringset = false;
	NumberFormat percent = NumberFormat.getPercentInstance();
	
}