// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class ase extends ask
{

    private int a;
    private int b;

    public ase()
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
        h.add(new aqz(1, f / 2 - 100, g / 4 + 120 + byte0, bc.a("menu.returnToMenu")));
        if(!e.A())
        {
            ((aqz)h.get(0)).e = bc.a("menu.disconnect");
        }
        h.add(new aqz(4, f / 2 - 100, g / 4 + 24 + byte0, bc.a("menu.returnToGame")));
        h.add(new aqz(0, f / 2 - 100, g / 4 + 96 + byte0, 98, 20, bc.a("menu.options")));
        aqz aqz1;
        h.add(aqz1 = new aqz(7, f / 2 + 2, g / 4 + 96 + byte0, 98, 20, bc.a("menu.shareToLan")));
        h.add(new aqz(5, f / 2 - 100, g / 4 + 48 + byte0, 98, 20, bc.a("gui.achievements")));
        h.add(new aqz(6, f / 2 + 2, g / 4 + 48 + byte0, 98, 20, bc.a("gui.stats")));
		h.add(new aqz(8, f / 2 - 100, g / 4 + 144 + byte0, "WMLL Options..."));
		if (WMLL.i.debugClassPresent)
			h.add(new aqz(9001, f - 52, g - 22, 50, 20, "Reload"));
        aqz1.g = e.B() && !e.C().al();
    }

    protected void a(aqz aqz1)
    {
        switch(aqz1.f)
        {
        case 8:
        	e.a(new WMLLOptions());
        	break;
        case 9001:
        	e.a(new ase());
        	break;
        case 0: // '\0'
            e.a(new asc(this, e.y));
            break;

        case 1: // '\001'
            aqz1.g = false;
            e.E.a(jd.j, 1);
            e.e.B();
            e.a((awm)null);
            e.a(new bcm());
            break;

        case 4: // '\004'
            e.a((ask)null);
            e.h();
            e.A.f();
            break;

        case 5: // '\005'
            e.a(new asw(e.E));
            break;

        case 6: // '\006'
            e.a(new asx(this, e.E));
            break;

        case 7: // '\007'
            e.a(new asp(this));
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
