// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class apk extends apn
{

    private int a;
    private int b;

    public apk()
    {
        a = 0;
        b = 0;
    }

    public void w_()
    {
        a = 0;
        h.clear();
        byte byte0 = -16;
        h.add(new aoh(1, f / 2 - 100, g / 4 + 120 + byte0, aj.a("menu.returnToMenu")));
        if(!e.A())
        {
            ((aoh)h.get(0)).e = aj.a("menu.disconnect");
        }
        h.add(new aoh(4, f / 2 - 100, g / 4 + 24 + byte0, aj.a("menu.returnToGame")));
        h.add(new aoh(0, f / 2 - 100, g / 4 + 96 + byte0, 98, 20, aj.a("menu.options")));
        aoh aoh1;
        h.add(aoh1 = new aoh(7, f / 2 + 2, g / 4 + 96 + byte0, 98, 20, aj.a("menu.shareToLan")));
        h.add(new aoh(5, f / 2 - 100, g / 4 + 48 + byte0, 98, 20, aj.a("gui.achievements")));
        h.add(new aoh(6, f / 2 + 2, g / 4 + 48 + byte0, 98, 20, aj.a("gui.stats")));
		h.add(new aoh(8, f / 2 - 100, g / 4 + 144 + byte0, "WMLL Options..."));
		if (WMLL.i.debugClassPresent)
			h.add(new aoh(9001, f - 52, g - 22, 50, 20, "Reload"));
        aoh1.g = e.B() && !e.C().c();
    }

    protected void a(aoh aoh1)
    {
        switch(aoh1.f)
        {
        
        case 8:
        	e.a(new WMLLOptions(this));
        	break;
        
        case 0: // '\0'
            e.a(new api(this, e.y));
            break;

        case 1: // '\001'
            aoh1.g = false;
            e.E.a(hu.j, 1);
            e.e.A();
            e.a((atd)null);
            e.a(new ays());
            break;

        case 4: // '\004'
            e.a((apn)null);
            e.h();
            break;

        case 5: // '\005'
            e.a(new apz(e.E));
            break;

        case 6: // '\006'
            e.a(new aqa(this, e.E));
            break;

        case 7: // '\007'
            e.a(new aps(this));
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
