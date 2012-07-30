import org.lwjgl.input.Keyboard;


public class WMLLYesNo extends apm {

	protected WMLL wmll;
	protected apm parent;
	byte offset = -16;
	
	public WMLLYesNo(WMLL wmll, apm parent) {
		this.parent = parent;
		this.wmll = wmll;
	}
	
	@SuppressWarnings("unchecked")
	public void w_() {
		h.clear();
		h.add(new aog(0, f / 2 - 100, g / 2 + 10 + offset, 98, 20, "Yes"));
		h.add(new aog(1, f / 2 + 2, g / 2 + 10 + offset, 98, 20, "No"));
		if (WMLL.debugClassPresent)
			h.add(new aog(9001, f - 52, g - 22, 50, 20, "Reload"));
	}
	
	protected void a(aog b) {
		if (b.f == 9001)
			e.a(new WMLLYesNo(wmll, parent));
		if (b.f == 0 || b.f == 1) {
			if (b.f == 0) {
				WMLL.options = WMLL.outputOptions = null;
				WMLL.settingsFile.delete();
				WMLL.outputOptionsFile.delete();
				wmll.loadOptions();
			}
			try {
				e.a(parent.getClass().newInstance());
			} catch (Exception e1) {
				e1.printStackTrace();
				e.a((apm)null);
			}
		}
	}
	
	protected void a(char c1, int i1) {
		if (i1 == Keyboard.KEY_ESCAPE && parent != null) {
			e.a(parent);
		}
		else
			super.a(c1, i1);
	}
	
	public void a(int i, int j, float f) {
		v_();
		a(k, "\247cAre you sure you want to reset ALL settings to their defaults?", this.f / 2, g / 2 - 20, 0xffffff);
		WMLLOptions.renderWMLLVersion();
		super.a(i, j, f);
	}
	
	
	
}
