// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;

public class avv extends awb
{

    private int a;
    private int b;

    public avv()
    {
    }

    public void A_()
    {
        a = 0;
        i.clear();
        byte byte0 = -16;
        byte byte1 = 98;
        i.add(new auq(1, g / 2 - 100, h / 4 + 120 + byte0, bjy.a("menu.returnToMenu")));
        if(!f.A())
        {
            ((auq)i.get(0)).f = bjy.a("menu.disconnect");
        }
        i.add(new auq(4, g / 2 - 100, h / 4 + 24 + byte0, bjy.a("menu.returnToGame")));
        i.add(new auq(0, g / 2 - 100, h / 4 + 96 + byte0, 98, 20, bjy.a("menu.options")));
        auq auq1;
        i.add(auq1 = new auq(7, g / 2 + 2, h / 4 + 96 + byte0, 98, 20, bjy.a("menu.shareToLan")));
        i.add(new auq(5, g / 2 - 100, h / 4 + 48 + byte0, 98, 20, bjy.a("gui.achievements")));
        i.add(new auq(6, g / 2 + 2, h / 4 + 48 + byte0, 98, 20, bjy.a("gui.stats")));
		if (!WMLL.i.useML) { 
        	i.add(new auq(9000, g / 2 - 100, h / 4 + 144 + byte0, "WMLL Options..."));
			if (WMLL.i.debugClassPresent)
				i.add(new auq(9001, g - 52, h - 22, 50, 20, "Reload"));
		}
        auq1.h = f.B() && !f.C().c();
    }

    protected void a(auq auq1)
    {
        switch(auq1.g)
        {
		
		case 9000:
        	f.a(new WMLLOptionsMenu(this));
        	break;
        case 9001:
        	f.a(new avv());
        	break;
		
        case 0: // '\0'
            f.a(new avt(this, f.u));
            break;

        case 1: // '\001'
            auq1.h = false;
            f.y.a(kz.j, 1);
            f.f.F();
            f.a((bda)null);
            f.a(new blq());
            break;

        case 4: // '\004'
            f.a((awb)null);
            f.g();
            f.v.f();
            break;

        case 5: // '\005'
            f.a(new awn(f.y));
            break;

        case 6: // '\006'
            f.a(new awo(this, f.y));
            break;

        case 7: // '\007'
            f.a(new awg(this));
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
