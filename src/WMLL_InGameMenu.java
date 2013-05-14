
public class WMLL_InGameMenu extends axl {
	
	public WMLL_InGameMenu() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public void A_() {
		super.A_();
		k.add(new awg(9000, h / 2 - 100, i / 4 + 144 + -16, "WMLL Options..."));
		if (WMLL.i.debugClassPresent)
			k.add(new awg(9001, h - 52, i - 22, 50, 20, "Reload"));
		
	}
	
	@Override
    protected void a(awg awg1)
    {
		switch (awg1.f) {
		case 9000:
        	g.a(new WMLLOptionsMenu(this));
        	break;
        case 9001:
        	g.a(new axl());
        	break;
		}
		super.a(awg1);
    }
}
