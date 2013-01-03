import org.lwjgl.input.Keyboard;


public class WMLLOptionsGeneral extends auy {

	private WMLL wmll;
	private auy parent;
	private boolean isBinding, hasErrored;
	private ato bindingButton;

	public WMLLOptionsGeneral(WMLL wmll, auy aum) {
		this.wmll = wmll;
		this.parent = aum;
	}

	@SuppressWarnings("unchecked")
	public void y_() {
		i.clear();
		if (wmll.debugClassPresent)
			i.add(new ato(9001, g - 20, 0, 20, 20, "R"));
		String enabledString = "Enabled on "+wmll.getWorldName()+": "+(WMLL.Enabled ? "Yes" : "No");
		i.add(new ato(0, g / 2 - 150, h / 4, 300, 20, enabledString));
		i.add(bindingButton = new ato(1, g / 2 - 150, h / 4 - 25, 98, 20, "Cycle Key: "+Keyboard.getKeyName(WMLL.F4Key)));
		i.add(new ato(2, g / 2 - 190, h - 25, 380, 20, "Done"));
		i.add(new ato(3, g / 2 - 50, h / 4 - 25, 200, 20, "Auto detect SP world seeds: "+(wmll.autoSeed ? "ON" : "OFF")));
		i.add(new ato(4, g / 2 - 150, h / 4 + 25, 300, 20, "Autohide when a GUI is open: "+(wmll.showUnderGUIs ? "OFF" : "ON")));
		i.add(new ato(5, g / 2 - 150, h / 4 + 50, 300, 20, "Show world name/server IP on main menu: "+(!wmll.showWorldName ? "OFF" : "ON")));
	}

	protected void a(ato b) {
		switch (b.f) {
		case 9001:
			f.a(new WMLLOptionsGeneral(this.wmll, this.parent));
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
				f.a(parent);
			else
				f.a((auy)null);
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
			f.a(new WMLLOptionsGeneral(this.wmll, this.parent));
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
			f.a(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? null : parent);
	}

	public void a(int i, int j, float f) {
		e();
		super.a(i, j, f);
	}

}
