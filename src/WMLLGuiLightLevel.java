import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.lwjgl.input.Keyboard;


public class WMLLGuiLightLevel extends apm {
	
	protected WMLL wmll;
	private apm parent;
	private String title;
	private Properties outputOptions;
	private String lightString;
	private int page = 1;
	private aog parameterButton, colouringButton, doneButton;
	private aor lightEditbox, skyEditbox, rawEditbox, blockEditbox, lightLevelEditbox;
	private final List<aor> page3editboxes = new ArrayList<aor>();

	public WMLLGuiLightLevel(WMLL w, apm parent) {
		this.wmll = w;
		this.parent = parent;
		title = "WMLL Light Level Customization";
	}
	
	@SuppressWarnings("unchecked")
	public void w_() {
		outputOptions = WMLL.outputOptions;
		if (outputOptions == null)
			outputOptions = new Properties();
		byte o = -16;
		h.clear();
		
		doneButton = new aog(0, f / 2 - 112, g / 4 + 150 + o, 226, 20, "Done");
		h.add(doneButton);
		
		int a = wmll.getFontRenderer().a("Parameter Help") + 10;
		parameterButton = new aog(1, (f - a) / 2, g / 4 + 65 + o, a, 20, "Parameter Help");
		h.add(parameterButton);
		
		a = wmll.getFontRenderer().a("Colouring Options...") + 10;
		colouringButton = new aog(2, (f - a) / 2, g / 4 + 75, a, 20, "Colouring Options...");
		h.add(colouringButton);
		
		/*
		 * New Editbox:
		 * new aor(fontrenderer, posx, posy, width, height);
		 */
		lightLevelEditbox = new aor(/*this,*/ k, f / 2 - ((wmll.getWindowSize().a() - 20) / 2), 70, wmll.getWindowSize().a() - 20, 20/*, outputOptions.getProperty("lightString", "Light level: %LightLevel%")*/);
		lightLevelEditbox.f(76);
		lightLevelEditbox.a(outputOptions.getProperty("lightString", "Light level: %LightLevel%"));
		//lightLevelEditbox.a = true;
		lightLevelEditbox.b(true);
		
		blockEditbox = new aor(k, f / 2 - 10, 43, 20, 10);
		blockEditbox.f(2);
		blockEditbox.a(outputOptions.getProperty("highlightBlock", "8"));
		page3editboxes.add(blockEditbox);
		
		skyEditbox = new aor(k, f / 2 - 10, 73, 20, 10);
		skyEditbox.a(outputOptions.getProperty("highlightSky", "8"));
		skyEditbox.f(2);
		page3editboxes.add(skyEditbox);
		
		rawEditbox = new aor(k, f / 2 - 10, 103, 20, 10);
		rawEditbox.f(2);
		rawEditbox.a(outputOptions.getProperty("highlightRaw", "8"));
		page3editboxes.add(rawEditbox);
		
		lightEditbox = new aor(k, f / 2 - 10, 133, 20, 10);
		lightEditbox.f(2);
		lightEditbox.a(outputOptions.getProperty("highlightLight", "8"));
		page3editboxes.add(lightEditbox);
		
		if (WMLL.debugClassPresent)
			h.add(new aog(9001, f - 52, g - 22, 50, 20, "Reload"));
		generateLightStringPreview();
	}
	
	protected void a(aog b) {
		if (b.f == 0) {
			if (page == 1) {
				performSave();
				e.a(parent);
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
			e.a(new WMLLGuiLightLevel(wmll, parent));
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
		if (Keyboard.KEY_ESCAPE == i) {
			performSave();
			e.a(parent);
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
		v_();
		if (page == 1)
			a(k, title, this.f / 2, 20, 0xffffff);
		WMLLOptions.renderWMLLVersion();
		if (page == 1) {
			lightLevelEditbox.f();
			a(k, lightString, this.f / 2, 95, 0xffffff);
		}
		else if (page == 2) {
			a(k, "Parameters", this.f / 2, 15, 0xffffff);
			
			String b = "\247c%LightLevel%";
			a(k, b, this.f / 2, 45, 0xffffff);
			b = "Returns the current light level.";
			a(k, b, this.f / 2, 55, 0xffffff);

			b = "\247c%RawLight%";
			a(k, b, this.f / 2, 65, 0xffffff);
			b = "Returns the current raw light level.";
			a(k, b, this.f / 2, 75, 0xffffff);

			b = "\247c%SkyLight%";
			a(k, b, this.f / 2, 85, 0xffffff);
			b = "Returns the sky's current light level.";
			a(k, b, this.f / 2, 95, 0xffffff);

			b = "\247c%BlockLight%";
			a(k, b, this.f / 2, 105, 0xffffff);
			b = "Returns the block's light level.";
			a(k, b, this.f / 2, 115, 0xffffff);
			
			b = "\247c%Biome%";
			a(k, b, this.f / 2, 125, 0xffffff);
			b = "Returns the current biome.";
			a(k, b, this.f / 2, 135, 0xffffff);
			
			b = "\247c%x%\247r, \247c%y% \247r&\247c %z%";
			a(k, b, this.f / 2 + 15, 145, 0xffffff);
			b = "Return the coordinate repective to the letter.";
			a(k, b, this.f / 2, 155, 0xffffff);
		}
		else if (page == 3) {
			for (int x = 0; x < page3editboxes.size(); x++) {
				page3editboxes.get(x).f();
			}
			a(k, "Highlight block light when less than...", this.f / 2, 30, 0xffffff);
			a(k, "Highlight sky light when less than...", this.f / 2, 60, 0xffffff);
			a(k, "Highlight raw light when less than...", this.f / 2, 90, 0xffffff);
			a(k, "Highlight light level when less than...", this.f / 2, 120, 0xffffff);
			a(k, "\247cSetting a value to 0 will disable highlighting for that output", this.f / 2, 160, 0xffffff);
		}
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
	
//	public void e() {
//		Keyboard.enableRepeatEvents(false);
//	}
	
	// Custom functions
	
	public void generateLightStringPreview() {
		lightString = wmll.generateLightString(lightLevelEditbox.b());	
	}
	
	public void performSave() {
		outputOptions.put("lightString", lightLevelEditbox.b());
		WMLLGuiOutputOptions.outputOptions = WMLL.outputOptions = outputOptions;
		System.out.println(outputOptions);
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
