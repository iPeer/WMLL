import org.lwjgl.input.Keyboard;


public class WMLLGuiSMPSeed extends xa {
	
	protected WMLL wmll;
	private xa parent;
	private String title;
	
	private aiv seedBox;

	public WMLLGuiSMPSeed(WMLL wmll, xa parent) {
		this.wmll = wmll;
		this.parent = parent;
		this.title = "WMLL SMP Seed Configuration";
	}
	@SuppressWarnings("unchecked")
	public void c() {
		Keyboard.enableRepeatEvents(true);
		s.clear();
		byte o = -16;
		if (WMLL.debugClassPresent)
			s.add(new adi(9001, q - 52, r - 22, 50, 20, "Reload"));
		/*
		 * New Editbox:
		 * new aiv(Gui, fontrenderer, posx, posy, width, height, default text);
		 */
		seedBox = new aiv(/*this,*/ u, q / 2 - ((wmll.getWindowSize().a() - 20) / 2), 110, wmll.getWindowSize().a() - 20, 20/*, WMLL.options.getProperty("Seed:"+wmll.getWorldName().toLowerCase(), "")*/);
		//s.add(seedBox);
		seedBox.a(72);
		if (wmll.getWorldSeed() != 0)
			seedBox.a(Long.toString(wmll.getWorldSeed()));
		seedBox.b(true);
		s.add(new adi(0, q / 2 - 100, r / 4 + 90 + o, "Save Seed"));
	}
	
	protected void a(adi b) {
		if (b.f == 9001) {
			p.a(new WMLLGuiSMPSeed(wmll, parent));
		}
		else if (b.f == 0) {
			if (!seedBox.b().equals("")) {
				System.out.println(true+ " | "+seedBox.b());
				WMLL.options.put("Seed:"+wmll.getWorldName().toLowerCase(), seedBox.b());
			}
			p.a(parent);
		}
	}
	
	public void a() {
		seedBox.a();
	}
	
	protected void a(char c, int i) {
		if (Keyboard.KEY_ESCAPE == i)
			p.a(parent);
		else if (seedBox.j())
			seedBox.a(c, i);
		else
			super.a(c, i);
	}
	
	protected void a(int i, int j, int k) {
		super.a(i, j, k);
		seedBox.a(i, j, k);
	}
	
	public void a(int i, int j, float f) {
		r_();
		// (fontrenderer, text, x, y, colour)
		a(u, title, q / 2, 20, 0xffffff);
		a(u, "If you know the seed for this server, you can enter it here.", q / 2, 80, 0xffffff);
		a(u, "This will enable both the seed display and allow Slime chunk detection.", q / 2, 90, 0xffffff);	
		seedBox.f();
		WMLLOptions.renderWMLLVersion();
		super.a(i, j, f);
	}
	
	public void e() {
		Keyboard.enableRepeatEvents(false);
	}

}
