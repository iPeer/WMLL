
import java.util.Properties;

import org.lwjgl.input.Keyboard;


public class WMLLGuiOutputOptions extends asw {

	protected WMLL wmll;
	private asw parent;
	private String title;
	private static final String[] colourNames = {"Black", "Dark Blue", "Dark Green", "Cyan", "Red", "Purple", "Orange", "Light Grey", "Dark Grey", "Lavender?", "Lime Green", "Light Blue", "Bright Red", "Pink", "Yellow", "White"};
	private static final String[] outputLocations = {"Top Left", "Top Right", "Bottom Left", "Bottom Right"};
	public static Properties outputOptions;
	private arl SMPSeedButton, showButton, eOutputsButton;

	@SuppressWarnings("static-access")
	public WMLLGuiOutputOptions(WMLL wmll, asw parent) {
		this.wmll = wmll;
		this.parent = parent;
		this.outputOptions = WMLL.options;
		title = "WMLL Configuration";
	}

	@SuppressWarnings("unchecked")
	public void A_() {
			h.clear();
			if (outputOptions == null)
				outputOptions = new Properties();
			int tc = WMLL.TextColour;
			byte o = -16;
			if (wmll.debugClassPresent)
				h.add(new arl(9001, f - 52, g - 22, 50, 20, "Reload"));
			h.add(new arl(1, f / 2 - 112, g / 4 + 150 + o, 226, 20, "Done"));
			h.add(new arl(3, f / 2 - 112, g / 4 - 5 + o, 112, 20, outputLocations[WMLL.outputLocation]));
			h.add(new arl(4, f / 2 + 2, g / 4 - 5 + o, 112, 20, "\247"+Integer.toHexString(tc)+colourNames[tc]));
			h.add(new arl(5, f / 2 - 112, g / 4 + 75 + o, 226, 20, "Customize output"));
			h.add(new arl(12, f / 2 - 112, g / 4 + 45 + o, 226, 20, "Enable Saturation bar Compatibility: "+(wmll.satBar ? "Yes" : "No")));
			h.add(new arl(6, f / 2 - 112, g / 4 + 20 + o, 112, 20, "Override F3: "+(wmll.wmllOverrideF3 ? "Yes" : "No")));
			h.add(new arl(8, f / 2 + 2, g / 4 + 20 + o, 112, 20, "Seed w/ Coords: "+(wmll.showSeedWithCoords ? "Yes" : "No")));
			h.add(new arl(7, f / 2 + 2, g / 4 + 100 + o, 112, 20, "Output type: "+(wmll.classicOutput ? "Classic" : "Custom")));
			h.add(SMPSeedButton = new arl(9, f / 2 - 112, g / 4 + 100 + o, 112, 20, "Enter seed"));
			h.add(eOutputsButton = new arl(11, f / 2 - 112, g / 4 + 125 + o, 226, 20, "Enabled outputs..."));
			h.add(showButton = new arl(10, f / 2 - 112, g / 4 + 50 + o, 226, 20, "Show: "+getStringForLightOption(WMLL.WMLLI)));
			SMPSeedButton.g = wmll.isMultiplayer() || !wmll.autoSeed;
			eOutputsButton.h = showButton.h = (WMLL.useImages || wmll.classicOutput);
	}

	protected void a(arl b) {
		if (b.f == 1 || b.f == 2) {
			if (b.f == 2) { // Next
				System.out.println("NO GUI YET!");
			}
			else // Done
				e.a(parent);
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
			e.a(new WMLLGuiLightLevel(wmll, this));
		}
		else if (b.f == 7) {
			boolean a = !wmll.classicOutput;
			b.e = "Output type: "+(a ? "Classic" : "Custom");
			wmll.classicOutput = a;
		}
		else if (b.f == 8) {
			boolean a = wmll.showSeedWithCoords;
			a = !a;
			wmll.showSeedWithCoords = a;
			b.e = "Seed w/ Coords: "+(a ? "Yes" : "No");
		}
		else if (b.f == 9) {
			e.a(new WMLLGuiSMPSeed(wmll, this));
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
			e.a(new WMLLEnabledOutputs(wmll, this));
		}
		else if (b.f == 12) {
			boolean a = !wmll.satBar;
			b.e = "Enable Saturation bar Compatibility: "+(a ? "Yes" : "No");
			wmll.satBar = a;
		}
		else if (b.f == 9001) // Debug button
			e.a(new WMLLGuiOutputOptions(wmll, parent));
	}

	protected void a(char c, int i) {
		if (Keyboard.KEY_ESCAPE == i)
			e.a(parent);
		else
			super.a(c, i);
	}

	public void a(int i, int j, float f) {
		z_();
		// (fontrenderer, text, x, y, colour)
		a(k, title, this.f / 2, 20, 0xffffff);
		WMLLOptions.renderWMLLVersion();
		super.a(i, j, f);
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
