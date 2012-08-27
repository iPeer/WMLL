
import org.lwjgl.input.Keyboard;


public class WMLLEnabledOutputs extends apn {

	protected WMLL wmll;
	protected apn parent;
	private String title;
	
	public WMLLEnabledOutputs(WMLL wmll, apn parent) {
		this.wmll = wmll;
		this.parent = parent;
		title = "WMLL Enabled Output Configuration";
	}
	
	@SuppressWarnings("unchecked")
	public void w_() {
		byte o = -16;
		h.clear();
		h.add(new aoh(0, f / 2 - 172, g / 4 - 5 + o, 170, 20, "Just Light: "+(wmll.isOutputEnabled(0) ? "ON" : "OFF")));
		h.add(new aoh(1, f / 2 + 2, g / 4 - 5 + o, 170, 20, "Light & Indicators: "+(wmll.isOutputEnabled(1) ? "ON" : "OFF")));
		h.add(new aoh(2, f / 2 - 172, g / 4 + 20 + o, 170, 20, "Light & FPS: "+(wmll.isOutputEnabled(2) ? "ON" : "OFF")));
		h.add(new aoh(3, f / 2 + 2, g / 4 + 20 + o, 170, 20, "Light & Compass: "+(wmll.isOutputEnabled(3) ? "ON" : "OFF")));
		h.add(new aoh(4, f / 2 - 172, g / 4 + 45 + o, 170, 20, "Light, Indicators & Compass: "+(wmll.isOutputEnabled(4) ? "ON" : "OFF")));
		h.add(new aoh(5, f / 2 + 2, g / 4 + 45 + o, 170, 20, "Light, FPS & Compass: "+(wmll.isOutputEnabled(5) ? "ON" : "OFF")));
		h.add(new aoh(6, f / 2 - 172, g / 4 + 70 + o, 170, 20, "Just Indicators: "+(wmll.isOutputEnabled(6) ? "ON" : "OFF")));
		h.add(new aoh(7, f / 2 + 2, g / 4 + 70 + o, 170, 20, "Just FPS: "+(wmll.isOutputEnabled(7) ? "ON" : "OFF")));
		h.add(new aoh(8, f / 2 - 172, g / 4 + 95 + o, 170, 20, "Just Compass: "+(wmll.isOutputEnabled(8) ? "ON" : "OFF")));
		h.add(new aoh(9, f / 2 + 2, g / 4 + 95 + o, 170, 20, "Indicators & Compass: "+(wmll.isOutputEnabled(9) ? "ON" : "OFF")));
		h.add(new aoh(10, f / 2 - 172, g / 4 + 120 + o, 170, 20, "FPS & Compass: "+(wmll.isOutputEnabled(10) ? "ON" : "OFF")));
		h.add(new aoh(11, f / 2 + 2, g / 4 + 120 + o, 170, 20, "Nothing: "+(wmll.isOutputEnabled(11) ? "ON" : "OFF")));
		h.add(new aoh(12, f / 2 - 172, g / 4 + 145 + o, 85, 20, "All ON"));
		h.add(new aoh(13, f / 2 - 86, g / 4 + 145 + o, 85, 20, "All OFF"));
		h.add(new aoh(14, f / 2 + 2, g / 4 + 145 + o, 170, 20, "Done"));
		if (wmll.debugClassPresent)
			h.add(new aoh(9001, f - 52, g - 22, 50, 20, "Reload"));
		
	}
	
	protected void a(aoh b) {
		if (b.f == 9001) // Debug button
			e.a(new WMLLEnabledOutputs(wmll, parent));
		else if (b.f == 14) {
			e.a(parent);
		}
		else if (b.f == 12) { // All on
			for (int x = 0; x <= 11; x++) {
				if (WMLL.options.containsKey("Output"+x))
					WMLL.options.remove("Output"+x);
				String buttonText = ((aoh)h.get(x)).e;
				((aoh)h.get(x)).e = buttonText.replaceAll("OFF", "ON");
				WMLL.options.setProperty("AllOutputsOff", "false");
				WMLL.Enabled = true;
			}
		}
		else if (b.f == 13) { // All off
			for (int x = 0; x <= 11; x++) {
				WMLL.options.setProperty("Output"+x, "false");
				String buttonText = ((aoh)h.get(x)).e;
				((aoh)h.get(x)).e = buttonText.replaceAll("ON", "OFF");
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
			String buttonText = ((aoh)h.get(outputID)).e;
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
			e.a(parent);
		else
			super.a(c, i);
	}
	
	public void a(int i, int j, float f) {
		v_();
		// (fontrenderer, text, x, y, colour)
		a(k, title, this.f / 2, 20, 0xffffff);
		WMLLOptions.renderWMLLVersion();
		super.a(i, j, f);
	}
	
}
