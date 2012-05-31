// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class ok extends aay
{

    private int a;
    private int b;

    public ok()
    {
        a = 0;
        b = 0;
    }

    public void c()
    {
        a = 0;
        s.clear();
        byte byte0 = -16;
        s.add(new ahy(1, q / 2 - 100, r / 4 + 120 + byte0, ej.a("menu.returnToMenu")));
        if(!p.z())
        {
            ((ahy)s.get(0)).e = ej.a("menu.disconnect");
        }
        s.add(new ahy(4, q / 2 - 100, r / 4 + 24 + byte0, ej.a("menu.returnToGame")));
        //s.add(new ahy(0, q / 2 - 100, r / 4 + 96 + byte0, ej.a("menu.options")));
        s.add(new ahy(5, q / 2 - 100, r / 4 + 48 + byte0, 98, 20, ej.a("gui.achievements")));
        s.add(new ahy(6, q / 2 + 2, r / 4 + 48 + byte0, 98, 20, ej.a("gui.stats")));
		s.add(new ahy(0, q / 2 - 100, r / 4 + 96 + byte0, 98, 20, ej.a("menu.options")));
        s.add(new ahy(7, q / 2 + 2, r / 4 + 96 + byte0, 98, 20, "WMLL Options..."));
		if (WMLL.debugClassPresent)
			s.add(new ahy(9001, q - 52, r - 22, 50, 20, "Reload"));
    }

    protected void a(ahy ahy1)
    {
        switch(ahy1.f)
        {
        case 7:
        	p.a(new WMLLOptions(this));
        	return;
        case 9001:
        	p.a(new ok());
        	return;
        case 0: // '\0'
            p.a(new dk(this, p.z));
            break;

        case 1: // '\001'
            p.I.a(jk.j, 1);
            p.f.f();
            p.a((mf)null);
            p.a(new adq());
            break;

        case 4: // '\004'
            p.a((aay)null);
            p.g();
            break;

        case 5: // '\005'
            p.a(new wb(p.I));
            break;

        case 6: // '\006'
            p.a(new er(this, p.I));
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
