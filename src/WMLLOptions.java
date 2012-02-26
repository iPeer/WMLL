import org.lwjgl.input.Keyboard;

public class WMLLOptions extends vk {

	public WMLLOptions(WMLL i) {
		title = "WMLL Configuration";
		this.wmll = i;
	}


	@SuppressWarnings({ "unchecked", "static-access" })
	public void c() {
		s.clear();
		String debug = WMLL.WMLLDebugActive() == true ? "ON": "OFF";
		String ikey = Keyboard.getKeyName(wmll.F4Key);
		int TextColour = wmll.TextColour;
		String clockformat = "24 hr";
		int a = wmll.clockSetting;
		String locationString = "Location: "+(wmll.useImages ? getStringForLocation(0) : getStringForLocation(wmll.outputLocation));
		if (a < 3)
			clockformat = (a == 2 ? "24 hr" : "12 hr");
		else clockformat = "OFF";

		//String clockformat = wmll.clockSetting == 2 ? "24 hr" : "12 hr";
		byte offset = -16;
		/*
		 * New button
		 * abi((int)ID, x, y[, width, height], text)
		 */
		s.add(new abi(1, q / 2 - 100, r / 4 + 170 + offset, "Done"));
		s.add(new abi(0, q / 2 - 100, r / 4 - 5 + offset, 98, 20, "Debug: "+debug));
		s.add(new abi(2, q / 2 + 2, r / 4 - 5 + offset, 98, 20, "Cycle Key: "+ikey));
		s.add(new abi(5, q / 2 - 100, r / 4 + 20 + offset, 98, 20, (clockformat == "OFF" ? "Clock is " : "Time Format: ")+clockformat));
		s.add(new abi(6, q / 2 + 2, r / 4 + 20 + offset, 98, 20, "Images: "+(wmll.useImages ? "ON" : "OFF")));
		s.add(new abi(3, q / 2 - 100, r / 4 + 105 + offset, getStringForLightOption(wmll.WMLLI)));
		s.add(new abi(4, q / 2 - 100, r / 4 + 130 + offset, "\247"+Integer.toHexString(TextColour)+"Text Colour"));
		s.add(new abi(7, q / 2 - 56, r / 4 + 45 + offset, 112, 20, locationString));
		String enabledString = "Enabled on "+(wmll.getWorldName() == "MpServer" ? "SMP" : "this world")+": "+wmll.Enabled;
		int i = wmll.getFontRenderer().a(enabledString);
		s.add(new abi(8, (q - (i + 10)) / 2, r / 4 + 70 + offset, i + 10, 20, enabledString));
		for (int i1 = 4; i1 < 8; i1++) {
			((abi)s.get(i1)).h = wmll.Enabled;
		}
	}

	@SuppressWarnings("static-access")
	protected void a(abi button) {
		if (button.f == 1) {
			//p.a(null);
			wmll.optionsOpen = false;
			p.s = null;
			p.g();
			wmll.loadOptions();
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
			int i2 = wmll.WMLLI;
			i2++;
			if (i2 > 7)
				i2 = 0;
			button.e = getStringForLightOption(i2);
			wmll.WMLLI = i2;
		}
		if (button.f == 4) {
			int colourid = wmll.TextColour;
			colourid++;
			if (colourid > 15)
				colourid = 0;
			String prefix1 = "\247"+Integer.toHexString(colourid);
			wmll.TextColour = colourid;
			button.e = prefix1+"Text Colour";
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
			int loc = wmll.outputLocation;
			loc++;
			if (loc > 3)
				loc = 0;
			wmll.outputLocation = loc;
			button.e = "Location: "+getStringForLocation(loc);
		}
		if (button.f == 8) {
			boolean a = wmll.Enabled;
			wmll.Enabled = !a;
			wmll.options.setProperty("World-"+wmll.getWorldName(), Boolean.toString(!a));
			for (int i = 4; i < 8; i++) {
				((abi)s.get(i)).h = !a;
			}
			button.e = "Enabled on "+(wmll.getWorldName() == "MpServer" ? "SMP" : "this world")+": "+!a;
		}
		if (button.f > 0)
			wmll.saveOptions();
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
	
	public String getStringForLocation(int l) {
		switch (l) {
		case 0:
			return "Top left";
		case 1:
			return "Top right";
		case 2:
			return "Bottom left";
		default:
			return "Bottom right";
		}
	}

	protected void a(char c1, int i1) {
		if (isBinding) {
			WMLL.F4Key = i1;
			isBinding = false;
			((abi)s.get(2)).e = "Cycle Key: "+Keyboard.getKeyName(i1);
		}
		else {
			super.a(c1, i1);
		}
	}

	public void a(int i, int j, float f) {
		k();
		// (fontrenderer, text, x, y, colour)
		a(u, title, q / 2, 20, 0xffffff);
		a(u, "What to Display", q / 2, r / 4 + 80, 0xffffff);
		int a = (q - ((wmll.getFontRenderer().a(WMLL.WMLLVER) + 2) / 2));
		a(u, WMLL.WMLLVER, a, r - 9, 0x444444);
		super.a(i, j, f);
	}

	private String title;
	private WMLL wmll;
	public static boolean isBinding;

}