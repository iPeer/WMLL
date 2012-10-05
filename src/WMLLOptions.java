
import org.lwjgl.input.Keyboard;

public class WMLLOptions extends ask {
		
	public WMLLOptions() {
		this.wmll = WMLL.i;
	}
	
	public WMLLOptions(ask parent) {
		this.parent = parent;
		this.wmll = WMLL.i;
	}
	
	public WMLLOptions(WMLL i) {
		this.wmll = i;
	}


	public WMLLOptions(WMLL wmll, ask parent) {
		this.wmll = wmll;
		this.parent = parent;
	}


	@SuppressWarnings({ "unchecked", "static-access" })
	public void A_() {
		h.clear();
		String debug = WMLL.WMLLDebugActive() == true ? "ON": "OFF";
		String ikey = Keyboard.getKeyName(wmll.F4Key);

		//String clockformat = wmll.clockSetting == 2 ? "24 hr" : "12 hr";
		byte offset = -16;
		/*
		 * New button
		 * aqz((int)ID, x, y[, width, height], text)
		 */
		h.add(new aqz(1, f / 2 - 100, g / 4 + 150 + offset, "Done"));
		h.add(new aqz(0, f / 2 - 100, g / 4 - 5 + offset, 98, 20, "Debug: "+debug));
		h.add(new aqz(2, f / 2 + 2, g / 4 - 5 + offset, 98, 20, "Cycle Key: "+ikey));
		//h.add(new aqz(5, f / 2 - 100, g / 4 + 20 + offset, 98, 20, (clockformat == "OFF" ? "Clock is " : "Time Format: ")+clockformat));
		h.add(new aqz(6, f / 2 + - 100, g / 4 + 20 + offset, 98, 20, "Images: "+(wmll.useImages ? "ON" : "OFF")));
		h.add(new aqz(7, f / 2 - 100, g / 4 + 105 + offset, "Check for updates: "+(wmll.autoUpdateCheck ? "Yes" : "No")));
		h.add(new aqz(3, f / 2 - 100, g / 4 + 130 + offset, "More options..."));
		h.add(new aqz(4, f / 2 - 100, g / 4 + 85 + offset, "Reset settings to defaults"));
		h.add(new aqz(9, f / 2 - 100, g / 4 + 65 + offset, "Auto acquire seed: "+(wmll.autoSeed ? "Yes" : "No")));
		//h.add(new aqz(4, f / 2 - 100, g / 4 + 130 + offset, "\247"+Integer.toHexString(TextColour)+"Text Colour"));
		String enabledString = "Enabled on "+(wmll.isMultiplayer() ? "this server" : "single player")+": "+(wmll.Enabled ? "Yes" : "No");
		h.add(new aqz(8, f / 2 - 100, g / 4 + 45 + offset, enabledString));
		if (wmll.debugClassPresent)
			h.add(new aqz(9001, f - 52, g - 22, 50, 20, "Reload"));
		if (!wmll.Enabled)
			for (int x = 3; x < 6; x++)
				((aqz)h.get(x)).h = false;
	}

