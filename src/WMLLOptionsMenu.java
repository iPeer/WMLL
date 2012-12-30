import java.awt.Desktop;
import java.net.URI;

import org.lwjgl.input.Keyboard;


public class WMLLOptionsMenu extends aul {

	private WMLL wmll;
	private aul parent;
	private static final String threadURL = "http://www.minecraftforum.net/topic/170739-";
	private Desktop desktop;

	public WMLLOptionsMenu() { }

	public WMLLOptionsMenu(WMLL wmll) { 
		this.wmll = wmll;
	}

	public WMLLOptionsMenu(WMLL wmll, aul aum) {
		this.wmll = wmll;
		this.parent = aum;
	}

	public WMLLOptionsMenu(aul aum) {
		this.parent = aum;
		this.wmll = WMLL.i;
	}

	@SuppressWarnings("unchecked")
	public void A_() {
		i.clear();
		try {
			if (wmll.isEnabled()) {
				i.add(new atb(0, g / 2 - 200, h - 95, "General Settings"));
				i.add(new atb(1, g / 2 + 2, h - 95, "Output Settings"));
				i.add(new atb(2, g / 2 - 200, h - 70, "Miscellaneous Settings"));
				i.add(new atb(3, g / 2 + 2, h - 70, "Compatibility Settings"));
				atb updateButton;
				i.add(updateButton = new atb(-1, g / 2 - 100, h / 4 + 23, "View forum thread"));
				updateButton.h = wmll.updateInfo.length > 0 && Desktop.isDesktopSupported() && (this.desktop = Desktop.getDesktop()).isSupported(Desktop.Action.BROWSE);
			}
			else {
				i.add(new atb(999, g / 2 - 50, h - 110, 100, 20, "Enable it!"));
			}
			i.add(new atb(4, g / 2 - 190, h - 30, 380, 20, (wmll.isEnabled() ? "Done" : "Cancel")));
			if (wmll.debugClassPresent) {
				i.add(new atb(9000, g - 40, 0, 20, 20, (WMLL.debugActive ? "\247l" : "")+"D"));
				i.add(new atb(9001, g - 20, 0, 20, 20, "R"));
			}
		}
		catch (Exception e) {
			f.a(new WMLLError(this, e));
		}
	}

	protected void a(atb b) {
		switch (b.f) {
		case 0:
			f.a(new WMLLOptionsGeneral(this.wmll, this));
			return;
		case 1:
			f.a(new WMLLOptionsOutput(this.wmll, this));
			return;
		case 2:
			f.a (new WMLLOptionsMisc(this.wmll, this));
			return;
		case 3:
			f.a(new WMLLOptionsCompat(this.wmll, this));
			return;
		case 4:
			if (parent != null)
				f.a(parent);
			else
				f.a((aul)null);
			wmll.saveOptions();
			return;
		case -1:
			try {
				this.desktop.browse(new URI(threadURL));
			}
			catch (Exception e) { b.h = false; }
			return;
		case 9001:
			if (this.parent != null)
				f.a(new WMLLOptionsMenu(this.wmll, this.parent));
			else
				f.a(new WMLLOptionsMenu(this.wmll));
			return;
		case 9000:
			boolean a = !WMLL.debugActive;
			WMLL.debugActive = a;
			b.e = (WMLL.debugActive ? "\247l" : "")+"D";
			return;
		case 999:
			WMLL.options.put("World-"+wmll.getWorldName(), "true");
			f.a(new WMLLOptionsMenu(this.wmll, this.parent));
			return;
		}
	}

	protected void a(char c, int i) {
		if (i == Keyboard.KEY_ESCAPE) {
			wmll.saveOptions();
			f.a(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? null : parent);
		}

	}

	public void a(int i, int j, float f) {
		e();
		a(l, "What's My Light Level", g / 2, h / 4 - 20, 0xffffff);
		a(l, WMLL.wmllVersion()+" for "+WMLL.getMinecraftVersion(), g / 2, h / 4 - 10, 0x888888);
		if (wmll.showWorldName)
			a(l, wmll.getWorldName(), g / 2, h / 4, 0x888888);
		if (wmll.updateInfo.length > 0) {
			a(l, "\247c"+wmll.updateInfo[0]+"\247a is available for Minecraft \247c"+wmll.updateInfo[1], g / 2, h / 4 + 10, 0x00ff00);
		}
		if (!wmll.isEnabled())
			a(l, "WMLL is currently disabled on the "+(wmll.isMultiplayer() ? "server" : "world")+".", g / 2, h / 4 + 60, 0xffffff);
		super.a(i, j, f);
	}

}
