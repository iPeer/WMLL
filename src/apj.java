// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class apj extends apm
{

    private int a;
    private int b;

    public apj()
    {
        a = 0;
        b = 0;
    }

    public void w_()
    {
        a = 0;
        h.clear();
        byte byte0 = -16;
        h.add(new aog(1, f / 2 - 100, g / 4 + 120 + byte0, aj.a("menu.returnToMenu")));
        if(!e.A())
        {
            ((aog)h.get(0)).e = aj.a("menu.disconnect");
        }
        h.add(new aog(4, f / 2 - 100, g / 4 + 24 + byte0, aj.a("menu.returnToGame")));
        aog aog1;
        h.add(aog1 = new aog(7, f / 2 + 2, g / 4 + 96 + byte0, 98, 20, aj.a("menu.shareToLan")));
        h.add(new aog(5, f / 2 - 100, g / 4 + 48 + byte0, 98, 20, aj.a("gui.achievements")));
        h.add(new aog(6, f / 2 + 2, g / 4 + 48 + byte0, 98, 20, aj.a("gui.stats")));
		h.add(new aog(0, f / 2 - 100, g / 4 + 96 + byte0, 98, 20, aj.a("menu.options")));
		h.add(new aog(8, f / 2 - 100, g / 4 + 144 + byte0, "WMLL Options..."));
		if (WMLL.debugClassPresent)
			h.add(new aog(9001, f - 52, g - 22, 50, 20, "Reload"));
        aog1.g = e.B() && !e.C().c();
    }

    protected void a(aog aog1)
    {
        switch(aog1.f)
        {
        case 0: // '\0'
            e.a(new aph(this, e.y));
            break;

        case 1: // '\001'
            aog1.g = false;
            e.E.a(ht.j, 1);
            e.e.A();
            e.a((atc)null);
            e.a(new ayr());
            break;

        case 4: // '\004'
            e.a((apm)null);
            e.h();
            break;

        case 5: // '\005'
            e.a(new apy(e.E));
            break;

        case 6: // '\006'
            e.a(new apz(this, e.E));
            break;

        case 7: // '\007'
            e.a(new apr(this));
            break;
            
        case 8:
        	e.a(new WMLLOptions(this));
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
        v_();
        a(k, "Game menu", this.f / 2, 40, 0xffffff);
        super.a(i, j, f);
    }
}
