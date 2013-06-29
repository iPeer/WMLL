// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;

public class avo extends avu
{

    private int a;
    private int b;

    public avo()
    {
    }

    public void A_()
    {
        a = 0;
        i.clear();
        byte byte0 = -16;
        byte byte1 = 98;
        i.add(new auj(1, g / 2 - 100, h / 4 + 120 + byte0, bjo.a("menu.returnToMenu")));
        if(!f.A())
        {
            ((auj)i.get(0)).f = bjo.a("menu.disconnect");
        }
        i.add(new auj(4, g / 2 - 100, h / 4 + 24 + byte0, bjo.a("menu.returnToGame")));
        i.add(new auj(0, g / 2 - 100, h / 4 + 96 + byte0, 98, 20, bjo.a("menu.options")));
        auj auj1;
        i.add(auj1 = new auj(7, g / 2 + 2, h / 4 + 96 + byte0, 98, 20, bjo.a("menu.shareToLan")));
        i.add(new auj(5, g / 2 - 100, h / 4 + 48 + byte0, 98, 20, bjo.a("gui.achievements")));
        i.add(new auj(6, g / 2 + 2, h / 4 + 48 + byte0, 98, 20, bjo.a("gui.stats")));
		if (!WMLL.i.useML) { 
        	i.add(new auj(9000, g / 2 - 100, h / 4 + 144 + byte0, "WMLL Options..."));
			if (WMLL.i.debugClassPresent)
				i.add(new auj(9001, g - 52, h - 22, 50, 20, "Reload"));
        }
        auj1.h = f.B() && !f.C().c();
    }

    protected void a(auj auj1)
    {
        switch(auj1.g)
        {
		        
		case 9000:
        	f.a(new WMLLOptionsMenu(this));
        	break;
        case 9001:
        	f.a(new avo());
        	break;
			
        case 0: // '\0'
            f.a(new avm(this, f.t));
            break;

        case 1: // '\001'
            auj1.h = false;
            f.x.a(kw.j, 1);
            f.e.F();
            f.a((bcr)null);
            f.a(new blg());
            break;

        case 4: // '\004'
            f.a((avu)null);
            f.g();
            f.u.f();
            break;

        case 5: // '\005'
            f.a(new awg(f.x));
            break;

        case 6: // '\006'
            f.a(new awh(this, f.x));
            break;

        case 7: // '\007'
            f.a(new avz(this));
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
