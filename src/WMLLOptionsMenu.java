import java.awt.Desktop;
import java.net.URI;

import net.minecraftforge.common.ForgeVersion;

import org.lwjgl.input.Keyboard;


public class WMLLOptionsMenu extends axr {

	private WMLL wmll;
	private axr parent;
	private static final String threadURL = "http://www.minecraftforum.net/topic/170739-";
	private Desktop desktop;

	public WMLLOptionsMenu() {	}

	public WMLLOptionsMenu(WMLL wmll) { 
		this.wmll = wmll;
	}

	public WMLLOptionsMenu(WMLL wmll, axr aum) {
		this.wmll = wmll;
		this.parent = aum;
	}

	public WMLLOptionsMenu(axr aum) {
		this.parent = aum;
		if (!WMLL.i.realInit) { 
			System.err.println("[WMLL] [FATAL] WMLL was not initialized!");
		}
		this.wmll = WMLL.i;
	}

	@SuppressWarnings("unchecked")
	public void A_() {
		k.clear();
		try {
			if (wmll.isEnabled()) {
				k.add(new awg(0, h / 2 - 200, i - 95, "General Settings"));
				k.add(new awg(1, h / 2 + 2, i - 95, "Output Settings"));
				k.add(new awg(2, h / 2 - 200, i - 70, "Miscellaneous Settings"));
				k.add(new awg(3, h / 2 + 2, i - 70, "Compatibility Settings"));
				awg updateButton;
				k.add(updateButton = new awg(-1, h / 2 - 100, i / 4 + 23, "View forum thread"));
				updateButton.h = wmll.updateInfo.length > 0 && Desktop.isDesktopSupported() && (this.desktop = Desktop.getDesktop()).isSupported(Desktop.Action.BROWSE);
			}
			else {
				k.add(new awg(999, h / 2 - 50, i - 110, 100, 20, "Enable it!"));
			}
			k.add(new awg(4, h / 2 - 190, i - 30, 380, 20, (wmll.isEnabled() ? "Done" : "Cancel")));
			if (wmll.debugClassPresent) {
				k.add(new awg(9000, h - 40, 0, 20, 20, (WMLL.debugActive ? "\247l" : "")+"D"));
				k.add(new awg(9001, h - 20, 0, 20, 20, "R"));
			}
		}
		catch (Exception e) {
			g.a(new WMLLError(this, e));
		}
	}

	protected void a(awg b) {
		switch (b.f) {
		case 0:
			g.a(new WMLLOptionsGeneral(this.wmll, this));
			return;
		case 1:
			g.a(new WMLLOptionsOutput(this.wmll, this));
			return;
		case 2:
			g.a (new WMLLOptionsMisc(this.wmll, this));
			return;
		case 3:
			g.a(new WMLLOptionsCompat(this.wmll, this));
			return;
		case 4:
			if (parent != null)
				g.a(parent);
			else
				g.a((axr)null);
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
				g.a(new WMLLOptionsMenu(this.wmll, this.parent));
			else
				g.a(new WMLLOptionsMenu(this.wmll));
			return;
		case 9000:
			boolean a = !WMLL.debugActive;
			WMLL.debugActive = a;
			b.e = (WMLL.debugActive ? "\247l" : "")+"D";
			return;
		case 999:
			WMLL.options.put("World-"+wmll.getWorldName(), "true");
			g.a(new WMLLOptionsMenu(this.wmll, this.parent));
			return;
		}
	}

	protected void a(char c, int i) {
		if (i == Keyboard.KEY_ESCAPE) {
			wmll.saveOptions();
			g.a(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? null : parent);
		}

	}

	public void a(int i, int j, float f) {
		e();
		a(m, "What's My Light Level"+(WMLL.i.useML ? (WMLL.i.useForge ? " running on Forge "+ForgeVersion.getVersion() : " running via a ModLoader") : ""), h / 2, this.i / 4 - 20, 0xffffff);
		a(m, WMLL.wmllVersion()+(!WMLL.versionName().equals("") ? " (\""+WMLL.versionName()+"\" edition)" : "")+" for Minecraft "+WMLL.getMinecraftVersion(), h / 2, this.i / 4 - 10, 0x888888);
		if (wmll.showWorldName)
			a(m, wmll.getWorldName(), h / 2, this.i / 4, 0x888888);
		if (wmll.updateInfo.length > 0) {
			a(m, "\247c"+wmll.updateInfo[0]+"\247a is available for Minecraft \247c"+wmll.updateInfo[1], h / 2, this.i / 4 + 10, 0x00ff00);
		}
		if (!wmll.isEnabled())
			a(m, "WMLL is currently disabled on the "+(wmll.isMultiplayer() ? "server" : "world")+".", h / 2, this.i / 4 + 60, 0xffffff);
		if (!WMLL.i.realInit)
			a(m, "WMLL was not initialized poroperly. This can happen if you load it along-side mods that also modify", h / 2, this.i / 4 + 60, 0xffffff);
		super.a(i, j, f);
	}

}
