// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class aus extends auy
{

    private int a;
    private int b;

    public aus()
    {
        a = 0;
        b = 0;
    }

    public void y_()
    {
        a = 0;
        i.clear();
        byte byte0 = -16;
        i.add(new ato(1, g / 2 - 100, h / 4 + 120 + byte0, bm.a("menu.returnToMenu")));
        if(!f.A())
        {
            ((ato)i.get(0)).e = bm.a("menu.disconnect");
        }
        i.add(new ato(4, g / 2 - 100, h / 4 + 24 + byte0, bm.a("menu.returnToGame")));
        i.add(new ato(0, g / 2 - 100, h / 4 + 96 + byte0, 98, 20, bm.a("menu.options")));
        ato ato1;
        i.add(ato1 = new ato(7, g / 2 + 2, h / 4 + 96 + byte0, 98, 20, bm.a("menu.shareToLan")));
        i.add(new ato(5, g / 2 - 100, h / 4 + 48 + byte0, 98, 20, bm.a("gui.achievements")));
        i.add(new ato(6, g / 2 + 2, h / 4 + 48 + byte0, 98, 20, bm.a("gui.stats")));
		i.add(new ato(8, g / 2 - 100, h / 4 + 144 + byte0, "WMLL Options..."));
		if (WMLL.i.debugClassPresent)
			i.add(new ato(9001, g - 52, h - 22, 50, 20, "Reload"));
		ato1.g = f.B() && !f.C().al();
    }

    protected void a(ato ato1)
    {
        switch(ato1.f)
        {
		case 8:
        	f.a(new WMLLOptionsMenu(this));
        	break;
        case 9001:
        	f.a(new aus());
        	break;
        	
        case 0: // '\0'
            f.a(new auq(this, f.y));
            break;

        case 1: // '\001'
            ato1.g = false;
            f.E.a(jq.j, 1);
            f.e.C();
            f.a((azd)null);
            f.a(new bfm());
            break;

        case 4: // '\004'
            f.a((auy)null);
            f.h();
            f.A.f();
            break;

        case 5: // '\005'
            f.a(new avk(f.E));
            break;

        case 6: // '\006'
            f.a(new avl(this, f.E));
            break;

        case 7: // '\007'
            f.a(new avd(this));
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
