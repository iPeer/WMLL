
import java.util.Properties;

import org.lwjgl.input.Keyboard;


public class WMLLGuiOutputOptions extends aue {

	protected WMLL wmll;
	private aue parent;
	private String title;
	private static final String[] colourNames = {"Black", "Dark Blue", "Dark Green", "Cyan", "Red", "Purple", "Orange", "Light Grey", "Dark Grey", "Lavender?", "Lime Green", "Light Blue", "Bright Red", "Pink", "Yellow", "White"};
	private static final String[] outputLocations = {"Top Left", "Top Right", "Bottom Left", "Bottom Right"};
	public static Properties outputOptions;
	private ast SMPSeedButton, showButton, eOutputsButton, satBarButton;

	@SuppressWarnings("static-access")
	public WMLLGuiOutputOptions(WMLL wmll, aue parent) {
		this.wmll = wmll;
		this.parent = parent;
		this.outputOptions = WMLL.options;
		title = "WMLL Configuration";
	}

	@SuppressWarnings("unchecked")
	public void A_() {
			i.clear();
			if (outputOptions == null)
				outputOptions = new Properties();
			int tc = WMLL.TextColour;
			byte o = -16;
			if (wmll.debugClassPresent)
				i.add(new ast(9001, g - 52, h - 22, 50, 20, "Reload"));
			i.add(new ast(1, g / 2 - 112, h / 4 + 150 + o, 226, 20, "Done"));
			i.add(new ast(3, g / 2 - 112, h / 4 - 5 + o, 112, 20, outputLocations[WMLL.outputLocation]));
			i.add(new ast(4, g / 2 + 2, h / 4 - 5 + o, 112, 20, "\247"+Integer.toHexString(tc)+colourNames[tc]));
			i.add(new ast(5, g / 2 - 112, h / 4 + 75 + o, (wmll.classicOutput ? 112 : 226), 20, "Customize output"));
			if (wmll.classicOutput)
				i.add(satBarButton = new ast(12, g / 2 + 2, h / 4 + 75 + o, 112, 20, "Sat Bar Enabled?: "+(wmll.satBar ? "Yes" : "No")));
			else
				i.add(satBarButton = new ast(12, g / 2 - 112, h / 4 + 45 + o, 226, 20, "Enable Saturation bar Compatibility: "+(wmll.satBar ? "Yes" : "No")));
			i.add(new ast(6, g / 2 - 112, h / 4 + 20 + o, 112, 20, "Override F3: "+(wmll.wmllOverrideF3 ? "Yes" : "No")));
			i.add(new ast(8, g / 2 + 2, h / 4 + 20 + o, 112, 20, "Seed w/ Coords: "+(wmll.showSeedWithCoords ? "Yes" : "No")));
			i.add(new ast(7, g / 2 + 2, h / 4 + 100 + o, 112, 20, "Output type: "+(wmll.classicOutput ? "Classic" : "Custom")));
			i.add(SMPSeedButton = new ast(9, g / 2 - 112, h / 4 + 100 + o, 112, 20, "Enter seed"));
			i.add(eOutputsButton = new ast(11, g / 2 - 112, h / 4 + 125 + o, 226, 20, "Enabled outputs..."));
			i.add(showButton = new ast(10, g / 2 - 112, h / 4 + 45 + o, 226, 20, "Show: "+getStringForLightOption(WMLL.WMLLI)));
			SMPSeedButton.g = wmll.isMultiplayer() || !wmll.autoSeed;
			eOutputsButton.h = showButton.h = (WMLL.useImages || wmll.classicOutput);
			satBarButton.g = !wmll.compatDisabled;
	}

	protected void a(ast b) {
		if (b.f == 1 || b.f == 2) {
			if (b.f == 2) { // Next
				System.out.println("NO GUI YET!");
			}
			else // Done
				f.a(parent);
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
			f.a(new WMLLGuiLightLevel(wmll, this));
		}
		else if (b.f == 7) {
			boolean a = !wmll.classicOutput;
			b.e = "Output type: "+(a ? "Classic" : "Custom");
			wmll.classicOutput = a;
			f.a(new WMLLGuiOutputOptions(wmll, parent));
		}
		else if (b.f == 8) {
			boolean a = wmll.showSeedWithCoords;
			a = !a;
			wmll.showSeedWithCoords = a;
			b.e = "Seed w/ Coords: "+(a ? "Yes" : "No");
		}
		else if (b.f == 9) {
			f.a(new WMLLGuiSMPSeed(wmll, this));
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
			f.a(new WMLLEnabledOutputs(wmll, this));
		}
		else if (b.f == 12) {
			wmll.toggleSatBar();
			boolean a = wmll.satBar;
			String pre = (wmll.classicOutput ? "Sat Bar Enabled?: " : "Enable Saturation bar Compatibility: ");
			b.e = pre+(a ? "Yes" : "No");
		}
		else if (b.f == 9001) // Debug button
			f.a(new WMLLGuiOutputOptions(wmll, parent));
	}

	protected void a(char c, int i) {
		if (Keyboard.KEY_ESCAPE == i)
			f.a(parent);
		else
			super.a(c, i);
	}

	public void a(int i, int j, float f) {
		z_();
		// (fontrenderer, text, x, y, colour)
		a(l, title, this.g / 2, 20, 0xffffff);
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
