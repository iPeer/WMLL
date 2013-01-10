
public class WMLLError extends avm {

	private Throwable e;

	public WMLLError(avm aum, Throwable e) {
		this.e = e;
	}

	@SuppressWarnings("unchecked")
	public void z_() {
		i.clear();
		i.add(new auc(0, g / 2 - 190, h - 30, 380, 20, "Okay"));
	}

	public void a(auc b) {
		switch (b.f) {
		case 0:
			f.a((avm)null);
			return;
		}
	}

	public void a(int i, int j, float f) {
		e();
		String[] a = this.e.getClass().toString().split("\\.");
		a(l, "Oh dear! It looks like something went wrong while WMLL was trying to open", g / 2, 20, 0xffffff);
		a(l, "one of its GUIs because an exception called \247o"+a[a.length - 1]+"\247r occurred.", g / 2, 32, 0xffffff);
		if (WMLL.i.Rei || WMLL.i.ZansMinimap || WMLL.i.AlienRadar) {
			a(l, "You have the following mods installed that are known to cause this issue:", g / 2, 56, 0xffffff);
			int y = 68;
			if (WMLL.i.Rei)
				a(l, "Rei's Minimap", g / 2, y+=12, 0xbbbbbb);
			if (WMLL.i.ZansMinimap)
				a(l, "Zan's Minimap", g/  2, y+=12, 0xbbbbbb);
			if (WMLL.i.AlienRadar)
				a(l, "Alien Motion Detector Minimap", g / 2, y+=12, 0xbbbbbb);
			a(l, "Before reporting this as a bug, please try reinstalling the conflicting mod(s)", g / 2, y+=24, 0xffffff);
			a(l, "and WMLL, taking care to install WMLL \247llast\247r.", g / 2, y+=12, 0xffffff);
			a(l, "If that \247ldoesn't\247r work, please report this as a bug!", g / 2, y+=24, 0xffffff);
			a(l, "If that \247ldoes\247r work, remember to always install WMLL \247oAFTER\247r the mod(s) listed above.", g / 2, y+=12, 0xffffff);
		}
		else {
			a(l, "You don't appear to have any mods installed that are known to cause this issue.", g / 2, 56, 0xffffff);
			a(l, "Please report this as a bug ASAP!", g / 2, 68, 0xffffff);
		}
		super.a(i, j, f);
	}

}
