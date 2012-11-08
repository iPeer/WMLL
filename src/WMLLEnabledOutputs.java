
import org.lwjgl.input.Keyboard;


public class WMLLEnabledOutputs extends aue {

	protected WMLL wmll;
	protected aue parent;
	private String title;
	
	public WMLLEnabledOutputs(WMLL wmll, aue parent) {
		this.wmll = wmll;
		this.parent = parent;
		title = "WMLL Enabled Output Configuration";
	}
	
	@SuppressWarnings("unchecked")
	public void A_() {
		byte o = -16;
		i.clear();
		i.add(new ast(0, g / 2 - 172, g / 4 - 5 + o, 170, 20, "Just Light: "+(wmll.isOutputEnabled(0) ? "ON" : "OFF")));
		i.add(new ast(1, g / 2 + 2, g / 4 - 5 + o, 170, 20, "Light & Indicators: "+(wmll.isOutputEnabled(1) ? "ON" : "OFF")));
		i.add(new ast(2, g / 2 - 172, g / 4 + 20 + o, 170, 20, "Light & FPS: "+(wmll.isOutputEnabled(2) ? "ON" : "OFF")));
		i.add(new ast(3, g / 2 + 2, g / 4 + 20 + o, 170, 20, "Light & Compass: "+(wmll.isOutputEnabled(3) ? "ON" : "OFF")));
		i.add(new ast(4, g / 2 - 172, g / 4 + 45 + o, 170, 20, "Light, Indicators & Compass: "+(wmll.isOutputEnabled(4) ? "ON" : "OFF")));
		i.add(new ast(5, g / 2 + 2, g / 4 + 45 + o, 170, 20, "Light, FPS & Compass: "+(wmll.isOutputEnabled(5) ? "ON" : "OFF")));
		i.add(new ast(6, g / 2 - 172, g / 4 + 70 + o, 170, 20, "Just Indicators: "+(wmll.isOutputEnabled(6) ? "ON" : "OFF")));
		i.add(new ast(7, g / 2 + 2, g / 4 + 70 + o, 170, 20, "Just FPS: "+(wmll.isOutputEnabled(7) ? "ON" : "OFF")));
		i.add(new ast(8, g / 2 - 172, g / 4 + 95 + o, 170, 20, "Just Compass: "+(wmll.isOutputEnabled(8) ? "ON" : "OFF")));
		i.add(new ast(9, g / 2 + 2, g / 4 + 95 + o, 170, 20, "Indicators & Compass: "+(wmll.isOutputEnabled(9) ? "ON" : "OFF")));
		i.add(new ast(10, g / 2 - 172, g / 4 + 120 + o, 170, 20, "FPS & Compass: "+(wmll.isOutputEnabled(10) ? "ON" : "OFF")));
		i.add(new ast(11, g / 2 + 2, g / 4 + 120 + o, 170, 20, "Nothing: "+(wmll.isOutputEnabled(11) ? "ON" : "OFF")));
		i.add(new ast(12, g / 2 - 172, g / 4 + 145 + o, 85, 20, "All ON"));
		i.add(new ast(13, g / 2 - 86, g / 4 + 145 + o, 85, 20, "All OFF"));
		i.add(new ast(14, g / 2 + 2, g / 4 + 145 + o, 170, 20, "Done"));
		if (wmll.debugClassPresent)
			i.add(new ast(9001, g - 52, g - 22, 50, 20, "Reload"));
		
	}
	
	protected void a(ast b) {
		if (b.f == 9001) // Debug button
			f.a(new WMLLEnabledOutputs(wmll, parent));
		else if (b.f == 14) {
			f.a(parent);
		}
		else if (b.f == 12) { // All on
			for (int x = 0; x <= 11; x++) {
				if (WMLL.options.containsKey("Output"+x))
					WMLL.options.remove("Output"+x);
				String buttonText = ((ast)i.get(x)).e;
				((ast)i.get(x)).e = buttonText.replaceAll("OFF", "ON");
				WMLL.options.setProperty("AllOutputsOff", "false");
				WMLL.Enabled = true;
			}
		}
		else if (b.f == 13) { // All off
			for (int x = 0; x <= 11; x++) {
				WMLL.options.setProperty("Output"+x, "false");
				String buttonText = ((ast)i.get(x)).e;
				((ast)i.get(x)).e = buttonText.replaceAll("ON", "OFF");
				WMLL.options.setProperty("AllOutputsOff", "true");
				WMLL.Enabled = false;
			}
		}
		else {
			int outputID = b.f;
			boolean enabled = wmll.isOutputEnabled(outputID);
			WMLL.options.setProperty("Output"+outputID, Boolean.toString(!enabled));
			if (!enabled == true) {
				WMLL.options.setProperty("AllOutputsOff", "false");
				WMLL.Enabled = !enabled;
			}
			String buttonText = ((ast)i.get(outputID)).e;
			b.e = buttonText.split(":")[0]+": "+(wmll.isOutputEnabled(outputID) ? "ON" : "OFF");
			System.out.println(wmll.areAllOutputsDisabled());
			if (wmll.areAllOutputsDisabled() && !enabled == false) {
				WMLL.options.setProperty("AllOutputsOff", "true");
				WMLL.Enabled = false;
				return;
			}
			while (!wmll.isOutputEnabled(WMLL.WMLLI))
				WMLL.WMLLI++;
		}
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
	
}
