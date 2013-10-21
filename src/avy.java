// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv
// Source File Name:   SourceFile

import java.util.List;

public class avy extends awe
{
    
    private int a;
    private int b;
    
    public avy()
    {
    }
    
    public void A_()
    {
	a = 0;
	i.clear();
	byte byte0 = -16;
	byte byte1 = 98;
	i.add(new aut(1, g / 2 - 100, h / 4 + 120 + byte0, bkb.a("menu.returnToMenu")));
	if(!f.A())
	{
	    ((aut)i.get(0)).f = bkb.a("menu.disconnect");
	}
	i.add(new aut(4, g / 2 - 100, h / 4 + 24 + byte0, bkb.a("menu.returnToGame")));
	i.add(new aut(0, g / 2 - 100, h / 4 + 96 + byte0, 98, 20, bkb.a("menu.options")));
	aut aut1;
	i.add(aut1 = new aut(7, g / 2 + 2, h / 4 + 96 + byte0, 98, 20, bkb.a("menu.shareToLan")));
	i.add(new aut(5, g / 2 - 100, h / 4 + 48 + byte0, 98, 20, bkb.a("gui.achievements")));
	i.add(new aut(6, g / 2 + 2, h / 4 + 48 + byte0, 98, 20, bkb.a("gui.stats")));
	if (!WMLL.i.useML) {
	    i.add(new aut(9000, g / 2 - 100, h / 4 + 144 + byte0, "WMLL Options..."));
	    if (WMLL.i.debugClassPresent)
		i.add(new aut(9001, g - 52, h - 22, 50, 20, "Reload"));
	}
	aut1.h = f.B() && !f.C().c();
    }
    
    protected void a(aut aut1)
    {
	switch(aut1.g)
	{
	    
	    case 9000:
		f.a(new WMLLOptionsMenu(this));
		break;
	    case 9001:
		f.a(new avy());
		break;
	    case 0: // '\0'
		f.a(new avw(this, f.u));
		break;
		
	    case 1: // '\001'
		aut1.h = false;
		f.y.a(la.j, 1);
		f.f.F();
		f.a((bdd)null);
		f.a(new blt());
		break;
		
	    case 4: // '\004'
		f.a((awe)null);
		f.g();
		f.v.f();
		break;
		
	    case 5: // '\005'
		f.a(new awq(f.y));
		break;
		
	    case 6: // '\006'
		f.a(new awr(this, f.y));
		break;
		
	    case 7: // '\007'
		f.a(new awj(this));
		break;
	}
    }
    
    public void c()
    {
	super.c();
	b++;
    }
    
    public void a(int i, int j, float f)
    {
	e();
	a(o, "Game menu", g / 2, 40, 0xffffff);
	super.a(i, j, f);
    }
}
