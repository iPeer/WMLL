// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class axf extends axl
{

    private int a;
    private int b;

    public axf()
    {
        a = 0;
        b = 0;
    }

    public void A_()
    {
        a = 0;
        i.clear();
        byte byte0 = -16;
        i.add(new awa(1, g / 2 - 100, h / 4 + 120 + byte0, bo.a("menu.returnToMenu")));
        if(!f.B())
        {
            ((awa)i.get(0)).e = bo.a("menu.disconnect");
        }
        i.add(new awa(4, g / 2 - 100, h / 4 + 24 + byte0, bo.a("menu.returnToGame")));
        i.add(new awa(0, g / 2 - 100, h / 4 + 96 + byte0, 98, 20, bo.a("menu.options")));
        awa awa1;
        i.add(awa1 = new awa(7, g / 2 + 2, h / 4 + 96 + byte0, 98, 20, bo.a("menu.shareToLan")));
        i.add(new awa(5, g / 2 - 100, h / 4 + 48 + byte0, 98, 20, bo.a("gui.achievements")));
        i.add(new awa(6, g / 2 + 2, h / 4 + 48 + byte0, 98, 20, bo.a("gui.stats")));
		i.add(new awa(8, g / 2 - 100, h / 4 + 144 + byte0, "WMLL Options..."));
		if (WMLL.i.debugClassPresent)
			i.add(new awa(9001, g - 52, h - 22, 50, 20, "Reload"));
		awa1.g = f.C() && !f.D().am();
    }

    protected void a(awa awa1)
    {
        switch(awa1.f)
        {
		case 8:
        	f.a(new WMLLOptionsMenu(this));
        	break;
        case 9001:
        	f.a(new axf());
        	break;
        	
        case 0: // '\0'
            f.a(new axd(this, f.z));
            break;

        case 1: // '\001'
            awa1.g = false;
            f.F.a(kf.j, 1);
            f.e.D();
            f.a((bdm)null);
            f.a(new bjz());
            break;

        case 4: // '\004'
            f.a((axl)null);
            f.h();
            f.B.f();
            break;

        case 5: // '\005'
            f.a(new axx(f.F));
            break;

        case 6: // '\006'
            f.a(new axy(this, f.F));
            break;

        case 7: // '\007'
            f.a(new axq(this));
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
        a(l, "Game menu", g / 2, 40, 0xffffff);
        super.a(i, j, f);
    }
}
