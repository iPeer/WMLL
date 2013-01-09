import org.lwjgl.input.Keyboard;


public class WMLLOptionsCompat extends aul {

	private WMLL wmll;
	private aul parent;
	private atb rei, zans, alien, forge;
	private int page = 1;

	public WMLLOptionsCompat(WMLL wmll, aul aum) {
		this(wmll, aum, 1);
	}

	public WMLLOptionsCompat(WMLL wmll, aul aum, int page) {
		this.wmll = wmll;
		this.parent = aum;
		this.page = page;
	}

	@SuppressWarnings("unchecked")
	public void A_() {
		i.clear();
		if (wmll.debugClassPresent)
			i.add(new atb(9001, g - 20, 0, 20, 20, "R"));
		i.add(new atb(0, g / 2 - 190, h - 30, (this.page == 1 ? 295 : 380), 20, "Done"));
		if (this.page == 1) {
			i.add(new atb(5, g / 2 + 105, h - 30, 85, 20, "Mod Statuses"));
			i.add(rei = new atb(1, g / 2 - 145, h / 4 + 45, 140, 20, "Rei's Minimap: "+(wmll.ReiEnabled && !wmll.ReiUseMl ? "ON" : "OFF")));
			i.add(zans = new atb(2, g / 2 + 2, h / 4 + 45, 140, 20, "Zan's Minimap: "+(wmll.ZansEnabled && wmll.ZansMinimap ? "ON" : "OFF")));
			i.add(alien = new atb(3, g / 2 - 70, h / 4 + 70, 140, 20, "Alien Motion Detector: "+(wmll.AlienEnabled && wmll.AlienRadar ? "ON" : "OFF")));
			rei.g = wmll.Rei && !wmll.ReiUseMl;
			zans.g = wmll.ZansMinimap;
			alien.g = wmll.AlienRadar;

			i.add(forge = new atb(4, g / 2 - 70, h / 4 + 120, 140, 20, "Enable Forge Hooks: "+(wmll.forgeEnabled ? "ON" : "OFF")));
			forge.g = wmll.forgeDetected;
		}
	}

	protected void a(char c, int i) {
		if (i == Keyboard.KEY_ESCAPE)
			f.a(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? null : parent);
	}

	protected void a(atb b) {
		switch (b.f) {
		case 9001:
			f.a(new WMLLOptionsCompat(this.wmll, this.parent, this.page));
			return;
		case 0:
			if (this.page == 2)
				f.a(new WMLLOptionsCompat(this.wmll, this.parent, 1));
			else
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
		case 5:
			f.a(new WMLLOptionsCompat(this.wmll, this.parent, 2));
			return;
		}
	}

	public void a(int i, int j, float f) {
		e();
		if (this.page == 1) {
			a(l, "This menu allows you to change how WMLL handles it's compatibility with", g / 2, h / 4 - 52, 0xffffff);
			a(l, "other conflicting mods you may have installed. If the mod's button is greyed out,", g / 2, h / 4 - 40, 0xffffff);
			a(l, "it means that the mod is not installed or WMLL could not detect it.", g / 2, h / 4 - 28, 0xffffff);
			a(l, "If a mod you have installed is greyed out, click \247oMod Statuses\247r", g / 2, h / 4 - 4, 0xffffff);
			a(l, "to see what went wrong.", g / 2, h / 4 + 8, 0xffffff);
			
			a(l, "Minimaps", g / 2, h / 4 + 32, 0xbbbbbb);

			a(l, "Other", g / 2, h / 4 + 105, 0xbbbbbb);
		}
		else {
			a(l, "This menu allows you to see what mods are loaded, and if they're not", g / 2, h / 4 - 40, 0xffffff);
			a(l, "gives information on what went wrong when WMLL tried to load them.", g / 2, h / 4 - 28, 0xffffff);
			a(l, "Rei's Minimap: "+(wmll.reiError.equals("") ? "\247aOK" : "\247c"+(wmll.reiError.contains(":") ? wmll.reiError.split(":")[0] : wmll.reiError)), g / 2, h / 4, 0xffffff);
			a(l, "Zan's Minimap: "+(wmll.zanError.equals("") ? "\247aOK" : "\247c"+(wmll.zanError.contains(":") ? wmll.zanError.split(":")[0] : wmll.zanError)), g / 2, h / 4 + 12, 0xffffff);
			a(l, "Alien MT: "+(wmll.alienError.equals("") ? "\247aOK" : "\247c"+(wmll.alienError.contains(":") ? wmll.alienError.split(":")[0] : wmll.alienError)), g / 2, h / 4 + 24, 0xffffff);
			a(l, "Forge: "+(wmll.forgeError.equals("") ? "\247aOK" : "\247c"+(wmll.forgeError.contains(":") ? wmll.forgeError.split(":")[0] : wmll.forgeError)), g / 2, h / 4 + 36, 0xffffff);
		}
		super.a(i, j, f);
	}

	private void updateButtons() {
		rei.e = "Rei's Minimap: "+(wmll.ReiEnabled && !wmll.ReiUseMl ? "ON" : "OFF");
		zans.e = "Zan's Minimap: "+(wmll.ZansEnabled ? "ON" : "OFF");
		alien.e = "Alien Motion Detector: "+(wmll.AlienEnabled ? "ON" : "OFF");
	}

}
