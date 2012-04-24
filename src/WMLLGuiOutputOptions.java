import java.util.Properties;

import org.lwjgl.input.Keyboard;


public class WMLLGuiOutputOptions extends wq {

	protected WMLL wmll;
	private wq parent;
	private String title;
	private static final String[] colourNames = {"Black", "Dark Blue", "Dark Green", "Cyan", "Red", "Purple", "Orange", "Light Grey", "Dark Grey", "Lavender?", "Lime Green", "Light Blue", "Bright Red", "Pink", "Yellow", "White"};
	private static final String[] outputLocations = {"Top Left", "Top Right", "Bottom Left", "Bottom Right"};
	public static Properties outputOptions;
	private acv F3TypeButton, SMPSeedButton;

	@SuppressWarnings("static-access")
	public WMLLGuiOutputOptions(WMLL wmll, wq parent) {
		this.wmll = wmll;
		this.parent = parent;
		this.outputOptions = WMLL.outputOptions;
		title = "WMLL Output Configuration";
	}

	@SuppressWarnings("unchecked")
	public void c() {
		s.clear();
		if (outputOptions == null)
			outputOptions = new Properties();
		int tc = WMLL.TextColour;
		byte o = -16;
		/*
		 * New button
		 * acv((int)ID, x, y[, width, height], text)
		 * y = y+25 for each button
		 */
		s.add(new acv(1, q / 2 - 112, r / 4 + 150 + o, 226, 20, "Done"));
		//s.add(new acv(0, q / 2 - 152, r / 4 + 150 + o, 98, 20, "<<"));
		//s.add(new acv(2, q / 2 + 2, r / 4 + 150 + o, 98, 20, ">>"));
		s.add(new acv(3, q / 2 - 112, r / 4 - 5 + o, 112, 20, outputLocations[WMLL.outputLocation]));
		s.add(new acv(4, q / 2 + 2, r / 4 - 5 + o, 112, 20, "\247"+Integer.toHexString(tc)+colourNames[tc]));
		if (WMLL.debugClassPresent)
			s.add(new acv(9001, q - 52, r - 22, 50, 20, "Reload"));
		s.add(new acv(5, q / 2 + 2, r / 4 + 100 + o, 112, 20, "Light Level options..."));
		s.add(new acv(6, q / 2 - 112, r / 4 + 20 + o, 112, 20, "Override F3: "+(wmll.wmllOverrideF3 ? "Yes" : "No")));
		//s.add(F3TypeButton = new acv(7, q / 2 + 2, r / 4 + 20 + o, 112, 20, "F3 Type: "+(wmll.F3Type == 1 ? "Alternate" : "Classic")));
		s.add(new acv(8, q / 2 + 2, r / 4 + 20 + o, 112, 20, "Seed w/ Coords: "+(wmll.showSeedWithCoords ? "Yes" : "No")));
		s.add(SMPSeedButton = new acv(9, q / 2 - 112, r / 4 + 125 + o, 226, 20, "Enter seed for this server..."));
		s.add(new acv(11, q / 2 - 112, r / 4 + 100 + o, 112, 20, "Enabled outputs..."));
		/*		s.add(new acv(2, q / 2 + 2, r / 4 + 45 + o, 98, 20, "f"));
		s.add(new acv(0, q / 2 - 100, r / 4 + 70 + o, 98, 20, "g"));
		s.add(new acv(2, q / 2 + 2, r / 4 + 70 + o, 98, 20, "h"));
		s.add(new acv(0, q / 2 - 100, r / 4 + 95 + o, 98, 20, "i"));
		s.add(new acv(2, q / 2 + 2, r / 4 + 95 + o, 98, 20, "j"));*/
		s.add(new acv(10, q / 2 - 112, r / 4 + 75 + o, 226, 20, "Show: "+getStringForLightOption(WMLL.WMLLI)));
		//F3TypeButton.h = false;
		SMPSeedButton.i = wmll.isMultiplayer() || WMLL.debugClassPresent;

	}

	protected void a(acv b) {
		if (b.f == 1 || b.f == 2) {
			if (b.f == 2) { // Next
				System.out.println("NO GUI YET!");
			}
			else // Done
				p.a(parent);
			WMLL.outputOptions = outputOptions;
		}
		else if (b.f == 3) { // Output location
			int a = WMLL.outputLocation;
			a++;
			if (a > 3)
				a = 0;
			b.e = outputLocations[a];
			WMLL.outputLocation = a;
		}
		else if (b.f == 4) { // Text Colour
			int a = WMLL.TextColour;
			a++;
			if (a > 15)
				a = 0;
			b.e = "\247"+Integer.toHexString(a)+colourNames[a];
			WMLL.TextColour = a;
		}
		else if (b.f == 6) { // F3 override
			wmll.wmllOverrideF3 = !wmll.wmllOverrideF3;
			b.e = "Override F3: "+(wmll.wmllOverrideF3 ? "Yes" : "No");
		}
		else if (b.f == 5) { // Light level options
			p.a(new WMLLGuiLightLevel(wmll, this));
		}
		else if (b.f == 7) {
			int a = wmll.F3Type;
			a++;
			if (a > 1)
				a = 0;
			wmll.F3Type = a;
			b.e = "F3 Type: "+(a == 1 ? "Alternate" : "Classic");
		}
		else if (b.f == 8) {
			boolean a = wmll.showSeedWithCoords;
			a = !a;
			wmll.showSeedWithCoords = a;
			b.e = "Seed w/ Coords: "+(a ? "Yes" : "No");
		}
		else if (b.f == 9) {
			p.a(new WMLLGuiSMPSeed(wmll, this));
		}
		else if (b.f == 10) {
			int i2 = WMLL.WMLLI;
			i2++;
			if (i2 > 11)
				i2 = 0;
			b.e = "Show: "+getStringForLightOption(i2);
			WMLL.WMLLI = i2;
		}
		else if (b.f == 11) {
			p.a(new WMLLEnabledOutputs(wmll, this));
		}
		else if (b.f == 9001) // Debug button
			p.a(new WMLLGuiOutputOptions(wmll, parent));
	}

	protected void a(char c, int i) {
		if (Keyboard.KEY_ESCAPE == i)
			p.a(parent);
		else
			super.a(c, i);
	}

	public void a(int i, int j, float f) {
		r_();
		// (fontrenderer, text, x, y, colour)
		a(u, title, q / 2, 20, 0xffffff);
		WMLLOptions.renderWMLLVersion();
		super.a(i, j, f);
	}

	public void e() {
		Keyboard.enableRepeatEvents(false);
	}

	// Begin custom functions

	public String getStringForLightOption(int o) {
		switch (o) {
		case 0:
			return "Just Light";
		case 1:
			return "Light + Indicators";
		case 2:
			return "Light + FPS/Chunk Updates";
		case 3:
			return "Light & Compass";
		case 4:
			return "Light, Indicators & Compass";
		case 5:
			return "Light, FPS & Coordinates";
		case 6:
			return "Just Indicators";
		case 7:
			return "Just FPS/Chunk updates";
		case 8:
			return "Just Compass";
			//		case 7: 
			//			return "Just Seed";
		case 9:
			return "Just Indicators and Compass";
		case 10:
			return "Just FPS & Coordinates";
		default:
			return "Nothing";
		}
	}

}
