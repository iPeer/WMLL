// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class asq extends asw
{

    private int a;
    private int b;

    public asq()
    {
        a = 0;
        b = 0;
    }

    public void A_()
    {
        a = 0;
        h.clear();
        byte byte0 = -16;
        h.add(new arl(1, f / 2 - 100, g / 4 + 120 + byte0, bd.a("menu.returnToMenu")));
        if(!e.A())
        {
            ((arl)h.get(0)).e = bd.a("menu.disconnect");
        }
        h.add(new arl(4, f / 2 - 100, g / 4 + 24 + byte0, bd.a("menu.returnToGame")));
        h.add(new arl(0, f / 2 - 100, g / 4 + 96 + byte0, 98, 20, bd.a("menu.options")));
        arl arl1;
        h.add(arl1 = new arl(7, f / 2 + 2, g / 4 + 96 + byte0, 98, 20, bd.a("menu.shareToLan")));
        h.add(new arl(5, f / 2 - 100, g / 4 + 48 + byte0, 98, 20, bd.a("gui.achievements")));
        h.add(new arl(6, f / 2 + 2, g / 4 + 48 + byte0, 98, 20, bd.a("gui.stats")));
		h.add(new arl(8, f / 2 - 100, g / 4 + 144 + byte0, "WMLL Options..."));
		if (WMLL.i.debugClassPresent)
			h.add(new arl(9001, f - 52, g - 22, 50, 20, "Reload"));
        arl1.g = e.B() && !e.C().al();
    }

    protected void a(arl arl1)
    {
        switch(arl1.f)
        {
        case 8:
        	e.a(new WMLLOptions());
        	break;
        case 9001:
        	e.a(new asp());
        	break;
        case 0: // '\0'
            e.a(new aso(this, e.y));
            break;

        case 1: // '\001'
            arl1.g = false;
            e.E.a(jh.j, 1);
            e.e.B();
            e.a((awz)null);
            e.a(new bcz());
            break;

        case 4: // '\004'
            e.a((asv)null);
            e.h();
            e.A.f();
            break;

        case 5: // '\005'
            e.a(new ati(e.E));
            break;

        case 6: // '\006'
            e.a(new atj(this, e.E));
            break;

        case 7: // '\007'
            e.a(new atb(this));
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
        z_();
        a(k, "Game menu", this.f / 2, 40, 0xffffff);
        super.a(i, j, f);
    }
}
