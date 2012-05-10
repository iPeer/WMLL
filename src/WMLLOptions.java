import org.lwjgl.input.Keyboard;

public class WMLLOptions extends zp {
		
	public WMLLOptions() {
		this.wmll = WMLL.i;
	}
	
	public WMLLOptions(zp parent) {
		this.parent = parent;
		this.wmll = WMLL.i;
	}
	
	public WMLLOptions(WMLL i) {
		this.wmll = i;
	}


	public WMLLOptions(WMLL wmll, zp parent) {
		this.wmll = wmll;
		this.parent = parent;
	}


	@SuppressWarnings({ "unchecked", "static-access" })
	public void c() {
		s.clear();
		String debug = WMLL.WMLLDebugActive() == true ? "ON": "OFF";
		String ikey = Keyboard.getKeyName(wmll.F4Key);
		String clockformat = "24 hr";
		int a = wmll.clockSetting;
		if (a < 3)
			clockformat = (a == 2 ? "24 hr" : "12 hr");
		else clockformat = "OFF";

		//String clockformat = wmll.clockSetting == 2 ? "24 hr" : "12 hr";
		byte offset = -16;
		/*
		 * New button
		 * agk((int)ID, x, y[, width, height], text)
		 */
		s.add(new agk(1, q / 2 - 100, r / 4 + 150 + offset, "Done"));
		s.add(new agk(0, q / 2 - 100, r / 4 - 5 + offset, 98, 20, "Debug: "+debug));
		s.add(new agk(2, q / 2 + 2, r / 4 - 5 + offset, 98, 20, "Cycle Key: "+ikey));
		s.add(new agk(5, q / 2 - 100, r / 4 + 20 + offset, 98, 20, (clockformat == "OFF" ? "Clock is " : "Time Format: ")+clockformat));
		s.add(new agk(6, q / 2 + 2, r / 4 + 20 + offset, 98, 20, "Images: "+(wmll.useImages ? "ON" : "OFF")));
		s.add(new agk(3, q / 2 - 100, r / 4 + 125 + offset, "Output options..."));
		s.add(new agk(4, q / 2 - 100, r / 4 + 85 + offset, "Reset settings to defaults"));
		//s.add(new agk(4, q / 2 - 100, r / 4 + 130 + offset, "\247"+Integer.toHexString(TextColour)+"Text Colour"));
		String enabledString = "Enabled on "+(wmll.getWorldName() == "MpServer" ? "SMP" : "this world")+": "+(wmll.Enabled ? "Yes" : "No");
		int i = wmll.getFontRenderer().a(enabledString);
		s.add(new agk(8, (q - (i + 10)) / 2, r / 4 + 45 + offset, i + 10, 20, enabledString));
		if (WMLL.debugClassPresent)
			s.add(new agk(9001, q - 52, r - 22, 50, 20, "Reload"));
		if (!wmll.Enabled)
			for (int x = 3; x < 6; x++)
				((agk)s.get(x)).h = false;
	}

	@SuppressWarnings("static-access")
	protected void a(agk button) {
		if (button.f == 1) {
			wmll.optionsOpen = false;
			wmll.saveOptions();
			if (parent == null) {
				p.s = null;
				p.g();
			}
			else {
				p.a(parent);
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
			p.a(new WMLLGuiOutputOptions(WMLL.i, this));
		}
		if (button.f == 4) {
			p.a (new WMLLYesNo(wmll, this));
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
		if (button.f == 8) {
			boolean a = wmll.Enabled;
			wmll.Enabled = !a;
			wmll.options.setProperty("World-"+wmll.getWorldName(), Boolean.toString(!a));
			for (int x = 3; x < 6; x++)
				((agk)s.get(x)).h = !a;
			button.e = "Enabled on "+(wmll.getWorldName() == "MpServer" ? "SMP" : "this world")+": "+(!a ? "Yes" : "No");
		}

		if (button.f == 9001) // Debug button
			p.a(new WMLLOptions(wmll));
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
			p.a(parent);
		}
		else if (isBinding && i1 != Keyboard.KEY_ESCAPE) {
			WMLL.F4Key = i1;
			isBinding = false;
			((agk)s.get(2)).e = "Cycle Key: "+Keyboard.getKeyName(i1);
		}
		else {
			super.a(c1, i1);
		}
	}
	
	public void a() {

	}

	public void a(int i, int j, float f) {
		t_();
		// (fontrenderer, text, x, y, colour)
		a(u, title, q / 2, 20, 0xffffff);
		renderWMLLVersion();
		super.a(i, j, f);
	}
	
	public static void renderWMLLVersion() {
		WMLL wmll = WMLL.i;
		int r = wmll.getWindowSize().b();
		String ver = "WMLL "+WMLL.wmllVersion()+" ("+WMLL.getMinecraftVersion()+")";
		wmll.drawStringUsingPixels(ver, 2, r - 9, 0x444444);
	}
	
	public zp getParent() {
		return parent;
	}

	private String title = "WMLL Configuration";;
	private WMLL wmll;
	public zp parent;
	public static boolean isBinding;

}