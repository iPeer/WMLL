import org.lwjgl.input.Keyboard;


public class WMLLEnabledOutputs extends abm {

	protected WMLL wmll;
	protected abm parent;
	private String title;
	
	public WMLLEnabledOutputs(WMLL wmll, abm parent) {
		this.wmll = wmll;
		this.parent = parent;
		title = "WMLL Enabled Output Configuration";
	}
	
	@SuppressWarnings("unchecked")
	public void c() {
		byte o = -16;
		s.clear();
		s.add(new ain(0, q / 2 - 172, r / 4 - 5 + o, 170, 20, "Just Light: "+(wmll.isOutputEnabled(0) ? "ON" : "OFF")));
		s.add(new ain(1, q / 2 + 2, r / 4 - 5 + o, 170, 20, "Light & Indicators: "+(wmll.isOutputEnabled(1) ? "ON" : "OFF")));
		s.add(new ain(2, q / 2 - 172, r / 4 + 20 + o, 170, 20, "Light & FPS: "+(wmll.isOutputEnabled(2) ? "ON" : "OFF")));
		s.add(new ain(3, q / 2 + 2, r / 4 + 20 + o, 170, 20, "Light & Compass: "+(wmll.isOutputEnabled(3) ? "ON" : "OFF")));
		s.add(new ain(4, q / 2 - 172, r / 4 + 45 + o, 170, 20, "Light, Indicators & Compass: "+(wmll.isOutputEnabled(4) ? "ON" : "OFF")));
		s.add(new ain(5, q / 2 + 2, r / 4 + 45 + o, 170, 20, "Light, FPS & Compass: "+(wmll.isOutputEnabled(5) ? "ON" : "OFF")));
		s.add(new ain(6, q / 2 - 172, r / 4 + 70 + o, 170, 20, "Just Indicators: "+(wmll.isOutputEnabled(6) ? "ON" : "OFF")));
		s.add(new ain(7, q / 2 + 2, r / 4 + 70 + o, 170, 20, "Just FPS: "+(wmll.isOutputEnabled(7) ? "ON" : "OFF")));
		s.add(new ain(8, q / 2 - 172, r / 4 + 95 + o, 170, 20, "Just Compass: "+(wmll.isOutputEnabled(8) ? "ON" : "OFF")));
		s.add(new ain(9, q / 2 + 2, r / 4 + 95 + o, 170, 20, "Indicators & Compass: "+(wmll.isOutputEnabled(9) ? "ON" : "OFF")));
		s.add(new ain(10, q / 2 - 172, r / 4 + 120 + o, 170, 20, "FPS & Compass: "+(wmll.isOutputEnabled(10) ? "ON" : "OFF")));
		s.add(new ain(11, q / 2 + 2, r / 4 + 120 + o, 170, 20, "Nothing: "+(wmll.isOutputEnabled(11) ? "ON" : "OFF")));
		s.add(new ain(12, q / 2 - 172, r / 4 + 145 + o, 85, 20, "All ON"));
		s.add(new ain(13, q / 2 - 86, r / 4 + 145 + o, 85, 20, "All OFF"));
		s.add(new ain(14, q / 2 + 2, r / 4 + 145 + o, 170, 20, "Done"));
		if (WMLL.debugClassPresent)
			s.add(new ain(9001, q - 52, r - 22, 50, 20, "Reload"));
		
	}
	
	protected void a(ain b) {
		if (b.f == 9001) // Debug button
			p.a(new WMLLEnabledOutputs(wmll, parent));
		else if (b.f == 14) {
			p.a(parent);
		}
		else if (b.f == 12) { // All on
			for (int x = 0; x <= 11; x++) {
				if (WMLL.options.containsKey("Output"+x))
					WMLL.options.remove("Output"+x);
				String buttonText = ((ain)s.get(x)).e;
				((ain)s.get(x)).e = buttonText.replaceAll("OFF", "ON");
				WMLL.options.setProperty("AllOutputsOff", "false");
				WMLL.Enabled = true;
			}
		}
		else if (b.f == 13) { // All off
			for (int x = 0; x <= 11; x++) {
				WMLL.options.setProperty("Output"+x, "false");
				String buttonText = ((ain)s.get(x)).e;
				((ain)s.get(x)).e = buttonText.replaceAll("ON", "OFF");
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
			String buttonText = ((ain)s.get(outputID)).e;
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
			p.a(parent);
		else
			super.a(c, i);
	}
	
	public void a(int i, int j, float f) {
		u_();
		// (fontrenderer, text, x, y, colour)
		a(u, title, q / 2, 20, 0xffffff);
		WMLLOptions.renderWMLLVersion();
		super.a(i, j, f);
	}
	
}
