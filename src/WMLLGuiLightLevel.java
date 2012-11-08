
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.lwjgl.input.Keyboard;


public class WMLLGuiLightLevel extends aue {
	
	protected WMLL wmll;
	private aue parent;
	private String title;
	private Properties outputOptions;
	private String lightString;
	private int page = 1;
	private ast parameterButton, colouringButton, doneButton;
	private atg lightEditbox, skyEditbox, rawEditbox, blockEditbox, lightLevelEditbox;
	private final List<atg> page3editboxes = new ArrayList<atg>();

	public WMLLGuiLightLevel(WMLL w, aue parent) {
		this.wmll = w;
		this.parent = parent;
		title = "WMLL Output Customization";
	}
	
	@SuppressWarnings("unchecked")
	public void A_() {
		Keyboard.enableRepeatEvents(true);
		outputOptions = WMLL.options;
		if (outputOptions == null)
			outputOptions = new Properties();
		byte o = -16;
		i.clear();
		
		//doneButton = new aoh(0, g / 2 - 112, g / 4 + 150 + o, 226, 20, "Done");
		//i.add(doneButton);
		i.add(new ast(3, g / 2 - 112, 45, 226, 20, "View full parameter list"));
		
//		int a = wmll.getFontRenderer().a("Parameter Help") + 10;
//		parameterButton = new aoh(1, (f - a) / 2, g / 4 + 65 + o, a, 20, "Parameter Help");
//		i.add(parameterButton);
//		
//		a = wmll.getFontRenderer().a("Colouring Options...") + 10;
//		colouringButton = new aoh(2, (f - a) / 2, g / 4 + 75, a, 20, "Colouring Options...");
//		i.add(colouringButton);
		
		/*
		 * New Editbox:
		 * new atg(fontrenderer, posx, posy, width, height);
		 */
		lightLevelEditbox = new atg(/*this,*/ l, g / 2 - ((wmll.getWindowSize().a() - 20) / 2), 70, wmll.getWindowSize().a() - 20, 20/*, outputOptions.getProperty("lightString", "Light level: %LightLevel%")*/);
		lightLevelEditbox.f(500);
		lightLevelEditbox.a(outputOptions.getProperty("lightString", "Light level: %LightLevel%"));
		//lightLevelEditbox.a = true;
		lightLevelEditbox.b(true);
		
		blockEditbox = new atg(l, g / 2 - 10, 43, 20, 10);
		blockEditbox.f(2);
		blockEditbox.a(outputOptions.getProperty("highlightBlock", "8"));
		page3editboxes.add(blockEditbox);
		
		skyEditbox = new atg(l, g / 2 - 10, 73, 20, 10);
		skyEditbox.a(outputOptions.getProperty("highlightSky", "8"));
		skyEditbox.f(2);
		page3editboxes.add(skyEditbox);
		
		rawEditbox = new atg(l, g / 2 - 10, 103, 20, 10);
		rawEditbox.f(2);
		rawEditbox.a(outputOptions.getProperty("highlightRaw", "8"));
		page3editboxes.add(rawEditbox);
		
		lightEditbox = new atg(l, g / 2 - 10, 133, 20, 10);
		lightEditbox.f(2);
		lightEditbox.a(outputOptions.getProperty("highlightLight", "8"));
		page3editboxes.add(lightEditbox);
		
		if (wmll.debugClassPresent)
			i.add(new ast(9001, g - 52, h - 22, 50, 20, "Reload"));
		generateLightStringPreview();
	}
	
