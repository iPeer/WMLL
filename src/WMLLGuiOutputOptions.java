import java.util.Properties;

import org.lwjgl.input.Keyboard;


public class WMLLGuiOutputOptions extends vl {
	
	protected WMLL wmll;
	private vl parent;
	private String title;
	private static final String[] colourNames = {"Black", "Dark Blue", "Dark Green", "Cyan", "Red", "Purple", "Orange", "Light Grey", "Dark Grey", "Lavender?", "Lime Green", "Light Blue", "Bright Red", "Pink", "Yellow", "White"};
	private static final String[] outputLocations = {"Top Left", "Top Right", "Bottom Left", "Bottom Right"};
	public static Properties outputOptions;
	
	public WMLLGuiOutputOptions(WMLL wmll, vl parent) {
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
		 * abk((int)ID, x, y[, width, height], text)
		 * y = y+25 for each button
		*/
		s.add(new abk(1, q / 2 - 100, r / 4 + 150 + o, 98, 20, "Done"));
		//s.add(new abk(0, q / 2 - 152, r / 4 + 150 + o, 98, 20, "<<"));
		s.add(new abk(2, q / 2 + 2, r / 4 + 150 + o, 98, 20, ">>"));
		s.add(new abk(3, q / 2 - 107, r / 4 - 5 + o, 108, 20, outputLocations[WMLL.outputLocation]));
		s.add(new abk(4, q / 2 + 7, r / 4 - 5 + o, 108, 20, "\247"+Integer.toHexString(tc)+colourNames[tc]));
		if (WMLL.debugClassPresent)
			s.add(new abk(9001, 2, r - 22, 50, 20, "Reload"));
		s.add(new abk(5, q / 2 - 100, r / 4 + 120 + o, "Light Level options..."));
/*		s.add(new abk(0, q / 2 - 100, r / 4 + 20 + o, 98, 20, "c"));
		s.add(new abk(2, q / 2 + 2, r / 4 + 20 + o, 98, 20, "d"));
		s.add(new abk(0, q / 2 - 100, r / 4 + 45 + o, 98, 20, "e"));
		s.add(new abk(2, q / 2 + 2, r / 4 + 45 + o, 98, 20, "f"));
		s.add(new abk(0, q / 2 - 100, r / 4 + 70 + o, 98, 20, "g"));
		s.add(new abk(2, q / 2 + 2, r / 4 + 70 + o, 98, 20, "h"));
		s.add(new abk(0, q / 2 - 100, r / 4 + 95 + o, 98, 20, "i"));
		s.add(new abk(2, q / 2 + 2, r / 4 + 95 + o, 98, 20, "j"));*/
		
	}
	
	protected void a(abk b) {
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
			getButton(3).e = outputLocations[a];
			WMLL.outputLocation = a;
		}
		else if (b.f == 4) { // Text Colour
			int a = WMLL.TextColour;
			a++;
			if (a > 15)
				a = 0;
			getButton(4).e = "\247"+Integer.toHexString(a)+colourNames[a];
			WMLL.TextColour = a;
		}
		else if (b.f == 9001) // Debug button
			p.a(new WMLLGuiOutputOptions(wmll, parent));
		else if (b.f == 5) { //Light level options
			p.a(new WMLLGuiLightLevel(wmll, this));
		}
	}
	
	protected void a(char c, int i) {
		if (Keyboard.KEY_ESCAPE == i)
			p.a(parent);
		else
			super.a(c, i);
	}
	
	public void a(int i, int j, float f) {
		k();
		// (fontrenderer, text, x, y, colour)
		a(u, title, q / 2, 20, 0xffffff);
		int a = (q - ((wmll.getFontRenderer().a(WMLL.WMLLVER) + 2) / 2));
		a(u, WMLL.WMLLVER, a, r - 9, 0x444444);
		super.a(i, j, f);
	}
	
	public void e() {
		Keyboard.enableRepeatEvents(false);
	}
	
	// Begin custom functions
	
	private abk getButton(int id) {
		return ((abk)s.get(id));
	}
	
}
