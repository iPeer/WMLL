// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class avg extends avm
{

    private int a;
    private int b;

    public avg()
    {
        a = 0;
        b = 0;
    }

    public void z_()
    {
        a = 0;
        i.clear();
        byte byte0 = -16;
        i.add(new auc(1, g / 2 - 100, h / 4 + 120 + byte0, bm.a("menu.returnToMenu")));
        if(!f.A())
        {
            ((auc)i.get(0)).e = bm.a("menu.disconnect");
        }
        i.add(new auc(4, g / 2 - 100, h / 4 + 24 + byte0, bm.a("menu.returnToGame")));
        i.add(new auc(0, g / 2 - 100, h / 4 + 96 + byte0, 98, 20, bm.a("menu.options")));
        auc auc1;
        i.add(auc1 = new auc(7, g / 2 + 2, h / 4 + 96 + byte0, 98, 20, bm.a("menu.shareToLan")));
        i.add(new auc(5, g / 2 - 100, h / 4 + 48 + byte0, 98, 20, bm.a("gui.achievements")));
        i.add(new auc(6, g / 2 + 2, h / 4 + 48 + byte0, 98, 20, bm.a("gui.stats")));
        auc1.g = f.B() && !f.C().al();
        i.add(new auc(8, g / 2 - 100, h / 4 + 144 + byte0, "WMLL Options..."));
		if (WMLL.i.debugClassPresent)
			i.add(new auc(9001, g - 52, h - 22, 50, 20, "Reload"));
    }

    protected void a(auc auc1)
    {
        switch(auc1.f)
        {
		case 8:
        	f.a(new WMLLOptionsMenu(this));
        	break;
        case 9001:
        	f.a(new avg());
        	break;
        	
        case 0: // '\0'
            f.a(new ave(this, f.y));
            break;

        case 1: // '\001'
            auc1.g = false;
            f.E.a(jq.j, 1);
            f.e.C();
            f.a((azr)null);
            f.a(new bgc());
            break;

        case 4: // '\004'
            f.a((avm)null);
            f.h();
            f.A.f();
            break;

        case 5: // '\005'
            f.a(new avy(f.E));
            break;

        case 6: // '\006'
            f.a(new avz(this, f.E));
            break;

        case 7: // '\007'
            f.a(new avr(this));
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
