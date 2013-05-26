import org.lwjgl.input.Keyboard;


public class WMLLOptionsGeneral extends axr {

	private WMLL wmll;
	private axr parent;
	private boolean isBinding;
	private awg bindingButton, autohideForce;

	public WMLLOptionsGeneral(WMLL wmll, axr aum) {
		this.wmll = wmll;
		this.parent = aum;
	}

	@SuppressWarnings("unchecked")
	public void A_() {
		k.clear();
		if (wmll.debugClassPresent)
			k.add(new awg(9001, h - 20, 0, 20, 20, "R"));
		String enabledString = "Enabled on "+wmll.getWorldName()+": "+(WMLL.Enabled ? "Yes" : "No");
		k.add(new awg(0, h / 2 - 150, i / 4, 300, 20, enabledString));
		k.add(bindingButton = new awg(1, h / 2 - 150, i / 4 - 25, 98, 20, "Cycle Key: "+Keyboard.getKeyName(WMLL.F4Key)));
		k.add(new awg(2, h / 2 - 190, i - 25, 380, 20, "Done"));
		k.add(new awg(3, h / 2 - 50, i / 4 - 25, 200, 20, "Auto detect SP world seeds: "+(wmll.autoSeed ? "ON" : "OFF")));
		k.add(new awg(4, h / 2 - 150, i / 4 + 25, 300, 20, "Autohide when a GUI is open: "+(wmll.showUnderGUIs ? "OFF" : "ON")));
		k.add(autohideForce = new awg(6, h / 2 - 150, i / 4 + 50, 300, 20, "Always obey autohide setting: "+(wmll.forceAutohideObey ? "ON" : "OFF")));
		k.add(new awg(5, h / 2 - 150, i / 4 + 75, 300, 20, "Show world name/server IP on main menu: "+(!wmll.showWorldName ? "OFF" : "ON")));
		autohideForce.g = wmll.useML;
	}

	protected void a(awg b) {
		switch (b.f) {
		case 9001:
			g.a(new WMLLOptionsGeneral(this.wmll, this.parent));
			return;
		case 0:
			boolean a = WMLL.Enabled;
			WMLL.options.setProperty("World-"+wmll.getWorldName(), Boolean.toString(!a));
			b.e = "Enabled on "+(wmll.isMultiplayer() ? wmll.getWorldName().split(":")[0] : wmll.getWorldName())+": "+(!a ? "Yes" : "No");
			WMLL.Enabled = !a;
			return;
		case 1:
			isBinding = true;
			b.e = "Press a key...";
			return;
		case 2:
			if (parent != null)
				g.a(parent);
			else
				g.a((axl)null);
			return;
		case 3:
			boolean c = wmll.autoSeed;
			b.e = "Auto detect SSP world seeds: "+(c ? "ON" : "OFF");
			wmll.autoSeed = !c;
			return;
		case 4:
			c = wmll.showUnderGUIs;
			b.e = "Autohide when a GUI is open: "+(!c ? "OFF" : "ON");
			wmll.showUnderGUIs = !c;
			return;
		case 5:
			c = wmll.showWorldName;
			b.e = "Show world name/server IP on main menu: "+(c ? "OFF" : "ON");
			wmll.showWorldName = !c;
			g.a(new WMLLOptionsGeneral(this.wmll, this.parent));
			return;
		case 6:
			c = wmll.forceAutohideObey;
			b.e = "Always obey autohide setting: "+(c ? "OFF" : "ON");
			wmll.forceAutohideObey = !c;
			return;
		}
	}

	protected void a(char c, int i) {
		if (isBinding && i != Keyboard.KEY_ESCAPE) {
			WMLL.F4Key = i;
			isBinding = false;
			bindingButton.e = "Cycle Key: "+Keyboard.getKeyName(i);
		}
		else if (i == Keyboard.KEY_ESCAPE && parent != null)
			g.a(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? null : parent);
	}

	public void a(int i, int j, float f) {
		e();
		super.a(i, j, f);
	}

}
