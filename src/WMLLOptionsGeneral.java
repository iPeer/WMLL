import org.lwjgl.input.Keyboard;


public class WMLLOptionsGeneral extends awe {

	private WMLL wmll;
	private awe parent;
	private boolean isBinding;
	private aut bindingButton, autohideForce;

	public WMLLOptionsGeneral(WMLL wmll, awe parent2) {
		this.wmll = wmll;
		this.parent = parent2;
	}

	@SuppressWarnings("unchecked")
	public void A_() {
		i.clear();
		if (wmll.debugClassPresent || WMLL.WMLLDebugActive())
			i.add(new aut(9001, g - 60, 0, 60, 20, "Reload GUI"));
		String enabledString = "Enabled on "+wmll.getWorldName()+": "+(WMLL.Enabled ? "Yes" : "No");
		i.add(new aut(0, g / 2 - 150, h / 4, 300, 20, enabledString));
		i.add(bindingButton = new aut(1, g / 2 - 150, h / 4 - 25, 98, 20, "Cycle Key: "+Keyboard.getKeyName(WMLL.F4Key)));
		i.add(new aut(2, g / 2 - 190, h - 25, 380, 20, "Done"));
		i.add(new aut(3, g / 2 - 50, h / 4 - 25, 200, 20, "Auto detect SP world seeds: "+(wmll.autoSeed ? "ON" : "OFF")));
		i.add(new aut(4, g / 2 - 150, h / 4 + 25, 300, 20, "Autohide when a GUI is open: "+(wmll.showUnderGUIs ? "OFF" : "ON")));
		i.add(autohideForce = new aut(6, g / 2 - 150, h / 4 + 50, 300, 20, "Always obey autohide setting: "+(wmll.forceAutohideObey ? "ON" : "OFF")));
		i.add(new aut(5, g / 2 - 150, h / 4 + 75, 300, 20, "Show world name/server IP on main menu: "+(!wmll.showWorldName ? "OFF" : "ON")));
		autohideForce.h = wmll.useML;
	}

	protected void a(aut b) {
		switch (b.g) {
		case 9001:
			f.a(new WMLLOptionsGeneral(this.wmll, this.parent));
			return;
		case 0:
			boolean a = WMLL.Enabled;
			WMLL.options.setProperty("World-"+wmll.getWorldName(), Boolean.toString(!a));
			b.f = "Enabled on "+(wmll.isMultiplayer() ? wmll.getWorldName().split(":")[0] : wmll.getWorldName())+": "+(!a ? "Yes" : "No");
			WMLL.Enabled = !a;
			return;
		case 1:
			isBinding = true;
			b.f = "Press a key...";
			return;
		case 2:
			if (parent != null)
				f.a(parent);
			else
				f.a((awe)null);
			return;
		case 3:
			boolean c = wmll.autoSeed;
			b.f = "Auto detect SSP world seeds: "+(c ? "ON" : "OFF");
			wmll.autoSeed = !c;
			return;
		case 4:
			c = wmll.showUnderGUIs;
			b.f = "Autohide when a GUI is open: "+(!c ? "OFF" : "ON");
			wmll.showUnderGUIs = !c;
			return;
		case 5:
			c = wmll.showWorldName;
			b.f = "Show world name/server IP on main menu: "+(c ? "OFF" : "ON");
			wmll.showWorldName = !c;
			f.a(new WMLLOptionsGeneral(this.wmll, this.parent));
			return;
		case 6:
			c = wmll.forceAutohideObey;
			b.f = "Always obey autohide setting: "+(c ? "OFF" : "ON");
			wmll.forceAutohideObey = !c;
			return;
		}
	}

	protected void a(char c, int i) {
		if (isBinding && i != Keyboard.KEY_ESCAPE) {
			WMLL.F4Key = i;
			isBinding = false;
			bindingButton.f = "Cycle Key: "+Keyboard.getKeyName(i);
		}
		else if (i == Keyboard.KEY_ESCAPE && parent != null)
			f.a(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? null : parent);
	}

	public void a(int i, int j, float f) {
		e();
		super.a(i, j, f);
	}

}
