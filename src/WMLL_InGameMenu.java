
public class WMLL_InGameMenu extends avp {
	
	public WMLL_InGameMenu() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public void A_() {
		super.A_();
		i.add(new auk(9000, g / 2 - 100, h / 4 + 144 + -16, "WMLL Options..."));
		if (WMLL.i.debugClassPresent)
			i.add(new auk(9001, g - 52, h - 22, 50, 20, "Reload"));
		
	}
	
	@Override
    protected void a(auk awg1)
    {
		switch (awg1.g) {
		case 9000:
        	f.a(new WMLLOptionsMenu(this));
        	break;
        case 9001:
        	f.a(new avo());
        	break;
		}
		super.a(awg1);
    }
}
