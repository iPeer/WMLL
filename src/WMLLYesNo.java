
import org.lwjgl.input.Keyboard;


public class WMLLYesNo extends aue {

	protected WMLL wmll;
	protected aue parent;
	byte offset = -16;
	
	public WMLLYesNo(WMLL wmll, aue parent) {
		this.parent = parent;
		this.wmll = wmll;
	}
	
	@SuppressWarnings("unchecked")
	public void A_() {
		i.clear();
		i.add(new ast(0, g / 2 - 100, g / 2 + 10 + offset, 98, 20, "Yes"));
		i.add(new ast(1, g / 2 + 2, g / 2 + 10 + offset, 98, 20, "No"));
		if (wmll.debugClassPresent)
			i.add(new ast(9001, g - 52, g - 22, 50, 20, "Reload"));
	}
	
	protected void a(ast b) {
		if (b.f == 9001)
			f.a(new WMLLYesNo(wmll, parent));
		if (b.f == 0 || b.f == 1) {
			if (b.f == 0) {
				WMLL.options = WMLL.outputOptions = null;
				WMLL.settingsFile.delete();
				WMLL.outputOptionsFile.delete();
				wmll.loadOptions();
			}
			try {
				f.a(parent.getClass().newInstance());
			} catch (Exception e1) {
				e1.printStackTrace();
				f.a((asv)null);
			}
		}
	}
	
	protected void a(char c1, int i1) {
		if (i1 == Keyboard.KEY_ESCAPE && parent != null) {
			f.a(parent);
		}
		else
			super.a(c1, i1);
	}
	
	public void a(int i, int j, float f) {
		z_();
		a(l, "\247cAre you sure you want to reset ALL settings to their defaults?", this.g / 2, g / 2 - 20, 0xffffff);
		WMLLOptions.renderWMLLVersion();
		super.a(i, j, f);
	}
	
	
	
}
