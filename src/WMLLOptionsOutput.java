import java.awt.Color;
import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;


public class WMLLOptionsOutput extends awe {
    
    private static final String[] outputs = {"Just Light", "Light & Indicators", "Light, FPS & Chunk Updates", "Light & Compass",
	"Light, Indicators & Compass", "Light, FPS & Coordinates", "Just Indicators", "Just FPS & Chunk Updates", "Just Compass",
	"Just Indicators and Compass", "Just FPS & Coordinates", "Nothing"};
    
    private static final String[] locations = {"Top Left", "Top Right", "Bottom Left", "Bottom Right"};
    
    
    private WMLL wmll;
    private awe parent;
    
    public avf lightLevelBox, R, G, B, seedBox;
    private aut showButton;
    private ArrayList<avf> editboxes = new ArrayList<avf>();
    private Desktop desktop;
    private int[] colours;
    
    public WMLLOptionsOutput(WMLL wmll, awe aum) {
	this.wmll = wmll;
	this.parent = aum;
    }
    
    
    public void b() {
	Keyboard.enableRepeatEvents(false);
    }
    
    @SuppressWarnings("unchecked")
    public void A_() {
	Keyboard.enableRepeatEvents(true);
	i.clear();
	lightLevelBox = new avf(o, g / 2 - ((wmll.getWindowSize().a() - 20) / 2), 35, wmll.getWindowSize().a() - 20, 15);
	lightLevelBox.f(500);
	lightLevelBox.a(WMLL.options.getProperty("lightString", "Light level: %LightLevel%"));
	lightLevelBox.b(true);
	i.add(showButton = new aut(1, g / 2 - 75, h / 4 + 5, 275, 20, "Show: "+outputs[WMLL.WMLLI]));
	R = new avf(o, g / 2 - 60, h / 4 + 70, 30, 10);
	R.f(3);
	R.a(WMLL.options.getProperty("RGB-R", "255"));
	G = new avf(o, g / 2 - 15, h / 4 + 70, 30, 10);
	G.f(3);
	G.a(WMLL.options.getProperty("RGB-G", "255"));
	B = new avf(o, g / 2 + 30, h / 4 + 70, 30, 10);
	B.f(3);
	B.a(WMLL.options.getProperty("RGB-B", "255"));
	if (R.b().equals(""))
	    R.a("0");
	if (G.b().equals(""))
	    G.a("0");
	if (B.b().equals(""))
	    B.a("0");
	colours = new int[3];
	colours[0] = Integer.valueOf((R.b().equals("") ? "0" : R.b()));
	colours[1] = Integer.valueOf((G.b().equals("") ? "0" : G.b()));
	colours[2] = Integer.valueOf((B.b().equals("") ? "0" : B.b()));
	seedBox = new avf(o, g / 2 - ((wmll.getWindowSize().a() - 20) / 2), h / 4 + 105, wmll.getWindowSize().a() - 20, 15);
	seedBox.f(500);
	seedBox.a(wmll.getWorldSeed() != 0 ? Long.toString(wmll.getWorldSeed()) : "");
	editboxes.add(seedBox);
	editboxes.add(lightLevelBox);
	editboxes.add(R);
	editboxes.add(G);
	editboxes.add(B);
	showButton.h = wmll.classicOutput || WMLL.useImages;
	i.add(new aut(2, g / 2 - 200, h / 4 + 5, 120, 20, "Location: "+locations[WMLL.outputLocation]));
	i.add(new aut(3, g / 2 - 200, h / 4 + 30, 120, 20, "Output type: "+(wmll.classicOutput ? "Classic" : "Custom")));
	i.add(new aut(4, g / 2 - 75, h / 4 + 30, 120, 20, "Images: "+(WMLL.useImages ? "ON" : "OFF")));
	i.add(new aut(5, g / 2 + 50, h / 4 + 30, 150, 20, "Colour Low Light Levels: "+(WMLL.colourLowLight ? "ON" : "OFF")));
	i.add(new aut(0, g / 2 - 190, h - 30, 380, 20, "Done"));
	aut paramButton;
	i.add(paramButton = new aut(-1, wmll.getWindowSize().a() - 108, 19, 100, 14, "Parameter help"));
	paramButton.h = Desktop.isDesktopSupported() && (this.desktop = Desktop.getDesktop()).isSupported(Desktop.Action.BROWSE);
	if (wmll.debugClassPresent || WMLL.WMLLDebugActive())
	    i.add(new aut(9001, g - 60, 0, 60, 20, "Reload GUI"));
    }
    
