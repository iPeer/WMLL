import org.lwjgl.input.Keyboard;


public class WMLLOptionsMisc extends axr {

	private WMLL wmll;
	private axr parent;
	private int resetClicks = 0;
	
	public WMLLOptionsMisc(WMLL wmll, axr aum) {
		this.wmll = wmll;
		this.parent = aum;
	}
	
	@SuppressWarnings("unchecked")
	public void A_() {
		k.clear();
		if (wmll.debugClassPresent)
			k.add(new awg(9001, h - 20, 0, 20, 20, "R"));
		k.add(new awg(0, h / 2 - 190, h - 30, 380, 20, "Done"));
		k.add(new awg(1, h / 2 - 100, h / 4 + 25, 200, 20, "Reload Settings from file"));
		k.add(new awg(2, h / 2 - 100, h / 4 + 50, 200, 20, "Reset Settings to defaults"));
		k.add(new awg(3, h / 2 - 100, h / 4, 200, 20, "Automatically check for updates: "+(wmll.autoUpdateCheck ? "ON" : "OFF")));
	}
	
	public void a(awg b) {
		switch (b.f) {
		case 9001:
			g.a(new WMLLOptionsMisc(this.wmll, this.parent));
			return;
		case 0:
			g.a(parent);
			return;
		case 1:
			wmll.loadOptions();
			g.a(new WMLLOptionsMisc(this.wmll, this.parent));
			return;
		case 2:
			resetClicks++;
			if (resetClicks == 2) {
				WMLL.options.clear();
				wmll.deleteSettingsFile();
				wmll.loadOptions();
				wmll.lastWorld = "";
				wmll.warnedAboutConflicts = false;
				g.a(new WMLLOptionsMisc(this.wmll, this.parent));
			}
			return;
		}
	}
	
	protected void a(char c, int i) {
		if (i == Keyboard.KEY_ESCAPE)
			g.a(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? null : parent);
	}
	
	public void a(int i, int j, float f) {
		e();
		if (resetClicks == 1)
			a(m, "\247cClick again to confirm.", h / 2, this.i / 4 + 75, 0xffffff);
		super.a(i, j, f);
	}

}