	protected void a(ast b) {
		if (b.f == 3) {
			if (!Desktop.isDesktopSupported()) {
				System.err.println("[WMLL] FATAL: System doesn't support Desktop!");
				return;
			}
			Desktop a = Desktop.getDesktop();
			if (!a.isSupported(Desktop.Action.BROWSE)) {
				System.err.println("[WMLL] FATAL: System doesn't support Desktop.BROWSE!");
				return;
			}
			try {
				URI c = new URI("http://www.minecraftforum.net/topic/170739-#parameters");
				a.browse(c);
			} catch (URISyntaxException e1) {
				System.err.println("[WMLL] Invalid URI syntax!\nStacktrace:");
				e1.printStackTrace();
			} catch (IOException e2) {
				System.err.println("[WMLL] SystemIO error!\nStacktrace:");

				e2.printStackTrace();
			}
		}
		if (b.f == 0) {
			if (page == 1) {
				performSave();
				f.a(parent);
			}
			else if (page >= 2) {
				if (page == 2)
					page--;
				else 
					page = 1;
				parameterButton.h = colouringButton.h = true;
				doneButton.e = "Done";
				for (int x = 0; x < page3editboxes.size(); x++) {
					page3editboxes.get(x).b(false);
				}
				outputOptions.put("highlightLight", verify(lightEditbox.b()));
				outputOptions.put("highlightBlock", verify(blockEditbox.b()));
				outputOptions.put("highlightRaw", verify(rawEditbox.b()));
				outputOptions.put("highlightSky", verify(skyEditbox.b()));
				lightLevelEditbox.b(true);
			}
		}
		else if (b.f == 9001) // Debug button
			f.a(new WMLLGuiLightLevel(wmll, parent));
		else if (b.f == 1 || b.f == 2) {
			if (b.f == 2) {
				page = 3;
				b.h = parameterButton.h = false;
				doneButton.e = "<<";
			}
			else {
				page++;
				b.h = colouringButton.h = false;
				doneButton.e = "<<";
			}
			lightLevelEditbox.b(false);
		}

	}
	
	protected void a(char c, int i) {
		if (Keyboard.KEY_ESCAPE == i || Keyboard.KEY_RETURN == i) {
			performSave();
			f.a(parent);
		}
		else if (lightLevelEditbox.l()) {
			lightLevelEditbox.a(c, i);
			generateLightStringPreview();
		}
		else if (blockEditbox.l())
			blockEditbox.a(c, i);
		else if (lightEditbox.l())
			lightEditbox.a(c, i);
		else if (rawEditbox.l())
			rawEditbox.a(c, i);
		else if (skyEditbox.l())
			skyEditbox.a(c, i);
		else
			super.a(c, i);
	}
	
	public void a(int i, int j, float f) {
		// (fontrenderer, text, x, y, colour)
		z_();
		WMLLOptions.renderWMLLVersion();
			a(l, title, this.g / 2, 20, 0xffffff);
			lightLevelEditbox.f();
			a(l, "\247aOnce done, hit enter or escape to save.", this.g / 2, 93, 0xffffff);
			a(l, "Preview:", this.g / 2, 105, 0xbbbbbb);
			lightString = lightString.replaceAll("\\\\t", "");
			String[] a = lightString.split("\\\\n");
			int b = 0;
			for (String c : a)
				a(l, c, this.g / 2, 115+(b++*12), 0xffffff);
			
			//a(l, "For a full list of parameters and what they symbolise, please check", this.g / 2, 45, 0xbbbbbb);
			//a(l, "the WMLL forum thread.", this.g / 2, 57, 0xbbbbbb);
		super.a(i, j, f);
	}
	
	public void a() {
		lightLevelEditbox.a();
		for (int x = 0; x < page3editboxes.size(); x++) {
			page3editboxes.get(x).a();
		}
	}
	
	protected void a(int i, int j, int k) {
		super.a(i, j, k);
		if (page == 3) {
			blockEditbox.a(i, j, k);
			rawEditbox.a(i, j, k);
			skyEditbox.a(i, j, k);
			lightEditbox.a(i, j, k);
		}
		else if (page == 1)
			lightLevelEditbox.a(i, j, k);
	}
	
	public void b() {
		Keyboard.enableRepeatEvents(false);
	}
	
	// Custom functions
	
	public void generateLightStringPreview() {
		lightString = wmll.generateLightString(lightLevelEditbox.b());	
	}
	
	public void performSave() {
		outputOptions.put("lightString", lightLevelEditbox.b());
		WMLLGuiOutputOptions.outputOptions = WMLL.options = outputOptions;
		wmll.saveOptions();
	}
	
	private String verify(String a) {
		try {
			int a1 = Integer.parseInt(a);
			if (a1 < 0 || a1 > 16)
				a1 = 16;
			return Integer.toString(a1);
		}
		catch (NumberFormatException n) {
			return Integer.toString(8);
		}
	}
	
}
