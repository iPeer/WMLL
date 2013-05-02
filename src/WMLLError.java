
public class WMLLError extends axr {

	private Throwable e;

	public WMLLError(axr aum, Throwable e) {
		this.e = e;
	}

	@SuppressWarnings("unchecked")
	public void A_() {
		k.clear();
		k.add(new awg(0, h / 2 - 190, h - 30, 380, 20, "Okay"));
	}

	public void a(awg b) {
		switch (b.f) {
		case 0:
			g.a((axl)null);
			return;
		}
	}

	public void a(int i, int j, float f) {
		e();
		String[] a = this.e.getClass().toString().split("\\.");
		a(m, "Oh dear! It looks like something went wrong while WMLL was trying to open", h / 2, 20, 0xffffff);
		a(m, "one of its GUIs because an exception called \247o"+a[a.length - 1]+"\247r occurred.", h / 2, 32, 0xffffff);
		if (WMLL.i.Rei || WMLL.i.ZansMinimap || WMLL.i.AlienRadar) {
			a(m, "You have the following mods installed that are known to cause this issue:", h / 2, 56, 0xffffff);
			int y = 68;
			if (WMLL.i.Rei)
				a(m, "Rei's Minimap", h / 2, y+=12, 0xbbbbbb);
			if (WMLL.i.ZansMinimap)
				a(m, "Zan's Minimap", h / 2, y+=12, 0xbbbbbb);
			if (WMLL.i.AlienRadar)
				a(m, "Alien Motion Detector Minimap", h / 2, y+=12, 0xbbbbbb);
			a(m, "Before reporting this as a bug, please try reinstalling the conflicting mod(s)", h / 2, y+=24, 0xffffff);
			a(m, "and WMLL, taking care to install WMLL \247llast\247r.", h / 2, y+=12, 0xffffff);
			a(m, "If that \247ldoesn't\247r work, please report this as a bug!", h / 2, y+=24, 0xffffff);
			a(m, "If that \247ldoes\247r work, remember to always install WMLL \247oAFTER\247r the mod(s) listed above.", h / 2, y+=12, 0xffffff);
		}
		else {
			a(m, "You don't appear to have any mods installed that are known to cause this issue.", h / 2, 56, 0xffffff);
			a(m, "Please report this as a bug ASAP!", h / 2, 68, 0xffffff);
		}
		super.a(i, j, f);
	}

}
