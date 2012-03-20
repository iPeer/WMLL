import org.lwjgl.input.Keyboard;


public class WMLLGuiSMPSeed extends vl {
	
	protected WMLL wmll;
	private vl parent;
	private String title;
	
	private ago seedBox;

	public WMLLGuiSMPSeed(WMLL wmll, vl parent) {
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
			s.add(new abk(9001, 2, r - 22, 50, 20, "Reload"));
		/*
		 * New Editbox:
		 * new ago(Gui, fontrenderer, posx, posy, width, height, default text);
		 */
		seedBox = new ago(this, u, q / 2 - ((wmll.getWindowSize().a() - 20) / 2), 110, wmll.getWindowSize().a() - 20, 20, WMLL.options.getProperty("Seed:"+wmll.getWorldName().toLowerCase(), ""));
		//s.add(seedBox);
		seedBox.a = true;
		s.add(new abk(0, q / 2 - 100, r / 4 + 90 + o, "Save Seed"));
	}
	
	protected void a(abk b) {
		if (b.f == 9001) {
			p.a(new WMLLGuiSMPSeed(wmll, parent));
		}
		else if (b.f == 0) {
			if (!seedBox.a().equals("")) {
				System.out.println(true+ " | "+seedBox.a());
				WMLL.options.put("Seed:"+wmll.getWorldName().toLowerCase(), seedBox.a());
			}
			p.a(parent);
		}
	}
	
	public void a() {
		seedBox.b();
	}
	
	protected void a(char c, int i) {
		if (Keyboard.KEY_ESCAPE == i)
			p.a(parent);
		else if (seedBox.a)
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
		
		seedBox.c();
		super.a(i, j, f);
	}
	
	public void e() {
		Keyboard.enableRepeatEvents(false);
	}

}
