// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class on extends abf
{

    private int a;
    private int b;

    public on()
    {
        a = 0;
        b = 0;
    }

    @SuppressWarnings("unchecked")
	public void c()
    {
        a = 0;
        s.clear();
        byte byte0 = -16;
        s.add(new aie(1, q / 2 - 100, r / 4 + 120 + byte0, ei.a("menu.returnToMenu")));
        if(!p.A())
        {
            ((aie)s.get(0)).e = ei.a("menu.disconnect");
        }
        s.add(new aie(4, q / 2 - 100, r / 4 + 24 + byte0, ei.a("menu.returnToGame")));
        aie aie1;
        s.add(aie1 = new aie(7, q / 2 + 2, r / 4 + 96 + byte0, 98, 20, ei.a("menu.shareToLan")));
        s.add(new aie(5, q / 2 - 100, r / 4 + 48 + byte0, 98, 20, ei.a("gui.achievements")));
        s.add(new aie(6, q / 2 + 2, r / 4 + 48 + byte0, 98, 20, ei.a("gui.stats")));
		s.add(new aie(0, q / 2 - 100, r / 4 + 96 + byte0, 98, 20, ei.a("menu.options")));
		s.add(new aie(8, q / 2 - 100, r / 4 + 144 + byte0, "WMLL Options..."));
		if (WMLL.debugClassPresent)
			s.add(new aie(9001, q - 52, r - 22, 50, 20, "Reload"));
        aie1.h = p.B() && !p.C().af();
    }

    protected void a(aie aie1)
    {
        switch(aie1.f)
        {
		case 9001:
        	p.a(new on());
        	return;
        case 8:
        	p.a(new WMLLOptions(this));
        	return;
		
        case 0: // '\0'
            p.a(new dh(this, p.z));
            break;

        case 1: // '\001'
            aie1.h = false;
            p.I.a(jk.j, 1);
            p.f.f();
            p.a((mh)null);
            p.a(new adx());
            break;

        case 4: // '\004'
            p.a((abf)null);
            p.h();
            break;

        case 5: // '\005'
            p.a(new wf(p.I));
            break;

        case 6: // '\006'
            p.a(new ep(this, p.I));
            break;

        case 7: // '\007'
            p.a(new my(this));
            break;
        }
    }

    public void R_()
    {
        super.R_();
        b++;
    }

    public void a(int i, int j, float f)
    {
        t_();
        a(u, "Game menu", q / 2, 40, 0xffffff);
        super.a(i, j, f);
    }
}
