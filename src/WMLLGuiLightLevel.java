import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.lwjgl.input.Keyboard;


public class WMLLGuiLightLevel extends vl {
	
	protected WMLL wmll;
	private vl parent;
	private String title;
	private Properties outputOptions;
	private String lightString;
	private int page = 1;
	private abk parameterButton, colouringButton, doneButton;
	private ago lightEditbox, skyEditbox, rawEditbox, blockEditbox, lightLevelEditbox;
	private final List<ago> page3editboxes = new ArrayList<ago>();
	
	private static zl[] enums;

	public WMLLGuiLightLevel(WMLL w, vl parent) {
		this.wmll = w;
		this.parent = parent;
		title = "WMLL Light Level Customization";
	}
	
	@SuppressWarnings("unchecked")
	public void c() {
		outputOptions = WMLL.outputOptions;
		if (outputOptions == null)
			outputOptions = new Properties();
		Keyboard.enableRepeatEvents(true);
		byte o = -16;
		s.clear();
		
		doneButton = new abk(0, q / 2 - 100, r / 4 + 150 + o, "Done");
		s.add(doneButton);
		
		int a = wmll.getFontRenderer().a("Parameter Help") + 10;
		parameterButton = new abk(1, (q - a) / 2, r / 4 + 65 + o, a, 20, "Parameter Help");
		s.add(parameterButton);
		
		a = wmll.getFontRenderer().a("Colouring Options...") + 10;
		colouringButton = new abk(2, (q - a) / 2, r / 4 + 75, a, 20, "Colouring Options...");
		s.add(colouringButton);
		
		/*
		 * New Editbox:
		 * new ago(Gui, fontrenderer, posx, posy, width, height, default text);
		 */
		lightLevelEditbox = new ago(this, u, q / 2 - ((wmll.getWindowSize().a() - 20) / 2), 70, wmll.getWindowSize().a() - 20, 20, outputOptions.getProperty("lightString", "Light level: %LightLevel%"));
		lightLevelEditbox.a = true;
		lightLevelEditbox.a(76);

		blockEditbox = new ago(this, u, q / 2 - 10, 43, 20, 10, outputOptions.getProperty("highlightBlock", "8"));
		blockEditbox.a(2);
		page3editboxes.add(blockEditbox);
		
		skyEditbox = new ago(this, u, q / 2 - 10, 73, 20, 10, outputOptions.getProperty("highlightSky", "8"));
		skyEditbox.a(2);
		page3editboxes.add(skyEditbox);
		
		rawEditbox = new ago(this, u, q / 2 - 10, 103, 20, 10, outputOptions.getProperty("highlightRaw", "8"));
		rawEditbox.a(2);
		page3editboxes.add(rawEditbox);
		
		lightEditbox = new ago(this, u, q / 2 - 10, 133, 20, 10, outputOptions.getProperty("highlightLight", "8"));
		lightEditbox.a(2);
		page3editboxes.add(lightEditbox);
		
		if (WMLL.debugClassPresent)
			s.add(new abk(9001, 2, r - 22, 50, 20, "Reload"));
		generateLightStringPreview();
	}
	
	protected void a(abk b) {
		if (b.f == 0) {
			if (page == 1) {
				performSave();
				p.a(parent);
				e();
			}
			else if (page >= 2) {
				if (page == 2)
					page--;
				else 
					page = 1;
				parameterButton.i = colouringButton.i = true;
				doneButton.e = "Done";
				for (int x = 0; x < page3editboxes.size(); x++) {
					page3editboxes.get(x).a = false;
				}
				outputOptions.put("highlightLight", verify(lightEditbox.a()));
				outputOptions.put("highlightBlock", verify(blockEditbox.a()));
				outputOptions.put("highlightRaw", verify(rawEditbox.a()));
				outputOptions.put("highlightSky", verify(skyEditbox.a()));
				lightLevelEditbox.a = true;
			}
		}
		else if (b.f == 9001) // Debug button
			p.a(new WMLLGuiLightLevel(wmll, parent));
		else if (b.f == 1 || b.f == 2) {
			if (b.f == 2) {
				page = 3;
				b.i = parameterButton.i = false;
				doneButton.e = "<<";
			}
			else {
				page++;
				b.i = colouringButton.i = false;
				doneButton.e = "<<";
			}
			lightLevelEditbox.a = false;
		}

	}
	
	protected void a(char c, int i) {
		if (Keyboard.KEY_ESCAPE == i) {
			performSave();
			p.a(parent);
		}
		else if (lightLevelEditbox.a) {
			lightLevelEditbox.a(c, i);
			generateLightStringPreview();
		}
		else if (blockEditbox.a)
			blockEditbox.a(c, i);
		else if (lightEditbox.a)
			lightEditbox.a(c, i);
		else if (rawEditbox.a)
			rawEditbox.a(c, i);
		else if (skyEditbox.a)
			skyEditbox.a(c, i);
		else
			super.a(c, i);
	}
	
	public void a(int i, int j, float f) {
		k();
		// (fontrenderer, text, x, y, colour)
		if (page == 1)
			a(u, title, q / 2, 20, 0xffffff);
		int a = (q - ((wmll.getFontRenderer().a(WMLL.WMLLVER) + 2) / 2));
		a(u, WMLL.WMLLVER, a, r - 9, 0x444444);
		if (page == 1) {
			lightLevelEditbox.c();
			a(u, lightString, q / 2, 95, 0xffffff);
		}
		else if (page == 2) {
			a(u, "Parameters", q / 2, 15, 0xffffff);
			
			String b = "\247c%LightLevel%";
			a(u, b, q / 2, 45, 0xffffff);
			b = "Returns the current light level.";
			a(u, b, q / 2, 55, 0xffffff);

			b = "\247c%RawLight%";
			a(u, b, q / 2, 65, 0xffffff);
			b = "Returns the current raw light level.";
			a(u, b, q / 2, 75, 0xffffff);

			b = "\247c%SkyLight%";
			a(u, b, q / 2, 85, 0xffffff);
			b = "Returns the sky's current light level.";
			a(u, b, q / 2, 95, 0xffffff);

			b = "\247c%BlockLight%";
			a(u, b, q / 2, 105, 0xffffff);
			b = "Returns the block's light level.";
			a(u, b, q / 2, 115, 0xffffff);
			
			b = "\247c%Biome%";
			a(u, b, q / 2, 125, 0xffffff);
			b = "Returns the current biome.";
			a(u, b, q / 2, 135, 0xffffff);
		}
		else if (page == 3) {
			for (int x = 0; x < page3editboxes.size(); x++) {
				page3editboxes.get(x).c();
			}
			a(u, "Highlight block light when less than...", q / 2, 30, 0xffffff);
			a(u, "Highlight sky light when less than...", q / 2, 60, 0xffffff);
			a(u, "Highlight raw light when less than...", q / 2, 90, 0xffffff);
			a(u, "Highlight light level when less than...", q / 2, 120, 0xffffff);
			a(u, "\247cSetting a value to 0 will disable highlighting for that output", q / 2, 160, 0xffffff);
		}
		
		super.a(i, j, f);
	}
	
	public void a() {
		lightLevelEditbox.b();
		for (int x = 0; x < page3editboxes.size(); x++) {
			page3editboxes.get(x).b();
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
	
	public void e() {
		Keyboard.enableRepeatEvents(false);
	}
	
	// Custom functions
	
	public void generateLightStringPreview() {
		lightString = wmll.generateLightString(lightLevelEditbox.a());	
	}
	
	public void performSave() {
		outputOptions.put("lightString", lightLevelEditbox.a());
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
