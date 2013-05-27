import org.lwjgl.input.Keyboard;


public class WMLLOptionsCompat extends axr {

	private WMLL wmll;
	private axr parent;
	private awg rei, zans, alien;
	private int page = 1;

	public WMLLOptionsCompat(WMLL wmll, axr aum) {
		this(wmll, aum, 1);
	}

	public WMLLOptionsCompat(WMLL wmll, axr aum, int page) {
		this.wmll = wmll;
		this.parent = aum;
		this.page = page;
	}

	@SuppressWarnings("unchecked")
	public void A_() {
		k.clear();
		if (wmll.debugClassPresent)
			k.add(new awg(9001, h - 20, 0, 20, 20, "R"));
		k.add(new awg(0, h / 2 - 190, i - 30, (this.page == 1 ? 295 : 380), 20, "Done"));
		if (this.page == 1) {
			k.add(new awg(5, h / 2 + 105, i - 30, 85, 20, "Mod Statuses"));
			k.add(rei = new awg(1, h / 2 - 145, i / 4 + 45, 140, 20, "Rei's Minimap: "+(wmll.ReiEnabled && !wmll.ReiUseMl ? "ON" : "OFF")));
			k.add(zans = new awg(2, h / 2 + 2, i / 4 + 45, 140, 20, "Zan's Minimap: "+(wmll.ZansEnabled && wmll.ZansMinimap ? "ON" : "OFF")));
			k.add(alien = new awg(3, h / 2 - 70, i / 4 + 70, 140, 20, "Alien Motion Detector: "+(wmll.AlienEnabled && wmll.AlienRadar ? "ON" : "OFF")));
			rei.g = wmll.Rei && !wmll.ReiUseMl;
			zans.g = wmll.ZansMinimap;
			alien.g = wmll.AlienRadar;
		}
	}

	protected void a(char c, int i) {
		if (i == Keyboard.KEY_ESCAPE)
			g.a(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? null : parent);
	}

	protected void a(awg b) {
		switch (b.f) {
		case 9001:
			g.a(new WMLLOptionsCompat(this.wmll, this.parent, this.page));
			return;
		case 0:
			if (this.page == 2)
				g.a(new WMLLOptionsCompat(this.wmll, this.parent, 1));
			else
				g.a(parent);
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
			g.a(new WMLLOptionsCompat(this.wmll, this.parent, 2));
			return;
		}
	}

	public void a(int i, int j, float f) {
		e();
		if (this.page == 1) {
			a(m, "This menu allows you to change how WMLL handles it's compatibility with", h / 2, this.i / 4 - 52, 0xffffff);
			a(m, "other conflicting mods you may have installed. If the mod's button is greyed out,", h / 2, this.i / 4 - 40, 0xffffff);
			a(m, "it means that the mod is not installed or WMLL could not detect it.", h / 2, this.i / 4 - 28, 0xffffff);
			a(m, "If a mod you have installed is greyed out, click \247oMod Statuses\247r", h / 2, this.i / 4 - 4, 0xffffff);
			a(m, "to see what went wrong.", h / 2, this.i / 4 + 8, 0xffffff);
			
			a(m, "Minimaps", h / 2, this.i / 4 + 32, 0xbbbbbb);
		}
		else {
			a(m, "This menu allows you to see what mods are loaded, and if they're not", h / 2, this.i / 4 - 40, 0xffffff);
			a(m, "gives information on what went wrong when WMLL tried to load them.", h / 2, this.i / 4 - 28, 0xffffff);
			a(m, "Rei's Minimap: "+(wmll.reiError.equals("") ? "\247aOK" : "\247c"+(wmll.reiError.contains(":") ? wmll.reiError.split(":")[0] : wmll.reiError)), h / 2, this.i / 4, 0xffffff);
			a(m, "Zan's Minimap: "+(wmll.zanError.equals("") ? "\247aOK" : "\247c"+(wmll.zanError.contains(":") ? wmll.zanError.split(":")[0] : wmll.zanError)), h / 2, this.i / 4 + 12, 0xffffff);
			a(m, "Alien MT: "+(wmll.alienError.equals("") ? "\247aOK" : "\247c"+(wmll.alienError.contains(":") ? wmll.alienError.split(":")[0] : wmll.alienError)), h / 2, this.i / 4 + 24, 0xffffff);
			a(m, "Forge: "+(wmll.forgeError.equals("") || wmll.useML ? "\247aOK" : "\247c"+(wmll.forgeError.contains(":") ? wmll.forgeError.split(":")[0] : wmll.forgeError)), h / 2, this.i / 4 + 36, 0xffffff);
		}
		super.a(i, j, f);
	}

	private void updateButtons() {
		rei.e = "Rei's Minimap: "+(wmll.ReiEnabled && !wmll.ReiUseMl ? "ON" : "OFF");
		zans.e = "Zan's Minimap: "+(wmll.ZansEnabled ? "ON" : "OFF");
		alien.e = "Alien Motion Detector: "+(wmll.AlienEnabled ? "ON" : "OFF");
	}

}