    protected void a(aut b) {
	switch (b.g) {
	    case 9001:
		f.a(new WMLLOptionsOutput(this.wmll, this.parent));
		return;
	    case 0:
		WMLL.options.put("RGB-R", R.b());
		WMLL.options.put("RGB-G", G.b());
		WMLL.options.put("RGB-B", B.b());
		WMLL.TextColour = new Color(colours[0], colours[1], colours[2]).getRGB();
		WMLL.options.put("lightString", lightLevelBox.b());
		if (!wmll.autoSeed && !seedBox.b().equals(wmll.getWorldSeed()) && seedBox.b() != "") {
		    wmll.worldSeedSet = true;
		    wmll.worldSeed = Long.valueOf(seedBox.b());
		    WMLL.options.put("Seed:"+wmll.getWorldName(), seedBox.b());
		}
		f.a(parent);
		return;
	    case 1:
		int c = WMLL.WMLLI;
		c++;
		if (c > (outputs.length - 1))
		    c = 0;
		b.f = "Show: "+outputs[c];
		WMLL.WMLLI = c;
		return;
	    case 2:
		int d = WMLL.outputLocation;
		d++;
		if (d > (locations.length - 1))
		    d = 0;
		b.f = "Location: "+locations[d];
		WMLL.outputLocation = d;
		return;
	    case 3:
		boolean a = wmll.classicOutput;
		b.f = "Output type: "+(!a ? "Classic" : "Custom");
		wmll.classicOutput = !a;
		showButton.h = !a || WMLL.useImages;
		return;
	    case 4:
		a = WMLL.useImages;
		b.f = "Images: "+(!a ? "ON" : "OFF");
		WMLL.useImages = !a;
		showButton.h = wmll.classicOutput || WMLL.useImages;
		return;
	    case 5:
		a = WMLL.colourLowLight;
		b.f = "Colour Low Light Levels: "+(!a ? "ON" : "OFF");
		WMLL.colourLowLight = !a;
		return;
	    case -1:
		try {
		    this.desktop.browse(new URI("http://www.minecraftforum.net/topic/170739-#parameters"));
		}
		catch (Exception e) { b.h = false; }
		return;
	}
    }
    
    protected void a(char c, int i) {
	if (Keyboard.KEY_ESCAPE == i)
	    f.a(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? null : parent);
	else if (lightLevelBox.l())
	    lightLevelBox.a(c, i);
	else if (seedBox.l())
	    seedBox.a(c, i);
	else if (R.l() || G.l() || B.l()) {
	    if (R.l())
		R.a(c, i);
	    if (G.l())
		G.a(c, i);
	    if (B.l())
		B.a(c, i);
	    //colours = new int[3];
	    colours[0] = Integer.valueOf((R.b().equals("") ? "0" : R.b()));
	    colours[1] = Integer.valueOf((G.b().equals("") ? "0" : G.b()));
	    colours[2] = Integer.valueOf((B.b().equals("") ? "0" : B.b()));
	    if (colours[0] > 255)
		colours[0] = 255;
	    if (colours[0] < 0)
		colours[0] = 0;
	    if (colours[1] > 255)
		colours[1] = 255;
	    if (colours[1] < 0)
		colours[1] = 0;
	    if (colours[2] > 255)
		colours[2] = 255;
	    if (colours[2] < 0)
		colours[2] = 0;
	}
	else
	    super.a(c, i);
    }
    
    public void a(int i, int j, float f) { // [152pre] [iPeer] These using the width variable as height is weird.
	e();
	a(o, "Output format", g / 2 - ((wmll.getWindowSize().a() - wmll.getFontRenderer().a("Output format") - 20) / 2), 22, 0xffffff);
	a(o, "Text Colour", g / 2, h / 4 + 55, 0xffffff);
	a(o, "R: ", g / 2 - 63, h / 4 + 71, 0xffffff);
	a(o, "G: ", g / 2 - 18, h / 4 + 71, 0xffffff);
	a(o, "B: ", g / 2 + 27, h / 4 + 71, 0xffffff);
	int r = checkColour(colours[0]);
	int g1 = checkColour(colours[1]);
	int b = checkColour(colours[2]);
	a(o, "Colour preview", g / 2, h / 4 + 85, new Color(r, g1, b).getRGB());
	a(o, "Seed", g / 2 - ((wmll.getWindowSize().a() - wmll.getFontRenderer().a("Seed") - 20) / 2), h / 4 + 92, 0xffffff);
	for (avf a : editboxes)
	    a.f();
	super.a(i, j, f);
    }
    
    public void a() {
	lightLevelBox.a();
	R.a();
	f.a();
	B.a();
	seedBox.a();
    }
    
    protected void a(int i, int j, int k) {
	super.a(i, j, k);
	lightLevelBox.a(i, j, k);
	R.a(i, j, k);
	B.a(i, j, k);
	G.a(i, j, k);
	seedBox.a(i, j, k);
    }
    
    private int checkColour(int i) {
	if (i < 0)
	    return 0;
	else if (i > 255)
	    return 255;
	else return i;
    }
    
}
