
import org.lwjgl.input.Keyboard;


public class WMLLGuiSMPSeed extends aue {
	
	protected WMLL wmll;
	private aue parent;
	private String title;
	
	private atg seedBox;

	public WMLLGuiSMPSeed(WMLL wmll, aue parent) {
		this.wmll = wmll;
		this.parent = parent;
		this.title = "WMLL SMP Seed Configuration";
	}
	@SuppressWarnings("unchecked")
	public void A_() {
		Keyboard.enableRepeatEvents(true);
		i.clear();
		byte o = -16;
		if (wmll.debugClassPresent)
			i.add(new ast(9001, g - 52, h - 22, 50, 20, "Reload"));
		/*
		 * New Editbox:
		 * new aoj(Gui, fontrenderer, posx, posy, width, height, default text);
		 */
		seedBox = new atg(/*this,*/ l, g / 2 - ((wmll.getWindowSize().a() - 20) / 2), 110, wmll.getWindowSize().a() - 20, 20/*, WMLL.options.getProperty("Seed:"+wmll.getWorldName().toLowerCase(), "")*/);
		//h.add(seedBox);
		seedBox.a(72);
		if (wmll.getWorldSeed() != 0)
			seedBox.a(Long.toString(wmll.getWorldSeed()));
		seedBox.b(true);
		i.add(new ast(0, g / 2 - 100, h / 4 + 90 + o, "Save Seed"));
	}
	
	protected void a(ast b) {
		if (b.f == 9001) {
			f.a(new WMLLGuiSMPSeed(wmll, parent));
		}
		else if (b.f == 0) {
			if (!seedBox.b().equals("")) {
				//System.out.println(true+ " | "+seedBox.b());
				WMLL.options.put("Seed:"+wmll.getWorldName().toLowerCase(), seedBox.b());
			}
			f.a(parent);
		}
	}
	
//	public void a() {
//		seedBox.a();
//	}
	
	protected void a(char c, int i) {
		if (seedBox.l()) {
			seedBox.a(c, i);
		}
		else {
			super.a(c, i);
		}

	}
	
//	protected void a(int i, int j, int k) {
//		super.a(i, j, k);
//		seedBox.a(i, j, k);
//	}
	
	public void a(int i, int j, float f) {
		z_();
		// (fontrenderer, text, x, y, colour)
		a(l, title, this.g / 2, 20, 0xffffff);
		a(l, "If you know the seed for this server, you can enter it here.", this.g / 2, 80, 0xffffff);
		a(l, "This will enable both the seed display and allow Slime chunk detection.", this.g / 2, 90, 0xffffff);	
		seedBox.f();
		WMLLOptions.renderWMLLVersion();
		super.a(i, j, f);
	}
	
//	public void e() {
//		Keyboard.enableRepeatEvents(false);
//	}

}
