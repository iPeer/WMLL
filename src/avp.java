// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;

public class avp extends avv
{

    private int a;
    private int b;

    public avp()
    {
    }

    public void A_()
    {
        a = 0;
        i.clear();
        byte byte0 = -16;
        byte byte1 = 98;
        i.add(new auk(1, g / 2 - 100, h / 4 + 120 + byte0, bjq.a("menu.returnToMenu")));
        if(!f.A())
        {
            ((auk)i.get(0)).f = bjq.a("menu.disconnect");
        }
        i.add(new auk(4, g / 2 - 100, h / 4 + 24 + byte0, bjq.a("menu.returnToGame")));
        i.add(new auk(0, g / 2 - 100, h / 4 + 96 + byte0, 98, 20, bjq.a("menu.options")));
        auk auk1;
        i.add(auk1 = new auk(7, g / 2 + 2, h / 4 + 96 + byte0, 98, 20, bjq.a("menu.shareToLan")));
        i.add(new auk(5, g / 2 - 100, h / 4 + 48 + byte0, 98, 20, bjq.a("gui.achievements")));
        i.add(new auk(6, g / 2 + 2, h / 4 + 48 + byte0, 98, 20, bjq.a("gui.stats")));
		if (!WMLL.i.useML) { 
        	i.add(new auk(9000, g / 2 - 100, h / 4 + 144 + byte0, "WMLL Options..."));
			if (WMLL.i.debugClassPresent)
				i.add(new auk(9001, g - 52, h - 22, 50, 20, "Reload"));
        }
        auk1.h = f.B() && !f.C().c();
    }

    protected void a(auk auk1)
    {
        switch(auk1.g)
        {
		
		case 9000:
        	f.a(new WMLLOptionsMenu(this));
        	break;
        case 9001:
        	f.a(new avo());
        	break;
		
        case 0: // '\0'
            f.a(new avn(this, f.t));
            break;

        case 1: // '\001'
            auk1.h = false;
            f.x.a(kx.j, 1);
            f.e.F();
            f.a((bcu)null);
            f.a(new bli());
            break;

        case 4: // '\004'
            f.a((avv)null);
            f.g();
            f.u.f();
            break;

        case 5: // '\005'
            f.a(new awh(f.x));
            break;

        case 6: // '\006'
            f.a(new awi(this, f.x));
            break;

        case 7: // '\007'
            f.a(new awa(this));
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
