import org.lwjgl.input.Keyboard;


public class WMLLGuiSMPSeed extends vy {
	
	protected WMLL wmll;
	private vy parent;
	private String title;
	
	private ahh seedBox;

	public WMLLGuiSMPSeed(WMLL wmll, vy parent) {
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
			s.add(new aca(9001, 2, r - 22, 50, 20, "Reload"));
		/*
		 * New Editbox:
		 * new ahh(Gui, fontrenderer, posx, posy, width, height, default text);
		 */
		seedBox = new ahh(/*this,*/ u, q / 2 - ((wmll.getWindowSize().a() - 20) / 2), 110, wmll.getWindowSize().a() - 20, 20/*, WMLL.options.getProperty("Seed:"+wmll.getWorldName().toLowerCase(), "")*/);
		//s.add(seedBox);
		seedBox.a(72);
		seedBox.a(Long.toString(wmll.getWorldSeed()));
		seedBox.b(true);
		s.add(new aca(0, q / 2 - 100, r / 4 + 90 + o, "Save Seed"));
	}
	
	protected void a(aca b) {
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
		k();
		// (fontrenderer, text, x, y, colour)
		a(u, title, q / 2, 20, 0xffffff);
		a(u, "If you know the seed for this server, you can enter it here.", q / 2, 80, 0xffffff);
		a(u, "This will enable both the seed display and allow Slime chunk detection.", q / 2, 90, 0xffffff);	
		seedBox.f();
		super.a(i, j, f);
	}
	
	public void e() {
		Keyboard.enableRepeatEvents(false);
	}

}
