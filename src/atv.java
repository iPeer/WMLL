// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class atv extends aub
{

    private int a;
    private int b;

    public atv()
    {
        a = 0;
        b = 0;
    }

    @SuppressWarnings("unchecked")
	public void A_()
    {
        a = 0;
        h.clear();
        byte byte0 = -16;
        h.add(new asq(1, f / 2 - 100, g / 4 + 120 + byte0, bl.a("menu.returnToMenu")));
        if(!e.A())
        {
            ((asq)h.get(0)).e = bl.a("menu.disconnect");
        }
        h.add(new asq(4, f / 2 - 100, g / 4 + 24 + byte0, bl.a("menu.returnToGame")));
        h.add(new asq(0, f / 2 - 100, g / 4 + 96 + byte0, 98, 20, bl.a("menu.options")));
        asq asq1;
        h.add(asq1 = new asq(7, f / 2 + 2, g / 4 + 96 + byte0, 98, 20, bl.a("menu.shareToLan")));
        h.add(new asq(5, f / 2 - 100, g / 4 + 48 + byte0, 98, 20, bl.a("gui.achievements")));
        h.add(new asq(6, f / 2 + 2, g / 4 + 48 + byte0, 98, 20, bl.a("gui.stats")));
		h.add(new asq(8, f / 2 - 100, g / 4 + 144 + byte0, "WMLL Options..."));
		if (WMLL.i.debugClassPresent)
			h.add(new asq(9001, f - 52, g - 22, 50, 20, "Reload"));
        asq1.g = e.B() && !e.C().al();
    }

    protected void a(asq asq1)
    {
        switch(asq1.f)
        {
        case 8:
        	e.a(new WMLLOptions(this));
        	break;
        case 9001:
        	e.a(new atv());
        	break;
        	
        case 0: // '\0'
            e.a(new att(this, e.y));
            break;

        case 1: // '\001'
            asq1.g = false;
            e.E.a(jp.j, 1);
            e.e.C();
            e.a((aye)null);
            e.a(new bef());
            break;

        case 4: // '\004'
            e.a((aub)null);
            e.h();
            e.A.f();
            break;

        case 5: // '\005'
            e.a(new aun(e.E));
            break;

        case 6: // '\006'
            e.a(new auo(this, e.E));
            break;

        case 7: // '\007'
            e.a(new aug(this));
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
