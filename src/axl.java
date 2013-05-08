// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class axl extends axr
{

    private int a;
    private int b;

    public axl()
    {
        a = 0;
        b = 0;
    }

    public void A_()
    {
        a = 0;
        k.clear();
        byte byte0 = -16;
        k.add(new awg(1, h / 2 - 100, i / 4 + 120 + byte0, bo.a("menu.returnToMenu")));
        if(!g.B())
        {
            ((awg)k.get(0)).e = bo.a("menu.disconnect");
        }
        k.add(new awg(4, h / 2 - 100, i / 4 + 24 + byte0, bo.a("menu.returnToGame")));
        k.add(new awg(0, h / 2 - 100, i / 4 + 96 + byte0, 98, 20, bo.a("menu.options")));
        awg awg1;
        k.add(awg1 = new awg(7, h / 2 + 2, i / 4 + 96 + byte0, 98, 20, bo.a("menu.shareToLan")));
        k.add(new awg(5, h / 2 - 100, i / 4 + 48 + byte0, 98, 20, bo.a("gui.achievements")));
        k.add(new awg(6, h / 2 + 2, i / 4 + 48 + byte0, 98, 20, bo.a("gui.stats")));
        if (!WMLL.i.useML) { 
        	k.add(new awg(9000, h / 2 - 100, i / 4 + 144 + byte0, "WMLL Options..."));
			if (WMLL.i.debugClassPresent)
				k.add(new awg(9001, h - 52, i - 22, 50, 20, "Reload"));
        }
        awg1.g = g.C() && !g.D().am();
    }

    protected void a(awg awg1)
    {
        switch(awg1.f)
        {     
        
		case 9000:
        	g.a(new WMLLOptionsMenu(this));
        	break;
        case 9001:
        	g.a(new axl());
        	break;
        	
        case 0: // '\0'
            g.a(new axj(this, g.z));
            break;

        case 1: // '\001'
            awg1.g = false;
            g.F.a(kf.j, 1);
			g.e.F();
            g.a((bds)null);
            g.a(new bkf());
            break;

        case 4: // '\004'
            g.a((axr)null);
            g.h();
            g.B.f();
            break;

        case 5: // '\005'
            g.a(new ayd(g.F));
            break;

        case 6: // '\006'
            g.a(new aye(this, g.F));
            break;

        case 7: // '\007'
            g.a(new axw(this));
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
        a(m, "Game menu", h / 2, 40, 0xffffff);
        super.a(i, j, f);
    }
}