	@SuppressWarnings("static-access")
	protected void a(aqz button) {
		if (button.f == 1) {
			wmll.optionsOpen = false;
			wmll.saveOptions();
			if (parent == null) {
				e.s = null;
				e.h();
			}
			else {
				e.a(parent);
			}
		}
		if (button.f == 0) {
			WMLL.toggleDebug();
			String debug = wmll.debugActive == true ? "ON": "OFF";
			button.e = "Debug: "+debug;
		}
		if (button.f == 2) {
			isBinding = true;
			button.e = "Press a key...";
		}
		if (button.f == 3) {
			e.a(new WMLLGuiOutputOptions(WMLL.i, this));
		}
		if (button.f == 4) {
			e.a(new WMLLYesNo(wmll, this));
		}
		if (button.f == 5) {
			int a = wmll.clockSetting;
			a++;
			if (a > 3)
				a = 1;
			button.e = (a < 3 ? "Time Format: " : "Clock is ")+ buttonTextForClockSetting(a);
			wmll.clockSetting = a;
		}
		if (button.f == 6) {
			wmll.useImages = !wmll.useImages;
			button.e = "Images: "+(wmll.useImages ? "ON" : "OFF");
		}
		if (button.f == 7) {
			boolean a = !wmll.autoUpdateCheck;
			button.e = "Check for updates: "+(a ? "Yes" : "No");
			if (!a && wmll.wmllUpdateCheck.running)
				wmll.wmllUpdateCheck.stop1();
			else if (a && wmll.wmllUpdateCheck.running)
				wmll.wmllUpdateCheck.start();
			wmll.autoUpdateCheck = a;
		}
		if (button.f == 8) {
			boolean a = wmll.Enabled;
			wmll.Enabled = !a;
			wmll.options.setProperty("World-"+wmll.getWorldName(), Boolean.toString(!a));
			for (int x = 3; x < 6; x++)
				((aqz)h.get(x)).h = !a;
			button.e = "Enabled on "+(wmll.getWorldName() == "MpServer" ? "this server" : "this world")+": "+(!a ? "Yes" : "No");
		}
		if (button.f == 9) {
			boolean a = !wmll.autoSeed;
			wmll.autoSeed = a;
			wmll.options.put("autoAcquireSeed", Boolean.toString(a));
			button.e = "Auto acquire seed: "+(a ? "Yes" : "No");
		}

		if (button.f == 9001) // Debug button
			e.a(new WMLLOptions(wmll));
	}

	private static String buttonTextForClockSetting(int i) {
		switch (i) {
		case 1:
			return "12 hr";
		case 2:
			return "24 hr";
		default:
			return "OFF";
		}
	}

	public String getStringForLightOption(int o) {
		switch (o) {
		case 0:
			return "Just Light";
		case 1:
			return "Light + Indicators";
		case 2:
			return "Light + FPS/Chunk Updates";
		case 3:
			return "Light + Coords, Facing, Biome & Seed";
//		case 4:
//			return "Light + Seed";
		case 4:
			return "Just Indicators";
		case 5:
			return "Just FPS/Chunk updates";
		case 6:
			return "Just Coords, Facing, Biome & Seed";
//		case 7: 
//			return "Just Seed";
		default:
			return "Nothing";
		}
	}

	protected void a(char c1, int i1) {
		if (i1 == Keyboard.KEY_ESCAPE && parent != null) {
			e.a(parent);
		}
		else if (isBinding && i1 != Keyboard.KEY_ESCAPE) {
			WMLL.F4Key = i1;
			isBinding = false;
			((aqz)h.get(2)).e = "Cycle Key: "+Keyboard.getKeyName(i1);
		}
		else {
			super.a(c1, i1);
		}
	}
	
	public void a() {

	}

	public void a(int i, int j, float f) {
		z_();
		// (fontrenderer, text, x, y, colour)
		a(k, title, this.f / 2, 20, 0xffffff);
		renderWMLLVersion();
		super.a(i, j, f);
	}
	
	public static void renderWMLLVersion() {
		WMLL wmll = WMLL.i;
		int r = wmll.getWindowSize().b();
		String ver = "WMLL "+WMLL.wmllVersion()+" ("+WMLL.getMinecraftVersion()+")";
		wmll.drawStringUsingPixels(ver, 2, r - 9, 0x444444);
		String[] a = (wmll.mc.r).toString().split("@");
		wmll.drawStringUsingPixels("GUI: "+a[0]+" (@"+a[1]+")", 2, r - 18, 0x333333);
	}
	
	public ask getParent() {
		return parent;
	}

	private String title = "WMLL Configuration";;
	private WMLL wmll;
	public ask parent;
	public static boolean isBinding;

}