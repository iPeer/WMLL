// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class aty extends aue
{

    private int a;
    private int b;

    public aty()
    {
        a = 0;
        b = 0;
    }

    public void A_()
    {
        a = 0;
        i.clear();
        byte byte0 = -16;
        i.add(new ast(1, g / 2 - 100, h / 4 + 120 + byte0, bm.a("menu.returnToMenu")));
        if(!f.A())
        {
            ((ast)i.get(0)).e = bm.a("menu.disconnect");
        }
        i.add(new ast(4, g / 2 - 100, h / 4 + 24 + byte0, bm.a("menu.returnToGame")));
        i.add(new ast(0, g / 2 - 100, h / 4 + 96 + byte0, 98, 20, bm.a("menu.options")));
        ast ast1;
        i.add(ast1 = new ast(7, g / 2 + 2, h / 4 + 96 + byte0, 98, 20, bm.a("menu.shareToLan")));
        i.add(new ast(5, g / 2 - 100, h / 4 + 48 + byte0, 98, 20, bm.a("gui.achievements")));
        i.add(new ast(6, g / 2 + 2, h / 4 + 48 + byte0, 98, 20, bm.a("gui.stats")));
		i.add(new ast(8, g / 2 - 100, h / 4 + 144 + byte0, "WMLL Options..."));
		if (WMLL.i.debugClassPresent)
			i.add(new ast(9001, g - 52, h - 22, 50, 20, "Reload"));
        ast1.g = f.B() && !f.C().al();
    }

    protected void a(ast ast1)
    {
        switch(ast1.f)
        {
		case 8:
        	f.a(new WMLLOptionsMenu(this));
        	break;
        case 9001:
        	f.a(new aty());
        	break;
        	
        case 0: // '\0'
            f.a(new atw(this, f.y));
            break;

        case 1: // '\001'
            ast1.g = false;
            f.E.a(jq.j, 1);
            f.e.C();
            f.a((ayh)null);
            f.a(new bem());
            break;

        case 4: // '\004'
            f.a((aye)null);
            f.h();
            f.A.f();
            break;

        case 5: // '\005'
            f.a(new auq(f.E));
            break;

        case 6: // '\006'
            f.a(new aur(this, f.E));
            break;

        case 7: // '\007'
            f.a(new auj(this));
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
        a(l, "Game menu", g / 2, 40, 0xffffff);
        super.a(i, j, f);
    }
}
