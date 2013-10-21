import org.lwjgl.input.Keyboard;


public class WMLLOptionsCompat extends awe {

	private WMLL wmll;
	private awe parent;
	private aut rei, zans, alien;
	private int page = 1;

	public WMLLOptionsCompat(WMLL wmll, awe aum) {
		this(wmll, aum, 1);
	}

	public WMLLOptionsCompat(WMLL wmll, awe parent2, int page) {
		this.wmll = wmll;
		this.parent = parent2;
		this.page = page;
	}

	@SuppressWarnings("unchecked")
	public void A_() {
		i.clear();
		if (wmll.debugClassPresent || WMLL.WMLLDebugActive())
			i.add(new aut(9001, g - 60, 0, 60, 20, "Reload GUI"));
		i.add(new aut(0, g / 2 - 190, h - 30, (this.page == 1 ? 295 : 380), 20, "Done"));
		if (this.page == 1) {
			i.add(new aut(5, g / 2 + 105, h - 30, 85, 20, "Mod Statuses"));
			i.add(rei = new aut(1, g / 2 - 145, h / 4 + 45, 140, 20, "Rei's Minimap: "+(wmll.ReiEnabled && !wmll.ReiUseMl ? "ON" : "OFF")));
			i.add(zans = new aut(2, g / 2 + 2, h / 4 + 45, 140, 20, "Zan's Minimap: "+(wmll.ZansEnabled && wmll.ZansMinimap ? "ON" : "OFF")));
			i.add(alien = new aut(3, g / 2 - 70, h / 4 + 70, 140, 20, "Alien Motion Detector: "+(wmll.AlienEnabled && wmll.AlienRadar ? "ON" : "OFF")));
			rei.h = wmll.Rei && !wmll.ReiUseMl;
			zans.h = wmll.ZansMinimap;
			alien.h = wmll.AlienRadar;
		}
	}

	protected void a(char c, int i) {
		if (i == Keyboard.KEY_ESCAPE)
			f.a(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? null : parent);
	}

	protected void a(aut b) {
		switch (b.g) {
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
		case 5:
			f.a(new WMLLOptionsCompat(this.wmll, this.parent, 2));
			return;
		}
	}

	public void a(int i, int j, float f) {
		e();
		if (this.page == 1) {
			a(o, "This menu allows you to change how WMLL handles it's compatibility with", g / 2, h / 4 - 52, 0xffffff);
			a(o, "other conflicting mods you may have installed. If the mod's button is greyed out,", g / 2, h / 4 - 40, 0xffffff);
			a(o, "it means that the mod is not installed or WMLL could not detect it.", g / 2, h / 4 - 28, 0xffffff);
			a(o, "If a mod you have installed is greyed out, click \247oMod Statuses\247r", g / 2, h / 4 - 4, 0xffffff);
			a(o, "to see what went wrong.", h / 2, h / 4 + 8, 0xffffff);
			
			a(o, "Minimaps", g / 2, h / 4 + 32, 0xbbbbbb);
		}
		else {
			a(o, "This menu allows you to see what mods are loaded, and if they're not", g / 2, h / 4 - 40, 0xffffff);
			a(o, "gives information on what went wrong when WMLL tried to load them.", g / 2, h / 4 - 28, 0xffffff);
			a(o, "Rei's Minimap: "+(wmll.reiError.equals("") ? "\247aOK" : "\247c"+(wmll.reiError.contains(":") ? wmll.reiError.split(":")[0] : wmll.reiError)), g / 2, h / 4, 0xffffff);
			a(o, "Zan's Minimap: "+(wmll.zanError.equals("") ? "\247aOK" : "\247c"+(wmll.zanError.contains(":") ? wmll.zanError.split(":")[0] : wmll.zanError)), g / 2, h / 4 + 12, 0xffffff);
			a(o, "Alien MT: "+(wmll.alienError.equals("") ? "\247aOK" : "\247c"+(wmll.alienError.contains(":") ? wmll.alienError.split(":")[0] : wmll.alienError)), g / 2, h / 4 + 24, 0xffffff);
			a(o, "Forge: "+(wmll.forgeError.equals("") || wmll.useML ? "\247aOK" : "\247c"+(wmll.forgeError.contains(":") ? wmll.forgeError.split(":")[0] : wmll.forgeError)), g / 2, h / 4 + 36, 0xffffff);
		}
		super.a(i, j, f);
	}

	private void updateButtons() {
		rei.f = "Rei's Minimap: "+(wmll.ReiEnabled && !wmll.ReiUseMl ? "ON" : "OFF");
		zans.f = "Zan's Minimap: "+(wmll.ZansEnabled ? "ON" : "OFF");
		alien.f = "Alien Motion Detector: "+(wmll.AlienEnabled ? "ON" : "OFF");
	}

}
