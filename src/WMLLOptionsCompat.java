import org.lwjgl.input.Keyboard;


public class WMLLOptionsCompat extends aue {

	private WMLL wmll;
	private aue parent;
	private ast rei, zans, alien, forge;

	public WMLLOptionsCompat(WMLL wmll, aue aue) {
		this.wmll = wmll;
		this.parent = aue;
	}

	@SuppressWarnings("unchecked")
	public void A_() {
		i.clear();
		if (wmll.debugClassPresent)
			i.add(new ast(9001, g - 20, 0, 20, 20, "R"));
		i.add(new ast(0, g / 2 - 190, h - 30, 380, 20, "Done"));
		i.add(rei = new ast(1, g / 2 - 145, h / 4 + 25, 140, 20, "Rei's Minimap: "+(wmll.ReiEnabled && !wmll.ReiUseMl ? "ON" : "OFF")));
		i.add(zans = new ast(2, g / 2 + 2, h / 4 + 25, 140, 20, "Zan's Minimap: "+(wmll.ZansEnabled && wmll.ZansMinimap ? "ON" : "OFF")));
		i.add(alien = new ast(3, g / 2 - 70, h / 4 + 50, 140, 20, "Alien Motion Detector: "+(wmll.AlienEnabled && wmll.AlienRadar ? "ON" : "OFF")));
		rei.g = wmll.Rei && !wmll.ReiUseMl;
		zans.g = wmll.ZansMinimap;
		alien.g = wmll.AlienRadar;

		i.add(forge = new ast(4, g / 2 - 70, h / 4 + 100, 140, 20, "Enable Forge Hooks: "+(wmll.forgeEnabled ? "ON" : "OFF")));
		forge.g = wmll.forgeDetected;
	}

	protected void a(char c, int i) {
		if (i == Keyboard.KEY_ESCAPE)
			f.a(parent);
	}

	protected void a(ast b) {
		switch (b.f) {
		case 9001:
			f.a(new WMLLOptionsCompat(this.wmll, this.parent));
			return;
		case 0:
			f.a(parent);
			return;
		case 1:
			boolean a = wmll.ReiEnabled;
			wmll.ReiEnabled = !a;
			if (!a)
				wmll.ZansEnabled = wmll.AlienEnabled = false;
			updateButtons();
			return;
		case 2:
			a = wmll.ZansEnabled;
			wmll.ZansEnabled = !a;
			if (!a)
				wmll.ReiEnabled = wmll.AlienEnabled = false;
			updateButtons();
			return;
		case 3:
			a = wmll.AlienEnabled;
			wmll.AlienEnabled = !a;
			if (!a)
				wmll.ReiEnabled = wmll.ZansEnabled = false;
			updateButtons();
			return;
		case 4:
			a = wmll.forgeEnabled;
			b.e = "Enable Forge Hooks: "+(!a ? "ON" : "OFF");
			wmll.forgeEnabled = !a;
			return;

		}
	}

	public void a(int i, int j, float f) {
		z_();

		a(l, "This menu allows you to change how WMLL handles it's compatibility with", g / 2, h / 4 - 40, 0xffffff);
		a(l, "other conflicting mods you may have installed. If the mod's button is greyed out,", g / 2, h / 4 - 28, 0xffffff);
		a(l, "it means that the mod is not installed or WMLL could not detect it.", g / 2, h / 4 - 16, 0xffffff);

		a(l, "Minimaps", g / 2, h / 4 + 8, 0xbbbbbb);

		a(l, "Other", g / 2, h / 4 + 85, 0xbbbbbb);
		super.a(i, j, f);
	}

	private void updateButtons() {
		rei.e = "Rei's Minimap: "+(wmll.ReiEnabled && !wmll.ReiUseMl ? "ON" : "OFF");
		zans.e = "Zan's Minimap: "+(wmll.ZansEnabled ? "ON" : "OFF");
		alien.e = "Alien Motion Detector: "+(wmll.AlienEnabled ? "ON" : "OFF");
	}

}
